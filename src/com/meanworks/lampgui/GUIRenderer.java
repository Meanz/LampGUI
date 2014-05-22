package com.meanworks.lampgui;

import java.awt.Color;

public interface GUIRenderer {

	public void drawString(String text, int x, int y, Color c);
	
	public void drawRectangle(int x, int y, int width, int height, Color c);
	
	public void fillRectangle(int x, int y, int width, int height, Color c);
	
	
}
