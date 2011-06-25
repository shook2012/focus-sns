package org.osforce.connect.service.stream.impl;

import java.util.Date;
import java.util.List;

import org.osforce.connect.dao.stream.ActivityDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.stream.Activity;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.stream.ActivityService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 14, 2011 - 11:37:22 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

	private UserDao userDao;
	private ProjectDao projectDao;
	private ActivityDao activityDao;
	
	public ActivityServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Autowired
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public Activity getActivity(Long activityId) {
		return activityDao.get(activityId);
	}

	public void createActivity(Activity activity) {
		updateActivity(activity);
	}

	public void updateActivity(Activity activity) {
		Date now = new Date();
		if(activity.getEnteredId()!=null) {
			User enteredBy = userDao.get(activity.getEnteredId());
			activity.setEnteredBy(enteredBy);
		}
		if(activity.getProjectId()!=null) {
			Project project = projectDao.get(activity.getProjectId());
			activity.setProject(project);
		}
		if(activity.getParentId()!=null) {
			Activity parent = activityDao.get(activity.getParentId());
			activity.setParent(parent);
			//
			parent.setModified(now);
			activityDao.update(parent);
		}
		activity.setModified(now);
		if(activity.getId()==null) {
			activity.setEntered(now);
			activityDao.save(activity);
		} else {
			activityDao.update(activity);
		}
	}

	public void deleteActivity(Long activityId) {
		activityDao.delete(activityId);
	}

	public Page<Activity> getActivityPage(Page<Activity> page, List<String> activityTypes) {
		return activityDao.findActivityPage(page, activityTypes);
	}
	
	public Page<Activity> getActivityPage(Page<Activity> page, Project project, List<String> activityTypes) {
		return activityDao.findActivityPage(page, project, activityTypes);
	}
	
}
