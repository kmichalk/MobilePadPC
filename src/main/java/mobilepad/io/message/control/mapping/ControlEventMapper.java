package mobilepad.io.message.control.mapping;

import mobilepad.app.conf.ConfigurationComponent;
import mobilepad.app.conf.ConfigurationItem;
import mobilepad.app.control.ApplicationController;
import mobilepad.io.message.control.ControlEvent;

public interface ControlEventMapper extends ConfigurationComponent
{
	public static class Mapping implements ConfigurationItem
	{
		public int id;
		public ControlEvent event;

		public Mapping(int id, ControlEvent event) {
			this.id = id;
			this.event = event;
		}


		@Override
		public void apply(ApplicationController controller) {
			controller.getEventMapper().map(event, id, true);
		}
	}

	boolean map(Mapping mapping);
	boolean map(ControlEvent event, int id, boolean override);
	ControlEvent get(int id);
}
