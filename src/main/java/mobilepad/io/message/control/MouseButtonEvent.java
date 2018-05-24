package mobilepad.io.message.control;

public class MouseButtonEvent extends ControlEvent
{
	public int button;
	public boolean press;
	public boolean release;

	public MouseButtonEvent(int button) {
		super(Type.MOUSE_BUTTON);
		this.button = button;
		this.press = true;
		this.release = true;
	}


	public MouseButtonEvent(int button, boolean press, boolean release) {
		super(Type.MOUSE_BUTTON);
		this.button = button;
		this.press = press;
		this.release = release;
	}
}
