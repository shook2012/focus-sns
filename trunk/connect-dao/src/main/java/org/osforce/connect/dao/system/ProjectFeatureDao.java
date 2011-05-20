package org.osforce.connect.dao.system;

import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.spring4me.dao.BaseDao;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:04:13 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProjectFeatureDao extends BaseDao<ProjectFeature> {

	/**
	 * Find project feature by project feature code and project id
	 * @param code
	 * @param projectId
	 * @return
	 */
	ProjectFeature findProjectFeature(String code, Long projectId);

}
