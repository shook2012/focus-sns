package org.osforce.connect.service.tracker;

import org.osforce.connect.entity.system.User;


/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 27, 2011 - 2:11:32 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface UserTracker extends Tracker {

	User getOnlineUser(Long userId);
	
	Long countOnlineUsers();
	
}
