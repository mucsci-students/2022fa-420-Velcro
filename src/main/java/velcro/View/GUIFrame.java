/**
 * Filename: GUIFrame.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: View portion of GUIFrame panel.
 * 
 */
package main.java.velcro.View;

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

import main.java.velcro.Controller.*;
import main.java.velcro.Model.*;

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
		public static double angleBetween(ZEllipse from, ZEllipse to) {
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
			return getPointOnCircle(point, radians, Math.max(bounds.getWidth(), bounds.getHeight()) / 1.25d);
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
			g2d.drawString(type, (int) line.getCenter().getX() -40, (int) line.getCenter().getY());
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

		// Dialog moved here for testing purposes.
		public static String prompt(int test) {
			if (test == -100000) {
				return "";
			} else if (test == -200000){
				return null;
			} else if (test == -300000){
				return "test";
			} else {
				String input = JOptionPane.showInputDialog("Please enter a class name!");
				return input;
			}
		}
		
		// Handles visual aspects of mouseclick.
		public void clickHandler(MouseEvent e, MovingAdapter ma, int x, int y, boolean test) {
			if (test)
				return;
			if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				if (ma.whatItHit(GUIFrame.arr) != null) {
					GUIFrame.thisMemento.add(GUIFrame.thisInstance);
					ZEllipse hit = ma.whatItHit(GUIFrame.arr);
					String newName;
					try {
						newName = JOptionPane.showInputDialog("Please enter a new class name!", hit.className);
						if (newName != null) {
							
							try {
								String testClass = GUIFrame.thisInstance.getClass(newName).getName();
								JOptionPane.showInternalMessageDialog(null,
										"Class name already in use!", "Renaming Class",
										JOptionPane.ERROR_MESSAGE);
							} catch (NullPointerException e3) {
								Classes changed = GUIFrame.thisInstance.getClass(hit.className);
								changed.rename(hit.className, newName);
								GUIFrame.thisFrame.classModel.removeElement(hit.className);
								hit.className = newName;
								GUIFrame.thisFrame.classModel.addElement(newName);
								hit.setName(newName);
								changed = GUIFrame.thisInstance.getClass(newName);
								GUIFrame.thisInstance.setHighlight(changed);
							}
						}
					} catch (NullPointerException e2) {
					}
					GUIFrame.draw();
					return;
				}

			} else if (e.getButton() == MouseEvent.BUTTON3 || e.isShiftDown() ) {
				if (!GUIController.missAllLines(GUIFrame.lnLst, 0, (double) x, (double) y)) {
					GUIFrame.thisMemento.add(GUIFrame.thisInstance);
					ZLine line = GUIController.lineItHit(GUIFrame.lnLst, x, y);
					if (JOptionPane.showConfirmDialog(null, "Delete Relationship?", "Delete Relationship",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						GUIFrame.thisInstance.getClass(line.source).removeRelationship(line.source, line.destination);
						GUIFrame.thisInstance.getClass(line.destination).removeRelationship(line.source, line.destination);
						GUIFrame.lnLst.remove(line);
						GUIFrame.draw();
					} else {
						return;
					}

					return;
				}

				if (ma.whatItHit(GUIFrame.arr) == null) {
					GUIFrame.thisMemento.add(GUIFrame.thisInstance);
					ZEllipse newEllipse = new ZEllipse(x - 40, y - 40, 80, 80, "", GUIFrame.thisInstance);
					if (newEllipse.className != null && !newEllipse.className.equals("")) {
						GUIFrame.arr.add(newEllipse);

						GUIFrame.thisInstance.addClass(newEllipse.className);
						GUIFrame.thisInstance.getClass(newEllipse.className).setLocation(x - 40, y - 40);
						GUIFrame.thisFrame.classModel.addElement(newEllipse.className);
					}
					GUIFrame.draw();
				} else {
					ZEllipse hit = ma.whatItHit(GUIFrame.arr);
					GUIFrame.thisMemento.add(GUIFrame.thisInstance);
					GUIFrame.pntLst.add(hit);
					if (GUIFrame.source == null) {
						GUIFrame.source = hit.center();
						GUIFrame.sourceName = hit.className;
					} else {
						Object[] types = { "Aggregation", "Composition", "Inheritance", "Realization" };
						String n = "";
						n = (String) JOptionPane.showInputDialog(null, "Relationship Type?", "Set Relationship Type",
								JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
						if (n == null || n.equals("")) {
						} else {
							GUIFrame.thisInstance.getClass(hit.className).addRelationship(GUIFrame.sourceName, hit.className, n);
							GUIFrame.thisInstance.getClass(GUIFrame.sourceName).addRelationship(GUIFrame.sourceName, hit.className, n);
						}
						GUIFrame.source = null;
					}
					GUIFrame.draw();
				}
				return;
			} else if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
				if (ma.whatItHit(GUIFrame.arr) == null) {
					return;
				}
				GUIFrame.thisMemento.add(GUIFrame.thisInstance);
				ZEllipse hit = ma.whatItHit(GUIFrame.arr);
				if (GUIFrame.thisInstance.getClass(hit.className) != null) {
					GUIFrame.thisFrame.comboBox.setSelectedItem(GUIFrame.thisInstance.getClass(hit.className).getName());
					GUIFrame.thisInstance.setHighlight(GUIFrame.thisInstance.getClass(hit.className));
				}

				Classes thisClass;
				if (GUIFrame.thisInstance.getClass((String) GUIFrame.thisFrame.comboBox.getSelectedItem()) != null) {
					GUIFrame.thisFrame.fieldModel.removeAllElements();
					thisClass = GUIFrame.thisInstance.getClass((String) GUIFrame.thisFrame.comboBox.getSelectedItem());
					for (int i = 0; i < thisClass.fieldList.size(); i++) {
						GUIFrame.thisFrame.fieldModel.addElement(thisClass.fieldList.get(i).getName());
					}
				}

				if (GUIFrame.thisInstance.getClass((String) GUIFrame.thisFrame.comboBox.getSelectedItem()) != null) {
					GUIFrame.thisFrame.relationshipsModel.removeAllElements();
					thisClass = GUIFrame.thisInstance.getClass((String) GUIFrame.thisFrame.comboBox.getSelectedItem());
					for (int i = 0; i < thisClass.relationshipList.size(); i++) {

						if (!thisClass.relationshipList.get(i).getDestination().equals(thisClass.getName())) {
							GUIFrame.thisFrame.relationshipsModel.addElement(thisClass.relationshipList.get(i).getDestination());
						}
					}
				}
				GUIFrame.draw();
			}

		}
		
		// Handles visual aspect of DoMove
		public void doMoveGui(int x, int y, int dx, int dy, boolean test) {
			if (test)
				return;
			IteratorList.Iterator iter = GUIFrame.arr.getIterator();
			for (iter.first(); !iter.isDone(); iter.next()) {
				ZEllipse thisOne = iter.currentValue();
				if (thisOne.isHit(x, y)) {
					thisOne.addX(dx);
					thisOne.addY(dy);
					GUIFrame.draw();
				}
			}
		}
		
		// Determine what class was clicked on.
		public ZEllipse whatItHit(IteratorList arr, int x, int y, boolean test) {
			if (test)
				return null;
			IteratorList.Iterator iter = arr.getIterator();
			for (iter.first(); !iter.isDone(); iter.next()) {
				if (iter.currentValue().isHit(x, y)) {
					return iter.currentValue();
				}
			}
			return null;
		}

		// Required override.
		@Override
		public void paintComponent(Graphics g) {
			pubG = g;
			super.paintComponent(g);
			GUIController.doDrawing(g);
		}
}
