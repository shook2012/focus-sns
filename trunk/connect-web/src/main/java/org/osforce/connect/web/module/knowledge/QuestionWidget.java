package org.osforce.connect.web.module.knowledge;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.commons.Statistic;
import org.osforce.connect.entity.commons.Tag;
import org.osforce.connect.entity.knowledge.Question;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.StatisticService;
import org.osforce.connect.service.commons.TagService;
import org.osforce.connect.service.knowledge.QuestionService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.bind.annotation.SessionParam;
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
 * @create May 20, 2011 - 6:47:15 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/knowledge/question")
public class QuestionWidget {

	private TagService tagService;
	private StatisticService statisticService;
	private QuestionService questionService;
	
	public QuestionWidget() {
	}
	
	@Autowired
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	@Autowired
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	@RequestMapping("/recent-view")
	@Permission({"question-view"})
	public String doRecentView(Page<Question> page, 
			Project project, Model model) {
		page = questionService.getQuestionPage(page, project.getId());
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "knowledge/question-recent";
	}
	
	@RequestMapping("/list-view")
	@Permission({"question-view"})
	public String doListView(@SessionParam String questionOrder,
			Page<Question> page, Project project, Model model) {
		page = questionService.getQuestionPage(page, project.getId());
		for(Question question : page.getResult()) {
			// views count
			Statistic statistic = statisticService.getStatistic(question.getId(), Question.NAME);
			question.setViews(statistic!=null ? statistic.getCount() : 0);
			// tags
			List<Tag> tags = tagService.getTagList(question.getId(), Question.NAME);
			question.setTags(tags);
			model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		}
		return "knowledge/question-list";
	}
	
	@RequestMapping("/detail-view")
	@Permission({"question-view"})
	public String doDetailView(@RequestParam(required=false) 
			Long questionId, User user, Model model) {
		if(questionId!=null) {
			Question question = questionService.viewQuestion(questionId);
			model.addAttribute(AttributeKeys.QUESTION_KEY_READABLE, question);
			return "knowledge/question-detail";
		}
		return "commons/blank";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"question-add", "question-edit"}, userRequired=true, projectRequired=true)
	public String doFormView(@RequestParam(required=false) Long questionId,
			@ModelAttribute @Valid Question question, BindingResult result, 
			Project project, User user, Model model, Boolean showErrors) {
		if(!showErrors) {
			if(questionId==null) {
				question.setEnteredBy(user);
				question.setModifiedBy(user);
				question.setProject(project);
			} else {
				question = questionService.getQuestion(questionId);
				// tags 
				List<Tag> tags = tagService.getTagList(questionId, Question.NAME);
				question.setTags(tags);
			}
			model.addAttribute(AttributeKeys.QUESTION_KEY_READABLE, question);
		} 
		return "knowledge/question-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"question-add", "question-edit"}, userRequired=true, projectRequired=true)
	public String doFormAction(@RequestParam String[] tags,
			@ModelAttribute @Valid Question question, BindingResult result, 
			Project project, User user, Model model) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_KNOWLEDGE);
			return "page:/knowledge/question-form";
		}
		//
		if(question.getId()==null) {
			questionService.createQuestion(question);
		} else {
			question.setModifiedBy(user);
			questionService.updateQuestion(question);
		}
		// tags
		for(String name : tags) {
			if(StringUtils.isNotBlank(name)) {
				Tag tag = new Tag(name, question.getId(), Question.NAME);
				tag.setUserId(user.getId());
				tagService.createTag(tag);
			}
		}
		return String.format("redirect:/%s/knowledge/question/detail?questionId=%s", 
				project.getUniqueId(), question.getId());
	}
	
}
