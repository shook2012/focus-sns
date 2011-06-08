package org.osforce.connect.task.discussion;

import java.util.Map;

import org.osforce.connect.entity.discussion.Reply;
import org.osforce.connect.service.discussion.ReplyService;
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
 * @create May 5, 2011 - 3:20:39 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class ReplyCreateEmailTask extends AbstractEmailTask {

	private static final String REPLY_CREATE_SUBJECT = "email/reply_create_subject.ftl";
	private static final String REPLY_CREATE_CONTENT = "email/reply_create_content.ftl";

	private ReplyService replyService;
	private Configuration configuration;

	public ReplyCreateEmailTask() {
	}

	@Autowired
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	@Autowired
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	protected void prepareMessage(MimeMessageHelper helper,
			Map<Object, Object> context) throws Exception {
		Long replyId = (Long) context.get("replyId");
		Reply reply = replyService.getReply(replyId);
		context.put("topic", reply.getTopic());
		context.put("reply", reply);
		context.put("site", reply.getTopic().getForum().getProject().getCategory().getSite());
		helper.addTo(reply.getTopic().getEnteredBy().getEmail(),
				reply.getTopic().getEnteredBy().getNickname());
		//
		String subject = FreeMarkerTemplateUtils.processTemplateIntoString(
				configuration.getTemplate(REPLY_CREATE_SUBJECT), context);
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(
				configuration.getTemplate(REPLY_CREATE_CONTENT), context);
		helper.setSubject(subject);
		helper.setText(content, true);
	}

}
