package org.osforce.connect.service.blog.impl;

import java.util.Date;
import java.util.List;

import org.osforce.connect.dao.blog.PostCategoryDao;
import org.osforce.connect.dao.blog.PostDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.blog.PostCategory;
import org.osforce.connect.entity.blog.Post;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.blog.PostService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:19:23 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

	private UserDao userDao;
	private ProjectDao projectDao;
	private PostDao postDao;
	private PostCategoryDao postCategoryDao;
	
	public PostServiceImpl() {
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
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
	
	@Autowired
	public void setPostCategoryDao(PostCategoryDao postCategoryDao) {
		this.postCategoryDao = postCategoryDao;
	}

	public Post viewPost(Long postId) {
		return postDao.get(postId);
	}
	
	public Post getPost(Long postId) {
		return postDao.get(postId);
	}

	public void createPost(Post post) {
		updatePost(post);
	}

	public void updatePost(Post post) {
		if(post.getEnteredId()!=null) {
			User enteredBy = userDao.get(post.getEnteredId());
			post.setEnteredBy(enteredBy);
		}
		if(post.getModifiedId()!=null) {
			User modifiedBy = userDao.get(post.getModifiedId());
			post.setModifiedBy(modifiedBy);
		}
		if(post.getCategoryId()!=null) {
			PostCategory category = postCategoryDao.get(post.getCategoryId());
			post.setCategory(category);
		}
		if(post.getProjectId()!=null) {
			Project project = projectDao.get(post.getProjectId());
			post.setProject(project);
		}
		Date now = new Date();
		post.setModified(now);
		if(post.getId()==null) {
			post.setEntered(now);
			postDao.save(post);
		} else {
			postDao.update(post);
		}
	}

	public void deletePost(Long postId) {
		postDao.delete(postId);
	}
	
	public Page<Post> getPostPage(Page<Post> page, 
			Long projectId, Long categoryId) {
		return postDao.findPostList(page, projectId, categoryId);
	}
	
	public Page<Post> getPostPage(Page<Post> page, List<String> categoryCodes) {
		return postDao.findPostPage(page, categoryCodes);
	}
	
}
