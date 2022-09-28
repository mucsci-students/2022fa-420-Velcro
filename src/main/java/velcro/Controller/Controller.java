package velcro.Controller;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

import velcro.Model.*;
import velcro.View.*;

public class Controller {

	private Instance model;
	private String view;
	static String renameTextField = "";

	public Controller(Instance model, String view) throws Exception {
		initController(model, view);
	}

	// Method for executing various pages.
	public static void initController(Instance mode, String v) throws Exception {
		switch (v) {
		case "LandingPage":
			LandingPage landingWindow = new LandingPage(mode);
			landingWindow.homepage.setVisible(true);
			break;
		case "AttributesPage":
			AttributesPage attributesWindow = new AttributesPage(mode);
			initAttributesPage();
			break;
		case "ClassesPage":
			ClassesPage classesWindow = new ClassesPage(mode);
			break;
		case "SavePage":
			SavePage saveWindow = new SavePage(mode);
			break;
		case "LoadPage":
			LoadPage loadWindow = new LoadPage(mode);
			break;
		case "RelationshipsPage":
			RelationshipsPage relationshipsWindow = new RelationshipsPage(mode);
			break;
		}

	}



	



	/** Methods related to AttributesPage.
	**
	**
	**/
		// Overrides required; Swing does not natively support clickable text
	public static MouseAdapter attHelpListener = new MouseAdapter() {
		String s = "<html>'Attribute Name' : Field1 &#9 'Rename Attribute Name' : Field2<br>To add an Attribute, enter a unique attribute name in Field1 and press the 'Add' button.<br> The action will fail if there is already an attribute with that name or if the entered name if invalid.<br> To delete an Attribute, enter the name of the attribute that you wish to delete in Field1 and press the 'Delete' button.<br> The action will fail if there is no attribute with the given name.<br>To rename an Attribute, enter the current name of the attribute that you wish to change in Field1,<br> and the name that you wish to change it to in Field2.<br> To initiate the action, hit the 'Rename' button once both fields have been entered. The action will fail if there is no<br> existing attribute with the given name or if the new name being entered is either already in use or is invalid.</html>";

		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(AttributesPage.attributesPage, s);
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}
	};

	// Initially populates Attributes combo box
	public static void initAttributesPage() {
		Classes thisClass;
		try {
			thisClass = AttributesPage.thisInstance.getClass(AttributesPage.comboBox.getSelectedItem().toString());
			if (AttributesPage.thisInstance.getClass(AttributesPage.comboBox.getSelectedItem().toString()) != null
					&& AttributesPage.thisInstance.classList.size() != 0) {
				if (thisClass != null && thisClass.attributeList != null && thisClass.attributeList.size() != 0) {
					for (int i = 0; i < thisClass.attributeList.size(); i++) {
						AttributesPage.model_1.addElement(thisClass.attributeList.get(i).getName());
					}
					AttributesPage.comboBox_1.setMaximumRowCount(thisClass.attributeList.size());
				}
			}
		} catch (NullPointerException e1) {
		}
	}

	// Initially populates class combo box
	public static void populateClasses() {
		if (AttributesPage.thisInstance.classList != null) {
			if (AttributesPage.thisInstance.classList.size() != 0) {
				for (int i = 0; i < AttributesPage.thisInstance.classList.size(); i++) {
					AttributesPage.model.addElement(AttributesPage.thisInstance.classList.get(i).getName());
				}
				AttributesPage.comboBox.setMaximumRowCount(AttributesPage.thisInstance.classList.size());
			}
		}
	}

	// Prevent button use with empty text field.
	public static ActionListener att = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter an attribute name!");
		}
	};

	// Prevent button use with empty text field.
	public static ActionListener delAtt = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter an attribute name!");
		}
	};

	// Prevent button use with empty text field.
	public static ActionListener renAtt = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter an attribute name!");

		}
	};

	// Action Listener for adding attributes.
	public static ActionListener finalAtt = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = AttributesPage.btnNewButton;
				try {
					input.removeActionListener(att);
				} catch (Exception e1) {
				}
				att = new Controller.AddAttribute(AttributesPage.thisInstance,
						AttributesPage.comboBox.getSelectedItem(), AttributesPage.comboBox_1.getSelectedItem(),
						AttributesPage.attributesPage, AttributesPage.model_1);
				input.addActionListener(att);
			}
		}
	};

	// Action listener for deleting attributes.
	public static ActionListener finalDelAtt = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = AttributesPage.btnDelete;
				try {
					input.removeActionListener(delAtt);
				} catch (Exception e1) {
				}
				delAtt = new Controller.DelAttribute(AttributesPage.thisInstance,
						AttributesPage.comboBox.getSelectedItem(), AttributesPage.comboBox_1.getSelectedItem(),
						AttributesPage.attributesPage, AttributesPage.model_1);
				input.addActionListener(delAtt);
			}
		}
	};

	// Action listener for renaming attributes.
	public static ActionListener finalRenAtt = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = AttributesPage.btnRename;
				try {
					input.removeActionListener(renAtt);
				} catch (Exception e1) {
				}
				renAtt = new Controller.RenameAttribute(AttributesPage.thisInstance,
						AttributesPage.comboBox.getSelectedItem(), AttributesPage.comboBox_1.getSelectedItem(),
						AttributesPage.attributesPage, AttributesPage.model_1, renameTextField);
				input.addActionListener(renAtt);
			}
		}
	};

	// Method for adding attributes.
	public static class AddAttribute implements ActionListener {
		private Instance thisInstance;
		private String comboBoxContents;
		private String comboBox_1SelectedItem;
		private JFrame page;
		private DefaultComboBoxModel<String> model;

		// Constructor.
		public AddAttribute(Instance thisInstance, Object comboBoxContents, Object comboBox_1SelectedItem, JFrame page,
				DefaultComboBoxModel<String> model) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBoxContents = comboBoxContents.toString();
			if (comboBox_1SelectedItem != null)
				this.comboBox_1SelectedItem = comboBox_1SelectedItem.toString();
			this.page = page;
			this.model = model;
		}

		public void actionPerformed(ActionEvent e) {
			Classes thisClass;
			// Checks if classes are entered already
			if (comboBoxContents == null || comboBoxContents.equals("")) {
				JOptionPane.showMessageDialog(page, "Please enter classes first.");
				return;
			}
			try {
				thisClass = thisInstance.getClass(comboBoxContents);
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Please enter classes first.");
				return;
			}

			// Checks if combo box field is somehow empty
			if (comboBox_1SelectedItem == null || comboBox_1SelectedItem.equals("")
					|| !containsAlphaNumeric(comboBox_1SelectedItem)) {
				JOptionPane.showMessageDialog(page, "Please enter an attribute name.");
				return;
			}
			// Checks if Attribute already exists.
			try {
				Attributes orig = thisClass.getAttribute(comboBox_1SelectedItem);
				String test = orig.getName();
			} catch (NullPointerException e1) {
				// Adds attribute, updates combo box
				thisClass.addAttribute(comboBox_1SelectedItem);
				model.addElement(comboBox_1SelectedItem);
				JOptionPane.showMessageDialog(page, "Attribute added successfully.");
				return;
			}

			JOptionPane.showMessageDialog(page, "Attribute already exists!");
		}
	}

	// Method for deleting attributes.
	public static class DelAttribute implements ActionListener {
		private Instance thisInstance;
		private String comboBoxContents;
		private String comboBox_1SelectedItem;
		private JFrame page;
		private DefaultComboBoxModel<String> model;

		// Constructor.
		public DelAttribute(Instance thisInstance, Object comboBoxContents, Object comboBox_1SelectedItem, JFrame page,
				DefaultComboBoxModel<String> model) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBoxContents = comboBoxContents.toString();
			if (comboBox_1SelectedItem != null)
				this.comboBox_1SelectedItem = comboBox_1SelectedItem.toString();
			this.page = page;
			this.model = model;
		}

		public void actionPerformed(ActionEvent e) {
			Classes thisClass;
			// Tests if classes already exist
			try {
				thisClass = thisInstance.getClass(comboBoxContents);
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Please enter classes first.");
				return;
			}
			// Tests if any attributes already exist
			if (thisClass.attributeList == null || thisClass.attributeList.size() == 0) {
				JOptionPane.showMessageDialog(page, "No attributes currently!");
				return;
			}
			// Checks to see if combo box is empty
			if (comboBox_1SelectedItem.equals("") || !containsAlphaNumeric(comboBox_1SelectedItem)) {
				JOptionPane.showMessageDialog(page, "Please enter an attribute name.");
				return;
			}
			// Removes attribute, updates combo box
			if (thisClass.removeAttribute(comboBox_1SelectedItem)) {
				model.removeElement(comboBox_1SelectedItem);
				JOptionPane.showMessageDialog(page, "Attribute removed successfully.");
			} else
				JOptionPane.showMessageDialog(page, "Attribute removal failed, attribute not found.");
		}

	}

	// Document listener to detect letters input.
	public static DocumentListener attLetterListener = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) {
			doSomething();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			doSomething();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			doSomething();
		}

		// On any change, redefine the action listener.
		public void doSomething() {
			try {
				AttributesPage.btnRename.removeActionListener(renAtt);
			} catch (Exception e) {
			}
			renameTextField = AttributesPage.txtuseOnlyFor.getText();
			JButton input = AttributesPage.btnRename;
			renAtt = new Controller.RenameAttribute(AttributesPage.thisInstance,
					AttributesPage.comboBox.getSelectedItem(), AttributesPage.comboBox_1.getSelectedItem(),
					AttributesPage.attributesPage, AttributesPage.model_1, renameTextField);
			input.addActionListener(renAtt);
		}
	};

	// Method for renaming attributes.
	public static class RenameAttribute implements ActionListener {
		private Instance thisInstance;
		private String comboBoxContents;
		private String comboBox_1SelectedItem;
		private JFrame page;
		private DefaultComboBoxModel<String> model;
		private String comboBox_2Contents;

		// Constructor.
		public RenameAttribute(Instance thisInstance, Object comboBoxContents, Object comboBox_1SelectedItem,
				JFrame page, DefaultComboBoxModel<String> model, Object comboBox_2Contents) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBoxContents = comboBoxContents.toString();
			if (comboBox_1SelectedItem != null)
				this.comboBox_1SelectedItem = comboBox_1SelectedItem.toString();
			if (comboBox_2Contents != null)
				this.comboBox_2Contents = comboBox_2Contents.toString();
			this.page = page;
			this.model = model;
		}

		public void actionPerformed(ActionEvent e) {
			Classes thisClass;
			// Tests if classes already exist
			try {
				thisClass = thisInstance.getClass(comboBoxContents);
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Please enter classes first.");
				return;
			}
			// Checks for empty text box.
			if (comboBox_1SelectedItem == null || comboBox_2Contents == null || comboBox_1SelectedItem.equals("")
					|| comboBox_2Contents.equals("") || !containsAlphaNumeric(comboBox_1SelectedItem)
					|| !containsAlphaNumeric(comboBox_2Contents)) {

				JOptionPane.showMessageDialog(page, "Please enter an attribute and new name.");
				JOptionPane.showMessageDialog(page, this.comboBox_1SelectedItem + " . " + this.comboBox_2Contents);
				return;
			}

			// Checks if Attribute exists.
			try {
				Attributes orig = thisClass.getAttribute(comboBox_1SelectedItem);
				String test = orig.getName();
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Attribute not found.");
				return;
			}

			// Checks if rename is already taken.
			Attributes orig2 = thisClass.getAttribute(comboBox_2Contents);
			try {
				String test2 = orig2.getName();
			} catch (NullPointerException e1) {
				// Renames attribute, updates Attribute combo box
				Attributes orig3 = thisClass.getAttribute(comboBox_1SelectedItem);
				if (orig3.rename(comboBox_2Contents)) {
					model.addElement(comboBox_2Contents);
					model.removeElement(comboBox_1SelectedItem);
					AttributesPage.comboBox_1.setSelectedItem(comboBox_2Contents);
					JOptionPane.showMessageDialog(page, "Attribute renamed successfully.");
					return;
				} else {
					JOptionPane.showMessageDialog(page, "Attribute not found.");
					return;
				}
			}
			JOptionPane.showMessageDialog(page, "Attribute already exists!");
			return;
		}

	}
	
	/** Methods related to ClassesPage.
	**
	**
	**/
	
	// Overrides required; Swing does not natively support clickable text
	public static MouseAdapter claHelpListener = new MouseAdapter() {
		String s = "<html>'Class Name' : Field1 &#9 'Rename Class Name' : Field2 <br>To add classes, enter a unique classname in Field1 and click the 'Add' button.<br> The action will fail if the classname entered is already in use or is invalid.<br> To delete classes, enter the name of the class you want to delete in Field1 and click the 'Delete' button.<br> The action will fail if no class exists with the entered classname.<br> To rename a class, enter the name of the existing class Field1 and enter the new name you want it to have in Field2.<br> Once both fields are filled, click the 'Rename' button.<br> The action will fail if there is no existing class with a name matching what was entered in Field1, or if the name<br> entered in Field2 is already in use or is invalid.</html>";

		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(ClassesPage.classPage, s);
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}
	};

	// Prevent button use with empty text field.
	public static ActionListener cla = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter a class name!");
		}
	};

	// Action Listener for adding classes.
	public static ActionListener finalAddClass = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = ClassesPage.btnNewButton;
				try {
					input.removeActionListener(cla);
				} catch (Exception e1) {
				}
				cla = new Controller.AddClass(ClassesPage.thisInstance, ClassesPage.comboBox_2.getSelectedItem(),
						ClassesPage.classPage, ClassesPage.model, ClassesPage.model_1, ClassesPage.model_2);
				input.addActionListener(cla);
			}
		}
	};

	// Method for adding classes.
	public static class AddClass implements ActionListener {
		private JFrame page;
		private Instance thisInstance;
		private String comboBox_2;
		private DefaultComboBoxModel<String> model1;
		private DefaultComboBoxModel<String> model2;
		private DefaultComboBoxModel<String> model3;

		// Constructor.
		public AddClass(Instance thisInstance, Object comboBoxContents, JFrame page,
				DefaultComboBoxModel<String> model1, DefaultComboBoxModel<String> model2,
				DefaultComboBoxModel<String> model3) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBox_2 = comboBoxContents.toString();
			this.page = page;
			this.model1 = model1;
			this.model2 = model2;
			this.model3 = model3;
		}

		public void actionPerformed(ActionEvent e) {
			if (e == null && e.getSource().toString() != null) {
				JOptionPane.showMessageDialog(page, "Please enter a class name.");
				return;
			}
			// Checks for empty text field.
			if (comboBox_2 == null || comboBox_2.equals("") || !containsAlphaNumeric(comboBox_2)) {
				JOptionPane.showMessageDialog(page, "Please enter a class name.");
				return;
			}
			Classes orig;
			orig = thisInstance.getClass(comboBox_2);
			if (orig != null) {
				JOptionPane.showMessageDialog(page, "Class already exists!");
				return;
			}
			thisInstance.addClass(comboBox_2);
			model1.addElement(comboBox_2);
			model2.addElement(comboBox_2);
			model3.addElement(comboBox_2);
			JOptionPane.showMessageDialog(page, "Class added successfully.");
		}
	}

	// Prevent button use with empty text field.
	public static ActionListener delCla = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter a class name!");
		}
	};

	// Action Listener for adding classes.
	public static ActionListener finalDelClass = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = ClassesPage.btnDelete;
				try {
					input.removeActionListener(delCla);
				} catch (Exception e1) {
				}
				delCla = new Controller.DelClass(ClassesPage.thisInstance, ClassesPage.comboBox_1.getSelectedItem(),
						ClassesPage.classPage, ClassesPage.model, ClassesPage.model_1, ClassesPage.model_2);
				input.addActionListener(delCla);
			}
		}
	};

	// Method for deleting classes.
	public static class DelClass implements ActionListener {
		private JFrame page;
		private Instance thisInstance;
		private String comboBox_1;
		private DefaultComboBoxModel<String> model1;
		private DefaultComboBoxModel<String> model2;
		private DefaultComboBoxModel<String> model3;

		// Constructor.
		public DelClass(Instance thisInstance, Object comboBoxContents, JFrame page,
				DefaultComboBoxModel<String> model1, DefaultComboBoxModel<String> model2,
				DefaultComboBoxModel<String> model3) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBox_1 = comboBoxContents.toString();
			this.page = page;
			this.model1 = model1;
			this.model2 = model2;
			this.model3 = model3;
		}

		public void actionPerformed(ActionEvent e) {
			if (thisInstance.classList == null || thisInstance.classList.size() == 0) {
				JOptionPane.showMessageDialog(page, "No classes currently!");
				return;
			}

			if (thisInstance.removeClass(comboBox_1)) {
				model1.removeElement(comboBox_1);
				model2.removeElement(comboBox_1);
				JOptionPane.showMessageDialog(page, "Class removed successfully.");
			} else
				JOptionPane.showMessageDialog(page, "Class removal failed, class not found.");
		}
	}

	// Create models for comboBox use; needs to be created early so methods can
	// add/remove from it to update it live.
	public static void initClasses(Instance inputInstance) {
		ClassesPage.thisInstance = inputInstance;
		ClassesPage.model = new DefaultComboBoxModel<String>();
		ClassesPage.model_1 = new DefaultComboBoxModel<String>();
		ClassesPage.model_2 = new DefaultComboBoxModel<String>();
		ClassesPage.comboBox = new JComboBox<String>(ClassesPage.model);
		ClassesPage.comboBox_1 = new JComboBox<String>(ClassesPage.model_1);
		ClassesPage.comboBox_2 = new JComboBox<String>(ClassesPage.model_2);

		// Populates combo boxes initially
		if (ClassesPage.thisInstance.classList != null) {
			if (ClassesPage.thisInstance.classList.size() != 0) {
				for (int i = 0; i < ClassesPage.thisInstance.classList.size(); i++) {
					ClassesPage.model.addElement(ClassesPage.thisInstance.classList.get(i).getName());
					ClassesPage.model_1.addElement(ClassesPage.thisInstance.classList.get(i).getName());
					ClassesPage.model_2.addElement(ClassesPage.thisInstance.classList.get(i).getName());
				}
				ClassesPage.comboBox.setMaximumRowCount(ClassesPage.thisInstance.classList.size());
			}
		}
	}

	// Method for displaying class contents.
	public static ActionListener displayClasses = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Classes thisClass;
			try {
				thisClass = ClassesPage.thisInstance.getClass(ClassesPage.comboBox.getSelectedItem().toString());
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(ClassesPage.classPage, "Please enter classes first.");
				return;
			}

			// Just in case user manages to empty the field.
			if (thisClass == null) {
				JOptionPane.showMessageDialog(ClassesPage.classPage, "Class not found.");
				return;
			}

			// Draws a table outlining existing class's contents in dialog
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Name");
			model.addColumn("Attribute");
			model.addColumn("Relationship Source");
			model.addColumn("Relationship Destination");
			// Adds class name
			model.addRow(new Object[] { thisClass.getName() });
			// Adds all of class's attributes
			if (thisClass.attributeList != null) {
				if (thisClass.attributeList.size() != 0) {
					for (int i = 0; i < thisClass.attributeList.size(); i++) {
						model.addRow(new Object[] { " ", thisClass.attributeList.get(i).getName() });
					}
				}
			}
			// Adds all of class's relationships
			if (thisClass.relationshipList != null) {
				if (thisClass.relationshipList.size() != 0) {
					for (int i = 0; i < thisClass.relationshipList.size(); i++) {
						model.addRow(new Object[] { " ", " ", thisClass.relationshipList.get(i).getSource(),
								thisClass.relationshipList.get(i).getDestination() });

					}
				}
			}
			// Set table size so header is visible.
			JTable table = new JTable(model);
			table.getColumnModel().getColumn(2).setPreferredWidth(110);
			table.getColumnModel().getColumn(3).setPreferredWidth(135);
			JOptionPane.showMessageDialog(null, new JScrollPane(table));
		}

	};

	// Prevent button use with empty text field.
	public static ActionListener renCla = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter a class name!");
		}
	};

	// Action Listener for renaming classes.
	public static ActionListener finalRenClass = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = ClassesPage.btnRename;
				try {
					input.removeActionListener(renCla);
				} catch (Exception e1) {
				}
				renCla = new Controller.RenClass(ClassesPage.thisInstance, ClassesPage.comboBox_1.getSelectedItem(),
						renameTextField, ClassesPage.comboBox_2, ClassesPage.classPage, ClassesPage.model,
						ClassesPage.model_1, ClassesPage.model_2);
				input.addActionListener(renCla);
			}
		}
	};

	// Document listener to detect letters input.
	public static DocumentListener claLetterListener = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) {
			doSomething();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			doSomething();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			doSomething();
		}

		// On any change, redefine the action listener.
		public void doSomething() {
			try {
				ClassesPage.btnRename.removeActionListener(renCla);
			} catch (Exception e) {
			}
			renameTextField = ClassesPage.textField2.getText();
			JButton input = ClassesPage.btnRename;
			renCla = new Controller.RenClass(ClassesPage.thisInstance, ClassesPage.comboBox_1.getSelectedItem(),
					renameTextField, ClassesPage.comboBox_2, ClassesPage.classPage, ClassesPage.model,
					ClassesPage.model_1, ClassesPage.model_2);
			input.addActionListener(renCla);
		}
	};

	// Method for renaming classes.
	public static class RenClass implements ActionListener {
		private JFrame page;
		private Instance thisInstance;
		private String comboBox_2;
		private JComboBox comboBox;
		private String textField2;
		private DefaultComboBoxModel<String> model;
		private DefaultComboBoxModel<String> model_1;
		private DefaultComboBoxModel<String> model_2;

		// Constructor.
		public RenClass(Instance thisInstance, Object comboBoxContents, Object textField2, JComboBox comboBox,
				JFrame page, DefaultComboBoxModel<String> model1, DefaultComboBoxModel<String> model2,
				DefaultComboBoxModel<String> model3) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBox_2 = comboBoxContents.toString();
			if (textField2 != null)
				this.textField2 = textField2.toString();
			this.page = page;
			this.model = model1;
			this.model_1 = model2;
			this.model_2 = model3;
			this.comboBox = comboBox;
		}

		public void actionPerformed(ActionEvent e) {
			if (comboBox_2 == null || comboBox_2.equals("") || textField2.equals("")
					|| !containsAlphaNumeric(comboBox_2) || !containsAlphaNumeric(textField2)) {
				JOptionPane.showMessageDialog(page, textField2);
				JOptionPane.showMessageDialog(page, "Please enter a class and new name.");
				return;
			}
			Classes orig;
			try {
				thisInstance.getClass(comboBox_2);
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Please enter classes first.");
				return;
			}
			orig = thisInstance.getClass(comboBox_2);
			Classes orig2 = thisInstance.getClass(textField2);
			if (orig2 != null) {
				JOptionPane.showMessageDialog(page, "Class already exists!");
				return;
			}
			if (orig.rename(comboBox_2, textField2)) {
				model.removeElement(comboBox_2);
				model.addElement(textField2);
				model_1.removeElement(comboBox_2);
				model_1.addElement(textField2);
				model_2.removeElement(comboBox_2);
				model_2.addElement(textField2);
				comboBox.setSelectedItem(textField2);
				JOptionPane.showMessageDialog(page, "Class renamed successfully.");
			} else {
				JOptionPane.showMessageDialog(page, "Class not found.");
			}

		}
	}

	// Home button action for classes page.
	public static ActionListener classesButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LandingPage.homepage.dispose();
			try {
				initController(LandingPage.thisInstance, "ClassesPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};

	
	/** Methods related to LoadPage.
	**
	**
	**/
	// Overrides required, since Swing doesn't natively support clickable text
	public static MouseAdapter loadHelpListener = new MouseAdapter() {
		String s = "<html>Load a JSON file by either entering the pathname<br> in the textbox or by browsing your file system</html>";

		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(LoadPage.loadPage, s);
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}
	};
	
	public static ActionListener loadBrowser = new ActionListener() {
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
				LoadPage.textField.setText("" + chooser.getSelectedFile().getAbsolutePath());
			}
		}

	};

	// Home button action for Load page.
	public static ActionListener loadHomeButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LoadPage.loadPage.dispose();
			Instance newInstance = (LoadPage.addInstance == null) ? LoadPage.thisInstance : LoadPage.addInstance;
			try {
				initController(newInstance, "LandingPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};

	// Prevent button use with empty text field.
	public static ActionListener loadFile = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter a file name or path!");

		}
	};

	// Method for loading classes.
	public static class load implements ActionListener {
		private String comboBoxContents;

		// Constructor.
		public load(String comboBoxContents) {
			if (comboBoxContents != null) {
				this.comboBoxContents = comboBoxContents;
			}
		}

		// Method for translating json file into an Instance object.
		public static Instance jsonReader(File file) throws Exception {
			Instance newInstance = new Instance();
			Gson gson = new Gson();
			newInstance = gson.fromJson(new FileReader(file), Instance.class);
			return newInstance;
		}

		public void actionPerformed(ActionEvent e) {
			// Checks for empty field.
			if (comboBoxContents.equals("")) {
				JOptionPane.showMessageDialog(LoadPage.loadPage, "Enter a file name or address.");
				return;
			}

			// Tries to load indicated file
			Instance newInstance = new Instance();
			try {
				File file = new File(comboBoxContents);
				newInstance = jsonReader(file);
				JButton button = (JButton) e.getSource();
				LoadPage.addInstance = newInstance;
				// thisInstance.copy(newInstance);
				JOptionPane.showMessageDialog(LoadPage.loadPage, "Json file loaded.");
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(LoadPage.loadPage, "Json file not found.");
				return;
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(LoadPage.loadPage, "Error loading Json file.");
				return;
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(LoadPage.loadPage, "Error loading Json file.");
			}
		}
	}

	// Listens for text in Loading screen textbox.
	public static DocumentListener loadLetterListener = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) {
			doSomething();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			doSomething();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			doSomething();
		}

		// On any change, redefine the action listener.
		public void doSomething() {
			try {
				LoadPage.btnNewButton_1.removeActionListener(loadFile);
			} catch (Exception e) {
			}
			renameTextField = LoadPage.textField.getText();
			JButton input = LoadPage.btnNewButton_1;
			loadFile = new Controller.load(renameTextField);
			input.addActionListener(loadFile);
		}
	};

	/** Methods related to SavePage.
	**
	**
	**/
	// Overrides required; Swing does not natively support clickable text
	public static MouseAdapter saveHelpListener = new MouseAdapter() {
		String s = "<html>Save: Save file by entering a filename in the textbox.<br> '.json' extension will be added automatically to filename if not entered by user.<br> This action will fail if a file already exists with the entered name or<br> or if the entered filename is invalid.</html>";

		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(SavePage.savePage, s);
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}
	};
	
	// Home button action for Save page.
	public static ActionListener saveHomeButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SavePage.savePage.dispose();
			try {
				initController(SavePage.thisInstance, "LandingPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};

	public static void saveInit() {
		// Creates content page for displaying file list
		SavePage.savePage.setContentPane(new SavePage(SavePage.model));
		SavePage.savePage.pack();

		// Loads saves from temp file
		SavePage.recentSaves = loadRecentSavesFile(SavePage.recentSaves);

		// Updates model with local files and saved history
		SavePage.model = addLocalsToModel(SavePage.model, SavePage.fileNames);
		try {
			SavePage.model = addSaveHistoryToModel(SavePage.model);
		} catch (IOException e2) {
		}
	}

	// Ensures the input file name ends in .json.
	private static String affixJson(String input) {
		if (input.length() > 5 && input.substring(input.length() - 5).equals(".json")) {
			return input;
		}
		return input += ".json";
	}

	// Returns an array of local json file names.
	public static Object[] localFiles() {
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
	
	// ActionListener to clear save history.
	public static ActionListener clearSaveHistory = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String tmpdir = System.getProperty("java.io.tmpdir");
			File newFile = new File(tmpdir + "\\velcroRecentSaves.txt");
			try {
				FileUtils.forceDelete(newFile);
			} catch (IOException e1) {
			}
			SavePage.model.clear();
			SavePage.fileNames = localFiles();
			SavePage.model = addLocalsToModel(SavePage.model, SavePage.fileNames);
			JOptionPane.showMessageDialog(SavePage.savePage, "Save file history cleared.");
		}
	};

	// Method for saving instance to Json.
	public static ActionListener save = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
			fileChooser.setDialogTitle("Specify a file to save");
			int userSelection = fileChooser.showSaveDialog(SavePage.savePage);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				if (!fileToSave.getAbsolutePath().equals("")) {
					String newName = affixJson(fileToSave.getAbsolutePath());
					File newFile = new File(newName);
					// Check to make sure file doesn't already exist.
					if (newFile.exists()) {
						Object[] options = { "OK", "CANCEL" };
						// Option to overwrite file.
						int n = JOptionPane.showOptionDialog(SavePage.savePage,
								"File already exists! Click OK to overwrite!", "WARNING", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
						if (n == 1) {
							JOptionPane.showMessageDialog(SavePage.savePage, "Save file cancelled.");
							return;
						} else {
							BufferedWriter writer;
							try {
								// Writes the json file.
								writer = new BufferedWriter(new FileWriter(newFile, true));
								writer.append(SavePage.thisInstance.printToJson());
								writer.close();
								// Updates recent saves
								SavePage.recentSaves = addObject(SavePage.recentSaves, newName, true);
								SavePage.recentSaves = saveRecentSavesFile(SavePage.recentSaves);
							} catch (IOException e1) {
							}
							JOptionPane.showMessageDialog(SavePage.savePage, "File overwritten successfully.");
							return;
						}
					}
					BufferedWriter writer;
					try {
						// Writes the json file.
						writer = new BufferedWriter(new FileWriter(newFile, true));
						writer.append(convertToGsonString(SavePage.thisInstance));
						writer.close();
						// Updates recent saves
						SavePage.recentSaves = addObject(SavePage.recentSaves, newName, true);
						SavePage.recentSaves = saveRecentSavesFile(SavePage.recentSaves);
						// Updates the file list
						SavePage.model.addElement(newName);
					} catch (IOException e1) {
					}
					JOptionPane.showMessageDialog(SavePage.savePage, "File saved successfully.");
				}
			}
		}
	};

	/** Methods related to LandingPage.
	**
	**
	**/
	
	// Home button action for attributes page.
	public static ActionListener attributesHomeButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			AttributesPage.attributesPage.dispose();
			try {
				initController(AttributesPage.thisInstance, "LandingPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};

	// Home button action for classes page.
	public static ActionListener classesHomeButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ClassesPage.classPage.dispose();
			try {
				initController(ClassesPage.thisInstance, "LandingPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};

	// Home button action for save page.
	public static ActionListener saveButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LandingPage.homepage.dispose();
			try {
				initController(LandingPage.thisInstance, "SavePage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};
	
	// Home button action for relationships page.
	public static ActionListener relationshipsButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LandingPage.homepage.dispose();
			try {
				initController(LandingPage.thisInstance, "RelationshipsPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};
	
	// Home button action for load page.
	public static ActionListener loadButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LandingPage.homepage.dispose();
			try {
				initController(LandingPage.thisInstance, "LoadPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};
	
	// Home button action for attributes page.
	public static ActionListener attributesButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LandingPage.homepage.dispose();
			try {
				initController(LandingPage.thisInstance, "AttributesPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};

	// Button for exiting app.
	public static ActionListener exitButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LandingPage.homepage.dispose();
			System.exit(0);
		}
	};

	// Method to list all classes and their contents.
	public static ActionListener classList = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// Aborts if classList is empty
			if (LandingPage.thisInstance.classList == null || LandingPage.thisInstance.classList.size() == 0) {
				JOptionPane.showMessageDialog(LandingPage.homepage, "Please enter classes first.");
				return;
			}

			// Sets up table for pop-up
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Name");
			model.addColumn("Attribute");
			model.addColumn("Relationship Source");
			model.addColumn("Relationship Destination");
			boolean first = true;
			for (int h = 0; h < LandingPage.thisInstance.classList.size(); h++) {
				// Adds a blank line between classes.
				if (first) {
					first = false;
				} else {
					model.addRow(new Object[] { " ", " ", " ", " " });
				}
				Classes thisClass = LandingPage.thisInstance.classList.get(h);
				// Adds class name
				model.addRow(new Object[] { thisClass.getName() });
				// Adds all class's attributes
				if (thisClass.attributeList != null) {
					if (thisClass.attributeList.size() != 0) {
						for (int i = 0; i < thisClass.attributeList.size(); i++) {
							model.addRow(new Object[] { " ", thisClass.attributeList.get(i).getName() });
						}
					}
				}
				// Adds all class's relationships
				if (thisClass.relationshipList != null) {
					if (thisClass.relationshipList.size() != 0) {
						for (int i = 0; i < thisClass.relationshipList.size(); i++) {
							model.addRow(new Object[] { " ", " ", thisClass.relationshipList.get(i).getSource(),
									thisClass.relationshipList.get(i).getDestination() });

						}
					}
				}

			}
			// Adjusting table so the headers are fully visible
			JTable table = new JTable(model);
			table.getColumnModel().getColumn(2).setPreferredWidth(110);
			table.getColumnModel().getColumn(3).setPreferredWidth(135);
			JOptionPane.showMessageDialog(null, new JScrollPane(table));

		}
	};

	// Overrides required, since Swing doesn't natively support clickable text
	public static MouseAdapter lanHelpListener = new MouseAdapter() {
		String s = "<html>Classes:&#9Add, Delete, Rename, and List Classes and their contents<br> Attributes:&#9Add/Delete attributes to/from classes, and Rename attributes<br> Relationships: Add and Delete Relationships between two Classes<br> Save:&#9Save current instance in a JSON file format<br> Load:&#9Load JSON files from directory<br> Exit:&#9Close the application</html>";

		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(LandingPage.homepage, s);
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}
	};

	/** Methods related to RelationshipsPage.
	**
	**
	**/

	// Overrides required; Swing does not natively support clickable text
	public static MouseAdapter relHelpListener = new MouseAdapter() {
		String s = "<html>Add: Enter name of the source class into 'Source' field, enter name of destination class into 'Destination' field<br> and click the 'Add' button.<br> This action will fail if a Relationship already exists with the given Source and Destination classes<br> Delete: Enter name of source class into 'Source' field, enter name of destination class into 'Destination' field, and click the 'Delete' button.<br> This action will fail if no Relationship exists with the given Source and Destination class names.</html>";

		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(RelationshipsPage.relationPage, s);
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}
	};
	
	// Home button action for attributes page.
	public static ActionListener relationshipHomeButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			RelationshipsPage.relationPage.dispose();
			try {
				initController(RelationshipsPage.thisInstance, "LandingPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};
	
	// Home button action for Relationships page.
	public static ActionListener relationshipsHomeButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			RelationshipsPage.relationPage.dispose();
			try {
				initController(RelationshipsPage.thisInstance, "LandingPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};

	// Initial creation of Relationship models.
	public static void relInit(Instance inputInstance) {
		RelationshipsPage.thisInstance = inputInstance;
		RelationshipsPage.model = new DefaultComboBoxModel<String>();
		RelationshipsPage.model_1 = new DefaultComboBoxModel<String>();
		RelationshipsPage.comboBox = new JComboBox<String>(RelationshipsPage.model);
		RelationshipsPage.comboBox_1 = new JComboBox<String>(RelationshipsPage.model_1);

		// Populates comboBoxes
		if (RelationshipsPage.thisInstance.classList != null) {
			if (RelationshipsPage.thisInstance.classList.size() != 0) {
				for (int i = 0; i < RelationshipsPage.thisInstance.classList.size(); i++) {
					RelationshipsPage.model.addElement(RelationshipsPage.thisInstance.classList.get(i).getName());
					RelationshipsPage.model_1.addElement(RelationshipsPage.thisInstance.classList.get(i).getName());
				}
				RelationshipsPage.comboBox.setMaximumRowCount(RelationshipsPage.thisInstance.classList.size());
			}
		}
	}

	// Action listener to add relationship
	public static ActionListener addRel = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// Checks that classes exist
			String sourceClassName;
			String destinationClassName;
			try {
				sourceClassName = RelationshipsPage.comboBox.getSelectedItem().toString();
				destinationClassName = RelationshipsPage.comboBox_1.getSelectedItem().toString();
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(RelationshipsPage.relationPage, "Please enter classes first.");
				return;
			}
			// Source and destination cannot be the same
			if (sourceClassName.equals(destinationClassName)) {
				JOptionPane.showMessageDialog(RelationshipsPage.relationPage,
						"Please enter a seperate source and destination.");
				return;
			}
			Classes sourceClass = RelationshipsPage.thisInstance.getClass(sourceClassName);
			Classes destinationClass = RelationshipsPage.thisInstance.getClass(destinationClassName);
			// Checks for empty input fields
			if (sourceClass == null || sourceClass.equals("") || destinationClass == null
					|| destinationClass.equals("")) {
				JOptionPane.showMessageDialog(RelationshipsPage.relationPage, "Please enter classes first.");
				return;
			}
			// Checks to see if relationship already exists
			Relationships orig = sourceClass.getRelationship(sourceClassName, destinationClassName);
			if (orig != null) {
				JOptionPane.showMessageDialog(RelationshipsPage.relationPage, "Relationship already exists!");
				return;
			}
			// Adds relationship
			sourceClass.addRelationship(sourceClassName, destinationClassName);
			destinationClass.addRelationship(sourceClassName, destinationClassName);
			JOptionPane.showMessageDialog(RelationshipsPage.relationPage, "Relationship added successfully.");
		}
	};

	// Action listener to remove relationship
	public static ActionListener delRel = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// Check that classes exist
			String sourceClassName;
			String destinationClassName;
			try {
				sourceClassName = RelationshipsPage.comboBox.getSelectedItem().toString();
				destinationClassName = RelationshipsPage.comboBox_1.getSelectedItem().toString();
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(RelationshipsPage.relationPage, "Please enter classes first.");
				return;
			}
			Classes sourceClass = RelationshipsPage.thisInstance.getClass(sourceClassName);
			Classes destinationClass = RelationshipsPage.thisInstance.getClass(destinationClassName);
			// Source and destination cannot be the same
			if (sourceClassName.equals(destinationClassName)) {
				JOptionPane.showMessageDialog(RelationshipsPage.relationPage,
						"Please enter a seperate source and destination.");
				return;
			}
			// Checks for empty input
			if (sourceClass == null || sourceClass.equals("") || destinationClass == null
					|| destinationClass.equals("")) {
				JOptionPane.showMessageDialog(RelationshipsPage.relationPage, "Please enter classes first.");
				return;
			}
			// Looks for relationship
			Relationships orig = sourceClass.getRelationship(sourceClassName, destinationClassName);
			if (orig == null) {
				JOptionPane.showMessageDialog(RelationshipsPage.relationPage, "Relationship not found!");
				return;
			}
			// Removes relationship
			if (sourceClass.removeRelationship(sourceClassName, destinationClassName)
					&& destinationClass.removeRelationship(sourceClassName, destinationClassName))
				JOptionPane.showMessageDialog(RelationshipsPage.relationPage, "Relationship removed successfully.");
			else
				JOptionPane.showMessageDialog(RelationshipsPage.relationPage, "Relationship removal failed.");
		}
	};

	// Method for listing all relationships.
	public static ActionListener listRelationships = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (RelationshipsPage.thisInstance.classList != null
					&& RelationshipsPage.thisInstance.classList.size() != 0) {
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Source");
				model.addColumn("Destination");
				// Creates list of already-listed relationships
				Object[][] allRelations = new Object[0][2];
				for (int i = 0; i < RelationshipsPage.thisInstance.classList.size(); i++) {
					Classes thisClass = RelationshipsPage.thisInstance.classList.get(i);
					// Checks for empty relationship list
					if (thisClass.relationshipList != null && thisClass.relationshipList.size() != 0) {
						for (int j = 0; j < thisClass.relationshipList.size(); j++) {
							Object[] newObject = new Object[] { thisClass.relationshipList.get(j).source,
									thisClass.relationshipList.get(j).destination };
							// Checks if relationship was already printed
							if (!containsElement(allRelations, newObject)) {
								model.addRow(newObject);
								allRelations = addElement(allRelations, newObject);
							}
						}
					}
				}
				JTable table = new JTable(model);
				JOptionPane.showMessageDialog(null, new JScrollPane(table));
			} else {
				JOptionPane.showMessageDialog(RelationshipsPage.relationPage, "No relationships currently exist!");
				return;
			}

		}
	};

	/** Utility methods.
	**
	**
	**/

	// Function for determining if array of object arrays contains object array.
	// Used to determine if relationship has already been printed.
	private static boolean containsElement(Object[][] array, Object[] search) {
		if (array == null || array.length == 0)
			return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i][0].equals(search[0]) && array[i][1].equals(search[1]))
				return true;
		}
		return false;
	}
	
	// Function for adding an object array to an array of object arrays.
	// Used to determine if relationship has already been printed.
	private static Object[][] addElement(Object[][] array, Object[] add) {
		Object[][] newObject;
		if (array != null && array.length != 0) {
			newObject = new Object[array.length + 1][add.length];
		} else {
			newObject = new Object[1][add.length];
		}
		if (array != null && array.length != 0) {
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					newObject[i][j] = array[i][j];
				}
			}
		}
		if (array != null && array.length != 0) {
			for (int i = 0; i < add.length; i++) {
				newObject[array.length][i] = add[i];
			}
		} else {
			for (int i = 0; i < add.length; i++) {
				newObject[0][i] = add[i];
			}
		}
		return newObject;
	}

	// Method to remove duplicates from an array.
	public static <E> E[] removeDuplicates(E[] array) {
		List<E> list = Arrays.asList(array);
		HashSet<E> set = new HashSet<E>();
		set.addAll(list);
		return (E[]) set.toArray();
	}

	// Method to convert object to String.
	public static String convertToGsonString(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
	// Adds local files to file list model.
	private static DefaultListModel addLocalsToModel(DefaultListModel inputModel, Object[] addedFiles) {
		if (addedFiles != null && addedFiles.length > 0) {
			for (int i = 0; i < addedFiles.length; i++) {
				String contents = inputModel.toString();
				// Compares objects as strings to prevent duplicates.
				if (!contents.contains(addedFiles[i].toString())
						&& !contents.contains(" " + addedFiles[i].toString())) {
					inputModel.addElement(addedFiles[i]);
				}
			}
		}
		return inputModel;
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

	// Method for determining if input contains alphanumerals.
	private static boolean containsAlphaNumeric(String input) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
		return p.matcher(input).find();
	}

}
