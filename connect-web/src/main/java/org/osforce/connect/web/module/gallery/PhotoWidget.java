package org.osforce.connect.web.module.gallery;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.entity.gallery.Album;
import org.osforce.connect.entity.gallery.Photo;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.security.annotation.Permission;
import org.osforce.connect.service.commons.AttachmentService;
import org.osforce.connect.service.gallery.AlbumService;
import org.osforce.connect.service.gallery.PhotoService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.module.util.AttachmentUtil;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
			@RequestParam(required=false) Long photoId,
			Page<Photo> page, Model model) {
		if(albumId!=null) {
			page = photoService.getPhotoPage(page, albumId);
			model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
			//
			Photo photo = page.getResult().isEmpty() ? null:page.getResult().get(0);
			if(photoId!=null) {
				photo = photoService.getPhoto(photoId);
			}
			model.addAttribute(AttributeKeys.PHOTO_KEY_READABLE, photo);
		}
		return "gallery/photo-list";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"photo-add", "photo-edit"}, userRequired=true, projectRequired=true)
	public String doFormView(
			@RequestParam(required=false) Long albumId,
			@RequestParam(required=false) Long photoId, 
			@ModelAttribute @Valid Photo photo, BindingResult result,
			Project project, User user, Model model, Boolean showErrors) {
		if(!showErrors) {
			photo.setAlbumId(albumId);
			photo.setEnteredId(user.getId());
			photo.setModifiedId(user.getId());
			model.addAttribute(AttributeKeys.PHOTO_KEY_READABLE, photo);
			//
			List<Album> albums = albumService.getAlbumList(project.getId());
			Map<String, String> albumOptions = new HashMap<String, String>();
			for(Album album : albums) {
				albumOptions.put(album.getId().toString(), album.getName());
			}
			model.addAttribute(AttributeKeys.ALBUM_LIST_KEY_READABLE, albums);
			model.addAttribute("albumOptions", albumOptions);
		}
		return "gallery/photo-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"photo-add", "photo-edit"}, userRequired=true, projectRequired=true)
	public String doActionForm(
			@RequestParam MultipartFile file,
			@ModelAttribute @Valid Photo photo, 
			BindingResult result, Model model, Project project) throws IOException {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_GALLERY);
			return "page:/gallery/photo-form";
		}
		//
		Attachment attachment = new Attachment();
		attachment.setContentType(file.getContentType());
		attachment.setFileName(file.getOriginalFilename());
		attachment.setSize(file.getSize());
		attachment.setBytes(file.getBytes());
		attachmentService.createAttachment(attachment);
		AttachmentUtil.write(attachment);
		//
		photo.setRealFile(attachment);
		if(photo.getId()==null) {
			photoService.createPhoto(photo);
		} else {
			photoService.updatePhoto(photo);
		}
		return String.format("redirect:/%s/gallery/photo/form?photoId=%s", 
				project.getUniqueId(), photo.getId());
	}
	
}
