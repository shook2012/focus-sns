package org.osforce.connect.dao.system;

import java.util.List;

import org.osforce.connect.entity.system.Permission;
import org.osforce.spring4me.dao.BaseDao;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 24, 2011 - 12:13:07 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface PermissionDao extends BaseDao<Permission> {

	/**
	 * Find permission by code
	 * @param code
	 * @return
	 */
	Permission findPermission(String code);

	/**
	 * Find permission by resource id and category id
	 * @param resourceId
	 * @param categoryId
	 * @return
	 */
	Permission findPermission(Long resourceId, Long categoryId);

	/**
	 * Find permission by resource code and category id
	 * @param resourceCode
	 * @param categoryId
	 * @return
	 */
	Permission findPermission(String resourceCode, Long categoryId);

	/**
	 * Find permissions by site id and category id
	 * @param siteId
	 * @param categoryId
	 * @return
	 */
	List<Permission> findPermissionList(Long siteId, Long categoryId);

}
