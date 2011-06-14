package org.osforce.connect.web.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * This interceptor should the first in interceptor stack!
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 20, 2011 - 11:02:23 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class UrlSecurityInterceptor extends HandlerInterceptorAdapter {

	public UrlSecurityInterceptor() {
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//System.out.println("do url security check : " + request.getRequestURI());
		
		return super.preHandle(request, response, handler);
	}
	
}
