package org.osforce.connect.web.module.commons;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.math.NumberUtils;
import org.osforce.connect.entity.commons.Comment;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.CommentService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.commons.collection.CollectionUtil;
import org.osforce.spring4me.web.bind.annotation.PrefParam;
import org.osforce.spring4me.web.bind.annotation.RequestAttr;
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

import com.ocpsoft.pretty.time.PrettyTime;

@Widget
@RequestMapping("/commons/comment")
public class CommentWidget {

	private CommentService commentService;
	
	public CommentWidget() {
	}
	
	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@RequestMapping("/list-view")
	public String doListView(@PrefParam String paramName, 
			@PrefParam String entity, @ModelAttribute @Valid Comment comment, BindingResult result,
			@RequestAttr User user, @RequestAttr Project project, Model model, Boolean showErrors, WebRequest request) {
		if(!showErrors) {
			Long linkedId = NumberUtils.createLong(request.getParameter(paramName));
			comment.setLinkedId(linkedId);
			comment.setEnteredBy(user);
			comment.setModifiedBy(user);
			comment.setEntity(entity);
			List<Comment> comments = commentService.getCommentList(linkedId, entity);
			model.addAttribute(AttributeKeys.COMMENT_KEY_READABLE, comment);
			model.addAttribute(AttributeKeys.COMMENT_LIST_KEY_READABLE, comments);
		}
		return "commons/comment-list";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> doFormAction(
			@ModelAttribute @Valid Comment comment, BindingResult result) {
		if(result.hasErrors()) {
			return Collections.singletonMap("error", "true");
		}
		if(comment.getId()==null) {
			commentService.createComment(comment);
		} else {
			commentService.updateComment(comment);
		}
		//
		return Collections.singletonMap("id", comment.getId().toString());
	}
	
	@RequestMapping(value="/list-action", method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> list(
			@RequestParam Long linkedId, @RequestParam String entity) {
		List<Comment> comments = commentService.getCommentList(linkedId, entity);
		List<Map<String, Object>> commentList = CollectionUtil.newArrayList();
		for(Comment comment : comments) {
			Map<String, Object> model = CollectionUtil.newHashMap();
			model.put("id", comment.getId());
			model.put("linkedId", comment.getLinkedId());
			model.put("content", comment.getContent());
			model.put("entered_pretty", new PrettyTime().format(comment.getEntered()));
			model.put("enteredBy_project_profile_logo_id", comment.getEnteredBy().getProject().getProfile().getLogoId());
			model.put("enteredBy_project_category_code", comment.getEnteredBy().getProject().getCategory().getCode());
			model.put("enteredBy_project_uniqueId", comment.getEnteredBy().getProject().getUniqueId());
			commentList.add(model);
		}
		return commentList;
	}
	
}
