package org.osforce.connect.web.module.knowledge;

import javax.validation.Valid;

import org.osforce.connect.entity.knowledge.Answer;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.knowledge.AnswerService;
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
 * @since 1.1.0
 * @create May 20, 2011 - 6:46:37 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/knowledge/answer")
public class AnswerWidget {

	private AnswerService answerService;
	
	public AnswerWidget() {
	}
	
	@Autowired
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}
	
	@RequestMapping("/list-view")
	@Permission({"answer-view"})
	public String doListView(@RequestParam Long questionId,
			Page<Answer> page, Model model) {
		page = answerService.getAnswerPage(page, questionId);
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "knowledge/answer-list";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"answer-add", "answer-edit"}, userRequired=true, projectRequired=true)
	public String doFormView(@RequestParam Long questionId,
			@ModelAttribute @Valid Answer answer, BindingResult result,
			Project project, User user, Model model, Boolean showErrors) {
		if(!showErrors) {
			answer.setQuestionId(questionId);
			answer.setEnteredBy(user);
			answer.setModifiedBy(user);
			model.addAttribute(AttributeKeys.ANSWER_KEY_READABLE, answer);
		}
		return "knowledge/answer-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"answer-add", "answer-edit"}, userRequired=true, projectRequired=true)
	public String doFormAction(@ModelAttribute @Valid Answer answer, 
			BindingResult result, Model model, Project project) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_KNOWLEDGE);
			return "page:/knowledge/question-detail";
		}
		//
		answerService.createAnswer(answer);
		return String.format("redirect:/%s/knowledge/question/detail?questionId=%s", 
				project.getUniqueId(), answer.getQuestionId());
	}
	
}
