package org.osforce.connect.task.knowledge;

import java.util.Map;

import org.osforce.connect.entity.knowledge.Question;
import org.osforce.connect.entity.stream.Activity;
import org.osforce.connect.service.knowledge.QuestionService;
import org.osforce.connect.service.stream.ActivityService;
import org.osforce.spring4me.task.AbstractTask;
import org.osforce.spring4me.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Apr 27, 2011 - 1:21:55 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class QuestionActivityStreamTask extends AbstractTask {

	private ActivityService activityService;
	private QuestionService questionService;

	public QuestionActivityStreamTask() {
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long questionId = (Long) context.get("questionId");
		Question question = questionService.getQuestion(questionId);
		String template = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(questionId);
		activity.setEntity(Question.NAME);
		activity.setType(Question.NAME);
		activity.setDescription(template);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(question.getProjectId());
		activity.setEnteredId(question.getModifiedId());
		activityService.createActivity(activity);
	}

}
