package org.osforce.connect.dao.system;

import java.util.List;

import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:03:56 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProjectCategoryDao extends BaseDao<ProjectCategory> {

	/**
	 * Find project category by site id and category code
	 * @param siteId
	 * @param categoryCode
	 * @return
	 */
	ProjectCategory findProjectCategory(Long siteId, String categoryCode);
	
	/**
	 * Find project category list by site id and parent project category id
	 * @param siteId
	 * @param parentId Note: parent project category id may be null
	 * @return
	 */
	List<ProjectCategory> findProjectCategoryList(Long siteId, Long parentId);
	
	/**
	 * Find project category page by site id and parent project category id
	 * @param page
	 * @param siteId
	 * @param parentId
	 * @return
	 */
	Page<ProjectCategory> findProjectCategoryPage(Page<ProjectCategory> page,
			Long siteId, Long parentId);
	
}
