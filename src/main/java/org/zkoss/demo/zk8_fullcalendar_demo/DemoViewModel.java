package org.zkoss.demo.zk8_fullcalendar_demo;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.Date;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ClientCommand;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.NotifyCommand;
import org.zkoss.zul.Popup;

@NotifyCommand(value="doEventsChange", onChange="vm.events")
@ClientCommand({"doEventClicked", "doDayClicked", "doEventsChange"})
public class DemoViewModel {
	
	private EventsDataModel dataModel;
	private Collection<EventObject> events;
	private EventObject tempEvent;
	
	
	@Init
	public void init() throws GeneralSecurityException, IOException {
		// init event data model
		dataModel = new DemoDataModel();
//		dataModel = new GoogleCalendarDataModel();
		events = dataModel.getEvents();
	}
	
	@Command
	@NotifyChange("tempEvent")
	public void doDayClicked(@BindingParam("dateClicked") long dateClicked) {
		tempEvent = new EventObject();
		tempEvent.setStart(new Date(dateClicked));
	}
	
	@Command
	@NotifyChange("tempEvent")
	public void doEventClicked(@BindingParam("evtId") String evtId) {
		tempEvent = dataModel.getEventById(evtId);
	}
	
	@Command
	@NotifyChange("events")
	public void doEventChanged(@BindingParam("evtId") String evtId, 
			@BindingParam("startTime") long startTime, @BindingParam("endTime") long endTime) {
		EventObject target = dataModel.getEventById(evtId);
		
		if (startTime != 0) {
			target.setStart(new Date(startTime));
		}
		
		if (endTime != 0) {
			target.setEnd(new Date(endTime));
		}
		
		dataModel.updateEvent(target);
		
		events = dataModel.getEvents();
	}
		
	@Command
	@NotifyChange("events")
	public void modEvent(@BindingParam("pop") Popup pop) {
		dataModel.updateEvent(tempEvent);
		events = dataModel.getEvents();
		tempEvent = new EventObject();
		pop.close();
	}
	
	@Command
	@NotifyChange("events")
	public void createEvent(@BindingParam("pop") Popup pop) {
		dataModel.createEvent(tempEvent);
		events = dataModel.getEvents();
		pop.close();
	}
	
	@Command
	@NotifyChange("tempEvent")
	public void clearForm() {
		tempEvent.setTitle("");
		tempEvent.setStart(null);
		tempEvent.setEnd(null);
	}
		
	public Collection<EventObject> getEvents() {
		return events;
	}
		
	public EventObject getTempEvent() {
		return tempEvent;
	}
}
