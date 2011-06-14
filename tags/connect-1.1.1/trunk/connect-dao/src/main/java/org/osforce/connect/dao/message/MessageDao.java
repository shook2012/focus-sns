package org.osforce.connect.dao.message;

import org.osforce.connect.entity.message.Message;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:21:10 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface MessageDao extends BaseDao<Message> {

	/**
	 * Find message page by ...
	 * @param page
	 * @param projectId
	 * @param box
	 * @return
	 */
	Page<Message> findMessagePage(Page<Message> page, Long projectId, String box);

	/**
	 * Count messages by ...
	 * @param projectId
	 * @param read
	 * @return
	 */
	Long countMessages(Long projectId, Boolean read);

}
