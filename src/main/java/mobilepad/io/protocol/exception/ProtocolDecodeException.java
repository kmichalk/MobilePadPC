package mobilepad.io.protocol.exception;

import java.io.IOException;

/**
 * Exception thrown when current protocol could not decode received information
 */
public class ProtocolDecodeException extends IOException
{
	public ProtocolDecodeException() {
	}


	public ProtocolDecodeException(String message) {
		super(message);
	}


	public ProtocolDecodeException(String message, Throwable cause) {
		super(message + "; " + cause.getMessage(), cause);
	}


	public ProtocolDecodeException(Throwable cause) {
		super(cause);
	}
}
