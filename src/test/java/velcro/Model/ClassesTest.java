package velcro.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassesTest {

    @Test
    void getName() {
        Classes test = new Classes("test1");
        Assertions.assertEquals("test1", test.getName());
    }

    @Test
    void testEquals() {
        Classes test = new Classes("test1");
        Classes test1 = new Classes("test2");
        Assertions.assertEquals(false, test.equals(test1));
        Assertions.assertEquals(true, test.equals(test));
    }

    @Test
    void rename() {
        Classes test = new Classes("test1");
        test.rename("test1","test2");
        Assertions.assertEquals(true,  test.getName().equals("test2"));
        Assertions.assertEquals(false,  test.getName().equals("test1"));
    }

    @Test
    void addField() {
    }

    @Test
    void addMethod() {
    }

    @Test
    void removeField() {
    }

    @Test
    void removeMethod() {
    }

    @Test
    void testRemoveField() {
    }

    @Test
    void getField() {
    }

    @Test
    void getMethod() {
    }

    @Test
    void addRelationship() {
        Classes class1 = new Classes("test1");
        Classes class2 = new Classes("test1");
        Classes newInstance = new Classes("test1");
        newInstance.addRelationship("testsource", "testdestination", "Aggregation");
        Assertions.assertEquals("testsource", newInstance.relationshipList.get(0).source);
        Assertions.assertEquals("testdestination", newInstance.relationshipList.get(0).destination);
    }

    @Test
    void removeRelationship() {
        Classes newInstance = new Classes("test1");
        newInstance.addRelationship("testsource", "testdestination", "Aggregation");
        newInstance.removeRelationship("testsource", "testdestination", "Aggregation");
        Assertions.assertEquals(0, newInstance.relationshipList.size());
    }

    @Test
    void checkRelationship() {
        Classes newInstance = new Classes("test1");
        newInstance.addRelationship("testsource", "testdestination", "Aggregation");
        Assertions.assertEquals(false, newInstance.checkRelationship("testtextwrong", "testdestination", "Aggregation"));
        Assertions.assertEquals(true, newInstance.checkRelationship("testsource", "testdestination", "Aggregation"));
    }

    @Test
    void getRelationship() {

    }
}