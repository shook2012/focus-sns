package org.osforce.connect.web.module.admin;

import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 22, 2011 - 12:51:22 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/admin/admin")
public class AdminWidget {

	public AdminWidget() {
	}
	
	@RequestMapping("/info-view")
	public String doInfoView() {
		return "admin/admin-info";
	}
	
}
