package com.meanworks.lampgui;

public interface IInputPipe {

	public int getX();
	public int getY();
	
	public int getDX();
	public int getDY();
	
	public boolean isMousePressed(int button);
	public boolean isMouseReleased(int button);
	
	
	
}
