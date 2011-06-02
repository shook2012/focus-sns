package org.osforce.connect.web.module.system;

import org.osforce.connect.entity.system.Project;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 20, 2011 - 1:44:48 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/system/project")
public class ProjectWidget {

	public ProjectWidget() {
	}
	
	@RequestMapping("/title-view")
	@Permission(projectRequired=true)
	public String doTitleView(Project project) {
		if(project==null) {
			return "commons/blank";
		}
		return "system/project-title";
	}
	
}
