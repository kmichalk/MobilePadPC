package mobilepad.io.protocol.exception;

import java.io.IOException;

public class ProtocolDecodeException extends IOException
{
	public ProtocolDecodeException() {
	}


	public ProtocolDecodeException(String message) {
		super(message);
	}


	public ProtocolDecodeException(String message, Throwable cause) {
		super(message, cause);
	}


	public ProtocolDecodeException(Throwable cause) {
		super(cause);
	}
}
