/**
 * Filename: GUIController.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Controller for the visual GUI.
 * 
 */


package velcro.Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

import velcro.Model.*;
import velcro.View.*;

public class GUIController {


	/**
		GUIFrame methods.
	**/
	
	// Retrieve ZEllipse given input name.
	public static ZEllipse getEll(String name) {
		if (GUIFrame.arr.size() > 0) {
			IteratorList.Iterator first = GUIFrame.arr.getIterator();
			for (first.first(); !first.isDone(); first.next())
				if (first.currentValue().className.equals(name))
					return first.currentValue();
		}
		return null;
	}
	
	// Determines if a click misses all lines.
	public static boolean missAllLines(List<ZLine> arr, int count, double x, double y) {
		if (count >= arr.size())
			return true;
		if (arr.get(count).ptLineDist(x, y) < 1)
			return false;
		return missAllLines(arr, count + 1, x, y);
	}
	
	// Returns hit line on click.
	public static ZLine lineItHit(List<ZLine> arr, double x, double y) {
		for (int i = 0; i < arr.size(); i++)
			if (arr.get(i).ptLineDist(x, y) < 1)
				return arr.get(i);
		return null;
	}
	
	// Perform drawing.
	public static void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("Serif", Font.BOLD, 20);
		g2d.setFont(font);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		GUIFrame.arr.clear();
		for (int i = 0; i < GUIFrame.thisInstance.classList.size(); i++) {
			GUIFrame.arr.add(new ZEllipse(GUIFrame.thisInstance.classList.get(i).point.x, GUIFrame.thisInstance.classList.get(i).point.y, 80, 80,
					GUIFrame.thisInstance.classList.get(i).getName(), GUIFrame.thisInstance));
		}
		IteratorList.Iterator iter = GUIFrame.arr.getIterator();
		for (iter.first(); !iter.isDone(); iter.next()) {
			g2d.setPaint(new Color(0, 0, 0));
			if (iter.currentValue() != null && iter.currentValue().className != null) {
				g2d.drawString(iter.currentValue().className, (int) iter.currentValue().getX() + 10,
						(int) iter.currentValue().getY() + 100);

			}
			
			if (GUIFrame.thisInstance.highlight != null) {
				if (iter.currentValue().className.equals(GUIFrame.thisInstance.highlight.getName())) {
					g2d.setPaint(new Color(255, 0, 0));
					g2d.draw(new ZRectangle((float) iter.currentValue().getX() -2, (float) iter.currentValue().getY() -2, 83,
							83));
				}
			}
			g2d.setPaint(new Color(0, 0, 200));
			g2d.fill(iter.currentValue());
		}
		GUIFrame.lnLst.clear();
		for (int i = 0; i < GUIFrame.thisInstance.classList.size(); i++) {
			for (int j = 0; j < GUIFrame.thisInstance.classList.get(i).relationshipList.size(); j++) {
				ZEllipse source = GUIController.getEll(GUIFrame.thisInstance.classList.get(i).relationshipList.get(j).getSource());
				ZEllipse destination = GUIController.getEll(GUIFrame.thisInstance.classList.get(i).relationshipList.get(j).getDestination());
				String type = GUIFrame.thisInstance.classList.get(i).relationshipList.get(j).getType();
				g2d.setColor(Color.RED);
				ZLine line = GUIFrame.addLine(source, destination, g2d, type,
						GUIFrame.thisInstance.classList.get(i).relationshipList.get(j).getSource(),
						GUIFrame.thisInstance.classList.get(i).relationshipList.get(j).getDestination());
				if (source == null || destination == null || source.equals(destination))
					continue;
				g2d.draw(line);
				GUIFrame.lnLst.add(line);
			}
		}

		if (GUIFrame.source != null) {
			g2d.setPaint(new Color(255, 0, 0));
			g2d.drawString("Source", (int) GUIFrame.source.getX() + 15, (int) GUIFrame.source.getY() + 47);
		}
		g2d.dispose();
	}
	
	
}
