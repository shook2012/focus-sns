package org.osforce.connect.service.calendar.impl;

import java.util.Date;
import java.util.List;

import org.osforce.connect.dao.calendar.EventDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.calendar.Event;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.calendar.EventService;
import org.osforce.spring4me.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:33:15 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {

	private UserDao userDao;
	private EventDao eventDao;
	private ProjectDao projectDao;
	
	public EventServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public Event getEvent(Long eventId) {
		return eventDao.get(eventId);
	}

	public void createEvent(Event event) {
		updateEvent(event);
	}

	public void updateEvent(Event event) {
		if(event.getEnteredId()!=null) {
			User enteredBy = userDao.get(event.getEnteredId());
			event.setEnteredBy(enteredBy);
		}
		if(event.getModifiedId()!=null) {
			User modifiedBy = userDao.get(event.getModifiedId());
			event.setModifiedBy(modifiedBy);
		} 
		if(event.getProjectId()!=null) {
			Project project = projectDao.get(event.getProjectId());
			event.setProject(project);
		}
		Date now = new Date();
		event.setModified(now);
		if(event.getId()==null) {
			event.setEntered(now);
			eventDao.save(event);
		} else {
			eventDao.update(event);
		}
	}

	public void deleteEvent(Long eventId) {
		eventDao.delete(eventId);
	}
	
	public List<Event> getEventList(Long projectId, Date start, Date end) {
		return eventDao.findEventList(projectId, start, end);
	}
	
	public Page<Event> getEventPage(Page<Event> page, Long projectId, Date start) {
		return eventDao.findEventPage(page, projectId, start);
	}

	public void notifyEvent(Event event) {}
	
}
