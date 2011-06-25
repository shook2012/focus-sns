package org.osforce.connect.service.calendar;

import java.util.Date;
import java.util.List;

import org.osforce.connect.entity.calendar.Event;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:32:57 PM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
public interface EventService {

	Event getEvent(Long eventId);

	void createEvent(Event event);

	void updateEvent(Event event);

	void deleteEvent(Long eventId);

	List<Event> getEventList(Date start, Date end);
	
	List<Event> getEventList(Long projectId, Date start, Date end);
	
	Page<Event> getEventPage(Page<Event> page, List<String> categoryCodes, Date start);

	Page<Event> getEventPage(Page<Event> page, Long projectId, Date start);

	/**
	 * notify event called by @code EventScheduler
	 * @param event
	 * 
	 * @see EventScheduler
	 */
	void notifyEvent(Event event);

}
