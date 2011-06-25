package org.osforce.connect.service.system.impl;

import java.util.Date;

import org.apache.commons.lang.math.NumberUtils;
import org.osforce.connect.dao.system.ProjectCategoryDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 12:43:42 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	private UserDao userDao;
	private ProjectDao projectDao;
	private ProjectCategoryDao categoryDao;
	
	public ProjectServiceImpl() {
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
	public void setCategoryDao(ProjectCategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public Project getProject(Long projectId) {
		return projectDao.get(projectId);
	}

	public void createProject(Project project) {
		updateProject(project);
	}

	public void updateProject(Project project) {
		if(project.getCategoryId()!=null) {
			ProjectCategory category = categoryDao.get(project.getCategoryId());
			project.setCategory(category);
		}
		if(project.getSubCategoryId1()!=null) {
			ProjectCategory subCategory1 = categoryDao
					.get(project.getSubCategoryId1());
			project.setSubCategory1(subCategory1);
		}
		if(project.getSubCategoryId2()!=null) {
			ProjectCategory subCategory2 = categoryDao
					.get(project.getSubCategoryId2());
			project.setSubCategory2(subCategory2);
		}
		if(project.getSubCategoryId3()!=null) {
			ProjectCategory subCategory3 = categoryDao
					.get(project.getSubCategoryId3());
			project.setSubCategory3(subCategory3);
		}
		if(project.getEnteredId()!=null) {
			User enteredBy = userDao.get(project.getEnteredId()) ;
			project.setEnteredBy(enteredBy);
		} 
		if(project.getModifiedId()!=null) {
			User modifiedBy = userDao.get(project.getModifiedId());
			project.setModifiedBy(modifiedBy);
		}
		Date now = new Date();
		project.setModified(now);
		// validate uniqueId
		validateUniqueId(project);
		//
		if(project.getId()==null) {
			project.setEntered(now);
			projectDao.save(project);
		} else {
			projectDao.update(project);
		}
	}

	private void validateUniqueId(Project project) {
		String uniqueId = project.getUniqueId();
		Project persisted = projectDao.findProject(uniqueId);
		if(persisted!=null && (project.getId()==null || 
				NumberUtils.compare(project.getId(), persisted.getId())==0)) {
			Long count = projectDao.countProjects();
			uniqueId += "-" + ++count;
		}
		project.setUniqueId(uniqueId);
	}

	public void deleteProject(Long projectId) {
		projectDao.delete(projectId);
	}

	public Page<Project> getProjectPage(Page<Project> page) {
		return projectDao.findProjectPage(page, null);
	}

	public Project getProject(String uniqueId) {
		return projectDao.findProject(uniqueId);
	}
	
	public Page<Project> getProjectPage(Page<Project> page, Long categoryId) {
		return projectDao.findProjectPage(page, categoryId);
	}
	
}
