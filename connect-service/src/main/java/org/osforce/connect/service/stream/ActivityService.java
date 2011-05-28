package org.osforce.connect.service.stream;

import java.util.List;

import org.osforce.connect.entity.stream.Activity;
import org.osforce.connect.entity.system.Project;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 14, 2011 - 11:38:07 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ActivityService {

	Activity getActivity(Long activityId);
	
	void createActivity(Activity activity);
	
	void updateActivity(Activity activity);
	
	void deleteActivity(Long activityId);

	/**
	 * Find activity page
	 * @param page
	 * @param activityTypes
	 * @return
	 */
	Page<Activity> getActivityPage(Page<Activity> page, List<String> activityTypes);

	/**
	 * Find activity page
	 * @param page
	 * @param projectId
	 * @param asList
	 * @return
	 */
	Page<Activity> getActivityPage(Page<Activity> page, Project project, List<String> asList);
}
