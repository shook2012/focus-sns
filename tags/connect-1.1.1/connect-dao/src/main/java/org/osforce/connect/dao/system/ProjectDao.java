package org.osforce.connect.dao.system;

import org.osforce.connect.entity.system.Project;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:02:28 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProjectDao extends BaseDao<Project> {

	/**
	 * Find project by unique id
	 * @param uniqueId
	 * @return
	 */
	Project findProject(String uniqueId);

	/**
	 * Find project page by category id
	 * @param page
	 * @param categoryId  Note: category id may be null
	 * @return
	 */
	Page<Project> findProjectPage(Page<Project> page, Long categoryId);

	/**
	 * Count projects
	 * @return total project's count
	 */
	Long countProjects();

	/*Page<Project> findConcernedPage(Page<Project> page,
			ProjectCategory category, User user);*/

}
