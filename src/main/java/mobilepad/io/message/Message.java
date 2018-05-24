package mobilepad.io.message;


import java.io.Serializable;

public abstract class Message implements Serializable
{
	public enum Type{
		COMMAND, CUSTOM, DISCONNECT, KEY_COMBINATION, KEY, MOUSE_BUTTON, MOUSE_MOVE, INFO
	}


	public Type type;


	public Message(Type type) {
		this.type = type;
	}
}
