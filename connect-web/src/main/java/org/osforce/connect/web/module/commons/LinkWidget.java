package org.osforce.connect.web.module.commons;

import org.osforce.connect.service.commons.LinkService;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 30, 2011 - 12:30:13 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/commons/link")
public class LinkWidget {

	private LinkService linkService;
	
	public LinkWidget() {
	}
	
	@Autowired
	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
	
	
	
}
