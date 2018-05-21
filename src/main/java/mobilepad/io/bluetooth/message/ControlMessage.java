package mobilepad.io.bluetooth.message;

public abstract class ControlMessage extends Message
{
	public ControlMessage(){
		super(MessageType.CONTROL);
	}

	public abstract void apply();
}
