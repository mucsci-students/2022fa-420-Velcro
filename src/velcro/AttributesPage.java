/**
 * Filename: AttributesPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: A page for interfacing with Attributes options.
 * 
 */

package velcro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AttributesPage {

	JFrame attributesPage = new JFrame();
	private JTextField textField;
	private JTextField txtuseOnlyFor;

	// Page buttons & layout.
	AttributesPage(Instance thisInstance) {
		attributesPage.setTitle("Velcro CSCI 420 :: Attributes");
		attributesPage.setBounds(100, 100, 700, 500);
		attributesPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		attributesPage.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(54, 119, 255, 51);
		attributesPage.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtuseOnlyFor = new JTextField();
		txtuseOnlyFor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtuseOnlyFor.setColumns(10);
		txtuseOnlyFor.setBounds(369, 119, 255, 51);
		attributesPage.getContentPane().add(txtuseOnlyFor);
		
		// Button to add attributes.
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(54, 247, 159, 69);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(attributesPage, "Please enter an attribute name.");
					return;
				}
				thisInstance.addAttribute(textField.getText());
				JOptionPane.showMessageDialog(attributesPage, "Attribute added successfully.");
			}});
		attributesPage.getContentPane().add(btnNewButton);
		
		// Button to delete attributes.
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(265, 247, 159, 69);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(attributesPage, "Please enter an attribute name.");
					return;
				}
				if (thisInstance.removeAttribute(textField.getText()))
					JOptionPane.showMessageDialog(attributesPage, "Attribute removed successfully.");
				else
					JOptionPane.showMessageDialog(attributesPage, "Attribute removal failed, attribute not found.");
			}});
		attributesPage.getContentPane().add(btnDelete);
		
		// Button to rename attributes.
		JButton btnRename = new JButton("Rename");
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRename.setBounds(472, 247, 159, 69);
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || txtuseOnlyFor.getText().equals("")) {
					JOptionPane.showMessageDialog(attributesPage, "Please enter an attribute and new name.");
					return;
				}
				Attributes orig = thisInstance.getAttribute(textField.getText());
				if (orig == null) {
					JOptionPane.showMessageDialog(attributesPage, "Attribute not found.");
					return;
				}
				if (orig.rename(txtuseOnlyFor.getText())) {
					JOptionPane.showMessageDialog(attributesPage, "Attribute renamed successfully.");
				} else {
					JOptionPane.showMessageDialog(attributesPage, "Attribute not found.");
				}
			}});
		attributesPage.getContentPane().add(btnRename);
		
		JLabel lblNewLabel = new JLabel("Attribute Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(129, 63, 115, 45);
		attributesPage.getContentPane().add(lblNewLabel);
		
		JLabel lblReplaceClassName = new JLabel("Rename Attribute Name");
		lblReplaceClassName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblReplaceClassName.setBounds(411, 63, 159, 45);
		attributesPage.getContentPane().add(lblReplaceClassName);
		
		// Button to return to the landing page.
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attributesPage.dispose();
				LandingPage window = new LandingPage(thisInstance);
				window.homepage.setVisible(true);
			}});
		attributesPage.getContentPane().add(btnHomepage);
		
		attributesPage.setVisible(true);
		
		
	}
}
