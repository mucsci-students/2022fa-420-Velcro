/**
 * Filename: Relationships.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Relationships class.
 * 
 */

package velcro;

public class Relationships {
	String source;
	String destination;
	
	// Constructor.
	Relationships(String source, String destination) {
		this.source = source;
		this.destination = destination;
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
