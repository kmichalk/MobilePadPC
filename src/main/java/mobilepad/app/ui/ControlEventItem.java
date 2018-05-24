package mobilepad.app.ui;

import mobilepad.io.message.control.ControlEvent;

public class ControlEventItem
{
	private ControlEvent event;
	private String name;
	private String description;


	public ControlEventItem(){
		event = null;
		name = "";
		description = "";
	}

	public ControlEventItem(ControlEvent event, String name, String description) {
		this.event = event;
		this.name = name;
		this.description = description;
	}


	public ControlEventItem(ControlEvent event, String name) {
		this.event = event;
		this.name = name;
		this.description = "";
	}

	@Override
	public String toString(){
		return name;
	}


	public final ControlEvent getEvent() {
		return event;
	}


	public final String getName() {
		return name;
	}


	public final String getDescription() {
		return description;
	}
}
