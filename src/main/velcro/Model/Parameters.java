/**
 * Filename: Parameters.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Parameters class.
 * 
 */



package main.java.velcro.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameters {
	
	@SerializedName("name")
	@Expose
	String name;
	String parentClass;
	
	@SerializedName("type")
	@Expose
	String type;
	
	// Fields constructor.
	public Parameters(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	// Returns Fields name.
	public String getName() {
		return name;
	}

	// Returns Fields type.
	public String getType() {
		return type;
	}
	
	// Returns boolean of whether input Fields object equals this Fields object.
	public boolean equals(Fields input) {
		return (input.name.equals(this.name) && input.type.equals(this.type));
	}
	
	// Renames Field.
	public boolean rename(String newName) {
		if (newName.equals(""))
			return false;
		this.name = newName;
		return true;
	}
	

	
}
