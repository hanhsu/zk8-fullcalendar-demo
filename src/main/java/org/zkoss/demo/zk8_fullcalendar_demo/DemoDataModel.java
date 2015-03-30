package org.zkoss.demo.zk8_fullcalendar_demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class DemoDataModel implements EventsDataModel {
	private Map<String, EventObject> _evtMap;
	private SimpleDateFormat  dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	
	public DemoDataModel() {
		dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		_evtMap = new HashMap<String, EventObject>();
		EventObject evt1 = new EventObject("evt1", "test1", parseDate("2015-03-15 14:00"));
		EventObject evt2 = new EventObject("evt2", "test2", parseDate("2015-03-17 09:30"));
		evt2.setEnd(parseDate("2015-03-20 00:00"));
		_evtMap.put(evt1.getId(), evt1);
		_evtMap.put(evt2.getId(), evt2);
	}
	
	public Collection<EventObject> getEvents() {
		return _evtMap.values();
	}
	
	public int getEventCount() {
		return _evtMap.size();
	}
	
	public EventObject getEventById(String id) {
		return _evtMap.get(id);
	}
	
	public void updateEvent(EventObject event) {
		_evtMap.put(event.getId(), event);
	}
	
	public void createEvent(EventObject event) {
		event.setId("evt" + (getEventCount() + 1));
		_evtMap.put(event.getId(), event);
	}
	
	private Date parseDate(String source) {
		Date result = null;
		try {
			result = dateFormatter.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
