package mobilepad.io.message.control;

/**
 * An event executing double click of a specified mouse button
 */
public class MouseDoubleClickEvent extends ControlEvent
{
	/**
	 * The mouse button
	 */
	public int button;


	/**
	 * Default constructor
	 */
	public MouseDoubleClickEvent() {
		super(Type.DOUBLE_CLICK);
		this.button = MouseButtonEvent.Button.LEFT;
	}


	/**
	 * Constructor
	 * @param button the mouse button
	 */
	public MouseDoubleClickEvent(int button) {
		super(Type.DOUBLE_CLICK);
		this.button = button;
	}
}
