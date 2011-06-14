package org.osforce.connect.web.module.profile;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.profile.ProfileService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.dao.Page;
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
 * @create May 19, 2011 - 10:01:32 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/profile/profile")
public class ProfileWidget {

	private ProfileService profileService;
	private ProjectCategoryService categoryService;
	
	public ProfileWidget() {
	}
	
	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@Autowired
	public void setCategoryService(ProjectCategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping("/list-view")
	public String doListView(@PrefParam String categoryCode,
			@RequestAttr User user, @RequestAttr Site site,
			Page<Profile> page, Model model, WebRequest request) {
		String mode = (String) request.getAttribute("mode", WebRequest.SCOPE_REQUEST);
		ProjectCategory category = categoryService.getProjectCategory(site, categoryCode);
		if(StringUtils.isBlank(mode) && user!=null) {
			page = profileService.getProfilePage(page, user, category.getId());
		} else {
			page = profileService.getProfilePage(page, category.getId());
		}
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "profile/profile-list";
	}
	
	@RequestMapping("/detail-view")
	@Permission({"profile-view"})
	public String doDetailView(@RequestAttr Project project, Model model) {
		Profile profile = project.getProfile();
		model.addAttribute(AttributeKeys.PROFILE_KEY_READABLE, profile);
		return "profile/profile-detail";
	}
	
	@RequestMapping("/form-view")
	@Permission({"profile-add", "profile-edit"})
	public String doFormView(@RequestParam(required=false) Long profileId, 
			@ModelAttribute @Valid Profile profile, BindingResult result, Model model) {
		if(profileId==null) {
			model.addAttribute(AttributeKeys.PROFILE_KEY_READABLE, profile);
		} else {
			profile = profileService.getProfile(profileId);
			model.addAttribute(AttributeKeys.PROFILE_KEY_READABLE, profile);
		}
		return "profile/profile-form";
	}
	
	@RequestMapping("/form-action")
	@Permission({"profile-add", "profile-edit"})
	public String doFormAction(
			@ModelAttribute @Valid Profile profile, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_PROFILE);
			return "page:/profile/profile-form";
		}
		//
		profileService.updateProfile(profile);
		return String.format("redirect:/%s/profile", profile.getProject().getUniqueId());
	}
	
}
