package org.osforce.connect.web.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.route.RouteController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 31, 2011 - 2:44:23 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class ObjectNotFoundInterceptor extends HandlerInterceptorAdapter {

	public ObjectNotFoundInterceptor() {
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//
		siteNotFound(request, response, handler);
		//
		return super.preHandle(request, response, handler);
	}
	
	protected void siteNotFound(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		if(handler.getClass().isAssignableFrom(RouteController.class)) {
			if(request.getAttribute(AttributeKeys.SITE_KEY)==null) {
				String redirectPath = request.getContextPath() + "/system";
				String requestPath = request.getRequestURI();
				if(!StringUtils.startsWith(requestPath, redirectPath)) {
					response.sendRedirect(redirectPath);
				}
			}
		}
	}
	
}
