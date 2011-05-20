package org.osforce.connect.service.message.impl;

import java.util.Date;

import org.osforce.connect.dao.message.MessageDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.message.Message;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.message.MessageService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:21:41 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	private UserDao userDao;
	private ProjectDao projectDao;
	private MessageDao messageDao;

	public MessageServiceImpl() {
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Autowired
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public Message getMessage(Long messageId) {
		return messageDao.get(messageId);
	}

	public void createMessage(Message message) {
		updateMessage(message);
	}

	public void updateMessage(Message message) {
		if(message.getEnteredId()!=null) {
			User enteredBy = userDao.get(message.getEnteredId());
			message.setEnteredBy(enteredBy);
		}
		if(message.getFromId()!=null) {
			Project from = projectDao.get(message.getFromId());
			message.setFrom(from);
		}
		if(message.getToId()!=null) {
			Project to = projectDao.get(message.getToId());
			message.setTo(to);
		}
		Date now = new Date();
		if(message.getId()==null) {
			message.setEntered(now);
			messageDao.save(message);
		} else {
			messageDao.update(message);
		}
	}

	public void deleteMessage(Long messageId) {
		messageDao.delete(messageId);
	}

	public Page<Message> getMessagePage(Page<Message> page, Long projectId, String box) {
		return messageDao.findMessagePage(page, projectId, box);
	}

	public Long countMessages(Long projectId, Boolean read) {
		return messageDao.countMessages(projectId, read);
	}

}
