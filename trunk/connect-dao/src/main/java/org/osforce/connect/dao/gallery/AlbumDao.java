package org.osforce.connect.dao.gallery;

import java.util.List;

import org.osforce.connect.entity.gallery.Album;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 22, 2011 - 10:25:11 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface AlbumDao extends BaseDao<Album> {

	/**
	 * Find albums
	 * @param projectId
	 * @return
	 */
	List<Album> findAlbumList(Long projectId);
	
	/**
	 * Find album page
	 * @param page
	 * @param projectId
	 * @return
	 */
	Page<Album> findAlbumPage(Page<Album> page, Long projectId);

}
