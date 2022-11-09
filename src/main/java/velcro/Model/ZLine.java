/**
 * Filename: ZLine.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: ZLine object extends Line2D, for drawing relationships.
 * 
 */

package velcro.Model;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class ZLine extends Line2D.Float {
	public String type;
	public String source;
	public String destination;

	public void setType(String input) {
		this.type = input;
	}

	public ZLine(Point2D input1, Point2D input2, String source, String destination) {
		super(input1, input2);
		this.type = "";
		this.source = source;
		this.destination = destination;
	}

	public Point2D getCenter() {
		return new Point2D.Double((x1 + x2) / 2, (y1 + y2) / 2);
	}
}