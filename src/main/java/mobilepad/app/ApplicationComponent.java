package mobilepad.app;


import java.util.logging.Level;

/**
 * Base class for every class that is associated with an instance of Application
 */
public class ApplicationComponent
{
	private static Level exceptionLoggingLevel = Level.SEVERE;
	private static Level stringLoggingLevel = Level.INFO;

	/**
	 * The reference to the parent application
	 */
	protected Application parentApplication;


	/**
	 * Sets the default logging level for exceptions
	 * @param level the logging level
	 */
	public static void setDefaultExceptionLoggingLevel(Level level){
		exceptionLoggingLevel = level;
	}


	/**
	 * Sets the default logging level for string messages
	 * @param level the logging level
	 */
	public void setDefaultStringLoggingLevel(Level level){
		stringLoggingLevel = level;
	}


	/**
	 * The default constructor. Associates the component with instance of application
	 */
	public ApplicationComponent() {
		this.parentApplication = Application.getInstance();
	}


	/**
	 * Logs a message on specified level using application logger
	 * @param level the log level
	 * @param message the message
	 */
	protected final void log(Level level, String message){
		parentApplication.getLogger().log(level, message);
	}


	/**
	 * Logs a message using application logger
	 * @param message the message
	 */
	protected final void log(String message){
		parentApplication.getLogger().log(stringLoggingLevel, message);
	}


	/**
	 * Logs a throwable using application logger
	 * @param t the throwable exception
	 */
	protected final void log(Throwable t){
		parentApplication.getLogger().log(exceptionLoggingLevel, t.getMessage());
	}


	/**
	 * Logs a throwable on specified level using application logger
	 * @param level the log level
	 * @param e the throwable exception
	 */
	protected final void log(Level level, Throwable e){
		parentApplication.getLogger().log(level, e.getMessage());
	}
}
