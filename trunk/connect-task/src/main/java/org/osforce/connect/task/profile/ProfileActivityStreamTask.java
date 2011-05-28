package org.osforce.connect.task.profile;

import java.io.IOException;
import java.util.Map;

import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.stream.Activity;
import org.osforce.connect.service.profile.ProfileService;
import org.osforce.connect.service.stream.ActivityService;
import org.osforce.spring4me.task.AbstractTask;
import org.osforce.spring4me.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;

import freemarker.template.TemplateException;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Mar 1, 2011 - 11:15:03 PM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class ProfileActivityStreamTask extends AbstractTask {

	private ProfileService profileService;
	private ActivityService activityService;

	public ProfileActivityStreamTask() {
	}

	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	protected void doTask(Map<Object, Object> context) throws IOException, TemplateException {
		Long profileId = (Long) context.get("profileId");
		String templateName = (String) context.get("template");
		Profile profile = profileService.getProfile(profileId);
		Activity activity = new Activity();
		activity.setLinkedId(profile.getId());
		activity.setEntity(Profile.NAME);
		activity.setType(Profile.NAME);
		activity.setDescription(templateName);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setEnteredId(profile.getModifiedId());
		activity.setProjectId(profile.getProjectId());
		activityService.createActivity(activity);
	}

}
