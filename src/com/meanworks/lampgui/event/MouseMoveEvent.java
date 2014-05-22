package com.meanworks.lampgui.event;

public class MouseMoveEvent extends MouseEvent {

	private int mouseDX = 0;
	private int mouseDY = 0;
	
	public MouseMoveEvent(int newX, int newY, int dx, int dy) {
		super(newX, newY);
		this.mouseDX = dx;
		this.mouseDY = dy;
	}
	
	public int getMouseDX() {
		return mouseDX;
	}
	
	public int getMouseDY() {
		return mouseDY;
	}
	
}
