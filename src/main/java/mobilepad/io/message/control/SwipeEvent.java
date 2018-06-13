package mobilepad.io.message.control;

/**
 * Event sent when a swipe on screen was made in mobile application.
 * Contains more information about the event
 */
public class SwipeEvent extends ControlEvent
{
	/**
	 * Number of fingers used in the swipe
	 */
	public int fingers;
	/**
	 * time duration in milliseconds
	 */
	public long duration;
	/**
	 * The distance in pixels
	 */
	public double distance;


	/**
	 * Default constructor
	 */
	public SwipeEvent() {
		super(Type.SWIPE);
	}


	/**
	 * Constructor
	 * @param fingers number of fingers
	 * @param duration duration in milliseconds
	 * @param distance distance in pixels
	 */
	public SwipeEvent(int fingers, long duration, double distance) {
		super(Type.SWIPE);
		this.fingers = fingers;
		this.duration = duration;
		this.distance = distance;
	}
}
