package org.osforce.connect.dao.gallery.impl;

import java.util.List;

import org.osforce.connect.dao.gallery.AlbumDao;
import org.osforce.connect.entity.gallery.Album;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 22, 2011 - 10:27:34 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class AlbumDaoImpl extends  AbstractDao<Album>
	implements AlbumDao {
	
	public AlbumDaoImpl() {
		super(Album.class);
	}

	static final String JPQL0 = "FROM Album AS a WHERE a.project.id = ?1 ORDER BY a.entered DESC";
	public List<Album> findAlbumList(Long projectId) {
		return findList(JPQL0, projectId);
	}
	
	public Page<Album> findAlbumPage(Page<Album> page, Long projectId) {
		return findPage(page, JPQL0, projectId);
	}

}
