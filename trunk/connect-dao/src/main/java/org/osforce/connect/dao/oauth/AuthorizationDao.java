/**
 *
 */
package org.osforce.connect.dao.oauth;

import org.osforce.connect.entity.oauth.Authorization;
import org.osforce.spring4me.dao.BaseDao;

/**
 * @author gavin
 * @since 1.0.3
 * @create May 10, 2011 - 3:56:01 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface AuthorizationDao extends BaseDao<Authorization> {

	/**
	 * Find unique authorization by target and user id
	 * @param target
	 * @param userId
	 * @return
	 */
	Authorization findAuthorization(String target, Long userId);

}
