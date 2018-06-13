package mobilepad.io.message.control.mapping;

import mobilepad.app.conf.ConfigurationItem;
import mobilepad.io.protocol.Protocol;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Class that reads and writes configuration
 */
public interface ConfigurationFileHandler
{
	/**
	 * Save the configuration
	 * @param file the output file
	 * @param protocol used protocol
	 * @param items the configuration
	 * @throws IOException if an error with file occurs
	 */
	void saveConfiguration(File file, Protocol protocol, Collection<ConfigurationItem> items) throws IOException;

	/**
	 * Load the configuration
	 * @param file the input file
	 * @param protocol the used protocol
	 * @return the loaded configuration
	 * @throws IOException if error with file occurs
	 */
	Collection<ConfigurationItem> loadConfiguration(File file, Protocol protocol) throws IOException;
}
