package mobilepad.io.protocol;

import mobilepad.io.protocol.exception.ProtocolDecodeException;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;

public class XMLProtocol implements Protocol
{
	@Override
	public <T> T decode(InputStream in) throws ProtocolDecodeException {
		XMLDecoder decoder = new XMLDecoder(in);
		try{
			return (T)decoder.readObject();
		}
		catch (ClassCastException e){
			throw new ProtocolDecodeException("Received object could not be decoded as expected.");
		}
		catch (ArrayIndexOutOfBoundsException e){
			return null;
		}
	}


	@Override
	public <T> void encode(T o, OutputStream out) {
		XMLEncoder encoder = new XMLEncoder(out);
		encoder.writeObject(o);
		encoder.flush();
	}
}
