package org.osforce.connect.web.module.discussion;

import java.util.List;

import javax.validation.Valid;

import org.osforce.connect.entity.commons.Statistic;
import org.osforce.connect.entity.discussion.Forum;
import org.osforce.connect.entity.discussion.Topic;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.StatisticService;
import org.osforce.connect.service.discussion.ForumService;
import org.osforce.connect.service.discussion.TopicService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.bind.annotation.RequestAttr;
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
 * @create May 21, 2011 - 12:05:40 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/discussion/forum")
public class ForumWidget {

	private TopicService topicService;
	private ForumService forumService;
	private StatisticService statisticService;
	
	public ForumWidget() {
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
	
	@RequestMapping("/list-view")
	@Permission({"forum-view"})
	public String doListView(Page<Topic> page, 
			@RequestAttr Project project, Model model) {
		List<Forum> forums = forumService.getForumList(project);
		for(Forum forum : forums) {
			page = topicService.getTopicPage(page, forum.getId());
			for(Topic topic : page.getResult()) {
				Statistic statistic = statisticService.getStatistic(Statistic.TYPE_VIEW, topic.getId(), Topic.NAME);
				if(statistic!=null) {
					topic.setViews(statistic.getCount());
				}
			}
			forum.setTopics(page.getResult());
		}
		model.addAttribute(AttributeKeys.FORUM_LIST_KEY_READABLE, forums);
		return "discussion/forum-list";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"forum-add", "forum-edit"}, userRequired=true, projectRequired=true)
	public String doFormView(@RequestParam(required=false) Long forumId,
			@ModelAttribute @Valid Forum forum, BindingResult result, 
			@RequestAttr User user, @RequestAttr Project project, Model model, Boolean showErrors) {
		if(!showErrors) {
			forum.setEnteredBy(user);
			forum.setModifiedBy(user);
			forum.setProject(project);
			if(forumId!=null) {
				forum = forumService.getForum(forumId);
			}
			model.addAttribute(AttributeKeys.FORUM_KEY_READABLE, forum);
		}
		return "discussion/forum-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"forum-add", "forum-edit"}, userRequired=true, projectRequired=true)
	public String doFormAction(@ModelAttribute @Valid Forum forum, 
			BindingResult result, Model model, @RequestAttr Project project) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_DISCUSSION);
			return "page:/discussion/forum-form";
		}
		//
		if(forum.getId()==null) {
			forumService.createForum(forum);
		} else {
			forumService.updateForum(forum);
		}
		return String.format("redirect:/%s/discussion/forum/list", project.getUniqueId());
	}
	
}
