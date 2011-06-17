package org.osforce.connect.web.module.list;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.blog.Post;
import org.osforce.connect.entity.discussion.Topic;
import org.osforce.connect.entity.document.File;
import org.osforce.connect.entity.gallery.Photo;
import org.osforce.connect.entity.knowledge.Question;
import org.osforce.connect.entity.list.Link;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.service.blog.PostService;
import org.osforce.connect.service.discussion.TopicService;
import org.osforce.connect.service.document.FileService;
import org.osforce.connect.service.gallery.PhotoService;
import org.osforce.connect.service.knowledge.QuestionService;
import org.osforce.connect.service.list.LinkService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.bind.annotation.PrefParam;
import org.osforce.spring4me.web.bind.annotation.RequestAttr;
import org.osforce.spring4me.web.bind.annotation.SessionParam;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 30, 2011 - 12:30:13 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/list/link")
public class LinkWidget {

	private LinkService linkService;
	private FileService fileService;
	private PostService postService;
	private TopicService topicService;
	private PhotoService photoService;
	private QuestionService questionService;
	
	public LinkWidget() {
	}
	
	@Autowired
	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
	
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@Autowired
	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}
	
	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	@RequestMapping("/recent-view")
	@Permission(value={"link-view"}, projectRequired=true)
	public String doRecentView(Page<Link> page, Model model,
			@RequestAttr Project project, @PrefParam String linkTypes) {
		page.desc("l.entered");
		List<String> types = Arrays.asList(StringUtils.split(linkTypes, ","));
		page = linkService.getLinkPage(page, project, types);
		associateLinkedEntity(page);
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "list/link-recent";
	}
	
	@RequestMapping("/list-view")
	@Permission(value={"link-view"}, projectRequired=true)
	public String doListView(Page<Link> page, 
			@PrefParam("Question") String entity, Model model, 
			@RequestAttr Project project, @SessionParam String linkEntity) {
		// if blank use default entity
		if(StringUtils.isBlank(linkEntity)) {
			linkEntity = entity;  
		}
		page = linkService.getLinkPage(page, project, linkEntity);
		associateLinkedEntity(page);
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "list/link-list";
	}
	
	private void associateLinkedEntity(Page<Link> page) {
		for(Link link : page.getResult()) {
			Object entity = null;
			if(StringUtils.equals(link.getEntity(), Question.NAME)) {
				entity = questionService.getQuestion(link.getToId());
			} else if (StringUtils.equals(link.getEntity(), Post.NAME)) {
				entity = postService.getPost(link.getToId());
			} else if (StringUtils.equals(link.getEntity(), Topic.NAME)) {
				entity = topicService.getTopic(link.getToId());
			} else if (StringUtils.equals(link.getEntity(), Photo.NAME)) {
				entity = photoService.getPhoto(link.getToId());
			} else if (StringUtils.equals(link.getEntity(), File.NAME)) {
				entity = fileService.getFile(link.getToId());
			}
			link.setLinkedEntity(entity);
		}
	}
	
	/**
	 * 
	 * @param type  such as focus, favorite ...
	 * @param link
	 * @return
	 */
	@RequestMapping(value={"/{type}"})
	public @ResponseBody Object focus(@PathVariable String type, Link link) {
		link.setType(type);
		Link tmp = linkService.getLink(link.getFromId(), link.getToId(), link.getEntity());
		if(tmp==null) {
			linkService.createLink(link);
		} else {
			tmp.setType(link.getType());
			linkService.updateLink(tmp);
		}
		return Collections.singletonMap("id", link.getId());
	}
	
}
