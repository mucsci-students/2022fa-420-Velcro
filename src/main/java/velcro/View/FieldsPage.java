/**
 * Filename: fieldsPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: A page for interfacing with Fields options.
 * 
 */

package velcro.View;

import velcro.Controller.*;
import velcro.Model.*;
import velcro.View.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import velcro.Model.Fields;
import velcro.Model.Classes;
import velcro.Model.Instance;

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

public class FieldsPage {

	public static JFrame fieldsPage = new JFrame();
	public static JTextField txtuseOnlyFor;
	public static JComboBox<String> comboBox;
	public static JComboBox<String> comboBox_1;
	public static JComboBox<String> comboBox_2;
	public static Instance thisInstance;
	public static JButton btnNewButton;
	public static JButton btnDelete;
	public static JButton btnHomepage;
	public static JButton btnRename;
	public static String comboContents;

	// Page buttons & layout.
	public FieldsPage(Instance thisInstance) {

		this.thisInstance = thisInstance;
		// Models for combo boxes assigned here so they can be updated live
		comboBox = new JComboBox<String>(thisInstance.classModel);
		comboBox_1 = new JComboBox<String>();
		comboBox_2 = new JComboBox<String>(thisInstance.returnTypes);
		comboContents = "";
		
		// Initial pane setup.
		fieldsPage.setTitle("Velcro CSCI 420 :: Fields");
		fieldsPage.setBounds(100, 100, 700, 500);
		fieldsPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fieldsPage.getContentPane().setLayout(null);

		// Text field for renaming
		txtuseOnlyFor = new JTextField();
		txtuseOnlyFor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtuseOnlyFor.setColumns(10);
		txtuseOnlyFor.setBounds(472, 253, 159, 29);
		txtuseOnlyFor.getDocument().addDocumentListener(Controller.fldLetterListener);
		fieldsPage.getContentPane().add(txtuseOnlyFor);

		// Button to add Fields.
		btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(54, 247, 159, 69);
		btnNewButton.addActionListener(Controller.finalFld);
		fieldsPage.getContentPane().add(btnNewButton);

		// Button to delete Fields.
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(265, 247, 159, 69);
		btnDelete.addActionListener(Controller.delFld);
		fieldsPage.getContentPane().add(btnDelete);

		// Button to rename Fields.
		btnRename = new JButton("Rename");
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRename.setBounds(472, 287, 159, 29);
		fieldsPage.getContentPane().add(btnRename);

		// Label for Field name
		JLabel lblNewLabel = new JLabel("Field Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(134, 20, 94, 36);
		fieldsPage.getContentPane().add(lblNewLabel);

		// Button to return to the landing page.
		btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(Controller.fieldsHomeButton);
		fieldsPage.getContentPane().add(btnHomepage);

		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Fields Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);
		lblNewLabel_1.addMouseListener(Controller.fldHelpListener);
		fieldsPage.getContentPane().add(lblNewLabel_1);

		// Creates classes field.
		comboBox.setEditable(false);
		comboBox.setBounds(400, 119, 231, 51);
//		comboBox.setModel(thisInstance.classModel);
		comboBox.addActionListener(Controller.finalFld);
		comboBox.addActionListener(Controller.finalDelFld);
		comboBox.addActionListener(Controller.finalRenFld);
		fieldsPage.getContentPane().add(comboBox);

		// Class label.
		JLabel lblClass = new JLabel("Class");
		lblClass.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblClass.setBounds(491, 74, 51, 23);
		fieldsPage.getContentPane().add(lblClass);

		// Create comboBox for name entry field.
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(54, 63, 255, 51);
		((JTextField)comboBox_1.getEditor().getEditorComponent()).getDocument().addDocumentListener(Controller.fldListener);
		fieldsPage.getContentPane().add(comboBox_1);
		
		JLabel lblReturnType = new JLabel("Return Type");
		lblReturnType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblReturnType.setBounds(134, 119, 94, 45);
		fieldsPage.getContentPane().add(lblReturnType);
		
		comboBox_2.setEditable(false);
		comboBox_2.setBounds(54, 162, 255, 51);
		fieldsPage.getContentPane().add(comboBox_2);

		fieldsPage.setVisible(true);
	}
}
