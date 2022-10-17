/**
 * Filename: ParametersPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: A page for interfacing with Parameters options.
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

import main.java.velcro.Model.Fields;
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

public class ParametersPage {

	public static JFrame parametersPage = new JFrame();
	public static JTextField txtuseOnlyFor;
	public static JComboBox<String> comboBox;
	public static JComboBox<String> comboBox_1;
	public static JComboBox<String> comboBox_2;
	public static JComboBox<String> comboBox_3;
	public static Instance thisInstance;
	public static JButton btnNewButton;
	public static JButton btnDelete;
	public static JButton btnRename;
	public static Methods thisMethod;
	private JLabel lblClass_2;
	public static String comboContents;

	// Page buttons & layout.
	public ParametersPage(Instance thisInstance, Methods method) {

		this.thisMethod = method;
		this.thisInstance = thisInstance;
		comboContents = "";
		
		// Models for combo boxes assigned here so they can be updated live
		comboBox = new JComboBox<String>(thisInstance.classModel);
		comboBox_1 = new JComboBox<String>();
		comboBox_2 = new JComboBox<String>(thisInstance.returnTypes);
		comboBox_3 = new JComboBox<String>();
		
		// Initial pane setup.
		parametersPage.setTitle("Velcro CSCI 420 :: Parameters");
		parametersPage.setBounds(100, 100, 700, 500);
		parametersPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parametersPage.getContentPane().setLayout(null);

		// Text field for renaming
		txtuseOnlyFor = new JTextField();
		txtuseOnlyFor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtuseOnlyFor.setColumns(10);
		txtuseOnlyFor.setBounds(472, 253, 159, 29);
		txtuseOnlyFor.getDocument().addDocumentListener(Controller.parLetterListener);
		parametersPage.getContentPane().add(txtuseOnlyFor);

		// Button to add Fields.
		btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(54, 247, 159, 69);
		btnNewButton.addActionListener(new Controller.AddParameter(ParametersPage.thisInstance,
				ParametersPage.comboBox.getSelectedItem(), ParametersPage.comboContents,
				ParametersPage.parametersPage, ParametersPage.comboBox_2.getSelectedItem()));
		parametersPage.getContentPane().add(btnNewButton);

		// Button to delete Fields.
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(265, 247, 159, 69);
//		btnDelete.addActionListener(Controller.delPar);
		btnDelete.addActionListener(new Controller.DelParameter(ParametersPage.thisInstance,
				ParametersPage.comboBox.getSelectedItem(), ParametersPage.comboContents,
				ParametersPage.parametersPage));
		parametersPage.getContentPane().add(btnDelete);

		// Button to rename Fields.
		btnRename = new JButton("Rename");
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRename.setBounds(472, 287, 159, 29);
//		btnRename.addActionListener(Controller.finalRenPar);
		btnRename.addActionListener(new Controller.RenameParameter(ParametersPage.thisInstance,
				ParametersPage.comboBox.getSelectedItem(), ParametersPage.comboContents,
				ParametersPage.parametersPage, ParametersPage.txtuseOnlyFor.getText()));
		parametersPage.getContentPane().add(btnRename);

		// Label for Field name
		JLabel lblNewLabel = new JLabel("Parameter Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(120, 16, 133, 36);
		parametersPage.getContentPane().add(lblNewLabel);

		// Button to return to the landing page.
		JButton btnHomepage = new JButton("Methods");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(Controller.parametersMethodButton);
		parametersPage.getContentPane().add(btnHomepage);

		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Parameter Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);
		lblNewLabel_1.addMouseListener(Controller.parHelpListener);
		parametersPage.getContentPane().add(lblNewLabel_1);

		// Creates classes field.
		comboBox.setEditable(false);
		comboBox.setBounds(400, 63, 231, 51);
		//comboBox.setModel(thisInstance.classModel);
		comboBox.addActionListener(Controller.finalMet);
		comboBox.addActionListener(Controller.finalDelMet);
		comboBox.addActionListener(Controller.finalRenMet);
		parametersPage.getContentPane().add(comboBox);

		// Class label.
		JLabel lblClass = new JLabel("Method");
		lblClass.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblClass.setBounds(491, 128, 61, 23);
		parametersPage.getContentPane().add(lblClass);

		// Create comboBox for name entry field.
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(54, 63, 255, 51);
//		comboBox_1.addActionListener(Controller.finalPar);
//		comboBox_1.addActionListener(Controller.finalDelPar);
//		comboBox_1.addActionListener(Controller.finalRenPar);
		((JTextField)comboBox_1.getEditor().getEditorComponent()).getDocument().addDocumentListener(Controller.parListener);
		parametersPage.getContentPane().add(comboBox_1);
		
		JLabel lblReturnType = new JLabel("Return Type");
		lblReturnType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblReturnType.setBounds(134, 119, 94, 45);
		parametersPage.getContentPane().add(lblReturnType);
		
		comboBox_2.setEditable(false);
		comboBox_2.setBounds(54, 162, 255, 51);
//		comboBox_2.addActionListener(Controller.finalPar);
		parametersPage.getContentPane().add(comboBox_2);
		
		lblClass_2 = new JLabel("Class");
		lblClass_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblClass_2.setBounds(489, 32, 61, 23);
		parametersPage.getContentPane().add(lblClass_2);
		
		comboBox_3.setEditable(false);
		comboBox_3.setBounds(400, 162, 231, 51);
		comboBox_3.addActionListener(Controller.finalPar);
		comboBox_3.addActionListener(Controller.finalDelPar);
		comboBox_3.addActionListener(Controller.finalRenPar);
		parametersPage.getContentPane().add(comboBox_3);
		
		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnClearAll.setBounds(54, 359, 159, 69);
		btnClearAll.addActionListener(Controller.clearParams);
		parametersPage.getContentPane().add(btnClearAll);

		parametersPage.setVisible(true);
	}
}
