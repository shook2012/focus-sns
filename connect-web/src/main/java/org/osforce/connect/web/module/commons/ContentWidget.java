package org.osforce.connect.web.module.commons;

import org.osforce.spring4me.web.bind.annotation.PrefParam;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 19, 2011 - 2:45:17 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/commons/content")
public class ContentWidget {

	public ContentWidget() {
	}
	
	@RequestMapping("/detail-view")
	public String doDetailView(@PrefParam String content, Model model) {
		model.addAttribute("content", content);
		return "commons/content-detail";	
	}
	
}
