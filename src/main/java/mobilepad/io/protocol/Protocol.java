package mobilepad.io.protocol;

import com.sun.istack.internal.NotNull;
import mobilepad.io.protocol.exception.ProtocolDecodeException;
import mobilepad.io.bluetooth.message.Message;

public interface Protocol
{
	static void assertNotNull(byte[] data) throws ProtocolDecodeException {
		if (data == null)
			throw new ProtocolDecodeException("Could not decode, data was null.");
	}

	static void assertNonZero(byte[] data) throws ProtocolDecodeException {
		if (data.length == 0)
			throw new ProtocolDecodeException("Could not decode, data was empty.");
	}

	Message decode(@NotNull byte[] data) throws ProtocolDecodeException;
	byte[] encode(@NotNull Message message);
}
