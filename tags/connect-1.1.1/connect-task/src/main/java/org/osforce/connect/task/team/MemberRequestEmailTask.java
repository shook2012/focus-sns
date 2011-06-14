package org.osforce.connect.task.team;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
 * @create May 5, 2011 - 11:23:08 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Task
public class MemberRequestEmailTask extends AbstractEmailTask {

	private static final String MEMBER_REQUEST_SUBJECT = "email/member_request_subject.ftl";
	private static final String MEMBER_REQUEST_CONTENT = "email/member_request_content.ftl";

	private Configuration configuration;
	private MemberService memberService;

	public MemberRequestEmailTask() {
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
		if(StringUtils.equals(member.getStatus(), TeamMember.STATUS_NEED_ACCEPT)) {
			helper.addTo(member.getUser().getEmail(),
					member.getUser().getNickname());
		} else if(StringUtils.equals(member.getStatus(), TeamMember.STATUS_WAIT_APPROVE)) {
			helper.addTo(member.getProject().getEnteredBy().getEmail(),
					member.getProject().getEnteredBy().getNickname());
		}
		//
		String subject = FreeMarkerTemplateUtils.processTemplateIntoString(
				configuration.getTemplate(MEMBER_REQUEST_SUBJECT), context);
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(
				configuration.getTemplate(MEMBER_REQUEST_CONTENT), context);
		helper.setSubject(subject);
		helper.setText(content, true);
	}

}
