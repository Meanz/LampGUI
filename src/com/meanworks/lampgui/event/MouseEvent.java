package com.meanworks.lampgui.event;

public abstract class MouseEvent {

	/**
	 * Current mouse Values
	 */
	private int mouseX, mouseY;

	/**
	 * Construct a new MouseEvent
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @param lastMouseX
	 * @param lastMouseY
	 * @param mouseDX
	 * @param mouseDY
	 */
	public MouseEvent(int mouseX, int mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}

	/**
	 * Get the X coordinate of the mouse
	 * 
	 * @return
	 */
	public int getX() {
		return mouseX;
	}

	/**
	 * Get the Y coordinate of the mouse
	 * 
	 * @return
	 */
	public int getY() {
		return mouseY;
	}

}
