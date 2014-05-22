package com.meanworks.lamp2d;

import com.meanworks.lampgui.GUIMouseListener;

public class Lamp2DGUIInputAdapter implements MouseListener {

	private GUIMouseListener adapter;

	public Lamp2DGUIInputAdapter(GUIMouseListener adapter) {
		this.adapter = adapter;
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		adapter.mousePressed(button, x, y);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		adapter.mouseReleased(button, x, y);
	}

	@Override
	public void mouseMoved(int newX, int newY) {
		adapter.mouseMoved(newX, newY);
	}

}
