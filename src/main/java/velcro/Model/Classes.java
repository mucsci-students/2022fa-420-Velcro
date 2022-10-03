/**
 * Filename: Classes.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Classes class.
 * 
 */

package velcro.Model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Classes {
	
	@SerializedName("name")
	@Expose
	String name;
	
	@SerializedName("attributeList")
	@Expose
	public List<Attributes> attributeList = new ArrayList<Attributes>();
	
	@SerializedName("relationships")
	@Expose
	public List<Relationships> relationshipList = new ArrayList<Relationships>();
	
	// Classes constructor.
	Classes(String name) {
		this.name = name;
	}
	
	// Overloaded classes constructor.
	Classes(String name, List<Attributes> attributeList, List<Relationships> relationshipsList) {
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
		if (this.relationshipList != null && relationshipList.size() != 0) {
			for (int i = 0; i<relationshipList.size(); i++) {
				if (this.relationshipList.get(i).getSource().equals(oldName)) {
					this.relationshipList.get(i).setSource(newName);
				}
				if (this.relationshipList.get(i).getDestination().equals(oldName)) {
					this.relationshipList.get(i).setDestination(newName);
				}
			}
			
		}
		return true;
	}
	
	
	// Adds an input Attribute to the AttributesList array.
	public void addAttribute(String elementAdded) {
		attributeList.add(new Attributes(elementAdded));
	}

	// Removes an input Attribute from the AttributesList array.
	public boolean removeAttribute(String elementRemoved) {
		boolean found = false;
		for (int i = 0; !found && i < attributeList.size(); i++) {
			if (attributeList.get(i).getName().equals(elementRemoved)) {
				found = true;
				attributeList.remove(attributeList.get(i));
				continue;
			}
		}
		return found;
	}

	// Overloaded removeAttributes, that accepts an Attributes object as input.
	public void removeAttribute(Attributes input) {
		attributeList.remove(input);
		return;
	}

	// Returns matching Attribute.
	public Attributes getAttribute(String elementChecked) {
		if (attributeList == null || attributeList.size() == 0)
			return null;
		for (int i = 0; i < attributeList.size(); i++) {
			if (attributeList.get(i).getName().equals(elementChecked))
				return attributeList.get(i);
		}
		return null;
	}

	// Adds a relationship to RelationshipsList array.
	public void addRelationship(String source, String destination, String type) {
		Relationships newRel = new Relationships(source, destination, type);
		relationshipList.add(newRel);
	}

	// Removes the first relationship matching given input source and destination.
	public boolean removeRelationship(String sourceRemoved, String destinationRemoved, String typeRemoved) {
		if (this.relationshipList == null || relationshipList.size() == 0)
			return false;
		List<Relationships> output = new ArrayList<Relationships>();
		boolean found = false;
		for (int i = 0; !found && i < relationshipList.size(); i++) {
			if (relationshipList.get(i).getSource().equals(sourceRemoved)
					&& relationshipList.get(i).getDestination().equals(destinationRemoved)
					&& relationshipList.get(i).getType().equals(typeRemoved)) {
				found = true;
				relationshipList.remove(relationshipList.get(i));
				continue;
			}
		}
		return found;
	}


	// Boolean that returns true if the relationshipList contains input.
	public boolean checkRelationship(String sourceChecked, String destinationChecked, String typeChecked) {
		if (this.relationshipList == null || relationshipList.size() == 0)
			return false;
		for (int i = 0; i < relationshipList.size(); i++) {
			if (relationshipList.get(i).getSource().equals(sourceChecked)
					&& relationshipList.get(i).getDestination().equals(destinationChecked)
					&& relationshipList.get(i).getType().equals(typeChecked))
				return true;
		}
		return false;
	}

	// Returns matching Relationship.
	public Relationships getRelationship(String source, String destination, String type) {
		if (this.relationshipList == null || relationshipList.size() == 0)
			return null;
		for (int i = 0; i < relationshipList.size(); i++) {
			if (relationshipList.get(i).getSource().equals(source)
					&& relationshipList.get(i).getDestination().equals(destination) && relationshipList.get(i).getType().equals(type))
				return relationshipList.get(i);
		}
		return null;
	}

}
