package com.meanworks.lampgui;

public interface GUIMouseListener {

	/**
	 * Called when a mouse button is pressed
	 * 
	 * @param button
	 * @param x
	 * @param y
	 */
	public void mousePressed(int button, int x, int y);

	/**
	 * Called when a mouse button is released
	 * 
	 * @param button
	 * @param x
	 * @param y
	 */
	public void mouseReleased(int button, int x, int y);

	/**
	 * Called when the mouse is moved
	 * 
	 * @param newX
	 * @param newY
	 */
	public void mouseMoved(int newX, int newY);

}
