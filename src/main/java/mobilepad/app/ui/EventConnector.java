package mobilepad.app.ui;

import com.sun.istack.internal.NotNull;
import mobilepad.app.ApplicationComponent;
import mobilepad.io.message.control.ControlEvent;

class EventConnector extends ApplicationComponent
{
	private InputEventElem lastSelectedInputEventElem = null;

	void inputEventElemSelected(InputEventElem e){
		lastSelectedInputEventElem = e;
	}

	void controlEventElemSelected(@NotNull ControlEventItem e) throws NullPointerException, IllegalStateException {
		ControlEvent event = e.getEvent();
		if (event == null)
			throw new NullPointerException("ControlEventElem contained null ControlEvent.");
		if (lastSelectedInputEventElem == null)
			throw new IllegalStateException("No InputEventElem was selected.");
		parentApplication.getController().getEventMapper().map(event, lastSelectedInputEventElem.getId(), true);
		lastSelectedInputEventElem.setControlEvent(e.getEvent());
		lastSelectedInputEventElem = null;
	}
}
