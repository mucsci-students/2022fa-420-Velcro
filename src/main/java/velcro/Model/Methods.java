/**
 * Filename: Methods.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Methods class.
 * 
 */



package velcro.Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Methods {
	
	@SerializedName("name")
	@Expose
	String name;
	String parentClass;
	
	@SerializedName("type")
	@Expose
	String type;
	
	@SerializedName("params")
	@Expose
	public List<Fields> paramList = new ArrayList<Fields>();
	
	public static DefaultComboBoxModel<String> paramModel;
	public static DefaultComboBoxModel<String> paramModel1;
	
	// Method's constructor.
	public Methods(String name, String type, List<Fields> params) {
		this.name = name;
		this.type = type;
		this.paramList = params;
		paramModel = new DefaultComboBoxModel<String>();
		paramModel1 = new DefaultComboBoxModel<String>();
	}
	
	// Returns Method's name.
	public String getName() {
		return name;
	}

	// Returns Method's type.
	public String getType() {
		return type;
	}
	
	// Returns Method's parameter list.
	public List<Fields> getParams() {
		return paramList;
	}
	
	// Returns boolean of whether input Methods object equals this Methods object.
	public boolean equals(Methods input) {
		return (input.name.equals(this.name) && input.type.equals(this.type));
	}
	
	// Renames Method.
	public boolean rename(String newName) {
		if (newName.equals(""))
			return false;
		this.name = newName;
		return true;
	}
	
	// Adds an input Method to the MethodList array.
	public void addParam(String elementAdded, String type) {
		paramList.add(new Fields(elementAdded, type));
	}

	// Removes an input Field from the FieldsList array.
	public boolean removeParam(String elementRemoved) {
		boolean found = false;
		for (int i = 0; !found && i < paramList.size(); i++) {
			if (paramList.get(i).getName().equals(elementRemoved)) {
				found = true;
				paramList.remove(paramList.get(i));
				continue;
			}
		}
		return found;
	}
	
	// Clears all parameters
	public void clearParam() {
		paramList = new ArrayList<Fields>();
		return;
	}
	
	// Returns matching Parameter.
	public Fields getParam(String elementChecked) {
		if (this.paramList == null || this.paramList.size() == 0)
			return null;
		for (int i = 0; i < paramList.size(); i++) {
			if (paramList.get(i).getName().equals(elementChecked)) {
				return paramList.get(i);
			}
		}
		return null;
	}
}
