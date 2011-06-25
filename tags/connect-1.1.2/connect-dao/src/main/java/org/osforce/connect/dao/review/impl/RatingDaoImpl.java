package org.osforce.connect.dao.review.impl;

import org.osforce.connect.dao.review.RatingDao;
import org.osforce.connect.entity.review.Rating;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.2
 * @create Jun 23, 2011 - 12:51:39 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class RatingDaoImpl extends AbstractDao<Rating> implements RatingDao {

	public RatingDaoImpl() {
		super(Rating.class);
	}
	
	static final String JPQL0 = "FROM Rating AS r WHERE r.project.id = ?1";
	public Page<Rating> findRatingPage(Page<Rating> page, Long projectId) {
		return findPage(page, JPQL0, projectId);
	}
	
}
