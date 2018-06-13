package mobilepad.app.ui;

import mobilepad.io.message.control.ControlEvent;

/**
 * The control event element on the list in GUI of the application
 */
public class ControlEventItem
{
	private ControlEvent event;
	private String name;
	private String description;


	/**
	 * The default constructor
	 */
	public ControlEventItem(){
		event = null;
		name = "";
		description = "";
	}


	/**
	 * Constructor
	 * @param event the event
	 * @param name name of the ecvent
	 * @param description description of event
	 */
	public ControlEventItem(ControlEvent event, String name, String description) {
		this.event = event;
		this.name = name;
		this.description = description;
	}


	/**
	 * Constructor defaulting the description
	 * @param event the event
	 * @param name name of the event
	 */
	public ControlEventItem(ControlEvent event, String name) {
		this.event = event;
		this.name = name;
		this.description = "";
	}


	/**
	 * returns name of the event to be displayed
	 * @return the name
	 */
	@Override
	public String toString(){
		return name;
	}


	/**
	 * Gets the event
	 * @return the event
	 */
	public final ControlEvent getEvent() {
		return event;
	}


	/**
	 * gets toe event name
	 * @return event name
	 */
	public final String getName() {
		return name;
	}


	/**
	 * Get the description
	 * @return description
	 */
	public final String getDescription() {
		return description;
	}
}
