/**
 * Filename: RelationshipsPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Obsolete page for managing Relationships in the Instance.
 * 
 */

package main.java.velcro.Obsolete;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import main.java.velcro.Model.*;
import main.java.velcro.View.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JRadioButton;

public class RelationshipsPage {

//	// JFrame creation and needed attributes.
//	public static JFrame relationPage = new JFrame();
//	public static DefaultComboBoxModel<String> inputInstance;
//	public static JRadioButton rdbtnNewRadioButton;
//	public static JRadioButton rdbtnNewRadioButton_1;
//	public static JRadioButton rdbtnNewRadioButton_2;
//	public static JRadioButton rdbtnNewRadioButton_3;
//	public static ButtonGroup group;
//	public static JComboBox<String> comboBox;
//	public static JComboBox<String> comboBox_1;
//	public static JButton btnNewButton;
//	public static JButton btnDelete;
//	public static Instance thisInstance;
//
//	// Constructor.
//	public RelationshipsPage(Instance thisInstance) {
//	
//		// Models need to be defined early for subsequent adding/subtracting.
//		Controller.relInit(thisInstance);
//
//		relationPage.setTitle("Velcro CSCI 420 :: Relationships");
//		relationPage.setBounds(100, 100, 700, 500);
//		relationPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		relationPage.getContentPane().setLayout(null);
//
//		// Button to add relationship to Instance.
//		btnNewButton = new JButton("Add");
//		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		btnNewButton.setBounds(66, 247, 159, 69);
//		btnNewButton.addActionListener(Controller.rel);
//		relationPage.getContentPane().add(btnNewButton);
//
//		// Button to remove relationship from Instance.
//		btnDelete = new JButton("Delete");
//		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		btnDelete.setBounds(265, 247, 159, 69);
//		btnDelete.addActionListener(Controller.delRela);
//		relationPage.getContentPane().add(btnDelete);
//
//		// Source label
//		JLabel lblNewLabel = new JLabel("Source Class Name");
//		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblNewLabel.setBounds(67, 63, 150, 45);
//		relationPage.getContentPane().add(lblNewLabel);
//
//		// Destination label
//		JLabel lblReplaceClassName = new JLabel("Destination Class Name");
//		lblReplaceClassName.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblReplaceClassName.setBounds(449, 63, 191, 45);
//		relationPage.getContentPane().add(lblReplaceClassName);
//
//		// Button returns to landing page.
//		JButton btnHomepage = new JButton("Homepage");
//		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		btnHomepage.setBounds(265, 359, 159, 69);
//		btnHomepage.addActionListener(Controller.relationshipHomeButton);
//		relationPage.getContentPane().add(btnHomepage);
//
//		// Help text with help info message.
//		JLabel lblNewLabel_1 = new JLabel("Help");
//		lblNewLabel_1.setToolTipText("Relationship Page Help");
//		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblNewLabel_1.setBounds(623, 11, 51, 29);
//		lblNewLabel_1.addMouseListener(Controller.relHelpListener);
//		relationPage.getContentPane().add(lblNewLabel_1);
//
//		// List all button action
//		JButton btnListAll = new JButton("List All");
//		btnListAll.addActionListener(Controller.listRelationships);
//		btnListAll.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		btnListAll.setBounds(465, 247, 159, 69);
//		relationPage.getContentPane().add(btnListAll);
//
//
//		comboBox.setEditable(false);
//		comboBox.setBounds(46, 113, 182, 69);
//		comboBox.setModel(thisInstance.classModel);
//		comboBox.addActionListener(Controller.finalRel);
//		relationPage.getContentPane().add(comboBox);
//
//		comboBox_1.setEditable(false);
//		comboBox_1.setBounds(447, 113, 182, 69);
//		comboBox_1.setModel(thisInstance.classModel_2);
//		comboBox_1.addActionListener(Controller.finalRel);
//		relationPage.getContentPane().add(comboBox_1);
//		
//		rdbtnNewRadioButton = new JRadioButton("Aggregation");
//		rdbtnNewRadioButton.setSelected(true);
//		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		rdbtnNewRadioButton.setBounds(284, 119, 115, 23);
//		rdbtnNewRadioButton.setActionCommand("Aggregation");
//		relationPage.getContentPane().add(rdbtnNewRadioButton);
//		
//		rdbtnNewRadioButton_1 = new JRadioButton("Composition");
//		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		rdbtnNewRadioButton_1.setBounds(284, 145, 115, 23);
//		rdbtnNewRadioButton_1.setActionCommand("Composition");
//		relationPage.getContentPane().add(rdbtnNewRadioButton_1);
//		
//		rdbtnNewRadioButton_2 = new JRadioButton("Inheritance");
//		rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		rdbtnNewRadioButton_2.setBounds(284, 171, 115, 23);
//		rdbtnNewRadioButton_2.setActionCommand("Inheritance");
//		relationPage.getContentPane().add(rdbtnNewRadioButton_2);
//		
//		rdbtnNewRadioButton_3 = new JRadioButton("Realization");
//		rdbtnNewRadioButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		rdbtnNewRadioButton_3.setBounds(284, 197, 115, 23);
//		rdbtnNewRadioButton_3.setActionCommand("Realization");
//		relationPage.getContentPane().add(rdbtnNewRadioButton_3);
//		
//		JLabel lblRelationshipType = new JLabel("Relationship Type");
//		lblRelationshipType.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblRelationshipType.setBounds(277, 63, 138, 45);
//		relationPage.getContentPane().add(lblRelationshipType);
//
//		
//		relationPage.setVisible(true);
//	}
}
