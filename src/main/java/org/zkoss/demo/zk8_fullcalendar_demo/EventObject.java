package org.zkoss.demo.zk8_fullcalendar_demo;

import java.util.Date;

public class EventObject {
	
	private String id;
	private String title;
	private boolean allDay;
	private Date start;
	private Date end;
	private String url;
	private String className;
	private boolean editable;
	private boolean startEditable;
	private boolean durationEditable;
	private String rendering;
	private boolean overlap;
	private String constranit;
	private String color;
	private String backgroundColor;
	private String borderColor;
	private String textColor;
	
	public EventObject() {
		// set default configs
		this.overlap = true;
		this.editable = true;
		this.startEditable = true;
		this.durationEditable = true;
	}
	
	public EventObject(String id, String title, Date start) {
		this.id = id;
		this.title = title;
		this.start = start;
		
		// set default configs
		this.overlap = true;
		this.editable = true;
		this.startEditable = true;
		this.durationEditable = true;
	}
	
	// constructor with end
	public EventObject(String id, String title, Date start, Date end) {
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		
		// set default configs
		this.overlap = true;
		this.editable = true;
		this.startEditable = true;
		this.durationEditable = true;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isStartEditable() {
		return startEditable;
	}

	public void setStartEditable(boolean startEditable) {
		this.startEditable = startEditable;
	}

	public boolean isDurationEditable() {
		return durationEditable;
	}

	public void setDurationEditable(boolean durationEditable) {
		this.durationEditable = durationEditable;
	}

	public String getRendering() {
		return rendering;
	}

	public void setRendering(String rendering) {
		this.rendering = rendering;
	}

	public boolean isOverlap() {
		return overlap;
	}

	public void setOverlap(boolean overlap) {
		this.overlap = overlap;
	}

	public String getConstranit() {
		return constranit;
	}

	public void setConstranit(String constranit) {
		this.constranit = constranit;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}
}
