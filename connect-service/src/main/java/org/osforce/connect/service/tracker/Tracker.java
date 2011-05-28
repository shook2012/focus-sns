package org.osforce.connect.service.tracker;



/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 0.1.0
 * @create May 27, 2011 - 2:09:21 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface Tracker {

	void track(String targetName, Object targetValue);
	
	void untrack(String targetName, Object targetValue);
	
}
