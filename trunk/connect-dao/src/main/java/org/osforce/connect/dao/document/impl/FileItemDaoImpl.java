package org.osforce.connect.dao.document.impl;

import java.util.List;

import org.osforce.connect.dao.document.FileItemDao;
import org.osforce.connect.entity.document.FileItem;
import org.osforce.spring4me.dao.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:18:23 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class FileItemDaoImpl extends AbstractDao<FileItem>
	implements FileItemDao {

	public FileItemDaoImpl() {
		super(FileItem.class);
	}
	
	static final String JPQL0 = "FROM FileItem AS fi WHERE fi.folder.id = ?1";
	public List<FileItem> findFileItemList(Long folderId) {
		return findList(JPQL0, folderId);
	}
}
