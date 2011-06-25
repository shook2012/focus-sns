package org.osforce.connect.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.connect.service.system.SiteService;
import org.osforce.connect.service.system.UserService;
import org.osforce.connect.service.team.MemberService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.route.RouteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Expose object to web context
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 18, 2011 - 2:22:31 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class ObjectExposeInterceptor extends HandlerInterceptorAdapter {

	private SiteService siteService;
	private UserService userService;
	private ProjectService projectService;
	private MemberService memberService;
	private ProjectCategoryService categoryService;
	
	public ObjectExposeInterceptor() {
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
	
	@Autowired
	public void setCategoryService(ProjectCategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//
		exposeContextPath(request);
		//
		exposeQueryString(request);
		//
		exposeRequestPath(request);
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
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//
		exposeQualifier(request, handler);
		//
		exposeProjectCategory(request, handler);
	}
	
	protected void exposeContextPath(HttpServletRequest request) {
		request.setAttribute(AttributeKeys.BASE_KEY_READABLE, request.getContextPath());
	}
	
	protected void exposeQueryString(HttpServletRequest request) {
		request.setAttribute(AttributeKeys.QUERY_STRING_KEY_READABLE, request.getQueryString());
	}
	
	protected void exposeRequestPath(HttpServletRequest request) {
		request.setAttribute(AttributeKeys.REQUEST_PATH_KEY_READABLE, request.getRequestURI());
	}
	
	protected void exposeSite(HttpServletRequest request) {
		if(request.getAttribute(AttributeKeys.SITE_KEY)==null) {
			String domain = request.getServerName();
			Site site = siteService.getSite(domain);
			request.setAttribute(AttributeKeys.SITE_KEY, site);
			request.setAttribute(AttributeKeys.SITE_KEY_READABLE, site);
			request.setAttribute(AttributeKeys.SITE_DOMAIN_KEY_READABLE, domain);
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
		if(uniqueId==null) {
			uniqueId = request.getParameter(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE);
		}
		if(StringUtils.isNotBlank(uniqueId)) {
			Project project = projectService.getProject(uniqueId);
			request.setAttribute(AttributeKeys.PROJECT_KEY, project);
			request.setAttribute(AttributeKeys.PROJECT_KEY_READABLE, project);
		}
	}
	
	protected void exposeProjectCategory(HttpServletRequest request, Object handler) {
		if(handler instanceof RouteController) {
			String categoryIdStr = request.getParameter("categoryId");
			Project project = (Project) request.getAttribute(AttributeKeys.PROJECT_KEY);
			Site site = (Site) request.getAttribute(AttributeKeys.SITE_KEY);
			String categoryCode = (String) request.getAttribute("categoryCode");
			ProjectCategory currentCategory = null;
			if(StringUtils.isNotBlank(categoryIdStr)) {
				currentCategory = categoryService.getProjectCategory(NumberUtils.createLong(categoryIdStr));
			} else if(StringUtils.isNotBlank(categoryCode)) {
				currentCategory = categoryService.getProjectCategory(site, categoryCode);
			} else if(project!=null) {
				currentCategory = project.getCategory();
			}
			request.setAttribute(AttributeKeys.PROJECT_CATEGORY_CURRENT_KEY_READABLE, currentCategory);
		}
	}
	
	protected void exposeMember(HttpServletRequest request) {
		Project project = (Project) request.getAttribute(AttributeKeys.PROJECT_KEY);
		User user = (User) request.getAttribute(AttributeKeys.USER_KEY);
		if(project!=null && user!=null) {
			TeamMember member = memberService.getMember(project, user, Boolean.TRUE);
			request.setAttribute(AttributeKeys.TEAM_MEMBER_KEY, member);
			request.setAttribute(AttributeKeys.TEAM_MEMBER_KEY_READABLE, member);
		}
	}
	
	protected void exposeQualifier(HttpServletRequest request, Object handler) {
		if(handler instanceof RouteController) {
			// expose qualifier
			String uniqueId = (String) request.getAttribute(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE);
			if(uniqueId==null) {
				uniqueId = request.getParameter(AttributeKeys.PROJECT_UNIQUE_KEY_READABLE);
			}
			if(StringUtils.isNotBlank(uniqueId)) {
				Project project = projectService.getProject(uniqueId);
				request.setAttribute("qualifier", project.getCategory().getCode());
			}
			String categoryCode = (String) request.getAttribute("categoryCode");
			if(StringUtils.isNotBlank(categoryCode)) {
				request.setAttribute("qualifier", categoryCode);
			}
			String categoryIdStr = request.getParameter("categoryId");
			if(StringUtils.isNotBlank(categoryIdStr)) {
				ProjectCategory category = categoryService.getProjectCategory(NumberUtils.createLong(categoryIdStr));
				request.setAttribute("qualifier", category.getCode());
			}
		}
	}
	
}
