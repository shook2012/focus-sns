package org.osforce.connect.dao.system;

import java.util.List;

import org.osforce.connect.entity.system.Resource;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 10:54:42 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ResourceDao extends BaseDao<Resource> {

	/**
	 * Find resource by resource code
	 * @param code
	 * @return
	 */
	Resource findResource(String code);

	/**
	 * Find resource list order by resource code
	 * @return
	 */
	List<Resource> findResourceList();

	/**
	 * Find resource page order by resource code
	 * @param page
	 * @return
	 */
	Page<Resource> findResourcePage(Page<Resource> page);

}
