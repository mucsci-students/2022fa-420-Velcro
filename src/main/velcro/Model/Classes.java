/**
 * Filename: Classes.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Classes class.
 * 
 */

package main.java.velcro.Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Classes {
	
	@SerializedName("name")
	@Expose
	String name;
	
	@SerializedName("fields")
	@Expose
	public List<Fields> fieldList = new ArrayList<Fields>();
	
	@SerializedName("methods")
	@Expose
	public List<Methods> methodList = new ArrayList<Methods>();
	
	@SerializedName("relationships")
	@Expose
	public List<Relationships> relationshipList = new ArrayList<Relationships>();

	public static DefaultComboBoxModel<String> fieldModel;
	public static DefaultComboBoxModel<String> fieldModel_1;
	public static DefaultComboBoxModel<String> methodModel;
	public static DefaultComboBoxModel<String> methodModel_1;
	
	// Classes constructor.
	Classes(String name) {
		this.name = name;
		fieldModel = new DefaultComboBoxModel<String>();
		fieldModel_1 = new DefaultComboBoxModel<String>();
		methodModel = new DefaultComboBoxModel<String>();
		methodModel_1 = new DefaultComboBoxModel<String>();
	}
	
	// Overloaded classes constructor.
	Classes(String name, List<Fields> fieldList, List<Methods> methodList, List<Relationships> relationshipsList) {
		this.name = name;
		this.fieldList = fieldList;
		this.relationshipList = relationshipsList;
		this.methodList = methodList;
		fieldModel = new DefaultComboBoxModel<String>();
		fieldModel_1 = new DefaultComboBoxModel<String>();
		methodModel = new DefaultComboBoxModel<String>();
		methodModel_1 = new DefaultComboBoxModel<String>();
	}
	
	// Returns Classes object name.
	public String getName() {
		return name;
	}
	
	// Returns boolean comparing input Classes object to this object.
	public boolean equals(Classes input) {
		return (input.name.equals(this.name) && input.relationshipList.equals(this.relationshipList) && input.fieldList.equals(this.fieldList));
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
	
	
	// Adds an input Field to the FieldsList array.
	public void addField(String elementAdded, String typeAdded) {
		fieldList.add(new Fields(elementAdded, typeAdded));
	}
	
	// Adds an input Method to the MethodList array.
	public void addMethod(String elementAdded, String type, List<Fields> params) {
		methodList.add(new Methods(elementAdded, type, params));
	}

	// Removes an input Field from the FieldsList array.
	public boolean removeField(String elementRemoved) {
		boolean found = false;
		for (int i = 0; !found && i < fieldList.size(); i++) {
			if (fieldList.get(i).getName().equals(elementRemoved)) {
				found = true;
				fieldList.remove(fieldList.get(i));
				continue;
			}
		}
		return found;
	}
	
	// Removes an input Method from the MethodsField array.
	public boolean removeMethod(String elementRemoved) {
		boolean found = false;
		for (int i = 0; !found && i < methodList.size(); i++) {
			if (methodList.get(i).getName().equals(elementRemoved)) {
				found = true;
				methodList.remove(methodList.get(i));
				continue;
			}
		}
		return found;
	}

	// Overloaded removeFields, that accepts an Fields object as input.
	public void removeField(Fields input) {
		fieldList.remove(input);
		return;
	}

	// Returns matching Field.
	public Fields getField(String elementChecked) {
		if (fieldList == null || fieldList.size() == 0)
			return null;
		for (int i = 0; i < fieldList.size(); i++) {
			if (fieldList.get(i).getName().equals(elementChecked))
				return fieldList.get(i);
		}
		return null;
	}

	// Returns matching Field.
	public Methods getMethod(String elementChecked) {
		if (methodList == null || methodList.size() == 0)
			return null;
		for (int i = 0; i < methodList.size(); i++) {
			if (methodList.get(i).getName().equals(elementChecked))
				return methodList.get(i);
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
