package org.osforce.connect.dao.profile;

import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.User;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 6:50:36 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProfileDao extends BaseDao<Profile> {

	/**
	 * Find profile by project id
	 * @param projectId
	 * @return
	 */
	Profile findProfile(Long projectId);
	
	/**
	 * Find profiles by category id
	 * @param page
	 * @param categoryId
	 * @return
	 */
	Page<Profile> findProfilePage(Page<Profile> page, Long categoryId);
	
	/**
	 * 
	 * @param page
	 * @param categoryId
	 * @param userId
	 * @return
	 */
	Page<Profile> findProfilePage(Page<Profile> page, User user, Long categoryId);




}
