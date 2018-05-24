package mobilepad.app;


import java.util.logging.Level;

public class ApplicationComponent
{
	private static Level exceptionLoggingLevel = Level.SEVERE;
	private static Level stringLoggingLevel = Level.INFO;

	protected Application parentApplication;


	public static void setDefaultExceptionLoggingLevel(Level level){
		exceptionLoggingLevel = level;
	}

	public void setDefaultStringLoggingLevel(Level level){
		stringLoggingLevel = level;
	}

	public ApplicationComponent() {
		this.parentApplication = Application.getInstance();
	}


	public final void log(Level level, String message){
		parentApplication.getLogger().log(level, message);
	}

	public final void log(String message){
		parentApplication.getLogger().log(stringLoggingLevel, message);
	}

	public final void log(Throwable t){
		parentApplication.getLogger().log(exceptionLoggingLevel, t.getMessage());
	}

	public final void log(Level level, Throwable e){
		parentApplication.getLogger().log(level, e.getMessage());
	}
}
