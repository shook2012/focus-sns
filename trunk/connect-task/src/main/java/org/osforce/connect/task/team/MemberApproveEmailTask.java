package org.osforce.connect.task.team;

import java.util.Map;

import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.team.MemberService;
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
 * @create May 5, 2011 - 11:22:57 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class MemberApproveEmailTask extends AbstractEmailTask {

	private static final String MEMBER_APPROVE_SUBJECT = "email/member_approve_subject.ftl";
	private static final String MEMBER_APPROVE_CONTENT = "email/member_approve_content.ftl";

	private Configuration configuration;
	private MemberService memberService;

	public MemberApproveEmailTask() {
	}

	@Autowired
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	protected void prepareMessage(MimeMessageHelper helper,
			Map<Object, Object> context) throws Exception {
		Long memberId = (Long) context.get("memberId");
		TeamMember member = memberService.getMember(memberId);
		context.put("member", member);
		context.put("site", member.getProject().getCategory().getSite());
		helper.addTo(member.getUser().getEmail(), member.getUser().getNickname());
		//
		String subject = FreeMarkerTemplateUtils.processTemplateIntoString(
				configuration.getTemplate(MEMBER_APPROVE_SUBJECT), context);
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(
				configuration.getTemplate(MEMBER_APPROVE_CONTENT), context);
		helper.setSubject(subject);
		helper.setText(content, true);
	}

}
