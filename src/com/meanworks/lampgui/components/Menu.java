package com.meanworks.lampgui.components;

import java.awt.Color;

import com.meanworks.lampgui.Component;
import com.meanworks.lampgui.GUIRenderer;
import com.meanworks.lampgui.LampGUI;
import com.meanworks.lampgui.event.MouseClickEvent;
import com.meanworks.lampgui.event.MouseMoveEvent;

public class Menu extends Component {

	/**
	 * The title of this menu
	 */
	private String title;

	/**
	 * The options of this menu
	 */
	private String[] options;

	/**
	 * The background color of this menu
	 */
	private Color bgColor = Color.gray;

	/**
	 * The border color of this menu
	 */
	private Color borderColor = Color.lightGray;

	/**
	 * The text color of this menu
	 */
	private Color textColor = Color.white;

	/**
	 * The hover color of this menu
	 */
	private Color hoverColor = new Color(100, 100, 100); // Some whitish color

	/**
	 * The current hover index
	 */
	private int hoverIndex = -1;

	/**
	 * The range away from the menu the mouse can be before this menu auto
	 * disposes
	 */
	private int maxRange = 100;

	/**
	 * Create a menu
	 * 
	 * @param options
	 * @param title
	 * @param x
	 * @param y
	 */
	public Menu(String[] options, String title, int x, int y) {
		super(LampGUI.generateName("menu_"), x, y, 0, 0);

		// Calculate height and width using font measures
		// This should be the job of a renderer, so this will happen
		// The first time this menu is rendered
		// For now let's just put in some default values
		setSize(150, 150);

		// Save the title
		this.title = title;

		// Set the options
		this.options = options;

	}

	/**
	 * Called when an option is clicked
	 * 
	 * @param option
	 */
	public void onOptionClick(int option) {

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
		// TODO Auto-generated method stub

		if (isInside(mouseEvent)) {
			int startY = 24;
			int optionGap = 24;
			int mouseY = mouseEvent.getY() - 24 - getY();
			if (mouseY < 0) {
				hoverIndex = -1;
			} else {
				int index = mouseY / optionGap;
				if (index >= options.length) {
					hoverIndex = -1;
				} else {
					if (mouseEvent.getButton() == 0 && !mouseEvent.isPressed()) {
						onOptionClick(hoverIndex);
						return true;
					}
				}
			}
		}
		return false;
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
		// Check if we are hovering

		if (isInside(mouseEvent)) {

			int startY = 24;
			int optionGap = 24;
			int mouseY = mouseEvent.getY() - 24 - getY();
			if (mouseY < 0) {
				hoverIndex = -1;
			} else {
				int index = mouseY / optionGap;
				if (index >= options.length) {
					hoverIndex = -1;
				} else {
					hoverIndex = index;
				}
			}
		} else {
			// Check distance from the menu
			if (mouseEvent.getY() < (getY() - maxRange)) {
				dispose();
			}
			if (mouseEvent.getY() > (getY() + getHeight() + maxRange)) {
				dispose();
			}
			if (mouseEvent.getX() < (getX() - maxRange)) {
				dispose();
			}
			if (mouseEvent.getX() > (getX() + getWidth() + maxRange)) {
				dispose();
			}
			hoverIndex = -1;
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
	 * @see
	 * com.meanworks.lampgui.Component#render(com.meanworks.lampgui.GUIRenderer)
	 */
	@Override
	public void render(GUIRenderer renderer) {
		// TODO Auto-generated method stub

		// Render the background
		renderer.fillRectangle(getX(), getY(), getWidth(), getHeight(), bgColor);

		// Draw the border
		renderer.drawRectangle(getX(), getY(), getWidth(), getHeight(),
				borderColor);

		// Draw the title bar
		renderer.drawRectangle(getX(), getY(), getWidth(), 18, borderColor);

		// Render the title text
		int textWidth = 40; // Heheh
		renderer.drawString(title, getX() + getWidth() / 2 - textWidth,
				getY() + 2, textColor);

		// Render the options
		int optionGap = 24;
		int optionStartY = 24;

		// Draw the hover index
		if (hoverIndex != -1) {
			renderer.fillRectangle(getX() + 1, getY() + optionStartY
					+ (hoverIndex * optionGap), getWidth() - 2, optionGap,
					hoverColor);
		}

		for (String option : options) {
			renderer.drawString(option, getX() + 10, getY() + optionStartY + 4,
					textColor);
			optionStartY += optionGap;
		}

	}

}
