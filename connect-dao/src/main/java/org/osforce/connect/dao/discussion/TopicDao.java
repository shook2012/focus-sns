package org.osforce.connect.dao.discussion;

import java.util.List;

import org.osforce.connect.entity.discussion.Topic;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:10:14 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface TopicDao extends BaseDao<Topic> {

	/**
	 * Find topic page by... NOTE: project id and forum id can not both null
	 * @param page
	 * @param projectId
	 * @param forumId
	 * @return
	 */
	Page<Topic> findTopicPage(Page<Topic> page, Long projectId, Long forumId);

	Page<Topic> findTopicPage(Page<Topic> page, List<String> categoryCodes);
}
