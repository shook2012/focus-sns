package org.osforce.connect.web.security.interceptor;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.system.PermissionService;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.commons.collection.CollectionUtil;
import org.osforce.spring4me.web.interceptor.WidgetInterceptorAdapter;
import org.osforce.spring4me.web.widget.WidgetConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.support.HandlerMethodResolver;

/**
 * This interceptor should the last one in interceptor stack
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 20, 2011 - 3:49:11 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class WidgetSecurityInterceptor extends WidgetInterceptorAdapter {
	private static final String PROJECT_KEY = "_" + Project.class.getName();
	private static final String USER_KEY = "_" + User.class.getName();

	private PermissionService permissionService; 
	
	private Map<String, Permission> permissionMappings = CollectionUtil.newHashMap();
	
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
		return validatePermissions(request);
	}
	
	protected boolean validatePermissions(HttpServletRequest request) {
		WidgetConfig widgetConfig = (WidgetConfig) request.getAttribute(WidgetConfig.KEY) ;
		if(widgetConfig!=null) {
			Project project = (Project) request.getAttribute(PROJECT_KEY);
			User user = (User) request.getAttribute(USER_KEY);
			String requestPath = widgetConfig.getPath();
			Permission permission = permissionMappings.get(requestPath);
			if(permission!=null) {
				// validate user
				if(permission.userRequired() && user==null) {
					return false;
				}
				// validate project
				if(permission.projectRequired() && project==null) {
					return false;
				}
				// validate resource
				String[] resources = permission.value();
				return permissionService.hasPermission(project, user, resources);
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
					for(String mappingPath : mappingPaths) {
						permissionMappings.put(mappingPath, permission);
					}
				}
			}
		}
	}
	
	protected List<String> buildMappingPaths(String[] namespaces, String[] paths) {
		List<String> mappingPaths = CollectionUtil.newArrayList();
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
