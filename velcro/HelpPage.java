package velcro;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class HelpPage {

	JFrame helpPage = new JFrame();
	private JTextField txtuseOnlyFor;
	private JLabel lblNewLabel;

	HelpPage() {
		helpPage.setTitle("Velcro CSCI 420 :: Help");
		helpPage.setBounds(100, 100, 700, 500);
		helpPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		helpPage.getContentPane().setLayout(null);
		
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpPage.dispose();
				LandingPage window = new LandingPage();
				window.homepage.setVisible(true);
			}});
		helpPage.getContentPane().add(btnHomepage);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setText("<html>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</html>");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(10, 11, 664, 325);
		helpPage.getContentPane().add(lblNewLabel);
		
		helpPage.setVisible(true);
		
		
	}
}
