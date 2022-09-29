/**
 * Filename: Instance.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Instance class, represents a set of data containing Attributes, Classes, and Relationships lists.
 * 
 */

package velcro.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Instance {

	// Required attributes.
	@SerializedName("classList")
	@Expose
	public List<Classes> classList;

	// Constructor.
	public Instance() {
		classList = new ArrayList<Classes>();
	}

	// Overloaded constructor; for use with loading option or other import
	// strategies.
	Instance(Instance input) {
		if (input.classList == null || input.classList.size() == 0)
			return;
		this.classList = input.classList;
		for (int i = 0; i < input.classList.size(); i++) {
			if (input.classList.get(i).attributeList != null && input.classList.get(i).attributeList.size() != 0) {
				for (int j = 0; j < input.classList.get(i).attributeList.size(); j++) {
					this.classList.get(i).addAttribute(input.classList.get(i).attributeList.get(j).getName());
				}
			}
			if (input.classList.get(i).relationshipList != null && input.classList.get(i).relationshipList.size() != 0) {
				for (int j = 0; j < input.classList.get(i).relationshipList.size(); j++) {
					this.classList.get(i).addRelationship(input.classList.get(i).relationshipList.get(j).getSource(),input.classList.get(i).relationshipList.get(j).getDestination() );
				}
			}
		}
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
		for (int i = 0; !found && i < classList.size(); i++) {
			if (classList.get(i).getName().equals(input.getName())) {
				found = true;
				classList.remove(classList.get(i));
				continue;
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
	public Instance copy(Instance source) {
		Instance newInstance = new Instance(source);
		return newInstance;
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
