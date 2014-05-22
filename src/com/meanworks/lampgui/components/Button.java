package com.meanworks.lampgui.components;

import java.awt.Color;

import com.meanworks.lampgui.Component;
import com.meanworks.lampgui.GUIRenderer;
import com.meanworks.lampgui.LampGUI;
import com.meanworks.lampgui.event.MouseClickEvent;

public class Button extends Component {

	/**
	 * The background color of this button
	 */
	private Color bgColor = Color.gray;

	/**
	 * The color of the border
	 */
	private Color borderColor = Color.lightGray;

	/**
	 * The color of the title
	 */
	private Color titleColor = Color.white;

	/**
	 * The title of this button
	 */
	private String title;

	/**
	 * Whether we have pressed our button or not
	 */
	private boolean isPressed = false;

	/**
	 * 
	 * @param title
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Button(String title, int x, int y, int width, int height) {
		super(LampGUI.generateName("button_"), x, y, width, height);
		this.title = title;
	}

	/**
	 * Called when this button is clicked
	 */
	public void onClick() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.meanworks.lampgui.Component#onMouseClick(com.meanworks.lampgui.event
	 * .MouseClickEvent)
	 */
	@Override
	public boolean onMouseClick(MouseClickEvent mouseEvent) {
		System.err.println("Mouse Click! Button: " + mouseEvent.getButton()
				+ " Pressed: " + mouseEvent.isPressed() + " X: "
				+ mouseEvent.getX() + " Y: " + mouseEvent.getY());
		if (mouseEvent.getButton() == 0 && mouseEvent.isPressed()
				&& isInside(mouseEvent)) {
			bgColor = Color.green;
			isPressed = true;
			toFront();
			return true;
		} else if (isPressed && mouseEvent.getButton() == 0) {
			if (isInside(mouseEvent)) {
				onClick();
				bgColor = Color.gray;
				return true;
			} else {
				bgColor = Color.gray;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.meanworks.lampgui.Component#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.meanworks.lampgui.Component#render()
	 */
	@Override
	public void render(GUIRenderer renderer) {
		// TODO Auto-generated method stub

		// Draw background
		renderer.fillRectangle(getX(), getY(), getWidth(), getHeight(), bgColor);

		// Draw border
		renderer.drawRectangle(getX(), getY(), getWidth(), getHeight(),
				borderColor);

		// Draw the title
		renderer.drawString(title, getX(), getY(), titleColor);
	}

}
