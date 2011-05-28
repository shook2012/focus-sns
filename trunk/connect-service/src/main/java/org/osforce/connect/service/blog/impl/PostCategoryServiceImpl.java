package org.osforce.connect.service.blog.impl;

import java.util.List;

import org.osforce.connect.dao.blog.PostCategoryDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.entity.blog.PostCategory;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.service.blog.PostCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:22:58 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class PostCategoryServiceImpl implements PostCategoryService {

	private ProjectDao projectDao;
	private PostCategoryDao blogCategoryDao;
	
	public PostCategoryServiceImpl() {
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Autowired
	public void setBlogCategoryDao(PostCategoryDao blogCategoryDao) {
		this.blogCategoryDao = blogCategoryDao;
	}
	
	public PostCategory getBlogCategory(Long categoryId) {
		return blogCategoryDao.get(categoryId);
	}
	
	public PostCategory getBlogCategory(Long projectId, String categoryLabel) {
		return blogCategoryDao.findBlogCategory(projectId, categoryLabel);
	}

	public void createBlogCategory(PostCategory category) {
		updateBlogCategory(category);
	}

	public void updateBlogCategory(PostCategory category) {
		if(category.getProjectId()!=null) {
			Project project = projectDao.get(category.getProjectId());
			category.setProject(project);
		}
		if(category.getId()==null) {
			blogCategoryDao.save(category);
		} else {
			blogCategoryDao.update(category);
		}
	}

	public void deleteBlogCategory(Long categoryId) {
		blogCategoryDao.delete(categoryId);
	}
	
	public List<PostCategory> getBlogCategoryList(Project project) {
		return blogCategoryDao.findBlogCategoryList(project.getId());
	}
}
