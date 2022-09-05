/**
 * Filename: LandingPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Date: September 1st, 2022
 * Description: A mockup GUI.
 * 
 */

package velcro;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LandingPage {

	JFrame homepage;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;

	Instance thisInstance = new Instance();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LandingPage window = new LandingPage();
					window.homepage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LandingPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		homepage = new JFrame();
		homepage.setTitle("Velcro CSCI 420 :: Homepage");
		homepage.setBounds(100, 100, 700, 500);
		homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homepage.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Classes");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(76, 48, 207, 57);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage.dispose();
				ClassesPage classScreen = new ClassesPage();
			}});
		homepage.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Attributes");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(358, 48, 207, 57);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage.dispose();
				AttributesPage attributesScreen = new AttributesPage();
			}});
		homepage.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Relationships");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(76, 147, 207, 57);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage.dispose();
				RelationshipsPage relationsPage = new RelationshipsPage();
			}});
		homepage.getContentPane().add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Save");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_3.setBounds(358, 147, 207, 57);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage.dispose();
				SavePage saveScreen = new SavePage(thisInstance);
			}});
		homepage.getContentPane().add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Load");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_4.setBounds(76, 249, 207, 57);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage.dispose();
				LoadPage loadScreen = new LoadPage();
			}});
		homepage.getContentPane().add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("Help");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_5.setBounds(358, 249, 207, 57);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage.dispose();
				HelpPage helpScreen = new HelpPage();
			}});
		homepage.getContentPane().add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("Exit");
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_6.setBounds(215, 365, 207, 57);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage.dispose();
				System.exit(0);
			}});
		homepage.getContentPane().add(btnNewButton_6);
	}
}
