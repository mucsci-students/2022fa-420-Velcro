/**
 * Filename: Instance.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Instance class, represents a set of data containing Fields, Classes, and Relationships lists.
 * 
 */

package main.java.velcro.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Instance {

	// Required fields.
	@SerializedName("classes")
	@Expose
	public List<Classes> classList;
	public static DefaultComboBoxModel<String> classModel;
	public static DefaultComboBoxModel<String> classModel_2;
	public static DefaultComboBoxModel<String> classModel_3;
	public static DefaultComboBoxModel<String> returnTypes;
	public static Classes highlight;

	// Constructor.
	public Instance() {
		classList = new ArrayList<Classes>();
		classModel = new DefaultComboBoxModel<String>();
		classModel_2 = new DefaultComboBoxModel<String>();
		classModel_3 = new DefaultComboBoxModel<String>();
		returnTypes = new DefaultComboBoxModel<String>();
		returnTypes.addElement("string");
		returnTypes.addElement("void");
		returnTypes.addElement("float");
		returnTypes.addElement("int");
		returnTypes.addElement("double");
		returnTypes.addElement("char");
	}


	// Sets Highlight Class.
	public void setHighlight(Classes input) {
		this.highlight = input;
	}

	// Adds an input class to the Classes array.
	public void addClass(String elementAdded) {
		classList.add(new Classes(elementAdded));
	}

	// Removes an input class from the Classes array.
	public boolean removeClass(String elementRemoved) {
		if (this.classList == null || this.classList.size() == 0)
			return false;
		boolean found = false;
		for (int i = 0; i < classList.size(); i++) {
			if (classList.get(i).getName().equals(elementRemoved)) {
				found = true;
				classList.remove(classList.get(i));
				continue;
			}
		}
		return found;
	}

	// Overloaded removeClass, to accept Classes as input for future development.
	public boolean removeClass(Classes input) {
		if (this.classList == null || this.classList.size() == 0)
			return false;
		boolean found = false;
		for (int i = 0; i < classList.size(); i++) {
			if (classList.get(i).getName().equals(input.getName())) {
				found = true;
				classList.remove(classList.get(i));
				return found;
			}
		}
		return found;
	}

	// Boolean that returns true if Classes array contains input.
	public boolean checkClass(String elementChecked) {
		if (this.classList == null || this.classList.size() == 0)
			return false;
		for (int i = 0; i < classList.size(); i++) {
			if (classList.get(i).getName().equals(elementChecked))
				return true;
		}
		return false;
	}

	// Overloaded checkClass that accepts an input Classes object, for future
	// development.
	public boolean checkClass(Classes elementChecked) {
		if (this.classList == null || this.classList.size() == 0)
			return false;
		for (int i = 0; i < classList.size(); i++) {
			if (classList.get(i).equals(elementChecked))
				return true;
		}
		return false;
	}

	// Returns matching Classes.
	public Classes getClass(String elementChecked) {
		if (this.classList == null || this.classList.size() == 0)
			return null;
		for (int i = 0; i < classList.size(); i++) {
			if (classList.get(i).getName().equals(elementChecked)) {
				return classList.get(i);
			}
		}
		return null;
	}

	// Returns object as Json-formatted String.
	public String printToJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	// Copies Instance object.
	public Instance copy(Instance input) {
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

	// Loads Json into this Instance
	public Instance loadJson(String fileAddress) throws FileNotFoundException, IOException {
		Gson gson = new Gson();
		try (BufferedReader br = new BufferedReader(new FileReader(fileAddress))) {
			Instance newInstance = gson.fromJson(br, Instance.class);
			return newInstance;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	

}
