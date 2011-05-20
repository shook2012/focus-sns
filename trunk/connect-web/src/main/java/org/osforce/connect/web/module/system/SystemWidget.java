package org.osforce.connect.web.module.system;

import org.osforce.connect.Application;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 18, 2011 - 5:27:35 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/system")
public class SystemWidget {

	@RequestMapping(value="menu-view")
	public String doMenuView() {
		return "system/menu";
	}
	
	@RequestMapping(value="info-view")
	public String doInfoView(Model model) {
		model.addAttribute(AttributeKeys.APPLICATION_KEY_READABLE, new Application());
		return "system/info";
	}
	
}
