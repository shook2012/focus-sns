package org.osforce.connect.web.module.system;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.osforce.connect.entity.commons.Template;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.TemplateService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.service.system.RoleService;
import org.osforce.connect.service.system.UserService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.module.util.ModuleUtil;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.bind.annotation.Pref;
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
 * @create May 19, 2011 - 10:22:20 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/system/user")
public class UserWidget {

	private RoleService roleService;
	private UserService userService;
	private TemplateService templateService;
	private ProjectCategoryService categoryService;
	
	public UserWidget() {
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	@Autowired
	public void setCategoryService(ProjectCategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping("/list-view")
	public String doListView(@RequestParam(required=false) Long siteId, 
			Page<User> page, Model model) {
		if(siteId==null) {
			page = userService.getUserPage(page);
		} else {
			page = userService.getUserPage(page, siteId);
		}
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/user-list";
	}
	
	@RequestMapping("/login-view")
	public String doLoginView(@ModelAttribute @Valid LoginBean loginBean, BindingResult result) {
		return "system/user-login";
	}
	
	@RequestMapping(value="/login-action", method=RequestMethod.POST)
	public String doLoginAction(@Valid LoginBean loginBean, BindingResult result, 
			Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			return "page:/login";
		}
		//
		User user = userService.loginUser(loginBean.getUsername(), loginBean.getPassword());
		if(user!=null) {
			session.setAttribute(AttributeKeys.USER_ID_KEY, user.getId());
		}
		return String.format("redirect:/%s/profile", user.getProject().getUniqueId());
	}
	
	@RequestMapping("/logout-action")
	public String doLogoutAction(HttpSession session) {
		session.removeAttribute(AttributeKeys.USER_ID_KEY);
		return "redirect:/";
	}
	
	@RequestMapping("/register-view")
	public String doRegisterView(@Pref("people-features") String templateCode,
			@Pref("people") String categoryCode, Site site, HttpSession session) {
		ProjectCategory category = categoryService.getProjectCategory(site.getId(), categoryCode);
		//
		Template template = templateService.getTemplate(category.getId(), templateCode);
		List<ProjectFeature> modules = ModuleUtil.parseToModules(template.getContent());
		Project project = new Project();
		// set project category
		project.setCategory(category);
		// set features
		project.setFeatures(modules);
		// set default role to features
		for(ProjectFeature feature : modules) {
			Role role = roleService.getRole(feature.getRoleCode(), category.getId());
			feature.setRole(role);
		}
		session.setAttribute(AttributeKeys.PROJECT_KEY, project);
		return "system/user-register";
	}
	
	@RequestMapping(value="/register-view", method=RequestMethod.POST)
	public String doRegisterAction(@Valid User user, BindingResult result, 
			Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
		}
		//
		Project project = (Project) session.getAttribute(AttributeKeys.PROFILE_KEY);
		userService.registerUser(user, project);
		// remove project from session
		session.removeAttribute(AttributeKeys.PROJECT_KEY);
		return "redirect:/";
	}
	
}
