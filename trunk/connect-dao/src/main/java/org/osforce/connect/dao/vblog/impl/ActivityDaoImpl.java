package org.osforce.connect.dao.vblog.impl;

import java.util.List;

import org.osforce.connect.dao.vblog.ActivityDao;
import org.osforce.connect.entity.vblog.Activity;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 10:53:43 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ActivityDaoImpl extends AbstractDao<Activity> 
	implements ActivityDao {

	public ActivityDaoImpl() {
		super(Activity.class);
	}
	
	static final String JPQL0 = "FROM Activity AS a WHERE a.type IN (?1) ORDER BY a.entered DESC";
	public Page<Activity> findActivityPage(Page<Activity> page, List<String> activityTypes) {
		return findPage(page, JPQL0, activityTypes);
	}
	
	static final String JPQL1 = "";
	public Page<Activity> findActivityPage(Page<Activity> page, Long projectId, List<String> activityTypes) {
		return null;
	}
	
	/*static final String FIND_PAGE_JPQL = "FROM Activity a WHERE a.type IN (?1) AND a.project.profile.id IN (?2) ORDER BY a.entered DESC";
	public Page<Activity> findPage(Page<Activity> page, Project project,
			List<String> activityTypes) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(project.getProfileId());
		List<Long> tmp = find("SELECT l.toId FROM Link l WHERE l.from.id = ?1 AND l.entity = ?2", 
				Long.class, project.getId(), Profile.NAME);
		ids.addAll(tmp);
		tmp = find("SELECT tm.project.profile.id FROM TeamMember tm WHERE tm.user.project.id = ?1", 
				Long.class, project.getId());
		ids.addAll(tmp);
		return findPage(page, FIND_PAGE_JPQL, activityTypes, ids);
	}*/
}
