package mobilepad.io.message.control;

public class MouseMoveEvent extends ControlEvent
{
	public int x;
	public int y;


	public MouseMoveEvent(int x, int y) {
		super(Type.MOUSE_MOVE);
		this.x = x;
		this.y = y;
	}
}
