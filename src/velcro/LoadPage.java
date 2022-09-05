/**
 * Filename: LoadPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: A page for loading Json files into the GUI.
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
import javax.swing.JFileChooser;

public class LoadPage {

	JFrame loadPage = new JFrame();
	private JTextField textField;
	private JTextField txtuseOnlyFor;

	LoadPage() {
		loadPage.setTitle("Velcro CSCI 420 :: Load");
		loadPage.setBounds(100, 100, 700, 500);
		loadPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadPage.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(91, 131, 528, 51);
		textField.setColumns(10);
		loadPage.getContentPane().add(textField);
		
		// Opens a browser to navigate to the target Json file.
		JButton btnNewButton = new JButton("Browse...");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(265, 204, 159, 69);
		btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            JFileChooser chooser = new JFileChooser();
	            chooser.setCurrentDirectory(new java.io.File("."));
	            chooser.setDialogTitle("Browse the folder to process");
	            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            chooser.setAcceptAllFileFilterUsed(false);

	            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	            	textField.setText("" + chooser.getSelectedFile());
	            } else {
	                System.out.println("No Selection");
	            }
	        }
	    });
		loadPage.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Load File");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(319, 74, 81, 45);
		loadPage.getContentPane().add(lblNewLabel);
		
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 364, 159, 69);
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadPage.dispose();
				LandingPage window = new LandingPage();
				window.homepage.setVisible(true);
			}});
		loadPage.getContentPane().add(btnHomepage);
		
		// Button that loads the Json file at the given address.
		// TODO: use Gson to translate file contents and load into current Instance.
		JButton btnNewButton_1 = new JButton("Load");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(265, 284, 159, 69);
		loadPage.getContentPane().add(btnNewButton_1);
		
		loadPage.setVisible(true);	
	}
}
