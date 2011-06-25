package org.osforce.connect.dao.calendar;

import java.util.Date;
import java.util.List;

import org.osforce.connect.entity.calendar.Event;
import org.osforce.spring4me.dao.BaseDao;
import org.osforce.spring4me.dao.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 11:03:31 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface EventDao extends BaseDao<Event> {

	/**
	 * Find events by ...
	 * @param projectId
	 * @param start  Note:  start can not be null
	 * @param end
	 * @return
	 */
	List<Event> findEventList(Long projectId, Date start, Date end);

	/**
	 * Find events by page
	 * @param page
	 * @param projectId
	 * @param start
	 * @return
	 */
	Page<Event> findEventPage(Page<Event> page, Long projectId, Date start);

	Page<Event> findEventPage(Page<Event> page, List<String> categoryCodes, Date start);

}
