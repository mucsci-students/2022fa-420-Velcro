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
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

public class ClassesPage {

	// Required JFrame constructor and attributes.
	JFrame classPage = new JFrame();
	private JTextField textField;
	private JTextField textField2;

	// Page constructor.
	ClassesPage(Instance thisInstance) {
		
		// Buttons and labels for GUI.
		classPage.setTitle("Velcro CSCI 420 :: Classes");
		classPage.setBounds(100, 100, 700, 500);
		classPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		classPage.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(54, 119, 255, 51);
		classPage.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField2.setColumns(10);
		textField2.setBounds(369, 119, 255, 51);
		classPage.getContentPane().add(textField2);
		
		// Button to add Class.
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(54, 247, 159, 69);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checks for empty text field.
				if (!containsAlphaNumeric(textField.getText())) {
					JOptionPane.showMessageDialog(classPage, "Please enter a class name.");
					return;
				}
				// Checks for Class already existing.
				Classes orig = thisInstance.getClass(textField.getText());
				if (orig != null) {
					JOptionPane.showMessageDialog(classPage, "Class already exists!");
					return;
				}
				thisInstance.addClass(textField.getText());
				JOptionPane.showMessageDialog(classPage, "Class added successfully.");
			}});
		classPage.getContentPane().add(btnNewButton);
		
		// Button to delete class.
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(265, 247, 159, 69);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!containsAlphaNumeric(textField.getText())) {
					JOptionPane.showMessageDialog(classPage, "Please enter a class name.");
					return;
				}
				if (thisInstance.removeClass(textField.getText()))
					JOptionPane.showMessageDialog(classPage, "Class removed successfully.");
				else
					JOptionPane.showMessageDialog(classPage, "Class removal failed, class not found.");
			}});
		classPage.getContentPane().add(btnDelete);
		
		// Button to rename class.
		JButton btnRename = new JButton("Rename");
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRename.setBounds(472, 247, 159, 69);
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!containsAlphaNumeric(textField.getText()) || !containsAlphaNumeric(textField2.getText())) {
					JOptionPane.showMessageDialog(classPage, "Please enter a class and new name.");
					return;
				}
				Classes orig = thisInstance.getClass(textField.getText());
				if (orig == null) {
					JOptionPane.showMessageDialog(classPage, "Class not found.");
					return;
				}
				Classes orig2 = thisInstance.getClass(textField2.getText());
				if (orig2 != null) {
					JOptionPane.showMessageDialog(classPage, "Class already exists!");
					return;
				}
				if (orig.rename(textField2.getText())) {
					JOptionPane.showMessageDialog(classPage, "Class renamed successfully.");
				} else {
					JOptionPane.showMessageDialog(classPage, "Class not found.");
				}
			}});
		classPage.getContentPane().add(btnRename);
		
		JLabel lblNewLabel = new JLabel("Class Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(129, 63, 115, 45);
		classPage.getContentPane().add(lblNewLabel);
		
		JLabel lblReplaceClassName = new JLabel("Rename Class Name");
		lblReplaceClassName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblReplaceClassName.setBounds(411, 63, 159, 45);
		classPage.getContentPane().add(lblReplaceClassName);
		
		// Button to return to landing page.
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				classPage.dispose();
				LandingPage window = new LandingPage(thisInstance);
				window.homepage.setVisible(true);
			}});
		classPage.getContentPane().add(btnHomepage);
		
		// Button to display class list and contents.
		JButton btnClassList = new JButton("All Class Contents");
		btnClassList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClassList.setBounds(54, 359, 159, 69);
		btnClassList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (thisInstance.classList == null) {
					JOptionPane.showMessageDialog(classPage, "No current classes.");
					return;
				}
				// Aborts if classList is empty or null
		        if (thisInstance.classList == null || thisInstance.classList.length == 0){
		        	JOptionPane.showMessageDialog(classPage, "No classes currently exist!");
		        	return;
				}

		        // Draws a table outlining existing class's contents in dialog
				DefaultTableModel model = new DefaultTableModel();
		        model.addColumn("Name");
		        for (int i = 0; i<thisInstance.classList.length; i++) {
		        	model.addRow(new Object[]{thisInstance.classList[i].getName()});
		        }
		        JTable table = new JTable(model);
				JOptionPane.showMessageDialog(null, new JScrollPane(table));		
			}
		});
		classPage.getContentPane().add(btnClassList);
		
		// Button to display Class contents.
		JButton btnListContents = new JButton("Class Contents");
		btnListContents.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnListContents.setBounds(472, 359, 159, 69);
		btnListContents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checks for empty text field.
				if (!containsAlphaNumeric(textField.getText())) {
					JOptionPane.showMessageDialog(classPage, "Please enter a class name.");
					return;
				}
				// Checks if named class exists.
				Classes orig = thisInstance.getClass(textField.getText());
				if (orig == null) {
					JOptionPane.showMessageDialog(classPage, "Class not found.");
					return;			
				}

		        // Draws a table outlining existing class's contents in dialog
				DefaultTableModel model = new DefaultTableModel();
		        model.addColumn("Name");
		        model.addRow(new Object[]{orig.getName()});
		        JTable table = new JTable(model);
				JOptionPane.showMessageDialog(null, new JScrollPane(table));			
		}});		
		Classes orig = thisInstance.getClass(textField.getText());
		classPage.getContentPane().add(btnListContents);
		
		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Class Page Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29); 
		String s = "<html>'Class Name' : Field1 &#9 'Rename Class Name' : Field2 <br>To add classes, enter a unique classname in Field1 and click the 'Add' button.<br> The action will fail if the classname entered is already in use or is invalid.<br> To delete classes, enter the name of the class you want to delete in Field1 and click the 'Delete' button.<br> The action will fail if no class exists with the entered classname.<br> To rename a class, enter the name of the existing class Field1 and enter the new name you want it to have in Field2.<br> Once both fields are filled, click the 'Rename' button.<br> The action will fail if there is no existing class with a name matching what was entered in Field1, or if the name<br> entered in Field2 is already in use or is invalid.</html>";		
									
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
		
		classPage.setVisible(true);
	}
	
	private static boolean containsAlphaNumeric(String input) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
		return p.matcher(input).find();
	}
}
