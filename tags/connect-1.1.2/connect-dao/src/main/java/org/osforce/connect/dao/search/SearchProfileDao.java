package org.osforce.connect.dao.search;

import org.osforce.connect.entity.profile.Profile;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.2
 * @create Jun 16, 2011 - 8:44:12 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface SearchProfileDao {

	/**
	 * Search profile page by title and categoryId
	 * @param page
	 * @param title
	 * @param categoryId
	 * @return
	 */
	Page<Profile> searchProfilePage(Page<Profile> page, String title, Long categoryId);

}
