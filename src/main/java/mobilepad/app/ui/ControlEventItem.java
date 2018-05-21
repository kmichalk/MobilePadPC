package mobilepad.app.ui;

import mobilepad.io.bluetooth.message.control.ControlEvent;

public class ControlEventItem
{
	public ControlEvent event;
	public String name;
	public String description;


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
}
