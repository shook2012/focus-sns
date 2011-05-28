package org.osforce.connect.web.module.document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.osforce.connect.entity.document.Folder;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.security.annotation.Permission;
import org.osforce.connect.service.document.FolderService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 21, 2011 - 12:57:04 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/document/folder")
public class FolderWidget {

	private FolderService folderService;
	
	public FolderWidget() {
	}
	
	@Autowired
	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}
	
	@RequestMapping("/tree-view")
	@Permission({"folder-view"})
	public  String doTreeView() {
		return "document/folder-tree";
	}

	@RequestMapping(value="/tree-action", method=RequestMethod.POST)
	public @ResponseBody Object doTreeAction(
			@RequestParam(required=false) Long id, Project project) {
		List<Folder> folders = Collections.emptyList();
		List<Map<String, Object>> nodeList = new ArrayList<Map<String, Object>>();
		if(id==-1) {
			Map<String, Object> node = new HashMap<String, Object>();
			node.put("id", "0");
			node.put("data", "ROOT");
			node.put("haschild", true);
			nodeList.add(node);
		} else if(id==0) { 
			folders = folderService.getFolderList(project.getId());
		} else {
			folders = folderService.getFolderList(project.getId(), id);
		}
		for(Folder folder : folders) {
			Map<String, Object> node = new HashMap<String, Object>();
			node.put("id", String.valueOf(folder.getId()));
			node.put("data", folder.getName());
			node.put("haschild", true);
			nodeList.add(node);
		}
		return nodeList;
	}

	@RequestMapping("/form-view")
	@Permission(value={"folder-add", "folder-edit"}, userRequired=true, projectRequired=true)
	public String doFormView(@RequestParam(required=false) Long folderId, 
			@RequestParam(required=false) Long parentFolderId,
			@ModelAttribute @Valid Folder folder, BindingResult result,
			Project project, User user, Model model, Boolean showErrors) {
		if(!showErrors) {
			folder.setModifiedBy(user);
			folder.setEnteredBy(user);
			folder.setProject(project);
			if(folderId!=null) {
				folder = folderService.getFolder(folderId);
			}
			Folder parentFolder = null;
			if(parentFolderId!=null) {
				parentFolder = folderService.getFolder(parentFolderId);
				folder.setParent(parentFolder);
			}
			model.addAttribute(AttributeKeys.FOLDER_KEY_READABLE, folder);
		}
		return "document/folder-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"folder-add", "folder-edit"}, userRequired=true, projectRequired=true)
	public String doFormAction(@ModelAttribute @Valid Folder folder, 
			BindingResult result, Model model, Project project) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_DOCUMENT);
			return "page:/document/folder-form";
		}
		//
		if(folder.getId()==null) {
			folderService.createFolder(folder);
		} else {
			folderService.updateFolder(folder);
		}
		return String.format("redirect:/%s/document/file/list?folderId=%s", 
				project.getUniqueId(), folder.getId());
	}

}
