package mobilepad.io.bluetooth.message.control;

import java.io.IOException;

public class CommandEvent implements ControlEvent
{
	public String command;
	public boolean runThread;

	public CommandEvent(){
		this.command = "";
		this.runThread = false;
	}

	public CommandEvent(String command){
		this.command = command;
		this.runThread = false;
	}


	public CommandEvent(String command, boolean runThread) {
		this.command = command;
		this.runThread = runThread;
	}


	@Override
	public void apply() {
		Runnable execution = () -> {
			try {
				Runtime.getRuntime().exec(command);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		};
		if (runThread)
			new Thread(execution).start();
		else
			execution.run();
	}
}
