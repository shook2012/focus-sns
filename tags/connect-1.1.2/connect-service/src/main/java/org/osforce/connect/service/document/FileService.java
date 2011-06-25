package org.osforce.connect.service.document;

import java.util.List;

import org.osforce.connect.entity.document.File;
import org.osforce.connect.entity.system.Project;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:37:10 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface FileService {

	File getFile(Long fileId);
	
	void createFile(File file);
	
	void updateFile(File file);
	
	void deleteFile(Long fileId);

	List<File> getFileList(Long folderId);

	Page<File> getFilePage(Page<File> page, Project project, Boolean featured);
}
