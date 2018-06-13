package mobilepad.io.message.control;

/**
 * An event executing mouse button press
 */
public class MouseButtonEvent extends ControlEvent
{
	/**
	 * Class containing static literals for usage in mobile application
	 */
	public static class Button{
		public static final int LEFT = 1024;
		public static final int MIDDLE = 2048;
		public static final int RIGHT = 4096;
	}

	/**
	 * The mouse button
	 */
	public int button;
	/**
	 * Sepcifies if the keys should be pressed
	 */
	public boolean press;
	/**
	 * Sepcifies if the keys should be released
	 */
	public boolean release;


	/**
	 * Default constructor
	 */
	public MouseButtonEvent(){
		super(Type.MOUSE_BUTTON);
		this.button = Button.LEFT;
		this.press = true;
		this.release = true;
	}


	/**
	 * Coonstructor
	 * @param button the mouse button
	 */
	public MouseButtonEvent(int button) {
		super(Type.MOUSE_BUTTON);
		this.button = button;
		this.press = true;
		this.release = true;
	}


	/**
	 * Constructor
	 * @param button the mouse button
	 * @param press Sepcifies if the keys should be pressed
	 * @param release Sepcifies if the keys should be released
	 */
	public MouseButtonEvent(int button, boolean press, boolean release) {
		super(Type.MOUSE_BUTTON);
		this.button = button;
		this.press = press;
		this.release = release;
	}
}
