package org.zkoss.demo.zk8_fullcalendar_demo;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.zkoss.zk.ui.Executions;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

public class GoogleCalendarDataModel implements EventsDataModel {
	private Calendar gcalService;
	private List<Event> gevents; 
	
	public GoogleCalendarDataModel() throws GeneralSecurityException, IOException {
		ServletContext ctx = Executions.getCurrent().getSession().getWebApp().getServletContext();
		GoogleCredential credentials = new GoogleCredential.Builder().setTransport(GoogleNetHttpTransport.newTrustedTransport())
			.setJsonFactory(new GsonFactory())
			.setServiceAccountId("472149778389-kb7j3v8rao1pbmju3l9r93nnr5spu0fb@developer.gserviceaccount.com")
			.setServiceAccountScopes(Arrays.asList("https://www.googleapis.com/auth/calendar"))
			.setServiceAccountPrivateKeyFromP12File(new File(ctx.getRealPath("/resources/Calendar Demo-c84f75e0662a.p12")))
			.build();
		
		gcalService = new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), new GsonFactory(), credentials).build();
	}
	
	public Collection<EventObject> getEvents() {
		gevents = new ArrayList<Event>();
		List<EventObject> result = new ArrayList<EventObject>();
		String pageToken = null;
		
		do {
		  Events events = null;
		try {
			events = gcalService.events().list("hanhsu.potix@gmail.com").setPageToken(pageToken).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  List<Event> items = events.getItems();
		  gevents.addAll(items);
		  pageToken = events.getNextPageToken();
		} while (pageToken != null);
		
		for (Event each : gevents) {
			long startTime = each.getStart().getDateTime().getValue();
			long endTime = each.getEnd().getDateTime().getValue();
			String title = each.getSummary();
			String id =each.getId();
			result.add(new EventObject(id, title, new Date(startTime), new Date(endTime)));
		}
		
		return result;
	}

	public EventObject getEventById(String id) {
		Event event = null;
		try {
			event = gcalService.events().get("hanhsu.potix@gmail.com", id).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		EventObject result = null;
		if (event != null) {
			long startTime = event.getStart().getDateTime().getValue();
			long endTime = event.getEnd().getDateTime().getValue();
			String title = event.getSummary();
			result = new EventObject(id, title, new Date(startTime), new Date(endTime));
		}
		
		return result;
	}

	public int getEventCount() {
		return getEvents().size();
	}

	public void updateEvent(EventObject event) {
		String id = event.getId();
		Event gevent = null;
		try {
			gevent = gcalService.events().get("hanhsu.potix@gmail.com", id).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (event.getStart() != null) {
			gevent.setStart(new EventDateTime().setDateTime(new DateTime(event.getStart())));
		}
		
		if (event.getEnd() != null) {
			gevent.setEnd(new EventDateTime().setDateTime(new DateTime(event.getEnd())));
		}
		
		gevent.setSummary(event.getTitle());
		
		try {
			Event updatedEvent = gcalService.events().update("hanhsu.potix@gmail.com", id, gevent).execute();
//			System.out.println(updatedEvent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createEvent(EventObject event) {
		Event gevent = new Event();
		gevent.setSummary(event.getTitle());
		
		if (event.getStart() != null) {
			gevent.setStart(new EventDateTime().setDateTime(new DateTime(event.getStart())));
		}
		
		if (event.getEnd() != null) {
			gevent.setEnd(new EventDateTime().setDateTime(new DateTime(event.getEnd())));
		}
		
		try {
			Event created = gcalService.events().insert("hanhsu.potix@gmail.com", gevent).execute();
//			System.out.println("!!created: " + created);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
