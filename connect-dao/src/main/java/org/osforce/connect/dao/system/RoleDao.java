package org.osforce.connect.dao.system;

import java.util.List;

import org.osforce.connect.entity.system.Role;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 10:08:43 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface RoleDao extends BaseDao<Role> {

	/**
	 * Find role by role code and category id
	 * @param code
	 * @param categoryId
	 * @return
	 */
	Role findRole(String code, Long categoryId);

	/**
	 * Find role list by site id and category id
	 * @param siteId
	 * @param categoryId
	 * @return
	 */
	List<Role> findRoleList(Long siteId, Long categoryId);

	/**
	 * Find role page by site id
	 * @param page
	 * @param siteId
	 * @return
	 */
	Page<Role> findRolePage(Page<Role> page, Long siteId);

}
