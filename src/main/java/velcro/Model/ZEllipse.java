package main.java.velcro.Model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


import main.*;

import javax.swing.JOptionPane;

public class ZEllipse extends Ellipse2D.Float {

	public String className;
	public List<ZEllipse> destinations;
	public Classes thisClass;

	public ZEllipse(float x, float y, float width, float height, String name, Instance thisInstance) {
		destinations = new ArrayList<ZEllipse>();
		setFrame(x, y, width, height);
		if (name == null || name.equals("")) {
			String input = JOptionPane.showInputDialog("Please enter a class name!");
			if (input == null || input.equals("")) {
				return;
			}
			this.className = input;
		} else {
			this.className = name;
		}
		thisClass = thisInstance.getClass(name);
	}
	
	public Point2D center() {
		return new Point2D.Float(this.x, this.y);
	}

	public boolean isHit(float x, float y) {

		return getBounds2D().contains(x, y);
	}

	public void addX(float x) {

		this.x += x;
		if (this.x < 0) {
			this.x = 0;
		}
		if (this.x >= MovingScalingEx.panelWidth - 100) {
			this.x = MovingScalingEx.panelWidth - 100;
		}
		thisClass.setLocation((int) this.x, (int) this.y);
	}

	public void addY(float y) {

		this.y += y;
		if (this.y >= MovingScalingEx.panelHeight - 150) {
			this.y = MovingScalingEx.panelHeight - 150;
		}
		if (this.y < 0) {
			this.y = 0;
		}
		thisClass.setLocation((int) this.x, (int) this.y);
	}

	public void addWidth(float w) {

		this.width += w;
	}

	public void addHeight(float h) {

		this.height += h;
	}

	public void setName(String input) {
		this.className = input;
	}

}
