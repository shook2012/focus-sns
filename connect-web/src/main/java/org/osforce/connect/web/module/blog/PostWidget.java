package org.osforce.connect.web.module.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.blog.Post;
import org.osforce.connect.entity.blog.PostCategory;
import org.osforce.connect.entity.commons.Statistic;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.blog.PostCategoryService;
import org.osforce.connect.service.blog.PostService;
import org.osforce.connect.service.commons.CommentService;
import org.osforce.connect.service.commons.StatisticService;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.bind.annotation.PrefParam;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 0.1.0
 * @create May 21, 2011 - 10:24:26 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/blog/post")
public class PostWidget {

	private PostService postService;
	private ProjectService projectService;
	private StatisticService statisticService;
	private CommentService commentService;
	private PostCategoryService postCategoryService;
	
	public PostWidget() {
	}
	
	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@Autowired
	public void setPostCategoryService(PostCategoryService postCategoryService) {
		this.postCategoryService = postCategoryService;
	}
	
	@RequestMapping("/top-view")
	@Permission({"post-view"})
	public String doTopView(Page<Statistic> page, Project project, Model model) {
		page = statisticService.getTopStatisticPage(page, project, Post.NAME);
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		for(Statistic statistic : page.getResult()) {
			Object linkedEntity = postService.getPost(statistic.getLinkedId());
			statistic.setLinkedEntity(linkedEntity);
		}
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "blog/post-top";
	}
	
	@RequestMapping("/recent-view")
	@Permission({"post-view"})
	public String doRecentView(@PrefParam String uniqueId, @PrefParam String categoryLabel,
			Page<Post> page, Project project, Model model) {
		if(StringUtils.isNotBlank(uniqueId)) {
			Project tmp = projectService.getProject(uniqueId);
			if(tmp!=null) {
				project = tmp;
			}
		}
		PostCategory postCategory = null;
		if(StringUtils.isNotBlank(categoryLabel)) {
			 postCategory = postCategoryService
					.getBlogCategory(project.getId(), categoryLabel);
		}
		page = postService.getPostPage(page, project.getId(), 
				postCategory!=null?postCategory.getId():null);
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		for(Post post : page.getResult()) {
			Statistic statistic = statisticService.getStatistic(Statistic.TYPE_VIEW, post.getId(), Post.NAME);
			if(statistic!=null) {
				post.setViews(statistic.getCount());
			}
		}
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "blog/post-recent";
	}
	
	@RequestMapping("/list-view")
	@Permission({"post-view"})
	public String doListView(@RequestParam(required=false) Long categoryId, 
			Project project, Page<Post> page,  Model model) {
		page = postService.getPostPage(page, project.getId(), categoryId);
		for(Post post : page.getResult()) {
			// views
			Statistic statistic = statisticService.getStatistic(Statistic.TYPE_VIEW, post.getId(), Post.NAME);
			if(statistic!=null) {
				post.setViews(statistic.getCount());
			}
			// count comment
			Long commentNumber = commentService.countComment(post.getId(), Post.NAME);
			post.setCommentNumber(commentNumber);
		}
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "blog/post-list";
	}
	
	@RequestMapping("/detail-view")
	@Permission({"post-view"})
	public String doDetailView(@RequestParam Long postId, User user, Model model) {
		Post post = postService.viewPost(postId);
		model.addAttribute(AttributeKeys.BLOG_POST_KEY_READABLE, post);
		return "blog/post-detail";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"post-add","post-edit"}, userRequired=true, projectRequired=true)
	public String doFormView(@RequestParam(required=false) Long postId,
			@ModelAttribute @Valid Post post, BindingResult result,
			User user, Project project, Model model, Boolean showErrors) {
		if(!showErrors) {
			post.setEnteredId(user.getId());
			post.setModifiedId(user.getId());
			post.setProjectId(project.getId());
			if(postId!=null) {
				post = postService.getPost(postId);
			}
			model.addAttribute(AttributeKeys.BLOG_POST_KEY_READABLE, post);
		}
		// post categories
		List<PostCategory> categories = postCategoryService.getBlogCategoryList(project);
		Map<String, String> categoryOptions = new HashMap<String, String>();
		for(PostCategory category : categories) {
			categoryOptions.put(category.getId().toString(), category.getLabel());
		}
		model.addAttribute("categoryOptions", categoryOptions);
		return "blog/post-form";
	}
	
	@RequestMapping("/form-action")
	@Permission(value={"post-add","post-edit"}, userRequired=true)
	public String doFormAction(@ModelAttribute @Valid Post post, 
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_BLOG);
			return "page:/blog/post-form";
		}
		postService.updatePost(post);
		return String.format("redirect:/%s/blog/post/detail?postId=%s", 
				post.getProject().getUniqueId(), post.getId());
	}
	
}
