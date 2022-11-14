package main.java.velcro.Model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ClassesTest {

    @Test
    public void setLocation() {

        Classes test = new Classes("test");
        test.setLocation(0,0);

        assertEquals(0,test.point.x);
        assertEquals(0,test.point.y);
    }

    @Test
    public void getName() {
        Classes test = new Classes("class1");
        assertEquals("class1",test.getName());
    }



    @Test
    public void testEquals() {/*
        ArrayList<Fields> fieldsList = new ArrayList<>();
        ArrayList<Methods> methodsList = new ArrayList<>();
        ArrayList<Relationships> relationshipsList = new ArrayList<>();
        Classes test = new Classes("name",fieldsList,methodsList,relationshipsList);
        Classes test1 = new Classes("name",fieldsList,methodsList,relationshipsList);
        Classes test2 = new Classes("name1",fieldsList,methodsList,relationshipsList);
        Classes test3 = new Classes("name",fieldsList,methodsList,relationshipsList);
        Classes test4 = new Classes("name",fieldsList,methodsList,relationshipsList);
        assertEquals(true, test.equals(test));
        assertEquals(true, test.equals(test1));
        assertEquals(false, test.equals(test2));
        test1.addRelationship("source","destination","type");
        assertEquals(false, test.equals(test1));
        test3.addField("source","destination");
        assertEquals(false, test.equals(test3));
        ArrayList<Parameters> list = new ArrayList<>();
        list.add(new Parameters("name", "type"));
        test3.addMethod("source","destination",list);
        assertEquals(false, test.equals(test3));
        */
    }

    @Test
    public void rename() {
        Classes test = new Classes("test");
        Classes test1 = new Classes("");

        assertEquals(true, test.rename(test.getName(), "new"));
        assertEquals(true, test.rename("test", "new"));
        assertEquals(false, test1.rename(test.getName(), ""));
        assertEquals(false, test1.rename(test1.getName(), "new"));
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

        assertEquals(null, test.getField("test1"));
        test.addField("test1","field1");
        assertEquals(test.fieldList.get(0), test.getField("test1"));
    }

    @Test
    public void getMethod() {
        Classes test = new Classes("test1");

        assertEquals(null, test.getField("test1"));
        test.addField("test1","field1");
        assertEquals(test.fieldList.get(0), test.getField("test1"));
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
        assertEquals(false,test1.checkRelationship("source", "destination1","type1"));
    }

    @Test
    public void getRelationship() {
        Classes test = new Classes("test1");
        Classes test1 = new Classes("test1");
        test1.addRelationship("source", "destination","type");

        assertEquals(null,test.getRelationship("",""));
        assertEquals(test1.relationshipList.get(0),test1.getRelationship("source","destination"));
    }
}