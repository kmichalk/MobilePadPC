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
import mobilepad.io.message.control.mapping.DefaultConfigurationFileHandler;
import mobilepad.io.protocol.Protocol;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;

/**
 * Class handling configuration and control events of an application.
 */
public class ApplicationController extends ApplicationComponent
{
	private Robot robot;
	private ControlEventMapper customEventMapper;
	private ConfigurationFileHandler mappingFileHandler;
	private Protocol configurationProtocol;


	/**
	 * The default constructor
	 * @throws ApplicationControllerInitializationException when the Robot could not be initialized
	 */
	public ApplicationController() throws ApplicationControllerInitializationException {
		try {
			this.robot = new Robot();
			this.robot.setAutoDelay(50);
			this.customEventMapper = new ArrayControlEventMapper();
			this.mappingFileHandler = new DefaultConfigurationFileHandler();
			this.configurationProtocol = Application.DEFAULT_SERIALIZATION_PROTOCOL;
		}
		catch (AWTException e) {
			throw new ApplicationControllerInitializationException("Application controller could not be initialized.", e);
		}
	}


	/**
	 * Gets the protocol for serializing configuration
	 * @return the protocol
	 */
	public Protocol getConfigurationProtocol() {
		return configurationProtocol;
	}


	/**
	 * Gets the mapping file handler
	 * @return the mapping file handler
	 */
	public ConfigurationFileHandler getMappingFileHandler() {
		return mappingFileHandler;
	}


	/**
	 * gets the event handler
	 * @return the event handler
	 */
	public ControlEventMapper getEventMapper() {
		return customEventMapper;
	}


	/**
	 * Applies all configuration elements from the collection
	 * @param configurationItems the configuration read from file
	 */
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


	private void applyCustom(CustomEvent event){
		handleMessage(customEventMapper.get(event.id));
	}


	private void applySwipe(SwipeEvent event){
		//Point pos = MouseInfo.getPointerInfo().getLocation();
		//mouseMove(pos.x + event.);
	}

	private void applyDoubleClick(MouseDoubleClickEvent event){
		robot.mousePress(event.button);
		robot.mouseRelease(event.button);
		robot.mousePress(event.button);
		robot.mouseRelease(event.button);
	}


	/**
	 * Controls the application and applies a specific command using a Robot depending on the message type
	 * @param message the received message to be applied
	 */
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
				case SWIPE:
					applySwipe((SwipeEvent)message);
					break;
				case DOUBLE_CLICK:
					applyDoubleClick((MouseDoubleClickEvent)message);
					break;
			}
		}
		catch (Throwable t){
			log(new UnexpectedException("Error occurred when executing received message", t));
		}
	}


	private void mouseSet(int x, int y){
		robot.mouseMove(x, y);
	}

	private void mouseMove(int x, int y) {
		Point pos = MouseInfo.getPointerInfo().getLocation();
		robot.mouseMove(pos.x + x, pos.y + y);
	}


	private void mousePress(int buttons) {
		robot.mousePress(buttons);
	}


	private void mouseRelease(int buttons) {
		robot.mouseRelease(buttons);
	}


	private void mouseWheel(int wheelAmt) {
		robot.mouseWheel(wheelAmt);
	}


	private void keyPress(int keycode) {
		robot.keyPress(keycode);

	}


	private void keyRelease(int keycode) {
		robot.keyRelease(keycode);
	}


//	public Color getPixelColor(int x, int y) {
//		return robot.getPixelColor(x, y);
//	}
//
//
//	public BufferedImage createScreenCapture(Rectangle screenRect) {
//		return robot.createScreenCapture(screenRect);
//	}
//
//
//	public boolean isAutoWaitForIdle() {
//		return robot.isAutoWaitForIdle();
//	}
//
//
//	public void setAutoWaitForIdle(boolean isOn) {
//		robot.setAutoWaitForIdle(isOn);
//	}
//
//
//	public int getAutoDelay() {
//		return robot.getAutoDelay();
//	}
//
//
//	public void setAutoDelay(int ms) {
//		robot.setAutoDelay(ms);
//	}
//
//
//	public void delay(int ms) {
//		robot.delay(ms);
//	}
//
//
//	public void waitForIdle() {
//		robot.waitForIdle();
//	}
}

