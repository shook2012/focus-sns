package org.osforce.connect.web.module.blog;

import java.util.List;

import javax.validation.Valid;

import org.osforce.connect.entity.blog.PostCategory;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.connect.service.blog.PostCategoryService;
import org.osforce.connect.web.AttributeKeys;
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
 * @create May 22, 2011 - 6:56:12 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/blog/category")
public class PostCategoryWidget {

	private PostCategoryService postCategoryService;
	
	public PostCategoryWidget() {
	}
	
	@Autowired
	public void setPostCategoryService(PostCategoryService postCategoryService) {
		this.postCategoryService = postCategoryService;
	}
	
	@RequestMapping("/list-view")
	@Permission({"post-category-detail-view"})
	public String doListView(Project project, Model model) {
		List<PostCategory> categories = postCategoryService.getBlogCategoryList(project);
		if(categories.size()==0) {
			return "commons/blank";
		}
		model.addAttribute(AttributeKeys.BLOG_CATEGORY_LIST_KEY_READABLE, categories);
		return "blog/category-list";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"post-category-detail-add", "post-category-detail-edit"}, userRequired=true, projectRequired=true)
	public String doFormView(
			@RequestParam(required=false) Long categoryId,
			Project project, Model model, Boolean showErrors,
			@ModelAttribute("category") @Valid PostCategory category, BindingResult result) {
		if(!showErrors) {
			category.setProjectId(project.getId());
			if(categoryId!=null) {
				category = postCategoryService.getBlogCategory(categoryId);
			}
			model.addAttribute(AttributeKeys.BLOG_CATEGORY_KEY_READABLE, category);
		}
		return "blog/category-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"post-category-detail-add", "post-category-detail-edit"}, userRequired=true, projectRequired=true)
	public String doFormAction(@ModelAttribute("category") @Valid PostCategory category, 
			BindingResult result, Project project, Model model) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_BLOG);
			return "page:/blog/category-form";
		}
		//
		if(category.getId()==null) {
			postCategoryService.createBlogCategory(category);
		} else {
			postCategoryService.updateBlogCategory(category);
		}
		return String.format("redirect:/%s/blog/category/form?categoryId=%s", 
				category.getProject().getUniqueId(), category.getId());
	}
	
}
