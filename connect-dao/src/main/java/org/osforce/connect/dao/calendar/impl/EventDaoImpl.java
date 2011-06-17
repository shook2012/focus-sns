package org.osforce.connect.dao.calendar.impl;

import java.util.Date;
import java.util.List;

import org.osforce.connect.dao.calendar.EventDao;
import org.osforce.connect.entity.calendar.Event;
import org.osforce.spring4me.dao.AbstractDao;
import org.osforce.spring4me.dao.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 11:04:06 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class EventDaoImpl extends AbstractDao<Event> 
		implements EventDao {
	
	public EventDaoImpl() {
		super(Event.class);
	}

	static final String JPQL0 = "FROM Event e %s";
	public List<Event> findEventList(Long projectId, Date start, Date end) {
		Assert.notNull(start, "Prameter start can not be null!");
		if(projectId!=null && end!=null) {
			return findList(String.format(JPQL0, "WHERE e.project.id = ?1 AND e.start >= ?2 AND e.end <=?3"),  projectId, start, end);
		} else if(projectId!=null) {
			return findList(String.format(JPQL0, "WHERE e.project.id = ?1 AND e.start >= ?2"), projectId, start);
		} else {
			return findList(String.format(JPQL0, "WHERE e.start >= ?1 AND e.end <= ?2"), start, end);
		}
	}
	
	static final String JPQL1 = "FROM Event e WHERE e.start >= ?1 %s";
	public Page<Event> findEventPage(Page<Event> page, Long projectId, Date start) {
		Assert.notNull(start, "Prameter start can not be null!");
		if(projectId==null) {
			return findPage(page, String.format(JPQL1, ""), start);
		} else {
			return findPage(page, String.format(JPQL1, "AND e.project.id = ?2"), start, projectId);
		}
	}
	
	static final String JPQL2 = "FROM Event e WHERE e.start >= ?1 AND e.project.category.code IN (?2)";
	public Page<Event> findEventPage(Page<Event> page, List<String> categoryCodes, Date start) {
		Assert.notNull(start, "Prameter start can not be null!");
		return findPage(page, JPQL2, start, categoryCodes);
	}
}
