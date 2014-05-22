package com.meanworks.lampgui.test;

import java.awt.Color;
import java.awt.event.KeyEvent;

import org.lwjgl.input.Keyboard;

import com.meanworks.lamp2d.Application;
import com.meanworks.lamp2d.Config;
import com.meanworks.lamp2d.Game;
import com.meanworks.lamp2d.Input;
import com.meanworks.lamp2d.Lamp2DGUIInputAdapter;
import com.meanworks.lamp2d.Lamp2DGUIRenderer;
import com.meanworks.lamp2d.LampImage;
import com.meanworks.lamp2d.Renderer;
import com.meanworks.lamp2d.SpriteLoader;
import com.meanworks.lampgui.GUIException;
import com.meanworks.lampgui.GUIInputHandler;
import com.meanworks.lampgui.LampGUI;
import com.meanworks.lampgui.components.Window;

public class Test implements Application {

	/**
	 * The different game states
	 * 
	 * @author Meanz
	 * 
	 */
	public enum State {
		PLAYING, MENU, PAUSED
	}

	/**
	 * A test sprite
	 */
	private LampImage bg;

	/**
	 * The current state of the game
	 */
	private State gameState;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.meanworks.lamp2d.Application#preload()
	 */
	@Override
	public void preload() {
		bg = SpriteLoader.loadSprite("./data/gui/loading_bg.png");
		gameState = State.PLAYING;

		Window window = new Window("Test Window", 10, 10, 300, 350);

		try {
			
			GUIInputHandler inputHandler = new GUIInputHandler();
			Lamp2DGUIInputAdapter adapter = new Lamp2DGUIInputAdapter(inputHandler);
			Input.addMouseListener(adapter);
			LampGUI.init(new Lamp2DGUIRenderer(Game.getRenderer()), inputHandler);
			LampGUI.addComponent(window);
		} catch (GUIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.meanworks.lamp2d.Application#update()
	 */
	@Override
	public void update() {
		if (Input.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Game.stop();
		}

		if (Input.isKeyPressed(Keyboard.KEY_A)) {
			System.err.println("KEY A Pressed");
		}
		if (Input.isKeyDown(Keyboard.KEY_A)) {
			System.err.println("KEY A Held");
		}
		if (Input.isKeyReleased(Keyboard.KEY_A)) {
			System.err.println("KEY A Released.");
		}

		

		if (gameState == State.PLAYING) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.meanworks.lamp2d.Application#render(com.meanworks.lamp2d.Renderer)
	 */
	@Override
	public void render(Renderer renderer) {
		renderer.setColor(Color.blue);
		renderer.fillRect(1, 1, 5, 5);
		renderer.drawLine(100, 100, 200, 100);
		renderer.draw(bg, 0, 0);
		renderer.drawString("Hello there, Woop!", 20, 50);
		
		//Render fps
		renderer.drawString("FPS: " + Game.getFPS(), 5, 5);
		
		LampGUI.update();
		LampGUI.render();

		if (gameState == State.PLAYING) {

		}
	}

	/**
	 * Entry point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Config config = new Config();
		config.width = 800;
		config.height = 640;

		Game lamp2d = new Game(new Test(), config);
		lamp2d.start();
	}
}
