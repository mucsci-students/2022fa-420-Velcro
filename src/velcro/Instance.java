/**
 * Filename: Instance.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Instance class, represents a set of data containing Attributes, Classes, and Relationships lists.
 * 
 */

package velcro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;

public class Instance {

	// Required attributes.
	Classes[] classList;

	// Constructor.
	Instance() {
	}

	// Overloaded constructor; for use with loading option or other import
	// strategies.
	Instance(Instance input) {
		if (input.classList == null || input.classList.length == 0)
			return;
		this.classList = input.classList;
		for (int i = 0; i < input.classList.length; i++) {
			if (input.classList[i].attributeList != null && input.classList[i].attributeList.length != 0) {
				for (int j = 0; j < input.classList[i].attributeList.length; j++) {
					this.classList[i].addAttribute(input.classList[i].attributeList[j].getName());
				}
			}
			if (input.classList[i].relationshipList != null && input.classList[i].relationshipList.length != 0) {
				for (int j = 0; j < input.classList[i].relationshipList.length; j++) {
					this.classList[i].addRelationship(input.classList[i].relationshipList[j].getSource(),input.classList[i].relationshipList[j].getDestination() );
				}
			}
		}
	}

	// Adds an input class to the Classes array.
	public void addClass(String elementAdded) {
		if (this.classList != null) {
			Classes[] output = Arrays.copyOf(this.classList, this.classList.length + 1);
			output[this.classList.length] = new Classes(elementAdded);
			this.classList = output;
		} else {
			Classes[] output = new Classes[1];
			output[0] = new Classes(elementAdded);
			this.classList = output;
		}
	}

	// Removes an input class from the Classes array.
	public boolean removeClass(String elementRemoved) {
		if (this.classList == null || this.classList.length == 0)
			return false;
		Classes[] output = new Classes[classList.length - 1];
		boolean found = false;
		for (int i = 0, j = 0; !found && i < classList.length; i++) {
			if (classList[i].getName().equals(elementRemoved)) {
				found = true;
				continue;
			}
			output[j] = classList[i];
			j++;
		}
		if (found) {
			this.classList = output;
			return true;
		} else
			return false;
	}

	// Overloaded removeClass, to accept Classes as input for future development.
	public boolean removeClass(Classes input) {
		if (this.classList == null || this.classList.length == 0)
			return false;
		Classes[] output = new Classes[classList.length - 1];
		boolean found = false;
		for (int i = 0, j = 0; !found && i < classList.length; i++) {
			if (classList[i].equals(input)) {
				found = true;
				continue;
			}
			output[j] = classList[i];
			j++;
		}
		if (found) {
			this.classList = output;
			return true;
		} else
			return false;
	}

	// Boolean that returns true if Classes array contains input.
	public boolean checkClass(String elementChecked) {
		if (this.classList == null || this.classList.length == 0)
			return false;
		for (int i = 0; i < classList.length; i++) {
			if (classList[i].getName().equals(elementChecked))
				return true;
		}
		return false;
	}

	// Overloaded checkClass that accepts an input Classes object, for future
	// development.
	public boolean checkClass(Classes elementChecked) {
		if (this.classList == null || this.classList.length == 0)
			return false;
		for (int i = 0; i < classList.length; i++) {
			if (classList[i].equals(elementChecked))
				return true;
		}
		return false;
	}

	// Returns matching Classes.
	public Classes getClass(String elementChecked) {
		if (this.classList == null || this.classList.length == 0)
			return null;
		for (int i = 0; i < classList.length; i++) {
			if (classList[i].getName().equals(elementChecked))
				return classList[i];
		}
		return null;
	}

	// Returns number of Classes objects matching input.
	public int countClass(String elementChecked) {
		if (this.classList == null || this.classList.length == 0)
			return 0;
		int count = 0;
		for (int i = 0; i < classList.length; i++) {
			if (classList[i].getName().equals(elementChecked))
				count++;
		}
		return count;
	}

	// Returns String of list of classes.
	public String classListToString() {
		String output = "";
		for (int i = 0; i < classList.length; i++) {
			output += classList[i].getName();
			output += "\n";
		}
		return output;
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
			this.copy(newInstance);
			return this;
		} catch (FileNotFoundException e) {
			return null;
		}
	}
}
