/**
 * Filename: DrawingGUI.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Initialization of new GUI for sprint three.
 * 
 */

package velcro.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import velcro.Controller.*;
import velcro.Model.*;
import velcro.View.*;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;

public class DrawingGUI extends JFrame {
	public Memento thisMemento = new Memento();
	public Instance thisInstance;
	public static int panelHeight = 1000;
	public static int panelWidth = 1200;
	public int keycode = -1;

	// Constructor.
	public DrawingGUI(Instance input) {
		thisInstance = input;
	}
	
	// Main method.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				Instance thisInstance = new Instance();
				DrawingGUI ex = new DrawingGUI(thisInstance);
				ex.resize();
				MenuFrame menuFrame = new MenuFrame(thisInstance, ex, panelHeight);
				ex.setFrame(menuFrame);
				ex.setVisible(true);
				//menuFrame.setSize(300, panelHeight);
				menuFrame.firstLoad();
				menuFrame.setVisible(true);
			}
		});
	}
	
	// Resize frames to fit smaller/laptop screens.
	public void resize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		panelHeight = (int) Math.min(screenSize.getHeight(), panelHeight);
		panelWidth = (int) Math.min(screenSize.getWidth() - 300, panelWidth);
	}

	// Methods for initializing frames.
	public void setFrame(MenuFrame menuFrame) {
		initUI(thisInstance, menuFrame);
	}

	public void initUI(Instance input, MenuFrame input2) {
		GUIFrame surface = new GUIFrame(input);
		surface.setFrame(input2);
		input2.setSurface(surface, this);
		getContentPane().add(surface);
		setTitle("Test Graphics");
		setSize(panelWidth, panelHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}

