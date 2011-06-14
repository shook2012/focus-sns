package org.osforce.connect.service.system.impl;

import java.util.List;

import org.osforce.connect.dao.system.ProjectCategoryDao;
import org.osforce.connect.dao.system.SiteDao;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 12:41:02 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ProjectCategoryServiceImpl implements ProjectCategoryService {

	private SiteDao siteDao;
	private ProjectCategoryDao projectCategoryDao;
	
	public ProjectCategoryServiceImpl() {
	}
	
	@Autowired
	public void setSiteDao(SiteDao siteDao) {
		this.siteDao = siteDao;
	}
	
	@Autowired
	public void setProjectCategoryDao(ProjectCategoryDao projectCategoryDao) {
		this.projectCategoryDao = projectCategoryDao;
	}
	
	public ProjectCategory getProjectCategory(Long categoryId) {
		return projectCategoryDao.get(categoryId);
	}
	
	public ProjectCategory getProjectCategory(Site site, String categoryCode) {
		return projectCategoryDao.findProjectCategory(site.getId(), categoryCode);
	}

	public void createProjectCategory(ProjectCategory projectCategory) {
		updateProjectCategory(projectCategory);
	}

	public void updateProjectCategory(ProjectCategory category) {
		if(category.getSiteId()!=null) {
			Site site = siteDao.get(category.getSiteId());
			category.setSite(site);
		}
		if(category.getParentId()!=null) {
			ProjectCategory parent = projectCategoryDao.get(category.getParentId());
			category.setParent(parent);
		}
		if(category.getId()==null) {
			projectCategoryDao.save(category);
		} else {
			projectCategoryDao.update(category);
		}
	}

	public void deleteProjectCategory(Long categoryId) {
		projectCategoryDao.delete(categoryId);
	}
	
	public List<ProjectCategory> getProjectCategoryList(Long siteId) {
		return projectCategoryDao.findProjectCategoryList(siteId, null);
	}
	
	public List<ProjectCategory> getProjectCategoryList(Long siteId, Long parentId) {
		return projectCategoryDao.findProjectCategoryList(siteId, parentId);
	}
	
	public List<ProjectCategory> getSiblingProjectCategoryList(Long siteId,
			Long categoryId) {
		ProjectCategory category = projectCategoryDao.get(categoryId);
		if(category.getParent()==null) {
			return projectCategoryDao.findProjectCategoryList(siteId, null);
		} else {
			return category.getParent().getChildren();
		}
	}
	
	public Page<ProjectCategory> getProjectCategoryPage(Page<ProjectCategory> page) {
		return projectCategoryDao.findProjectCategoryPage(page, null, null);
	}
	
	public Page<ProjectCategory> getProjectCategoryPage(Page<ProjectCategory> page, Long siteId) {
		return projectCategoryDao.findProjectCategoryPage(page, siteId, null);
	}
	
	public Page<ProjectCategory> getProjectCategoryPage(
			Page<ProjectCategory> page, Long siteId, Long parentId) {
		return projectCategoryDao.findProjectCategoryPage(page, siteId, parentId);
	}
	
}
