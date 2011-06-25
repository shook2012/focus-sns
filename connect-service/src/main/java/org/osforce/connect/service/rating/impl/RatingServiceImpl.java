package org.osforce.connect.service.rating.impl;

import java.util.Date;

import org.osforce.connect.dao.review.RatingDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.review.Rating;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.rating.RatingService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.2
 * @create Jun 23, 2011 - 12:53:29 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class RatingServiceImpl implements RatingService {

	private RatingDao ratingDao;
	private UserDao userDao;
	private ProjectDao projectDao;
	
	public RatingServiceImpl() {
	}
	
	@Autowired
	public void setRatingDao(RatingDao ratingDao) {
		this.ratingDao = ratingDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	public Rating getRating(Long ratingId) {
		return ratingDao.get(ratingId);
	}

	public void createRating(Rating rating) {
		updateRating(rating);
	}

	public void updateRating(Rating rating) {
		if(rating.getProjectId()!=null) {
			Project project = projectDao.get(rating.getProjectId());
			rating.setProject(project);
		}
		if(rating.getEnteredId()!=null) {
			User enteredBy = userDao.get(rating.getEnteredId());
			rating.setEnteredBy(enteredBy);
		}
		if(rating.getModifiedId()!=null) {
			User modifiedBy = userDao.get(rating.getModifiedId());
			rating.setModifiedBy(modifiedBy);
		}
		//
		Date now = new Date();
		rating.setModified(now);
		if(rating.getId()==null) {
			rating.setEntered(now);
			ratingDao.save(rating);
		} else {
			ratingDao.update(rating);
		}
	}
	
	public void deleteRating(Long ratingId) {
		ratingDao.delete(ratingId);
	}
	
	public Page<Rating> getRatingPage(Page<Rating> page, Project project) {
		return ratingDao.findRatingPage(page, project.getId());
	}
	
}
