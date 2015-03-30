package org.zkoss.demo.zk8_fullcalendar_demo;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

public class GoogleClientTeste {

	public static void main(String[] args) throws GeneralSecurityException, IOException {
		GoogleCredential credentials = new GoogleCredential.Builder().setTransport(GoogleNetHttpTransport.newTrustedTransport())
				  .setJsonFactory(new GsonFactory())
				  .setServiceAccountId("472149778389-kb7j3v8rao1pbmju3l9r93nnr5spu0fb@developer.gserviceaccount.com")
				  .setServiceAccountScopes(Arrays.asList("https://www.googleapis.com/auth/calendar.readonly"))
				  .setServiceAccountPrivateKeyFromP12File(new File("C:\\home\\Han\\workspace_task\\zk8-fullcalendar-demo\\src\\main\\webapp\\resources\\Calendar Demo-c84f75e0662a.p12"))
				.build();
				Calendar client = new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), new GsonFactory(), credentials).build();
				
				String pageToken = null;
				do {
				  Events events = client.events().list("hanhsu.potix@gmail.com").setPageToken(pageToken).execute();
				  List<Event> items = events.getItems();
				  for (Event event : items) {
				    System.out.println(event.getSummary());
				  }
				  pageToken = events.getNextPageToken();
				} while (pageToken != null);
	}
}
