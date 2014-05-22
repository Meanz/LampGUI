package com.meanworks.lampgui;

import java.util.HashMap;
import java.util.List;

import com.meanworks.lampgui.event.MouseEvent;

public class LampGUI {

	/**
	 * The input adapter for this gui
	 */
	private static GUIInputHandler inputHandler = new GUIInputHandler();

	/**
	 * The renderer for this gui
	 */
	private static GUIRenderer renderer = null;

	/**
	 * The list of components
	 */
	private static HashMap<String, Component> components = new HashMap<String, Component>();

	/**
	 * Initializes the Lamp GUI
	 * 
	 * @param renderer
	 * @param inputAdapter
	 */
	public static void init(GUIRenderer renderer, GUIInputHandler inputAdapter) {

		/*
		 * Initialize the LampGUI
		 */
		LampGUI.inputHandler = inputAdapter;
		LampGUI.renderer = renderer;

	}

	/**
	 * Get the input adapter that holds information about mouse position
	 * 
	 * @return
	 */
	public static GUIInputHandler getInput() {
		return inputHandler;
	}

	/**
	 * The component counter
	 */
	private static int nameId = 0;

	/**
	 * 
	 * @param prefix
	 * @return
	 */
	public static String generateName(String prefix) {
		if (nameId > Integer.MAX_VALUE) {
			nameId = 0;
		}
		return prefix + (nameId++);
	}

	/**
	 * Get the renderer to use for the built in components
	 * 
	 * @return
	 */
	public static GUIRenderer getRenderer() {
		return renderer;
	}

	/**
	 * Set the GUIRenderer for this GUI
	 * 
	 * @param renderer
	 */
	public static void setGUIRenderer(GUIRenderer _renderer) {
		renderer = _renderer;
	}

	/**
	 * Add a component to this gui
	 * 
	 * @param component
	 * @throws GUIException
	 */
	public static void addComponent(Component component) throws GUIException {
		if (component == null) {
			throw new GUIException("Given component is null");
		}
		components.put(component.getName(), component);

	}

	/**
	 * Updates the input of the GUI
	 */
	private static void updateInput() {
		/**
		 * Check in with the adapter for events
		 */
		List<MouseEvent> mouseEvents = inputHandler.getMouseEvents();
		for (MouseEvent event : mouseEvents) {
			for (Component component : components.values()) {
				if (component.fireMouseEvent(event)) {
					break; // don't send the mouse event to any other components
				}
			}
		}
		
		inputHandler.update();
	}

	/**
	 * Update all the gui components
	 */
	public static void update() {

		updateInput();

		for (Component component : components.values()) {
			component.fireUpdate();
		}
	}

	/**
	 * Render all the gui components
	 */
	public static void render() {
		for (Component component : components.values()) {
			component.fireRender();
		}
	}
}
