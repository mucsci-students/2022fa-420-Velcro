package main.java.velcro.Model;

import java.awt.geom.Point2D;

import org.junit.Test;

import static org.junit.Assert.*;
import main.java.velcro.main.DrawingGUI;

public class ZEllipseTest {

	@Test
	public void ZTest() {
		Instance newInstance = new Instance();
		newInstance.addClass("name");
		ZEllipse newEllipse = new ZEllipse(10,20,30,40,"name", newInstance);
		assertEquals(10, newEllipse.x, 0.01);
		assertEquals(20, newEllipse.y, 0.01);
		assertEquals(30, newEllipse.width, 0.01);
		assertEquals(40, newEllipse.height, 0.01);
		assertEquals("name", newEllipse.className);
		assertEquals("name", newEllipse.thisClass.getName());
		// ZEllipse newEllipse1 = new ZEllipse(-100000,20,30,40, null, newInstance);
		// ZEllipse newEllipse2 = new ZEllipse(-200000,20,30,40, null, newInstance);
		// ZEllipse newEllipse3 = new ZEllipse(-300000,20,30,40, null, newInstance);
		// ZEllipse newEllipse4 = new ZEllipse(-100000,20,30,40, "", newInstance);
		// ZEllipse newEllipse5 = new ZEllipse(-200000,20,30,40, "", newInstance);
		// ZEllipse newEllipse6 = new ZEllipse(-300000,20,30,40, "", newInstance);
		// assertEquals(null, newEllipse1);
		// assertEquals(null, newEllipse2);
		// assertEquals(null, newEllipse4);
		// assertEquals(null, newEllipse5);
		// assertEquals("test", newEllipse3.thisClass.getName());
		// assertEquals("test", newEllipse6.thisClass.getName());
	}
	
	@Test
	public void centerTest() {
		Instance newInstance = new Instance();
		newInstance.addClass("name");
		ZEllipse newEllipse = new ZEllipse(0,0,30,40,"name", newInstance);
		assertEquals(new Point2D.Float(40,0), newEllipse.center());
	}
	
	@Test
	public void isHitTest() {
		Instance newInstance = new Instance();
		ZEllipse newEllipse = new ZEllipse(0,0,30,40,"name", newInstance);
		assertEquals(true, newEllipse.isHit(0,0));
		assertEquals(false, newEllipse.isHit(10000,10000));
	}
	
	@Test
	public void addXTest() {
		Instance newInstance = new Instance();
		newInstance.addClass("name");
		ZEllipse newEllipse = new ZEllipse(0,0,30,40,"name", newInstance);
		assertEquals(0, newEllipse.x, 0.01);
		newEllipse.addX(10);
		assertEquals(10, newEllipse.x, 0.01);
		newEllipse.addX(-100);
		assertEquals(0, newEllipse.x, 0.01);
		assertEquals(newEllipse.thisClass.point.x, newEllipse.x, 0.01);
		assertEquals(newEllipse.thisClass.point.y, newEllipse.y, 0.01);
		newEllipse.addX(1000000000);
		assertEquals(DrawingGUI.panelWidth-100, newEllipse.x, 0.01);
	}
	
	@Test
	public void addYTest() {
		Instance newInstance = new Instance();
		newInstance.addClass("name");
		ZEllipse newEllipse = new ZEllipse(0,0,30,40,"name", newInstance);
		assertEquals(0, newEllipse.y, 0.01);
		newEllipse.addY(10);
		assertEquals(10, newEllipse.y, 0.01);
		newEllipse.addY(-100);
		assertEquals(0, newEllipse.y, 0.01);
		assertEquals(newEllipse.thisClass.point.x, newEllipse.x, 0.01);
		assertEquals(newEllipse.thisClass.point.y, newEllipse.y, 0.01);
		newEllipse.addY(1000000000);
		assertEquals(DrawingGUI.panelHeight-150, newEllipse.y, 0.01);
	}
	
	@Test
	public void addWidthTest() {
		Instance newInstance = new Instance();
		ZEllipse newEllipse = new ZEllipse(0,0,30,40,"name", newInstance);
		assertEquals(30, newEllipse.width, 0.01);	
		newEllipse.addWidth(100);
		assertEquals(130, newEllipse.width, 0.01);	
	}
	
	@Test
	public void addHeightTest() {
		Instance newInstance = new Instance();
		ZEllipse newEllipse = new ZEllipse(0,0,30,40,"name", newInstance);
		assertEquals(40, newEllipse.height, 0.01);	
		newEllipse.addHeight(100);
		assertEquals(140, newEllipse.height, 0.01);	
	}
	
	@Test
	public void setNameTest() {
		Instance newInstance = new Instance();
		ZEllipse newEllipse = new ZEllipse(0,0,30,40,"name", newInstance);
		assertEquals("name", newEllipse.className);	
		newEllipse.setName("newname");
		assertEquals("newname", newEllipse.className);	
	}

}
