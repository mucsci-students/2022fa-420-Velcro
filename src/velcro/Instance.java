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
	
	Instance(Instance input) {
		this.classList = input.classList;
		this.attributeList = input.attributeList;
		this.relationshipList = input.relationshipList;
	}
	
	// Adds an input class to the Classes array.
	public void addClass (String elementAdded) {
		 if (this.classList != null) {
			Classes[] output = Arrays.copyOf(this.classList, this.classList.length+1);
		  	output[this.classList.length] = new Classes(elementAdded);
		 	this.classList = output;
		 } else {
			 Classes[] output = new Classes[1];
			 output[0] = new Classes(elementAdded);
			 this.classList = output;
		 }
	}
	
	// Removes an input class from the Classes array.
	public void removeClass (String elementRemoved) {
		if (this.classList == null)
			return;
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
		if (this.classList == null)
			return;
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
		if (this.classList == null)
			return false;
		for (int i = 0; i<classList.length; i++) {
			if (classList[i].getName().equals(elementChecked))
				return true;
		}
		return false;
	}
	
	// Overloaded checkClass that accepts an input Classes object, for future development.
	public boolean checkClass (Classes elementChecked) {
		if (this.classList == null)
			return false;
		for (int i = 0; i<classList.length; i++) {
			if (classList[i].equals(elementChecked))
				return true;
		}
		return false;
	}
	
	// Returns number of Classes objects matching input.
	public int countClass (String elementChecked) {
		if (this.classList == null)
			return 0;
		int count = 0;
		for (int i = 0; i<classList.length; i++) {
			if (classList[i].getName().equals(elementChecked))
				count++;
		}
		return count;
	}
	
	// Adds an input Attribute to the AttributesList array.
	public void addAttribute (String elementAdded) {
		 if (this.attributeList != null) {
			Attributes[] output = Arrays.copyOf(this.attributeList, this.attributeList.length+1);
		  	output[this.attributeList.length] = new Attributes(elementAdded);
		 	this.attributeList = output;
		 } else {
			 Attributes[] output = new Attributes[1];
			 output[0] = new Attributes(elementAdded);
			 this.attributeList = output;
		 }
	}
	
	// Removes an input Attribute from the AttributesList array.
	public void removeAttribute (String elementRemoved) {
		if (this.attributeList == null)
			return;
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
		if (this.attributeList == null)
			return;
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
		if (this.attributeList == null)
			return 0;
		for (int i = 0; i<attributeList.length; i++) {
			if (attributeList[i].getName().equals(elementChecked))
				return true;
		}
		return false;
	}
	
	// Returns count of Attributes in AttributesList that matches input.
	public int countAttribute (String elementChecked) {
		if (this.attributeList == null)
			return 0;
		int count = 0;
		for (int i = 0; i<attributeList.length; i++) {
			if (attributeList[i].getName().equals(elementChecked))
				count++;
		}
		return count;
	}
	
	// Adds a relationship to RelationshipsList array.
	public void addRelationship (String source, String destination) {
		 if (this.relationshipList != null) {
			Relationships[] output = Arrays.copyOf(this.relationshipList, this.relationshipList.length+1);
		  	output[this.relationshipList.length] = new Relationships(source, destination);
		 	this.relationshipList = output;
		 } else {
			 Relationships[] output = new Relationships[1];
			 output[0] = new Relationships(source, destination);
			 this.relationshipList = output;
		 }
	}

	// Removes the first relationship matching given input source and destination.
	public void removeRelationship (String sourceRemoved, String destinationRemoved) {
		if (this.relationshipList == null)
			return;
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
		if (this.relationshipList == null)
			return;
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
		if (this.relationshipList == null)
			return false;
		for (int i = 0; i<relationshipList.length; i++) {
			if (relationshipList[i].getSource().equals(sourceChecked) && relationshipList[i].getDestination().equals(destinationChecked))
				return true;
		}
		return false;
	}
	
	// Returns count of relationships matching input.
	public int countRelationship (String sourceChecked, String destinationChecked) {
		int count = 0;
		for (int i = 0; i<relationshipList.length; i++) {
			if (relationshipList[i].getSource().equals(sourceChecked) && relationshipList[i].getDestination().equals(destinationChecked))
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
