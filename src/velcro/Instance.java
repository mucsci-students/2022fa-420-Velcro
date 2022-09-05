package velcro;

import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;

public class Instance {

	String[] classList;
	String[] attributeList;
	String[] relationshipList;	

	Instance() {
	}
	
	Instance(String[] classes, String[] attributes, String[] relationships) {
		classList = classes;
		attributeList = attributes;
		relationshipList = relationships;
	}
	
	public void addClass (String elementAdded) {
		 String[] output = Arrays.copyOf(classList, classList.length+1);
		 output[classList.length] = elementAdded;
		 this.classList = output;
	}
	
	public void addAttribute (String elementAdded) {
		 String[] output = Arrays.copyOf(attributeList, attributeList.length+1);
		 output[attributeList.length] = elementAdded;
		 this.attributeList = output;
	}
	
	public void addRelationship (String elementAdded) {
		 String[] output = Arrays.copyOf(relationshipList, relationshipList.length+1);
		 output[relationshipList.length] = elementAdded;
		 this.relationshipList = output;
	}

	
	public String printToJson() {
		Gson gson = new Gson(); 
		return gson.toJson(this);
	}
	
}
