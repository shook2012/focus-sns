package org.osforce.connect.service.gallery.impl;

import java.util.Date;
import java.util.List;

import org.osforce.connect.dao.gallery.AlbumDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.gallery.Album;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.gallery.AlbumService;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:42:22 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class AlbumServiceImpl extends AbstractDao<Album>
	implements AlbumService {

	private UserDao userDao;
	private AlbumDao albumDao;
	private ProjectDao projectDao;
	
	public AlbumServiceImpl() {
		super(Album.class);
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setAlbumDao(AlbumDao albumDao) {
		this.albumDao = albumDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public Album getAlbum(Long albumId) {
		return albumDao.get(albumId);
	}

	public void createAlbum(Album album) {
		updateAlbum(album);
	}

	public void updateAlbum(Album album) {
		if(album.getEnteredId()!=null) {
			User enteredBy = userDao.get(album.getEnteredId());
			album.setEnteredBy(enteredBy);
		}
		if(album.getModifiedId()!=null) {
			User modifiedBy = userDao.get(album.getModifiedId());
			album.setModifiedBy(modifiedBy);
		}
		if(album.getProjectId()!=null) {
			Project project = projectDao.get(album.getProjectId());
			album.setProject(project);
		}
		Date now = new Date();
		album.setEntered(now);
		if(album.getId()==null) {
			album.setEntered(now);
			albumDao.save(album);
		} else {
			albumDao.update(album);
		}
	}

	public void deleteAlbum(Long albumId) {
		albumDao.delete(albumId);
	}
	
	public Page<Album> getAlbumPage(Page<Album> page, Long projectId) {
		return albumDao.findAlbumPage(page, projectId);
	}
	
	public List<Album> getAlbumList(Long projectId) {
		return albumDao.findAlbumList(projectId);
	}
	
}
