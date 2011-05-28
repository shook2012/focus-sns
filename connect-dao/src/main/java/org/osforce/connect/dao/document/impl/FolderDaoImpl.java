package org.osforce.connect.dao.document.impl;

import java.util.List;

import org.osforce.connect.dao.document.FolderDao;
import org.osforce.connect.entity.document.Folder;
import org.osforce.spring4me.dao.AbstractDao;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

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
	
	static final String JPQL0 = "FROM Folder f WHERE f.project.id = ?1 %s";
	public List<Folder> findFolderList(Long projectId, Long parentId) {
		Assert.notNull(projectId, "Parameter project id can not be null!");
		if(parentId==null) {
			return findList(String.format(JPQL0, "AND f.parent IS NULL"), projectId);
		} else {
			return findList(String.format(JPQL0, "AND f.parent.id = ?2"), projectId, parentId);
		}
	}
}
