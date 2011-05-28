/**
 *
 */
package org.osforce.connect.dao.oauth.impl;

import org.osforce.connect.dao.oauth.AuthorizationDao;
import org.osforce.connect.entity.oauth.Authorization;
import org.osforce.spring4me.dao.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * @author gavin
 * @since 1.0.3
 * @create May 10, 2011 - 3:57:21 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class AuthorizationDaoImpl extends AbstractDao<Authorization>
	implements AuthorizationDao {

	public AuthorizationDaoImpl() {
		super(Authorization.class);
	}
	
	static final String JPQL0 = "FROM Authorization AS a WHERE a.target = ?1 AND a.user.id = ?2";
	public Authorization findAuthorization(String target, Long userId) {
		return findOne(JPQL0, target, userId);
	}

}
