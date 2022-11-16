package main.java.velcro.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ZRectangleTest {

	@Test
	public void ZRectangleTest() {
		ZRectangle newRect = new ZRectangle(10,20,30,40);
		assertEquals(10, newRect.x);
		assertEquals(20, newRect.y);
		assertEquals(30, newRect.width);
		assertEquals(40, newRect.height);
	}
	
	@Test
	public void setHeightTest() {
		ZRectangle newRect = new ZRectangle(10,20,30,40);
		assertEquals(40, newRect.height);
		newRect.setHeight(80);
		assertEquals(80, newRect.height);
	}
	
	@Test
	public void setSizeTest() {
		ZRectangle newRect = new ZRectangle(10,20,30,40);
		newRect.setSize(50, 60);
		assertEquals(10, newRect.x);
		assertEquals(20, newRect.y);
		assertEquals(50, newRect.width);
		assertEquals(60, newRect.height);
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
		assertEquals(10, newRect.x);
		newRect.addX(10);
		assertEquals(20, newRect.x);
	}
	
	@Test
	public void addYTest() {
		ZRectangle newRect = new ZRectangle(10,20,30,40);
		assertEquals(20, newRect.y);
		newRect.addY(10);
		assertEquals(30, newRect.y);
	}
	
	@Test
	public void addWidthTest() {
		ZRectangle newRect = new ZRectangle(10,20,30,40);
		assertEquals(30, newRect.width);
		newRect.addWidth(10);
		assertEquals(40, newRect.width);
	}
	
	
	@Test
	public void addHeightTest() {
		ZRectangle newRect = new ZRectangle(10,20,30,40);
		assertEquals(40, newRect.height);
		newRect.addWidth(10);
		assertEquals(50, newRect.height);
	}
}
