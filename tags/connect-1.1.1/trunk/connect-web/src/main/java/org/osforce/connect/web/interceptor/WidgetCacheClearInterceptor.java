package org.osforce.connect.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.osforce.spring4me.web.interceptor.WidgetInterceptorAdapter;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 28, 2011 - 12:05:01 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class WidgetCacheClearInterceptor extends WidgetInterceptorAdapter {

	public WidgetCacheClearInterceptor() {
	}
	
	@Override
	public void afterHandleCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(request.getRequestURI().endsWith("-action")) {
			String widgetName = StringUtils.uncapitalize(handler.getClass().getSimpleName());
			request.getSession().setAttribute("clearCache", widgetName);
		}
	}
	
}
