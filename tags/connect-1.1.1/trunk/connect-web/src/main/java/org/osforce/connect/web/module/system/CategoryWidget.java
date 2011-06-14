package org.osforce.connect.web.module.system;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.Valid;

import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.web.AttributeKeys;
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
 * @create May 18, 2011 - 9:33:50 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/system/category")
public class CategoryWidget {

	private ProjectCategoryService categoryService;
	
	public CategoryWidget() {
	}
	
	@Autowired
	public void setCategoryService(ProjectCategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(value="/list-view", method=RequestMethod.GET)
	public String doListView(Page<ProjectCategory> page, @RequestParam Long siteId, 
			@RequestParam(required=false) Long parentId,  Model model) {
		if(parentId!=null) {
			ProjectCategory parent = categoryService.getProjectCategory(parentId);
			model.addAttribute(AttributeKeys.PARENT_KEY_READABLE, parent);
		}
		page = categoryService.getProjectCategoryPage(page, siteId, parentId); 
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/category-list";
	}

	@RequestMapping(value="/form-view")
	public String doFormView(@RequestParam(required=false) Long categoryId, 
			@RequestParam(required=false) Long parentId, @RequestParam Long siteId, 
			@ModelAttribute(AttributeKeys.PROJECT_CATEGORY_KEY_READABLE) 
			@Valid ProjectCategory category, BindingResult result, Model model) {
		category.setSiteId(siteId);
		category.setParentId(parentId);
		if(parentId!=null) {
			List<ProjectCategory> categories = categoryService.getSiblingProjectCategoryList(siteId, parentId);
			model.addAttribute("categoryOptions", getCategoryOptions(categories));
			model.addAttribute(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
		} else if (categoryId!=null) {
			category = categoryService.getProjectCategory(categoryId);
			if(category.getParent()!=null) {
				List<ProjectCategory> categories = categoryService
						.getSiblingProjectCategoryList(siteId, category.getParentId());
				model.addAttribute("categoryOptions", getCategoryOptions(categories));
				model.addAttribute(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
			}
		}
		model.addAttribute(AttributeKeys.PROJECT_CATEGORY_KEY_READABLE, category);
		return "system/category-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	public String doFormAction(@Valid ProjectCategory category, BindingResult result) {
		if(result.hasErrors()) {
			return "page:/system/category-form";
		}
		if(category.getId()==null) {
			categoryService.createProjectCategory(category);
		} else {
			categoryService.updateProjectCategory(category);
		}
		return String.format("redirect:/system/category/list?siteId=%s", category.getSiteId());
	}
	
	private Map<String, String> getCategoryOptions(List<ProjectCategory> categories) {
		Map<String, String> categoryOptions = new TreeMap<String, String>();
		for(ProjectCategory tmp : categories) {
			categoryOptions.put(tmp.getId().toString(), tmp.getLabel());
		}
		return categoryOptions;
	}
	
}
