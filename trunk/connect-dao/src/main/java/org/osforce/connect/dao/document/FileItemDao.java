package org.osforce.connect.dao.document;

import java.util.List;

import org.osforce.connect.entity.document.FileItem;
import org.osforce.spring4me.dao.BaseDao;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:17:54 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface FileItemDao extends BaseDao<FileItem> {

	/**
	 * Find file items 
	 * @param folderId
	 * @return
	 */
	List<FileItem> findFileItemList(Long folderId);

}
