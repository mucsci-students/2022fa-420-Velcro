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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class LoadPage {

	JFrame loadPage = new JFrame();
	private JTextField textField;
	Instance thisInstance;

	// Page buttons & layout.
	LoadPage(Instance thisInstance) {
		this.thisInstance = thisInstance;
		loadPage.setTitle("Velcro CSCI 420 :: Load");
		loadPage.setBounds(100, 100, 700, 500);
		loadPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadPage.getContentPane().setLayout(null);
		
		// Text field for file address.
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(91, 131, 528, 51);
		textField.setColumns(10);
		loadPage.getContentPane().add(textField);
		
		// Opens a file browser to navigate to the target Json file.
		JButton btnNewButton = new JButton("Browse...");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(265, 204, 159, 69);
		btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            JFileChooser chooser = new JFileChooser();
	            chooser.setCurrentDirectory(new java.io.File("."));
	            chooser.setDialogTitle("Browse the folder to process");
	            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	            chooser.setAcceptAllFileFilterUsed(false);
	            // Filter for what files to show in browser.
	            FileNameExtensionFilter filter = new FileNameExtensionFilter(".json files", "json");
	            chooser.addChoosableFileFilter(filter);
	        	// Opens file browser dialogue.
	            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	            	textField.setText("" + chooser.getSelectedFile().getAbsolutePath());
	            }
	        }
	    });
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
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadPage.dispose();
				LandingPage window = new LandingPage(thisInstance);
				window.homepage.setVisible(true);
			}});
		loadPage.getContentPane().add(btnHomepage);
		
		// Button that loads the Json file at the given address.
		JButton btnNewButton_1 = new JButton("Load");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(265, 284, 159, 69);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Instance newInstance = new Instance();
					newInstance.loadJson(textField.getText());
					thisInstance.copy(newInstance);
					JOptionPane.showMessageDialog(loadPage, "Json file loaded.");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(loadPage, "Json file not found.");
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(loadPage, "Error loading Json file.");
				}
			}
		});
		loadPage.getContentPane().add(btnNewButton_1);
		
		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Load Page Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);
		String s = "<html>Load a JSON file by either entering the pathname<br> in the textbox or by browsing your file system</html>";		
								
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
									 
		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(loadPage, s);
		}
						 
		@Override
		public void mouseExited(MouseEvent e) {
		}
							 
		@Override
		public void mouseEntered(MouseEvent e) {
		}
							 
		});
		loadPage.getContentPane().add(lblNewLabel_1);
		
		// Makes page visible.
		loadPage.setVisible(true);	
	}
}