package org.osforce.connect.service.document.impl;

import java.util.Date;
import java.util.List;

import org.osforce.connect.dao.commons.AttachmentDao;
import org.osforce.connect.dao.document.FileDao;
import org.osforce.connect.dao.document.FolderDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.entity.document.File;
import org.osforce.connect.entity.document.Folder;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.document.FileService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:37:35 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class FileServiceImpl implements FileService {

	private FileDao fileDao;
	private FolderDao folderDao;
	private UserDao userDao;
	private AttachmentDao attachmentDao;
	
	public FileServiceImpl() {
	}
	
	@Autowired
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}
	
	@Autowired
	public void setFolderDao(FolderDao folderDao) {
		this.folderDao = folderDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	public File getFile(Long fileId) {
		return fileDao.get(fileId);
	}

	public void createFile(File file) {
		updateFile(file);
	}

	public void updateFile(File file) {
		if(file.getEnteredId()!=null) {
			User enteredBy = userDao.get(file.getEnteredId());
			file.setEnteredBy(enteredBy);
		} 
		if(file.getModifiedId()!=null) {
			User modifiedBy = userDao.get(file.getModifiedId());
			file.setModifiedBy(modifiedBy);
		}
		if(file.getFolderId()!=null) {
			Folder folder = folderDao.get(file.getFolderId());
			file.setFolder(folder);
		}
		if(file.getRealFileId()!=null) {
			Attachment realFile = attachmentDao.get(file.getRealFileId());
			file.setRealFile(realFile);
		}
		Date now = new Date();
		file.setModified(now);
		if(file.getId()==null) {
			file.setEntered(now);
			fileDao.save(file);
		} else {
			fileDao.update(file);
		}
	}

	public void deleteFile(Long fileId) {
		fileDao.delete(fileId);
	}
	
	public List<File> getFileList(Long folderId) {
		return fileDao.findFileList(folderId);
	}
	
	public Page<File> getFilePage(Page<File> page, Project project,
			Boolean featured) {
		return fileDao.findFilePage(page, project.getId(), featured);
	}
}
