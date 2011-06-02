package org.osforce.connect.web.module.system;

import javax.validation.Valid;

import org.osforce.connect.entity.system.Site;
import org.osforce.connect.service.system.SiteService;
import org.osforce.connect.service.system.ThemeService;
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

@Widget
@RequestMapping("/system/site")
public class SiteWidget {

	private SiteService siteService;
	private ThemeService themeService;

	public SiteWidget() {
	}
	
	@Autowired
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	@Autowired
	public void setThemeService(ThemeService themeService) {
		this.themeService = themeService;
	}

	@RequestMapping(value="/list-view")
	public String doListView(Page<Site> page, Model model) {
		page = siteService.getSitePage(page);
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/site-list";
	}

	@RequestMapping(value="/copyright-view")
	public String doCopyrightView(Site site, Model model) {
		model.addAttribute(AttributeKeys.SITE_KEY_READABLE, site);
		return "system/site-copyright";
	}

	@RequestMapping(value="/form-view")
	public String doFormView(@RequestParam(required=false) Long siteId, 
			@ModelAttribute @Valid Site site, BindingResult result, Model model) {
		if(siteId!=null) {
			site = siteService.getSite(siteId);
		}
		model.addAttribute(AttributeKeys.SITE_KEY, site);
		model.addAttribute(AttributeKeys.SITE_KEY_READABLE, site);
		return "system/site-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	public String doFormAction(@ModelAttribute @Valid Site site, BindingResult result) {
		if(result.hasErrors()) {
			return "page:/system/site-form";
		}
		if(site.getId()==null) {
			siteService.createSite(site);
		} else {
			siteService.updateSite(site);
		}
		return "redirect:/system/site/list";
	}
	
}
