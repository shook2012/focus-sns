package org.osforce.connect.dao.system;

import org.osforce.connect.entity.system.Site;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

public interface SiteDao extends BaseDao<Site> {

	/**
	 * Find site by domain
	 * @param domain
	 * @return
	 */
	Site findSite(String domain);

	/**
	 * Find site page
	 * @param page
	 * @return
	 */
	Page<Site> findSitePage(Page<Site> page);

}
