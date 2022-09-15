/**
 * Filename: LandingPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: A GUI for the group project UML editor.
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LandingPage {

	JFrame homepage;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
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

	public LandingPage(Instance input) {
		thisInstance = input;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// JFrame creation
		homepage = new JFrame();
		homepage.setTitle("Velcro CSCI 420 :: Homepage");
		homepage.setBounds(100, 100, 700, 500);
		homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homepage.getContentPane().setLayout(null);

		// Button to navigate to Classes page
		JButton btnNewButton = new JButton("Classes");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(76, 57, 207, 57);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage.dispose();
				ClassesPage classScreen = new ClassesPage(thisInstance);
			}
		});
		homepage.getContentPane().add(btnNewButton);

		// Button to navigate to Attributes page
		btnNewButton_1 = new JButton("Attributes");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(358, 57, 207, 57);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage.dispose();
				AttributesPage attributesScreen = new AttributesPage(thisInstance);
			}
		});
		homepage.getContentPane().add(btnNewButton_1);

		// Button to navigate to Relationships page
		btnNewButton_2 = new JButton("Relationships");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(76, 190, 207, 57);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage.dispose();
				RelationshipsPage relationsPage = new RelationshipsPage(thisInstance);
			}
		});
		homepage.getContentPane().add(btnNewButton_2);

		// Button to navigate to Save page
		btnNewButton_3 = new JButton("Save");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_3.setBounds(358, 190, 207, 57);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage.dispose();
				SavePage saveScreen = new SavePage(thisInstance);
			}
		});
		homepage.getContentPane().add(btnNewButton_3);

		// Button to navigate to Load page
		btnNewButton_4 = new JButton("Load");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_4.setBounds(358, 319, 207, 57);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage.dispose();
				LoadPage loadScreen = new LoadPage(thisInstance);
			}
		});
		homepage.getContentPane().add(btnNewButton_4);

		// Exit button
		btnNewButton_6 = new JButton("Exit");
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_6.setBounds(76, 319, 207, 57);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage.dispose();
				System.exit(0);
			}
		});
		homepage.getContentPane().add(btnNewButton_6);

		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Homepage Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);
		String s = "<html>Classes:&#9Add, Delete, Rename, and List Classes and their contents<br> Attributes:&#9Add/Delete attributes to/from classes, and Rename attributes<br> Relationships: Add and Delete Relationships between two Classes<br> Save:&#9Save current instance in a JSON file format<br> Load:&#9Load JSON files from directory<br> Exit:&#9Close the application</html>";

		// Overrides required, since Swing doesn't natively support clickable text
		lblNewLabel_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(homepage, s);
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

		});
		homepage.getContentPane().add(lblNewLabel_1);
	}
}
