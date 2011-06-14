package org.osforce.connect.web.security.tag.freemarker;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.system.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 20, 2011 - 10:29:34 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class SecurityTag implements TemplateDirectiveModel {
	private static final String PROJECT_KEY = "_" + Project.class.getName();
	private static final String USER_KEY = "_" + User.class.getName();
	
	private PermissionService permissionService;
	
	public SecurityTag() {
	}
	
	@Autowired
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String code = params.get("code").toString();
		Assert.notNull(code, "Parameter code can not be null");
		String[] resources = StringUtils.split(code, "|");
		BeanModel projectModel = (BeanModel) env.getDataModel().get(PROJECT_KEY);
		BeanModel userModel = (BeanModel) env.getDataModel().get(USER_KEY);
		Project project = (Project) projectModel.getWrappedObject();
		User user = userModel!=null ? (User) userModel.getWrappedObject() : null;
		if(permissionService.hasPermission(project, user, resources)) {
			body.render(env.getOut());
		}
	}

}
