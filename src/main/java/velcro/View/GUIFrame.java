/**
 * Filename: GUIFrame.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: View portion of GUIFrame panel.
 * 
 */
package velcro.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import velcro.Controller.*;
import velcro.Model.*;

public class GUIFrame extends JPanel {

		public static MenuFrame thisFrame;
		public static Instance thisInstance;
		public static GUIFrame myFrame;
		public static Memento thisMemento;
		public static List<ZEllipse> pntLst = new ArrayList<ZEllipse>();
		public static List<ZLine> lnLst = new ArrayList<ZLine>();
		public static List<String> relLst = new ArrayList<String>();
		public static IteratorList arr = new IteratorList();
		public Graphics pubG;
		public int keycode = -1;
		public static Point2D source;
		public static String sourceName;

		// Constructor.
		public GUIFrame(Instance input) {
			thisInstance = input;
			myFrame = this;
		}
		
		// Access to repaint method.
		public static void draw() {
			myFrame.repaint();
		}

		// Sets access to frame panel.
		public void setFrame(MenuFrame input2) {
			thisFrame = input2;
			thisMemento = thisFrame.thisMemento;
			initUI(thisInstance, input2);
		}

		// Initialize panel.
		public void initUI(Instance input, MenuFrame input2) {
			thisFrame = input2;
			MovingAdapter ma = new MovingAdapter();
			addMouseMotionListener(ma);
			addMouseListener(ma);
			thisMemento.add(input);
		}

		// Access methods for frame.
		public static Point2D center(Rectangle2D bounds) {
			return new Point2D.Double(bounds.getCenterX(), bounds.getCenterY());
		}

		// Access methods for frame.
		public static double angleBetween(Shape from, Shape to) {
			return angleBetween(center(from.getBounds2D()), center(to.getBounds2D()));
		}

		// Access methods for frame.
		public static double angleBetween(Point2D from, Point2D to) {
			double x = from.getX();
			double y = from.getY();
			double deltaX = to.getX() - x;
			double deltaY = to.getY() - y;
			double rotation = -Math.atan2(deltaX, deltaY);
			rotation = Math.toRadians(Math.toDegrees(rotation) + 180);
			return rotation;
		}

		// Access methods for frame.
		public static Point2D getPointOnCircle(Shape shape, double radians) {
			Rectangle2D bounds = shape.getBounds();
			Point2D point = center(bounds);
			return getPointOnCircle(point, radians, Math.max(bounds.getWidth(), bounds.getHeight()) / 2d);
		}

		// Access methods for frame.
		public static Point2D getPointOnCircle(Point2D center, double radians, double radius) {
			double x = center.getX();
			double y = center.getY();
			radians = radians - Math.toRadians(90.0);
			double xPosy = Math.round((float) (x + Math.cos(radians) * radius));
			double yPosy = Math.round((float) (y + Math.sin(radians) * radius));
			return new Point2D.Double(xPosy, yPosy);

		}
		
		// Draw lines and arrows method.
		public static ZLine addLine(ZEllipse ell1, ZEllipse ell2, Graphics2D g2d, String type, String source,
				String destination) {
			if (ell1 == null || ell2 == null || ell1.equals(ell2))
				return null;
			// Draw relationship line
			double from = angleBetween(ell1, ell2);
			double to = angleBetween(ell2, ell1);
			Point2D pointFrom = getPointOnCircle(ell1, from);
			Point2D pointTo = getPointOnCircle(ell2, to);
			// Draw relationship type
			g2d.setColor(Color.darkGray);
			ZLine line = new ZLine(pointFrom, pointTo, source, destination);
			g2d.drawString(type, (int) line.getCenter().getX() - 40, (int) line.getCenter().getY());
			g2d.setColor(Color.RED);
			// Draw arrowheads
			ArrowHead arrowHead = new ArrowHead();
			AffineTransform at = AffineTransform
					.getTranslateInstance(pointTo.getX() - (arrowHead.getBounds2D().getWidth() / 2d), pointTo.getY());
			at.rotate(from, arrowHead.getBounds2D().getCenterX(), 0);
			arrowHead.transform(at);
			g2d.draw(arrowHead);
			return line;
		}

		// Required override.
		@Override
		public void paintComponent(Graphics g) {
			pubG = g;
			super.paintComponent(g);
			GUIController.doDrawing(g);
		}
}
