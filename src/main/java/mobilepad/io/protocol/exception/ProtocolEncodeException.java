package mobilepad.io.protocol.exception;

import java.io.IOException;

/**
 * Exception thrown when current protocol could not serialize an object
 */
public class ProtocolEncodeException extends IOException
{
	public ProtocolEncodeException() {
	}


	public ProtocolEncodeException(String message) {
		super(message);
	}


	public ProtocolEncodeException(String message, Throwable cause) {
		super(message, cause);
	}


	public ProtocolEncodeException(Throwable cause) {
		super(cause);
	}
}
