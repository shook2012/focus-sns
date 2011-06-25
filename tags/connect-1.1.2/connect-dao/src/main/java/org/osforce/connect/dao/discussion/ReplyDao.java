package org.osforce.connect.dao.discussion;

import org.osforce.connect.entity.discussion.Reply;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:09:47 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ReplyDao extends BaseDao<Reply> {

	/**
	 * Find reply page by...
	 * @param page
	 * @param topicId
	 * @return
	 */
	Page<Reply> findReplyPage(Page<Reply> page, Long topicId);

}
