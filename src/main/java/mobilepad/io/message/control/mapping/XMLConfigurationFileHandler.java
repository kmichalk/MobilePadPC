package mobilepad.io.message.control.mapping;

import mobilepad.app.ApplicationComponent;
import mobilepad.app.conf.ConfigurationItem;
import mobilepad.io.protocol.Protocol;
import mobilepad.io.protocol.exception.ProtocolDecodeException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class XMLConfigurationFileHandler extends ApplicationComponent implements ConfigurationFileHandler
{
	@Override
	public void saveConfiguration(File file, Protocol protocol, Collection<ConfigurationItem> items)
			throws IOException {
		FileOutputStream out = new FileOutputStream(file);
		for (ConfigurationItem i : items)
			protocol.encode(i, out);
	}


	@Override
	public Collection<ConfigurationItem> loadConfiguration(File file, Protocol protocol)
			throws IOException {
		FileInputStream in = new FileInputStream(file);
		ArrayList<ConfigurationItem> items = new ArrayList<>();
		try {
			ConfigurationItem i;
			while ((i = protocol.decode(in)) != null)
				items.add(i);
		}
		catch (ProtocolDecodeException e){
			log(e);
		}
		return items;
	}
}
