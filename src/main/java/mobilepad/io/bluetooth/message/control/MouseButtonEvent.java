package mobilepad.io.bluetooth.message.control;

import mobilepad.app.Application;
import mobilepad.app.control.ApplicationController;

public class MouseButtonEvent extends KeyEvent
{
	public MouseButtonEvent() {
	}


	public MouseButtonEvent(int button) {
		super(button, true, true);
	}


	@Override
	public void apply() {
		ApplicationController controller = Application.getInstance().getController();
		if (press)
			controller.mousePress(key);
		if (release)
			controller.mouseRelease(key);
	}
}
