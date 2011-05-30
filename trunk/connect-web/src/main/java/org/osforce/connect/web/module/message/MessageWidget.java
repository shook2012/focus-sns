package org.osforce.connect.web.module.message;

import java.util.Collections;

import javax.validation.Valid;

import org.osforce.connect.entity.message.Message;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.message.MessageService;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.bind.annotation.Pref;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 0.1.0
 * @create May 22, 2011 - 10:03:05 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/message/message")
public class MessageWidget {

	private ProjectService projectService;
	private MessageService messageService;
	
	public MessageWidget() {
	}
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@RequestMapping("/list-view")
	@Permission({"message-view"})
	public String doListView(@Pref String  box, 
			Page<Message> page, User user, Project project, Model model) {
		page = messageService.getMessagePage(page, project, box);
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		model.addAttribute("box", box);
		return "message/message-list";
	}
	
	@RequestMapping("/info-view")
	@Permission({"message-view"})
	public String doInfoView(Project project, Model model) {
		Long unreadCount = messageService.countMessages(project, false);
		if(unreadCount==0) {
			return "commons/blank";
		}
		model.addAttribute("unreadCount", unreadCount);
		return "message/message-info";
	}
	
	@RequestMapping("/detail-view")
	@Permission({"message-view"})
	public String doDetailView(@RequestParam Long messageId,
			User user, Model model, WebRequest request) {
		Message message = messageService.getMessage(messageId);
		String box = (String) request.getAttribute("box", WebRequest.SCOPE_REQUEST);
		if("inbox".equals(box)) {
			message.setReadBy(user);
			message.setRead(true);
			messageService.updateMessage(message);
		}
		model.addAttribute(AttributeKeys.MESSAGE_KEY_READABLE, message);
		return "message/message-detail";
	}
	
	@RequestMapping("/form-view")
	public String doFormView(@RequestParam Long toId, 
			@RequestParam(required=false) Long messageId, 
			@RequestParam Long fromId, User user, Model model,
			@ModelAttribute @Valid Message message, BindingResult result, Boolean showErrors) {
		if(!showErrors) {
			message.setEnteredBy(user);
			if(fromId!=null) {
				Project from = projectService.getProject(fromId);
				message.setFrom(from);
			} 
			if(toId!=null) {
				Project to = projectService.getProject(toId);
				message.setTo(to);
			}
			if(messageId!=null) {
				Message original = messageService.getMessage(messageId);
				message.setSubject(original.getSubject());
				message.setTo(original.getFrom());
				message.setReply(true);
			}
			model.addAttribute(AttributeKeys.MESSAGE_KEY_READABLE, message);
		}
		return "message/message-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	public @ResponseBody Object doFormAction(@ModelAttribute @Valid Message message, 
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			return Collections.singletonMap("error", result.getAllErrors().size());
		}
		//
		if(message.getId()==null) {
			messageService.createMessage(message);
		} else {
			messageService.updateMessage(message);
		}
		return Collections.singletonMap("id", message.getId());
	}
	
}
