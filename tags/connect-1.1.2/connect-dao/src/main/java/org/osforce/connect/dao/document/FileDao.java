package org.osforce.connect.dao.document;

import java.util.List;

import org.osforce.connect.entity.document.File;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:17:54 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface FileDao extends BaseDao<File> {

	/**
	 * Find file items 
	 * @param folderId
	 * @return
	 */
	List<File> findFileList(Long folderId);

	Page<File> findFilePage(Page<File> page, Long projectId, Boolean featured);

}
