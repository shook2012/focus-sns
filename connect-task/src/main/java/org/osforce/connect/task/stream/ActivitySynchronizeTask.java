/**
 *
 */
package org.osforce.connect.task.stream;

import java.util.Map;

import org.osforce.connect.entity.oauth.Authorization;
import org.osforce.connect.entity.stream.Activity;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.oauth.AuthorizationService;
import org.osforce.connect.service.stream.ActivityService;
import org.osforce.spring4me.commons.collection.CollectionUtil;
import org.osforce.spring4me.social.api.service.ApiService;
import org.osforce.spring4me.task.AbstractTask;
import org.osforce.spring4me.task.annotation.Task;
import org.scribe.model.Token;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author gavin
 * @since 1.0.3
 * @create May 10, 2011 - 11:24:06 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class ActivitySynchronizeTask extends AbstractTask
	implements ApplicationContextAware {

	private ActivityService activityService;
	private AuthorizationService authorizationService;
	private ApplicationContext applicationContext;

	public ActivitySynchronizeTask() {
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Autowired
	public void setAuthorizationService(
			AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long activityId = (Long) context.get("activityId");
		String[] targets = (String[]) context.get("targets");
		if(targets!=null) {
			Activity activity = activityService.getActivity(activityId);
			for(String target : targets) {
				Token accessToken = getAccessToken(target, activity.getEnteredBy());
				ApiService apiService = getApiService(target);
				Map<String, Object> params = CollectionUtil.newHashMap();
				params.put("format", "json");
				params.put("accessToken", accessToken);
				params.put("status", activity.getDescription());
				apiService.updateStatus(params);
			}
		}
	}

	private Token getAccessToken(String target, User user) {
		Authorization authorization = authorizationService.getAuthorization(target, user.getId());
		return new Token(authorization.getToken(), authorization.getSecret());
	}

	private ApiService getApiService(String target) {
		String beanId = target + ApiService.class.getSimpleName();
		return  applicationContext.getBean(beanId, ApiService.class);
	}

}
