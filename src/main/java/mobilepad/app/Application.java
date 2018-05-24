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

public abstract class Application extends javafx.application.Application
{
	private static final Level DEFAULT_LOGGING_LEVEL = Level.INFO;
	private static final Handler DEFAULT_LOGGING_HANDLER = new ConsoleHandler();
	public static final Protocol DEFAULT_SERIALIZATION_PROTOCOL = new DefaultBinarySerializationProtocol();
	private static Application instance = null;
	private Stage primaryStage;
	private Logger logger;
	private ApplicationController controller;


	protected static void setInstance(Application instance) throws SingletonException {
		if (Application.instance == null)
			Application.instance = instance;
		else
			throw new SingletonException(Application.instance.getClass());
	}


	public static Application getInstance(){
		return instance;
	}

	protected Application() throws ApplicationInitializationException {
		setInstance(this);
		this.primaryStage = null;
		this.controller = new ApplicationController();
		this.logger = Logger.getLogger(instance.getClass().getName());
		this.logger.setLevel(DEFAULT_LOGGING_LEVEL);
		this.logger.addHandler(DEFAULT_LOGGING_HANDLER);
	}


	@Override
	public synchronized void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
	}


	public final Stage getPrimaryStage() {
		return primaryStage;
	}


	public final Logger getLogger() {
		return logger;
	}

	public final ApplicationController getController(){
		return controller;
	}
}
