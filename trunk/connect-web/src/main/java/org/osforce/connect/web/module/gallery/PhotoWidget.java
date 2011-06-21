package org.osforce.connect.web.module.gallery;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.math.NumberUtils;
import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.entity.gallery.Album;
import org.osforce.connect.entity.gallery.Photo;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.AttachmentService;
import org.osforce.connect.service.gallery.AlbumService;
import org.osforce.connect.service.gallery.PhotoService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.module.util.AttachmentUtil;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.commons.collection.CollectionUtil;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.bind.annotation.RequestAttr;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 21, 2011 - 11:16:51 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/gallery/photo")
public class PhotoWidget {

	private PhotoService photoService;
	private AlbumService albumService;
	private AttachmentService attachmentService;
	
	public PhotoWidget() {
	}
	
	@Autowired
	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}
	
	@Autowired
	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	@Autowired
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	@RequestMapping("/list-view")
	@Permission({"photo-view"})
	public String doListView(@RequestParam Long albumId, 
			Page<Photo> page, Model model) {
		page = photoService.getPhotoPage(page, albumId);
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "gallery/photo-list";
	}
	
	@RequestMapping("/detail-view")
	@Permission({"photo-view"})
	public String doDetailView(@RequestParam(required=false) Long albumId, 
			@RequestParam(required=false) Long photoId, Model model) {
		Photo photo = null;
		if(albumId!=null) {
			List<Photo> photos = photoService.getPhotoList(albumId);
			photo = photos.get(0);
		}
		if(photoId!=null) {
			photo = photoService.getPhoto(photoId);
		}
		model.addAttribute(AttributeKeys.PHOTO_KEY_READABLE, photo);
		return "gallery/photo-detail";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"photo-add", "photo-edit"}, userRequired=true, projectRequired=true)
	public String doFormView(
			@RequestParam(required=false) Long albumId,
			@RequestParam(required=false) Long photoId, 
			@ModelAttribute @Valid Photo photo, BindingResult result,
			@RequestAttr Project project, @RequestAttr User user, Model model, Boolean showErrors) {
		if(!showErrors) {
			photo.setAlbumId(albumId);
			photo.setEnteredId(user.getId());
			photo.setModifiedId(user.getId());
			if(photoId!=null) {
				photo = photoService.getPhoto(photoId);
			}
			model.addAttribute(AttributeKeys.PHOTO_KEY_READABLE, photo);
		}
		//
		List<Album> albums = albumService.getAlbumList(project.getId());
		Map<String, String> albumOptions = CollectionUtil.newHashMap();
		for(Album album : albums) {
			albumOptions.put(album.getId().toString(), album.getName());
		}
		model.addAttribute(AttributeKeys.ALBUM_LIST_KEY_READABLE, albums);
		model.addAttribute("albumOptions", albumOptions);
		return "gallery/photo-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"photo-add", "photo-edit"}, userRequired=true, projectRequired=true)
	public String doFormAction(
			@RequestParam MultipartFile file,
			@ModelAttribute @Valid Photo photo, BindingResult result, 
			@RequestAttr Project project, Model model, WebRequest request) throws IOException {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_GALLERY);
			return "page:/gallery/photo-form";
		}
		//
		if(file.getSize()>0) {
			Attachment attachment = AttachmentUtil.parse(file);
			attachmentService.createAttachment(attachment);
			AttachmentUtil.write(attachment);
			//
			if(photo.getRealFileId()!=null) {
				Attachment needDelete = attachmentService.getAttachment(photo.getRealFileId());
				AttachmentUtil.delete(needDelete);
				attachmentService.deleteAttachment(photo.getRealFileId());
			}
			photo.setRealFileId(attachment.getId());
		}
		//
		if(photo.getId()==null) {
			photoService.createPhoto(photo);
		} else {
			photoService.updatePhoto(photo);
		}
		//
		syncSessionPhotoList(photo, request, false);
		return String.format("redirect:/%s/gallery/photo/form?albumId=%s", 
				project.getUniqueId(), photo.getAlbumId());
	}
	
	@RequestMapping(value="/delete-action", method=RequestMethod.GET)
	@Permission(value={"photo-edit"}, userRequired=true)
	public String doDeleteAction(@RequestParam Long photoId, WebRequest request) {
		Photo photo = photoService.getPhoto(photoId);
		String uniqueId = photo.getAlbum().getProject().getUniqueId();
		Long albumId = photo.getAlbumId();
		// delete from session
		syncSessionPhotoList(photo, request, true);
		// delete from database
		AttachmentUtil.delete(photo.getRealFile());
		attachmentService.deleteAttachment(photo.getRealFileId());
		photoService.deletePhoto(photoId);
		return String.format("redirect:/%s/gallery/photo/form?albumId=%s", uniqueId, albumId);
	}
	
	@SuppressWarnings("unchecked")
	public void syncSessionPhotoList(Photo photo, WebRequest request, Boolean reverse) {
		List<Photo> photos = (List<Photo>) request.getAttribute(
				AttributeKeys.PHOTO_LIST_KEY_READABLE, WebRequest.SCOPE_SESSION);
		List<Photo> tmp = CollectionUtil.newArrayList();
		if(reverse) {
			for(Photo p : photos) {
				if(NumberUtils.compare(p.getId(), photo.getId())!=0) {
					tmp.add(p);
				} 
			}
		} else {
			if(photos!=null) {
				for(Photo p : photos) {
					if(NumberUtils.compare(p.getId(), photo.getId())!=0) {
						tmp.add(p);
					} 
				}
			}
			tmp.add(0, photo);
		}
		request.setAttribute(AttributeKeys.PHOTO_LIST_KEY_READABLE, tmp, WebRequest.SCOPE_SESSION);
	}
	
}
