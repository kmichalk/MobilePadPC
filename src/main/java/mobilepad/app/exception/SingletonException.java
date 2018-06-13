package mobilepad.app.exception;

/**
 * An exception thrown when the singleton class was already initialized
 */
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
