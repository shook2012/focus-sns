package org.osforce.connect.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.connect.service.system.SiteService;
import org.osforce.connect.service.system.UserService;
import org.osforce.connect.service.team.MemberService;
import org.osforce.connect.web.AttributeKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Expose object to web context
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 18, 2011 - 2:22:31 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class ObjectExposeInteceptor extends HandlerInterceptorAdapter {

	private SiteService siteService;
	private UserService userService;
	private ProjectService projectService;
	private MemberService memberService;
	
	public ObjectExposeInteceptor() {
	}
	
	@Autowired
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
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
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//
		exposeContextPath(request);
		//
		exposeSite(request);
		//
		exposeTheme(request);
		//
		exposeProject(request);
		//
		exposeUser(request);
		//
		exposeMember(request);
		//
		return true;
	}
	
	protected void exposeContextPath(HttpServletRequest request) {
		request.setAttribute(AttributeKeys.BASE_KEY_READABLE, request.getContextPath());
	}
	
	protected void exposeSite(HttpServletRequest request) {
		if(request.getAttribute(AttributeKeys.SITE_KEY)==null) {
			String domain = request.getServerName();
			Site site = siteService.getSite(domain);
			request.setAttribute(AttributeKeys.SITE_KEY, site);
			request.setAttribute(AttributeKeys.SITE_KEY_READABLE, site);
		}
	}
	// TODO FIXME
	protected void exposeTheme(HttpServletRequest request) {
		request.setAttribute(AttributeKeys.THEME_KEY_READABLE, "default");
	}
	
	protected void exposeUser(HttpServletRequest request) {
		if(request.getSession().getAttribute(AttributeKeys.USER_ID_KEY)!=null) {
			Long userId = (Long) request.getSession().getAttribute(AttributeKeys.USER_ID_KEY);
			User user = userService.getUser(userId);
			request.setAttribute(AttributeKeys.USER_KEY, user);
			request.setAttribute(AttributeKeys.USER_KEY_READABLE, user);
		}
	}
	
	protected void exposeProject(HttpServletRequest request) {
		String uniqueId = (String) request.getAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE);
		if(StringUtils.isNotBlank(uniqueId)) {
			Project project = projectService.getProject(uniqueId);
			request.setAttribute(AttributeKeys.PROJECT_KEY, project);
			request.setAttribute(AttributeKeys.PROJECT_KEY_READABLE, project);
		}
	}
	
	protected void exposeMember(HttpServletRequest request) {
		Project project = (Project) request.getAttribute(AttributeKeys.PROJECT_KEY);
		User user = (User) request.getAttribute(AttributeKeys.USER_KEY);
		if(project!=null && user!=null) {
			TeamMember member = memberService.getMember(user.getId(), project.getId());
			request.setAttribute(AttributeKeys.TEAM_MEMBER_KEY, member);
			request.setAttribute(AttributeKeys.TEAM_MEMBER_KEY_READABLE, member);
		}
	}
	
}
