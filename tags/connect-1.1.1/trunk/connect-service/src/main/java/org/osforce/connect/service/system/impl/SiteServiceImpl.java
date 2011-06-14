package org.osforce.connect.service.system.impl;

import org.osforce.connect.dao.system.SiteDao;
import org.osforce.connect.dao.system.ThemeDao;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.Theme;
import org.osforce.connect.service.system.SiteService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 4:14:21 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class SiteServiceImpl implements SiteService {

	private SiteDao siteDao;
	private ThemeDao themeDao;
	
	public SiteServiceImpl() {
	}
	
	@Autowired
	public void setSiteDao(SiteDao siteDao) {
		this.siteDao = siteDao;
	}
	
	@Autowired
	public void setThemeDao(ThemeDao themeDao) {
		this.themeDao = themeDao;
	}
	
	public Site getSite(Long siteId) {
		return siteDao.get(siteId);
	}
	
	public Site getSite(String domain) {
		return siteDao.findSite(domain);
	}
	
	public void createSite(Site site) {
		updateSite(site);
	}

	public void updateSite(Site site) {
		if(site.getThemeId()!=null) {
			Theme theme = themeDao.get(site.getThemeId());
			site.setTheme(theme);
		}
		if(site.getId()==null) {
			siteDao.save(site);
		} else {
			siteDao.update(site);
		}
	}

	public Page<Site> getSitePage(Page<Site> page) {
		return siteDao.findSitePage(page);
	}
	
}
