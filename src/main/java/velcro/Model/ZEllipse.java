package main.java.velcro.Model;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.java.velcro.View.GUIFrame;
import main.java.velcro.main.DrawingGUI;

public class ZEllipse extends Rectangle.Float {

	public String className;
	public List<ZEllipse> destinations;
	public Classes thisClass;

	// Constructor.
	public ZEllipse(float x, float y, float width, float height, String name, Instance thisInstance) {
		destinations = new ArrayList<ZEllipse>();
		setFrame(x, y, width, height);
		if (name == null || name.equals("")) {
			String input = GUIFrame.prompt((int) this.x);
			if (input == null || input.equals("")) {
				return;
			}
			this.className = input;
		} else {
			this.className = name;
		}
		thisClass = thisInstance.getClass(name);
	}
	
	// Returns center of zellipse.
	public Point2D center() {
		return new Point2D.Float(this.x +thisClass.width*.5f, this.y);
	}

	// Unused method for determining if click was inside bounds.
	public boolean isHit(float x, float y) {
		return getBounds2D().contains(x, y);
	}

	// Movement method, for use with mouse adjuster.
	public void addX(float x) {
		this.x += x;
		if (this.x < 0) {
			this.x = 0;
		}
		if (this.x >= DrawingGUI.panelWidth - 100) {
			this.x = DrawingGUI.panelWidth - 100;
		}
		thisClass.setLocation((int) this.x, (int) this.y);
	}

	// Movement method, for use with mouse adjuster.
	public void addY(float y) {
		this.y += y;
		if (this.y >= DrawingGUI.panelHeight - 150) {
			this.y = DrawingGUI.panelHeight - 150;
		}
		if (this.y < 0) {
			this.y = 0;
		}
		thisClass.setLocation((int) this.x, (int) this.y);
	}

	// Optional scaling method, to resize class circle.
	public void addWidth(float w) {
		this.width += w;
	}

	// Optional scaling method, to resize class circle.
	public void addHeight(float h) {
		this.height += h;
	}

	// Method for changing class name attribute.
	public void setName(String input) {
		this.className = input;
	}

}
