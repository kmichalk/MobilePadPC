package mobilepad.io.message.control;

/**
 * An event to be paired with an input event in program GUI
 */
public class CustomEvent extends ControlEvent
{
	/**
	 * Id of the event
	 */
	public int id;
	/**
	 * Argument object passed to executing function
	 */
	public Object argument;


	/**
	 * Constructor
	 * @param id the event id
	 */
	public CustomEvent(int id){
		super(Type.CUSTOM);
		this.id= id;
		this.argument = null;
	}


	/**
	 * Constructor
	 * @param id the event id
	 * @param argument Argument object passed to executing function
	 */
	public CustomEvent(int id, Object argument) {
		super(Type.CUSTOM);
		this.id = id;
		this.argument = argument;
	}
}
