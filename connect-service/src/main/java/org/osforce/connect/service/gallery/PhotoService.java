package org.osforce.connect.service.gallery;

import java.util.List;

import org.osforce.connect.entity.gallery.Photo;
import org.osforce.connect.entity.system.Project;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:33:25 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface PhotoService {

	Photo getPhoto(Long photoId);
	
	void createPhoto(Photo photo);
	
	void updatePhoto(Photo photo);
	
	void deletePhoto(Long photoId);
	
	List<Photo> getPhotoList(Long albumId);

	Page<Photo> getPhotoPage(Page<Photo> page, Long albumId);

	Page<Photo> getPhotoPage(Page<Photo> page, Project project);

}
