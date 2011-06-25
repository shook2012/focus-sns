package org.osforce.connect.dao.blog.impl;

import java.util.List;

import org.osforce.connect.dao.blog.PostCategoryDao;
import org.osforce.connect.entity.blog.PostCategory;
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
public class PostCategoryDaoImpl extends AbstractDao<PostCategory> 
	implements PostCategoryDao {

	public PostCategoryDaoImpl() {
		super(PostCategory.class);
	}

	static final String JPQL0 = "FROM PostCategory AS bc WHERE bc.project.id = ?1";
	public List<PostCategory> findBlogCategoryList(Long projectId) {
		return findList(JPQL0, projectId);
	}

	static final String JPQL1 = "FROM PostCategory AS bc WHERE bc.project.id = ?1 AND bc.label = ?2";
	public PostCategory findBlogCategory(Long projectId, String categoryLabel) {
		return findOne(JPQL1, projectId, categoryLabel);
	}
}
