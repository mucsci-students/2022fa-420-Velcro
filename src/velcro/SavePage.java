/**
 * Filename: SavePage.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: A page for exporting the current Instance into a Json file.
 * 
 */

package velcro;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Integers;

import javax.swing.JButton;

public class SavePage {

	// JFrame creation and required attributes.
	JFrame savePage = new JFrame();
	private JTextField textField;

	// Constructor.
	SavePage(Instance thisInstance) {
		savePage.setTitle("Velcro CSCI 420 :: Save");
		savePage.setBounds(100, 100, 700, 500);
		savePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		savePage.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(223, 131, 258, 51);
		textField.setColumns(10);
		savePage.getContentPane().add(textField);

		// Creates a file named whatever is input into the textfield, translates the Instance into Json, and saves it into the file.
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(265, 242, 159, 69);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {	
					if (!textField.getText().equals("")) {
						String newName = affixJson(textField.getText());
						File file = new File(newName);
						BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
						writer.append(thisInstance.printToJson());
						writer.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		savePage.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Save File As...");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(297, 75, 120, 45);
		savePage.getContentPane().add(lblNewLabel);

		// Button that returns to the landing page.
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savePage.dispose();
				LandingPage window = new LandingPage();
				window.homepage.setVisible(true);
			}
		});
		savePage.getContentPane().add(btnHomepage);

		savePage.setVisible(true);
	}

	// Ensures the input file name ends in .json.
	public static String affixJson(String input) {
		if (input.length() > 5 && input.substring(input.length() - 5).equals(".json")) {
			return input;
		}
		return input += ".json";
	}
}
