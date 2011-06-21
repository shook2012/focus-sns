package org.osforce.connect.service.team.impl;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.RoleDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.dao.team.MemberDao;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.team.MemberService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:43:19 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	private UserDao userDao;
	private RoleDao roleDao;
	private ProjectDao projectDao;
	private MemberDao memberDao;

	public MemberServiceImpl() {
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public TeamMember getMember(Long memberId) {
		return memberDao.get(memberId);
	}
	
	public TeamMember getMember(Project project, User user) {
		return memberDao.findMember(project.getId(), user.getId(), null);
	}

	public TeamMember getMember(Project project, User user, Boolean enabled) {
		return memberDao.findMember(project.getId(), user.getId(), enabled);
	}

	public void createMember(TeamMember member) {
		updateMember(member);
	}

	public void updateMember(TeamMember member) {
		if(member.getUserId()!=null) {
			User user = userDao.get(member.getUserId());
			member.setUser(user);
		}
		if(member.getRoleId()!=null) {
			Role role = roleDao.get(member.getRoleId());
			member.setRole(role);
		}
		if(member.getProjectId()!=null) {
			Project project = projectDao.get(member.getProjectId());
			member.setProject(project);
		}
		if(member.getId()==null) {
			memberDao.save(member);
		} else {
			memberDao.update(member);
		}
	}

	public void deleteMember(Long memberId) {
		memberDao.delete(memberId);
	}

	public Page<TeamMember> getMemberPage(Page<TeamMember> page, Project project) {
		return memberDao.findMemberPage(page, project.getId());
	}

	public List<TeamMember> getMemberList(Project project, User user,
			String status, Boolean reverse) {
		return memberDao.findMemberList(project.getId(), user.getId(), status, reverse);
	}

	public void requestMember(TeamMember member) {
		createMember(member);
	}

	public void approveMember(Long memberId) {
		TeamMember member = memberDao.get(memberId);
		member.setStatus(null);
		member.setEnabled(true);
		memberDao.update(member);
		// if project is user project
		if(NumberUtils.compare(member.getProject().getId(),
				member.getProject().getEnteredBy().getProject().getId())==0) {
			TeamMember otherSide = memberDao.findMember(
					member.getUser().getProjectId(), member.getProject().getEnteredId(), null);
			if(otherSide==null) {
				otherSide = new TeamMember();
			}
			otherSide.setStatus(null);
			otherSide.setEnabled(true);
			otherSide.setProjectId(member.getUser().getProjectId());
			otherSide.setUserId(member.getProject().getEnteredId());
			otherSide.setRoleId(member.getRoleId());
			memberDao.save(otherSide);
		}
	}
}
