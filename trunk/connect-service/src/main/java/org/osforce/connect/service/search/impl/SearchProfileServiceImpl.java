package org.osforce.connect.service.search.impl;

import org.osforce.connect.dao.search.SearchProfileDao;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.service.search.SearchProfileService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.1
 * @create Jun 16, 2011 - 1:06:30 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class SearchProfileServiceImpl implements SearchProfileService {

	private SearchProfileDao searchProfileDao;
	
	public SearchProfileServiceImpl() {
	}
	
	@Autowired
	public void setSearchProfileDao(SearchProfileDao searchProfileDao) {
		this.searchProfileDao = searchProfileDao;
	}
	
	public Page<Profile> searchProfilePage(Page<Profile> page, String title, Long categoryId) {
		return searchProfileDao.searchProfilePage(page, title, categoryId);
	}
	
}
