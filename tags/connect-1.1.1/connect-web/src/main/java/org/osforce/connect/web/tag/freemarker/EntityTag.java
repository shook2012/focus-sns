package org.osforce.connect.web.tag.freemarker;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.list.LinkService;
import org.osforce.connect.service.team.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 30, 2011 - 10:53:01 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class EntityTag implements TemplateDirectiveModel {

	private LinkService linkService;
	private MemberService memberService;
	
	public EntityTag() {
	}
	
	@Autowired
	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
	
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		BeanModel userModel = (BeanModel) params.get("user");
		BeanModel projectModel = (BeanModel) params.get("project");
		TemplateScalarModel entityModel = (TemplateScalarModel) params.get("entity");
		TemplateScalarModel existModel = (TemplateScalarModel) params.get("exist");
		Assert.notNull(entityModel, "Parameter entity can not be null!");
		Assert.notNull(userModel, "Parameter user can not be null!");
		Assert.notNull(projectModel, "Parameter project can not be null!");
		String entity = (String) entityModel.getAsString();
		User user = (User) userModel.getWrappedObject();
		Project project = (Project) projectModel.getWrappedObject();
		String exist = existModel!=null ? existModel.getAsString() : null;
		Object value = null;
		if(Profile.NAME.equals(entity)) {
			value = linkService.getLink(user.getProjectId(), project.getProfileId(), entity);
		} else if (TeamMember.NAME.equals(entity)) {
			value = memberService.getMember(project.getId(), user.getId(), Boolean.FALSE);
		}
		//
		boolean flag = false;
		if(StringUtils.equals("no", exist)) {
			flag = value==null;
		} else {
			flag = value!=null;
		}
		//
		if(flag) {
			body.render(env.getOut());
		}
	}
	
}
