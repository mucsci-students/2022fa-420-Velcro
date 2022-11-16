package main.java.velcro.Model;

import org.junit.Test;

import static org.junit.Assert.*;
public class ZRectangleTest {

	@Test
	public void setHeightTest() {
		ZRectangle newRect = new ZRectangle(10,20,30,40);
		assertEquals(10, newRect.x, 0.01);
		assertEquals(20, newRect.y, 0.01);
		assertEquals(30, newRect.width, 0.01);
		assertEquals(40, newRect.height, 0.01);
		newRect.setHeight(80);
		assertEquals(80, newRect.height, 0.01);
	}
	
	@Test
	public void setSizeTest() {
		ZRectangle newRect = new ZRectangle(10,20,30,40);
		newRect.setSize(50, 60);
		assertEquals(10, newRect.x, 0.01);
		assertEquals(20, newRect.y, 0.01);
		assertEquals(50, newRect.width, 0.01);
		assertEquals(60, newRect.height, 0.01);
	}
	
	@Test
	public void isHitTest() {
		ZRectangle newRect = new ZRectangle(10,10,10,10);
		assertEquals(true, newRect.isHit(15, 15));
		assertEquals(false, newRect.isHit(150, 150));
	}
	
	@Test
	public void addXTest() {
		ZRectangle newRect = new ZRectangle(10,20,30,40);
		assertEquals(10, newRect.x, 0.01);
		newRect.addX(10);
		assertEquals(20, newRect.x, 0.01);
	}
	
	@Test
	public void addYTest() {
		ZRectangle newRect = new ZRectangle(10,20,30,40);
		assertEquals(20, newRect.y, 0.01);
		newRect.addY(10);
		assertEquals(30, newRect.y, 0.01);
	}
	
	@Test
	public void addWidthTest() {
		ZRectangle newRect = new ZRectangle(10,20,30,40);
		assertEquals(30, newRect.width, 0.01);
		newRect.addWidth(10);
		assertEquals(40, newRect.width, 0.01);
	}
	
	
	@Test
	public void addHeightTest() {
		ZRectangle newRect = new ZRectangle(10,20,30,40);
		assertEquals(40, newRect.height, 0.01);
		newRect.addWidth(10);
		assertEquals(50, newRect.height, 0.01);
	}
}
