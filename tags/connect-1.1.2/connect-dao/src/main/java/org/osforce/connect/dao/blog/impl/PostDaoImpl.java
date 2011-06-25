package org.osforce.connect.dao.blog.impl;

import java.util.List;

import org.osforce.connect.dao.blog.PostDao;
import org.osforce.connect.entity.blog.Post;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 11:02:04 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class PostDaoImpl extends AbstractDao<Post>
		implements PostDao {
	
	public PostDaoImpl() {
		super(Post.class);
	}

	static final String JPQL0 = "FROM Post p %s";
	public Page<Post> findPostList(Page<Post> page, Long projectId,
			Long categoryId) {
		if(projectId!=null && categoryId!=null) {
			return findPage(page, String.format(JPQL0, "WHERE p.project.id = ?1 AND p.category.id = ?2"), projectId, categoryId);
		} else if(projectId!=null) {
			return findPage(page, String.format(JPQL0, "WHERE p.project.id = ?1"), projectId);
		} else if(categoryId!=null) {
			return findPage(page, String.format(JPQL0, "WHERE p.category.id = ?1"), categoryId);
		} else {
			return findPage(page, String.format(JPQL0, ""));
		}
	}

	static final String JPQL1 = "FROM Post AS p WHERE p.project.category.code IN (?1)"; 
	public Page<Post> findPostPage(Page<Post> page, List<String> categoryCodes) {
		return findPage(page, JPQL1, categoryCodes);
	}
	
}
