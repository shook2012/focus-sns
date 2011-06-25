package org.osforce.connect.dao.system.impl;

import java.util.List;

import org.osforce.connect.dao.system.SiteLinkDao;
import org.osforce.connect.entity.system.SiteLink;
import org.osforce.spring4me.dao.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 21, 2011 - 11:12:45 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class SiteLinkDaoImpl extends AbstractDao<SiteLink> 
	implements SiteLinkDao {

	public SiteLinkDaoImpl() {
		super(SiteLink.class);
	}
	
	static final String JPQL0 = "FROM SiteLink AS sl WHERE sl.site.id = ?1 ORDER BY sl.code";
	public List<SiteLink> findSiteLinkList(Long siteId) {
		return null;
	}
	
}
