package org.osforce.connect.web.module.system;

import java.util.List;
import java.util.Map;

import org.osforce.connect.entity.system.Permission;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.Resource;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.service.system.PermissionService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.service.system.ResourceService;
import org.osforce.connect.service.system.RoleService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.commons.collection.CollectionUtil;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 0.1.0
 * @create May 19, 2011 - 12:34:25 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/system/permission")
public class PermissionWidget {

	private RoleService roleService;
	private ResourceService resourceService;
	private PermissionService permissionService;
	private ProjectCategoryService categoryService;
	
	public PermissionWidget() {
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Autowired
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	@Autowired
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	@Autowired
	public void setCategoryService(ProjectCategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping("/list-view")
	public String doListView(@RequestParam Long siteId,
			@RequestParam(required=false) Long categoryId, Model model) {
		List<ProjectCategory> categories = categoryService.getProjectCategoryList(siteId, null);
		model.addAttribute(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
		if(categoryId==null && categories.size()>0) {
			categoryId = categories.get(0).getId();
		}
		model.addAttribute("categoryId", categoryId);
		//
		List<Permission> permissions = permissionService.getPermissionList(siteId, categoryId);
		Map<String, Permission> resourceMap = CollectionUtil.newHashMap();
		for(Permission p : permissions) {
			resourceMap.put(p.getResource().getCode(), p);
		}
		model.addAttribute("resourceMap", resourceMap);
		//
		List<Resource> resources = resourceService.getResourceList();
		model.addAttribute(AttributeKeys.RESOURCE_LIST_KEY_READABLE, resources);
		//
		List<Role> roles = roleService.getRoleList(categoryId);
		model.addAttribute(AttributeKeys.ROLE_LIST_KEY_READABLE, roles);
		return "system/permission-list";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	public String doFormAction(@RequestParam Long siteId, @RequestParam Long categoryId,
			@RequestParam Long[] resourceIds, @RequestParam Long[] roleIds) {
		for(int i=0; i<resourceIds.length; i++) {
			Long resourceId = resourceIds[i];
			Long roleId = roleIds[i];
			Permission permission = permissionService.getPermission(resourceId, categoryId);
			if(permission==null) {
				permission = new Permission(resourceId, categoryId);
			}
			permission.setRoleId(roleId);
			permissionService.updatePermission(permission);
		}
		return String.format("redirect:/system/permission/list?siteId=%s", siteId);
	}
	
}
