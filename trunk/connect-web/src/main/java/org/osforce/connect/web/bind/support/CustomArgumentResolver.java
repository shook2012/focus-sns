package org.osforce.connect.web.bind.support;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.widget.WidgetConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 13, 2011 - 4:37:04 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class CustomArgumentResolver implements WebArgumentResolver {

	private static List<Class<?>> classes = new ArrayList<Class<?>>();
	private ConversionService conversionService;
	
	static {
		//classes.add(Site.class);
		classes.add(Page.class);
		//classes.add(Project.class);
		//classes.add(User.class);
		//classes.add(TeamMember.class);
	}

	public CustomArgumentResolver() {
	}

	@Autowired
	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		Class<?> paramType = methodParameter.getParameterType();
		WidgetConfig widgetConfig = (WidgetConfig) webRequest
				.getAttribute(WidgetConfig.KEY, WebRequest.SCOPE_REQUEST);
		Object value = null;
		if(paramType.isAssignableFrom(Page.class)) {
			value = getPage(widgetConfig, webRequest.getNativeRequest(HttpServletRequest.class));
			return returnValue(webRequest, value, Page.class); 
		} /*else if(paramType.isAssignableFrom(Site.class)) {
			value = webRequest.getAttribute(AttributeKeys.SITE_KEY, WebRequest.SCOPE_REQUEST);
			return returnValue(webRequest, value, Site.class);
		} else if(paramType.isAssignableFrom(Project.class)) {
			value = webRequest.getAttribute(AttributeKeys.PROJECT_KEY, WebRequest.SCOPE_REQUEST);
			return returnValue(webRequest, value, Project.class);
		} else if(paramType.isAssignableFrom(User.class)) {
			value = webRequest.getAttribute(AttributeKeys.USER_KEY, WebRequest.SCOPE_REQUEST);
			return returnValue(webRequest, value, User.class);
		} else if(paramType.isAssignableFrom(TeamMember.class)) {
			value = webRequest.getAttribute(AttributeKeys.TEAM_MEMBER_KEY, WebRequest.SCOPE_REQUEST);
			return returnValue(webRequest, value, TeamMember.class);
		}*/ else if(AttributeKeys.SHOW_ERRORS_KEY_READABLE.equals(methodParameter.getParameterName())) {
			value = (Boolean) webRequest.getAttribute(
					AttributeKeys.SHOW_ERRORS_KEY_READABLE, WebRequest.SCOPE_REQUEST);
			return returnValue(webRequest, value!=null?value:false, Boolean.class);
		}
		//
		return WebArgumentResolver.UNRESOLVED;
	}
	
	private Object returnValue(NativeWebRequest webRequest, Object value, Class<?> clazz) {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		if(value==null && "POST".equals(request.getMethod())
				&& classes.contains(clazz)) {
			return WebArgumentResolver.UNRESOLVED;
		}
		return value;
	}
	
	// TODO need to refactor
	private Page<?> getPage(WidgetConfig widgetConfig, HttpServletRequest request) {
		//String fragmentId = widgetConfig.getId();
		//String pageKey = fragmentId + "_page";
		//Page<?> page = (Page<?>) request.getSession().getAttribute(pageKey);
		Page<?> page = null;
		if(page==null) {
			// new page
			Integer pageSize = 10;
			if(widgetConfig.getPrefs().get("limit")!=null) {
				String prefValue =widgetConfig.getPrefs().get("limit");
				pageSize = conversionService.convert(prefValue, Integer.class);
			} else if(widgetConfig.getPrefs().get("pageSize")!=null) {
				String prefValue = widgetConfig.getPrefs().get("pageSize");
				pageSize = conversionService.convert(prefValue, Integer.class);
			} 
			page = new Page<Object>(pageSize);
		}
		// set page no
		Integer pageNo = 1;
		if(request.getParameter("pageNo")!=null) {
			String paramValue = request.getParameter("pageNo");
			pageNo = conversionService.convert(paramValue, Integer.class);
		} 
		page.setPageNo(pageNo);
		// set page to session
		//request.getSession().setAttribute(pageKey, page);
		return page;
	}
}
