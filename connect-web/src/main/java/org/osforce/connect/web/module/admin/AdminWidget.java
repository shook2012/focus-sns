package org.osforce.connect.web.module.admin;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.commons.Template;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.service.commons.TemplateService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.service.system.ProjectFeatureService;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.connect.service.system.RoleService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.module.util.ModuleUtil;
import org.osforce.spring4me.web.bind.annotation.PrefParam;
import org.osforce.spring4me.web.bind.annotation.RequestAttr;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 22, 2011 - 12:51:22 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/admin")
public class AdminWidget {

	private RoleService roleService;
	private ProjectService projectService;
	private TemplateService templateService;
	private ProjectFeatureService featureService;
	private ProjectCategoryService categoryService;
	
	public AdminWidget() {
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
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
	
	@RequestMapping("/admin/info-view")
	public String doInfoView() {
		return "admin/admin-info";
	}
	
	@RequestMapping("/project/form-view")
	public String doProjectForm(@RequestAttr Site site,
			@ModelAttribute @Valid Project project, BindingResult result, 
			Model model, Boolean showErrors, WebRequest request) {
		if(!showErrors) {
			if(project==null || project.getId()==null) {
				project = (Project) request.getAttribute(AttributeKeys.PROJECT_KEY, WebRequest.SCOPE_REQUEST);
			}
			List<ProjectCategory> categories = categoryService.getProjectCategoryList(site.getId());
			model.addAttribute(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
			model.addAttribute(AttributeKeys.PROJECT_KEY_READABLE, project);
		}
		return "admin/project-form";
	}
	
	@RequestMapping("/project/form-action")
	public String doProjectAction(@ModelAttribute @Valid Project project, 
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE,ProjectFeature.FEATURE_ADMIN);
			return "page:/admin/project-form";
		}
		//
		projectService.updateProject(project);
		return String.format("redirect:/%s/admin/project/form", project.getUniqueId());
	}
	
	@RequestMapping("/features/form-view")
	public String doFeaturesForm(@PrefParam String templateCode, 
			@RequestAttr Project project, Model model) {
		Template template = templateService.getTemplate(project.getCategoryId(), templateCode);
		List<ProjectFeature> features = ModuleUtil.parseToModules(template.getContent());
		for(ProjectFeature feature : features) {
			for(ProjectFeature tmp : project.getFeatures()) {
				if(StringUtils.equals(feature.getCode(), tmp.getCode())) {
					feature.setId(tmp.getId());
					feature.setLabel(tmp.getLabel());
					feature.setShow(tmp.getShow());
					if(tmp.getLevel()!=null) {
						feature.setLevel(tmp.getLevel());
					}
					feature.setRoleId(tmp.getRoleId());
				}
			}
			feature.setProjectId(project.getId());
		}
		model.addAttribute(AttributeKeys.PROJECT_FEATURE_LIST_KEY_READABLE, features);
		//
		List<Role> roles = roleService.getRoleList(project.getCategoryId());
		model.addAttribute(AttributeKeys.ROLE_LIST_KEY_READABLE, roles);
		return "admin/features-form";
	}
	
	@RequestMapping("/features/form-action")
	public String doFeaturesAction(@RequestParam Long[] ids, 
			@RequestParam Long[] roleIds, @RequestParam Integer[] levels, 
			@RequestParam Boolean[] shows, @RequestParam String[] labels, @RequestAttr Project project) {
		for(int i=0; i<ids.length; i++) {
			ProjectFeature feature = featureService.getProjectFeature(ids[i]);
			feature.setLabel(labels[i]);
			feature.setLevel(levels[i]);
			feature.setShow(shows[i]);
			feature.setRoleId(roleIds[i]);
			feature.setProjectId(project.getId());
			featureService.updateProjectFeature(feature);
		}
		return String.format("redirect:/%s/admin/features/form", project.getUniqueId());
	}
	
}
