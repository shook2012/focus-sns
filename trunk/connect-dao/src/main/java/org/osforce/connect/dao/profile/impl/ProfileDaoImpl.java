package org.osforce.connect.dao.profile.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osforce.connect.dao.profile.ProfileDao;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.User;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 6:50:41 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ProfileDaoImpl extends AbstractDao<Profile>
	implements ProfileDao {

	public ProfileDaoImpl() {
		super(Profile.class);
	}

	static final String JPQL0 = "FROM Profile AS p WHRER p.project.id = ?1";
	public Profile findProfile(Long projectId) {
		return findOne(JPQL0, projectId);
	}
	
	static final String JPQL1 = "FROM Profile AS p WHERE p.project.category.id = ?1 AND p.project.publish = TRUE ORDER BY p.entered DESC";
	public Page<Profile> findProfilePage(Page<Profile> page, Long categoryId) {
		return findPage(page, JPQL1, categoryId);
	}

	static final String JPQL2 = "FROM Profile AS p WHERE p.project.category.id = ?1 AND p.id IN(?2) AND p.project.publish = TRUE ORDER BY p.entered DESC";
	public Page<Profile> findProfilePage(Page<Profile> page, User user, Long categoryId) {
		Set<Long> pIds = new HashSet<Long>();
		// link
		List<Long> _pIds = findList("SELECT l.toId FROM Link l WHERE l.from.id = ?1 AND l.entity = ?2",
				Long.class, user.getProjectId(), Profile.NAME);
		pIds.addAll(_pIds);
		// enteredBy
		_pIds = findList("SELECT p.id FROM Project p WHERE p.enteredBy.id = ?1", Long.class, user.getId());
		pIds.addAll(_pIds);
		// teamMember
		_pIds = findList("SELECT tm.project.id FROM TeamMember tm WHERE tm.user.id = ?1", Long.class, user.getId());
		pIds.addAll(_pIds);
		return findPage(page, JPQL2, categoryId, pIds);
	}
	
	/*static final String FIND_CONCERED_PAGE_JPQL = "FROM Profile p WHERE p.project.category.id = ?1 AND p.id IN (?2)";
	public Page<Profile> findConcernedPage(Page<Profile> page,
			ProjectCategory category, User user) {
		Set<Long> pIds = new HashSet<Long>();
		// link
		List<Long> _pIds = find("SELECT l.toId FROM Link l WHERE l.from.id = ?1 AND l.entity = ?2",
				Long.class, user.getProjectId(), Profile.NAME);
		pIds.addAll(_pIds);
		// enteredBy
		_pIds = find("SELECT p.id FROM Project p WHERE p.enteredBy.id = ?1", Long.class, user.getId());
		pIds.addAll(_pIds);
		// teamMember
		_pIds = find("SELECT tm.project.id FROM TeamMember tm WHERE tm.user.id = ?1", Long.class, user.getId());
		pIds.addAll(_pIds);
		return findPage(page, FIND_CONCERED_PAGE_JPQL, category.getId(), pIds);
	}*/

}
