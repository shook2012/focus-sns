package org.osforce.connect.dao.stream;

import java.util.List;

import org.osforce.connect.entity.stream.Activity;
import org.osforce.connect.entity.system.Project;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 10:53:07 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ActivityDao extends BaseDao<Activity> {

	/**
	 * Find activity page by ...
	 * @param page
	 * @param activityTypes
	 * @return
	 */
	Page<Activity> findActivityPage(Page<Activity> page, List<String> activityTypes);
	
	/**
	 * Find activity page by ...
	 * @param page
	 * @param projectId Note: project id may be null
	 * @param activityTypes
	 * @return
	 */
	Page<Activity> findActivityPage(Page<Activity> page, Project project,
			List<String> activityTypes);

	

}
