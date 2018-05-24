package mobilepad.app.control;

import mobilepad.app.Application;
import mobilepad.app.ApplicationComponent;
import mobilepad.app.MobilePadPC;
import mobilepad.app.conf.ConfigurationItem;
import mobilepad.app.control.exception.ApplicationControllerInitializationException;
import mobilepad.app.exception.UnexpectedException;
import mobilepad.io.message.InfoMessage;
import mobilepad.io.message.Message;
import mobilepad.io.message.control.*;
import mobilepad.io.message.control.mapping.ArrayControlEventMapper;
import mobilepad.io.message.control.mapping.ConfigurationFileHandler;
import mobilepad.io.message.control.mapping.ControlEventMapper;
import mobilepad.io.message.control.mapping.XMLConfigurationFileHandler;
import mobilepad.io.protocol.Protocol;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;

public class ApplicationController extends ApplicationComponent
{
	private Robot robot;
	private ControlEventMapper customEventMapper;
	private ConfigurationFileHandler mappingFileHandler;
	private Protocol configurationProtocol;

	public ApplicationController() throws ApplicationControllerInitializationException {
		try {
			this.robot = new Robot();
			this.robot.setAutoDelay(50);
			this.customEventMapper = new ArrayControlEventMapper();
			this.mappingFileHandler = new XMLConfigurationFileHandler();
			this.configurationProtocol = Application.DEFAULT_SERIALIZATION_PROTOCOL;
		}
		catch (AWTException e) {
			throw new ApplicationControllerInitializationException("Application controller could not be initialized.", e);
		}
	}


	public Protocol getConfigurationProtocol() {
		return configurationProtocol;
	}


	public ConfigurationFileHandler getMappingFileHandler() {
		return mappingFileHandler;
	}

	public ControlEventMapper getEventMapper() {
		return customEventMapper;
	}


	public void applyConfiguration(Collection<ConfigurationItem> configurationItems){
		for (ConfigurationItem i : configurationItems)
			i.apply(this);
	}


	private void applyCommand(CommandEvent event){
		Runnable execution = () -> {
			try {
				Runtime.getRuntime().exec(event.command);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		};
		if (event.runThread)
			new Thread(execution).start();
		else
			execution.run();
	}


	private void applyDisconnect(DisconnectEvent event){
		MobilePadPC.getInstance().getBluetoothServer().abortCurrentConnection();
	}


	private void applyKeyCombination(KeyCombinationEvent event){
		if (event.press)
			for (int k : event.keys)
				keyPress(k);
		if (event.release)
			for (int k : event.keys)
				keyRelease(k);
	}


	private void applyKey(KeyEvent event){
		if (event.press)
			keyPress(event.key);
		if (event.release)
			keyRelease(event.key);
	}


	private void applyMouseButton(MouseButtonEvent event){
		if (event.press)
			mousePress(event.button);
		if (event.release)
			mouseRelease(event.button);
	}


	private void applyMouseMove(MouseMoveEvent event){
		mouseMove(event.x, event.y);
	}



	public void applyCustom(CustomEvent event){
		handleMessage(customEventMapper.get(event.id));
	}


	public void handleMessage(Message message){
		try {
			switch(message.type){
				case COMMAND:
					applyCommand((CommandEvent)message);
					break;
				case CUSTOM:
					applyCustom((CustomEvent)message);
					break;
				case DISCONNECT:
					applyDisconnect((DisconnectEvent)message);
					break;
				case KEY_COMBINATION:
					applyKeyCombination((KeyCombinationEvent)message);
					break;
				case KEY:
					applyKey((KeyEvent) message);
					break;
				case MOUSE_BUTTON:
					applyMouseButton((MouseButtonEvent) message);
					break;
				case MOUSE_MOVE:
					applyMouseMove((MouseMoveEvent) message);
					break;
				case INFO:
					log(((InfoMessage)message).text);
					break;
			}
		}
		catch (Throwable t){
			log(new UnexpectedException("Error occurred when executing received message", t));
		}
	}



	public void mouseMove(int x, int y) {
		robot.mouseMove(x, y);
	}


	public void mousePress(int buttons) {
		robot.mousePress(buttons);
	}


	public void mouseRelease(int buttons) {
		robot.mouseRelease(buttons);
	}


	public void mouseWheel(int wheelAmt) {
		robot.mouseWheel(wheelAmt);
	}


	public void keyPress(int keycode) {
		robot.keyPress(keycode);
	}


	public void keyRelease(int keycode) {
		robot.keyRelease(keycode);
	}


	public Color getPixelColor(int x, int y) {
		return robot.getPixelColor(x, y);
	}


	public BufferedImage createScreenCapture(Rectangle screenRect) {
		return robot.createScreenCapture(screenRect);
	}


	public boolean isAutoWaitForIdle() {
		return robot.isAutoWaitForIdle();
	}


	public void setAutoWaitForIdle(boolean isOn) {
		robot.setAutoWaitForIdle(isOn);
	}


	public int getAutoDelay() {
		return robot.getAutoDelay();
	}


	public void setAutoDelay(int ms) {
		robot.setAutoDelay(ms);
	}


	public void delay(int ms) {
		robot.delay(ms);
	}


	public void waitForIdle() {
		robot.waitForIdle();
	}
}

