package org.osforce.connect.web.module.system;

import java.util.List;

import javax.validation.Valid;

import org.osforce.connect.entity.commons.Template;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.TemplateService;
import org.osforce.connect.service.profile.ProfileService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.service.system.ProjectFeatureService;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.connect.service.system.RoleService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.module.util.ModuleUtil;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.web.bind.annotation.PrefParam;
import org.osforce.spring4me.web.bind.annotation.RequestAttr;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 20, 2011 - 1:44:48 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/system/project")
public class ProjectWidget {

	private RoleService roleService;
	private ProfileService profileService;
	private ProjectService projectService;
	private TemplateService templateService;
	private ProjectFeatureService featureService;
	private ProjectCategoryService categoryService;
	
	public ProjectWidget() {
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	@Autowired
	public void setFeatureService(ProjectFeatureService featureService) {
		this.featureService = featureService;
	}
	
	@Autowired
	public void setCategoryService(ProjectCategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping("/title-view")
	@Permission(projectRequired=true)
	public String doTitleView(@RequestAttr Project project) {
		if(project==null) {
			return "commons/blank";
		}
		return "system/project-title";
	}
	
	@RequestMapping("/form-view")
	public String doProjectForm(@PrefParam String categoryCode,
			@PrefParam String templateCode, WebRequest request,
			@ModelAttribute @Valid Project project, BindingResult result, 
			@RequestAttr Site site, @RequestAttr User user, Model model, Boolean showErrors) {
		if(!showErrors) {
			ProjectCategory category = categoryService.getProjectCategory(site, categoryCode);
			project.setCategory(category);
			project.setEnteredBy(user);
			project.setModifiedBy(user);
			//
			Template template = templateService.getTemplate(category.getId(), templateCode);
			List<ProjectFeature> modules = ModuleUtil.parseToModules(template.getContent());
			request.setAttribute(AttributeKeys.PROJECT_FEATURE_LIST_KEY_READABLE, modules, WebRequest.SCOPE_SESSION);
			model.addAttribute(AttributeKeys.PROJECT_KEY_READABLE, project);
		}
		return "system/project-form";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/form-action")
	public String doProjectAction(WebRequest request,
			@ModelAttribute @Valid Project project, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE,ProjectFeature.FEATURE_ADMIN);
			return "page:project/form";
		}
		//
		List<ProjectFeature> features = (List<ProjectFeature>) request.getAttribute(
				AttributeKeys.PROJECT_FEATURE_LIST_KEY_READABLE, WebRequest.SCOPE_SESSION);
		projectService.createProject(project);
		for(ProjectFeature feature : features) {
			Role role = roleService.getRole(feature.getRoleCode(), project.getCategoryId());
			feature.setRole(role);
			feature.setProject(project);
			featureService.createProjectFeature(feature);
		}
		//
		Profile profile = new Profile();
		profile.setTitle(project.getTitle());
		profile.setProject(project);
		profile.setEnteredBy(project.getEnteredBy());
		profile.setModifiedBy(project.getModifiedBy());
		profile.setProject(project);
		profileService.createProfile(profile);
		return String.format("redirect:/%s/profile", project.getUniqueId());
	}
	
}
