package mobilepad.io.message.control;

public class CommandEvent extends ControlEvent
{
	public String command;
	public boolean runThread;


	public CommandEvent(String command){
		super(Type.COMMAND);
		this.command = command;
		this.runThread = false;
	}


	public CommandEvent(String command, boolean runThread) {
		super(Type.COMMAND);
		this.command = command;
		this.runThread = runThread;
	}
}
