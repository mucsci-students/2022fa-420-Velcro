package main.java.velcro.Model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class ZLineTest {

	@Test
	public void ZLineTest() {
		ZLine newLine = new ZLine(new Point2D.Float(0,0), new Point2D.Float(0,0), "source", "destination");
		assertEquals(0, newLine.x1);
		assertEquals(0, newLine.y1);
		assertEquals(0, newLine.x2);
		assertEquals(0, newLine.y2);
		assertEquals("", newLine.type);
		assertEquals(newLine.source, "source");
		assertEquals(newLine.destination, "destination");
	}
	
	@Test
	public void setTypeTest() {
		ZLine newLine = new ZLine(new Point2D.Float(0,0), new Point2D.Float(0,0), "source", "destination");
		assertEquals("", newLine.type);
		newLine.setType("string");
		assertEquals("string", newLine.type);
	}

	@Test
	public void getCenterTest() {
		ZLine newLine = new ZLine(new Point2D.Float(0,0), new Point2D.Float(0,0), "source", "destination");
		assertEquals(new Point2D.Double(0,0), newLine.getCenter());
	}
}
