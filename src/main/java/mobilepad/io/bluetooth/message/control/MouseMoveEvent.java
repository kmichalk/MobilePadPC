package mobilepad.io.bluetooth.message.control;

import mobilepad.app.Application;

public class MouseMoveEvent implements ControlEvent
{
	public int x;
	public int y;


	public MouseMoveEvent(){

	}

	public MouseMoveEvent(int x, int y) {
		this.x = x;
		this.y = y;
	}


	@Override
	public void apply() {
		Application.getInstance().getController().mouseMove(x,y);
	}
}
