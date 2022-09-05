/**
 * Filename: Instance.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Instance class, represents a set of data containing Attributes, Classes, and Relationships lists.
 * 
 */

package velcro;

import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;

public class Instance {

	// Required attributes.
	Classes[] classList;
	Attributes[] attributeList;
	Relationships[] relationshipList;	

	// Constructor.
	Instance() {		
	}
	
	// Overloaded constructor; for use with loading option or other import strategies.
	Instance(Classes[] classes, Attributes[] attributes, Relationships[] relationships) {
		classList = classes;
		attributeList = attributes;
		relationshipList = relationships;
	}
	
	// Adds an input class to the Classes array.
	public void addClass (String elementAdded) {
		 Classes[] output = Arrays.copyOf(classList, classList.length+1);
		 output[classList.length] = new Classes(elementAdded);
		 this.classList = output;
	}
	
	// Removes an input class from the Classes array.
	public void removeClass (String elementRemoved) {
		Classes[] output = new Classes[classList.length-1];
		boolean atMostOnce = true;
		for (int i = 0, j = 0; i< classList.length; i++) {
			if (atMostOnce && classList[i].getName().equals(elementRemoved)) {
				atMostOnce = false;
				continue;
			}
			output[j] = classList[i];
			j++;
		}
		this.classList = output;
	}
	
	// Overloaded removeClass, to accept Classes as input for future development.
	public void removeClass (Classes input) {
		Classes[] output = new Classes[classList.length-1];
		boolean atMostOnce = true;
		for (int i = 0, j = 0; i< classList.length; i++) {
			if (atMostOnce && classList[i].equals(input)) {
				atMostOnce = false;
				continue;
			}
			output[j] = classList[i];
			j++;
		}
		this.classList = output;
	}

	// Boolean that returns true if Classes array contains input.
	public boolean checkClass (String elementChecked) {
		for (int i = 0; i<classList.length; i++) {
			if (classList[i].getName().equals(elementChecked))
				return true;
		}
		return false;
	}
	
	// Overloaded checkClass that accepts an input Classes object, for future development.
	public boolean checkClass (Classes elementChecked) {
		for (int i = 0; i<classList.length; i++) {
			if (classList[i].equals(elementChecked))
				return true;
		}
		return false;
	}
	
	// Returns number of Classes objects matching input.
	public int countClass (String elementChecked) {
		int count = 0;
		for (int i = 0; i<classList.length; i++) {
			if (classList[i].getName().equals(elementChecked))
				count++;
		}
		return count;
	}
	
	// Adds an input Attribute to the AttributesList array.
	public void addAttribute (String elementAdded) {
		 Attributes[] output = Arrays.copyOf(attributeList, attributeList.length+1);
		 output[attributeList.length] = new Attributes(elementAdded);
		 this.attributeList = output;
	}
	
	// Removes an input Attribute from the AttributesList array.
	public void removeAttribute (String elementRemoved) {
		Attributes[] output = new Attributes[attributeList.length-1];
		boolean atMostOnce = true;
		for (int i = 0, j = 0; i< attributeList.length; i++) {
			if (attributeList[i].getName().equals(elementRemoved)) {
				atMostOnce = false;
				continue;
			}
			output[j] = attributeList[i];
			j++;
		}
		this.attributeList = output;
	}
	
	// Overloaded removeAttributes, that accepts an Attributes object as input.
	public void removeAttribute (Attributes input) {
		Attributes[] output = new Attributes[attributeList.length-1];
		boolean atMostOnce = true;
		for (int i = 0, j = 0; i< attributeList.length; i++) {
			if (atMostOnce && attributeList[i].equals(input)) {
				atMostOnce = false;
				continue;
			}
			output[j] = attributeList[i];
			j++;
		}
		this.attributeList = output;
	}
	
	// Boolean that returns whether AttributesList contains input.
	public boolean checkAttribute (String elementChecked) {
		for (int i = 0; i<attributeList.length; i++) {
			if (attributeList[i].getName().equals(elementChecked))
				return true;
		}
		return false;
	}
	
	// Returns count of Attributes in AttributesList that matches input.
	public int countAttribute (String elementChecked) {
		int count = 0;
		for (int i = 0; i<attributeList.length; i++) {
			if (attributeList[i].getName().equals(elementChecked))
				count++;
		}
		return count;
	}
	
	// Adds a relationship to RelationshipsList array.
	public void addRelationship (String source, String destination) {
		 Relationships[] output = Arrays.copyOf(relationshipList, relationshipList.length+1);
		 output[relationshipList.length] = new Relationships(source, destination);
		 this.relationshipList = output;
	}

	// Removes the first relationship matching given input source and destination.
	public void removeRelationship (String sourceRemoved, String destinationRemoved) {
		Relationships[] output = new Relationships[relationshipList.length-1];
		boolean atMostOnce = true;
		for (int i = 0, j = 0; i< relationshipList.length; i++) {
			if (atMostOnce && relationshipList[i].getSource().equals(sourceRemoved) && relationshipList[i].getDestination().equals(destinationRemoved)) {
				atMostOnce = false;
				continue;
			}
			output[j] = relationshipList[i];
			j++;
		}
		this.relationshipList = output;
	}
	
	// Overloaded removeRelationship method; accepts Relationships as input, for future development.
	public void removeRelationship (Relationships input) {
		Relationships[] output = new Relationships[relationshipList.length-1];
		boolean atMostOnce = true;
		for (int i = 0, j = 0; i< relationshipList.length; i++) {
			if (atMostOnce && relationshipList[i].equals(input)) {
				atMostOnce = false;
				continue;
			}
			output[j] = relationshipList[i];
			j++;
		}
		this.relationshipList = output;
	}
	
	// Boolean that returns true if the relationshipList contains input.
	public boolean checkRelationship (String sourceChecked, String destinationChecked) {
		for (int i = 0; i<relationshipList.length; i++) {
			if (relationshipList[i].getSource().equals(sourceChecked) && relationshipList[i].getDestination().equals(sourceDestination))
				return true;
		}
		return false;
	}
	
	// Returns count of relationships matching input.
	public int countRelationship (String sourceChecked, String destinationChecked) {
		int count = 0;
		for (int i = 0; i<relationshipList.length; i++) {
			if (relationshipList[i].getSource().equals(sourceChecked) && relationshipList[i].getDestination().equals(sourceDestination))
				count++;
		}
		return count;
	}
	
	// Returns object as Json-formatted String.
	public String printToJson() {
		Gson gson = new Gson(); 
		return gson.toJson(this);
	}
	
}
