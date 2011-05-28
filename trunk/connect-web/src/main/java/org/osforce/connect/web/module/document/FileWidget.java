package org.osforce.connect.web.module.document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.osforce.connect.entity.document.File;
import org.osforce.connect.entity.document.Folder;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.document.FileService;
import org.osforce.connect.service.document.FolderService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.security.annotation.Permission;
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
 * @create May 21, 2011 - 1:01:28 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/document/file")
public class FileWidget {

	private FileService fileService;
	private FolderService folderService;
	
	public FileWidget() {
	}
	
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	@Autowired
	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}
	
	@RequestMapping("/list-view")
	@Permission({"file-view"})
	public String doListView(@RequestParam(required=false) Long folderId,
			Project project, Model model) {
		// 
		if(folderId==null) {
			List<Folder> folders = folderService.getFolderList(project.getId());
			if(folders.size() > 0) {
				folderId = folders.get(0).getId();
			}
		}
		//
		List<File> files = Collections.emptyList();
		if(folderId!=null) {
			files = fileService.getFileList(folderId);
			Folder folder = folderService.getFolder(folderId);
			model.addAttribute(AttributeKeys.FOLDER_KEY_READABLE, folder);
			List<Folder> pathFolders = new ArrayList<Folder>();
			do {
				pathFolders.add(0, folder);
			} while ((folder = folder.getParent())!=null);
			model.addAttribute(AttributeKeys.FOLDER_LIST_KEY_READABLE, pathFolders);
		}
		model.addAttribute(AttributeKeys.FILE_ITEM_LIST_KEY_READABLE, files);
		return "document/file-list";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"file-add", "file-edit"}, userRequired=true, projectRequired=true)
	public String doFormView(@RequestParam(required=false) Long folderId, 
			@RequestParam(required=false) Long fileId,
			@ModelAttribute @Valid File file, BindingResult result,
			Project project, User user, Model model, Boolean showErrors) {
		if(!showErrors) {
			Folder folder = null;
			if(folderId!=null) {
				folder = folderService.getFolder(folderId);
			} 
			file.setFolder(folder);
			file.setEnteredBy(user);
			file.setModifiedBy(user);
			if(fileId!=null) {
				file = fileService.getFile(fileId);
			}
		}
		//
		List<Folder> folders = folderService.getFolderList(project.getId());
		model.addAttribute(AttributeKeys.FOLDER_LIST_KEY_READABLE, folders);
		return "document/file-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"file-add", "file-edit"}, userRequired=true, projectRequired=true)
	public String doFormAction() {
		return "";
	}
	
}
