package org.osforce.connect.web.module.search;

import java.util.List;

import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.service.search.SearchProfileService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.bind.annotation.RequestAttr;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.2
 * @create Jun 16, 2011 - 11:39:15 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/search/profile")
public class SearchProfileWidget {

	private ProjectCategoryService categoryService;
	private SearchProfileService searchProfileService;
	
	public SearchProfileWidget() {
	}
	
	@Autowired
	public void setCategoryService(ProjectCategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@Autowired
	public void setSearchProfileService(
			SearchProfileService searchProfileService) {
		this.searchProfileService = searchProfileService;
	}
	
	@RequestMapping("/form-view")
	public String doFormView(@RequestAttr Site site, Model model) {
		List<ProjectCategory> categories = categoryService.getProjectCategoryList(site.getId());
		model.addAttribute(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
		return "search/profile-form";
	}
	
	@RequestMapping(value="/result-view")
	public String doResultView(Page<Profile> page, Model model,
			@RequestParam String title, @RequestParam Long categoryId) {
		page = searchProfileService.searchProfilePage(page, title, categoryId);
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "search/profile-result";
	}
	
}