package velcro;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class SavePage {

	JFrame savePage = new JFrame();
	private JTextField textField;
	private JTextField txtuseOnlyFor;

	SavePage() {
		savePage.setTitle("Velcro CSCI 420 :: Save");
		savePage.setBounds(100, 100, 700, 500);
		savePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		savePage.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(223, 131, 258, 51);
		textField.setColumns(10);
		savePage.getContentPane().add(textField);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(265, 242, 159, 69);
		btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {

	        }
	    });
		savePage.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Save File As...");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(297, 75, 120, 45);
		savePage.getContentPane().add(lblNewLabel);
		
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savePage.dispose();
				LandingPage window = new LandingPage();
				window.homepage.setVisible(true);
			}});
		savePage.getContentPane().add(btnHomepage);
		
		savePage.setVisible(true);
		
		
	}
}
