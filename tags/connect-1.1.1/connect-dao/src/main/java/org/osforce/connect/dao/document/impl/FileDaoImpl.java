package org.osforce.connect.dao.document.impl;

import java.util.List;

import org.osforce.connect.dao.document.FileDao;
import org.osforce.connect.entity.document.File;
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
public class FileDaoImpl extends AbstractDao<File>
	implements FileDao {

	public FileDaoImpl() {
		super(File.class);
	}
	
	static final String JPQL0 = "FROM File AS f WHERE f.folder.id = ?1";
	public List<File> findFileList(Long folderId) {
		return findList(JPQL0, folderId);
	}
}
