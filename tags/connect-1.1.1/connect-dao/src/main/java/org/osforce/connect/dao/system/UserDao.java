package org.osforce.connect.dao.system;

import org.osforce.connect.entity.system.User;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 10:07:53 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface UserDao extends BaseDao<User> {

	/**
	 * Find user by username
	 * @param username
	 * @return
	 */
	User findUser(String username);

	/**
	 * Find user page by ...
	 * @param page
	 * @param siteId Note: site id may be null
	 * @return
	 */
	Page<User> findUserPage(Page<User> page, Long siteId);

	/**
	 * Find user page start with prefix
	 * @param page
	 * @param startWith
	 * @return
	 */
	Page<User> findUserPage(Page<User> page, String startWith);

}
