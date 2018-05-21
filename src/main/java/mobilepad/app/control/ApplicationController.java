package mobilepad.app.control;

import mobilepad.app.ApplicationComponent;
import mobilepad.app.control.exception.ApplicationControllerInitializationException;
import mobilepad.io.bluetooth.message.control.ControlEvent;
import mobilepad.io.bluetooth.message.control.CustomEvent;
import mobilepad.io.bluetooth.message.control.mapper.ArrayControlEventMapper;
import mobilepad.io.bluetooth.message.control.mapper.ControlEventMapper;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ApplicationController extends ApplicationComponent
{
	private Robot robot;
	private ControlEventMapper customEventMapper;

	public ApplicationController() throws ApplicationControllerInitializationException {
		try {
			this.robot = new Robot();
			this.robot.setAutoDelay(50);
			this.customEventMapper = new ArrayControlEventMapper();
		}
		catch (AWTException e) {
			throw new ApplicationControllerInitializationException("Application controller could not be initialized.", e);
		}
	}


	public boolean map(ControlEvent event, int id, boolean override) {
		return customEventMapper.map(event, id, override);
	}


	public ControlEvent get(int id) {
		return customEventMapper.get(id);
	}


	public void applyCustomEvent(CustomEvent event){
		customEventMapper.get(event.id).apply();
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

