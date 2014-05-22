package com.meanworks.lampgui.components;

import java.awt.Color;

import com.meanworks.lampgui.Component;
import com.meanworks.lampgui.GUIException;
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

		// Add a close button to this window
		final Window window = this;
		Button button = new Button(" X", getWidth() - 16, 0, 16, titleBarHeight) {

			@Override
			public void onClick() {
				window.dispose();
			}

		};

		Button button2 = new Button("Click Me!", 15, 25, 80, 24);

		try {
			this.addChild(button);
			this.addChild(button2);
		} catch (GUIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if (mouseEvent.getButton() == 0 && mouseEvent.isPressed()
				&& isInside(mouseEvent)) {
			// If we are inside title bar
			if (mouseEvent.getY() < getY() + titleBarHeight) {
				bgColor = Color.blue;
				dragging = true;
				dragX = mouseEvent.getX() - getX();
				dragY = mouseEvent.getY() - getY();
				toFront();
				setFocus();
				return true;
			} else {
				toFront();
				return true;
			}
		} else if (mouseEvent.getButton() == 0 && !mouseEvent.isPressed()) {
			if (dragging) {
				bgColor = Color.gray;
				dragging = false;
				unfocus();
				return true;
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
		// Nothing to do here, just yet
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.meanworks.lampgui.Component#render()
	 */
	@Override
	public void render(GUIRenderer renderer) {

		// Render Background
		renderer.fillRectangle(getX(), getY(), getWidth(), getHeight(), bgColor);

		// Render Border
		renderer.drawRectangle(getX(), getY(), getWidth(), getHeight(),
				borderColor);

		// Render Title bar ?
		renderer.drawRectangle(getX(), getY(), getWidth(), titleBarHeight,
				borderColor);

		// Render Title
		// g.drawString(title, getX() + (getWidth() / 2) -
		// g.getTextLength(title), getY() + g.getTextHeight(), textColor);
		renderer.drawString(title, getX() + (getWidth() / 2) - 45, getY(),
				textColor);
	}

}
