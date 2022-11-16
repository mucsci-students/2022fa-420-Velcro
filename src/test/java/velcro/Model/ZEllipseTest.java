package main.java.velcro.Model;

import java.awt.geom.Point2D;

import org.junit.Test;

import static org.junit.Assert.*;
import main.java.velcro.main.DrawingGUI;

public class ZEllipseTest {

	@Test
	public void ZTest() {
		Instance newInstance = new Instance();
		newInstance.addClass("class1");
		ZEllipse newEllipse = new ZEllipse(10,20,30,40,"name", newInstance);
		assertEquals(10, newEllipse.x);
		assertEquals(20, newEllipse.y);
		assertEquals(30, newEllipse.width);
		assertEquals(40, newEllipse.height);
		assertEquals(null, newEllipse.destinations);
		assertEquals("name", newEllipse.className);
		assertEquals("class1", newEllipse.thisClass.getName());
		ZEllipse newEllipse1 = new ZEllipse(-100000,20,30,40, null, newInstance);
		ZEllipse newEllipse2 = new ZEllipse(-200000,20,30,40, null, newInstance);
		ZEllipse newEllipse3 = new ZEllipse(-300000,20,30,40, null, newInstance);
		ZEllipse newEllipse4 = new ZEllipse(-100000,20,30,40, "", newInstance);
		ZEllipse newEllipse5 = new ZEllipse(-200000,20,30,40, "", newInstance);
		ZEllipse newEllipse6 = new ZEllipse(-300000,20,30,40, "", newInstance);
		assertEquals(null, newEllipse1);
		assertEquals(null, newEllipse2);
		assertEquals(null, newEllipse4);
		assertEquals(null, newEllipse5);
		assertEquals("test", newEllipse3.thisClass.getName());
		assertEquals("test", newEllipse6.thisClass.getName());
	}
	
	@Test
	public void centerTest() {
		Instance newInstance = new Instance();
		ZEllipse newEllipse = new ZEllipse(0,0,30,40,"name", newInstance);
		assertEquals(new Point2D.Float(0,0), newEllipse.center());
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
		ZEllipse newEllipse = new ZEllipse(0,0,30,40,"name", newInstance);
		assertEquals(0, newEllipse.x);
		newEllipse.addX(10);
		assertEquals(10, newEllipse.x);
		newEllipse.addX(-100);
		assertEquals(0, newEllipse.x);
		assertEquals(newEllipse.thisClass.point.x, newEllipse.x);
		assertEquals(newEllipse.thisClass.point.y, newEllipse.y);
		newEllipse.addX(1000000000);
		assertEquals(DrawingGUI.panelWidth-100, newEllipse.x);
	}
	
	@Test
	public void addYTest() {
		Instance newInstance = new Instance();
		ZEllipse newEllipse = new ZEllipse(0,0,30,40,"name", newInstance);
		assertEquals(0, newEllipse.y);
		newEllipse.addX(10);
		assertEquals(10, newEllipse.y);
		newEllipse.addX(-100);
		assertEquals(0, newEllipse.y);
		assertEquals(newEllipse.thisClass.point.x, newEllipse.x);
		assertEquals(newEllipse.thisClass.point.y, newEllipse.y);
		newEllipse.addX(1000000000);
		assertEquals(DrawingGUI.panelHeight-150, newEllipse.y);
	}
	
	@Test
	public void addWidthTest() {
		Instance newInstance = new Instance();
		ZEllipse newEllipse = new ZEllipse(0,0,30,40,"name", newInstance);
		assertEquals(30, newEllipse.width);	
		newEllipse.addWidth(100);
		assertEquals(130, newEllipse.width);	
	}
	
	@Test
	public void addHeightTest() {
		Instance newInstance = new Instance();
		ZEllipse newEllipse = new ZEllipse(0,0,30,40,"name", newInstance);
		assertEquals(40, newEllipse.height);	
		newEllipse.addWidth(100);
		assertEquals(140, newEllipse.height);	
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
