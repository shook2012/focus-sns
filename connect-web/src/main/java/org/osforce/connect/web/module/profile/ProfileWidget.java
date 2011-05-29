package org.osforce.connect.web.module.profile;

import javax.validation.Valid;

import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.service.profile.ProfileService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	public ProfileWidget() {
	}
	
	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@RequestMapping("/detail-view")
	@Permission({"profile-view"})
	public String doDetailView(Project project, Model model) {
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
	public String doFormAction(@ModelAttribute @Valid Profile profile,
			BindingResult result, Model model) {
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
