package mobilepad.io.bluetooth.message;


import java.io.Serializable;

public class Message implements Serializable
{
	public MessageType type;

	public Message(){

	}

	public Message(MessageType type) {
		this.type = type;
	}
}
