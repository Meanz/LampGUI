package com.meanworks.lampgui.event;

public class MouseClickEvent extends MouseEvent {

	/**
	 * What button was pressed or released
	 */
	private int button;
	/**
	 * Whether the button was pressed or not
	 */
	private boolean pressed;

	/**
	 * Construct a new mouse click event
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @param lastMouseX
	 * @param lastMouseY
	 * @param mouseDX
	 * @param mouseDY
	 * @param button
	 * @param pressed
	 */
	public MouseClickEvent(int mouseX, int mouseY, int button, boolean pressed) {
		super(mouseX, mouseY);
		this.button = button;
		this.pressed = pressed;
	}

	/**
	 * Get what button is pressed
	 * 
	 * @return
	 */
	public int getButton() {
		return button;
	}

	/**
	 * Get whether the mouse button is pressed or not
	 * 
	 * @return
	 */
	public boolean isPressed() {
		return pressed;
	}

}
