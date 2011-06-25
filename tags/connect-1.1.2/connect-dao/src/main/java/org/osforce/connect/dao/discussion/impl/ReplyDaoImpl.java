package org.osforce.connect.dao.discussion.impl;

import org.osforce.connect.dao.discussion.ReplyDao;
import org.osforce.connect.entity.discussion.Reply;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:12:21 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ReplyDaoImpl extends AbstractDao<Reply> 
	implements ReplyDao {

	public ReplyDaoImpl() {
		super(Reply.class);
	}
	
	static final String JPQL0 = "FROM Reply AS r WHERE r.topic.id = ?1 ORDER BY r.entered";
	public Page<Reply> findReplyPage(Page<Reply> page, Long topicId) {
		return findPage(page, JPQL0, topicId);
	}
}
