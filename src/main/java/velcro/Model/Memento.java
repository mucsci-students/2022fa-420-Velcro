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

	private Stack<Instance> undoStack;
	private Stack<Instance> redoStack;

	public Memento() {
		undoStack = new Stack<Instance>();
		redoStack = new Stack<Instance>();
	}
	
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
		undoStack.push(fakeInstance);
	}
	
	
	public void addRedo(Instance input) {
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
		

		redoStack.push(fakeInstance);
	}
	
	public Instance undo(Instance thisInstance) {
		if (undoStack == null || undoStack.size() == 0)
			return null;
		Instance newInstance = new Instance();
		newInstance = copyInstance(thisInstance);
		redoStack.push(newInstance);
		return undoStack.pop();
	}
	
	public Instance redo() {
		if (redoStack == null || redoStack.size() == 0)
			return null;
		Instance newInstance = new Instance();
		newInstance = copyInstance(redoStack.peek());
		
		undoStack.push(newInstance);
		return redoStack.pop();
	}
	
	public int count() {
		return undoStack.size();
	}
	
	public void seeAll() {
		for (int i = 0; i<redoStack.size(); i++) {

			redoStack.get(i).showContents();
		}
		
	}
	
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
}
