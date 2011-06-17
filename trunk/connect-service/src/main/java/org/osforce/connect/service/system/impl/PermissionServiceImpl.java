package org.osforce.connect.service.system.impl;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.osforce.connect.dao.system.PermissionDao;
import org.osforce.connect.dao.system.ProjectCategoryDao;
import org.osforce.connect.dao.system.ResourceDao;
import org.osforce.connect.dao.system.RoleDao;
import org.osforce.connect.dao.team.MemberDao;
import org.osforce.connect.entity.system.Permission;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.Resource;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.system.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Feb 24, 2011 - 12:52:12 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	private RoleDao roleDao;
	private MemberDao memberDao;
	private ResourceDao resourceDao;
	private PermissionDao permissionDao;
	private ProjectCategoryDao projectCategoryDao;

	public PermissionServiceImpl() {
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Autowired
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Autowired
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	@Autowired
	public void setProjectCategoryDao(ProjectCategoryDao projectCategoryDao) {
		this.projectCategoryDao = projectCategoryDao;
	}

	public Permission getPermission(Long permissionId) {
		return permissionDao.get(permissionId);
	}

	public Permission getPermission(String resourceCode) {
		return permissionDao.findPermission(resourceCode);
	};

	public Permission getPermission(Long resourceId, Long categoryId) {
		return permissionDao.findPermission(resourceId, categoryId);
	}

	public Permission getPermission(String resourceCode, Long categoryId) {
		return permissionDao.findPermission(resourceCode, categoryId);
	}

	public void createPermission(Permission permission) {
		updatePermission(permission);
	}

	public void updatePermission(Permission permission) {
		if(permission.getRoleId()!=null) {
			Role role = roleDao.get(permission.getRoleId());
			permission.setRole(role);
		}
		if(permission.getResourceId()!=null) {
			Resource resource = resourceDao.get(permission.getResourceId());
			permission.setResource(resource);
		}
		if(permission.getCategoryId()!=null) {
			ProjectCategory category = projectCategoryDao.get(permission.getCategoryId());
			permission.setCategory(category);
		}
		if(permission.getId()==null) {
			permissionDao.save(permission);
		} else {
			permissionDao.update(permission);
		}
	}

	public void deletePermission(Long permissionId) {
		permissionDao.delete(permissionId);
	}

	public List<Permission> getPermissionList(Long siteId, Long categoryId) {
		return permissionDao.findPermissionList(siteId, categoryId);
	}
	
	public Boolean hasPermission(Project project, User user, String[] resources) {
		for(String resource : resources) {
			if(hasPermission(project, user, resource)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean hasPermission(Project project, User user, String resource) {
		Assert.notNull(project, "Parameter project can not be null!");
		Assert.notNull(resource, "Parameter resource can not be null!");
		//
		int roleLevel = Role.LEVEL_LOW;
		if(user!=null) {
			TeamMember member = memberDao.findMember(
					project.getId(), user.getId(), Boolean.TRUE);
			if(member!=null) {
				roleLevel = member.getRole().getLevel();
			} else {
				//
				if(NumberUtils.compare(project.getEnteredBy().getId(), user.getId())==0 ||
						NumberUtils.compare(project.getId(), user.getProject().getId())==0) {
					return true;
				}
			}
		} 
		//
		Permission permission = getPermission(resource, project.getCategoryId());
		if(permission==null) {
			return false;
		}
		return NumberUtils.compare(roleLevel, permission.getRole().getLevel()) <= 0;
	}
}
