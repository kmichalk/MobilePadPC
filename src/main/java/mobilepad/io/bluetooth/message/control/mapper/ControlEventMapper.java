package mobilepad.io.bluetooth.message.control.mapper;

import mobilepad.io.bluetooth.message.control.ControlEvent;

public interface ControlEventMapper
{
	//int map(ControlEvent event);
	boolean map(ControlEvent event, int id, boolean override);
	ControlEvent get(int id);
}
