/*
 * Filename: IteratorList.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Implementation of Iteration design pattern, per project requirements.
 * 
 */

package velcro.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class IteratorList {

	private List<ZEllipse> list = new ArrayList<ZEllipse>();
	
	// Iterator class. Iterates through a list of ZEllipse objects, per project requirement.
	public class Iterator {
		private IteratorList iterList;
		private java.util.Iterator iterator;
		private ZEllipse value;
		
		// Constructor.
		public Iterator(IteratorList iteratorList) {
			iterList = iteratorList;
		}
		
		// Sets iterator to first element.
		public void first() {
			iterator = iterList.list.iterator();
			next();
		}
		
		// Iterates iterator through list.
		public void next() {
			try {
				value = (ZEllipse)iterator.next();
			} catch (NoSuchElementException ex) {
				value = null;
			}
		}
		
		// Returns true if list is at end element.
		public boolean isDone() {
			return value == null;
		}
		
		// Returns current list item.
		public ZEllipse currentValue() {
			return value;
		}
	}
	
	// Method for adding additional zellipse to list.
	public void add(ZEllipse input) {
		list.add(input);
	}
	
	// Method for removing zellipse from list.
	public void remove(ZEllipse input) {
		list.remove(input);
	}
	
	// Method for removing zellipse from list by name.
	public void remove(String input) {
		for (int i = 0; i<list.size(); i++) {
			if (list.get(i).className.equals(input)) {
				remove(list.get(i));
				break;
			}
		}
	}
	
	// Inializes and returns new iterator; multiple iterators can be moving through the list at once.
	public Iterator getIterator() {
		return new Iterator(this);
	}
	
	// Returns size of list.
	public int size() {
		return list.size();
	}
	
	// Clears main list.
	public void clear() {
		list.clear();
		return;
	}
	
}
