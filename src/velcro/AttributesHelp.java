/**
 * Filename: AttributesHelp.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Help page for navigating GUI.
 * 
 */

package velcro;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class AttributesHelp {

	// Required JFrame and attributes.
	JFrame attHelp = new JFrame();
	private JTextField txtuseOnlyFor;
	private JLabel lblNewLabel;

	// Constructor for help page.
	AttributesHelp(Instance thisInstance) {
		// Buttons and text for GUI.
		attHelp.setTitle("Velcro CSCI 420 :: Attributes :: Help");
		attHelp.setBounds(100, 100, 700, 500);
		attHelp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		attHelp.getContentPane().setLayout(null);
		
		// Button for returning to the Attributes page.
		JButton btnReturn = new JButton("Return");
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnReturn.setBounds(265, 359, 159, 69);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attHelp.dispose();
				AttributesPage window = new AttributesPage(thisInstance);
				window.homepage.setVisible(true);
			}});
		helpPage.getContentPane().add(btnHomepage);
		
		lblNewLabel = new JLabel("Attribute Help");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setText("<html>To add an Attribute, enter a unique attribute name in the left-most textbox and press the 'Add' button. The action will fail if there is already an attribute with that name or if the entered name if invalid.<br>
                               To delete an Attribute, enter the name of the attribute that you wish to delete in the textbox beneath 'Attribute Name' and press the 'Delete' button. The action will fail if there is no attribute with the given name.<br>
                               To rename an Attribute, enter the current name of the attribute that you wish to change in the textbox beneath 'Attribute Name', then type the name that you wish to change it to in the textbox beneath 'Change Attribute'. To initiate the action, hit the 'Rename' button once both the current and new names have been entered. The action will fail if there is no existing attribute with the given name or if the new name being entered is either already in use or is invalid.</html>");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(10, 11, 664, 325);
		attHelp.getContentPane().add(lblNewLabel);
		
		attHelp.setVisible(true);
	}
}
