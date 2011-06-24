package org.osforce.connect.dao.gallery.impl;

import java.util.List;

import org.osforce.connect.dao.gallery.PhotoDao;
import org.osforce.connect.entity.gallery.Photo;
import org.osforce.connect.entity.system.Project;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 22, 2011 - 10:26:32 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class PhotoDaoImpl extends AbstractDao<Photo>
	implements PhotoDao {

	public PhotoDaoImpl() {
		super(Photo.class);
	}
	
	static final String JPQL0 = "FROM Photo AS p WHERE p.album.id = ?1";
	public List<Photo> findPhotoList(Long albumId) {
		return findList(JPQL0, albumId);
	}
	
	static final String JPQL1 = "FROM Photo AS p WHERE p.album.id = ?1";
	public Page<Photo> findPhotoPage(Page<Photo> page, Long albumId) {
		return findPage(page, JPQL1, albumId);
	}
	
	static final String JPQL2 = "FROM Photo AS p WHERE p.album.project.id = ?1";
	public Page<Photo> findPhotoPage(Page<Photo> page, Project project) {
		return findPage(page, JPQL2, project.getId());
	}
	
}
