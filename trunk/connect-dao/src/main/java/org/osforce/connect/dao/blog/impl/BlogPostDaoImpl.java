package org.osforce.connect.dao.blog.impl;

import org.osforce.connect.dao.blog.BlogPostDao;
import org.osforce.connect.entity.blog.BlogPost;
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
public class BlogPostDaoImpl extends AbstractDao<BlogPost>
		implements BlogPostDao {
	
	public BlogPostDaoImpl() {
		super(BlogPost.class);
	}

	static final String JPQL0 = "FROM BlogPost bp %s ORDER BY bp.entered DESC";
	public Page<BlogPost> findBlogPostList(Page<BlogPost> page, Long projectId,
			Long categoryId) {
		if(projectId!=null && categoryId!=null) {
			return findPage(page, String.format(JPQL0, "WHERE bp.project.id = ?1 AND bp.category.id = ?2"), projectId, categoryId);
		} else if(projectId!=null) {
			return findPage(page, String.format(JPQL0, "WHERE bp.project.id = ?1"), projectId);
		} else if(categoryId!=null) {
			return findPage(page, String.format(JPQL0, "WHERE bp.category.id = ?1"), categoryId);
		} else {
			return findPage(page, String.format(JPQL0, ""));
		}
	}
}
