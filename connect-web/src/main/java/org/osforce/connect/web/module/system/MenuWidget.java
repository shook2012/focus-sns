package org.osforce.connect.web.module.system;

import java.util.ArrayList;
import java.util.List;

import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.system.PermissionService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.web.bind.annotation.Pref;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Widget
@RequestMapping("/system/menu")
public class MenuWidget {

	private PermissionService permissionService;
	private ProjectCategoryService categoryService;
	
	public MenuWidget() {
	}
	
	@Autowired
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	@Autowired
	public void setCategoryService(ProjectCategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping("/main-menu")
	public String doMainMenu(Site site, Model model) {
		List<ProjectCategory> menuitems = categoryService.getProjectCategoryList(site.getId(), null);
		model.addAttribute("menuitems", menuitems);
		return "system/main-menu";
	}
	
	@RequestMapping("/user-menu")
	public String doUserMenu() {
		return "system/user-menu";
	}
	
	@RequestMapping("/project-menu")
	public String doProjectMenu(Project project, User user,
			TeamMember member, Model model) {
		if(project==null) {
			return "commons/blank";
		}
		List<ProjectFeature> features = project.getFeatures();
		List<ProjectFeature> tmp = new ArrayList<ProjectFeature>();
		for(ProjectFeature feature : features) {
			String resource = "project-" +  feature.getCode().toLowerCase() + "-view";
			if(permissionService.hasPermission(project, user, resource)) {
				tmp.add(feature);
			}
		}
		model.addAttribute(AttributeKeys.PROJECT_FEATURE_LIST_KEY_READABLE, tmp);
		return "system/project-menu";
	}
	
	@RequestMapping("/custom-menu")
	public String doCustomMenu(@Pref String menuPath,
			@Pref(required=false) Boolean userRequired,
			@Pref(required=false) Boolean projectRequired,
			Project project, User user) {
		if(userRequired && user==null) {
			return "commons/blank";
		}
		return menuPath;
	}
	
}
