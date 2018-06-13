package mobilepad.io.message.control;

import mobilepad.io.message.Message;

/**
 * A type of message containing data to control the system. Base class for control events
 */
public abstract class ControlEvent extends Message
{
	/**
	 * Constructor setting type of event to be used by derived classes
	 * @param type the type of event
	 */
	public ControlEvent(Type type) {
		super(type);
	}
}
