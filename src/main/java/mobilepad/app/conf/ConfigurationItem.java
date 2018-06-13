package mobilepad.app.conf;

import mobilepad.app.control.ApplicationController;

/**
 * A configuration to be applied on application via AplicationController
 */
public interface ConfigurationItem
{
	/**
	 * Apply the configuration
	 * @param controller
	 */
	void apply(ApplicationController controller);
}
