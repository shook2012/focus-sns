package org.osforce.connect.web.module.profile;

import java.io.IOException;
import java.util.Collections;

import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.service.commons.AttachmentService;
import org.osforce.connect.service.profile.ProfileService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.module.util.AttachmentUtil;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Widget
@RequestMapping("/profile/logo")
public class LogoWidget {
	
	private ProfileService profileService;
	private AttachmentService attachmentService;
	
	public LogoWidget() {
	}

	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@Autowired
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"profile-add", "profile-edit"}, userRequired=true)
	public String doFormView(@RequestParam Long profileId, Model model) {
		Profile profile = profileService.getProfile(profileId);
		model.addAttribute(AttributeKeys.PROFILE_KEY_READABLE, profile);
		return "profile/logo-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"profile-add", "profile-edit"}, userRequired=true)
	public @ResponseBody Object doFormAction(
			@RequestParam MultipartFile file, 
			@RequestParam Long profileId) throws IOException {
		Attachment attachment = AttachmentUtil.parse(file);
		attachmentService.createAttachment(attachment);
		// write attachment content to local file
		AttachmentUtil.write(attachment);
		Profile profile = profileService.getProfile(profileId);
		Attachment logo = profile.getLogo();
		// delete logo
		if(logo!=null) {
			// delete from disk 
			AttachmentUtil.delete(logo);
			// delete from database
			attachmentService.deleteAttachment(logo.getId());
		}
		profile.setLogo(attachment);
		profileService.updateProfile(profile);
		return Collections.singletonMap("id", profile.getId());
	}
	
}
