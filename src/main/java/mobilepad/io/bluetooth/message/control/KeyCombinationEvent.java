package mobilepad.io.bluetooth.message.control;

import mobilepad.app.Application;
import mobilepad.app.control.ApplicationController;

public class KeyCombinationEvent implements ControlEvent
{
	public int[] keys;


	public KeyCombinationEvent() {
		keys = null;
	}


	public KeyCombinationEvent(int... keys) {
		this.keys = keys;
	}


	@Override
	public void apply() {
		ApplicationController controller = Application.getInstance().getController();
		for (int k : keys)
			controller.keyPress(k);
		for (int k : keys)
			controller.keyRelease(k);
	}
}
