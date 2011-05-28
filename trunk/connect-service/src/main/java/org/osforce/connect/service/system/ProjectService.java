package org.osforce.connect.service.system;

import org.osforce.connect.entity.system.Project;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 12:13:49 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProjectService {

	Project getProject(Long projectId);
	
	Project getProject(String uniqueId);
	
	void createProject(Project project);
	
	void updateProject(Project project);
	
	void deleteProject(Long projectId);
	
	/**
	 * Find project page 
	 * @param page
	 * @return
	 */
	Page<Project> getProjectPage(Page<Project> page);

	/**
	 * Find project page by category id
	 * @param page
	 * @param categoryId
	 * @return
	 */
	Page<Project> getProjectPage(Page<Project> page, Long categoryId);

}
