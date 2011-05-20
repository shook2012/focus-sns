package org.osforce.connect.web.module.profile;

import javax.validation.Valid;

import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.security.annotation.Permission;
import org.osforce.connect.service.profile.ProfileService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String doDetailView(Project project, Model model) {
		Profile profile = project.getProfile();
		model.addAttribute(AttributeKeys.PROFILE_KEY_READABLE, profile);
		return "profile/profile-detail";
	}
	
	@RequestMapping("/form-view")
	@Permission({"profile-detail-add", "profile-detail-edit"})
	public String doFormView(Project project, Model model, 
			@ModelAttribute @Valid Profile profile, BindingResult result) {
		model.addAttribute(AttributeKeys.PROFILE_KEY_READABLE, project.getProfile());
		return "profile/profile-form";
	}
	
	@RequestMapping("/form-action")
	public String doFormAction(@ModelAttribute @Valid Profile profile,
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			return "page:/profile/profile-form";
		}
		//
		profileService.updateProfile(profile);
		return String.format("redirect:/%s/profile", profile.getProject().getUniqueId());
	}
}
