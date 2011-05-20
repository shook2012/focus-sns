package org.osforce.connect.security.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.security.annotation.Permission;
import org.osforce.connect.service.system.PermissionService;
import org.osforce.spring4me.web.interceptor.WidgetInterceptorAdapter;
import org.osforce.spring4me.web.widget.WidgetConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.support.HandlerMethodResolver;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 20, 2011 - 3:49:11 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class WidgetSecurityInterceptor extends WidgetInterceptorAdapter {
	private static final String PROJECT_KEY = "_" + Project.class.getName();
	private static final String USER_KEY = "_" + User.class.getName();

	private PermissionService permissionService; 
	
	private Map<String, String[]> permissionMappings = new HashMap<String, String[]>();
	
	public WidgetSecurityInterceptor() {
	}
	
	@Autowired
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	@Override
	public boolean preHandleWidget(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//
		resolveHandlerMethodPermission(request, handler);
		//
		return checkPermissions(request);
	}
	
	protected boolean checkPermissions(HttpServletRequest request) {
		WidgetConfig widgetConfig = (WidgetConfig) request.getAttribute(WidgetConfig.KEY) ;
		if(widgetConfig!=null) {
			Project project = (Project) request.getAttribute(PROJECT_KEY);
			User user = (User) request.getAttribute(USER_KEY);
			String requestPath = widgetConfig.getPath();
			String[] resources = permissionMappings.get(requestPath);
			if(resources.length > 0) {
				for(String resource : resources) {
					if(permissionService.hasPermission(project, user, resource)) {
						return true;
					}
				}
				return false;
			}
		} 
		return true; 
	}

	protected void resolveHandlerMethodPermission(HttpServletRequest request, Object handler) {
		WidgetConfig widgetConfig = (WidgetConfig) request.getAttribute(WidgetConfig.KEY) ;
		if(widgetConfig!=null) {
			String requestPath = widgetConfig.getPath();
			if(!permissionMappings.containsKey(requestPath)) {
				HandlerMethodResolver handlerMethodResolver = new HandlerMethodResolver();
				handlerMethodResolver.init(handler.getClass());
				RequestMapping classRequestMapping = AnnotationUtils.findAnnotation(handler.getClass(), RequestMapping.class);
				String[] namespaces = classRequestMapping.value();
				Iterator<Method> iter = handlerMethodResolver.getHandlerMethods().iterator();
				while(iter.hasNext()) {
					Method method = iter.next();
					RequestMapping methodRequestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
					String[] paths = methodRequestMapping.value();
					List<String> mappingPaths = buildMappingPaths(namespaces, paths);
					Permission permission = AnnotationUtils.findAnnotation(method, Permission.class);
					String[] resourceCodes = permission!=null ? permission.value() : new String[]{};
					for(String mappingPath : mappingPaths) {
						permissionMappings.put(mappingPath, resourceCodes);
					}
				}
			}
		}
	}
	
	protected List<String> buildMappingPaths(String[] namespaces, String[] paths) {
		List<String> mappingPaths = new ArrayList<String>();
		for(String namespace : namespaces) {
			for(String path : paths) {
				if(!namespace.startsWith("/")) {
					namespace = "/" + namespace;
				}
				if(namespace.endsWith("/")) {
					namespace = namespace.substring(0, namespace.length()-1);
				}
				if(!path.startsWith("/")) {
					path = "/" + path;
				}
				if(path.endsWith("/")) {
					path = path.substring(0, path.length()-1);
				}
				mappingPaths.add(namespace + path);
			}
		}
		return mappingPaths;
	}
	
}
