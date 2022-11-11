/**
 * Filename: SavePage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Obsolete page for exporting the current Instance into a Json file.
 * 
 */
package main.java.velcro.Obsolete;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.google.gson.Gson;

import main.java.velcro.Model.*;
import main.java.velcro.View.*;

import org.apache.commons.io.FileUtils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class SavePage extends JPanel {
//	// JFrame creation and required attributes.
//	public static JFrame savePage = new JFrame();
//	public static Object[] recentSaves = new Object[0];
//	public static Object[] fileNames = Controller.localFiles();
//	public static DefaultListModel model = new DefaultListModel();
//	public static Instance thisInstance;
//
//	// Constructor with Instance parameter, to preserve current data
//	public SavePage(Instance thisInstance) {
//		this.thisInstance = thisInstance;
//		Controller.saveInit();
//
//		savePage.setTitle("Velcro CSCI 420 :: Save");
//		savePage.setBounds(100, 100, 700, 500);
//		savePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		savePage.getContentPane().setLayout(null);
//		
//		// Button that returns to the landing page.
//		JButton btnHomepage = new JButton("Homepage");
//		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		btnHomepage.setBounds(265, 381, 159, 69);
//		btnHomepage.addActionListener(Controller.saveHomeButton);
//		savePage.getContentPane().add(btnHomepage);
//
//		// Help text with help info message.
//		JLabel lblNewLabel_1 = new JLabel("Help");
//		lblNewLabel_1.setToolTipText("Save Page Help");
//		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblNewLabel_1.setBounds(623, 11, 51, 29);
//		lblNewLabel_1.addMouseListener(Controller.saveHelpListener);
//		savePage.getContentPane().add(lblNewLabel_1);
//
//		// Save as button. Loads file explorer dialogue and prompts user for save name/location.
//		JButton btnBrowse = new JButton("Save as...");
//		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		btnBrowse.setBounds(265, 286, 159, 69);
//		btnBrowse.addActionListener(Controller.save);
//		savePage.getContentPane().add(btnBrowse);
//		
//		// Button for clearing save history.
//		JButton btnBrowse_2 = new JButton("Clear Save History");
//		btnBrowse_2.addActionListener(Controller.clearSaveHistory);
//		btnBrowse_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		btnBrowse_2.setBounds(504, 381, 150, 69);
//		savePage.getContentPane().add(btnBrowse_2);
//
//		// Label for attribute name
//		JLabel lblNewLabel = new JLabel("Local Files and Recent Save Files");
//		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblNewLabel.setBounds(215, 13, 300, 45);
//		savePage.getContentPane().add(lblNewLabel);
//		
//		savePage.setVisible(true);
//	}
//
//	// Constructor to act as parent for file display window.
//	
//	public SavePage(DefaultListModel model) {
//		JList list1 = new JList(model);
//		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		JScrollPane list1scr = new JScrollPane(list1);
//		list1scr.setPreferredSize(new Dimension(500, 225));
//		setLayout(null);
//		list1scr.setBounds(new Rectangle(new Point(100, 50), list1scr.getPreferredSize()));
//		list1.setVisibleRowCount(8);
//		add(list1scr);
//	}
}