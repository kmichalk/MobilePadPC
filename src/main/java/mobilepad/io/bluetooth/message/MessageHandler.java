package mobilepad.io.bluetooth.message;

import mobilepad.app.ApplicationComponent;

public class MessageHandler extends ApplicationComponent
{
	public void handle(Message message){
		try {
			switch (message.type) {
				case UNKNOWN:
					log("Received message of unknown type.");
					break;
				case INFO:
					log(((InfoMessage)message).text);
					break;
				case ACTION:
					break;
			}
		}
		catch (ClassCastException e){
			log(e);
		}
	}
}
