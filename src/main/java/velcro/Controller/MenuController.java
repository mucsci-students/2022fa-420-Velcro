/**
 * Filename: MenuController.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Controller for the menu GUI.
 * 
 */

package main.java.velcro.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;

import main.java.velcro.Model.Classes;
import main.java.velcro.Model.Fields;
import main.java.velcro.Model.Instance;
import main.java.velcro.Model.IteratorList;
import main.java.velcro.Model.Methods;
import main.java.velcro.Model.Observer;
import main.java.velcro.Model.Parameters;
import main.java.velcro.Model.Relationships;
import main.java.velcro.Model.ZEllipse;
import main.java.velcro.Obsolete.SavePage;
import main.java.velcro.View.GUIFrame;
import main.java.velcro.View.MenuFrame;

public class MenuController {
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
		MenuFrame.thisInstance
				.setHighlight(MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()));
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
						thisClass = GUIFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem());
						thisClass.addField(fieldName.getText(), (String) type.getSelectedItem());
						MenuFrame.fieldModel.addElement(fieldName.getText());
					}
				}

				MenuFrame.repaintMe();
				GUIFrame.draw();
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
				GUIFrame.draw();
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
						Classes thisClass = MenuFrame.thisInstance
								.getClass((String) MenuFrame.comboBox.getSelectedItem());
						if (thisClass.getMethod((String) MenuFrame.comboBox_1_1_2.getSelectedItem()) != null) {
							Methods thisMethod = thisClass
									.getMethod((String) MenuFrame.comboBox_1_1_2.getSelectedItem());
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
				GUIFrame.draw();
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
							int n = JOptionPane.showOptionDialog(null, "File already exists! Click OK to overwrite!",
									"WARNING", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
									options[1]);
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
				GUIFrame.draw();
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

	// Take a screenshot of the current GUI.
	public static void setBtn11Listener() {
		Observer btnNewObserver_11 = new Observer(MenuFrame.btnNewButton_11, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
				fileChooser.setDialogTitle("Specify a save location");
				int userSelection = fileChooser.showSaveDialog(null);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					String savelocation;
					if (!(savelocation = fileToSave.getAbsolutePath()).equals("")) {
						if (savelocation.length() <4 || !savelocation.substring(savelocation.length() - 4).equals(".png")) {
							savelocation = savelocation + ".png";
						}
						File newFile = new File(savelocation);
						// Check to make sure file doesn't already exist.
						if (newFile.exists()) {
							Object[] options = { "OK", "CANCEL" };
							// Option to overwrite file.
							int n = JOptionPane.showOptionDialog(null,
									"File already exists! Click OK to overwrite!", "WARNING", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
							if (n == 1) {
								JOptionPane.showMessageDialog(null, "Save file cancelled.");
								return;
							} else {
									JPanel input = GUIFrame.myFrame;
									BufferedImage screenshotImage = new BufferedImage(input.getBounds().width, input.getBounds().height, BufferedImage.TYPE_INT_RGB);
									input.paint(screenshotImage.getGraphics());
									try {
										ImageIO.write(screenshotImage, "png", new File(fileToSave.getAbsolutePath()));
									} catch (IOException ex) {
										System.out.println("Error saving screenshot");
										return;
									}
								JOptionPane.showMessageDialog(null, "File overwritten successfully.");
								return;
							}
						}
									JPanel input = GUIFrame.myFrame;
									BufferedImage screenshotImage = new BufferedImage(input.getBounds().width, input.getBounds().height, BufferedImage.TYPE_INT_RGB);
									input.paint(screenshotImage.getGraphics());
									try {
										ImageIO.write(screenshotImage, "png", new File(savelocation));
									} catch (IOException ex) {
										System.out.println("Error saving screenshot");
										return;
									}
						JOptionPane.showMessageDialog(null, "Screenshot saved successfully.");
					}
				}
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
				if (MenuFrame.thisInstance
						.getClass(className = (String) MenuFrame.comboBox.getSelectedItem()) != null) {
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
					Classes thisClass = MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem());
					if (thisClass.getField(fieldName) == null)
						return;
					thisClass.removeField(fieldName);
					MenuFrame.fieldModel.removeElement(fieldName);
					MenuFrame.repaintMe();
					thisClass.width = 80;
					thisClass.height = 0;
					GUIFrame.draw();
				}
			}
		});
	}

	// Remove relationship button listener.
	public static void setBtn14Listener() {
		Observer btnNewObserver_14 = new Observer(MenuFrame.btnNewButton_14, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"To remove a relationship, right-click (or shift-click) on the relationship line.");
			}
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
					Classes thisClass = MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem());
					if (thisClass.getMethod(methodName) == null)
						return;
					thisClass.removeMethod(methodName);
					MenuFrame.methodsModel.removeElement(methodName);
					firstLoad();
					thisClass.width = 80;
					thisClass.height = 0;
					MenuFrame.repaintMe();
					GUIFrame.draw();
				}
			}
		});
	}

	// Remove parameter button listener.
	public static void setBtn16Listener() {
		Observer btnNewObserver_16 = new Observer(MenuFrame.btnNewButton_16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MenuFrame.comboBox.getSelectedItem() == null || MenuFrame.comboBox_1_1_2_1.getSelectedItem() == null
						|| MenuFrame.comboBox_1_1_2.getSelectedItem() == null)
					return;
				String methodName = MenuFrame.comboBox_1_1_2.getSelectedItem().toString();
				MenuFrame.thisMemento.add(MenuFrame.thisInstance);
				if (MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem()) != null) {
					Classes thisClass = MenuFrame.thisInstance.getClass((String) MenuFrame.comboBox.getSelectedItem());
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
					thisClass.width = 80;
					thisClass.height = 0;
					MenuFrame.repaintMe();
					GUIFrame.draw();
				}
			}
		});
	}

}
