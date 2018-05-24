package mobilepad.io.message.control.mapping;

import mobilepad.app.conf.ConfigurationItem;
import mobilepad.io.protocol.Protocol;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public interface ConfigurationFileHandler
{
	void saveConfiguration(File file, Protocol protocol, Collection<ConfigurationItem> items) throws IOException;
	Collection<ConfigurationItem> loadConfiguration(File file, Protocol protocol) throws IOException;
}
