package mobilepad.io.message.control;

/**
 * An event executing a combination of key presses
 */
public class KeyCombinationEvent extends ControlEvent
{
	/**
	 * The combination of keys
	 */
	public int[] keys;
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
	public KeyCombinationEvent() {
		super(Type.KEY_COMBINATION);
		keys = null;
	}


	/**
	 * Constructor setting the key combination
	 * @param keys the keys
	 */
	public KeyCombinationEvent(int... keys) {
		super(Type.KEY_COMBINATION);
		this.keys = keys;
		this.press = true;
		this.release = true;
	}

	/**
	 * Constructor setting the key combination
	 * @param keys the keys
	 * @param press Sepcifies if the keys should be pressed
	 * @param release Sepcifies if the keys should be released
	 */
	public KeyCombinationEvent(int[] keys, boolean press, boolean release) {
		super(Type.KEY_COMBINATION);
		this.keys = keys;
		this.press = press;
		this.release = release;
	}
}
