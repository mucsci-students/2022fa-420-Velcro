package main.java.velcro.Model;

import org.junit.Test;

import main.java.velcro.View.GUIFrame;
import main.java.velcro.View.MenuFrame;
import main.java.velcro.main.DrawingGUI;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import java.awt.event.MouseEvent;


public class MovingAdapterTest {

	@Test
	public void movingAdapterTest() {
		
		Instance thisInstance = new Instance();
		DrawingGUI ex = new DrawingGUI(thisInstance);
		GUIFrame newFrame = new GUIFrame(thisInstance);
//		ex.resize();
//		MenuFrame menuFrame = new MenuFrame(thisInstance, ex, 1000);
//		ex.setFrame(menuFrame);
//		menuFrame.firstLoad();
//		
		MouseEvent testClick = new MouseEvent(newFrame, 0, 0, 0, 0, 0, 0, 0, 1, false, 1);
		MouseEvent testClick2 = new MouseEvent(newFrame, 0, 0, 0, 100, 100, 0, 0, 1, false, 1);
		MouseEvent testClick3 = new MouseEvent(newFrame, 0, 0, 0, 100, 100, 0, 0, 1, false, 3);
		MouseEvent testClick4 = new MouseEvent(newFrame, 0, 0, 0, 0, 0, 0, 0, 1, false, 3);
		MouseEvent testClick5 = new MouseEvent(newFrame, 0, 0, 0, 100, 100, 0, 0, 2, false, 3);
		MouseEvent testClick6 = new MouseEvent(newFrame, 0, 0, 0, 0, 0, 0, 0, 2, false, 3);

		
		MovingAdapter test1 = new MovingAdapter();
		test1.test = true;
		
		
		test1.mousePressed(testClick);
		test1.mouseDragged(testClick);
		test1.mouseClicked(testClick);
		test1.doMove(testClick);
		test1.whatItHit(null);
		
	}

}
