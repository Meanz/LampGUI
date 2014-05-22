package com.meanworks.lampgui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

import com.meanworks.lampgui.event.MouseClickEvent;
import com.meanworks.lampgui.event.MouseEvent;
import com.meanworks.lampgui.event.MouseMoveEvent;

public abstract class Component {

	/**
	 * The position of the component
	 */
	private int x, y;

	/**
	 * The dimensions of the component
	 */
	private int width, height;

	/**
	 * The name of the component
	 */
	private String name;

	/**
	 * The parent of this component
	 */
	public Component parent;

	/**
	 * The children of this component
	 */
	private HashMap<String, Component> children = new HashMap<String, Component>();

	/**
	 * The render order of this component's children
	 */
	private LinkedList<Component> renderOrder = new LinkedList<Component>();

	/**
	 * Whether this component is disposed or not
	 */
	private boolean disposed = false;

	/**
	 * Construct a new Component
	 * 
	 * @param name
	 */
	public Component(String name, int x, int y, int width, int height) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.parent = null;
	}

	/**
	 * Construct a new Component
	 * 
	 * @param name
	 */
	public Component(String name, int width, int height) {
		this(name, 0, 0, width, height);
	}

	/**
	 * Construct a new component
	 * 
	 * @param name
	 */
	public Component(String name) {
		this(name, 0, 0, 0, 0);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Component(int x, int y) {
		this(LampGUI.generateName("comp_"));
	}

	/**
	 * Whether this component is disposed or not
	 * 
	 * @return
	 */
	public boolean isDisposed() {
		return disposed;
	}

	/**
	 * Remove the component from all registries
	 * 
	 * @param component
	 */
	public void dispose(Component component) {
		if (component == null) {
			return;
		}
		renderOrder.remove(component);
		children.remove(component.getName());
	}

	/**
	 * Dispose of this component
	 */
	public void dispose() {
		disposed = true;
		if (parent != null) {
			parent.dispose(this);
		} else {
			LampGUI.dispose(this);
		}
	}

	/**
	 * Move this component to the back of the render order
	 * 
	 * @param component
	 */
	public void toBack(Component component) {
		if (component == null) {
			return;
		}
		renderOrder.remove(component);
		renderOrder.addFirst(component);
	}

	/**
	 * Move the given component to the front of the render order
	 * 
	 * @param component
	 */
	public void toFront(Component component) {
		if (component == null) {
			return;
		}
		renderOrder.remove(component);
		renderOrder.addLast(component);
	}

	/**
	 * Send this component to the front of the render order
	 */
	public void toFront() {
		if (parent == null) {
			LampGUI.toFront(this);
		} else {
			parent.toFront(this);
		}
	}

	/**
	 * Send this component to the back of the render order
	 */
	public void toBack() {
		if (parent == null) {
			LampGUI.toBack(this);
		} else {
			parent.toBack(this);
		}
	}

	/**
	 * Get the parent of this component
	 * 
	 * @return
	 */
	public Component getParent() {
		return parent;
	}

	/**
	 * Adds a child to this component
	 * 
	 * @param child
	 * @throws GUIException
	 */
	public void addChild(Component child) throws GUIException {
		if (child == null) {
			throw new GUIException("Child can not be null!");
		}
		child.parent = this;
		children.put(child.getName(), child);
		renderOrder.add(child);
	}

	/**
	 * Get the name of this component
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the position of this component
	 * 
	 * @return
	 */
	public Point getPosition() {
		return new Point(x, y);
	}

	/**
	 * Get the X coordinate of this component
	 * 
	 * @return
	 */
	public int getX() {
		int _x = 0;
		Component p = parent;
		while (p != null) {
			_x += p.getAbsX();
			p = p.parent;
		}
		return _x + x;
	}

	/**
	 * Get the Y coordinate of this component
	 * 
	 * @return
	 */
	public int getY() {
		int _y = 0;
		Component p = parent;
		while (p != null) {
			_y += p.getAbsY();
			p = p.parent;
		}
		return _y + y;
	}

	/**
	 * Get the absolute x value
	 * 
	 * @return
	 */
	public int getAbsX() {
		return x;
	}

	/**
	 * Get the absolute x value
	 * 
	 * @return
	 */
	public int getAbsY() {
		return y;
	}

	/**
	 * Set the position of this component
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Set the position of this component
	 * 
	 * @param p
	 */
	public void setPosition(Point p) {
		setPosition(p.x, p.y);
	}

	/**
	 * Get the size of this component
	 * 
	 * @return
	 */
	public Dimension getSize() {
		return new Dimension(width, height);
	}

	/**
	 * Get the width of this component
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the height of this component
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the size of this component
	 * 
	 * @param width
	 * @param height
	 */
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Set the size of this component
	 * 
	 * @param d
	 */
	public void setSize(Dimension d) {
		setSize(d.width, d.height);
	}

	/**
	 * Check whether the given mouse event happened inside this component
	 * 
	 * @param mouseEvent
	 * @return
	 */
	public boolean isInside(MouseEvent mouseEvent) {
		if (mouseEvent.getX() >= getX()
				&& mouseEvent.getX() <= getX() + getWidth()) {
			if (mouseEvent.getY() >= getY()
					&& mouseEvent.getY() <= getY() + getHeight()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Handle mouse move events
	 * 
	 * @param mouseEvent
	 * @return
	 */
	public boolean onMouseMove(MouseMoveEvent mouseEvent) {
		return false; // Tell the system that we didn't do anything with this
						// mouse event
	}

	/**
	 * Handle mouse click events
	 * 
	 * @param mouseEvent
	 * @return
	 */
	public boolean onMouseClick(MouseClickEvent mouseEvent) {
		return false; // Tell the system that we didn't do anything with this
						// mouse event
	}

	/**
	 * Handle mouse events
	 * 
	 * @param mouseEvent
	 * @return
	 */
	public boolean onMouseEvent(MouseEvent mouseEvent) {
		if (mouseEvent instanceof MouseClickEvent) {
			return onMouseClick((MouseClickEvent) mouseEvent);
		} else if (mouseEvent instanceof MouseMoveEvent) {
			return onMouseMove((MouseMoveEvent) mouseEvent);
		} else {
			return false;
		}
	}

	/**
	 * Fire a mouse event
	 * 
	 * @param mouseEvent
	 */
	public final boolean fireMouseEvent(MouseEvent mouseEvent) {
		ListIterator<Component> it = renderOrder.listIterator(renderOrder
				.size());
		while (it.hasPrevious()) {
			Component child = it.previous();
			if (child.fireMouseEvent(mouseEvent)) {
				return true;
			}
		}
		if (onMouseEvent(mouseEvent)) {
			return true;
		}
		return false;
	}

	/**
	 * Update this component and all it's sub components
	 */
	public final void fireUpdate() {
		update();
		for (Component child : children.values()) {
			child.fireUpdate();
		}
	}

	/**
	 * Render this component and all it's sub components
	 */
	public final void fireRender(GUIRenderer renderer) {
		render(renderer);
		for (Component child : renderOrder) {
			child.fireRender(renderer);
		}
	}

	/**
	 * Updates the component
	 */
	public abstract void update();

	/**
	 * Renders the component
	 */
	public abstract void render(GUIRenderer renderer);

}
