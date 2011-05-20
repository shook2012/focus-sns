package org.osforce.connect.dao.document.impl;

import java.util.List;

import org.osforce.connect.dao.document.FolderDao;
import org.osforce.connect.entity.document.Folder;
import org.osforce.spring4me.dao.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:19:48 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class FolderDaoImpl extends AbstractDao<Folder> 
	implements FolderDao {

	public FolderDaoImpl() {
		super(Folder.class);
	}
	
	static final String FIND_ROOT_JPQL = "FROM Folder f WHERE f.parent IS NULL AND f.project.id = ?1";
	public List<Folder> findFolderList(Long projectId) {
		return findList(FIND_ROOT_JPQL, projectId);
	}
}
