/**
 * Filename: Relationships.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Relationships class.
 * 
 */

package velcro.Model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships {
	
	@SerializedName("source")
	@Expose
	public String source;
	
	@SerializedName("destination")
	@Expose
	public String destination;

	@SerializedName("type")
	@Expose
	public String type;
	
	// Constructor.
	Relationships(String source, String destination, String type) {
		this.source = source;
		this.destination = destination;
		this.type = type;
	}
	
	// Returns type as String.
	public String getType() {
		return type;
	}
	
	// Returns source as String.
	public String getSource() {
		return source;
	}
	
	// Sets relationship source
	public void setSource(String newName) {
		this.source = newName;
		return;
	}
	
	// Sets relationship destination
	public void setDestination(String newName) {
		this.destination = newName;
		return;
	}
	
	// Returns destination as String.
	public String getDestination() {
		return destination;
	}
	
	// Boolean returns if the compared Relationships match source & destination.
	public boolean equals(Relationships input) {
		return (input.getSource().equals(this.source) && input.getDestination().equals(this.destination));
	}
}
