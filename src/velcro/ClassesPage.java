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
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

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
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(classPage, "Please enter a class name.");
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
				if (textField.getText().equals("")) {
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
				if (textField.getText().equals("") || textField2.getText().equals("")) {
					JOptionPane.showMessageDialog(classPage, "Please enter a class and new name.");
					return;
				}
				Classes orig = thisInstance.getClass(textField.getText());
				if (orig == null) {
					JOptionPane.showMessageDialog(classPage, "Class not found.");
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
		
		// Button to display class list.
		JButton btnClassList = new JButton("Class List");
		btnClassList.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnClassList.setBounds(54, 359, 159, 69);
		btnClassList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (thisInstance.classList == null) {
					JOptionPane.showMessageDialog(classPage, "No current classes.");
					return;
				}
				String output = thisInstance.classListToString();
				JOptionPane.showMessageDialog(classPage, output);
			}
		});
		classPage.getContentPane().add(btnClassList);
		
		// Button to display Class contents.
		JButton btnListContents = new JButton("List Contents");
		btnListContents.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnListContents.setBounds(472, 359, 159, 69);
		btnListContents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(classPage, "Please enter a class name.");
					return;
				}
				Classes orig = thisInstance.getClass(textField.getText());
				if (orig == null) {
					JOptionPane.showMessageDialog(classPage, "Class not found.");
					return;			
				}
				String output = "Name: " + orig.getName();
				JOptionPane.showMessageDialog(classPage, output);					
		}});		
		Classes orig = thisInstance.getClass(textField.getText());
		classPage.getContentPane().add(btnListContents);
		
		classPage.setVisible(true);
	}
}
