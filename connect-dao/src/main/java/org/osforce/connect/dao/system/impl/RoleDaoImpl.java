package org.osforce.connect.dao.system.impl;

import java.util.List;

import org.osforce.connect.dao.system.RoleDao;
import org.osforce.connect.entity.system.Role;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 10:10:43 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {

	public RoleDaoImpl() {
		super(Role.class);
	}

	static final String JPQL0 = "FROM Role AS r WHERE r.code = ?1 AND r.category.id = ?2";
	public Role findRole(String code, Long categoryId) {
		return findOne(JPQL0, code, categoryId);
	}

	static final String JPQL1 = "FROM Role AS r %s ORDER BY r.code";
	public List<Role> findRoleList(Long siteId, Long categoryId) {
		if(siteId!=null && categoryId!=null) {
			return findList(String.format(JPQL1, "WHERE r.category.site.id = ?1 AND r.category.id = ?2"), siteId, categoryId);
		} else if(siteId!=null) {
			return findList(String.format(JPQL1, "WHERE r.category.site.id = ?1"), siteId);
		} else {
			return findList(String.format(JPQL1, "WHERE r.category.id = ?1"), categoryId);
		}
	}

	static final String JPQL2 = "FROM Role AS r WHERE r.category.site.id = ?1 ORDER BY r.code";
	public Page<Role> findRolePage(Page<Role> page, Long siteId) {
		return findPage(page, JPQL2, siteId);
	}
}
