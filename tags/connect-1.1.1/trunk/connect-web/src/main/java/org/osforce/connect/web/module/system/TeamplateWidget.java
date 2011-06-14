package org.osforce.connect.web.module.system;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.Valid;

import org.osforce.connect.entity.commons.Template;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.service.commons.TemplateService;
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
 * @create May 19, 2011 - 8:58:14 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/system/template")
public class TeamplateWidget {

	private TemplateService templateService;
	private ProjectCategoryService categoryService;
	
	public TeamplateWidget() {
	}
	
	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	@Autowired
	public void setCategoryService(ProjectCategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping("/list-view")
	public String doListView(@RequestParam Long siteId, 
			Page<Template> page, Model model) {
		page = templateService.getTemplatePage(page, siteId);
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/template-list";
	}
	
	@RequestMapping(value="/form-view")
	public String doFormView(@RequestParam(required=false) Long siteId, 
			@RequestParam(required=false) Long templateId, 
			@ModelAttribute @Valid Template template, BindingResult result, Model model) {
		if(templateId!=null) {
			template = templateService.getTemplate(templateId);
		}
		List<ProjectCategory> categories =  categoryService.getProjectCategoryList(siteId);
		Map<String, String> categoryOptions = new TreeMap<String, String>();
		for(ProjectCategory category : categories) {
			categoryOptions.put(category.getId().toString(), category.getLabel());
		}
		model.addAttribute("categoryOptions", categoryOptions);
		model.addAttribute(AttributeKeys.TEMPLATE_KEY_READABLE, template);
		model.addAttribute(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
		return "system/template-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	public String doFormAction(@Valid Template template, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "page:/system/template-form";
		}
		//
		if(template.getId()==null) {
			templateService.createTemplate(template);
		} else {
			templateService.updateTemplate(template);
		}
		model.addAttribute(AttributeKeys.TEMPLATE_KEY_READABLE, template);
		return String.format("redirect:/system/template/list?siteId=%s", template.getCategory().getSiteId());
	}
	
}
