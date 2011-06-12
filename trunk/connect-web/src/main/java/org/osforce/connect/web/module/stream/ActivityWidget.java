package org.osforce.connect.web.module.stream;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.stream.Activity;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.stream.ActivityService;
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
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 20, 2011 - 2:04:31 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/stream/activity")
public class ActivityWidget {

	private ActivityService activityService;
	
	public ActivityWidget() {
	}
	
	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	
	@RequestMapping("/list-view")
	@Permission(value={"activity-view"}, projectRequired=true)
	public String doListView(@PrefParam String activityTypes, 
			Page<Activity> page, @RequestAttr Project project, Model model) {
		List<String> types = Arrays.asList(StringUtils.split(activityTypes, ","));
		page = activityService.getActivityPage(page, project, types);
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "stream/activity-list";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"activity-add", "activity-edit"}, userRequired=true, projectRequired=true)
	public String doFormView(@PrefParam String activityType, @RequestAttr User user, 
			@PrefParam("false") Boolean showToolbar, @RequestAttr Project project,
			@ModelAttribute @Valid Activity activity, BindingResult result, Model model) {
		activity.setType(activityType);
		activity.setEnteredBy(user);
		activity.setProject(project);
		model.addAttribute(AttributeKeys.ACTIVITY_KEY_READABLE, activity);
		// 
		model.addAttribute("showToolbar", showToolbar);
		return "stream/activity-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"activity-add", "activity-edit"}, userRequired=true, projectRequired=true)
	public String doFormAction(@ModelAttribute @Valid Activity activity,
			BindingResult result, Model model, @RequestAttr Project project) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_PROFILE);
			return "page:/profile/profile-detail";
		}
		//
		if(activity.getId()==null) {
			activityService.createActivity(activity);
		} else {
			activityService.updateActivity(activity);
		}
		return String.format("redirect:/%s/profile", project.getUniqueId());
	}
	
}
