package mobilepad.io.protocol.exception;

public class XMLProtocolDecodeException extends ProtocolDecodeException
{
	public XMLProtocolDecodeException() {
	}


	public XMLProtocolDecodeException(String message) {
		super(message);
	}


	public XMLProtocolDecodeException(String message, Throwable cause) {
		super(message, cause);
	}


	public XMLProtocolDecodeException(Throwable cause) {
		super(cause);
	}
}
