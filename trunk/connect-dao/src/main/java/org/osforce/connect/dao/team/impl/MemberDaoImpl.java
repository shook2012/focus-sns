package org.osforce.connect.dao.team.impl;

import java.util.List;

import org.osforce.connect.dao.team.MemberDao;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:27:23 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class MemberDaoImpl extends AbstractDao<TeamMember>
	implements MemberDao {

	public MemberDaoImpl() {
		super(TeamMember.class);
	}
	
	static final String JPQL0 = "FROM TeamMember AS tm WHERE tm.project.id = ?1 AND tm.user.id = ?2 AND tm.enabled = TRUE";
	public TeamMember findMember(Long projectId, Long userId) {
		Assert.notNull(projectId, "Parameter project id can not be null!");
		Assert.notNull(userId, "Parameter user id can not be null!");
		return findOne(JPQL0, projectId, userId);
	}

	static final String JPQL1 = "FROM TeamMember AS tm WHRER tm.project.id = ?1 AND tm.enabled = TRUE";
	public Page<TeamMember> findMembers(Page<TeamMember> page, Long projectId) {
		return findPage(page, JPQL0, projectId);
	}
	
	static final String JPQL2 = "FROM TeamMember AS tm WHRER %s  AND tm.status = ?3 AND tm.enabled = FALSE";
	public List<TeamMember> findMembers(Long projectId, Long userId,
			String status, Boolean reverse) {
		if(reverse) {
			return findList(String.format(JPQL1, "tm.project.id = ?1 AND tm.user.id != ?2"), projectId, userId, status);
		} else {
			return findList(String.format(JPQL1, "tm.project.id != ?1 AND tm.user.id = ?2"), projectId, userId, status);
		}
	}
}
