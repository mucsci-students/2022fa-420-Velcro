/**
 * Filename: DrawingGUI.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Main initialization method for the visual GUI.
 * 
 */


package main.java.velcro.Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

public class GUIController {

	/**
	 * MenuFrame methods.
	 */
	
	// Populate initial ClassModel model.
	public static void initClassModel(DefaultComboBoxModel<String> classModel) {
		for (int i = 0; i < MenuFrame.thisInstance.classList.size(); i++) {
			classModel.addElement(MenuFrame.thisInstance.classList.get(i).getName());
		}
	}

	// Matches first combobox with class specified as highlight.
	public static void setHighlight() {
		if (MenuFrame.thisInstance.highlight != null) {
			for (int i = 0; i < MenuFrame.thisInstance.classList.size(); i++) {
				if (MenuFrame.thisInstance.highlight.equals(MenuFrame.thisInstance.classList.get(i))) {
					MenuFrame.comboBox.setSelectedItem(MenuFrame.thisInstance.classList.get(i).getName());
				}
			}
		}
	}

	// Add Observer to class combobox.
	public static void setComboBoxListener() {
		Observer comboObserver = new Observer(MenuFrame.comboBox, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstLoad();
			}
		});
	}

	// Method to run on first load to ensure comboboxes match instance data.
	public static void firstLoad() {
		MenuFrame.thisInstance.setHighlight(MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()));
		MenuFrame.thisObject.repaint();

		Classes thisClass;
		if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
			MenuFrame.fieldModel.removeAllElements();
			thisClass = MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem());
			for (int i = 0; i < thisClass.fieldList.size(); i++) {
				MenuFrame.fieldModel.addElement(thisClass.fieldList.get(i).getName());
			}
		}

		if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
			MenuFrame.relationshipsModel.removeAllElements();
			thisClass = MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem());
			for (int i = 0; i < thisClass.relationshipList.size(); i++) {

				if (!thisClass.relationshipList.get(i).getDestination().equals(thisClass.getName())) {
					MenuFrame.relationshipsModel.addElement(thisClass.relationshipList.get(i).getDestination());
				}
			}
		}

		if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
			MenuFrame.methodsModel.removeAllElements();
			thisClass = MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem());
			for (int i = 0; i < thisClass.methodList.size(); i++) {
				MenuFrame.methodsModel.addElement(thisClass.methodList.get(i).getName());
			}
		}
	}

	// Set observer for Relationships combobox.
	public static void setComboBox1Listener() {
		Observer combo_1Observer = new Observer(MenuFrame.comboBox_1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Classes thisClass;
				Relationships thisRelationship;
				if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
					MenuFrame.relationshipsTypeModel.removeAllElements();
					thisClass = MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem());
					if (thisClass.getRelationship(thisClass.getName(),
							(String) MenuFrame.comboBox_1.getSelectedItem()) != null) {
						thisRelationship = thisClass.getRelationship(thisClass.getName(),
								(String) MenuFrame.comboBox_1.getSelectedItem());
						MenuFrame.relationshipsTypeModel.addElement(thisRelationship.getType());
					}
				}
			}
		});
	}

	// Set field combobox listener.
	public static void setComboBox111Listener() {
		Observer combo_1_1_1Observer = new Observer(MenuFrame.comboBox_1_1_1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Classes thisClass;
				Fields thisField;
				if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
					MenuFrame.fieldTypeModel.removeAllElements();
					thisClass = MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem());
					if (thisClass.getField((String) MenuFrame.comboBox_1_1_1.getSelectedItem()) != null) {
						thisField = thisClass.getField((String) MenuFrame.comboBox_1_1_1.getSelectedItem());
						MenuFrame.fieldTypeModel.addElement(thisField.getType());
					}
				}
			}
		});
	}

	// Set methods combobox listener.
	public static void setComboBox112Listener() {
		Observer combo_1_1_2Observer = new Observer(MenuFrame.comboBox_1_1_2, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Classes thisClass;
				Methods thisMethod;
				MenuFrame.methodsTypeModel.removeAllElements();
				MenuFrame.paramsModel.removeAllElements();
				if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
					thisClass = MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem());
					if (thisClass.getMethod((String) MenuFrame.comboBox_1_1_2.getSelectedItem()) != null) {
						thisMethod = thisClass.getMethod((String) MenuFrame.comboBox_1_1_2.getSelectedItem());
						MenuFrame.methodsTypeModel.addElement(thisMethod.getType());
						if (thisMethod.paramList != null) {
							for (int i = 0; i < thisMethod.paramList.size(); i++) {
								MenuFrame.paramsModel.addElement(thisMethod.paramList.get(i).getName());
							}
						}
					}

				}
			}
		});

	}

	// Set parameters combobox listener.
	public static void setComboBox1121Listener() {
		Observer combo_1_1_2_1Observer = new Observer(MenuFrame.comboBox_1_1_2_1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Classes thisClass;
				Parameters thisParam;
				Methods thisMethod;
				MenuFrame.paramsTypeModel.removeAllElements();
				if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
					thisClass = MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem());
					if (thisClass.getMethod((String) MenuFrame.comboBox_1_1_2.getSelectedItem()) != null) {
						thisMethod = thisClass.getMethod((String) MenuFrame.comboBox_1_1_2.getSelectedItem());
						if (thisMethod.getParam((String) MenuFrame.comboBox_1_1_2_1.getSelectedItem()) != null) {
							thisParam = thisMethod.getParam((String) MenuFrame.comboBox_1_1_2_1.getSelectedItem());
							MenuFrame.paramsTypeModel.addElement(thisParam.getType());
						}
					}

				}
			}
		});
	}

	// Button logic for adding new field.
	public static void setBtnListener() {
		Observer btnNewObserver = new Observer(MenuFrame.btnNewButton, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Please enter the field name and type.";
				JTextField fieldName = new JTextField("Field Name");
				fieldName.addFocusListener((FocusListener) new FocusListener() {
					public void focusGained(FocusEvent e) {
						fieldName.setText("");
					}

					public void focusLost(FocusEvent e) {
					}
				});
				String[] types = { "int", "float", "String", "void", "double" };
				JComboBox type = new JComboBox(new DefaultComboBoxModel<String>(types));
				int result = JOptionPane.showOptionDialog(null, new Object[] { message, fieldName, type }, "Add Field",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (result == JOptionPane.OK_OPTION && fieldName.getText() != null && type.getSelectedItem() != null
						&& !fieldName.getText().equals("") && !((String) type.getSelectedItem()).equals("")) {
					MenuFrame.thisMemento.add(MenuFrame.thisInstance);
					if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
						Classes thisClass = MenuFrame.thisInstance
								.getClass((String) MenuFrame.comboBox.getSelectedItem());
						thisClass.addField(fieldName.getText(), (String) type.getSelectedItem());
						MenuFrame.fieldModel.addElement(fieldName.getText());
					}
				}

				MenuFrame.repaintMe();
			}
		});
	}

	// Button logic for adding new method.
	public static void setBtn1Listener() {
		Observer btnNew_1Observer = new Observer(MenuFrame.btnNewButton_1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Please enter the method name and type.";
				JTextField fieldName = new JTextField("Method Name");
				fieldName.addFocusListener((FocusListener) new FocusListener() {
					public void focusGained(FocusEvent e) {
						fieldName.setText("");
					}

					public void focusLost(FocusEvent e) {
					}
				});
				String[] types = { "int", "float", "String", "void", "double" };
				JComboBox type = new JComboBox(new DefaultComboBoxModel<String>(types));
				int result = JOptionPane.showOptionDialog(null, new Object[] { message, fieldName, type }, "Add Method",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (result == JOptionPane.OK_OPTION && fieldName.getText() != null && type.getSelectedItem() != null
						&& !fieldName.getText().equals("") && !((String) type.getSelectedItem()).equals("")) {
					MenuFrame.thisMemento.add(MenuFrame.thisInstance);
					if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
						Classes thisClass = MenuFrame.thisInstance
								.getClass((String) MenuFrame.comboBox.getSelectedItem());
						thisClass.addMethod(fieldName.getText(), (String) type.getSelectedItem(),
								new ArrayList<Parameters>());
						MenuFrame.methodsModel.addElement(fieldName.getText());
					}
				}

				MenuFrame.repaintMe();
			}
		});
	}

	// Button logic for adding new parameter.
	public static void setBtn2Listener() {
		Observer btnNewObserver_2 = new Observer(MenuFrame.btnNewButton_2, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Please enter the parameter name and type.";
				JTextField fieldName = new JTextField("Parameter Name");
				fieldName.addFocusListener((FocusListener) new FocusListener() {
					public void focusGained(FocusEvent e) {
						fieldName.setText("");
					}

					public void focusLost(FocusEvent e) {
					}
				});

				String[] types = { "int", "float", "String", "void", "double" };
				JComboBox type = new JComboBox(new DefaultComboBoxModel<String>(types));

				int result = JOptionPane.showOptionDialog(null, new Object[] { message, fieldName, type },
						"Add Parameter", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (result == JOptionPane.OK_OPTION && fieldName.getText() != null && type.getSelectedItem() != null
						&& !fieldName.getText().equals("") && !((String) type.getSelectedItem()).equals("")) {
					MenuFrame.thisMemento.add(MenuFrame.thisInstance);
					if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
						Classes thisClass = MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem());
						if (thisClass.getMethod((String) MenuFrame.comboBox_1_1_2.getSelectedItem()) != null) {
							Methods thisMethod = thisClass.getMethod((String) MenuFrame.comboBox_1_1_2.getSelectedItem());
							thisMethod.addParam(fieldName.getText(), (String) type.getSelectedItem());
							MenuFrame.paramsModel.addElement(fieldName.getText());

						} else {
							JOptionPane.showInternalMessageDialog(null,
									"A method must exist prior to adding a parameter.", "Adding Parameter",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				MenuFrame.repaintMe();
			}
		});
	}
	
	// Pop-up instructions for adding relationship.
	public static void setBtn3Listener() {
		Observer btnNewObserver_3 = new Observer(MenuFrame.btnNewButton_3, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInternalMessageDialog(null,
						"To add a relationship, right-click (or shift-click) on the source class, then right-click (or shift-click) on the destination class, then choose the relationship type.",
						"Adding Relationships", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	// Show contents listener.
	public static void setBtn4Listener() {
		Observer btnNewObserver_4 = new Observer(MenuFrame.btnNewButton_4, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame.thisInstance.showContents();
			}
		});
	}
	
	// Undo button listener.
	public static void setBtn5Listener() {
		MenuFrame.btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instance newInstance = MenuFrame.thisMemento.undo(MenuFrame.thisSurface.thisInstance);
				if (newInstance != null) {
					MenuFrame.thisSurface.thisInstance = copyInstance(newInstance);
					MenuFrame.thisInstance = copyInstance(newInstance);
					MenuFrame.thisSurface.revalidate();
					MenuFrame.thisSurface.repaint();
				}
			}
		});
	}
	
	// Method for copying instance.
	public static Instance copyInstance(Instance input) {
		Instance fakeInstance = new Instance();

		for (int i = 0; i < input.classList.size(); i++) {
			fakeInstance.addClass(input.classList.get(i).getName());
			fakeInstance.classList.get(i).setLocation(input.classList.get(i).point.x, input.classList.get(i).point.y);
			for (int j = 0; j < input.classList.get(i).fieldList.size(); j++) {
				fakeInstance.classList.get(i).addField(input.classList.get(i).fieldList.get(j).getName(),
						input.classList.get(i).fieldList.get(j).getType());
			}
			for (int j = 0; j < input.classList.get(i).relationshipList.size(); j++) {
				fakeInstance.classList.get(i).addRelationship(
						input.classList.get(i).relationshipList.get(j).getSource(),
						input.classList.get(i).relationshipList.get(j).getDestination(),
						input.classList.get(i).relationshipList.get(j).getType());
			}
			for (int j = 0; j < input.classList.get(i).methodList.size(); j++) {
				List<Parameters> newParamList = new ArrayList<Parameters>();
				for (int k = 0; k < input.classList.get(i).methodList.get(j).paramList.size(); k++) {
					newParamList.add(new Parameters(input.classList.get(i).methodList.get(j).paramList.get(k).getName(),
							input.classList.get(i).methodList.get(j).paramList.get(k).getType()));
				}
				fakeInstance.classList.get(i).addMethod(input.classList.get(i).methodList.get(j).getName(),
						input.classList.get(i).methodList.get(j).getType(), newParamList);
			}
		}
		fakeInstance.setHighlight(input.highlight);
		return fakeInstance;
	}
	
	// Redo button listener.
	public static void setBtn6Listener() {
		Observer btnNewObserver_6 = new Observer(MenuFrame.btnNewButton_6, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instance newInstance = MenuFrame.thisMemento.redo();
				if (newInstance != null) {
					MenuFrame.thisSurface.thisInstance = copyInstance(newInstance);
					MenuFrame.thisInstance = copyInstance(newInstance);
					MenuFrame.thisSurface.revalidate();
					MenuFrame.thisSurface.repaint();
				}
			}
		});
	}
	
	// Save button listener.
	public static void setBtn7Listener() {
		MenuFrame.btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
				fileChooser.setDialogTitle("Specify a file to save");
				int userSelection = fileChooser.showSaveDialog(null);
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
								JOptionPane.showMessageDialog(null, "Save file cancelled.");
								return;
							} else {
								BufferedWriter writer;
								try {
									// Writes the json file.
									writer = new BufferedWriter(new FileWriter(newFile, true));
									writer.append(MenuFrame.thisInstance.printToJson());
									writer.close();
								} catch (IOException e1) {
								}
								JOptionPane.showMessageDialog(null, "File overwritten successfully.");
								return;
							}
						}
						BufferedWriter writer;
						try {
							// Writes the json file.
							writer = new BufferedWriter(new FileWriter(newFile, true));
							writer.append(convertToGsonString(MenuFrame.thisInstance));
							writer.close();
						} catch (IOException e1) {
						}
						JOptionPane.showMessageDialog(null, "File saved successfully.");
					}
				}
			}
		});
	}
	
	// Affix ".json" to end of file name if not already present.
	private static String affixJson(String input) {
		if (input.length() > 5 && input.substring(input.length() - 5).equals(".json")) {
			return input;
		}
		return input += ".json";
	}
	
	// Method to convert object to String.
	public static String convertToGsonString(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
	// Method for translating json file into a Instance object.
	private static Instance jsonReader(File file) throws Exception {
		Instance newInstance = new Instance();
		Gson gson = new Gson();
		newInstance = gson.fromJson(new FileReader(file), Instance.class);
		return newInstance;
	}
	
	// Method for updating the class model on demand.
	private static void rebuildClassModel(Instance input) {
		DefaultComboBoxModel<String> newClassModel = new DefaultComboBoxModel<String>();
		IteratorList.Iterator iter = MenuFrame.thisSurface.arr.getIterator();
		for (iter.first(); !iter.isDone(); iter.next()) {
			newClassModel.addElement(iter.currentValue().className);
		}

		MenuFrame.classModel = newClassModel;
		MenuFrame.comboBox.setModel(newClassModel);
	}
	
	// Load button listener.
	public static void setBtn8Listener() {
		Observer btnNewObserver_8 = new Observer(MenuFrame.btnNewButton_8, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
				fileChooser.setDialogTitle("Specify a file to load");
				int userSelection = fileChooser.showSaveDialog(null);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToLoad = fileChooser.getSelectedFile();
					if (!fileToLoad.getAbsolutePath().equals("")) {
						Instance newInstance;
						try {
							newInstance = jsonReader(fileToLoad);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Error detected, no file loaded.");
							return;
						}
						MenuFrame.thisSurface.thisInstance = copyInstance(newInstance);
						MenuFrame.thisInstance = copyInstance(newInstance);

						MenuFrame.thisSurface.arr.clear();
						for (int i = 0; i < MenuFrame.thisInstance.classList.size(); i++) {
							MenuFrame.thisSurface.arr.add(new ZEllipse(MenuFrame.thisInstance.classList.get(i).point.x,
									MenuFrame.thisInstance.classList.get(i).point.y, 80, 80,
									MenuFrame.thisInstance.classList.get(i).getName(), MenuFrame.thisInstance));
						}

						rebuildClassModel(MenuFrame.thisInstance);
						MenuFrame.thisSurface.revalidate();
						MenuFrame.thisSurface.repaint();

						JOptionPane.showMessageDialog(null, "File loaded successfully.");
					}
				}
				MenuFrame.repaintMe();
			}
			
		});
	}
	

	// Exit button listener.
	public static void setBtn9Listener() {
		Observer btnNewObserver_9 = new Observer(MenuFrame.btnNewButton_9, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	// Exit button listener.
	public static void setBtn10Listener() {
		Observer btnNewObserver_10 = new Observer(MenuFrame.btnNewButton_10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MenuFrame.comboBox.getSelectedItem() == null)
					return;
				MenuFrame.thisInstance.showContents(MenuFrame.comboBox.getSelectedItem().toString());
			}
		});
	}
	
	// Load example data listener.
	public static void setBtn11Listener() {
		Observer btnNewObserver_11 = new Observer(MenuFrame.btnNewButton_11, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instance thisInstance1 = new Instance();
				thisInstance1.addClass("class1");
				thisInstance1.getClass("class1").addField("field1", "String");
				thisInstance1.getClass("class1").addField("field2", "int");
				thisInstance1.addClass("class2");
				thisInstance1.addClass("class3");
				thisInstance1.addClass("class4");
				thisInstance1.addClass("class5");
				thisInstance1.addClass("class6");
				List<Parameters> params2 = new ArrayList<Parameters>();
				params2.add(new Parameters("param2", "double"));
				params2.add(new Parameters("param3", "String"));
				params2.add(new Parameters("param4", "int"));
				thisInstance1.getClass("class5").addMethod("method2", "int", params2);
				thisInstance1.getClass("class5").addMethod("method3", "String", new ArrayList<Parameters>());
				thisInstance1.getClass("class5").addMethod("method4", "float", new ArrayList<Parameters>());
				thisInstance1.getClass("class6").addField("field3", "double");
				thisInstance1.getClass("class6").addField("field4", "int");
				List<Parameters> params1 = new ArrayList<Parameters>();
				params1.add(new Parameters("param1", "float"));
				thisInstance1.getClass("class1").setLocation(40, 40);
				thisInstance1.getClass("class2").setLocation(80, 340);
				thisInstance1.getClass("class3").setLocation(180, 140);
				thisInstance1.getClass("class4").setLocation(540, 540);
				thisInstance1.getClass("class5").setLocation(380, 190);
				thisInstance1.getClass("class6").setLocation(540, 240);
				thisInstance1.getClass("class3").addMethod("method1", "int", params1);
				thisInstance1.getClass("class3").addRelationship("class3", "class2", "Realization");
				thisInstance1.getClass("class2").addRelationship("class3", "class2", "Realization");
				thisInstance1.getClass("class2").addRelationship("class1", "class2", "Aggregation");
				thisInstance1.getClass("class1").addRelationship("class1", "class2", "Aggregation");
				thisInstance1.getClass("class2").addRelationship("class2", "class4", "Inheritance");
				thisInstance1.getClass("class4").addRelationship("class2", "class4", "Inheritance");
				thisInstance1.setHighlight(thisInstance1.getClass("class2"));
				MenuFrame.thisSurface.thisInstance = copyInstance(thisInstance1);
				MenuFrame.thisInstance = copyInstance(thisInstance1);

				MenuFrame.thisSurface.arr.clear();
				for (int i = 0; i < MenuFrame.thisInstance.classList.size(); i++) {
					MenuFrame.thisSurface.arr.add(
							new ZEllipse(MenuFrame.thisInstance.classList.get(i).point.x, MenuFrame.thisInstance.classList.get(i).point.y,
									80, 80, MenuFrame.thisInstance.classList.get(i).getName(), MenuFrame.thisInstance));
				}
				MenuFrame.thisSurface.revalidate();
				MenuFrame.thisSurface.repaint();
				rebuildClassModel(MenuFrame.thisInstance);
			}
		});
	}
	
	// Remove class button listener.
	public static void setBtn12Listener() {
		Observer btnNewObserver_12 = new Observer(MenuFrame.btnNewButton_12, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MenuFrame.comboBox.getSelectedItem() == null)
					return;
				String className;
				if (MenuFrame.thisInstance.getClass(className = (String) MenuFrame.comboBox.getSelectedItem()) != null) {
					MenuFrame.thisInstance.removeClass(className);
					MenuFrame.classModel.removeElement(className);
					GUIFrame.thisInstance.removeClass(className);
					GUIFrame.arr.remove(className);
					MenuFrame.repaintMe();
					GUIFrame.draw();
				}
			}
		});
	}
	
	// Remove field button listener.
	public static void setBtn13Listener() {
		Observer btnNewObserver_13 = new Observer(MenuFrame.btnNewButton_13, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MenuFrame.comboBox.getSelectedItem() == null || MenuFrame.comboBox_1_1_1.getSelectedItem() == null)
					return;
				String fieldName = MenuFrame.comboBox_1_1_1.getSelectedItem().toString();
				MenuFrame.thisMemento.add(MenuFrame.thisInstance);
				if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
					Classes thisClass = MenuFrame.thisInstance
							.getClass((String) MenuFrame.comboBox.getSelectedItem());
					if (thisClass.getField(fieldName) == null)
						return;
					thisClass.removeField(fieldName);
					MenuFrame.fieldModel.removeElement(fieldName);
					MenuFrame.repaintMe();
				}
			}
		});
	}
	
	// Remove relationship button listener.
	public static void setBtn14Listener() {
		Observer btnNewObserver_14 = new Observer(MenuFrame.btnNewButton_14, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "To remove a relationship, right-click (or shift-click) on the relationship line.");			}
		});
	}
	
	// Remove method button listener.
	public static void setBtn15Listener() {
		Observer btnNewObserver_10 = new Observer(MenuFrame.btnNewButton_15, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MenuFrame.comboBox.getSelectedItem() == null || MenuFrame.comboBox_1_1_2.getSelectedItem() == null)
					return;
				String methodName = MenuFrame.comboBox_1_1_2.getSelectedItem().toString();
				MenuFrame.thisMemento.add(MenuFrame.thisInstance);
				if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
					Classes thisClass = MenuFrame.thisInstance
							.getClass((String) MenuFrame.comboBox.getSelectedItem());
					if (thisClass.getMethod(methodName) == null)
						return;
					thisClass.removeMethod(methodName);
					MenuFrame.methodsModel.removeElement(methodName);
					firstLoad();
					MenuFrame.repaintMe();
				}
			}
		});
	}
	
	// Remove parameter button listener.
	public static void setBtn16Listener() {
		Observer btnNewObserver_16 = new Observer(MenuFrame.btnNewButton_16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MenuFrame.comboBox.getSelectedItem() == null || MenuFrame.comboBox_1_1_2_1.getSelectedItem() == null || MenuFrame.comboBox_1_1_2.getSelectedItem() == null)
					return;
				String methodName = MenuFrame.comboBox_1_1_2.getSelectedItem().toString();
				MenuFrame.thisMemento.add(MenuFrame.thisInstance);
				if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
					Classes thisClass = MenuFrame.thisInstance
							.getClass((String) MenuFrame.comboBox.getSelectedItem());
					Methods thisMethod;
					if ((thisMethod = thisClass.getMethod(methodName)) == null)
						return;
					
					String paramName = MenuFrame.comboBox_1_1_2_1.getSelectedItem().toString();
					Parameters thisParam;
					if ((thisParam = thisMethod.getParam(paramName)) == null)
						return;
					
					thisMethod.removeParam(paramName);
					MenuFrame.paramsModel.removeElement(paramName);
					firstLoad();
					MenuFrame.repaintMe();
				}
			}
		});
	}
	
	
	/**
		GUIFrame methods.
	**/
	
	// Retrieve ZEllipse given input name.
	public static ZEllipse getEll(String name) {
		if (GUIFrame.arr.size() > 0) {
			IteratorList.Iterator first = GUIFrame.arr.getIterator();
			for (first.first(); !first.isDone(); first.next())
				if (first.currentValue().className.equals(name))
					return first.currentValue();
		}
		return null;
	}
	
	// Determines if a click misses all lines.
	public static boolean missAllLines(List<ZLine> arr, int count, double x, double y) {
		if (count >= arr.size())
			return true;
		if (arr.get(count).ptLineDist(x, y) < 1)
			return false;
		return missAllLines(arr, count + 1, x, y);
	}
	
	// Returns hit line on click.
	public static ZLine lineItHit(List<ZLine> arr, double x, double y) {
		for (int i = 0; i < arr.size(); i++)
			if (arr.get(i).ptLineDist(x, y) < 1)
				return arr.get(i);
		return null;
	}
	
	// Perform drawing.
	public static void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("Serif", Font.BOLD, 20);
		g2d.setFont(font);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		GUIFrame.arr.clear();
		for (int i = 0; i < GUIFrame.thisInstance.classList.size(); i++) {
			GUIFrame.arr.add(new ZEllipse(GUIFrame.thisInstance.classList.get(i).point.x, GUIFrame.thisInstance.classList.get(i).point.y, 80, 80,
					GUIFrame.thisInstance.classList.get(i).getName(), GUIFrame.thisInstance));
		}
		IteratorList.Iterator iter = GUIFrame.arr.getIterator();
		for (iter.first(); !iter.isDone(); iter.next()) {
			g2d.setPaint(new Color(0, 0, 0));
			if (iter.currentValue() != null && iter.currentValue().className != null) {
				g2d.drawString(iter.currentValue().className, (int) iter.currentValue().getX() + 10,
						(int) iter.currentValue().getY() + 100);

			}
			
			if (GUIFrame.thisInstance.highlight != null) {
				if (iter.currentValue().className.equals(GUIFrame.thisInstance.highlight.getName())) {
					g2d.setPaint(new Color(255, 0, 0));
					g2d.draw(new ZRectangle((float) iter.currentValue().getX(), (float) iter.currentValue().getY(), 80,
							80));
				}
			}
			g2d.setPaint(new Color(0, 0, 200));
			g2d.fill(iter.currentValue());
		}
		GUIFrame.lnLst.clear();
		for (int i = 0; i < GUIFrame.thisInstance.classList.size(); i++) {
			for (int j = 0; j < GUIFrame.thisInstance.classList.get(i).relationshipList.size(); j++) {
				ZEllipse source = GUIController.getEll(GUIFrame.thisInstance.classList.get(i).relationshipList.get(j).getSource());
				ZEllipse destination = GUIController.getEll(GUIFrame.thisInstance.classList.get(i).relationshipList.get(j).getDestination());
				String type = GUIFrame.thisInstance.classList.get(i).relationshipList.get(j).getType();
				g2d.setColor(Color.RED);
				ZLine line = GUIFrame.addLine(source, destination, g2d, type,
						GUIFrame.thisInstance.classList.get(i).relationshipList.get(j).getSource(),
						GUIFrame.thisInstance.classList.get(i).relationshipList.get(j).getDestination());
				if (source == null || destination == null || source.equals(destination))
					continue;
				g2d.draw(line);
				GUIFrame.lnLst.add(line);
			}
		}

		if (GUIFrame.source != null) {
			g2d.setPaint(new Color(255, 0, 0));
			g2d.drawString("Source", (int) GUIFrame.source.getX() + 15, (int) GUIFrame.source.getY() + 47);
		}
		g2d.dispose();
	}
	
	
}
