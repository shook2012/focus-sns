package org.osforce.connect.dao.system.impl;

import java.util.List;

import org.osforce.connect.dao.system.PermissionDao;
import org.osforce.connect.entity.system.Permission;
import org.osforce.spring4me.dao.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 24, 2011 - 12:13:43 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class PermissionDaoImpl extends AbstractDao<Permission> 
	implements PermissionDao {

	public PermissionDaoImpl() {
		super(Permission.class);
	}
	
	static final String JPQL0 = "FROM Permission AS p WHERE p.resource.code = ?1";
	public Permission findPermission(String code) {
		return findOne(JPQL0, code);
	}
	
	static final String JPQL1 = "FROM Permission AS p WHERE p.resource.id = ?1 AND p.category.id = ?2";
	public Permission findPermission(Long resourceId, Long categoryId) {
		return findOne(JPQL1, resourceId, categoryId);
	}

	static final String JPQL2 = "FROM Permission AS p WHERE p.resource.code = ?1 AND p.category.id = ?2";
	public Permission findPermission(String resourceCode, Long categoryId) {
		return findOne(JPQL2, resourceCode, categoryId);
	}

	static final String JPQL3 = "FROM Permission AS p %s ORDER BY p.resource.code ASC";
	public List<Permission> findPermissionList(Long siteId, Long categoryId) {
		if(siteId!=null && categoryId!=null) {
			return findList(String.format(JPQL3, "WHERE p.category.site.id = ?1 AND p.category.id = ?2"), siteId, categoryId);
		} else if(siteId!=null) {
			return findList(String.format(JPQL3, "WHERE p.category.site.id = ?1"), siteId);
		} else {
			return findList(String.format(JPQL3, "WHERE p.category.id = ?1"), categoryId);
		}
	}
	
}
