package mobilepad.io.message.control;

/**
 * An event disconnecting currently paired device
 */
public class DisconnectEvent extends ControlEvent
{
	/**
	 * Default constructor
	 */
	public DisconnectEvent(){
		super(Type.DISCONNECT);
	}
}
