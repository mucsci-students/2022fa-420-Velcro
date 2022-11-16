/**
 * Filename: Memento.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Memento class for implementing undo/redo.
 * 
 */

package main.java.velcro.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Memento {
	public NullInstance NullInstance = new NullInstance();
	private Stack<Instance> undoStack;
	private Stack<Instance> redoStack;

	// Constructor. Initiates undo and redo stack with NullInstance.
	public Memento() {
		undoStack = new Stack<Instance>();
		undoStack.add(NullInstance.getInstance());
		redoStack = new Stack<Instance>();
		redoStack.add(NullInstance.getInstance());
	}
	
	// 	Add method to be called to manually clone an Instance following every meaningful action in the Controller.
	public void add(Instance input) {
		Instance fakeInstance = new Instance();
		for (int i = 0; i<input.classList.size(); i++) {
			fakeInstance.addClass(input.classList.get(i).getName());
			fakeInstance.classList.get(i).setLocation(input.classList.get(i).point.x, input.classList.get(i).point.y);
			for (int j = 0; j<input.classList.get(i).fieldList.size(); j++) {
				fakeInstance.classList.get(i).addField(input.classList.get(i).fieldList.get(j).getName(), input.classList.get(i).fieldList.get(j).getType());
			}
			for (int j = 0; j<input.classList.get(i).relationshipList.size(); j++) {
				fakeInstance.classList.get(i).addRelationship(input.classList.get(i).relationshipList.get(j).getSource(), input.classList.get(i).relationshipList.get(j).getDestination(), input.classList.get(i).relationshipList.get(j).getType());
			}
			for (int j = 0; j<input.classList.get(i).methodList.size(); j++) {
				List<Parameters> newParamList = new ArrayList<Parameters>();
				for (int k = 0; k<input.classList.get(i).methodList.get(j).paramList.size(); k++) {
					newParamList.add(new Parameters(input.classList.get(i).methodList.get(j).paramList.get(k).getName(), input.classList.get(i).methodList.get(j).paramList.get(k).getType()));
				}
				fakeInstance.classList.get(i).addMethod(input.classList.get(i).methodList.get(j).getName(), input.classList.get(i).methodList.get(j).getType(), newParamList);
			}
		}
		fakeInstance.setHighlight(input.highlight);
		redoStack.clear();
		redoStack.push(NullInstance.getInstance());
		undoStack.push(fakeInstance);
	}
	
	// Loading of the latest-stored undo memento.
	public Instance undo(Instance thisInstance) {
		// Checks if the next item is the NullInstance.
		if (undoStack.peek().equals(NullInstance.getInstance()))
			return null;
		Instance newInstance = new Instance();
		newInstance = copyInstance(thisInstance);
		// Pushes instance to redo stack.
		redoStack.push(newInstance);
		return undoStack.pop();
	}
	
	// Loading of the latest-stored redo memento.
	public Instance redo() {
		// Checks if the next item is the NullInstance.
		if (redoStack.peek().equals(NullInstance.getInstance()))
			return null;
		Instance newInstance = new Instance();
		newInstance = copyInstance(redoStack.peek());
		// Pushes instance to undo stack.
		undoStack.push(newInstance);
		return redoStack.pop();
	}
	
	// Returns count of undo stack.
	public int count() {
		return undoStack.size()-1;
	}
	
	// Makes a manual clone of input Instance, since Java passes by reference and the mementos should be unedited by subsequent edits to the Instance.
	public Instance copyInstance(Instance input) {
		Instance fakeInstance = new Instance();
		for (int i = 0; i<input.classList.size(); i++) {
			fakeInstance.addClass(input.classList.get(i).getName());
			fakeInstance.classList.get(i).setLocation(input.classList.get(i).point.x, input.classList.get(i).point.y);
			for (int j = 0; j<input.classList.get(i).fieldList.size(); j++) {
				fakeInstance.classList.get(i).addField(input.classList.get(i).fieldList.get(j).getName(), input.classList.get(i).fieldList.get(j).getType());
			}
			for (int j = 0; j<input.classList.get(i).relationshipList.size(); j++) {
				fakeInstance.classList.get(i).addRelationship(input.classList.get(i).relationshipList.get(j).getSource(), input.classList.get(i).relationshipList.get(j).getDestination(), input.classList.get(i).relationshipList.get(j).getType());
			}
			for (int j = 0; j<input.classList.get(i).methodList.size(); j++) {
				List<Parameters> newParamList = new ArrayList<Parameters>();
				for (int k = 0; k<input.classList.get(i).methodList.get(j).paramList.size(); k++) {
					newParamList.add(new Parameters(input.classList.get(i).methodList.get(j).paramList.get(k).getName(), input.classList.get(i).methodList.get(j).paramList.get(k).getType()));
				}
				fakeInstance.classList.get(i).addMethod(input.classList.get(i).methodList.get(j).getName(), input.classList.get(i).methodList.get(j).getType(), newParamList);
			}
		}
		fakeInstance.setHighlight(input.highlight);
		return fakeInstance;
	}
	
	
	// NullInstance is implemented below as a nested class. It acts as both a Null Object (an Instance
	// with declared, but not impactful, attributes) and as a Singleton (its creation and accessor
	// force the existence of only one NullInstance). In this implementation, it is inserted as an uneditable
	// bookend for the undo and redo stacks to signify the end of the stack, avoiding NullPointer
	// errors or the need to count the undo/redo stacks as preliminary checks.
	static class NullInstance extends Instance {
	    private NullInstance() {}

	    private static class NullInstanceHolder {
	        private static final NullInstance INSTANCE = new NullInstance();
	    }

	    public static NullInstance getInstance() {
	        return NullInstanceHolder.INSTANCE;
	    }
	}
}
