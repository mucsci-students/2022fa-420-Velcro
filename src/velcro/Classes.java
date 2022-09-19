/**
 * Filename: Classes.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Classes class.
 * 
 */

package velcro;

import java.util.Arrays;

public class Classes {
	String name;
	Attributes[] attributeList;
	Relationships[] relationshipList;
	
	// Classes constructor.
	Classes(String name) {
		this.name = name;
	}
	
	// Overloaded classes constructor.
	Classes(String name, Attributes[] attributeList, Relationships[] relationshipsList) {
		this.name = name;
		this.attributeList = attributeList;
		this.relationshipList = relationshipsList;
	}
	
	// Returns Classes object name.
	public String getName() {
		return name;
	}
	
	// Returns boolean comparing input Classes object to this object.
	public boolean equals(Classes input) {
		return (input.name.equals(this.name) && input.relationshipList.equals(this.relationshipList) && input.attributeList.equals(this.attributeList));
	}
	
	// Renames Class and all of its relationships that have the same source or destination as the changed class name
	public boolean rename(String oldName, String newName) {
		if (newName.equals("") || oldName.equals(""))
			return false;
		if (this.name.equals(oldName)) {
			this.name = newName;
		}
		if (this.relationshipList != null && this.relationshipList.length != 0) {
			for (int i = 0; i<relationshipList.length; i++) {
				if (this.relationshipList[i].getSource().equals(oldName)) {
					this.relationshipList[i].setSource(newName);
				}
				if (this.relationshipList[i].getDestination().equals(oldName)) {
					this.relationshipList[i].setDestination(newName);
				}
			}
			
		}
		return true;
	}
	
	
	// Adds an input Attribute to the AttributesList array.
	public void addAttribute(String elementAdded) {
		if (this.attributeList != null) {
			Attributes[] output = Arrays.copyOf(this.attributeList, this.attributeList.length + 1);
			output[this.attributeList.length] = new Attributes(elementAdded);
			this.attributeList = output;
		} else {
			Attributes[] output = new Attributes[1];
			output[0] = new Attributes(elementAdded);
			this.attributeList = output;
		}
	}

	// Removes an input Attribute from the AttributesList array.
	public boolean removeAttribute(String elementRemoved) {
		if (this.attributeList == null || this.attributeList.length == 0)
			return false;
		Attributes[] output = new Attributes[attributeList.length - 1];
		boolean found = false;
		for (int i = 0, j = 0; !found && i < attributeList.length; i++) {
			if (attributeList[i].getName().equals(elementRemoved)) {
				found = true;
				continue;
			}
			output[j] = attributeList[i];
			j++;
		}
		if (found) {
			this.attributeList = output;
			return true;
		} else
			return false;
	}

	// Overloaded removeAttributes, that accepts an Attributes object as input.
	public void removeAttribute(Attributes input) {
		if (this.attributeList == null || this.attributeList.length == 0)
			return;
		Attributes[] output = new Attributes[attributeList.length - 1];
		boolean found = false;
		for (int i = 0, j = 0; !found && i < attributeList.length; i++) {
			if (attributeList[i].equals(input)) {
				found = true;
				continue;
			}
			output[j] = attributeList[i];
			j++;
		}
		if (found) {
			this.attributeList = output;
			return;
		} else
			return;
	}

	// Boolean that returns whether AttributesList contains input.
	public boolean checkAttribute(String elementChecked) {
		if (this.attributeList == null || this.attributeList.length == 0)
			return false;
		for (int i = 0; i < attributeList.length; i++) {
			if (attributeList[i].getName().equals(elementChecked))
				return true;
		}
		return false;
	}

	// Returns matching Attribute.
	public Attributes getAttribute(String elementChecked) {
		if (this.attributeList == null || this.attributeList.length == 0)
			return null;
		for (int i = 0; i < attributeList.length; i++) {
			if (attributeList[i].getName().equals(elementChecked))
				return attributeList[i];
		}
		return null;
	}

	// Returns count of Attributes in AttributesList that matches input.
	public int countAttribute(String elementChecked) {
		if (this.attributeList == null || this.attributeList.length == 0)
			return 0;
		int count = 0;
		for (int i = 0; i < attributeList.length; i++) {
			if (attributeList[i].getName().equals(elementChecked))
				count++;
		}
		return count;
	}
	
	// Adds a relationship to RelationshipsList array.
	public void addRelationship(String source, String destination) {
		if (this.relationshipList != null) {
			Relationships[] output = Arrays.copyOf(this.relationshipList, this.relationshipList.length + 1);
			output[this.relationshipList.length] = new Relationships(source, destination);
			this.relationshipList = output;
		} else {
			Relationships[] output = new Relationships[1];
			output[0] = new Relationships(source, destination);
			this.relationshipList = output;
		}
	}

	// Removes the first relationship matching given input source and destination.
	public boolean removeRelationship(String sourceRemoved, String destinationRemoved) {
		if (this.relationshipList == null || this.relationshipList.length == 0)
			return false;
		Relationships[] output = new Relationships[relationshipList.length - 1];
		boolean found = false;
		for (int i = 0, j = 0; !found && i < relationshipList.length; i++) {
			if (relationshipList[i].getSource().equals(sourceRemoved)
					&& relationshipList[i].getDestination().equals(destinationRemoved)) {
				found = true;
				continue;
			}
			output[j] = relationshipList[i];
			j++;
		}
		if (found) {
			this.relationshipList = output;
			return true;
		} else
			return false;
	}

	// Overloaded removeRelationship method; accepts Relationships as input, for
	// future development.
	public boolean removeRelationship(Relationships input) {
		if (this.relationshipList == null || this.relationshipList.length == 0)
			return false;
		Relationships[] output = new Relationships[relationshipList.length - 1];
		boolean found = false;
		for (int i = 0, j = 0; !found && i < relationshipList.length; i++) {
			if (relationshipList[i].equals(input)) {
				found = true;
				continue;
			}
			output[j] = relationshipList[i];
			j++;
		}
		if (found) {
			this.relationshipList = output;
			return true;
		} else
			return false;
	}

	// Boolean that returns true if the relationshipList contains input.
	public boolean checkRelationship(String sourceChecked, String destinationChecked) {
		if (this.relationshipList == null || this.relationshipList.length == 0)
			return false;
		for (int i = 0; i < relationshipList.length; i++) {
			if (relationshipList[i].getSource().equals(sourceChecked)
					&& relationshipList[i].getDestination().equals(destinationChecked))
				return true;
		}
		return false;
	}

	// Returns matching Relationship.
	public Relationships getRelationship(String source, String destination) {
		if (this.relationshipList == null || this.relationshipList.length == 0)
			return null;
		for (int i = 0; i < relationshipList.length; i++) {
			if (relationshipList[i].getSource().equals(source)
					&& relationshipList[i].getDestination().equals(destination))
				return relationshipList[i];
		}
		return null;
	}

	// Returns count of relationships matching input.
	public int countRelationship(String sourceChecked, String destinationChecked) {
		int count = 0;
		if (this.relationshipList == null || this.relationshipList.length == 0)
			return 0;
		for (int i = 0; i < relationshipList.length; i++) {
			if (relationshipList[i].getSource().equals(sourceChecked)
					&& relationshipList[i].getDestination().equals(destinationChecked))
				count++;
		}
		return count;
	}

}
