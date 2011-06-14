package org.osforce.connect.web.module.calendar;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.osforce.connect.entity.calendar.Event;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.calendar.EventService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.security.annotation.Permission;
import org.osforce.spring4me.dao.Page;
import org.osforce.spring4me.web.bind.annotation.RequestAttr;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 21, 2011 - 9:37:21 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/calendar/event")
public class EventWidget {

	private EventService eventService;
	
	public EventWidget() {
	}
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@RequestMapping("/recent-view")
	@Permission({"event-view"})
	public String doRecentView(Page<Event> page, 
			@RequestAttr Project project, Model model) {
		page = eventService.getEventPage(page, project.getId(), new Date());
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		model.addAttribute(AttributeKeys.PAGE_KEY_READABLE, page);
		return "calendar/event-recent";
	}
	
	@RequestMapping("/list-view")
	@Permission({"event-view"})
	public String doListView(@RequestParam(required=false) String date, 
			 @RequestAttr Project project, Model model) throws ParseException {
		Date d = new Date();
		Date start = new Date();
		Date end = null;
		if(StringUtils.contains(date, "~")) {
			String startStr = StringUtils.substringBefore(date, "~");
			String endStr = StringUtils.substringAfter(date, "~");
			start = DateUtils.parseDate(startStr, new String[]{"yyyy/M/d"});
			end = DateUtils.parseDate(endStr, new String[]{"yyyy/M/d"});
		} else if(StringUtils.isNotBlank(date)) {
			d = DateUtils.parseDate(date, new String[]{"yyyy/M/d"});
			start = DateUtils.ceiling(DateUtils.addDays(d, -1), Calendar.DAY_OF_MONTH);
			end = DateUtils.ceiling(d, Calendar.DAY_OF_MONTH);
		} 
		//
		List<Event> events = eventService.getEventList(project.getId(), start, end);
		model.addAttribute(AttributeKeys.EVENT_LIST_KEY_READABLE, events);
		//
		model.addAttribute("date", d);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		return "calendar/event-list";
	}
	
	@RequestMapping("/detail-view")
	@Permission({"event-view"})
	public String doDetailView(@RequestParam Long eventId, Model model) {
		Event event = eventService.getEvent(eventId);
		model.addAttribute(AttributeKeys.EVENT_KEY_READABLE, event);
		return "calendar/event-detail";
	}
	
	@RequestMapping("/form-view")
	@Permission(value={"event-detail-add", "event-detail-edit"}, userRequired=true, projectRequired=true)
	public String doFormView(Model model,
			@RequestParam(required=false) Long eventId, 
			@RequestParam(required=false) String date, 
			@ModelAttribute @Valid Event event, BindingResult result,
			@RequestAttr Project project, @RequestAttr User user, Boolean showErrors) throws ParseException {
		if(!showErrors) {
			event.setEnteredBy(user);
			event.setModifiedBy(user);
			event.setProject(project);
			if(StringUtils.isNotBlank(date)) {
				Date start = DateUtils.parseDate(date+" 9:00", new String[]{"yyyy-MM-dd HH:mm"});
				Date end = DateUtils.parseDate(date+" 17:00", new String[]{"yyyy-MM-dd HH:mm"});
				event.setStart(start);
				event.setEnd(end);
			}
			if(eventId!=null) {
				event = eventService.getEvent(eventId);
			}
			model.addAttribute(AttributeKeys.EVENT_KEY_READABLE, event);
		}
		return "calendar/event-form";
	}
	
	@RequestMapping(value="/form-action", method=RequestMethod.POST)
	@Permission(value={"event-add", "event-edit"}, userRequired=true)
	public String doFormAction(@ModelAttribute @Valid Event event, 
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_CALENDAR);
			return "page:/calendar/event-form";
		}
		eventService.createEvent(event);
		return String.format("redirect:/%s/calendar", event.getProject().getUniqueId());
	}
	
}
