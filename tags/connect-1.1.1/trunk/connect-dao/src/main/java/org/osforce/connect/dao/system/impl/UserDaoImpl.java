package org.osforce.connect.dao.system.impl;

import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.system.User;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 10:09:37 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

	protected UserDaoImpl() {
		super(User.class);
	}
	
	static final String JPQL0 = "FROM User AS u WHERE u.username = ?1";
	public User findUser(String username) {
		return findOne(JPQL0, username);
	}

	static final String JPQL1 = "FROM User AS u %s ORDER BY u.entered DESC";
	public Page<User> findUserPage(Page<User> page, Long siteId) {
		if(siteId!=null) {
			return findPage(page, String.format(JPQL1, "WHERE u.project.category.site.id = ?1"), siteId);
		}
		return findPage(page, String.format(JPQL1, ""));
	}
	
	static final String JPQL2 = "FROM User AS u WHERE u.username LIKE ?1";
	public Page<User> findUserPage(Page<User> page, String startWith) {
		return findPage(page, JPQL2, startWith + "%");
	}
}
