package org.osforce.connect.dao.gallery.impl;

import org.osforce.connect.dao.gallery.PhotoDao;
import org.osforce.connect.entity.gallery.Photo;
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
	
	static final String JPQL0 = "FROM Photo AS p WHERE p.album.id = ?1 ORDER BY p.entered";
	public Page<Photo> findPhotoPage(Page<Photo> page, Long albumId) {
		return findPage(page, JPQL0, albumId);
	}
	
}
