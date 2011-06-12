package org.osforce.connect.web.module.list;

import java.util.Collections;

import org.osforce.connect.entity.commons.Link;
import org.osforce.connect.service.commons.LinkService;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public LinkWidget() {
	}
	
	@Autowired
	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
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
