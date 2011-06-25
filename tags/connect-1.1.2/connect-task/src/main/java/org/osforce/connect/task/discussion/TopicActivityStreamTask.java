package org.osforce.connect.task.discussion;

import java.util.Map;

import org.osforce.connect.entity.discussion.Topic;
import org.osforce.connect.entity.stream.Activity;
import org.osforce.connect.service.discussion.TopicService;
import org.osforce.connect.service.stream.ActivityService;
import org.osforce.spring4me.task.AbstractTask;
import org.osforce.spring4me.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 5:07:29 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class TopicActivityStreamTask extends AbstractTask {

	private TopicService topicService;
	private ActivityService activityService;

	public TopicActivityStreamTask() {
	}

	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long topicId = (Long) context.get("topicId");
		Topic topic = topicService.getTopic(topicId);
		String template = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(topicId);
		activity.setEntity(Topic.NAME);
		activity.setType(Topic.NAME);
		activity.setDescription(template);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(topic.getForum().getProjectId());
		activity.setEnteredId(topic.getModifiedId());
		activityService.createActivity(activity);
	}
}
