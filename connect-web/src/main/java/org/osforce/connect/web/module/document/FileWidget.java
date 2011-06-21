package org.osforce.connect.web.module.document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.math.NumberUtils;
import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.entity.document.File;
import org.osforce.connect.entity.document.Folder;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.AttachmentService;
import org.osforce.connect.service.document.FileService;
import org.osforce.connect.service.document.FolderService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.module.util.AttachmentUtil;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.commons.collection.CollectionUtil;
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
 * @create May 21, 2011 - 1:01:28 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/document/file")
public class FileWidget {

	private FileService fileService;
	private FolderService folderService;
	private AttachmentService attachmentService;
	
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
	
	@Autowired
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	@RequestMapping("/list-view")
	@Permission({"file-view"})
	public String doListView(@RequestAttr Project project,
			@RequestParam(required=false) Long folderId, Model model) {
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
			List<Folder> pathFolders = CollectionUtil.newArrayList();
			do {
				pathFolders.add(0, folder);
			} while ((folder = folder.getParent())!=null);
			model.addAttribute(AttributeKeys.FOLDER_LIST_KEY_READABLE, pathFolders);
		}
		model.addAttribute(AttributeKeys.FILE_LIST_KEY_READABLE, files);
		return "document/file-list";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"file-add", "file-edit"}, userRequired=true, projectRequired=true)
	public String doFormView(
			@RequestParam(required=false) Long fileId,
			@RequestParam(required=false) Long folderId, 
			@ModelAttribute @Valid File file, BindingResult result,
			@RequestAttr Project project, @RequestAttr User user, Model model, Boolean showErrors) {
		if(!showErrors) {
			file.setFolderId(folderId);
			file.setEnteredBy(user);
			file.setModifiedBy(user);
			if(fileId!=null) {
				file = fileService.getFile(fileId);
			}
			model.addAttribute(AttributeKeys.FILE_KEY_READABLE, file);
		}
		//
		List<Folder> folders = folderService.getFolderList(project.getId());
		model.addAttribute(AttributeKeys.FOLDER_LIST_KEY_READABLE, folders);
		return "document/file-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"file-add", "file-edit"}, userRequired=true, projectRequired=true)
	public String doFormAction(@RequestParam MultipartFile file,
			@ModelAttribute("file") @Valid File fileItem, BindingResult result, 
			Model model, @RequestAttr Project project, WebRequest request) throws IOException {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_DOCUMENT);
			return "page:/document/file-form";
		}
		//
		if(file.getSize() > 0) {
			Attachment attachment = AttachmentUtil.parse(file);
			attachmentService.createAttachment(attachment);
			AttachmentUtil.write(attachment);
			//
			if(fileItem.getRealFileId()!=null) {
				Attachment needDelete = attachmentService.getAttachment(fileItem.getRealFileId());
				AttachmentUtil.delete(needDelete);
				attachmentService.deleteAttachment(fileItem.getRealFileId());
			}
			fileItem.setRealFileId(attachment.getId());
		}
		if(fileItem.getId()==null) {
			fileService.createFile(fileItem);
		} else {
			fileService.updateFile(fileItem);
		}
		//
		syncSessionFileList(fileItem, request, Boolean.FALSE);
		return String.format("redirect:/%s/document/file/form?folderId=%s", 
				project.getUniqueId(), fileItem.getFolderId());
	}
	
	@RequestMapping(value="/delete-action", method=RequestMethod.GET)
	@Permission(value={"file-edit"}, userRequired=true)
	public String doDeleteAction(@RequestParam Long fileId, WebRequest request) {
		File file = fileService.getFile(fileId);
		String uniqueId = file.getFolder().getProject().getUniqueId();
		Long folderId = file.getFolderId();
		//
		syncSessionFileList(file, request, Boolean.TRUE);
		//
		AttachmentUtil.delete(file.getRealFile());
		attachmentService.deleteAttachment(file.getRealFileId());
		fileService.deleteFile(fileId);
		return String.format("redirect:/%s/document/file/list?folderId=%s", uniqueId, folderId);
	}
	
	@SuppressWarnings("unchecked")
	public void syncSessionFileList(File file, WebRequest request, Boolean reverse) {
		List<File> files = (List<File>) request.getAttribute(
				AttributeKeys.FILE_LIST_KEY_READABLE, WebRequest.SCOPE_SESSION);
		List<File> tmp = CollectionUtil.newArrayList();
		if(reverse) {
			for(File f : files) {
				if(NumberUtils.compare(f.getId(), file.getId())!=0) {
					tmp.add(f);
				} 
			}
		} else {
			if(files!=null) {
				for(File f : files) {
					if(NumberUtils.compare(f.getId(), file.getId())!=0) {
						tmp.add(f);
					} 
				}
			}
			tmp.add(0, file);
		}
		request.setAttribute(AttributeKeys.FILE_LIST_KEY_READABLE, tmp, WebRequest.SCOPE_SESSION);
	}
	
}
