package main.java.velcro.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassesTest {

    @Test
    void setLocation() {
        Classes test = new Classes("test");
        test.setLocation(0,0);

        Assertions.assertEquals(0,test.point.x);
        Assertions.assertEquals(0,test.point.y);
    }

    @Test
    void getName() {
        Classes test = new Classes("test");

        Assertions.assertEquals("test", test.getName());
    }

    @Test
    void testEquals() {
        List<Fields> fieldsList = new ArrayList<>();
        List<Methods> methodsList = new ArrayList<>();
        List<Relationships> relationshipsList = new ArrayList<>();
        Classes test = new Classes("name",fieldsList,methodsList,relationshipsList);
        Classes test1 = new Classes("name",fieldsList,methodsList,relationshipsList);
        Classes test2 = new Classes("name1",fieldsList,methodsList,relationshipsList);
        Classes test3 = new Classes("name",fieldsList,methodsList,relationshipsList);
        Classes test4 = new Classes("name",fieldsList,methodsList,relationshipsList);


        Assertions.assertEquals(true, test.equals(test));
        Assertions.assertEquals(true, test.equals(test1));
        Assertions.assertEquals(false, test.equals(test2));
        test1.addRelationship("source","destination","type");
        Assertions.assertEquals(false, test.equals(test1));
        test3.addField("source","destination");
        Assertions.assertEquals(false, test.equals(test3));
        List<Parameters> list = new ArrayList<>();
        list.add(new Parameters("name", "type"));
        test3.addMethod("source","destination",list);
        Assertions.assertEquals(false, test.equals(test3));
    }

    @Test
    void rename() {
        Classes test = new Classes("test");
        Classes test1 = new Classes("");

        Assertions.assertEquals(true, test.rename(test.getName(), "new"));
        Assertions.assertEquals(true, test.rename("test", "new"));
        Assertions.assertEquals(false, test1.rename(test.getName(), ""));
        Assertions.assertEquals(false, test1.rename(test1.getName(), "new"));
    }

    @Test
    void addField() {
        Classes test = new Classes("test1");

        Assertions.assertEquals(0, test.fieldList.size());
        test.addField("test1", "field1");
        Assertions.assertEquals(1, test.fieldList.size());
    }

    @Test
    void addMethod() {
        Classes test = new Classes("test1");
        List<Parameters> params = null;

        Assertions.assertEquals(0, test.methodList.size());
        test.addMethod("test1", "field1",params);
        Assertions.assertEquals(1, test.methodList.size());
    }

    @Test
    void removeField() {
        Classes test = new Classes("test1");
        test.addField("test1", "field1");

        Assertions.assertEquals(true, test.removeField("test1"));
        Assertions.assertEquals(false, test.removeField("notThere"));
    }

    @Test
    void removeMethod() {
        Classes test = new Classes("test1");
        List<Parameters> params = null;
        test.addMethod("test1", "field1",params);

        Assertions.assertEquals(true, test.removeMethod("test1"));
        Assertions.assertEquals(false, test.removeMethod("notThere"));

    }

    @Test
    void testRemoveField() {
        Classes test = new Classes("test1");
        test.addField("test1", "field1");

        Assertions.assertEquals(false, test.removeField("not there"));
        Assertions.assertEquals(true, test.removeField("test1"));
    }

    @Test
    void getField() {
        Classes test = new Classes("test1");

        Assertions.assertEquals(null, test.getField("test1"));
        test.addField("test1","field1");
        Assertions.assertEquals(test.fieldList.get(0), test.getField("test1"));
    }

    @Test
    void getMethod() {
        Classes test = new Classes("test1");

        Assertions.assertEquals(null, test.getField("test1"));
        test.addField("test1","field1");
        Assertions.assertEquals(test.fieldList.get(0), test.getField("test1"));
    }

    @Test
    void addRelationship() {
        Classes test = new Classes("test1");
        test.addRelationship("source","destination","type");

        Assertions.assertEquals("source",test.relationshipList.get(0).getSource());
    }

    @Test
    void removeRelationship() {
        Classes test = new Classes("test1");
        Classes test1 = new Classes("test1");
        test1.addRelationship("source", "destination","type");

        Assertions.assertEquals(true, test1.removeRelationship("source","destination"));
        Assertions.assertEquals(false, test.removeRelationship("",""));
        Assertions.assertEquals(false, test1.removeRelationship("source1","destination"));
        Assertions.assertEquals(false, test1.removeRelationship("source","destination1"));
        Assertions.assertEquals(false, test1.removeRelationship("source1","destination1"));
    }

    @Test
    void checkRelationship() {
        Classes test = new Classes("test1");
        Classes test1 = new Classes("test1");
        test1.addRelationship("source", "destination","type");

        Assertions.assertEquals(false,test.checkRelationship("","",""));
        Assertions.assertEquals(true,test1.checkRelationship("source", "destination","type"));
        Assertions.assertEquals(false,test1.checkRelationship("source1", "destination","type"));
        Assertions.assertEquals(false,test1.checkRelationship("source", "destination1","type"));
        Assertions.assertEquals(false,test1.checkRelationship("source", "destination1","type1"));
    }

    @Test
    void getRelationship() {
        Classes test = new Classes("test1");
        Classes test1 = new Classes("test1");
        test1.addRelationship("source", "destination","type");

        Assertions.assertEquals(null,test.getRelationship("",""));
        Assertions.assertEquals(test1.relationshipList.get(0),test1.getRelationship("source","destination"));
    }
}
