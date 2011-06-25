package org.osforce.connect.service.rating;

import org.osforce.connect.entity.review.Rating;
import org.osforce.connect.entity.system.Project;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.2
 * @create Jun 23, 2011 - 12:54:15 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface RatingService {
	
	Rating getRating(Long ratingId);
	
	void createRating(Rating rating);
	
	void updateRating(Rating rating);

	void deleteRating(Long ratingId);

	Page<Rating> getRatingPage(Page<Rating> page, Project project);
}
