/**
 *
 */
package org.osforce.connect.task.stream;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.connect.entity.stream.Activity;
import org.osforce.spring4me.commons.collection.CollectionUtil;
import org.osforce.spring4me.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author gavin
 * @since 1.0.3
 * @create May 10, 2011 - 11:23:21 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Aspect
@Component
public class StreamAspect {

	private Task activitySynchronizeTask;

	public StreamAspect() {
	}

	@Autowired
	@Qualifier("activitySynchronizeTask")
	public void setActivitySynchronizeTask(Task activitySynchronizeTask) {
		this.activitySynchronizeTask = activitySynchronizeTask;
	}

	@AfterReturning("execution(* org.osforce.connect.service.stream.ActivityService.createActivity(..))")
	public void createActivity(JoinPoint jp) {
		Activity activity = (Activity) jp.getArgs()[0];
		Map<Object, Object> context = CollectionUtil.newHashMap();
		context.put("activityId", activity.getId());
		context.put("targets", activity.getTargets());
		activitySynchronizeTask.doAsyncTask(context);
	}

}
