/**
 * Filename: Classes.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Classes class.
 * 
 */

package velcro;

public class Classes {
	String name;
	
	// Classes constructor.
	Classes(String name) {
		this.name = name;
	}
	
	// Returns Classes object name.
	public String getName() {
		return name;
	}
	
	// Returns boolean comparing input Classes object to this object.
	public boolean equals(Classes input) {
		return (input.name.equals(this.name));
	}
}
