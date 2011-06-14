package org.osforce.connect.service.system.impl;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.osforce.connect.dao.system.ProjectCategoryDao;
import org.osforce.connect.dao.system.RoleDao;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.service.system.RoleService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:13:20 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	private RoleDao roleDao;
	private ProjectCategoryDao projectCategoryDao;
	
	public RoleServiceImpl() {
	}
	
	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	@Autowired
	public void setProjectCategoryDao(ProjectCategoryDao projectCategoryDao) {
		this.projectCategoryDao = projectCategoryDao;
	}

	public Role getRole(Long roleId) {
		return roleDao.get(roleId);
	}
	
	public Role getRole(Long categoryId, Integer roleLevel) {
		List<Role> roles = roleDao.findRoleList(null, categoryId);
		Role tmp = null;
		for(Role role : roles) {
			if(tmp==null || (role.getLevel()<=roleLevel && 
					NumberUtils.compare(role.getLevel(), tmp.getLevel())>0)) {
				tmp = role;
			}
		}
		return tmp;
	}
	
	public Role getRole(String code, Long categoryId) {
		return roleDao.findRole(code, categoryId);
	}

	public void createRole(Role role) {
		updateRole(role);
	}

	public void updateRole(Role role) {
		if(role.getCategoryId()!=null) {
			ProjectCategory category = projectCategoryDao.get(role.getCategoryId());
			role.setCategory(category);
		}
		if(role.getId()==null) {
			roleDao.save(role);
		} else {
			roleDao.update(role);
		}
	}

	public void delete(Long roleId) {
		roleDao.delete(roleId);
	}
	
	public List<Role> getRoleList(Long siteId, Long categoryId) {
		return roleDao.findRoleList(siteId, categoryId);
	}
	
	public List<Role> getRoleList(Long categoryId) {
		return roleDao.findRoleList(null, categoryId);
	}
	
	public List<Role> getRoleList(Site site) {
		return roleDao.findRoleList(site.getId(), null);
	}
	
	public Page<Role> getRolePage(Page<Role> page, Long siteId) {
		return roleDao.findRolePage(page, siteId);
	}
}
