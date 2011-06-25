package org.osforce.connect.web.module.system;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.system.PermissionService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.commons.collection.CollectionUtil;
import org.osforce.spring4me.web.bind.annotation.PrefParam;
import org.osforce.spring4me.web.bind.annotation.RequestAttr;
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
	public String doMainMenu(@RequestAttr Site site, Model model) {
		List<ProjectCategory> categories = categoryService.getProjectCategoryList(site.getId());
		model.addAttribute(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
		return "system/main-menu";
	}
	
	@RequestMapping("/user-menu")
	public String doUserMenu() {
		return "system/user-menu";
	}
	
	@RequestMapping("/project-menu")
	public String doProjectMenu(@RequestAttr Project project, 
			@RequestAttr User user, @RequestAttr TeamMember member, Model model) {
		if(project==null) {
			return "commons/blank";
		}
		List<ProjectFeature> features = project.getFeatures();
		List<ProjectFeature> tmp = CollectionUtil.newArrayList();
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
	public String doCustomMenu(
			@PrefParam(required=false) String items, 
			@PrefParam("commons/menu") String menuPath,
			@PrefParam(value="false", required=false) Boolean userRequired,
			@PrefParam(value="false", required=false) Boolean projectRequired,
			@RequestAttr Project project, @RequestAttr User user, Model model) {
		if((userRequired && user==null) || 
				(projectRequired && project==null)) {
			return "commons/blank";
		}
		//
		if(StringUtils.isNotBlank(items)) {
			List<String[]> itemList = CollectionUtil.newArrayList();
			for(String item : StringUtils.split(items, "\n")) {
				if(StringUtils.contains(item, "=")) {
					String key = StringUtils.substringBefore(item, "=");
					String value = StringUtils.substringAfter(item, "=");
					itemList.add(new String[]{key, value});
				}
			}
			model.addAttribute("menuitems", itemList);
		}
		return menuPath;
	}
	
}
