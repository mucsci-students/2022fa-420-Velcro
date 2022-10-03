package main.java.velcro.Controller;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
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
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

import main.java.velcro.Model.*;
import main.java.velcro.View.*;

public class Controller {

	private Instance model;
	private String view;
	static String renameTextField = "";

	public Controller(Instance model, String view) throws Exception {
		initController(model, view);
	}

	public Controller(Instance model, String view, Methods method) throws Exception {
		initController(model, view, method);
	}
	
	// Method for executing various pages.
	public static void initController(Instance mode, String v) throws Exception {
		switch (v) {
		case "LandingPage":
			LandingPage landingWindow = new LandingPage(mode);
			landingWindow.homepage.setVisible(true);
			break;
		case "FieldsPage":
			FieldsPage fieldsWindow = new FieldsPage(mode);
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
		case "MethodsPage":
			MethodsPage methodsWindow = new MethodsPage(mode);
			break;
		case "RelationshipsPage":
			RelationshipsPage relationshipsWindow = new RelationshipsPage(mode);
			Controller.setGroup();
			break;
		}

	}

	// Method for parameters window.
	public static void initController(Instance mode, String v, Methods method) throws Exception {
		switch (v) {
		case "ParametersPage":
			ParametersPage parametersWindow = new ParametersPage(mode, method);
			updateMethods();
			break;
		}

	}


	

	
	
	
	
	
	
	/** Fields related to #FieldsPage.
	**
	**
	**/
	

		// Overrides required; Swing does not natively support clickable text
	public static MouseAdapter fldHelpListener = new MouseAdapter() {
		String s = "<html>'Field Name' : Field1 &#9 'Rename Field Name' : Field2<br>To add a Field, enter a unique field name in Field1 and press the 'Add' button.<br> The action will fail if there is already a field with that name or if the entered name if invalid.<br> To delete a Field, enter the name of the field that you wish to delete in Field1 and press the 'Delete' button.<br> The action will fail if there is no field with the given name.<br>To rename a Field, enter the current name of the field that you wish to change in Field1,<br> and the name that you wish to change it to in Field2.<br> To initiate the action, hit the 'Rename' button once both fields have been entered. The action will fail if there is no<br> existing field with the given name or if the new name being entered is either already in use or is invalid.</html>";

		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(FieldsPage.fieldsPage, s);
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}
	};

	// Prevent button use with empty text field.
	public static ActionListener fld = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter a field name!");
		}
	};

	// Prevent button use with empty text field.
	public static ActionListener delFld = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter a field name!");
		}
	};

	// Prevent button use with empty text field.
	public static ActionListener renFld = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter a field name!");

		}
	};
	
	// Home button action for Field page.
	public static ActionListener fieldsHomeButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Instance newInstance = FieldsPage.thisInstance;
			FieldsPage.fieldsPage.dispose();
			try {
				initController(newInstance, "LandingPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};


	// Action Listener for adding fields.
	public static ActionListener finalFld = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = FieldsPage.btnNewButton;
				try {
					input.removeActionListener(finalFld);
				} catch (Exception e1) {
				}
				finalFld = new Controller.AddField(FieldsPage.thisInstance,
						FieldsPage.comboBox.getSelectedItem(), FieldsPage.comboContents,
						FieldsPage.fieldsPage, FieldsPage.comboBox_2.getSelectedItem());
				input.addActionListener(finalFld);
			} else {
				JOptionPane.showMessageDialog(null, "Please enter a field name!");
			}
		}
	};

	// Action listener for deleting fields.
	public static ActionListener finalDelFld = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = FieldsPage.btnDelete;
				try {
					input.removeActionListener(finalDelFld);
				} catch (Exception e1) {
				}
				finalDelFld = new Controller.DelField(FieldsPage.thisInstance,
						FieldsPage.comboBox.getSelectedItem(), FieldsPage.comboContents,
						FieldsPage.fieldsPage);
				input.addActionListener(finalDelFld);
			} else {
				JOptionPane.showMessageDialog(null, "Please enter a field name!");
			}
		}
	};
	
	// Document listener to detect field input.
	public static DocumentListener fldListener = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
				input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
		    	  input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			int length = source.getLength();
			String input = "";
		      try {
		    	  input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		// On any change, redefine the action listener.
		public void doSomething(String inputString) {
			JButton input = FieldsPage.btnDelete;
			try {
				input.removeActionListener(finalDelFld);
			} catch (Exception e1) {
			}
			finalDelFld = new Controller.DelField(FieldsPage.thisInstance,
					FieldsPage.comboBox.getSelectedItem(), FieldsPage.comboContents,
					FieldsPage.fieldsPage);
			input.addActionListener(finalDelFld);
			
			JButton input2 = FieldsPage.btnNewButton;
			try {
				input2.removeActionListener(finalFld);
			} catch (Exception e1) {
			}
			finalFld = new Controller.AddField(FieldsPage.thisInstance,
					FieldsPage.comboBox.getSelectedItem(), FieldsPage.comboContents,
					FieldsPage.fieldsPage, FieldsPage.comboBox_2.getSelectedItem());
			input2.addActionListener(finalFld);
			
			
			
			try {
				FieldsPage.btnRename.removeActionListener(finalRenFld);
			} catch (Exception e) {
			}
			renameTextField = FieldsPage.txtuseOnlyFor.getText();
			JButton input3 = FieldsPage.btnRename;
			finalRenFld = new Controller.RenameField(FieldsPage.thisInstance,
					FieldsPage.comboBox.getSelectedItem(), FieldsPage.comboContents,
					FieldsPage.fieldsPage, FieldsPage.txtuseOnlyFor.getText());
			input3.addActionListener(finalRenFld);

			
			
			FieldsPage.comboContents = inputString;
			
		}
	};



	
	
	// Document listener to detect letters input for renames.
	public static DocumentListener fldLetterListener = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
				input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
				input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
				input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		// On any change, redefine the action listener.
		public void doSomething(String inputString) {
			try {
				FieldsPage.btnRename.removeActionListener(finalRenFld);
			} catch (Exception e) {
			}
			renameTextField = FieldsPage.txtuseOnlyFor.getText();
			JButton input = FieldsPage.btnRename;
			finalRenFld = new Controller.RenameField(FieldsPage.thisInstance,
					FieldsPage.comboBox.getSelectedItem(), inputString,
					FieldsPage.fieldsPage, FieldsPage.txtuseOnlyFor.getText());
			input.addActionListener(finalRenFld);
		}
	};
	
	// Action listener for renaming fields.
	public static ActionListener finalRenFld = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = FieldsPage.btnRename;
				try {
					input.removeActionListener(finalRenFld);
				} catch (Exception e1) {
				}
				finalRenFld = new Controller.RenameField(FieldsPage.thisInstance,
						FieldsPage.comboBox.getSelectedItem(), FieldsPage.comboContents,
						FieldsPage.fieldsPage, FieldsPage.txtuseOnlyFor.getText());
				input.addActionListener(finalRenFld);
			}
		}
	};

	// Field for adding fields.
	public static class AddField implements ActionListener {
		private Instance thisInstance;
		private String comboBoxContents;
		private String comboBox_1SelectedItem;
		private String comboBox_2SelectedItem;
		private JFrame page;
		private DefaultComboBoxModel<String> model;
		private DefaultComboBoxModel<String> model1;

		// Constructor.
		public AddField(Instance thisInstance, Object comboBoxContents, String comboBox_1SelectedItem, JFrame page, Object comboBox2) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBoxContents = comboBoxContents.toString();
				this.comboBox_1SelectedItem = comboBox_1SelectedItem;
			if (comboBox2 != null)
				this.comboBox_2SelectedItem = comboBox2.toString();
			this.page = page;
		}

		

		public void actionPerformed(ActionEvent e) {
			Classes thisClass;
			comboBox_1SelectedItem = FieldsPage.comboContents;
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

			model = thisClass.fieldModel;
			model1 = thisClass.fieldModel_1;
			
			// Checks if combo box field is somehow empty
			if (comboBox_1SelectedItem == null || comboBox_1SelectedItem.equals("")
					|| !containsAlphaNumeric(comboBox_1SelectedItem)) {
				JOptionPane.showMessageDialog(page, "Please enter a field name.");
				return;
			}
			// Checks if Field already exists.
			try {
				Fields orig = thisClass.getField(comboBox_1SelectedItem);
				String test = orig.getName();
			} catch (NullPointerException e1) {
				// Adds field, updates combo box
				thisClass.addField(comboBox_1SelectedItem, comboBox_2SelectedItem);
				model.addElement(comboBox_1SelectedItem);
				model1.addElement(comboBox_1SelectedItem);
				JOptionPane.showMessageDialog(page, "Field added successfully.");
				return;
			}

			JOptionPane.showMessageDialog(page, "Field already exists! ");
		}
	}

	// Field for deleting fields.
	public static class DelField implements ActionListener {
		private Instance thisInstance;
		private String comboBoxContents;
		private String comboBox_1SelectedItem;
		private JFrame page;
		private DefaultComboBoxModel<String> model;
		private DefaultComboBoxModel<String> model1;

		// Constructor.
		public DelField(Instance thisInstance, Object comboBoxContents, String comboBox_1SelectedItem, JFrame page) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBoxContents = comboBoxContents.toString();
			if (comboBox_1SelectedItem != null)
				this.comboBox_1SelectedItem = comboBox_1SelectedItem;
			this.page = page;
		}

		public void actionPerformed(ActionEvent e) {
			Classes thisClass;
			comboBox_1SelectedItem = FieldsPage.comboContents;
			// Tests if classes already exist
			try {
				thisClass = thisInstance.getClass(comboBoxContents);
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Please enter classes first.");
				return;
			}
			// Tests if any fields already exist
			try {
			if (thisClass.fieldList == null || thisClass.fieldList.size() == 0) {
				JOptionPane.showMessageDialog(page, "No fields currently!");
				return;
			}
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(page, "No fields currently!");
				return;
			}
			model = thisClass.fieldModel;
			model1 = thisClass.fieldModel_1;
			
			// Checks to see if combo box is empty
			if (comboBox_1SelectedItem.equals("") || !containsAlphaNumeric(comboBox_1SelectedItem)) {
				JOptionPane.showMessageDialog(page, "Please enter a field name.");
				return;
			}
			// Removes field, updates combo box
			if (thisClass.removeField(comboBox_1SelectedItem)) {
				model.removeElement(comboBox_1SelectedItem);
				JOptionPane.showMessageDialog(page, "Field removed successfully.");
			} else
				JOptionPane.showMessageDialog(page, "Field removal failed, field not found.");
		}

	}

	// Field for renaming fields.
	public static class RenameField implements ActionListener {
		private Instance thisInstance;
		private String comboBoxContents;
		private String comboBox_1SelectedItem;
		private JFrame page;
		private DefaultComboBoxModel<String> model;
		private DefaultComboBoxModel<String> model1;
		private String comboBox_2Contents;

		// Constructor.
		public RenameField(Instance thisInstance, Object comboBoxContents, String comboBox_1SelectedItem,
				JFrame page, String comboBox_2Contents) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBoxContents = comboBoxContents.toString();
			if (comboBox_1SelectedItem != null)
				this.comboBox_1SelectedItem = comboBox_1SelectedItem;
			if (comboBox_2Contents != null)
				this.comboBox_2Contents = comboBox_2Contents;
			this.page = page;
		}

		public void actionPerformed(ActionEvent e) {
			Classes thisClass;
			comboBox_1SelectedItem = FieldsPage.comboContents;
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

				JOptionPane.showMessageDialog(page, "Please enter a field and new name.");
				return;
			}
			
			model = thisClass.fieldModel;
			model1 = thisClass.fieldModel_1;
			
			// Checks if Field exists.
			try {
				Fields orig = thisClass.getField(comboBox_1SelectedItem);
				String test = orig.getName();
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Field not found.");
				return;
			}

			// Checks if rename is already taken.
			Fields orig2 = thisClass.getField(comboBox_2Contents);
			try {
				String test2 = orig2.getName();
			} catch (NullPointerException e1) {
				// Renames field, updates Field combo box
				Fields orig3 = thisClass.getField(comboBox_1SelectedItem);
				if (orig3.rename(comboBox_2Contents)) {
					model.addElement(comboBox_2Contents);
					model.removeElement(comboBox_1SelectedItem);
					model1.addElement(comboBox_2Contents);
					model1.removeElement(comboBox_1SelectedItem);
					FieldsPage.comboBox_1.setSelectedItem(comboBox_2Contents);
					JOptionPane.showMessageDialog(page, "Field renamed successfully.");
					return;
				} else {
					JOptionPane.showMessageDialog(page, "Field not found.");
					return;
				}
			}
			JOptionPane.showMessageDialog(page, "Field already exists!");
			return;
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** Methods related to #ClassesPage.
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
						ClassesPage.classPage, ClassesPage.thisInstance.classModel, ClassesPage.thisInstance.classModel_2, ClassesPage.thisInstance.classModel_3);
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

			// Checks for empty text fields.
			try {
				if (e == null && e.getSource().toString() != null) {
					JOptionPane.showMessageDialog(page, "Please enter a class name.");
					return;
				}
				if (comboBox_2 == null || comboBox_2.equals("") || !containsAlphaNumeric(comboBox_2)) {
					JOptionPane.showMessageDialog(page, "Please enter a class name.");
					return;
				}
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Please enter a class name.");
				return;
			}

			Classes orig;
			//try {
				orig = thisInstance.getClass(comboBox_2);
			//} catch (NullPointerException e1) {
				thisInstance.addClass(comboBox_2);
				model1.addElement(comboBox_2);
				model2.addElement(comboBox_2);
				model3.addElement(comboBox_2);
				JOptionPane.showMessageDialog(page, "Class added successfully.");
				return;
			//}
			
				//JOptionPane.showMessageDialog(page, "Class already exists!");
				//return;

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
						ClassesPage.classPage, ClassesPage.thisInstance.classModel, ClassesPage.thisInstance.classModel_2, ClassesPage.thisInstance.classModel_3);
				input.addActionListener(delCla);
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
		ClassesPage.comboBox = new JComboBox<String>(inputInstance.classModel);
		ClassesPage.comboBox_1 = new JComboBox<String>(inputInstance.classModel_2);
		ClassesPage.comboBox_2 = new JComboBox<String>(inputInstance.classModel_3);
		try {
			ClassesPage.comboBox.setMaximumRowCount(inputInstance.classList.size());
		} catch (NullPointerException e) {
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
			model.addColumn("Field");
			model.addColumn("Relationship Source");
			model.addColumn("Relationship Destination");
			model.addColumn("Relationship Type");
			// Adds class name
			model.addRow(new Object[] { thisClass.getName() });
			// Adds all of class's fields
			if (thisClass.fieldList != null) {
				if (thisClass.fieldList.size() != 0) {
					for (int i = 0; i < thisClass.fieldList.size(); i++) {
						model.addRow(new Object[] { " ", thisClass.fieldList.get(i).getName() });
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
			table.getColumnModel().getColumn(4).setPreferredWidth(110);
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
						renameTextField, ClassesPage.comboBox_2, ClassesPage.classPage, ClassesPage.thisInstance.classModel,
						ClassesPage.thisInstance.classModel_2, ClassesPage.thisInstance.classModel_3);
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
					renameTextField, ClassesPage.comboBox_2, ClassesPage.classPage, ClassesPage.thisInstance.classModel,
					ClassesPage.thisInstance.classModel_2, ClassesPage.thisInstance.classModel_3);
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
		private DefaultComboBoxModel<String> classModel_2;
		private DefaultComboBoxModel<String> classModel_3;

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
			this.classModel_2 = model2;
			this.classModel_3 = model3;
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
				classModel_2.removeElement(comboBox_2);
				classModel_2.addElement(textField2);
				classModel_3.removeElement(comboBox_2);
				classModel_3.addElement(textField2);
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** Methods related to #MethodsPage.
	**
	**
	**/
	
	
		// Overrides required; Swing does not natively support clickable text
	public static MouseAdapter metHelpListener = new MouseAdapter() {
		String s = "<html>'Method Name' : Field1 &#9 'Rename Method Name' : Field2<br>To add a Method, enter a unique method name in Field1 and press the 'Add' button.<br> The action will fail if there is already a method with that name or if the entered name if invalid.<br> To delete a Method, enter the name of the method that you wish to delete in Field1 and press the 'Delete' button.<br> The action will fail if there is no method with the given name.<br>To rename a Method, enter the current name of the method that you wish to change in Field1,<br> and the name that you wish to change it to in Field2.<br> To initiate the action, hit the 'Rename' button once both fields have been entered. The action will fail if there is no<br> existing method with the given name or if the new name being entered is either already in use or is invalid.</html>";

		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(MethodsPage.methodsPage, s);
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}
	};

	// Prevent button use with empty text field.
	public static ActionListener met = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter a method name!");
		}
	};

	// Prevent button use with empty text field.
	public static ActionListener delMet = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter a method name!");
		}
	};

	// Prevent button use with empty text field.
	public static ActionListener renMet = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter a method name!");

		}
	};
	
	// Home button action for Method page.
	public static ActionListener methodsHomeButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Instance newInstance = MethodsPage.thisInstance;
			MethodsPage.methodsPage.dispose();
			try {
				initController(newInstance, "LandingPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};

	// Parameters button action for Method page.
	public static ActionListener methodsParamButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Instance newInstance = MethodsPage.thisInstance;
			Methods method;
			try {
			method = newInstance.getClass(MethodsPage.comboBox.getSelectedItem().toString()).getMethod(MethodsPage.comboContents);
			} catch (NullPointerException e3) {
				JOptionPane.showMessageDialog(null, "Please enter a method first!");
				return;
			}
			MethodsPage.methodsPage.dispose();
			try {
				initController(newInstance, "ParametersPage", method);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};
	
	// Updates Parameter button
	public static ActionListener updateParamButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			MethodsPage.btnAddParameters.removeActionListener(methodsParamButton);
			MethodsPage.btnAddParameters.addActionListener(methodsParamButton);
		}
	};
	
	
	// Action Listener for adding methods.
	public static ActionListener finalMet = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = MethodsPage.btnNewButton;
				try {
					input.removeActionListener(finalMet);
				} catch (Exception e1) {
				}
				finalMet = new Controller.AddMethod(MethodsPage.thisInstance,
						MethodsPage.comboBox.getSelectedItem(), MethodsPage.comboContents,
						MethodsPage.methodsPage, MethodsPage.comboBox_2.getSelectedItem());
				input.addActionListener(finalMet);
			} else {
				JOptionPane.showMessageDialog(null, "Please enter a method name!");
			}
		}
	};

	// Action listener for deleting methods.
	public static ActionListener finalDelMet = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = MethodsPage.btnDelete;
				try {
					input.removeActionListener(finalDelMet);
				} catch (Exception e1) {
				}
				finalDelMet = new Controller.DelMethod(MethodsPage.thisInstance,
						MethodsPage.comboBox.getSelectedItem(), MethodsPage.comboContents,
						MethodsPage.methodsPage);
				input.addActionListener(finalDelMet);
			} else {
				JOptionPane.showMessageDialog(null, "Please enter a method name!");
			}
		}
	};
	
	// Document listener to detect method input.
	public static DocumentListener metListener = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
				input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
		    	  input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			int length = source.getLength();
			String input = "";
		      try {
		    	  input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		// On any change, redefine the action listener.
		public void doSomething(String inputString) {
			JButton input = MethodsPage.btnDelete;
			try {
				input.removeActionListener(finalDelMet);
			} catch (Exception e1) {
			}
			finalDelMet = new Controller.DelMethod(MethodsPage.thisInstance,
					MethodsPage.comboBox.getSelectedItem(), MethodsPage.comboContents,
					MethodsPage.methodsPage);
			input.addActionListener(finalDelMet);
			
			JButton input2 = MethodsPage.btnNewButton;
			try {
				input2.removeActionListener(finalMet);
			} catch (Exception e1) {
			}
			finalMet = new Controller.AddMethod(MethodsPage.thisInstance,
					MethodsPage.comboBox.getSelectedItem(), MethodsPage.comboContents,
					MethodsPage.methodsPage, MethodsPage.comboBox_2.getSelectedItem());
			input2.addActionListener(finalMet);
			
			
			
			try {
				MethodsPage.btnRename.removeActionListener(finalRenMet);
			} catch (Exception e) {
			}
			renameTextField = MethodsPage.txtuseOnlyFor.getText();
			JButton input3 = MethodsPage.btnRename;
			finalRenMet = new Controller.RenameMethod(MethodsPage.thisInstance,
					MethodsPage.comboBox.getSelectedItem(), MethodsPage.comboContents,
					MethodsPage.methodsPage, MethodsPage.txtuseOnlyFor.getText());
			input3.addActionListener(finalRenMet);
			
			
//			try {
//				MethodsPage.btnAddParameters.removeActionListener(methodsParamButton);
//			} catch (Exception e) {
//			}
			
			JButton input4 = MethodsPage.btnAddParameters;
			input4.removeActionListener(paramButton);
			try {
				Classes thisClass = MethodsPage.thisInstance.getClass(MethodsPage.comboBox.getSelectedItem().toString());
				Methods thisMethod = thisClass.getMethod(inputString);
				paramButton = new Controller.ParamButton(thisMethod, thisClass);
			} catch (Exception e1) {
			}
			input4.addActionListener(paramButton);
			
			
			MethodsPage.comboContents = inputString;
			
		}
	};

	
	public static ActionListener paramButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "")
				JOptionPane.showMessageDialog(null, "Please enter a method name!");
		}
	};
	
	
	public static class ParamButton implements ActionListener {
		Methods thisMethod;
		Classes thisClass;
		
		// Constructor.
		public ParamButton(Methods thisMethod, Classes thisClass) {
			this.thisMethod = thisMethod;
			this.thisClass = thisClass;
		}

		public void actionPerformed(ActionEvent e) {
			Instance newInstance = MethodsPage.thisInstance;
			MethodsPage.methodsPage.dispose();
			try {
				initController(newInstance, "ParametersPage", thisMethod);
			} catch (Exception e1) {
			}
		}
	}
	
	
	// Document listener to detect letters input for renames.
	public static DocumentListener metLetterListener = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
				input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
				input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
				input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		// On any change, redefine the action listener.
		public void doSomething(String inputString) {
			try {
				MethodsPage.btnRename.removeActionListener(finalRenMet);
			} catch (Exception e) {
			}
			renameTextField = MethodsPage.txtuseOnlyFor.getText();
			JButton input = MethodsPage.btnRename;
			finalRenMet = new Controller.RenameMethod(MethodsPage.thisInstance,
					MethodsPage.comboBox.getSelectedItem(), inputString,
					MethodsPage.methodsPage, MethodsPage.txtuseOnlyFor.getText());
			input.addActionListener(finalRenMet);
		}
	};
	
	// Action listener for renaming methods.
	public static ActionListener finalRenMet = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = MethodsPage.btnRename;
				try {
					input.removeActionListener(finalRenMet);
				} catch (Exception e1) {
				}
				finalRenMet = new Controller.RenameMethod(MethodsPage.thisInstance,
						MethodsPage.comboBox.getSelectedItem(), MethodsPage.comboContents,
						MethodsPage.methodsPage, MethodsPage.txtuseOnlyFor.getText());
				input.addActionListener(finalRenMet);
			}
		}
	};

	// Method for adding methods.
	public static class AddMethod implements ActionListener {
		private Instance thisInstance;
		private String comboBoxContents;
		private String comboBox_1SelectedItem;
		private String comboBox_2SelectedItem;
		private JFrame page;
		private DefaultComboBoxModel<String> model;
		private DefaultComboBoxModel<String> model1;

		// Constructor.
		public AddMethod(Instance thisInstance, Object comboBoxContents, String comboBox_1SelectedItem, JFrame page, Object comboBox2) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBoxContents = comboBoxContents.toString();
				this.comboBox_1SelectedItem = comboBox_1SelectedItem;
			if (comboBox2 != null)
				this.comboBox_2SelectedItem = comboBox2.toString();
			this.page = page;
		}

		

		public void actionPerformed(ActionEvent e) {
			Classes thisClass;
			comboBox_1SelectedItem = MethodsPage.comboContents;
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

			model = thisClass.methodModel;
			model1 = thisClass.methodModel_1;
			
			// Checks if combo box field is somehow empty
			if (comboBox_1SelectedItem == null || comboBox_1SelectedItem.equals("")
					|| !containsAlphaNumeric(comboBox_1SelectedItem)) {
				JOptionPane.showMessageDialog(page, "Please enter a method name.");
				return;
			}
			// Checks if Method already exists.
			try {
				Methods orig = thisClass.getMethod(comboBox_1SelectedItem);
				String test = orig.getName();
			} catch (NullPointerException e1) {
				// Adds method, updates combo box
				List<Fields> newFields = new ArrayList<Fields>();
				//newFields.add(new Fields("hello", "world"));
				thisClass.addMethod(comboBox_1SelectedItem, comboBox_2SelectedItem, newFields);
				model.addElement(comboBox_1SelectedItem);
				model1.addElement(comboBox_1SelectedItem);
				JOptionPane.showMessageDialog(page, "Method added successfully.");
				return;
			}

			JOptionPane.showMessageDialog(page, "Method already exists! ");
		}
	}

	// Method for deleting methods.
	public static class DelMethod implements ActionListener {
		private Instance thisInstance;
		private String comboBoxContents;
		private String comboBox_1SelectedItem;
		private JFrame page;
		private DefaultComboBoxModel<String> model;
		private DefaultComboBoxModel<String> model1;

		// Constructor.
		public DelMethod(Instance thisInstance, Object comboBoxContents, String comboBox_1SelectedItem, JFrame page) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBoxContents = comboBoxContents.toString();
			if (comboBox_1SelectedItem != null)
				this.comboBox_1SelectedItem = comboBox_1SelectedItem;
			this.page = page;
		}

		public void actionPerformed(ActionEvent e) {
			Classes thisClass;
			comboBox_1SelectedItem = MethodsPage.comboContents;
			// Tests if classes already exist
			try {
				thisClass = thisInstance.getClass(comboBoxContents);
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Please enter classes first.");
				return;
			}
			// Tests if any methods already exist
			try {
			if (thisClass.methodList == null || thisClass.methodList.size() == 0) {
				JOptionPane.showMessageDialog(page, "No methods currently!");
				return;
			}
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(page, "No methods currently!");
				return;
			}
			model = thisClass.methodModel;
			model1 = thisClass.methodModel_1;
			
			// Checks to see if combo box is empty
			if (comboBox_1SelectedItem.equals("") || !containsAlphaNumeric(comboBox_1SelectedItem)) {
				JOptionPane.showMessageDialog(page, "Please enter a method name.");
				return;
			}
			// Removes method, updates combo box
			if (thisClass.removeMethod(comboBox_1SelectedItem)) {
				model.removeElement(comboBox_1SelectedItem);
				JOptionPane.showMessageDialog(page, "Method removed successfully.");
			} else
				JOptionPane.showMessageDialog(page, "Method removal failed, method not found.");
		}

	}

	// Method for renaming methods.
	public static class RenameMethod implements ActionListener {
		private Instance thisInstance;
		private String comboBoxContents;
		private String comboBox_1SelectedItem;
		private JFrame page;
		private DefaultComboBoxModel<String> model;
		private DefaultComboBoxModel<String> model1;
		private String comboBox_2Contents;

		// Constructor.
		public RenameMethod(Instance thisInstance, Object comboBoxContents, String comboBox_1SelectedItem,
				JFrame page, String comboBox_2Contents) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBoxContents = comboBoxContents.toString();
			if (comboBox_1SelectedItem != null)
				this.comboBox_1SelectedItem = comboBox_1SelectedItem;
			if (comboBox_2Contents != null)
				this.comboBox_2Contents = comboBox_2Contents;
			this.page = page;
		}

		public void actionPerformed(ActionEvent e) {
			Classes thisClass;
			comboBox_1SelectedItem = MethodsPage.comboContents;
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

				JOptionPane.showMessageDialog(page, "Please enter a method and new name.");
				return;
			}
			
			model = thisClass.fieldModel;
			model1 = thisClass.fieldModel_1;
			
			// Checks if Method exists.
			try {
				Methods orig = thisClass.getMethod(comboBox_1SelectedItem);
				String test = orig.getName();
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Method not found.");
				return;
			}

			// Checks if rename is already taken.
			Methods orig2 = thisClass.getMethod(comboBox_2Contents);
			try {
				String test2 = orig2.getName();
			} catch (NullPointerException e1) {
				// Renames method, updates Method combo box
				Methods orig3 = thisClass.getMethod(comboBox_1SelectedItem);
				if (orig3.rename(comboBox_2Contents)) {
					model.addElement(comboBox_2Contents);
					model.removeElement(comboBox_1SelectedItem);
					model1.addElement(comboBox_2Contents);
					model1.removeElement(comboBox_1SelectedItem);
					MethodsPage.comboBox_1.setSelectedItem(comboBox_2Contents);
					JOptionPane.showMessageDialog(page, "Method renamed successfully.");
					return;
				} else {
					JOptionPane.showMessageDialog(page, "Method not found.");
					return;
				}
			}
			JOptionPane.showMessageDialog(page, "Method already exists!");
			return;
		}

	}
//	
//	
//	// Listener to update method's class.
//	public static ActionListener updateMethod = new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//			Classes thisClass = MethodsPage.thisInstance.getClass(MethodsPage.comboBox.getSelectedItem().toString());
//			if (thisClass != null && thisClass.methodModel != null) {
//				MethodsPage.comboBox_1 = new JComboBox<String>(thisClass.methodModel);
//			}
//		}
//	};
//	
	
	
	
	
	
	
	/** Methods related to #LoadPage.
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
	
	// Load browser window.
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

		// Method for translating json file into a Instance object.
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** Methods related to #SavePage.
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

	// Returns a array of local json file names.
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

	// Returns a array of file names from the locally-saved recent saves file.
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	/** Parameters related to #ParametersPage.
	**
	**
	**/
	
	// Clears all parameters.
	public static ActionListener clearParams = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ParametersPage.thisMethod.clearParam();
		}
	};
	
	// Methods comboField update.
	public static void updateMethods() {
		ParametersPage.comboBox_3.setModel(ParametersPage.thisInstance.getClass(ParametersPage.comboBox.toString()).methodModel);
	};
	
	// Methods button action for Parameter page.
	public static ActionListener parametersMethodButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Instance newInstance = ParametersPage.thisInstance;
			ParametersPage.parametersPage.dispose();
			try {
				initController(newInstance, "MethodsPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};
	
		// Overrides required; Swing does not natively support clickable text
	public static MouseAdapter parHelpListener = new MouseAdapter() {
		String s = "<html>'Parameter Name' : Parameter1 &#9 'Rename Parameter Name' : Parameter2<br>To add a Parameter, enter a unique parameter name in Parameter1 and press the 'Add' button.<br> The action will fail if there is already a parameter with that name or if the entered name if invalid.<br> To delete a Parameter, enter the name of the parameter that you wish to delete in Parameter1 and press the 'Delete' button.<br> The action will fail if there is no parameter with the given name.<br>To rename a Parameter, enter the current name of the parameter that you wish to change in Parameter1,<br> and the name that you wish to change it to in Parameter2.<br> To initiate the action, hit the 'Rename' button once both parameters have been entered. The action will fail if there is no<br> existing parameter with the given name or if the new name being entered is either already in use or is invalid.</html>";

		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(ParametersPage.parametersPage, s);
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}
	};

	// Prevent button use with empty text parameter.
	public static ActionListener par = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter a parameter name!");
		}
	};

	// Prevent button use with empty text parameter.
	public static ActionListener delPar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter a parameter name!");
		}
	};

	// Prevent button use with empty text parameter.
	public static ActionListener renPar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter a parameter name!");

		}
	};
	
	// Home button action for Parameter page.
	public static ActionListener parametersHomeButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Instance newInstance = ParametersPage.thisInstance;
			ParametersPage.parametersPage.dispose();
			try {
				initController(newInstance, "LandingPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};


	// Action Listener for adding parameters.
	public static ActionListener finalPar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = ParametersPage.btnNewButton;
				try {
					input.removeActionListener(finalPar);
				} catch (Exception e1) {
				}
				finalPar = new Controller.AddParameter(ParametersPage.thisInstance,
						ParametersPage.comboBox.getSelectedItem(), ParametersPage.comboContents,
						ParametersPage.parametersPage, ParametersPage.comboBox_2.getSelectedItem());
				input.addActionListener(finalPar);
			} else {
				JOptionPane.showMessageDialog(null, "Please enter a parameter name!");
			}
		}
	};

	// Action listener for deleting parameters.
	public static ActionListener finalDelPar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = ParametersPage.btnDelete;
				try {
					input.removeActionListener(finalDelPar);
				} catch (Exception e1) {
				}
				finalDelPar = new Controller.DelParameter(ParametersPage.thisInstance,
						ParametersPage.comboBox.getSelectedItem(), ParametersPage.comboContents,
						ParametersPage.parametersPage);
				input.addActionListener(finalDelPar);
			} else {
				JOptionPane.showMessageDialog(null, "Please enter a parameter name!");
			}
		}
	};
	
	// Document listener to detect parameter input.
	public static DocumentListener parListener = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
				input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
		    	  input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			int length = source.getLength();
			String input = "";
		      try {
		    	  input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		// On any change, redefine the action listener.
		public void doSomething(String inputString) {

			ParametersPage.comboContents = inputString;
			JButton input = ParametersPage.btnDelete;
			try {
				input.removeActionListener(finalDelPar);
			} catch (Exception e1) {
			}
			finalDelPar = new Controller.DelParameter(ParametersPage.thisInstance,
					ParametersPage.comboBox.getSelectedItem(), inputString,
					ParametersPage.parametersPage);
			input.addActionListener(finalDelPar);
			
			JButton input2 = ParametersPage.btnNewButton;
			try {
				input2.removeActionListener(finalPar);
			} catch (Exception e1) {
			}
			finalPar = new Controller.AddParameter(ParametersPage.thisInstance,
					ParametersPage.comboBox.getSelectedItem(), inputString,
					ParametersPage.parametersPage, ParametersPage.comboBox_2.getSelectedItem());
			input2.addActionListener(finalPar);
			
			
			
			try {
				ParametersPage.btnRename.removeActionListener(finalRenPar);
			} catch (Exception e) {
			}
			renameTextField = ParametersPage.txtuseOnlyFor.getText();
			JButton input3 = ParametersPage.btnRename;
			finalRenPar = new Controller.RenameParameter(ParametersPage.thisInstance,
					ParametersPage.comboBox.getSelectedItem(), inputString,
					ParametersPage.parametersPage, ParametersPage.txtuseOnlyFor.getText());
			input3.addActionListener(finalRenPar);

			
			
			
		}
	};



	
	
	// Document listener to detect letters input for renames.
	public static DocumentListener parLetterListener = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
				input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
				input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			Document source = e.getDocument();
			String input = "";
			int length = source.getLength();
		      try {
				input = source.getText(0, length);
			} catch (BadLocationException e1) {
			}
			doSomething(input);
		}

		// On any change, redefine the action listener.
		public void doSomething(String inputString) {
			try {
				ParametersPage.btnRename.removeActionListener(finalRenPar);
			} catch (Exception e) {
			}
			renameTextField = ParametersPage.txtuseOnlyFor.getText();
			JButton input = ParametersPage.btnRename;
			finalRenPar = new Controller.RenameParameter(ParametersPage.thisInstance,
					ParametersPage.comboBox.getSelectedItem(), inputString,
					ParametersPage.parametersPage, ParametersPage.txtuseOnlyFor.getText());
			input.addActionListener(finalRenPar);
		}
	};
	
	// Action listener for renaming parameters.
	public static ActionListener finalRenPar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input = ParametersPage.btnRename;
				try {
					input.removeActionListener(finalRenPar);
				} catch (Exception e1) {
				}
				finalRenPar = new Controller.RenameParameter(ParametersPage.thisInstance,
						ParametersPage.comboBox.getSelectedItem(), ParametersPage.comboContents,
						ParametersPage.parametersPage, ParametersPage.txtuseOnlyFor.getText());
				input.addActionListener(finalRenPar);
			}
		}
	};

	// Parameter for adding parameters.
	public static class AddParameter implements ActionListener {
		private Instance thisInstance;
		private String comboBoxContents;
		private String comboBox_1SelectedItem;
		private String comboBox_2SelectedItem;
		private JFrame page;
		private DefaultComboBoxModel<String> model;
		private DefaultComboBoxModel<String> model1;

		// Constructor.
		public AddParameter(Instance thisInstance, Object comboBoxContents, String comboBox_1SelectedItem, JFrame page, Object comboBox2) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBoxContents = comboBoxContents.toString();
				this.comboBox_1SelectedItem = comboBox_1SelectedItem;
			if (comboBox2 != null)
				this.comboBox_2SelectedItem = comboBox2.toString();
			this.page = page;
		}

		

		public void actionPerformed(ActionEvent e) {
			Methods thisMethod;
			comboBox_1SelectedItem = ParametersPage.comboContents;
			// Checks if classes are entered already
			if (comboBoxContents == null || comboBoxContents.equals("")) {
				JOptionPane.showMessageDialog(page, "Please enter classes first.");
				return;
			}
			try {
				thisMethod = thisInstance.getClass(comboBoxContents).getMethod(ParametersPage.comboBox_3.getSelectedItem().toString());
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Please enter classes first.");
				return;
			}

			model = thisMethod.paramModel;
			model1 = thisMethod.paramModel1;
			
			// Checks if combo box parameter is somehow empty
			if (comboBox_1SelectedItem == null || comboBox_1SelectedItem.equals("")
					|| !containsAlphaNumeric(comboBox_1SelectedItem)) {
				JOptionPane.showMessageDialog(page, "Please enter a parameter name.");
				return;
			}
			// Checks if Parameter already exists.
			try {
				Fields orig = thisMethod.getParam(comboBox_1SelectedItem);
				String test = orig.getName();
			} catch (NullPointerException e1) {
				// Adds parameter, updates combo box
				thisMethod.addParam(ParametersPage.comboContents, comboBox_2SelectedItem);
				model.addElement(comboBox_1SelectedItem);
				model1.addElement(comboBox_1SelectedItem);
				JOptionPane.showMessageDialog(page, "Parameter added successfully.");
				return;
			}

			JOptionPane.showMessageDialog(page, "Parameter already exists! ");
		}
	}

	// Parameter for deleting parameters.
	public static class DelParameter implements ActionListener {
		private Instance thisInstance;
		private String comboBoxContents;
		private String comboBox_1SelectedItem;
		private JFrame page;
		private DefaultComboBoxModel<String> model;
		private DefaultComboBoxModel<String> model1;

		// Constructor.
		public DelParameter(Instance thisInstance, Object comboBoxContents, String comboBox_1SelectedItem, JFrame page) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBoxContents = comboBoxContents.toString();
			if (comboBox_1SelectedItem != null)
				this.comboBox_1SelectedItem = comboBox_1SelectedItem;
			this.page = page;
		}

		public void actionPerformed(ActionEvent e) {
			Methods thisMethod;
			comboBox_1SelectedItem = ParametersPage.comboContents;
			// Tests if classes already exist
			try {
				thisMethod = thisInstance.getClass(comboBoxContents).getMethod(ParametersPage.comboBox_3.getSelectedItem().toString());
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Please enter classes first.");
				return;
			}
			// Tests if any parameters already exist
			try {
			if (thisMethod.paramList == null || thisMethod.paramList.size() == 0) {
				JOptionPane.showMessageDialog(page, "No parameters currently!");
				return;
			}
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(page, "No parameters currently!");
				return;
			}
			model = thisMethod.paramModel;
			model1 = thisMethod.paramModel1;
			
			// Checks to see if combo box is empty
			if (comboBox_1SelectedItem.equals("") || !containsAlphaNumeric(comboBox_1SelectedItem)) {
				JOptionPane.showMessageDialog(page, "Please enter a parameter name.");
				return;
			}
			// Removes parameter, updates combo box
			if (thisMethod.removeParam(comboBox_1SelectedItem)) {
				model.removeElement(comboBox_1SelectedItem);
				JOptionPane.showMessageDialog(page, "Parameter removed successfully.");
			} else
				JOptionPane.showMessageDialog(page, "Parameter removal failed, parameter not found.");
		}

	}

	// Parameter for renaming parameters.
	public static class RenameParameter implements ActionListener {
		private Instance thisInstance;
		private String comboBoxContents;
		private String comboBox_1SelectedItem;
		private JFrame page;
		private DefaultComboBoxModel<String> model;
		private DefaultComboBoxModel<String> model1;
		private String comboBox_2Contents;

		// Constructor.
		public RenameParameter(Instance thisInstance, Object comboBoxContents, String comboBox_1SelectedItem,
				JFrame page, String comboBox_2Contents) {
			this.thisInstance = thisInstance;
			if (comboBoxContents != null)
				this.comboBoxContents = comboBoxContents.toString();
			if (comboBox_1SelectedItem != null)
				this.comboBox_1SelectedItem = comboBox_1SelectedItem;
			if (comboBox_2Contents != null)
				this.comboBox_2Contents = comboBox_2Contents;
			this.page = page;
		}

		public void actionPerformed(ActionEvent e) {
			Methods thisMethod;
			comboBox_1SelectedItem = ParametersPage.comboContents;
			// Tests if classes already exist
			try {
				thisMethod = thisInstance.getClass(comboBoxContents).getMethod(ParametersPage.comboBox_3.getSelectedItem().toString());
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Please enter classes first.");
				return;
			}
			// Checks for empty text box.
			if (comboBox_1SelectedItem == null || comboBox_2Contents == null || comboBox_1SelectedItem.equals("")
					|| comboBox_2Contents.equals("") || !containsAlphaNumeric(comboBox_1SelectedItem)
					|| !containsAlphaNumeric(comboBox_2Contents)) {

				JOptionPane.showMessageDialog(page, "Please enter a parameter and new name.");
				return;
			}
			
			model = thisMethod.paramModel;
			model1 = thisMethod.paramModel1;
			
			// Checks if Parameter exists.
			try {
				Fields orig = thisMethod.getParam(comboBox_1SelectedItem);
				String test = orig.getName();
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(page, "Parameter not found.");
				return;
			}

			// Checks if rename is already taken.
			Fields orig2 = thisMethod.getParam(comboBox_2Contents);
			try {
				String test2 = orig2.getName();
			} catch (NullPointerException e1) {
				// Renames parameter, updates Parameter combo box
				Fields orig3 = thisMethod.getParam(comboBox_1SelectedItem);
				if (orig3.rename(comboBox_2Contents)) {
					model.addElement(comboBox_2Contents);
					model.removeElement(comboBox_1SelectedItem);
					model1.addElement(comboBox_2Contents);
					model1.removeElement(comboBox_1SelectedItem);
					ParametersPage.comboBox_1.setSelectedItem(comboBox_2Contents);
					JOptionPane.showMessageDialog(page, "Parameter renamed successfully.");
					return;
				} else {
					JOptionPane.showMessageDialog(page, "Parameter not found.");
					return;
				}
			}
			JOptionPane.showMessageDialog(page, "Parameter already exists!");
			return;
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	/** Methods related to #LandingPage. 
	**
	**
	**/
	
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
	
	// Home button action for methods page.
	public static ActionListener methodsButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LandingPage.homepage.dispose();
			try {
				initController(LandingPage.thisInstance, "MethodsPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};
	
	
	
	// Home button action for fields page.
	public static ActionListener fieldsButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LandingPage.homepage.dispose();
			try {
				initController(LandingPage.thisInstance, "FieldsPage");
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
			try {
			if (LandingPage.thisInstance.classList == null || LandingPage.thisInstance.classList.size() == 0) {
				JOptionPane.showMessageDialog(LandingPage.homepage, "Please enter classes first.");
				return;
			}
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(LandingPage.homepage, "Please enter classes first.");
				return;
			}

			// Sets up table for pop-up
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Class Name");
			model.addColumn("Relationship Source");
			model.addColumn("Relationship Destination");
			model.addColumn("Relationship Type");
			model.addColumn("Field Name");
			model.addColumn("Field Type");
			model.addColumn("Method Names");
			model.addColumn("Method Types");
			model.addColumn("Parameter Names");
			model.addColumn("Parameter Types");
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
				// Adds all class's relationships
				if (thisClass.relationshipList != null) {
					if (thisClass.relationshipList.size() != 0) {
						for (int i = 0; i < thisClass.relationshipList.size(); i++) {
							model.addRow(new Object[] { " ", thisClass.relationshipList.get(i).getSource(),
									thisClass.relationshipList.get(i).getDestination(), thisClass.relationshipList.get(i).getType() });

						}
					}
				}
				// Adds all class's fields
				if (thisClass.fieldList != null) {
					if (thisClass.fieldList.size() != 0) {
						for (int i = 0; i < thisClass.fieldList.size(); i++) {
							model.addRow(new Object[] { " ", " ", " ", " ", thisClass.fieldList.get(i).getName(), thisClass.fieldList.get(i).getType() });
						}
					}
				}
				
				// Adds all class's methods and parameters
				if (thisClass.methodList != null) {
					if (thisClass.methodList.size() != 0) {
						for (int i = 0; i < thisClass.methodList.size(); i++) {
							Methods thisMethod = thisClass.methodList.get(i);
							model.addRow(new Object[] { " ", " ", " ", " ", " ", " ", thisMethod.getName(), thisMethod.getType() });
							if (thisMethod.paramList.size() != 0)
								for (int j = 0; j<thisMethod.paramList.size(); j++) {
									model.addRow(new Object[] { " ", " ", " ", " ", " ", " ", " ", " ", thisMethod.paramList.get(j).getName(), thisMethod.paramList.get(j).getType() });
								}
						}
					}
				}
			}
			// Adjusting table so the headers are fully visible
			JTable table = new JTable(model);
			table.getColumnModel().getColumn(0).setPreferredWidth(110);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(150);
			table.getColumnModel().getColumn(3).setPreferredWidth(150);
			table.getColumnModel().getColumn(4).setPreferredWidth(110);
			table.getColumnModel().getColumn(5).setPreferredWidth(110);
			table.getColumnModel().getColumn(6).setPreferredWidth(150);
			table.getColumnModel().getColumn(7).setPreferredWidth(150);
			table.getColumnModel().getColumn(8).setPreferredWidth(150);
			table.getColumnModel().getColumn(9).setPreferredWidth(150);
			table.setPreferredScrollableViewportSize(table.getPreferredSize());
			table.setFillsViewportHeight(true);
			JOptionPane.showMessageDialog(null, new JScrollPane(table));

		}
	};

	// Overrides required, since Swing doesn't natively support clickable text
	public static MouseAdapter lanHelpListener = new MouseAdapter() {
		String s = "<html>Classes:&#9Add, Delete, Rename, and List Classes and their contents<br> Fields:&#9Add/Delete fields to/from classes, and Rename fields<br> Relationships: Add and Delete Relationships between two Classes<br> Save:&#9Save current instance in a JSON file format<br> Load:&#9Load JSON files from directory<br> Exit:&#9Close the application</html>";

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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** Methods related to #RelationshipsPage.
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
	
	
	// Action Listener for adding relationships.
	public static ActionListener finalRel = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().equals("")) {
				JButton input1 = RelationshipsPage.btnNewButton;
				JButton input2 = RelationshipsPage.btnDelete;
				try {
					input1.removeActionListener(rel);
					input2.removeActionListener(delRela);
				} catch (Exception e1) {
				}
				rel = addRel;
				input1.addActionListener(rel);
				delRela = delRel;
				input2.addActionListener(delRela);
			}
		}
	};
	
	
	// Prevent button use with empty Classes field.
	public static ActionListener rel = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter classes first!");
		}
	};

	// Prevent button use with empty text field.
	public static ActionListener delRela = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please enter classes first!");
		}
	};
	
	
	// Home button action for Relationships page.
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

	// Initial creation of Relationship models.
	public static void relInit(Instance inputInstance) {
		RelationshipsPage.thisInstance = inputInstance;
		RelationshipsPage.comboBox = new JComboBox<String>(inputInstance.classModel);
		RelationshipsPage.comboBox_1 = new JComboBox<String>(inputInstance.classModel_2);
	}
	
	// Set group must be called after button initialization.
	public static void setGroup() {
		RelationshipsPage.group = new ButtonGroup();
		RelationshipsPage.group.add(RelationshipsPage.rdbtnNewRadioButton);
		RelationshipsPage.group.add(RelationshipsPage.rdbtnNewRadioButton_1);
		RelationshipsPage.group.add(RelationshipsPage.rdbtnNewRadioButton_2);
		RelationshipsPage.group.add(RelationshipsPage.rdbtnNewRadioButton_3);
	}

	// Action listener to add relationship
	public static ActionListener addRel = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// Checks that classes exist
			String sourceClassName;
			String destinationClassName;
			String type;
			try {
				sourceClassName = RelationshipsPage.comboBox.getSelectedItem().toString();
				destinationClassName = RelationshipsPage.comboBox_1.getSelectedItem().toString();
				type = RelationshipsPage.group.getSelection().getActionCommand();
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
			Relationships orig = sourceClass.getRelationship(sourceClassName, destinationClassName, type);
			if (orig != null) {
				JOptionPane.showMessageDialog(RelationshipsPage.relationPage, "Relationship already exists!");
				return;
			}
			// Adds relationship
			sourceClass.addRelationship(sourceClassName, destinationClassName, type);
			destinationClass.addRelationship(sourceClassName, destinationClassName, type);
			JOptionPane.showMessageDialog(RelationshipsPage.relationPage, "Relationship added successfully.");
		}
	};

	// Action listener to remove relationship
	public static ActionListener delRel = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// Check that classes exist
			String sourceClassName;
			String destinationClassName;
			String type;
			try {
				sourceClassName = RelationshipsPage.comboBox.getSelectedItem().toString();
				destinationClassName = RelationshipsPage.comboBox_1.getSelectedItem().toString();
				type = RelationshipsPage.group.getSelection().getActionCommand();
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
			Relationships orig = sourceClass.getRelationship(sourceClassName, destinationClassName, type);
			if (orig == null) {
				JOptionPane.showMessageDialog(RelationshipsPage.relationPage, "Relationship not found!");
				return;
			}
			// Removes relationship
			if (sourceClass.removeRelationship(sourceClassName, destinationClassName, type)
					&& destinationClass.removeRelationship(sourceClassName, destinationClassName, type))
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
				model.addColumn("Type");
				// Creates list of already-listed relationships
				Object[][] allRelations = new Object[0][2];
				for (int i = 0; i < RelationshipsPage.thisInstance.classList.size(); i++) {
					Classes thisClass = RelationshipsPage.thisInstance.classList.get(i);
					// Checks for empty relationship list
					if (thisClass.relationshipList != null && thisClass.relationshipList.size() != 0) {
						for (int j = 0; j < thisClass.relationshipList.size(); j++) {
							Object[] newObject = new Object[] { thisClass.relationshipList.get(j).source,
									thisClass.relationshipList.get(j).destination, thisClass.relationshipList.get(j).type  };
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	/** #Utility methods.
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
	
	// Function for adding a object array to a array of object arrays.
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
