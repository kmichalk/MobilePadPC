package mobilepad.app.exception;

/**
 * An exception thrown when the aplication could not start
 */
public class ApplicationInitializationException extends Exception
{
	public ApplicationInitializationException() {
	}


	public ApplicationInitializationException(String message) {
		super(message);
	}


	public ApplicationInitializationException(String message, Throwable cause) {
		super(message, cause);
	}


	public ApplicationInitializationException(Throwable cause) {
		super(cause);
	}
}
