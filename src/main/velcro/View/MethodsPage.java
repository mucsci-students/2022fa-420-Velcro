/**
 * Filename: methodsPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: A page for interfacing with Methods options.
 * 
 */

package main.java.velcro.View;

import main.java.velcro.Controller.*;
import main.java.velcro.Model.*;
import main.java.velcro.View.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.java.velcro.Model.Methods;
import main.java.velcro.Model.Classes;
import main.java.velcro.Model.Instance;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;

public class MethodsPage {

	public static JFrame methodsPage = new JFrame();
	public static JTextField txtuseOnlyFor;
	public static JComboBox<String> comboBox;
	public static JComboBox<String> comboBox_1;
	public static JComboBox<String> comboBox_2;
	public static JComboBox<String> comboBox_3;
	public static Instance thisInstance;
	public static JButton btnNewButton;
	public static JButton btnDelete;
	public static JButton btnRename;
	public static JButton btnAddParameters;
	public static String comboContents = "";
	
	

	// Page buttons & layout.
	public MethodsPage(Instance thisInstance) {

		this.thisInstance = thisInstance;
		// Models for combo boxes assigned here so they can be updated live
		comboBox = new JComboBox<String>(thisInstance.classModel);
		comboBox_1 = new JComboBox<String>();
		comboBox_2 = new JComboBox<String>(thisInstance.returnTypes);
		comboBox_3 = new JComboBox<String>();
		
		// Initial pane setup.
		methodsPage.setTitle("Velcro CSCI 420 :: Methods");
		methodsPage.setBounds(100, 100, 700, 500);
		methodsPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		methodsPage.getContentPane().setLayout(null);

		// Text method for renaming
		txtuseOnlyFor = new JTextField();
		txtuseOnlyFor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtuseOnlyFor.setColumns(10);
		txtuseOnlyFor.setBounds(472, 253, 159, 29);
		txtuseOnlyFor.getDocument().addDocumentListener(Controller.metLetterListener);
		methodsPage.getContentPane().add(txtuseOnlyFor);

		// Button to add Methods.
		btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(54, 247, 159, 69);
		btnNewButton.addActionListener(Controller.finalMet);
		methodsPage.getContentPane().add(btnNewButton);

		// Button to delete Methods.
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(265, 247, 159, 69);
		btnDelete.addActionListener(Controller.finalDelMet);
		methodsPage.getContentPane().add(btnDelete);

		// Button to rename Methods.
		btnRename = new JButton("Rename");
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRename.setBounds(472, 287, 159, 29);
		btnRename.addActionListener(Controller.finalRenMet);
		methodsPage.getContentPane().add(btnRename);

		// Label for Method name
		JLabel lblNewLabel = new JLabel("Method Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(134, 20, 138, 36);
		methodsPage.getContentPane().add(lblNewLabel);

		// Button to return to the landing page.
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(Controller.methodsHomeButton);
		methodsPage.getContentPane().add(btnHomepage);

		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Methods Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);
		lblNewLabel_1.addMouseListener(Controller.metHelpListener);
		methodsPage.getContentPane().add(lblNewLabel_1);

		// Creates classes comboBox.
		comboBox.setEditable(false);
		comboBox.setBounds(400, 65, 231, 51);
		//comboBox.addActionListener(Controller.updateParametersListener);
		comboBox.addActionListener(Controller.finalMet);
		comboBox.addActionListener(Controller.finalDelMet);
		comboBox.addActionListener(Controller.finalRenMet);
		comboBox.addActionListener(Controller.updateParamButton);
		methodsPage.getContentPane().add(comboBox);

		// Class label.
		JLabel lblClass = new JLabel("Class");
		lblClass.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblClass.setBounds(493, 27, 51, 23);
		methodsPage.getContentPane().add(lblClass);

		// Create comboBox for name entry method.
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(54, 63, 255, 51);
		//comboBox_1.addActionListener(Controller.updateParametersListener);
//		comboBox_1.addActionListener(Controller.finalMet);
//		comboBox_1.addActionListener(Controller.finalDelMet);
//		comboBox_1.addActionListener(Controller.finalRenMet);
//		comboBox_1.addActionListener(Controller.paramButton);
		((JTextField)comboBox_1.getEditor().getEditorComponent()).getDocument().addDocumentListener(Controller.metListener);
		methodsPage.getContentPane().add(comboBox_1);
		
		JLabel lblReturnType = new JLabel("Return Type");
		lblReturnType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblReturnType.setBounds(134, 119, 94, 45);
		methodsPage.getContentPane().add(lblReturnType);
		
		comboBox_2.setEditable(false);
		comboBox_2.setBounds(54, 162, 255, 51);
		comboBox_2.addActionListener(Controller.finalMet);
		methodsPage.getContentPane().add(comboBox_2);
		
		comboBox_3.setEditable(false);
		comboBox_3.setBounds(400, 162, 231, 51);
		methodsPage.getContentPane().add(comboBox_3);
		
		JLabel lblParameters = new JLabel("Parameters");
		lblParameters.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblParameters.setBounds(472, 127, 94, 23);
		methodsPage.getContentPane().add(lblParameters);
		
		btnAddParameters = new JButton("Manage Parameters");
		btnAddParameters.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddParameters.setBounds(54, 359, 159, 69);
		btnAddParameters.addActionListener(Controller.methodsParamButton);
		methodsPage.getContentPane().add(btnAddParameters);

		methodsPage.setVisible(true);
	}
}
