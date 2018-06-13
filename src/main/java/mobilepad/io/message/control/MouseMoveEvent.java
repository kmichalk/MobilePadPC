package mobilepad.io.message.control;

/**
 * An event executing moving mouse to a specified location
 */
public class MouseMoveEvent extends ControlEvent
{
	/**
	 * The x coordinate
	 */
	public int x;
	/**
	 * The y coordinate
	 */
	public int y;


	/**
	 * Constructor
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public MouseMoveEvent(int x, int y) {
		super(Type.MOUSE_MOVE);
		this.x = x;
		this.y = y;
	}
}
