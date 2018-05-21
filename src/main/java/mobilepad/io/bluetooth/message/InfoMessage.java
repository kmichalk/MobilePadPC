package mobilepad.io.bluetooth.message;

public class InfoMessage extends Message
{
	public String text;


	public InfoMessage(String text) {
		super(MessageType.INFO);
		this.text = text;
	}


	public InfoMessage() {
		super(MessageType.INFO);
		this.text = "";
	}
}
