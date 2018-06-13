package mobilepad.io.message;

/**
 * Message used to print some debug information in PC application
 */
public class InfoMessage extends Message
{
	/**
	 * The message
	 */
	public String text;


	/**
	 * constructor
	 * @param text the message
	 */
	public InfoMessage(String text) {
		super(Type.INFO);
		this.text = text;
	}


	/**
	 * Default constructor. Sets message empty.
	 */
	public InfoMessage() {
		super(Type.INFO);
		this.text = "";
	}
}
