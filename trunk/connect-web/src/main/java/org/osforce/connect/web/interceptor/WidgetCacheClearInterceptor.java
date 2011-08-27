package org.osforce.connect.web.interceptor;

import org.apache.commons.lang.StringUtils;
import org.osforce.spring4me.web.interceptor.WidgetInterceptorAdapter;
import org.osforce.spring4me.web.widget.core.HttpWidgetRequest;
import org.osforce.spring4me.web.widget.core.HttpWidgetResponse;

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
	public void afterCompletionWidget(HttpWidgetRequest request,
			HttpWidgetResponse response, Object handler, Exception ex)
			throws Exception {
		if(request.getRequestURI().endsWith("-action")) {
			String widgetName = StringUtils.uncapitalize(handler.getClass().getSimpleName());
			request.getSession().setAttribute("clearCache", widgetName);
		}
	}
	
}
