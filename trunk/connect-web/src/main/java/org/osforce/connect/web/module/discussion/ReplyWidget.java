package org.osforce.connect.web.module.discussion;

import javax.validation.Valid;

import org.osforce.connect.entity.discussion.Reply;
import org.osforce.connect.entity.discussion.Topic;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.discussion.ReplyService;
import org.osforce.connect.service.discussion.TopicService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 0.1.0
 * @create May 21, 2011 - 12:07:22 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/discussion/reply")
public class ReplyWidget {

	private ReplyService replyService;
	private TopicService topicService;
	
	public ReplyWidget() {
	}
	
	@Autowired
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@RequestMapping("/list-view")
	@Permission({"reply-view"})
	public String doListView(@RequestParam Long topicId, Page<Reply> page, Model model) {
		Topic topic = topicService.viewTopic(topicId);
		page = replyService.getReplyPage(page, topicId);
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		model.addAttribute(AttributeKeys.TOPIC_KEY_READABLE, topic);
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "discussion/reply-list";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"reply-add", "reply-edit"}, userRequired=true)
	public String doFormView(@RequestParam Long topicId,
			@RequestParam(required=false) Long replyId, 
			@ModelAttribute @Valid Reply reply, BindingResult result,
			User user, Model model, Boolean showErrors) {
		if(!showErrors) {
			reply.setEnteredBy(user);
			reply.setModifiedBy(user);
			reply.setTopicId(topicId);
			if(replyId!=null) {
				reply = replyService.getReply(replyId);
			}
			model.addAttribute(AttributeKeys.REPLY_KEY_READABLE, reply);
		}
		Topic topic = topicService.getTopic(topicId);
		model.addAttribute(AttributeKeys.TOPIC_KEY_READABLE, topic);
		return "discussion/reply-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"reply-add", "reply-edit"}, userRequired=true, projectRequired=true)
	public String doFormAction(@ModelAttribute @Valid Reply reply, 
			BindingResult result, Model model, Project project) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_DISCUSSION);
			return "page:/discussion/reply-list";
		}
		//
		if(reply.getId()==null) {
			replyService.createReply(reply);
		} else {
			replyService.updateReply(reply);
		}
		return String.format("redirect:/%s/discussion/reply/list?topicId=%s", 
				project.getUniqueId(), reply.getTopicId());
	}
	
}
