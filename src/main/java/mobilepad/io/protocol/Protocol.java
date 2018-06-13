package mobilepad.io.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Class detaching the serialization process from implementation allowing usage of different serialization methods.
 */
public interface Protocol
{
	/**
	 * Deserialize an object from stream
	 * @param in the input stream
	 * @param <T> expected type of object
	 * @return the received object
	 * @throws IOException when an error occurs during decoding or reading from stream
	 */
	<T> T decode(InputStream in) throws IOException;

	/**
	 * Serialize an object to stream
	 * @param o the object to be serialized
	 * @param out the output stream
	 * @param <T> the object type
	 * @throws IOException when an error occurs during encoding or reading from stream
	 */
	<T> void encode(T o, OutputStream out) throws IOException;
}
