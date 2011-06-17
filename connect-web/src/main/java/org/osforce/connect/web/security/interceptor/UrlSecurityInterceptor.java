package org.osforce.connect.web.security.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.route.RouteController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * This interceptor should the first in interceptor stack!
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 20, 2011 - 11:02:23 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class UrlSecurityInterceptor extends HandlerInterceptorAdapter {
	
	private String[] administrators = new String[]{};

	public UrlSecurityInterceptor() {
	}
	
	public void setAdministrators(String administrators) {
		this.administrators = StringUtils.split(administrators, ",");
	}
	
	public void setAdministrators(String[] administrators) {
		this.administrators = administrators;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//
		validateSystemSecurity(request, response, handler);
		return true;
	}
	
	protected void validateSystemSecurity(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws IOException {
		if(handler instanceof RouteController) {
			String requestUri = request.getRequestURI();
			if(StringUtils.contains(requestUri, "system")) {
				User user = (User) request.getAttribute(AttributeKeys.USER_KEY);
				for(String administrator : administrators) {
					if(user!=null && StringUtils.equals(user.getUsername(), administrator)) {
						return ;
					}
				}
				//
				response.sendRedirect(request.getContextPath());
			}
		}
	}
	
}
