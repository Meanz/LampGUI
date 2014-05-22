package com.meanworks.lampgui.components;

import java.awt.Color;

import com.meanworks.lampgui.Component;
import com.meanworks.lampgui.LampGUI;
import com.meanworks.lampgui.GUIRenderer;
import com.meanworks.lampgui.event.MouseClickEvent;
import com.meanworks.lampgui.event.MouseMoveEvent;

public class Window extends Component {

	/**
	 * The title of this window
	 */
	private String title;

	/**
	 * The background color of this window
	 */
	private Color bgColor = Color.gray;

	/**
	 * The border color of this window
	 */
	private Color borderColor = Color.lightGray;

	/**
	 * The color of the text on this window
	 */
	private Color textColor = Color.white;

	/**
	 * The height of the title bar
	 */
	private int titleBarHeight = 18;

	/**
	 * Whether we are dragging the window or not
	 */
	private boolean dragging = false;
	private int dragX = 0, dragY = 0;

	/**
	 * 
	 * @param title
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Window(String title, int x, int y, int width, int height) {
		super(LampGUI.generateName("window_"), x, y, width, height);
		this.title = title;
	}

	/**
	 * Get the height of the title bar
	 * 
	 * @return
	 */
	public int getTitleBarHeight() {
		return titleBarHeight;
	}

	/**
	 * Set the height of the title bar
	 * 
	 * @param titleBarHeight
	 */
	public void setTitleBarHeight(int titleBarHeight) {
		this.titleBarHeight = titleBarHeight;
	}

	/**
	 * Get the background color of this window
	 * 
	 * @return
	 */
	public Color getBgColor() {
		return bgColor;
	}

	/**
	 * Set the background color of this window
	 * 
	 * @param bgColor
	 */
	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	/**
	 * Get the border color of this window
	 * 
	 * @return
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * Set the border color of this window
	 * 
	 * @param borderColor
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * Get the color of the text in this window
	 * 
	 * @return
	 */
	public Color getTextColor() {
		return textColor;
	}

	/**
	 * Set the text color of this window
	 * 
	 * @param textColor
	 */
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.meanworks.lampgui.Component#onMouseMove(com.meanworks.lampgui.event
	 * .MouseMoveEvent)
	 */
	@Override
	public boolean onMouseMove(MouseMoveEvent mouseEvent) {
		// TODO Auto-generated method stub
		if (dragging) {
			setPosition(mouseEvent.getX() - dragX, mouseEvent.getY() - dragY);
			return true;
		} else {
			return false;
		}
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
			// If we are inside title bar
			if (mouseEvent.getY() < getY() + titleBarHeight) {
				bgColor = Color.blue;
				dragging = true;
				dragX = mouseEvent.getX() - getX();
				dragY = mouseEvent.getY() - getY();
				return true;
			}
		} else if (mouseEvent.getButton() == 0 && !mouseEvent.isPressed()) {
			bgColor = Color.gray;
			dragging = false;
			return true;
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
		// Nothing to do here, just yet
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.meanworks.lampgui.Component#render()
	 */
	@Override
	public void render() {

		/*
		 * We can't render without a renderer
		 */
		if (LampGUI.getRenderer() == null) {
			return;
		}

		GUIRenderer g = LampGUI.getRenderer();

		// Render Background
		g.fillRectangle(getX(), getY(), getWidth(), getHeight(), bgColor);

		// Render Border
		g.drawRectangle(getX(), getY(), getWidth(), getHeight(), borderColor);

		// Render Title bar ?
		g.drawRectangle(getX(), getY(), getWidth(), titleBarHeight, borderColor);

		// Render Title
		// g.drawString(title, getX() + (getWidth() / 2) -
		// g.getTextLength(title), getY() + g.getTextHeight(), textColor);
		g.drawString(title, getX() + (getWidth() / 2) - 45, getY(),
				textColor);
	}

}
