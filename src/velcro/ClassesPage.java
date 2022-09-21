/**
 * Filename: ClassesPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Page containing buttons and layout of Classes page in GUI.
 * 
 */

package velcro;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.ComboBoxModel;

public class ClassesPage {

	// Required JFrame constructor and attributes.
	JFrame classPage = new JFrame();
	private JTextField textField2;

	// Page constructor.
	ClassesPage(Instance thisInstance) {

		// Create models for comboBox use; needs to be created early so methods can add/remove from it to update it live.
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> model_1 = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> model_2 = new DefaultComboBoxModel<String>();
		JComboBox<String> comboBox = new JComboBox<String>(model);
		JComboBox<String> comboBox_1 = new JComboBox<String>(model_1);
		JComboBox<String> comboBox_2 = new JComboBox<String>(model_2);

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
		classPage.getContentPane().add(textField2);

		// Button to add Class.
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(54, 247, 159, 69);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e == null && e.getSource().toString() != null)
				{
					JOptionPane.showMessageDialog(classPage, "Please enter a class name.");
					return;
				}
				// Checks for empty text field.
				if (comboBox_2.getSelectedItem() == null || comboBox_2.getSelectedItem().toString().equals("")
						|| !containsAlphaNumeric(comboBox_2.getSelectedItem().toString())) {
					JOptionPane.showMessageDialog(classPage, "Please enter a class name.");
					return;
				}
				Classes orig;
				orig = thisInstance.getClass(comboBox_2.getSelectedItem().toString());
				if (orig != null) {
					JOptionPane.showMessageDialog(classPage, "Class already exists!");
					return;
				}
				thisInstance.addClass(comboBox_2.getSelectedItem().toString());
				model.addElement(comboBox_2.getSelectedItem().toString());
				model_1.addElement(comboBox_2.getSelectedItem().toString());
				model_2.addElement(comboBox_2.getSelectedItem().toString());
				JOptionPane.showMessageDialog(classPage, "Class added successfully.");
			}
		});
		classPage.getContentPane().add(btnNewButton);

		// Button to delete class.
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(265, 281, 159, 35);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (thisInstance.classList == null || thisInstance.classList.length == 0) {
					JOptionPane.showMessageDialog(classPage, "No classes currently!");
					return;
				}

				if (thisInstance.removeClass(comboBox_1.getSelectedItem().toString())) {
					model.removeElement(comboBox_1.getSelectedItem().toString());
					model_1.removeElement(comboBox_1.getSelectedItem().toString());
					JOptionPane.showMessageDialog(classPage, "Class removed successfully.");
				} else
					JOptionPane.showMessageDialog(classPage, "Class removal failed, class not found.");
			}
		});
		classPage.getContentPane().add(btnDelete);

		// Button to rename class.
		JButton btnRename = new JButton("Rename");
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRename.setBounds(472, 281, 159, 35);
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_2.getSelectedItem().toString().equals("") || textField2.getText().equals("")
						|| !containsAlphaNumeric(comboBox_2.getSelectedItem().toString())
						|| !containsAlphaNumeric(textField2.getText())) {
					JOptionPane.showMessageDialog(classPage, "Please enter a class and new name.");
					return;
				}
				Classes orig;
				try {
					thisInstance.getClass(comboBox_2.getSelectedItem().toString());
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(classPage, "Please enter classes first.");
					return;
				}
				orig = thisInstance.getClass(comboBox_2.getSelectedItem().toString());
				Classes orig2 = thisInstance.getClass(textField2.getText());
				if (orig2 != null) {
					JOptionPane.showMessageDialog(classPage, "Class already exists!");
					return;
				}
				if (orig.rename(comboBox_2.getSelectedItem().toString(), textField2.getText())) {
					model.removeElement(comboBox_2.getSelectedItem().toString());
					model.addElement(textField2.getText());
					model_1.removeElement(comboBox_2.getSelectedItem().toString());
					model_1.addElement(textField2.getText());
					model_2.removeElement(comboBox_2.getSelectedItem().toString());
					model_2.addElement(textField2.getText());
					comboBox_2.setSelectedItem(textField2.getText());
					JOptionPane.showMessageDialog(classPage, "Class renamed successfully.");
				} else {
					JOptionPane.showMessageDialog(classPage, "Class not found.");
				}
			}
		});
		classPage.getContentPane().add(btnRename);

		JLabel lblNewLabel = new JLabel("Class Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(294, 63, 115, 45);
		classPage.getContentPane().add(lblNewLabel);

		// Button to return to landing page.
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				classPage.dispose();
				LandingPage window = new LandingPage(thisInstance);
				window.homepage.setVisible(true);
			}
		});
		classPage.getContentPane().add(btnHomepage);

		// Button to display class list and contents.
		JButton btnClassList = new JButton("All Class Contents");
		btnClassList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClassList.setBounds(54, 359, 159, 69);
		btnClassList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aborts if classList is empty
				if (thisInstance.classList == null || thisInstance.classList.length == 0) {
					JOptionPane.showMessageDialog(classPage, "Please enter classes first.");
					return;
				}

				// Sets up table for pop-up
				DefaultTableModel model_3 = new DefaultTableModel();
				model_3.addColumn("Name");
				model_3.addColumn("Attribute");
				model_3.addColumn("Relationship Source");
				model_3.addColumn("Relationship Destination");
				boolean first = true;
				for (int h = 0; h < thisInstance.classList.length; h++) {
					// Adds a blank line between classes.
					if (first) {
						first = false;
					} else {
						model_3.addRow(new Object[] { " ", " ", " ", " "});
					}
					Classes thisClass = thisInstance.classList[h];
					// Adds class name
					model_3.addRow(new Object[] { thisClass.getName() });
					// Adds all class's attributes
					if (thisClass.attributeList != null) {
						if (thisClass.attributeList.length != 0) {
							for (int i = 0; i < thisClass.attributeList.length; i++) {
								model_3.addRow(new Object[] { " ", thisClass.attributeList[i].getName() });
							}
						}
					}
					// Adds all class's relationships
					if (thisClass.relationshipList != null) {
						if (thisClass.relationshipList.length != 0) {
							for (int i = 0; i < thisClass.relationshipList.length; i++) {
								model_3.addRow(new Object[] { " ", " ", thisClass.relationshipList[i].getSource(),
										thisClass.relationshipList[i].getDestination() });

							}
						}
					}

				}
				// Adjusting table so the headers are fully visible
				JTable table = new JTable(model_3);
				table.getColumnModel().getColumn(2).setPreferredWidth(110);
				table.getColumnModel().getColumn(3).setPreferredWidth(135);
				JOptionPane.showMessageDialog(null, new JScrollPane(table));

			}
		});
		classPage.getContentPane().add(btnClassList);

		// Button to display Class contents.
		JButton btnListContents = new JButton("Class Contents");
		btnListContents.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnListContents.setBounds(472, 393, 159, 35);
		btnListContents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checks if classes exist.
				Classes thisClass;
				try {
					thisClass = thisInstance.getClass(comboBox_1.getSelectedItem().toString());
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(classPage, "Please enter classes first.");
					return;
				}

				// Just in case user manages to empty the field.
				if (thisClass == null) {
					JOptionPane.showMessageDialog(classPage, "Class not found.");
					return;
				}

				// Draws a table outlining existing class's contents in dialog
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Name");
				model.addColumn("Attribute");
				model.addColumn("Relationship Source");
				model.addColumn("Relationship Destination");
				// Adds class name
				model.addRow(new Object[] { thisClass.getName() });
				// Adds all of class's attributes
				if (thisClass.attributeList != null) {
					if (thisClass.attributeList.length != 0) {
						for (int i = 0; i < thisClass.attributeList.length; i++) {
							model.addRow(new Object[] { " ", thisClass.attributeList[i].getName() });
						}
					}
				}
				// Adds all of class's relationships
				if (thisClass.relationshipList != null) {
					if (thisClass.relationshipList.length != 0) {
						for (int i = 0; i < thisClass.relationshipList.length; i++) {
							model.addRow(new Object[] { " ", " ", thisClass.relationshipList[i].getSource(),
									thisClass.relationshipList[i].getDestination() });

						}
					}
				}
				// Set table size so header is visible.
				JTable table = new JTable(model);
				table.getColumnModel().getColumn(2).setPreferredWidth(110);
				table.getColumnModel().getColumn(3).setPreferredWidth(135);
				JOptionPane.showMessageDialog(null, new JScrollPane(table));
			}
		});
		classPage.getContentPane().add(btnListContents);

		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Class Page Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);
		String s = "<html>'Class Name' : Field1 &#9 'Rename Class Name' : Field2 <br>To add classes, enter a unique classname in Field1 and click the 'Add' button.<br> The action will fail if the classname entered is already in use or is invalid.<br> To delete classes, enter the name of the class you want to delete in Field1 and click the 'Delete' button.<br> The action will fail if no class exists with the entered classname.<br> To rename a class, enter the name of the existing class Field1 and enter the new name you want it to have in Field2.<br> Once both fields are filled, click the 'Rename' button.<br> The action will fail if there is no existing class with a name matching what was entered in Field1, or if the name<br> entered in Field2 is already in use or is invalid.</html>";

		// Override required, as Swing does not natively support clickable text.
		lblNewLabel_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(classPage, s);
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

		});
		classPage.getContentPane().add(lblNewLabel_1);

		// Populates combo boxes initially
		if (thisInstance.classList != null) {
			if (thisInstance.classList.length != 0) {
				for (int i = 0; i < thisInstance.classList.length; i++) {
					model.addElement(thisInstance.classList[i].getName());
					model_1.addElement(thisInstance.classList[i].getName());
					model_2.addElement(thisInstance.classList[i].getName());
				}
				comboBox.setMaximumRowCount(thisInstance.classList.length);
			}
		}

		comboBox.setBounds(472, 359, 159, 29);
		classPage.getContentPane().add(comboBox);

		comboBox_1.setBounds(265, 247, 159, 29);
		classPage.getContentPane().add(comboBox_1);

		comboBox_2.setBounds(215, 119, 255, 51);
		comboBox_2.setEditable(true);
		classPage.getContentPane().add(comboBox_2);

		classPage.setVisible(true);
	}

	private static boolean containsAlphaNumeric(String input) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
		return p.matcher(input).find();
	}
}
