package org.osforce.connect.task.system;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.connect.entity.system.Project;
import org.osforce.spring4me.commons.collection.CollectionUtil;
import org.osforce.spring4me.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Mar 5, 2011 - 3:34:48 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Aspect
@Component
public class SystemAspect {

	private Task userRegisterEmailTask;

	public SystemAspect() {
	}

	@Autowired
	@Qualifier("userRegisterEmailTask")
	public void setUserRegisterEmailTask(Task userRegisterEmailTask) {
		this.userRegisterEmailTask = userRegisterEmailTask;
	}

	@AfterReturning("execution(* org.osforce.connect.service.system.UserService.registerUser(..))")
	public void registerUser(JoinPoint jp) {
		Project project = (Project) jp.getArgs()[1];
		if(StringUtils.equals(project.getCategory().getCode(), "people")) {
			Map<Object, Object> context = CollectionUtil.newHashMap();
			context.put("siteId", project.getCategory().getSiteId());
			context.put("projectId", project.getId());
			context.put("userId", project.getEnteredId());
			userRegisterEmailTask.doAsyncTask(context);
		}
	}

}
