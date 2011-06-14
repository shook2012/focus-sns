package org.osforce.connect.web.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.osforce.connect.service.tracker.UserTracker;
import org.osforce.connect.web.AttributeKeys;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 27, 2011 - 2:09:00 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class TrackerListener implements HttpSessionAttributeListener {

	public TrackerListener() {
	}
	
	public void attributeAdded(HttpSessionBindingEvent se) {
		// user tracker
		UserTracker userTracker = getWebApplicationContext(se).getBean(UserTracker.class);
		if(AttributeKeys.USER_ID_KEY.equals(se.getName())) {
			userTracker.track(se.getName(), se.getValue());
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		// user tracker
		UserTracker userTracker = getWebApplicationContext(se).getBean(UserTracker.class);
		if(AttributeKeys.USER_ID_KEY.equals(se.getName())) {
			userTracker.untrack(se.getName(), se.getValue());
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
	}

	protected WebApplicationContext getWebApplicationContext(HttpSessionBindingEvent se) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(
				se.getSession().getServletContext());
	}
	
}
