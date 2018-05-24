package mobilepad.app.exception;

import mobilepad.io.util.StringConcat;

public class UnexpectedException extends Exception
{
	public UnexpectedException(String message, Throwable cause) {
		super(message, cause);
	}


	public UnexpectedException(Throwable cause) {
		super(cause);
	}


	@Override
	public String getMessage() {
		return StringConcat.concat(
				"Unexpected exception of type ",
				getCause().getClass().getName(),
				" caught: ",
				super.getMessage());
	}
}
