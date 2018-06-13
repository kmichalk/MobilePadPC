package mobilepad.app.ui;

import mobilepad.io.message.control.ControlEvent;

/**
 * Custom input event on the list in GUI of application to be paired with specific action
 */
public class InputEventElem
{
	private final int id;
	private final String name;
	private ControlEvent controlEvent;


	/**
	 * Constructor
	 * @param id the id on the list
	 * @param name the name of event
	 */
	public InputEventElem(int id, String name) {
		this.id = id;
		this.name = name;
		this.setControlEvent(null);
	}


	/**
	 * Constructor
	 * @param id the id on the list
	 * @param name the name of event
	 * @param controlEvent the paired contriol event
	 */
	public InputEventElem(int id, String name, ControlEvent controlEvent) {
		this.id = id;
		this.name = name;
		this.setControlEvent(controlEvent);
	}


	/**
	 * gets the id
	 * @return id
	 */
	public int getId() {
		return id;
	}


	/**
	 * Gets the name
	 * @return name
	 */
	public String getName() {
		return name;
	}


	/**
	 * gets the paired control event
	 * @return control event
	 */
	public ControlEvent getControlEvent() {
		return controlEvent;
	}


	/**
	 * Sets the control event as paired with this input event
	 * @param controlEvent the paired control event
	 */
	public void setControlEvent(ControlEvent controlEvent) {
		this.controlEvent = controlEvent;
	}
}
