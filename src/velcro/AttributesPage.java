/**
 * Filename: AttributesPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: A page for interfacing with Attributes options.
 * 
 */

package velcro;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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

	JFrame attributesPage = new JFrame();
	private JTextField txtuseOnlyFor;
	private JComboBox comboBox;

	// Page buttons & layout.
	AttributesPage(Instance thisInstance) {

		// Models for combo boxes made here so they can be updated live
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		JComboBox<String> comboBox = new JComboBox<String>(model);
		DefaultComboBoxModel<String> model_1 = new DefaultComboBoxModel<String>();
		JComboBox<String> comboBox_1 = new JComboBox<String>(model_1);
		
		attributesPage.setTitle("Velcro CSCI 420 :: Attributes");
		attributesPage.setBounds(100, 100, 700, 500);
		attributesPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		attributesPage.getContentPane().setLayout(null);

		// Text field for renaming
		txtuseOnlyFor = new JTextField();
		txtuseOnlyFor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtuseOnlyFor.setColumns(10);
		txtuseOnlyFor.setBounds(472, 253, 159, 29);
		attributesPage.getContentPane().add(txtuseOnlyFor);

		// Button to add attributes.
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(54, 247, 159, 69);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Classes thisClass;
				// Checks if classes are entered already
				try {
					thisClass = thisInstance.getClass(comboBox.getSelectedItem().toString());
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(attributesPage, "Please enter classes first.");
					return;					
				}
				
				// Checks if combo box field is somehow empty
				if (comboBox.getSelectedItem().toString().equals("") || !containsAlphaNumeric(comboBox.getSelectedItem().toString())) {
					JOptionPane.showMessageDialog(attributesPage, "Please enter an attribute name.");
					return;
				}
				// Checks if Attribute already exists.
				Attributes orig = thisClass.getAttribute(comboBox.getSelectedItem().toString());
				if (orig != null) {
					JOptionPane.showMessageDialog(attributesPage, "Attribute already exists!");
					return;
				}
				// Adds attribute, updates combo box
				thisClass.addAttribute(comboBox.getSelectedItem().toString());
				model_1.addElement(comboBox.getSelectedItem().toString());
				JOptionPane.showMessageDialog(attributesPage, "Attribute added successfully.");
			}
		});
		attributesPage.getContentPane().add(btnNewButton);

		// Button to delete attributes.
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(265, 247, 159, 69);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Classes thisClass;
				// Tests if classes already exist
				try {
					thisClass = thisInstance.getClass(comboBox.getSelectedItem().toString());
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(attributesPage, "Please enter classes first.");
					return;					
				}
				// Tests if any attributes already exist
				if (thisClass.attributeList == null || thisClass.attributeList.length == 0) {
					JOptionPane.showMessageDialog(attributesPage, "No attributes currently!");
					return;
				}
				// Checks to see if combo box is somehow empty
				if (comboBox.getSelectedItem().toString().equals("") || !containsAlphaNumeric(comboBox.getSelectedItem().toString())) {
					JOptionPane.showMessageDialog(attributesPage, "Please enter an attribute name.");
					return;
				}
				// Removes attribute, updates combo box
				if (thisClass.removeAttribute(comboBox.getSelectedItem().toString())) {
					model_1.removeElement(comboBox.getSelectedItem().toString());
					JOptionPane.showMessageDialog(attributesPage, "Attribute removed successfully.");
				}
				else
					JOptionPane.showMessageDialog(attributesPage, "Attribute removal failed, attribute not found.");
			}
		});
		attributesPage.getContentPane().add(btnDelete);

		// Button to rename attributes.
		JButton btnRename = new JButton("Rename");
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRename.setBounds(472, 287, 159, 29);
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Classes thisClass;
				// Tests if classes already exist
				try {
					thisClass = thisInstance.getClass(comboBox.getSelectedItem().toString());
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(attributesPage, "Please enter classes first.");
					return;					
				}
				// Checks for empty text box.
				if (comboBox.getSelectedItem().toString().equals("") || txtuseOnlyFor.getText().equals("")
						|| !containsAlphaNumeric(comboBox.getSelectedItem().toString())
						|| !containsAlphaNumeric(txtuseOnlyFor.getText())) {

					JOptionPane.showMessageDialog(attributesPage, "Please enter an attribute and new name.");
					return;
				}
			
				// Checks for attribute existing.
				Classes orig = thisInstance.getClass(comboBox.getSelectedItem().toString());
				if (orig == null) {
					JOptionPane.showMessageDialog(attributesPage, "Attribute not found.");
					return;
				}
				// Checks if rename is already taken.
				Attributes orig2 = thisClass.getAttribute(txtuseOnlyFor.getText());
				if (orig2 != null) {
					JOptionPane.showMessageDialog(attributesPage, "Attribute already exists!");
					return;
				}
				// Renames attribute, updates Attribute combo box
				Attributes orig3 = thisClass.getAttribute(comboBox.getSelectedItem().toString());
				if (orig3.rename(txtuseOnlyFor.getText())) {
					model_1.addElement(txtuseOnlyFor.getText());
					model_1.removeElement(comboBox.getSelectedItem().toString());
					comboBox_1.setSelectedItem(txtuseOnlyFor.getText());
					JOptionPane.showMessageDialog(attributesPage, "Attribute renamed successfully.");
				} else {
					JOptionPane.showMessageDialog(attributesPage, "Attribute not found.");
				}
			}
		});
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
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attributesPage.dispose();
				LandingPage window = new LandingPage(thisInstance);
				window.homepage.setVisible(true);
			}
		});
		attributesPage.getContentPane().add(btnHomepage);

		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Attributes Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);

		String s = "<html>'Attribute Name' : Field1 &#9 'Rename Attribute Name' : Field2<br>To add an Attribute, enter a unique attribute name in Field1 and press the 'Add' button.<br> The action will fail if there is already an attribute with that name or if the entered name if invalid.<br> To delete an Attribute, enter the name of the attribute that you wish to delete in Field1 and press the 'Delete' button.<br> The action will fail if there is no attribute with the given name.<br>To rename an Attribute, enter the current name of the attribute that you wish to change in Field1,<br> and the name that you wish to change it to in Field2.<br> To initiate the action, hit the 'Rename' button once both fields have been entered. The action will fail if there is no<br> existing attribute with the given name or if the new name being entered is either already in use or is invalid.</html>";

		// Overrides required; Swing does not natively support clickable text
		lblNewLabel_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(attributesPage, s);
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

		});
		attributesPage.getContentPane().add(lblNewLabel_1);

		// Initially populates class combo box
		if (thisInstance.classList != null) {
			if (thisInstance.classList.length != 0) {
				for (int i = 0; i < thisInstance.classList.length; i++) {
					model.addElement(thisInstance.classList[i].getName());
				}
				comboBox.setMaximumRowCount(thisInstance.classList.length);
			}
		}
		comboBox.setEditable(false);
		comboBox.setBounds(400, 119, 231, 51);
		comboBox.setModel(model);
		attributesPage.getContentPane().add(comboBox);
		
		// Class label.
		JLabel lblClass = new JLabel("Class");
		lblClass.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblClass.setBounds(491, 74, 51, 23);
		attributesPage.getContentPane().add(lblClass);
		
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(54, 119, 255, 51);
		attributesPage.getContentPane().add(comboBox_1);
		
		// Initially populates Attributes combo box
		Classes thisClass;
		try {
			thisClass = thisInstance.getClass(comboBox.getSelectedItem().toString());
			if (thisInstance.getClass(comboBox.getSelectedItem().toString()) != null && thisInstance.classList.length != 0) {
				if (thisClass != null && thisClass.attributeList != null && thisClass.attributeList.length != 0) {
					for (int i = 0; i < thisClass.attributeList.length; i++) {
						model_1.addElement(thisClass.attributeList[i].getName());
					}
					comboBox_1.setMaximumRowCount(thisClass.attributeList.length);
				}
			}
		} catch (NullPointerException e1) {
		}
		


		attributesPage.setVisible(true);
	}



	private static boolean containsAlphaNumeric(String input) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
		return p.matcher(input).find();
	}
}
