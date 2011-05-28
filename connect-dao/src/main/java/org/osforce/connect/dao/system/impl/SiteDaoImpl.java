package org.osforce.connect.dao.system.impl;

import org.osforce.connect.dao.system.SiteDao;
import org.osforce.connect.entity.system.Site;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * implement SiteDao interface
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 1:46:19 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class SiteDaoImpl extends AbstractDao<Site> implements SiteDao {

	public SiteDaoImpl() {
		super(Site.class);
	}
	
	static final String JPQL0 = "FROM Site AS s WHERE s.domain = ?1";
	public Site findSite(String domain) {
		return findOne(JPQL0, domain);
	}

	static final String JPQL1 = "FROM Site AS s ORDER BY s.domain DESC";
	public Page<Site> findSitePage(Page<Site> page) {
		return findPage(page, JPQL1);
	}
	
}
