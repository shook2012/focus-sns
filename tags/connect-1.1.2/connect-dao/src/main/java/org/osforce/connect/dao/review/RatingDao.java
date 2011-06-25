package org.osforce.connect.dao.review;

import org.osforce.connect.entity.review.Rating;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.2
 * @create Jun 23, 2011 - 12:51:17 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface RatingDao extends BaseDao<Rating> {

	Page<Rating> findRatingPage(Page<Rating> page, Long projectId);

}
