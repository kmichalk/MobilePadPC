package mobilepad.app;

import javafx.stage.Stage;
import mobilepad.app.control.ApplicationController;
import mobilepad.app.exception.ApplicationInitializationException;
import mobilepad.app.exception.SingletonException;
import mobilepad.io.protocol.DefaultBinarySerializationProtocol;
import mobilepad.io.protocol.Protocol;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The base class for application containing basic utilities. It assures that there is only one instance of this class.
 */
public abstract class Application extends javafx.application.Application
{
	private static final Level DEFAULT_LOGGING_LEVEL = Level.INFO;
	private static final Handler DEFAULT_LOGGING_HANDLER = new ConsoleHandler();
	public static final Protocol DEFAULT_SERIALIZATION_PROTOCOL = new DefaultBinarySerializationProtocol();
	private static Application instance = null;
	private Stage primaryStage;
	private Logger logger;
	private ApplicationController controller;


	private static void setInstance(Application instance) throws SingletonException {
		if (Application.instance == null)
			Application.instance = instance;
		else
			throw new SingletonException(Application.instance.getClass());
	}


	/**
	 * Gets the instance of singeton application
	 * @return the instance
	 */
	public static Application getInstance(){
		return instance;
	}


	/**
	 * Default constructor
	 * @throws ApplicationInitializationException when there was a problem with initialization of the application ie. the application was already initialized
	 */
	protected Application() throws ApplicationInitializationException {
		setInstance(this);
		this.primaryStage = null;
		this.controller = new ApplicationController();
		this.logger = Logger.getLogger(instance.getClass().getName());
		this.logger.setLevel(DEFAULT_LOGGING_LEVEL);
		//this.logger.addHandler(DEFAULT_LOGGING_HANDLER);
	}


	/**
	 * Overridden method run when the application starts
	 * @param primaryStage the stage on which the application GUI runs
	 * @throws Exception
	 */
	@Override
	public synchronized void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
	}


	/**
	 * Gets the associated stage
	 * @return the associated stage
	 */
	public final Stage getPrimaryStage() {
		return primaryStage;
	}


	/**
	 * Gets the main application logger for usage in application component classes
	 * @return the logger
	 */
	public final Logger getLogger() {
		return logger;
	}


	/**
	 * Gets access to the application controller
	 * @return the controller
	 */
	public final ApplicationController getController(){
		return controller;
	}
}
