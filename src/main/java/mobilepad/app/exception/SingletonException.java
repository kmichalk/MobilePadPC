package mobilepad.app.exception;

public class SingletonException extends ApplicationInitializationException
{
	public SingletonException() {
		super("Singleton object already initialized.");
	}


	public SingletonException(String message) {
		super(message);
	}


	public SingletonException(Class objectClass){
		super("Singleton object of class " + objectClass.getName() + " already initialized.");
	}
}
