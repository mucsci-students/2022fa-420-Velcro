/**
 * Filename: MovingAdapter.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Adapter class for extending MouseAdapter and handling mouse events.
 * 
 */


package main.java.velcro.Model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import main.java.velcro.Controller.*;
import main.java.velcro.main.*;
import main.java.velcro.View.*;

public class MovingAdapter extends MouseAdapter {

	public int x;
	public int y;
	public boolean test = false;

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		doMove(e);
	}

	
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		GUIFrame.myFrame.clickHandler(e, this, x, y, test);
	}

	public ZEllipse whatItHit(IteratorList arr) {
		return GUIFrame.myFrame.whatItHit(arr, x, y, test);
	}

	public void doMove(MouseEvent e) {

		int dx = e.getX() - x;
		int dy = e.getY() - y;

		GUIFrame.myFrame.doMoveGui(x, y, dx, dy, test);

		x += dx;
		y += dy;
	}
}