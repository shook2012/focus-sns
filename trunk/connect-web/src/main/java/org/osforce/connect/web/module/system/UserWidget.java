package org.osforce.connect.web.module.system;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.SystemUtils;
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
import org.osforce.spring4me.commons.cipher.CipherUtil;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.bind.annotation.PrefParam;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

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

	private static final String KEY_FILE = SystemUtils.USER_HOME + "/.connect/key";
	
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
			Model model, HttpSession session, HttpServletResponse response) throws Exception {
		User user = userService.loginUser(loginBean.getUsername(), loginBean.getPassword());
		if(result.hasErrors() || user==null) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			return "page:/login";
		}
		//
		if(loginBean.getRememberMe()) {
			String username = CipherUtil.encrypt(user.getUsername(), KEY_FILE);
			Cookie cookie = new Cookie(AttributeKeys.REMEMBER_ME_KEY, username);
			cookie.setPath("/connect");
			response.addCookie(cookie);
		}
		session.setAttribute(AttributeKeys.USER_ID_KEY, user.getId());
		return String.format("redirect:/%s/profile", user.getProject().getUniqueId());
	}
	
	@RequestMapping("/logout-action")
	public String doLogoutAction(HttpSession session) {
		session.removeAttribute(AttributeKeys.USER_ID_KEY);
		return "redirect:/";
	}
	
	@RequestMapping("/register-view")
	public String doRegisterView(
			@PrefParam String categoryCode, @PrefParam String templateCode,
			@ModelAttribute @Valid RegisterBean registerBean, BindingResult result, 
			Site site, Model model, WebRequest request) {
		ProjectCategory category = categoryService.getProjectCategory(site, categoryCode);
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
		request.setAttribute(AttributeKeys.PROJECT_KEY, project, WebRequest.SCOPE_SESSION);
		model.addAttribute(AttributeKeys.USER_KEY_READABLE, registerBean);
		return "system/user-register";
	}
	
	@RequestMapping(value="/register-action", method=RequestMethod.POST)
	public String doRegisterAction(@ModelAttribute @Valid RegisterBean registerBean, 
			BindingResult result, Model model, WebRequest request) {
		User user = userService.getUser(registerBean.getUsername());
		// FIXME
		if(user!=null) {
			result.addError(new ObjectError(result.getObjectName(), "user is already existed!"));
		}
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			return "page:/register";
		}
		//
		Project project = (Project) request.getAttribute(AttributeKeys.PROJECT_KEY, WebRequest.SCOPE_SESSION);
		userService.registerUser(registerBean.getUser(), project);
		// remove project from session
		request.removeAttribute(AttributeKeys.PROJECT_KEY, WebRequest.SCOPE_REQUEST);
		return "redirect:/";
	}
	
}
