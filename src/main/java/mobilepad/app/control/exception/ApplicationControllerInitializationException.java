package mobilepad.app.control.exception;

import mobilepad.app.exception.ApplicationInitializationException;

public class ApplicationControllerInitializationException extends ApplicationInitializationException
{
	public ApplicationControllerInitializationException() {
	}


	public ApplicationControllerInitializationException(String message) {
		super(message);
	}


	public ApplicationControllerInitializationException(String message, Throwable cause) {
		super(message, cause);
	}


	public ApplicationControllerInitializationException(Throwable cause) {
		super(cause);
	}
}
