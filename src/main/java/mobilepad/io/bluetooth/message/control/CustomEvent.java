package mobilepad.io.bluetooth.message.control;

import mobilepad.app.Application;

public class CustomEvent implements ControlEvent
{
	public int id;
	public Object argument;


	public CustomEvent(int id){
		this.id= id;
		this.argument = null;
	}

	public CustomEvent(int id, Object argument) {
		this.id = id;
		this.argument = argument;
	}


	@Override
	public void apply() {
		Application.getInstance().getController().applyCustomEvent(this);
	}
}
