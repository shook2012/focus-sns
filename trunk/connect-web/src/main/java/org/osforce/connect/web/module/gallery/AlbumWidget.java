package org.osforce.connect.web.module.gallery;

import javax.validation.Valid;

import org.osforce.connect.entity.gallery.Album;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.gallery.AlbumService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.security.annotation.Permission;
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

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 21, 2011 - 11:16:16 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/gallery/album")
public class AlbumWidget {

	private AlbumService albumService;
	
	public AlbumWidget() {
	}

	@Autowired
	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	@RequestMapping("/list-view")
	@Permission({"album-view"})
	public String doListView(Page<Album> page, 
			@RequestAttr Project project, Model model) {
		page = albumService.getAlbumPage(page, project.getId());
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "gallery/album-list";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"album-add", "album-edit"}, userRequired=true, projectRequired=true)
	public String doFormView(@RequestParam(required=false) Long albumId,
			@ModelAttribute @Valid Album album, BindingResult result,
			@RequestAttr Project project, @RequestAttr User user, Model model, Boolean showErrors) {
		if(!showErrors) {
			album.setEnteredBy(user);
			album.setModifiedBy(user);
			album.setProject(project);
			if(albumId!=null) {
				album = albumService.getAlbum(albumId);
			}
		}
		model.addAttribute(AttributeKeys.ALBUM_KEY_READABLE, album);
		return "gallery/album-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"album-add", "album-edit"}, userRequired=true, projectRequired=true)
	public String doFormAction(@ModelAttribute @Valid Album album, 
			BindingResult result, Model model, @RequestAttr Project project) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_GALLERY);
			return "page:/gallery/album-form";
		}
		//
		if(album.getId()==null) {
			albumService.createAlbum(album);
		} else {
			albumService.updateAlbum(album);
		}
		return String.format("redirect:/%s/gallery/album/list", project.getUniqueId());
	}
	
}
