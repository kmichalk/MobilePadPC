package mobilepad.app.conf;

import java.util.Collection;

/**
 * Interface for any object to be configured from file
 */
public interface ConfigurationComponent
{
	/**
	 * Returnd current configuration
	 * @return
	 */
	Collection<ConfigurationItem> getConfiguration();
}
