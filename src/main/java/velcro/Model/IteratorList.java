/**
 * Filename: IteratorList.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Implementation of Iteration design pattern, per project requirements.
 * 
 */

package main.java.velcro.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class IteratorList {

	private List<ZEllipse> list = new ArrayList<ZEllipse>();
	
	public class Iterator {
		private IteratorList iterList;
		private java.util.Iterator iterator;
		private ZEllipse value;
		
		public Iterator(IteratorList iteratorList) {
			iterList = iteratorList;
		}
		
		public void first() {
			iterator = iterList.list.iterator();
			next();
		}
		
		
		public void next() {
			try {
				value = (ZEllipse)iterator.next();
			} catch (NoSuchElementException ex) {
				value = null;
			}
		}
		
		public boolean isDone() {
			return value == null;
		}
		
		public ZEllipse currentValue() {
			return value;
		}
	}
	
	public void add(ZEllipse input) {
		list.add(input);
	}
	
	public Iterator getIterator() {
		return new Iterator(this);
	}
	
	public int size() {
		return list.size();
	}
	
	public void clear() {
		list.clear();
		return;
	}
	
}
