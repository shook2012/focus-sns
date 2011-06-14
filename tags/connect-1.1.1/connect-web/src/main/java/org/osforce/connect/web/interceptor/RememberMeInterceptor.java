package org.osforce.connect.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SystemUtils;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.system.UserService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.route.RouteController;
import org.osforce.spring4me.commons.cipher.CipherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 30, 2011 - 3:11:34 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class RememberMeInterceptor extends HandlerInterceptorAdapter {

	private static final String KEY_FILE = SystemUtils.USER_HOME + "/.connect/key";
	
	private UserService userService;
	
	public RememberMeInterceptor() {
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(handler.getClass().isAssignableFrom(RouteController.class)) {
			resolveRememberMe(request, response);
		}
		return true;
	}
	
	public void resolveRememberMe(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		if(request.getSession().getAttribute(AttributeKeys.USER_ID_KEY)==null) {
			Cookie cookie = WebUtils.getCookie(request, AttributeKeys.REMEMBER_ME_KEY);
			if(cookie!=null) {
				String username = CipherUtil.decrypt(cookie.getValue(), KEY_FILE);
				User user = userService.getUser(username);
				request.getSession().setAttribute(AttributeKeys.USER_ID_KEY, user.getId());
			}
		}
	}

}
