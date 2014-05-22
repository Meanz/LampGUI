package com.meanworks.lamp2d;

import java.awt.Color;

import com.meanworks.lampgui.GUIRenderer;

public class Lamp2DGUIRenderer implements GUIRenderer {

	private Renderer renderer;

	public Lamp2DGUIRenderer() {
		this.renderer = null;
	}

	public Lamp2DGUIRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public void drawString(String text, int x, int y, Color c) {
		// TODO Auto-generated method stub
		renderer.setColor(c);
		renderer.drawString(text, x, y);
	}

	@Override
	public void drawRectangle(int x, int y, int width, int height, Color c) {
		// TODO Auto-generated method stub
		renderer.setColor(c);
		renderer.drawRect(x, y, width, height);
	}

	@Override
	public void fillRectangle(int x, int y, int width, int height, Color c) {
		// TODO Auto-generated method stub
		renderer.setColor(c);
		renderer.fillRect(x, y, width, height);
	}

}
