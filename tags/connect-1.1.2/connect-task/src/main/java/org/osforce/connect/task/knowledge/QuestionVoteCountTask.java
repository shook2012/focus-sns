package org.osforce.connect.task.knowledge;

import java.util.Map;

import org.osforce.connect.service.knowledge.QuestionService;
import org.osforce.spring4me.task.AbstractTask;
import org.osforce.spring4me.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;

@Task
public class QuestionVoteCountTask extends AbstractTask {

	private QuestionService questionService;
	
	public QuestionVoteCountTask() {
	}
	
	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		
	}
	
}
