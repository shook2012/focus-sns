package org.osforce.connect.task.knowledge;

import java.util.Map;

import org.osforce.connect.entity.knowledge.Answer;
import org.osforce.connect.service.knowledge.AnswerService;
import org.osforce.spring4me.task.AbstractEmailTask;
import org.osforce.spring4me.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;

/**
 *
 * @author gavin
 * @since 1.0.2
 * @create May 5, 2011 - 5:49:18 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class AnswerCreateEmailTask extends AbstractEmailTask {

	private static final String ANSWER_CREATE_SUBJECT = "email/answer_create_subject.ftl";
	private static final String ANSWER_CREATE_CONTENT = "email/answer_create_content.ftl";

	private Configuration configuration;
	private AnswerService answerService;

	public AnswerCreateEmailTask() {
	}

	@Autowired
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	@Autowired
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	@Override
	protected void prepareMessage(MimeMessageHelper helper,
			Map<Object, Object> context) throws Exception {
		Long answerId = (Long) context.get("answerId");
		Answer answer = answerService.getAnswer(answerId);
		context.put("question", answer.getQuestion());
		context.put("answer", answer);
		context.put("site", answer.getQuestion().getProject().getCategory().getSite());
		helper.addTo(answer.getQuestion().getEnteredBy().getEmail(),
				answer.getQuestion().getEnteredBy().getNickname());
		//
		String subject = FreeMarkerTemplateUtils.processTemplateIntoString(
				configuration.getTemplate(ANSWER_CREATE_SUBJECT), context);
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(
				configuration.getTemplate(ANSWER_CREATE_CONTENT), context);
		helper.setSubject(subject);
		helper.setText(content, true);
	}

}
