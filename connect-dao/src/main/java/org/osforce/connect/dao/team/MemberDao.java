package org.osforce.connect.dao.team;

import java.util.List;

import org.osforce.connect.entity.team.TeamMember;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:26:31 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface MemberDao extends BaseDao<TeamMember> {

	/**
	 * Find one member
	 * @param projectId
	 * @param userId
	 * @return
	 */
	TeamMember findMember(Long projectId, Long userId, Boolean enabled);
	
	/**
	 * Find members by ...
	 * @param page
	 * @param projectId
	 * @return
	 */
	Page<TeamMember> findMemberPage(Page<TeamMember> page, Long projectId);

	/**
	 * Find unconnected members by ...
	 * @param projectId
	 * @param userId
	 * @param status
	 * @param reverse
	 * @return
	 */
	List<TeamMember> findMemberList(Long projectId, Long userId, String status,
			Boolean reverse);

}
