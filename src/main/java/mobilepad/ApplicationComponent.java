package mobilepad;


import java.util.logging.Level;

public class ApplicationComponent
{
	protected mobilepad.Application parentApplication;


	public ApplicationComponent() {
		this.parentApplication = Application.getInstance();
	}


	protected final void log(Level level, String message){
		parentApplication.getLogger().log(level, message);
	}

	protected final void log(Exception e){
		parentApplication.getLogger().log(Level.WARNING, e.getStackTrace().toString());
	}

	protected final void log(Level level, Exception e){
		parentApplication.getLogger().log(level, e.getMessage());
	}
}
