package org.osforce.connect.dao.search.impl;

import org.apache.lucene.search.Query;
import org.osforce.connect.dao.search.SearchProfileDao;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.spring4me.dao.AbstractSearchDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.2
 * @create Jun 16, 2011 - 2:09:20 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class SearchProfileDaoImpl extends AbstractSearchDao<Profile> 
	implements SearchProfileDao {

	public SearchProfileDaoImpl() {
		super(Profile.class);
	}
	
	public Page<Profile> searchProfilePage(Page<Profile> page, String title, Long categoryId) {
		Query query = getQueryBuilder().keyword().onField("title").matching(title).createQuery();
		if (categoryId != null) {
			query = getQueryBuilder()
					.bool()
					.must(getQueryBuilder().keyword().onField("title")
							.matching(title).createQuery())
					.must(getQueryBuilder().keyword()
							.onField("project.category.id")
							.matching(categoryId).createQuery())
					.createQuery();
		}
		return searchPage(page, query);
	}
	
}
