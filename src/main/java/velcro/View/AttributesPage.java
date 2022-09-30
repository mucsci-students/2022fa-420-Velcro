/**
 * Filename: AttributesPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: A page for interfacing with Attributes options.
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

import velcro.Model.Attributes;
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

public class AttributesPage {

	public static JFrame attributesPage = new JFrame();
	public static JTextField txtuseOnlyFor;
	public static JComboBox<String> comboBox;
	public static JComboBox<String> comboBox_1;
	public static Instance thisInstance;
	public static JButton btnNewButton;
	public static JButton btnDelete;
	public static JButton btnRename;

	// Page buttons & layout.
	public AttributesPage(Instance thisInstance) {

		this.thisInstance = thisInstance;
		// Models for combo boxes assigned here so they can be updated live
		comboBox = new JComboBox<String>(thisInstance.model);
		comboBox_1 = new JComboBox<String>(thisInstance.model_1);
		
		// Initial pane setup.
		attributesPage.setTitle("Velcro CSCI 420 :: Attributes");
		attributesPage.setBounds(100, 100, 700, 500);
		attributesPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		attributesPage.getContentPane().setLayout(null);

		// Text field for renaming
		txtuseOnlyFor = new JTextField();
		txtuseOnlyFor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtuseOnlyFor.setColumns(10);
		txtuseOnlyFor.setBounds(472, 253, 159, 29);
		txtuseOnlyFor.getDocument().addDocumentListener(Controller.attLetterListener);
		attributesPage.getContentPane().add(txtuseOnlyFor);

		// Button to add attributes.
		btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(54, 247, 159, 69);
		btnNewButton.addActionListener(Controller.att);
		attributesPage.getContentPane().add(btnNewButton);

		// Button to delete attributes.
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(265, 247, 159, 69);
		btnDelete.addActionListener(Controller.delAtt);
		attributesPage.getContentPane().add(btnDelete);

		// Button to rename attributes.
		btnRename = new JButton("Rename");
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRename.setBounds(472, 287, 159, 29);
		btnRename.addActionListener(Controller.renAtt);
		attributesPage.getContentPane().add(btnRename);

		// Label for attribute name
		JLabel lblNewLabel = new JLabel("Attribute Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(115, 63, 150, 45);
		attributesPage.getContentPane().add(lblNewLabel);

		// Button to return to the landing page.
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(Controller.attributesHomeButton);
		attributesPage.getContentPane().add(btnHomepage);

		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Attributes Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);
		lblNewLabel_1.addMouseListener(Controller.attHelpListener);
		attributesPage.getContentPane().add(lblNewLabel_1);

		// Creates classes field.
		comboBox.setEditable(false);
		comboBox.setBounds(400, 119, 231, 51);
		comboBox.setModel(thisInstance.model);
		attributesPage.getContentPane().add(comboBox);

		// Class label.
		JLabel lblClass = new JLabel("Class");
		lblClass.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblClass.setBounds(491, 74, 51, 23);
		attributesPage.getContentPane().add(lblClass);

		// Create comboBox for name entry field.
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(54, 119, 255, 51);
		comboBox_1.addActionListener(Controller.finalAtt);
		comboBox_1.addActionListener(Controller.finalDelAtt);
		attributesPage.getContentPane().add(comboBox_1);

		attributesPage.setVisible(true);
	}
}
