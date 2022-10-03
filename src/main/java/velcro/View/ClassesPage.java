/**
 * Filename: ClassesPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Page containing buttons and layout of Classes page in GUI.
 * 
 */

package velcro.View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import velcro.Controller.Controller;
import velcro.Model.*;
import velcro.View.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.swing.JComboBox;

public class ClassesPage {

	// Required JFrame constructor and attributes.
	public static JFrame classPage;
	public static JTextField textField2;
	public static JButton btnNewButton;
	public static JButton btnDelete;
//	public static DefaultComboBoxModel<String> model;
//	public static DefaultComboBoxModel<String> model_1;
//	public static DefaultComboBoxModel<String> model_2;
	public static JComboBox<String> comboBox;
	public static JComboBox<String> comboBox_1;
	public static JComboBox<String> comboBox_2;
	public static Instance thisInstance;
	public static JButton btnRename;


	// Page constructor.
	public ClassesPage(Instance thisInstance) {
		classPage = new JFrame();
		Controller.initClasses(thisInstance);


		// Buttons and labels for GUI.
		classPage.setTitle("Velcro CSCI 420 :: Classes");
		classPage.setBounds(100, 100, 700, 500);
		classPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		classPage.getContentPane().setLayout(null);

		// Text field for rename purposes
		textField2 = new JTextField();
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField2.setColumns(10);
		textField2.setBounds(472, 247, 159, 27);
		textField2.getDocument().addDocumentListener(Controller.claLetterListener);
		classPage.getContentPane().add(textField2);

		// Button to add Class.
		btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(54, 247, 159, 69);
		btnNewButton.addActionListener(Controller.cla);
		classPage.getContentPane().add(btnNewButton);

		// Button to delete class.
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(265, 281, 159, 35);
		btnDelete.addActionListener(Controller.delCla);
		classPage.getContentPane().add(btnDelete);

		// Button to rename class.
		btnRename = new JButton("Rename");
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRename.setBounds(472, 281, 159, 35);
		btnRename.addActionListener(Controller.renCla);
		classPage.getContentPane().add(btnRename);

		JLabel lblNewLabel = new JLabel("Class Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(294, 63, 115, 45);
		classPage.getContentPane().add(lblNewLabel);

		// Button to return to landing page.
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(Controller.classesHomeButton);
		classPage.getContentPane().add(btnHomepage);

		// Button to display Class contents.
		JButton btnListContents = new JButton("Class Contents");
		btnListContents.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnListContents.setBounds(472, 393, 159, 35);
		btnListContents.addActionListener(Controller.displayClasses);
		classPage.getContentPane().add(btnListContents);

		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Class Page Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);
		lblNewLabel_1.addMouseListener(Controller.claHelpListener);
		classPage.getContentPane().add(lblNewLabel_1);

		// Combo Box for displaying classes available for Display Class Contents.
		comboBox.setBounds(472, 359, 159, 29);
		classPage.getContentPane().add(comboBox);

		// Combo Box for displaying classes available for Delete Class.
		comboBox_1.setBounds(265, 247, 159, 29);
		comboBox_1.addActionListener(Controller.finalDelClass);
		classPage.getContentPane().add(comboBox_1);

		// Main combo box for character entry.
		comboBox_2.setBounds(215, 119, 255, 51);
		comboBox_2.setEditable(true);
		comboBox_2.addActionListener(Controller.finalAddClass);
		comboBox_2.addActionListener(Controller.finalRenClass);
		classPage.getContentPane().add(comboBox_2);

		classPage.setVisible(true);
	}
}
