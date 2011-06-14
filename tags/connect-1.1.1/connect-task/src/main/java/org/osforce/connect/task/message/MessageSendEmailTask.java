package org.osforce.connect.task.message;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import org.osforce.connect.entity.message.Message;
import org.osforce.connect.service.message.MessageService;
import org.osforce.spring4me.task.AbstractEmailTask;
import org.osforce.spring4me.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 3, 2011 - 3:21:12 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class MessageSendEmailTask extends AbstractEmailTask {
	private static final String TEMPLATE_MESSAGE_CREATE_SUBJECT = "email/message_create_subject.ftl";
	private static final String TEMPLATE_MESSAGE_CREATE_CONTENT = "email/message_create_content.ftl";
	
	private Configuration configuration;
	private MessageService messageService;
	
	public MessageSendEmailTask() {
	}
	
	@Autowired
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@Override
	protected void prepareMessage(MimeMessageHelper helper,
			Map<Object, Object> context) throws TemplateException, IOException, MessagingException {
		Long messageId = (Long) context.get("messageId");
		Message message = messageService.getMessage(messageId);
		context.put("message", message);
		context.put("site", message.getFrom().getCategory().getSite());
		//
		String subject = FreeMarkerTemplateUtils.processTemplateIntoString(
				configuration.getTemplate(TEMPLATE_MESSAGE_CREATE_SUBJECT), context); 
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(
				configuration.getTemplate(TEMPLATE_MESSAGE_CREATE_CONTENT), context);
		helper.setSubject(subject);
		helper.setText(content, true);
		helper.addTo(message.getTo().getEnteredBy().getEmail(), 
				message.getTo().getEnteredBy().getNickname());
	}
	
}
