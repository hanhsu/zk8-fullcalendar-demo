package org.zkoss.demo.zk8_fullcalendar_demo;

import java.util.Collection;

public interface EventsDataModel {
	
	public Collection<EventObject> getEvents();
	
	public EventObject getEventById(String id);
	
	public int getEventCount();
	
	void updateEvent(EventObject event);
	
	void createEvent(EventObject event);
}
