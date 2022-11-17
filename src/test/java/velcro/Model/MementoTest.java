package main.java.velcro.Model;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


import main.java.velcro.Model.Memento.NullInstance;

public class MementoTest {

	@Test
	public void addTest() {
		Memento test = new Memento();
		assertEquals(0, test.count());
		Instance testInstance = new Instance();
		testInstance.addClass("class1");
		testInstance.addClass("class2");
		testInstance.setHighlight(testInstance.getClass("class1"));
		testInstance.getClass("class1").addField("field1", "fieldtype1");
		testInstance.getClass("class1").addRelationship("class1", "class2", "type");
		Parameters newParam = new Parameters("param1", "paramtype");
		List<Parameters> paramList = new ArrayList<Parameters>();
		paramList.add(newParam);
		testInstance.getClass("class1").addMethod("method1", "methodtype1", paramList);
		test.add(testInstance);
		assertEquals(1, test.count());
		test.undo(testInstance);
		test.redo();
		assertEquals(1, test.count());
	}
	
	@Test
	public void undoTest() {
		Memento test = new Memento();
		Instance testInstance = new Instance();
		Instance testInstance1 = new Instance();
		assertEquals(null, test.undo(testInstance));
		testInstance.addClass("class1");
		testInstance.addClass("class2");
		testInstance.setHighlight(testInstance.getClass("class1"));
		testInstance.getClass("class1").addField("field1", "fieldtype1");
		testInstance.getClass("class1").addRelationship("class1", "class2", "type");
		Parameters newParam = new Parameters("param1", "paramtype");
		List<Parameters> paramList = new ArrayList<Parameters>();
		paramList.add(newParam);
		testInstance.getClass("class1").addMethod("method1", "methodtype1", paramList);
		test.add(testInstance);
		test.add(testInstance1);
		assertEquals(testInstance1, test.undo(testInstance));
		assertEquals(2, test.count());
		test.addRedo(testInstance1);
	}
	
	@Test
	public void redoTest1() {
		Memento test = new Memento();
		test.redo();
		Instance testInstance = new Instance();
		Instance testInstance1 = new Instance();
		assertEquals(null, test.undo(testInstance));
		test.add(testInstance);
		test.undo(testInstance1);
		test.redo();
		Instance testInstance2 = test.copyInstance(testInstance1);
		assertNotNull(testInstance2);
	}
	
	@Test
	public void redoTest2() {
		Memento test = new Memento();
		Instance testInstance = new Instance();
		Instance testInstance1 = new Instance();
		testInstance.addClass("class1");
		testInstance.addClass("class2");
		testInstance.setHighlight(testInstance.getClass("class1"));
		testInstance.getClass("class1").addField("field1", "fieldtype1");
		testInstance.getClass("class1").addRelationship("class1", "class2", "type");
		Parameters newParam = new Parameters("param1", "paramtype");
		List<Parameters> paramList = new ArrayList<Parameters>();
		paramList.add(newParam);
		testInstance.getClass("class1").addMethod("method1", "methodtype1", paramList);
		test.add(testInstance);
		test.add(testInstance1);
		test.undo(testInstance);
		assertEquals(testInstance, test.redo());
		//assertNotNull(test.NullInstance.getInstance());
	}
	
}
