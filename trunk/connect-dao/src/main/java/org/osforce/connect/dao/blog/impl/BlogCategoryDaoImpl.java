package org.osforce.connect.dao.blog.impl;

import java.util.List;

import org.osforce.connect.dao.blog.BlogCategoryDao;
import org.osforce.connect.entity.blog.BlogCategory;
import org.osforce.spring4me.dao.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:02:56 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class BlogCategoryDaoImpl extends AbstractDao<BlogCategory> 
	implements BlogCategoryDao {

	public BlogCategoryDaoImpl() {
		super(BlogCategory.class);
	}

	static final String JPQL0 = "FROM BlogCategory AS bc WHERE bc.project.id = ?1";
	public List<BlogCategory> findBlogCategoryList(Long projectId) {
		return findList(JPQL0, projectId);
	}

	static final String JPQL1 = "FROM BlogCategory AS bc WHERE bc.project.id = ?1 AND bc.label = ?2";
	public BlogCategory findBlogCategory(Long projectId, String categoryLabel) {
		return findOne(JPQL1, projectId, categoryLabel);
	}
}
