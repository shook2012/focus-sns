package org.osforce.connect.web.module.discussion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.osforce.connect.entity.commons.Statistic;
import org.osforce.connect.entity.discussion.Forum;
import org.osforce.connect.entity.discussion.Topic;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.StatisticService;
import org.osforce.connect.service.discussion.ForumService;
import org.osforce.connect.service.discussion.TopicService;
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

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 21, 2011 - 12:06:28 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/discussion/topic")
public class TopicWidget {

	private TopicService topicService;
	private ForumService forumService;
	private StatisticService statisticService;
	
	public TopicWidget() {
	}
	
	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@Autowired
	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}
	
	@Autowired
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	@RequestMapping("/top-view")
	@Permission({"category-view"})
	public String doTopView(@Pref String categoryCode, Project project,
			Page<Statistic> page, Site site, Model model) {
		page = statisticService.getTopStatisticPage(page, project, Topic.NAME);
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		for(Statistic statistic : page.getResult()) {
			Object linkedEntity = topicService.getTopic(statistic.getLinkedId());
			statistic.setLinkedEntity(linkedEntity);
		}
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "discussion/topic-top";
	}
	
	@RequestMapping("/recent-view")
	@Permission({"category-view"})
	public String doRecentView(Page<Topic> page, Project project, Model model) {
		page = topicService.getTopicPage(page, project);
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		for(Topic topic : page.getResult()) {
			Statistic statistic = statisticService.getStatistic(topic.getId(), Topic.NAME);
			if(statistic!=null) {
				topic.setViews(statistic.getCount());
			}
		}
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "discussion/topic-recent";
	}
	
	@RequestMapping("/list-view")
	@Permission({"category-view"})
	public String doListView(@RequestParam Long forumId, 
			Page<Topic> page,  Model model) {
		page = topicService.getTopicPage(page, forumId);
		for(Topic topic : page.getResult()) {
			Statistic statistic = statisticService.getStatistic(topic.getId(), Topic.NAME);
			if(statistic!=null) {
				topic.setViews(statistic.getCount());
			}
		}
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "discussion/topic-list";
	}
	
	@RequestMapping("/detail-view")
	@Permission({"category-view"})
	public String doDetailVIew(@RequestParam Long topicId, Model model) {
		Topic topic = topicService.getTopic(topicId);
		model.addAttribute(AttributeKeys.TOPIC_KEY_READABLE, topic);
		return "discussion/topic-detail";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"category-add", "category-edit"}, userRequired=true, projectRequired=true)
	public String doFormView( @RequestParam(required=false) Long topicId, 
			@RequestParam(required=false) Long forumId,
			@ModelAttribute @Valid Topic topic, BindingResult result,
			User user, Project project, Model model, Boolean showErrors) {
		if(!showErrors) {
			topic.setEnteredBy(user);
			topic.setModifiedBy(user);
			if(topicId!=null) {
				topic = topicService.getTopic(topicId);
			}
			if(forumId!=null) {
				Forum forum = forumService.getForum(forumId);
				topic.setForum(forum);
			}
			model.addAttribute(AttributeKeys.TOPIC_KEY_READABLE, topic);
		}
		List<Forum> forums = forumService.getForumList(project);
		Map<String, String> forumOptions = new HashMap<String, String>();
		for(Forum forum : forums) {
			forumOptions.put(forum.getId().toString(), forum.getName());
		}
		model.addAttribute(AttributeKeys.FORUM_LIST_KEY_READABLE, forums);
		model.addAttribute("forumOptions", forumOptions);
		return "discussion/topic-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"category-add", "category-edit"}, userRequired=true, projectRequired=true)
	public String doFormAction(@ModelAttribute @Valid Topic topic, 
			BindingResult result, Model model, Project project) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_DISCUSSION);
			return "page:/discussion/topic-form";
		}
		// 
		if(topic.getId()==null) {
			topicService.createTopic(topic);
		} else {
			topicService.updateTopic(topic);
		}
		return String.format("redirect:/%s/discussion/topic/list?forumId=%s", 
				project.getUniqueId(), topic.getForumId());
	}
	
}
