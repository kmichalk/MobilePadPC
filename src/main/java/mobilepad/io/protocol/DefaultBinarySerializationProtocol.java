package mobilepad.io.protocol;

import mobilepad.io.protocol.exception.ProtocolDecodeException;

import java.io.*;

/**
 * Serialization protocol using the default built-in java object streams as serialization method
 */
public class DefaultBinarySerializationProtocol implements Protocol
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T decode(InputStream in) throws IOException {
		try {
			return (T) new ObjectInputStream(in).readObject();
		}
		catch (ClassNotFoundException e) {
			throw new ProtocolDecodeException("Received object could not be decoded as expected type; " + e.getMessage());
		}
		catch (ClassCastException e){
			throw new ProtocolDecodeException("Could not decode data because of invalid class name; " + e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> void encode(T o, OutputStream out) throws IOException {
		new ObjectOutputStream(out).writeObject(o);
	}
}
