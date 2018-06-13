package mobilepad.io.message.control.mapping;

import mobilepad.app.conf.ConfigurationComponent;
import mobilepad.app.conf.ConfigurationItem;
import mobilepad.app.control.ApplicationController;
import mobilepad.io.message.control.ControlEvent;

/**
 * An interface for mapper associating custom events with actions
 */
public interface ControlEventMapper extends ConfigurationComponent
{
	/**
	 * Data class with single mapping parameters
	 */
	public static class Mapping implements ConfigurationItem
	{
		/**
		 * The input event id
		 */
		public int id;
		/**
		 * The associated control event
		 */
		public ControlEvent event;


		/**
		 * Constructor
		 * @param id the intpu event id
		 * @param event the control event
		 */
		public Mapping(int id, ControlEvent event) {
			this.id = id;
			this.event = event;
		}


		/**
		 * Applies the mapped event using specified application controller
		 * @param controller the application controller
		 */
		@Override
		public void apply(ApplicationController controller) {
			controller.getEventMapper().map(event, id, true);
		}
	}

	/**
	 * Makes the mapping
	 * @param mapping the event mapping
	 * @return true if the mapping was successfull and not repeated
	 */
	boolean map(Mapping mapping);

	/**
	 * Makes the mapping
	 * @param event the control event
	 * @param id the input event id
	 * @param override if true allows to override an existing mapping
	 * @return true if the mapping was successfull and not repeated
	 */
	boolean map(ControlEvent event, int id, boolean override);

	/**
	 * Gets mapped event by id
	 * @param id the input event id
	 * @return the control event
	 */
	ControlEvent get(int id);
}
