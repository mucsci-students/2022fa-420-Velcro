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
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RelationshipsPage {

	// JFrame creation and needed attributes.
	JFrame relationPage = new JFrame();
	private JTextField textField;
	private JTextField txtuseOnlyFor;

	// Constructor.
	RelationshipsPage(Instance thisInstance) {
		relationPage.setTitle("Velcro CSCI 420 :: Relationships");
		relationPage.setBounds(100, 100, 700, 500);
		relationPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		relationPage.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(54, 119, 255, 51);
		relationPage.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtuseOnlyFor = new JTextField();
		txtuseOnlyFor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtuseOnlyFor.setColumns(10);
		txtuseOnlyFor.setBounds(369, 119, 255, 51);
		relationPage.getContentPane().add(txtuseOnlyFor);

		// Button to add relationship to Instance.
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(150, 247, 159, 69);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || txtuseOnlyFor.getText().equals("")) {
					JOptionPane.showMessageDialog(relationPage, "Please enter a source and destination.");
					return;
				}
				thisInstance.addRelationship(textField.getText(), txtuseOnlyFor.getText());
				JOptionPane.showMessageDialog(relationPage, "Relationship added successfully.");
			}});
		relationPage.getContentPane().add(btnNewButton);
		
		// Button to remove relationship from Instance.
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(389, 247, 159, 69);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || txtuseOnlyFor.getText().equals("")) {
					JOptionPane.showMessageDialog(relationPage, "Please enter a source and destination.");
					return;
				}
				if (thisInstance.removeRelationship(textField.getText(), txtuseOnlyFor.getText()))
					JOptionPane.showMessageDialog(relationPage, "Relationship removed successfully.");
				else
					JOptionPane.showMessageDialog(relationPage, "Relationship removal failed, relationship not found.");
			}});
		relationPage.getContentPane().add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("Source Class Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(107, 63, 150, 45);
		relationPage.getContentPane().add(lblNewLabel);
		
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
			}});
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
		
		relationPage.setVisible(true);
	}
}
