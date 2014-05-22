package com.meanworks.lampgui;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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
	 * The render order of the components
	 */
	private static LinkedList<Component> renderOrder = new LinkedList<Component>();

	/**
	 * Add and remove queues
	 */
	private static LinkedList<Component> addQueue = new LinkedList<Component>();
	private static LinkedList<Component> removeQueue = new LinkedList<Component>();
	private static LinkedList<Component> toFrontQueue = new LinkedList<Component>();
	private static LinkedList<Component> toBackQueue = new LinkedList<Component>();

	/**
	 * The focus component
	 */
	private static Component focusComponent = null;

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
	 * The focus component
	 * 
	 * @return
	 */
	public static Component getFocus() {
		return focusComponent;
	}

	/**
	 * Set the focus component
	 * 
	 * @param component
	 */
	public static void setFocus(Component component) {
		focusComponent = component;
	}

	/**
	 * Remove the component from all registries
	 * 
	 * @param component
	 */
	public static void dispose(Component component) {
		if (component == null) {
			return;
		}
		removeQueue.add(component);
	}

	/**
	 * Move the given component to the front of the render list
	 * 
	 * @param component
	 */
	public static void toFront(Component component) {
		if (component == null) {
			return;
		}
		toFrontQueue.add(component);
	}

	/**
	 * Move the given component to the back of the render list
	 * 
	 * @param component
	 */
	public static void toBack(Component component) {
		if (component == null) {
			return;
		}
		toBackQueue.add(component);
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
		addQueue.add(component);
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
			if (focusComponent != null) {
				if (focusComponent.fireMouseEvent(event)) {
					continue;
				}
			}
			ListIterator<Component> it = renderOrder.listIterator(renderOrder
					.size());
			while (it.hasPrevious()) {
				Component component = it.previous();
				if (component.fireMouseEvent(event)) {
					System.out.println("Component: " + component.getName()
							+ " ate the message.");
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
		// Complete queue updates
		if (toFrontQueue.size() > 0) {
			for (Component component : toFrontQueue) {
				renderOrder.remove(component);
				renderOrder.addLast(component);
			}
			toFrontQueue.clear();
		}
		if (toBackQueue.size() > 0) {
			for (Component component : toBackQueue) {
				renderOrder.remove(component);
				renderOrder.addFirst(component);
			}
			toBackQueue.clear();
		}
		if (addQueue.size() > 0) {
			for (Component component : addQueue) {
				components.put(component.getName(), component);
				renderOrder.addLast(component);
			}
			addQueue.clear();
		}
		if (removeQueue.size() > 0) {
			for (Component component : removeQueue) {
				renderOrder.remove(component);
				components.remove(component.getName());
			}
			removeQueue.clear();
		}

		// Draw some debug information
		if (renderer != null)
			renderer.drawString(
					"Focus: " + (focusComponent != null ? focusComponent
							.getName() : "null"), 10, 25, Color.WHITE);

		// Render
		for (Component component : renderOrder) {
			component.fireRender(renderer);
		}
	}
}
