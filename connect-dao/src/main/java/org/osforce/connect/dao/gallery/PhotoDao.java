package org.osforce.connect.dao.gallery;

import org.osforce.connect.entity.gallery.Photo;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 22, 2011 - 10:25:34 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface PhotoDao extends BaseDao<Photo> {

	/**
	 * Find photo order by entered DESC
	 * @param page
	 * @param albumId
	 * @return
	 */
	Page<Photo> findPhotoPage(Page<Photo> page, Long albumId);

}
