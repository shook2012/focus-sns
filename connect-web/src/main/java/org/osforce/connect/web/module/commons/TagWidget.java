package org.osforce.connect.web.module.commons;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.osforce.connect.entity.commons.Tag;
import org.osforce.connect.service.commons.TagService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.commons.collection.CollectionUtil;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.bind.annotation.PrefParam;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.1
 * @create Jun 3, 2011 - 11:22:09 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/commons/tag")
public class TagWidget {

	private TagService tagService;
	
	public TagWidget() {
	}
	
	@Autowired
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	@RequestMapping("/list-view")
	public String doListView(@PrefParam String paramName,
			@PrefParam String entity, WebRequest request, Model model) {
		Long linkedId = NumberUtils.createLong(request.getParameter(paramName));
		List<Tag> tags = tagService.getTagList(linkedId, entity);
		model.addAttribute(AttributeKeys.TAG_LIST_KEY_READABLE, tags);
		return "commons/tag-list.ftl";
	}
	
	@RequestMapping(value="/delete-action", method=RequestMethod.GET)
	public @ResponseBody Object doDeleteAction(@RequestParam Long tagId) {
		tagService.deleteTag(tagId);
		return Collections.singletonMap("id", tagId);
	}
	
	@RequestMapping(value="/auto-action", method=RequestMethod.GET)
	public @ResponseBody Object autoComplete(@RequestParam String query) {
		Page<Tag> page = new Page<Tag>(10);
		page = tagService.getTagPage(page, query);
		Map<String, Object> model = CollectionUtil.newHashMap();
		model.put("query", query);
		model.put("data", "");
		List<String> suggestions = CollectionUtil.newArrayList();
		for(Tag tag : page.getResult()) {
			suggestions.add(tag.getName());
		}
		model.put("suggestions", suggestions);
		return model;
	}
	
}
