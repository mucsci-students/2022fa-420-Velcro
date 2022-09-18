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
import javax.swing.ListSelectionModel;

import org.apache.commons.io.FileUtils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class SavePage extends JPanel {
	// JFrame creation and required attributes.
	JFrame savePage = new JFrame();
	Object[] recentSaves = new Object[0];
	Object[] fileNames = localFiles();
	DefaultListModel model = new DefaultListModel();

	// Constructor with Instance parameter, to preserve current data
	SavePage(Instance thisInstance) {
		
		// Creates content page for displaying file list
		savePage.setContentPane(new SavePage(model));
		savePage.pack();
		
		// Loads saves from temp file
		recentSaves = loadRecentSavesFile(recentSaves);
		
		// Updates model with local files and saved history
		model = addLocalsToModel(model, fileNames);
		try {
			model = addSaveHistoryToModel(model);
		} catch (IOException e2) {
		}

		savePage.setTitle("Velcro CSCI 420 :: Save");
		savePage.setBounds(100, 100, 700, 500);
		savePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		savePage.getContentPane().setLayout(null);
		
		// Button that returns to the landing page.
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 381, 159, 69);
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savePage.dispose();
				LandingPage window = new LandingPage(thisInstance);
				window.homepage.setVisible(true);
			}
		});
		savePage.getContentPane().add(btnHomepage);

		// Help text with help info message.
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setToolTipText("Save Page Help");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(623, 11, 51, 29);
		String s = "<html>Save: Save file by entering a filename in the textbox.<br> '.json' extension will be added automatically to filename if not entered by user.<br> This action will fail if a file already exists with the entered name or<br> or if the entered filename is invalid.</html>";

		// Help link. Overrides are required, as Swing doesn't natively support
		// clickable text
		// Help link. Overrides are required, as Swing doesn't natively support clickable text
		lblNewLabel_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(savePage, s);
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

		});
		savePage.getContentPane().add(lblNewLabel_1);

		// Save as button. Loads file explorer dialogue and prompts user for save name/location.
		JButton btnBrowse = new JButton("Save as...");
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBrowse.setBounds(265, 286, 159, 69);
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
				fileChooser.setDialogTitle("Specify a file to save");
				int userSelection = fileChooser.showSaveDialog(savePage);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					if (!fileToSave.getAbsolutePath().equals("")) {
						String newName = affixJson(fileToSave.getAbsolutePath());
						File newFile = new File(newName);
						// Check to make sure file doesn't already exist.
						if (newFile.exists()) {
							Object[] options = { "OK", "CANCEL" };
							// Option to overwrite file.
							int n = JOptionPane.showOptionDialog(savePage, "File already exists! Click OK to overwrite!", "WARNING", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
							if (n==1) {
								JOptionPane.showMessageDialog(savePage, "Save file cancelled.");
								return;
							} else {
								BufferedWriter writer;
								try {
									// Writes the json file.
									writer = new BufferedWriter(new FileWriter(newFile, true));
									writer.append(thisInstance.printToJson());
									writer.close();
									// Updates recent saves
									recentSaves = addObject(recentSaves, newName, true);							
									recentSaves = saveRecentSavesFile(recentSaves);		
								} catch (IOException e1) {
								}
								JOptionPane.showMessageDialog(savePage, "File overwritten successfully.");
								return;
							}
						}
						BufferedWriter writer;
						try {
							// Writes the json file.
							writer = new BufferedWriter(new FileWriter(newFile, true));
							writer.append(thisInstance.printToJson());
							writer.close();
							// Updates recent saves
							recentSaves = addObject(recentSaves, newName, true);							
							recentSaves = saveRecentSavesFile(recentSaves);			
							// Updates the file list
							model.addElement(newName);
						} catch (IOException e1) {
						}
						JOptionPane.showMessageDialog(savePage, "File saved successfully.");
					}
				}
			}
		});
		savePage.getContentPane().add(btnBrowse);
		
		// Button for clearing save history.
		JButton btnBrowse_2 = new JButton("Clear Save History");
		btnBrowse_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tmpdir = System.getProperty("java.io.tmpdir");
				File newFile = new File(tmpdir + "\\velcroRecentSaves.txt");
				try {
					FileUtils.forceDelete(newFile);
				} catch (IOException e1) {
				}
				model.clear();
			    fileNames = localFiles();
				model = addLocalsToModel(model, fileNames);
				JOptionPane.showMessageDialog(savePage, "Save file history cleared.");
			}
		});
		btnBrowse_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBrowse_2.setBounds(504, 381, 150, 69);
		savePage.getContentPane().add(btnBrowse_2);

		// Label for attribute name
		JLabel lblNewLabel = new JLabel("Local Files and Recent Save Files");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(215, 13, 300, 45);
		savePage.getContentPane().add(lblNewLabel);
		
		savePage.setVisible(true);
	}

	// Ensures the input file name ends in .json.
	private static String affixJson(String input) {
		if (input.length() > 5 && input.substring(input.length() - 5).equals(".json")) {
			return input;
		}
		return input += ".json";
	}

	// Returns an array of local json file names.
	private static Object[] localFiles() {
		File path = new File(System.getProperty("user.dir"));
		File[] files = path.listFiles();
		Object[] output = new Object[0];
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				if (files[i].getName().length() > 5
						&& files[i].getName().substring(files[i].getName().length() - 5).equals(".json"))
					output = addObject(output, files[i], true);
			}
		}
		return output;
	}

	// Add Object, with option to remove duplicates.
	private static Object[] addObject(Object[] array, Object add, Boolean removeDuplicates) {
		if (array == null || array.length == 0) {
			Object[] newArray = { add };
			return newArray;
		}
		// If including duplicates...
		if (!removeDuplicates) {
			Object[] newArray = Arrays.copyOf(array, array.length + 1);
			newArray[array.length] = add;
			return newArray;
		}
		
		String contents = array.toString();
		// If not including duplicates, but value is unique.
		if (!contents.contains(add.toString()) && !contents.contains(" " + add.toString())) {
			Object[] newArray = Arrays.copyOf(array, array.length + 1);
			newArray[array.length] = add;
			return newArray;
		}
		
		// If duplicated and not including duplicate objects, return original array.
		return array;
	}

	// Adds local files to file list model.
	private static DefaultListModel addLocalsToModel(DefaultListModel inputModel, Object[] addedFiles) {
		if (addedFiles != null && addedFiles.length > 0) {
			for (int i = 0; i < addedFiles.length; i++) {
				String contents = inputModel.toString();
				// Compares objects as strings to prevent duplicates.
				if (!contents.contains(addedFiles[i].toString()) && !contents.contains(" " + addedFiles[i].toString())) {
					inputModel.addElement(addedFiles[i]);
				}
			}
		}
		return inputModel;
	}

	// Adds the save history to the file listing model.
	private static DefaultListModel addSaveHistoryToModel(DefaultListModel inputModel) throws IOException {
		String tmpdir = System.getProperty("java.io.tmpdir");
		if (!tmpdir.equals("")) {
			File newFile = new File(tmpdir + "\\velcroRecentSaves.txt");
			if (newFile.exists()) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(newFile));
					String str = br.readLine();
					if (str != null && str.length() >= 1) {
						str = str.substring(1, str.length() - 1);
						String[] tokens = str.split(", ");
						for (int i = 0; i < tokens.length; i++) {
							String contents = inputModel.toString();
							tokens[i] = tokens[i].trim();
							// Prevents duplicates from displaying.
							if (!contents.contains(tokens[i]) && !contents.contains(" " + tokens[i])) {
								inputModel.addElement(tokens[i]);
							}
						}
					}
					br.close();
				} catch (FileNotFoundException e) {
				}
			}
		}
		return inputModel;
	}

	// Saves the recent save files to a file in the user's temp folder.
	private static Object[] saveRecentSavesFile(Object[] recentSaves) throws IOException {
		String tmpdir = System.getProperty("java.io.tmpdir");
		if (!tmpdir.equals("")) {
			File newFile = new File(tmpdir + "\\velcroRecentSaves.txt");
			// If the file exists, import data
			if (newFile.exists()) {
				//recentSaves = loadRecentSavesFile(recentSaves);
				FileUtils.forceDelete(newFile);
			}
			BufferedWriter writer;
			try {
				// Writes the file.
				if (recentSaves != null && recentSaves.length > 0) {
					writer = new BufferedWriter(new FileWriter(newFile, true));
					writer.append(Arrays.toString(recentSaves));
					writer.close();
				}
			} catch (IOException e1) {
			}
		}
		return recentSaves;
	}

	// Returns an array of file names from the locally-saved recent saves file.
	private static Object[] loadRecentSavesFile(Object[] recentSaves) {
		String tmpdir = System.getProperty("java.io.tmpdir");
		if (!tmpdir.equals("")) {
			File newFile = new File(tmpdir + "\\velcroRecentSaves.txt");
			if (newFile.exists()) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(newFile));
					String str;
					str = br.readLine();
					if (str != null && str.length() >= 1) {
						str = str.substring(1, str.length() - 1);
						Object[] tokens = str.split(",");
						for (int i = 0; i < tokens.length; i++) {
							recentSaves = addObject(recentSaves, tokens[i], true);
						}
					}
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return recentSaves;
	}

	// Constructor to act as parent for file display window.
	public SavePage(DefaultListModel model) {
		JList list1 = new JList(model);
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane list1scr = new JScrollPane(list1);
		list1scr.setPreferredSize(new Dimension(500, 225));
		setLayout(null);
		list1scr.setBounds(new Rectangle(new Point(100, 50), list1scr.getPreferredSize()));
		list1.setVisibleRowCount(8);
		add(list1scr);
	}

	// Method to remove duplicates from an array.
	public static <E> E[] removeDuplicates(E[] array) {
		List<E> list = Arrays.asList(array);
		HashSet<E> set = new HashSet<E>();
		set.addAll(list);
		return (E[]) set.toArray();
	}
}