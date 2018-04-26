package mobilepad;

import javafx.stage.Stage;
import mobilepad.exception.SingletonException;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Application extends javafx.application.Application
{
	private static final Level DEFAULT_LOGGING_LEVEL = Level.INFO;
	private static final Handler DEFAULT_LOGGING_HANDLER = new ConsoleHandler();
	private static Application instance = null;
	private Logger logger;


	static Application getInstance(){
		return instance;
	}

	private final void initializeInstance() throws SingletonException {
		if (instance == null)
			instance = this;
		else
			throw new SingletonException(getClass());
	}

	public Application() {
		this.logger = Logger.getLogger(MobilePadPC.class.getName());
		this.logger.setLevel(DEFAULT_LOGGING_LEVEL);
		this.logger.addHandler(DEFAULT_LOGGING_HANDLER);
	}


	@Override
	public synchronized void start(Stage primaryStage) throws Exception {
		initializeInstance();
	}


	final Logger getLogger() {
		return logger;
	}
}
