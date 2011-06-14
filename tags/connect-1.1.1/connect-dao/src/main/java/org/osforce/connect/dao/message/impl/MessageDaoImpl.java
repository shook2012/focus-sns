package org.osforce.connect.dao.message.impl;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.dao.message.MessageDao;
import org.osforce.connect.entity.message.Message;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:23:34 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class MessageDaoImpl extends AbstractDao<Message>
	implements MessageDao {

	public MessageDaoImpl() {
		super(Message.class);
	}

	static final String JPQL0 = "FROM Message AS m %s ORDER BY m.entered DESC";
	public Page<Message> findMessagePage(Page<Message> page, Long projectId, String box) {
		if(StringUtils.equals(box, Message.BOX_IN)) {
			return findPage(page, String.format(JPQL0, "WHERE m.to.id = ?1"), projectId);
		} else if (StringUtils.equals(box, Message.BOX_SENT)) {
			return findPage(page, String.format(JPQL0, "WHERE m.from.id = ?1"), projectId);
		}
		return null;
	}
	
	static final String JPQL1 = "SELECT COUNT(*) FROM Message AS m WHERE m.to.id = ?1 AND m.read = ?2";
	public Long countMessages(Long projectId, Boolean read) {
		return count(JPQL1, projectId, read);
	}
}
