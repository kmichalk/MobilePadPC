package mobilepad.app.ui;

import mobilepad.io.message.control.ControlEvent;

public class InputEventElem
{
	private final int id;
	private final String name;
	private ControlEvent controlEvent;


	public InputEventElem(int id, String name) {
		this.id = id;
		this.name = name;
		this.setControlEvent(null);
	}


	public InputEventElem(int id, String name, ControlEvent controlEvent) {
		this.id = id;
		this.name = name;
		this.setControlEvent(controlEvent);
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public ControlEvent getControlEvent() {
		return controlEvent;
	}


	public void setControlEvent(ControlEvent controlEvent) {
		this.controlEvent = controlEvent;
	}
}
