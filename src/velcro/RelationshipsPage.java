/**
 * Filename: RelationshipsPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: A page for managing Relationships in the Instance.
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
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import javax.swing.ComboBoxModel;

public class RelationshipsPage {

	// JFrame creation and needed attributes.
	JFrame relationPage = new JFrame();

	// Constructor.
	RelationshipsPage(Instance thisInstance) {

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> model_1 = new DefaultComboBoxModel<String>();
		JComboBox<String> comboBox = new JComboBox<String>(model);
		JComboBox<String> comboBox_1 = new JComboBox<String>(model_1);

		relationPage.setTitle("Velcro CSCI 420 :: Relationships");
		relationPage.setBounds(100, 100, 700, 500);
		relationPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		relationPage.getContentPane().setLayout(null);

		// Button to add relationship to Instance.
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(66, 247, 159, 69);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checks that classes exist
				String sourceClassName;
				String destinationClassName;
				try {
					sourceClassName = comboBox.getSelectedItem().toString();
					destinationClassName = comboBox_1.getSelectedItem().toString();
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(relationPage, "Please enter classes first.");
					return;
				}
				// Source and destination cannot be the same
				if (sourceClassName.equals(destinationClassName)) {
					JOptionPane.showMessageDialog(relationPage, "Please enter a seperate source and destination.");
					return;
				}
				Classes sourceClass = thisInstance.getClass(sourceClassName);
				Classes destinationClass = thisInstance.getClass(destinationClassName);
				// Checks for empty input fields
				if (sourceClass == null || sourceClass.equals("") || destinationClass == null
						|| destinationClass.equals("")) {
					JOptionPane.showMessageDialog(relationPage, "Please enter classes first.");
					return;
				}
				// Checks to see if relationship already exists
				Relationships orig = sourceClass.getRelationship(sourceClassName, destinationClassName);
				if (orig != null) {
					JOptionPane.showMessageDialog(relationPage, "Relationship already exists!");
					return;
				}
				// Adds relationship
				sourceClass.addRelationship(sourceClassName, destinationClassName);
				destinationClass.addRelationship(sourceClassName, destinationClassName);
				JOptionPane.showMessageDialog(relationPage, "Relationship added successfully.");
			}
		});
		relationPage.getContentPane().add(btnNewButton);

		// Button to remove relationship from Instance.
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(265, 247, 159, 69);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Check that classes exist
				String sourceClassName;
				String destinationClassName;
				try {
					sourceClassName = comboBox.getSelectedItem().toString();
					destinationClassName = comboBox_1.getSelectedItem().toString();
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(relationPage, "Please enter classes first.");
					return;
				}
				Classes sourceClass = thisInstance.getClass(sourceClassName);
				Classes destinationClass = thisInstance.getClass(destinationClassName);
				// Source and destination cannot be the same
				if (sourceClassName.equals(destinationClassName)) {
					JOptionPane.showMessageDialog(relationPage, "Please enter a seperate source and destination.");
					return;
				}
				// Checks for empty input
				if (sourceClass == null || sourceClass.equals("") || destinationClass == null
						|| destinationClass.equals("")) {
					JOptionPane.showMessageDialog(relationPage, "Please enter classes first.");
					return;
				}
				// Looks for relationship
				Relationships orig = sourceClass.getRelationship(sourceClassName, destinationClassName);
				if (orig == null) {
					JOptionPane.showMessageDialog(relationPage, "Relationship not found!");
					return;
				}
				// Removes relationship
				if (sourceClass.removeRelationship(sourceClassName, destinationClassName)
						&& destinationClass.removeRelationship(sourceClassName, destinationClassName))
					JOptionPane.showMessageDialog(relationPage, "Relationship removed successfully.");
				else
					JOptionPane.showMessageDialog(relationPage, "Relationship removal failed.");
			}
		});
		relationPage.getContentPane().add(btnDelete);

		// Source label
		JLabel lblNewLabel = new JLabel("Source Class Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(107, 63, 150, 45);
		relationPage.getContentPane().add(lblNewLabel);

		// Destination label
		JLabel lblReplaceClassName = new JLabel("Destination Class Name");
		lblReplaceClassName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblReplaceClassName.setBounds(409, 63, 191, 45);
		relationPage.getContentPane().add(lblReplaceClassName);

		// Button returns to landing page.
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relationPage.dispose();
				LandingPage window = new LandingPage(thisInstance);
				window.homepage.setVisible(true);
			}
		});
		relationPage.getContentPane().add(btnHomepage);

		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Relationship Page Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);
		String s = "<html>Add: Enter name of the source class into 'Source' field, enter name of destination class into 'Destination' field<br> and click the 'Add' button.<br> This action will fail if a Relationship already exists with the given Source and Destination classes<br> Delete: Enter name of source class into 'Source' field, enter name of destination class into 'Destination' field, and click the 'Delete' button.<br> This action will fail if no Relationship exists with the given Source and Destination class names.</html>";

		lblNewLabel_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(relationPage, s);
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

		});
		relationPage.getContentPane().add(lblNewLabel_1);

		// List all button action
		JButton btnListAll = new JButton("List All");
		btnListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (thisInstance.classList != null && thisInstance.classList.length != 0) {
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Source");
					model.addColumn("Destination");
					// Creates list of already-listed relationships
					Object[][] allRelations = new Object[0][2];
					for (int i = 0; i < thisInstance.classList.length; i++) {
						Classes thisClass = thisInstance.classList[i];
						// Checks for empty relationship list
						if (thisClass.relationshipList != null && thisClass.relationshipList.length != 0) {
							for (int j = 0; j < thisClass.relationshipList.length; j++) {
								Object[] newObject = new Object[] { thisClass.relationshipList[j].source,
										thisClass.relationshipList[j].destination };
								// Checks if relationship was already printed
								if (!containsElement(allRelations, newObject)) {
									model.addRow(newObject);
									allRelations = addElement(allRelations, newObject);
								}
							}
						}
					}
					JTable table = new JTable(model);
					JOptionPane.showMessageDialog(null, new JScrollPane(table));
				} else {
					JOptionPane.showMessageDialog(relationPage, "No relationships currently exist!");
					return;
				}

			}
		});
		btnListAll.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnListAll.setBounds(465, 247, 159, 69);
		relationPage.getContentPane().add(btnListAll);

		// Populates comboBoxes
		if (thisInstance.classList != null) {
			if (thisInstance.classList.length != 0) {
				for (int i = 0; i < thisInstance.classList.length; i++) {
					model.addElement(thisInstance.classList[i].getName());
					model_1.addElement(thisInstance.classList[i].getName());
				}
				comboBox.setMaximumRowCount(thisInstance.classList.length);
			}
		}
		comboBox.setEditable(false);
		comboBox.setBounds(66, 113, 222, 69);
		comboBox.setModel(model);
		relationPage.getContentPane().add(comboBox);

		comboBox_1.setEditable(false);
		comboBox_1.setBounds(387, 113, 222, 69);
		relationPage.getContentPane().add(comboBox_1);

		relationPage.setVisible(true);
	}

	// Ensures input contains letters or numbers
	private static boolean containsAlphaNumeric(String input) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
		return p.matcher(input).find();
	}

	// Function for determining if array of object arrays contains object array.
	// Used to determine if relationship has already been printed.
	private static boolean containsElement(Object[][] array, Object[] search) {
		if (array == null || array.length == 0)
			return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i][0].equals(search[0]) && array[i][1].equals(search[1]))
				return true;
		}
		return false;
	}
	
	// Function for adding an object array to an array of object arrays.
	// Used to determine if relationship has already been printed.
	private static Object[][] addElement(Object[][] array, Object[] add) {
		Object[][] newObject;
		if (array != null && array.length != 0) {
			newObject = new Object[array.length + 1][add.length];
		} else {
			newObject = new Object[1][add.length];
		}
		if (array != null && array.length != 0) {
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					newObject[i][j] = array[i][j];
				}
			}
		}
		if (array != null && array.length != 0) {
			for (int i = 0; i < add.length; i++) {
				newObject[array.length][i] = add[i];
			}
		} else {
			for (int i = 0; i < add.length; i++) {
				newObject[0][i] = add[i];
			}
		}
		return newObject;
	}
}
