package org.osforce.connect.dao.gallery;

import java.util.List;

import org.osforce.connect.entity.gallery.Photo;
import org.osforce.connect.entity.system.Project;
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
	 * Find photo list order by entered DESC
	 * @param albumId
	 * @return
	 */
	List<Photo> findPhotoList(Long albumId);
	
	/**
	 * Find photo order by entered DESC
	 * @param page
	 * @param albumId
	 * @return
	 */
	Page<Photo> findPhotoPage(Page<Photo> page, Long albumId);

	Page<Photo> findPhotoPage(Page<Photo> page, Project project);

}
