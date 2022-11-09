/**
 * Filename: MovingAdapter.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Adapter class for extending MouseAdapter and handling mouse events.
 * 
 */


package velcro.Model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import velcro.Controller.*;
import velcro.main.*;
import velcro.View.*;

public class MovingAdapter extends MouseAdapter {

	private int x;
	private int y;

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		doMove(e);
	}

	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();

		
		if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
			if (whatItHit(GUIFrame.arr) != null) {
				GUIFrame.thisMemento.add(GUIFrame.thisInstance);
				ZEllipse hit = whatItHit(GUIFrame.arr);
				String newName;
				try {
					newName = JOptionPane.showInputDialog("Please enter a new class name!", hit.className);
					if (newName != null) {
						
						try {
							String testClass = GUIFrame.thisInstance.getClass(newName).getName();
							JOptionPane.showInternalMessageDialog(null,
									"Class name already in use!", "Renaming Class",
									JOptionPane.ERROR_MESSAGE); //herehere
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

			if (whatItHit(GUIFrame.arr) == null) {
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
				ZEllipse hit = whatItHit(GUIFrame.arr);
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
			if (whatItHit(GUIFrame.arr) == null) {
				return;
			}
			GUIFrame.thisMemento.add(GUIFrame.thisInstance);
			ZEllipse hit = whatItHit(GUIFrame.arr);
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

	public ZEllipse whatItHit(IteratorList arr) {
		IteratorList.Iterator iter = arr.getIterator();
		for (iter.first(); !iter.isDone(); iter.next()) {
			if (iter.currentValue().isHit(x, y)) {
				return iter.currentValue();
			}
		}
		return null;
	}

	private void doMove(MouseEvent e) {

		int dx = e.getX() - x;
		int dy = e.getY() - y;

		IteratorList.Iterator iter = GUIFrame.arr.getIterator();
		for (iter.first(); !iter.isDone(); iter.next()) {
			ZEllipse thisOne = iter.currentValue();
			if (thisOne.isHit(x, y)) {
				thisOne.addX(dx);
				thisOne.addY(dy);
				GUIFrame.draw();;
			}
		}

		x += dx;
		y += dy;
	}
}