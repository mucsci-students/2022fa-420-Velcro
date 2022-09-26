package velcro;

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
    }

    @Test
    void rename() {
        Classes test = new Classes("test1");
        test.rename("test1","test2");
        Assertions.assertEquals(true,  test.getName().equals("test2"));
        Assertions.assertEquals(false,  test.getName().equals("test1"));
    }

    @Test
    void addAttribute() {
        Classes newInstance = new Classes("test1");
        newInstance.addAttribute("testtext");
        Assertions.assertEquals( "testtext", newInstance.attributeList[0].name);
    }

    @Test
    void removeAttribute() {
        Classes newInstance = new Classes("test1");
        newInstance.addAttribute("testtext");
        newInstance.removeAttribute("testtext");
        Assertions.assertEquals(0, newInstance.attributeList.length);
    }

    @Test
    void testRemoveAttribute() {
        Classes newInstance = new Classes("test1");
        newInstance.addAttribute("anothertesttext");
        Attributes testAttribute3 = new Attributes("anothertesttext");
        newInstance.removeAttribute(testAttribute3);
        Assertions.assertEquals(0, newInstance.attributeList.length);
    }

    @Test
    void checkAttribute() {
        Classes newInstance = new Classes("test1");
        newInstance.addAttribute("testtext");
        Assertions.assertEquals( false, newInstance.checkAttribute("testtextwrong"));
    }

    @Test
    void getAttribute() {
    }

    @Test
    void countAttribute() {
        Classes newInstance = new Classes("test1");
        newInstance.addAttribute("testtext");
        Assertions.assertEquals(1, newInstance.countAttribute("testtext"));
    }

    @Test
    void addRelationship() {
        Classes class1 = new Classes("test1");
        Classes class2 = new Classes("test1");
        Classes newInstance = new Classes("test1");
        newInstance.addRelationship("testsource", "testdestination");
        Assertions.assertEquals("testsource", newInstance.relationshipList[0].source);
        Assertions.assertEquals("testdestination", newInstance.relationshipList[0].destination);
    }

    @Test
    void removeRelationship() {
        Classes newInstance = new Classes("test1");
        newInstance.addRelationship("testsource", "testdestination");
        newInstance.removeRelationship("testsource", "testdestination");
        Assertions.assertEquals(0, newInstance.relationshipList.length);
    }

    @Test
    void testRemoveRelationship() {
        Classes newInstance = new Classes("test1");
        newInstance.addRelationship("anothertestsource", "anothertestdestination");
        Relationships testRelationship3 = new Relationships("anothertestsource", "anothertestdestination");
        newInstance.removeRelationship(testRelationship3);
        Assertions.assertEquals(0, newInstance.relationshipList.length);
    }

    @Test
    void checkRelationship() {
        Classes newInstance = new Classes("test1");
        newInstance.addRelationship("testsource", "testdestination");
        Assertions.assertEquals(false, newInstance.checkRelationship("testtextwrong", "testdestination"));
    }

    @Test
    void getRelationship() {
    }

    @Test
    void countRelationship() {
        Classes newInstance = new Classes("test1");
        newInstance.addRelationship("testsource", "testdestination");
        Assertions.assertEquals(1, newInstance.countRelationship("testsource", "testdestination"));
    }
}