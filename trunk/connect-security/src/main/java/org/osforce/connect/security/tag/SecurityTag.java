package org.osforce.connect.security.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.system.PermissionService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 20, 2011 - 4:04:30 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class SecurityTag extends TagSupport {
	private static final long serialVersionUID = 707982809607721706L;
	private static final String PROJECT_KEY = "_" + Project.class.getName();
	private static final String USER_KEY = "_" + User.class.getName();
	
	private String code;
	
	public SecurityTag() {
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public int doStartTag() throws JspException {
		WebApplicationContext webApplicationContext = RequestContextUtils
				.getWebApplicationContext(pageContext.getRequest());
		PermissionService permissionService = webApplicationContext.getBean(PermissionService.class);
		Project project = (Project) pageContext.getRequest().getAttribute(PROJECT_KEY);
		User user = (User) pageContext.getRequest().getAttribute(USER_KEY);
		if(permissionService.hasPermission(project, user, code)) {
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}
	
}
