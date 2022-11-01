/**
 * Filename: Classes.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Classes class.
 * 
 */

package main.java.velcro.Model;

import java.awt.geom.Path2D;

public class ArrowHead extends Path2D.Double {

	public ArrowHead() {
		int size = 10;
		moveTo(0, size);
		lineTo(size / 2, 0);
		lineTo(size, size);
	}

}
