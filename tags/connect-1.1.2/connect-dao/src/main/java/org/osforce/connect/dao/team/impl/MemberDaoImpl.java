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
	
	static final String JPQL0 = "FROM TeamMember AS tm WHERE tm.project.id = ?1 AND tm.user.id = ?2 %s";
	public TeamMember findMember(Long projectId, Long userId, Boolean enabled) {
		Assert.notNull(projectId, "Parameter project id can not be null!");
		Assert.notNull(userId, "Parameter user id can not be null!");
		if(enabled==null) {
			return findOne(String.format(JPQL0, ""), projectId, userId);
		}
		return findOne(String.format(JPQL0, "AND tm.enabled = ?3"), projectId, userId, enabled);
	}

	static final String JPQL1 = "FROM TeamMember AS tm WHERE tm.project.id = ?1 AND tm.enabled = TRUE";
	public Page<TeamMember> findMemberPage(Page<TeamMember> page, Long projectId) {
		Assert.notNull(projectId, "Parameter project id can not be null!");
		return findPage(page, JPQL1, projectId);
	}
	
	static final String JPQL2 = "FROM TeamMember AS tm WHERE %s  AND tm.status = ?3 AND tm.enabled = FALSE";
	public List<TeamMember> findMemberList(Long projectId, Long userId,
			String status, Boolean reverse) {
		Assert.notNull(projectId, "Parameter project id can not be null!");
		Assert.notNull(userId, "Parameter user id can not be null!");
		Assert.notNull(status, "Parameter status can not be null!");
		if(reverse) {
			return findList(String.format(JPQL2, "tm.project.id = ?1 AND tm.user.id != ?2"), projectId, userId, status);
		} else {
			return findList(String.format(JPQL2, "tm.project.id != ?1 AND tm.user.id = ?2"), projectId, userId, status);
		}
	}
}
