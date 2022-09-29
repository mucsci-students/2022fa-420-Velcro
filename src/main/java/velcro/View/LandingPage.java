/**
 * Filename: LandingPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: A GUI for the group project UML editor.
 * 
 */

package velcro.View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import velcro.Controller.Controller;
import velcro.Model.*;
import velcro.View.*;

import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LandingPage {

	public static JFrame homepage;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_6;

	public static Instance thisInstance = new Instance();

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
		btnNewButton.addActionListener(Controller.classesButton);
		homepage.getContentPane().add(btnNewButton);

		// Button to navigate to Attributes page
		btnNewButton_1 = new JButton("Attributes");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(358, 57, 207, 57);
		btnNewButton_1.addActionListener(Controller.attributesButton);
		homepage.getContentPane().add(btnNewButton_1);

		// Button to navigate to Relationships page
		btnNewButton_2 = new JButton("Relationships");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(76, 164, 207, 57);
		btnNewButton_2.addActionListener(Controller.relationshipsButton);
		homepage.getContentPane().add(btnNewButton_2);

		// Button to navigate to Save page
		btnNewButton_3 = new JButton("Save");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_3.setBounds(358, 164, 207, 57);
		btnNewButton_3.addActionListener(Controller.saveButton);
		homepage.getContentPane().add(btnNewButton_3);

		// Button to navigate to Load page
		btnNewButton_4 = new JButton("Load");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_4.setBounds(358, 272, 207, 57);
		btnNewButton_4.addActionListener(Controller.loadButton);
		homepage.getContentPane().add(btnNewButton_4);

		// Button to display class list and contents.
		JButton btnClassList = new JButton("All Class Contents");
		btnClassList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClassList.setBounds(76, 275, 207, 54);
		btnClassList.addActionListener(Controller.classList);
		homepage.getContentPane().add(btnClassList);
		
		// Exit button.
		btnNewButton_6 = new JButton("Exit");
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_6.setBounds(221, 374, 207, 57);
		btnNewButton_6.addActionListener(Controller.exitButton);
		homepage.getContentPane().add(btnNewButton_6);

		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Homepage Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);
		lblNewLabel_1.addMouseListener(Controller.lanHelpListener);
		homepage.getContentPane().add(lblNewLabel_1);
	}
}
