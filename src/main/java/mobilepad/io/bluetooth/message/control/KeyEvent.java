package mobilepad.io.bluetooth.message.control;

import mobilepad.app.Application;
import mobilepad.app.control.ApplicationController;

public class KeyEvent implements ControlEvent
{
	public int key;
	public boolean press;
	public boolean release;

	public KeyEvent() {
	}


	public KeyEvent(int key) {
		this.key = key;
		press = true;
		release = true;
	}


	public KeyEvent(int key, boolean press, boolean release) {
		this.key = key;
		this.press = press;
		this.release = release;
	}


	@Override
	public void apply() {
		ApplicationController controller = Application.getInstance().getController();
		if (press)
			controller.keyPress(key);
		if (release)
			controller.keyRelease(key);
	}
}
