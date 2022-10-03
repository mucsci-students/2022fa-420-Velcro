/**
 * Filename: LoadPage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: A page for loading Json files into the GUI.
 * 
 */

package main.java.velcro.View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import com.google.gson.Gson;

import main.java.velcro.Controller.Controller;
import main.java.velcro.Model.*;
import main.java.velcro.View.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class LoadPage {

	public static JFrame loadPage = new JFrame();
	public static JTextField textField;
	public static Instance addInstance;
	public static Instance thisInstance;
	public static JButton btnNewButton_1;

	// Page buttons & layout.
	public LoadPage(Instance thisInstance) {
		loadPage.setTitle("Velcro CSCI 420 :: Load");
		loadPage.setBounds(100, 100, 700, 500);
		loadPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadPage.getContentPane().setLayout(null);

		// Text field for file address.
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(91, 131, 528, 51);
		textField.setColumns(10);
		textField.getDocument().addDocumentListener(Controller.loadLetterListener);
		loadPage.getContentPane().add(textField);

		// Opens a file browser to navigate to the target Json file.
		JButton btnNewButton = new JButton("Browse...");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(265, 204, 159, 69);
		btnNewButton.addActionListener(Controller.loadBrowser);
		loadPage.getContentPane().add(btnNewButton);

		// Load file text.
		JLabel lblNewLabel = new JLabel("Load File");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(319, 74, 81, 45);
		loadPage.getContentPane().add(lblNewLabel);

		// Home page button.
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 364, 159, 69);
		btnHomepage.addActionListener(Controller.loadHomeButton);
		loadPage.getContentPane().add(btnHomepage);

		// Button that loads the Json file at the given address.
		btnNewButton_1 = new JButton("Load");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(265, 284, 159, 69);
		btnNewButton_1.addActionListener(Controller.loadFile);
		loadPage.getContentPane().add(btnNewButton_1);

		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Load Page Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);
		lblNewLabel_1.addMouseListener(Controller.loadHelpListener);
		loadPage.getContentPane().add(lblNewLabel_1);

		// Makes page visible.
		loadPage.setVisible(true);
	}
	

}