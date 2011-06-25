package org.osforce.connect.web.module.team;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.connect.service.system.RoleService;
import org.osforce.connect.service.system.UserService;
import org.osforce.connect.service.team.MemberService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.commons.collection.CollectionUtil;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.bind.annotation.PrefParam;
import org.osforce.spring4me.web.bind.annotation.RequestAttr;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 21, 2011 - 1:59:14 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/team/member")
public class MemberWidget {

	private RoleService roleService;
	private UserService userService;
	private ProjectService projectService;
	private MemberService memberService;
	
	public MemberWidget() {
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/info-view")
	@Permission(value={"member-add"}, projectRequired=true, userRequired=true)
	public String doInfoView(@RequestAttr User user, 
			@RequestAttr Project project, Model model) {
		TeamMember teamMember = memberService.getMember(project, user, Boolean.TRUE);
		List<TeamMember> needApprove = Collections.emptyList();
		List<TeamMember> needAccept = Collections.emptyList();
		if(NumberUtils.compare(project.getId(), user.getProject().getId())==0 ||
				NumberUtils.compare(project.getEnteredBy().getId(), user.getId())==0 ||
				(teamMember!=null && teamMember.getRole().getLevel()<=10)) {
			needApprove = memberService.getMemberList(project, user, 
					TeamMember.STATUS_WAIT_APPROVE, true);
			needAccept = memberService.getMemberList(project, user, 
					TeamMember.STATUS_NEED_ACCEPT, false);
		}
		model.addAttribute("needApprove", needApprove);
		model.addAttribute("needAccept", needAccept);
		//
		List<Role> roles = roleService.getRoleList(project.getCategoryId());
		model.addAttribute(AttributeKeys.ROLE_LIST_KEY_READABLE, roles);
		return "team/member-info";
	}
	
	@RequestMapping("/list-view")
	@Permission({"member-view"})
	public String doListView(Page<TeamMember> page, 
			@RequestAttr User user, @RequestAttr Project project, Model model) {
		if(user!=null) {
			TeamMember teamMember = memberService.getMember(project, user, Boolean.TRUE);
			List<TeamMember> needApprove = Collections.emptyList();
			List<TeamMember> waitApprove = Collections.emptyList();
			List<TeamMember> needAccept = Collections.emptyList();
			List<TeamMember> waitAccept = Collections.emptyList();
			if(NumberUtils.compare(project.getId(), user.getProject().getId())==0 ||
					(teamMember!=null && teamMember.getRole().getLevel()<=10)) {
				needApprove = memberService.getMemberList(project, user, 
						TeamMember.STATUS_WAIT_APPROVE, true);
				waitApprove = memberService.getMemberList(project, user, 
						TeamMember.STATUS_WAIT_APPROVE, false);
				needAccept = memberService.getMemberList(project, user, 
						TeamMember.STATUS_NEED_ACCEPT, false);
				waitAccept = memberService.getMemberList(project, user, 
						TeamMember.STATUS_NEED_ACCEPT, true);
			}
			model.addAttribute("needApprove", needApprove);
			model.addAttribute("waitApprove", waitApprove);
			model.addAttribute("needAccept", needAccept);
			model.addAttribute("waitAccept", waitAccept);
		}
		page = memberService.getMemberPage(page, project);
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "team/member-list";
	}
	// TODO need role code support
	public String doShowView(@PrefParam String uniqueId, 
			@PrefParam String roleCode, Page<TeamMember> page, Model model) {
		if(StringUtils.isNotBlank(uniqueId)) {
			Project project = projectService.getProject(uniqueId);
			if(project!=null) {
				page = memberService.getMemberPage(page, project);
				model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
				return "team/member-show";
			}
		}
		return "commons/blank";
	}
	
	@RequestMapping("/invite-view")
	@Permission(value={"member-add", "member-edit"}, userRequired=true, projectRequired=true)
	public String doInviteView(@RequestAttr Project project, Model model) {
		List<Role> roles = roleService.getRoleList(project.getCategoryId());
		model.addAttribute(AttributeKeys.ROLE_LIST_KEY_READABLE, roles);
		return "team/member-invite";
	}
	
	@RequestMapping(value="/invite-action", method=RequestMethod.POST)
	@Permission(value={"member-add", "member-edit"}, userRequired=true, projectRequired=true)
	public String doInviteAction(@RequestParam String emails, Model model, 
			@RequestParam Long roleId, @RequestAttr Project project, @RequestAttr User user) {
		String[] emailsArray = StringUtils.split(emails, "\n");
		for(String email : emailsArray) {
			User tmp = userService.getUser(StringUtils.trim(email));
			if(tmp!=null && NumberUtils.compare(tmp.getId(), user.getId())!=0) {
				Role role = roleService.getRole(roleId);
				TeamMember member = memberService.getMember(project, user);
				if(member==null) {
					member = new TeamMember(project, tmp, role);
					member.setStatus(TeamMember.STATUS_NEED_ACCEPT);
					memberService.requestMember(member);
				} 
			}
		}
		return String.format("redirect:/%s/team", project.getUniqueId());
	}
	
	@RequestMapping(value="/auto-action", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> doAutoAction(
			@RequestParam String query) {
		Page<User> page = new Page<User>(10);
		page = userService.getUserPage(page, query);
		Map<String, Object> model = CollectionUtil.newHashMap();
		model.put("query", query);
		model.put("data", "");
		List<String> suggestions = CollectionUtil.newArrayList();
		for(User user : page.getResult()) {
			suggestions.add(user.getUsername());
		}
		model.put("suggestions", suggestions);
		return model;
	}
	
	@RequestMapping(value="/request")
	public @ResponseBody Object doRequestAction (TeamMember member) {
		Project project = projectService.getProject(member.getProjectId());
		Role role = roleService.getRole(project.getCategoryId(), Role.LEVEL_MIDDLE);
		member.setRole(role);
		member.setStatus(TeamMember.STATUS_WAIT_APPROVE);
		memberService.requestMember(member);
		return Collections.singletonMap("id", member.getId());
	}
	
	@RequestMapping(value={"/approve"})
	public @ResponseBody Object doApproveAction (
			@RequestParam Long memberId, @RequestParam(required=false) Long roleId) {
		memberService.approveMember(memberId);
		return Collections.singletonMap("id", memberId);
	}
	
}
