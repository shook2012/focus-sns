package org.osforce.connect.web.module.system;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.Valid;

import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.service.system.RoleService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 0.1.0
 * @create May 19, 2011 - 10:39:32 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/system/role")
public class RoleWidget {
	
	private RoleService roleService;
	private ProjectCategoryService categoryService;

	public RoleWidget() {
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Autowired
	public void setCategoryService(ProjectCategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping("/list-view")
	public String doListView(@RequestParam Long siteId,
			Page<Role> page, Model model) {
		page = roleService.getRolePage(page, siteId);
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/role-list";
	}
	
	@RequestMapping("/form-view")
	public String doFormView(@RequestParam(required=false) Long roleId, 
			@RequestParam(required=false) Long siteId, 
			@ModelAttribute @Valid Role role, BindingResult result, Model model) {
		if(roleId!=null) {
			role = roleService.getRole(roleId);
			siteId = role.getCategory().getSite().getId();
		}
		List<ProjectCategory> categories = categoryService.getProjectCategoryList(siteId);
		model.addAttribute("categoryOptions", generateCategoryOptions(categories));
		model.addAttribute(AttributeKeys.ROLE_KEY_READABLE, role);
		model.addAttribute("categories", categories);
		return "system/role-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	public String doFormAction(@Valid Role role, BindingResult result) {
		if(result.hasErrors()) {
			return "page:/system/role-form";
		}
		//
		if(role.getId()==null) {
			roleService.createRole(role);
		} else {
			roleService.updateRole(role);
		}
		return String.format("redirect:/system/role/list?siteId=%s", role.getCategory().getSiteId());
	}
	
	private Map<String, String> generateCategoryOptions(List<ProjectCategory> categories) {
		Map<String, String> categoryOptions = new TreeMap<String, String>();
		for(ProjectCategory category : categories) {
			categoryOptions.put(category.getId().toString(), category.getLabel());
		}
		return categoryOptions;
	}
	
}
