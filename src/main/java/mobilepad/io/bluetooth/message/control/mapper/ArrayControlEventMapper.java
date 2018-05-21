package mobilepad.io.bluetooth.message.control.mapper;

import mobilepad.io.bluetooth.message.control.ControlEvent;

public class ArrayControlEventMapper implements ControlEventMapper
{
	private static final int MAX_EVENTS = 64;
	private ControlEvent[] data;

	public ArrayControlEventMapper(){
		data = new ControlEvent[MAX_EVENTS];
	}

//	@Override
//	public int map(ControlEvent event){
//		for (int i=0;i<data.length;++i) {
//			if (data[i] == null) {
//				data[i] = event;
//
//			}
//		}
//	}


	@Override
	public boolean map(ControlEvent event, int id, boolean override) {
		if (id < data.length && (override || data[id] == null)) {
			data[id] = event;
			return true;
		}
		else
			return false;
	}


	@Override
	public ControlEvent get(int id){
		if (id < data.length)
			return data[id];
		else
			return null;
	}
}
