package main.java.velcro.Model;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ClassesTest {

	@Test
	public void ClassesTest() {
		List<Fields> fieldList = new ArrayList<Fields>();
		Fields fieldTest = new Fields("one", "two");
		fieldList.add(fieldTest);
		List<Methods> methodList = new ArrayList<Methods>();
		Methods methodTest = new Methods("three", "four", null);
		methodList.add(methodTest);
		List<Relationships> relationshipList = new ArrayList<Relationships>();
		Relationships relationshipTest = new Relationships("test2","test3", "testrelation");
		relationshipList.add(relationshipTest);
		Classes test = new Classes("test1", fieldList, methodList, relationshipList);
		assertEquals("test1", test.getName());
		assertEquals(fieldList, test.fieldList);
		assertEquals(methodList, test.methodList);
		assertEquals(relationshipList, test.relationshipList);
		assertEquals(new Point(0,0), test.point);
	}
	
	@Test
	public void testResize() {
		Classes test = new Classes("test");
		boolean check = test.resize(200, 200);
		assertEquals(200, test.width);
		assertEquals(200, test.height);
		assertEquals(true, check);
		assertEquals(false, test.resize(200, 200));
		assertEquals(true, test.resize(300, 200));
		assertEquals(true, test.resize(300, 400));
	}
	
	
    @Test
    public void setLocation() {
        Classes test = new Classes("test");
        test.setLocation(0,0);
        assertEquals(0,test.point.x);
        assertEquals(0,test.point.y);
        test.setLocation(10,10);
        assertEquals(10,test.point.x);
        assertEquals(10,test.point.y);
    }

    @Test
    public void getName() {
        Classes test = new Classes("class1");
        assertEquals("class1",test.getName());
    }



    @Test
    public void equals() {
        Classes test1 = new Classes("class1");
        Classes test2 = new Classes("class2");
        Classes test3 = new Classes("class1");
        assertEquals(true, test1.equals(test1));
        assertEquals(false, test1.equals(test2));
        test1.addField("fieldname", "fieldtype");
        assertEquals(false, test1.equals(test3));
        test3.addField("fieldname", "fieldtype");
        test3.addRelationship("source", "desintation", "type");
        assertEquals(false, test1.equals(test3));
    }

    @Test
    public void rename() {
		List<Relationships> relationshipList = new ArrayList<Relationships>();
		Relationships relationshipTest = new Relationships("test","test3", "testrelation");
		relationshipList.add(relationshipTest);
        Classes test = new Classes("test", null, null, relationshipList);
        Classes test1 = new Classes("");
        assertEquals(true, test.rename(test.getName(), "new"));
        assertEquals(true, test.rename("test", "new"));
        assertEquals(true, test.rename("test3", "new2"));
        assertEquals("new", test.relationshipList.get(0).getSource());
        assertEquals("new2", test.relationshipList.get(0).getDestination());
        assertEquals(false, test1.rename(test.getName(), ""));
        assertEquals(false, test1.rename(test1.getName(), "new"));
        Classes test2 = new Classes("testclass");
        test2.relationshipList = null;
        assertEquals(true, test2.rename("test", "test2"));
        Classes test3 = new Classes("testclass");
        test3.addRelationship("source", "destination", "type");
        test3.removeRelationship("source", "destination");
        assertEquals(true, test3.rename("testclass", "testclass2"));
    }
    
    @Test
    public void removeFieldTest() {
    	List<Fields> fieldsList = new ArrayList<Fields>();
    	Fields newField = new Fields("test1", "test2");
    	Classes newClass = new Classes("test", fieldsList, null, null);
    	assertEquals(0, fieldsList.size());
    	fieldsList.add(newField);
    	assertEquals(1, fieldsList.size());
    	newClass.removeField(newField);
    	assertEquals(0, fieldsList.size());
    }

    @Test
    public void addField() {
        Classes test = new Classes("test1");

        assertEquals(0, test.fieldList.size());
        test.addField("test1", "field1");
        assertEquals(1, test.fieldList.size());
    }

    @Test
    public void addMethod() {
        Classes test = new Classes("test1");
        ArrayList<Parameters> params = null;

        assertEquals(0, test.methodList.size());
        test.addMethod("test1", "field1",params);
        assertEquals(1, test.methodList.size());
    }

    @Test
    public void removeField() {
        Classes test = new Classes("test1");
        test.addField("test1", "field1");

        assertEquals(true, test.removeField("test1"));
        assertEquals(false, test.removeField("notThere"));
    }

    @Test
    public void removeMethod() {
        Classes test = new Classes("test1");
        ArrayList<Parameters> params = null;
        test.addMethod("test1", "field1", params);

        assertEquals(false, test.removeMethod("notThere"));
        assertEquals(true, test.removeMethod("test1"));
        assertEquals(false, test.removeMethod("notThere"));
    }

    @Test
    public void testGetRelationship() {
        Classes test = new Classes("test1");
        assertEquals(null, test.getRelationship("source", "destination"));

        Classes test1 = new Classes("test1");
        test1.addRelationship("source","destination","type");
        assertEquals(test1.relationshipList.get(0), test1.getRelationship("source", "destination"));


    }

    @Test
    public void testRemoveField() {
        Classes test = new Classes("test1");
        test.addField("test1", "field1");

        assertEquals(false, test.removeField("not there"));
        assertEquals(true, test.removeField("test1"));
    }

    @Test
    public void getField() {
        Classes test = new Classes("test1");

        assertEquals(null, test.getField(null));
        assertEquals(null, test.getField("test1"));
        test.addField("test1","field1");
        assertEquals(test.fieldList.get(0), test.getField("test1"));
        assertEquals(null, test.getField("test2"));
        
        Classes test2 = new Classes("test3");
        assertEquals(null, test2.getField("fail"));
        test2.fieldList = null;
        assertEquals(null, test2.getField("fail"));
    }

    @Test
    public void getMethod() {
        Classes test = new Classes("test1");
        assertEquals(null, test.getMethod(null));
        assertEquals(null, test.getMethod("test1"));
        test.addMethod("test1","field1", null);
        assertEquals(test.methodList.get(0), test.getMethod("test1"));
        assertEquals(null, test.getMethod("failtest"));

        Classes test2 = new Classes("test3");
		assertEquals(null, test2.getMethod("fail"));
		test2.methodList = null;
    	assertEquals(null, test2.getMethod("fail"));
    }

    @Test
    public void addRelationship() {
        Classes test = new Classes("test1");
        test.addRelationship("source","destination","type");

        assertEquals("source",test.relationshipList.get(0).getSource());
    }

    @Test
    public void removeRelationship() {
        Classes test = new Classes("test1");
        Classes test1 = new Classes("test1");
        test1.addRelationship("source", "destination","type");

        assertEquals(true, test1.removeRelationship("source","destination"));
        assertEquals(false, test.removeRelationship("",""));
        assertEquals(false, test1.removeRelationship("source1","destination"));
        assertEquals(false, test1.removeRelationship("source","destination1"));
        assertEquals(false, test1.removeRelationship("source1","destination1"));

        test.relationshipList = null;
        assertEquals(false, test.removeRelationship("a","a"));
        

        Classes test3 = new Classes("test3");
        test3.addRelationship("source1", "destination1", "type1");
        test3.addRelationship("source2", "destination2", "type2");
        test3.addRelationship("source3", "destination3", "type3");
        assertEquals(false, test3.removeRelationship("source2", "destination3"));
        assertEquals(false, test3.removeRelationship("source3", "destination2"));
        assertEquals(true, test3.removeRelationship("source2", "destination2"));
        
    }

    @Test
    public void checkRelationship() {
        Classes test = new Classes("test1");
        Classes test1 = new Classes("test1");
        test1.addRelationship("source", "destination","type");

        assertEquals(false,test.checkRelationship("","",""));
        assertEquals(true,test1.checkRelationship("source", "destination","type"));
        assertEquals(false,test1.checkRelationship("source1", "destination","type"));
        assertEquals(false,test1.checkRelationship("source", "destination1","type"));
        assertEquals(false,test1.checkRelationship("source", "destination","type1"));
        

        Classes test2 = new Classes("test3");
		assertEquals(false, test2.checkRelationship("fail", "fail", "fail"));
		test2.relationshipList = null;
		assertEquals(false, test2.checkRelationship("fail", "fail", "fail"));
    }

    @Test
    public void getRelationship() {
        Classes test = new Classes("test1");
        Classes test1 = new Classes("test1");
        test1.addRelationship("source", "destination","type");

        assertEquals(null,test.getRelationship("",""));
        assertEquals(test1.relationshipList.get(0),test1.getRelationship("source","destination"));
        assertEquals(null,test1.getRelationship("notsource", "notdestination"));
        assertEquals(null,test1.getRelationship("source", "notdestination"));
        

        Classes test2 = new Classes("test3");
		assertEquals(null, test2.getRelationship("fail", "fail"));
		test2.relationshipList = null;
		assertEquals(null, test2.getRelationship("fail", "fail"));
    }
}
