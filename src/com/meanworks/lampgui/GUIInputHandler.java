package com.meanworks.lampgui;

import java.util.LinkedList;
import java.util.List;

import com.meanworks.lampgui.event.MouseClickEvent;
import com.meanworks.lampgui.event.MouseEvent;
import com.meanworks.lampgui.event.MouseMoveEvent;

public class GUIInputHandler implements GUIMouseListener {

	private int mouseX = 0;
	private int mouseY = 0;
	private int mouseDX = 0;
	private int mouseDY = 0;
	private int lastX = 0;
	private int lastY = 0;

	private LinkedList<MouseEvent> mouseEvents = new LinkedList<MouseEvent>();

	/**
	 * 
	 */
	public GUIInputHandler() {

	}

	/**
	 * Create proper mouse events
	 */
	public void update() {
		mouseEvents.clear();
	}

	/**
	 * 
	 * @return
	 */
	public List<MouseEvent> getMouseEvents() {
		return mouseEvents;
	}

	/**
	 * 
	 */
	@Override
	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub
		// Generate a mouse event
		mouseEvents.push(new MouseClickEvent(x, y, button, true));
	}

	/**
	 * 
	 */
	@Override
	public void mouseReleased(int button, int x, int y) {
		// TODO Auto-generated method stub
		mouseEvents.push(new MouseClickEvent(x, y, button, false));
	}

	/**
	 * 
	 */
	@Override
	public void mouseMoved(int newX, int newY) {
		mouseEvents.push(new MouseMoveEvent(newX, newY, newX - lastX, newY - lastY));
		lastX = newX;
		lastY = newY;
	}

}
