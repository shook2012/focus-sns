package org.osforce.connect.service.tracker.impl;

import java.util.HashSet;
import java.util.Set;

import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.system.UserService;
import org.osforce.connect.service.tracker.UserTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is simple user track
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 27, 2011 - 2:12:33 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
public class DefaultUserTracker implements UserTracker {
	private Logger logger = LoggerFactory.getLogger(UserTracker.class);
	private Set<Object> cache = new HashSet<Object>();
	
	private UserService userService;
	
	public DefaultUserTracker() {
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void track(String targetName, Object targetValue) {
		cache.add(targetValue);
		logger.debug("track {} --- {}", targetName, targetValue);
	}
	
	public void untrack(String targetName, Object targetValue) {
		logger.debug("untrack {} --- {}", targetName, targetValue);
		cache.remove(targetValue);
	}
	
	public Long countOnlineUsers() {
		return new Long(cache.size());
	}
	
	public User getOnlineUser(Long userId) {
		if(cache.contains(userId)) {
			return userService.getUser(userId);
		}
		return null;
	}
	
}
