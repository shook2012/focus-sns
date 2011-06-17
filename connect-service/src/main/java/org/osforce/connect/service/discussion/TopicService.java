package org.osforce.connect.service.discussion;

import java.util.List;

import org.osforce.connect.entity.discussion.Topic;
import org.osforce.connect.entity.system.Project;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:50:55 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface TopicService {

	Topic viewTopic(Long topicId);
	
	Topic getTopic(Long topicId);
	
	void createTopic(Topic topic);
	
	void updateTopic(Topic topic);
	
	void deleteTopic(Long topicId);
	
	/**
	 * 
	 * @param page
	 * @param project
	 * @return
	 */
	Page<Topic> getTopicPage(Page<Topic> page, Project project);

	/**
	 * Find topic page by...
	 * @param page
	 * @param projectId Note: may be null
	 * @param forumId Note: may be null
	 * @return
	 */
	Page<Topic> getTopicPage(Page<Topic> page, Long forumId);

	Page<Topic> getTopicPage(Page<Topic> page, List<String> categoryCodes);
}

