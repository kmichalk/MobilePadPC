package mobilepad.io.message.control;

/**
 * A type of control event containing a command to be executed in OS terminal
 */
public class CommandEvent extends ControlEvent
{
	/**
	 * The command to be executed
	 */
	public String command;

	/**
	 * Specifies if the command should be run in separate thread
	 */
	public boolean runThread;


	/**
	 * The constructor
	 * @param command the command
	 */
	public CommandEvent(String command){
		super(Type.COMMAND);
		this.command = command;
		this.runThread = false;
	}


	/**
	 * Constructor
	 * @param command the command
	 * @param runThread Specifies if the command should be run in separate thread
	 */
	public CommandEvent(String command, boolean runThread) {
		super(Type.COMMAND);
		this.command = command;
		this.runThread = runThread;
	}
}
