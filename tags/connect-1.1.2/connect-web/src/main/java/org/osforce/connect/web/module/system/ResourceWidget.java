package org.osforce.connect.web.module.system;

import java.util.List;

import javax.validation.Valid;

import org.osforce.connect.entity.system.Resource;
import org.osforce.connect.service.system.ResourceService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 0.1.0
 * @create May 19, 2011 - 10:38:16 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/system/resource")
public class ResourceWidget {

	private ResourceService resourceService;
	
	public ResourceWidget() {
	}
	
	@Autowired
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	@RequestMapping(value="/list-view")
	public String doListView(Model model) {
		List<Resource> resources = resourceService.getResourceList();
		model.addAttribute(AttributeKeys.RESOURCE_LIST_KEY_READABLE, resources);
		return "system/resource-list";
	}
	
	@RequestMapping("/form-view")
	public String doFormView(@RequestParam(required=false) Long resourceId, 
			@ModelAttribute @Valid Resource resource, BindingResult result, Model model) {
		if(resourceId!=null) {
			resource = resourceService.getResource(resourceId);
		}
		model.addAttribute(AttributeKeys.RESOURCE_KEY_READABLE, resource);
		return "system/resource-form";
	}
	
	@RequestMapping(value="form-action", method=RequestMethod.POST)
	public String doFormAction(@Valid Resource resource, BindingResult result) {
		if(result.hasErrors()) {
			return "page:/system/resource-form";
		}
		//
		Assert.notNull(resource.getId(), "Resource is not allowed to add, please use synchronize instead!");
		resourceService.updateResource(resource);
		return "redirect:/system/resource/list";
	}
	
	/**
	 * Synchronize resources from resources.xml which is under /WEB-INF/ 
	 * @return
	 */
	@RequestMapping(value="/sync-action", method=RequestMethod.GET) 
	public String doSyncAction() {
		resourceService.syncResources();
		return "redirect:/system/resource/list";
	}
	
}
