package main.java.velcro.Model;


import org.junit.Test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstanceTest {

    @Test
    public void setInstance() {
        Instance test = new Instance();
    }
    
    @Test
    public void setHighlight() {
        Instance test = new Instance();
        test.addClass("testClass");
        test.setHighlight(test.getClass("testClass"));
        assertEquals("testClass", test.getClass("testClass").getName());
    }

    @Test
    public void addClass() {
        Instance test = new Instance();
        test.addClass("testClass");
        assertEquals("testClass", test.getClass("testClass").getName());
    }

    @Test
    public void removeClass() {
        Instance test = new Instance();
        assertEquals(false, test.removeClass("test"));
        test.addClass("testClass");
        assertEquals(false, test.removeClass("test"));
        assertEquals(true, test.removeClass("testClass"));
        assertEquals(false, test.removeClass("testClass"));
        assertEquals(0, test.classList.size());
        Instance test2 = new Instance();
        test2.classList = null;
        assertEquals(false, test2.removeClass("test"));
    }

    @Test
    public void testRemoveClass() {
        Instance test = new Instance();
        test.addClass("testClass");
        Classes testClass = test.getClass("testClass");
        test.removeClass(testClass);
        assertEquals(0, test.classList.size());
        
        Instance test2 = new Instance();
        assertEquals(false, test2.removeClass("test"));
        test2.classList = null;
        assertEquals(false, test2.removeClass("test"));
        
        Instance test3 = new Instance();
        test3.addClass("test1");
        test3.addClass("test2");
        test3.addClass("test3");
        assertEquals(true, test3.removeClass(test3.getClass("test2")));
        assertEquals(2, test3.classList.size());
        Instance test4 = new Instance();
        assertEquals(false, test4.removeClass(test3.getClass("test2")));
        test4.classList = null;
        assertEquals(false, test4.removeClass(test3.getClass("test2")));
    }

    @Test
    public void checkClass() {
    	Instance test = new Instance();
    	Classes newClass = new Classes("test3");
    	assertEquals(false, test.checkClass("test"));
    	assertEquals(false, test.checkClass("test3"));
    	assertEquals(false, test.checkClass(newClass));
    	test.classList = null;
    	assertEquals(false, test.checkClass("test"));
    	assertEquals(false, test.checkClass(newClass));
    	
    	Instance test1 = new Instance();
    	test1.addClass("test1");
    	test1.addClass("test2");
    	test1.addClass("test4");
    	Classes newClass1 = new Classes("newClass1");
    	assertEquals(true, test1.checkClass("test4"));
    	assertEquals(false, test1.checkClass("test3"));
    	assertEquals(false, test1.checkClass(newClass1));
    	assertEquals(true, test1.checkClass(test1.getClass("test1")));
    }
    
    @Test
    public void getClassTest() {
    	Instance test = new Instance();
    	assertEquals(null, test.getClass("check"));
    	test.classList = null;
    	assertEquals(null, test.getClass("check"));
    	
    	Instance test2 = new Instance();
    	test2.addClass("testclass");
    	assertEquals(null, test2.getClass("newcheck"));
    	assertEquals("testclass", test2.getClass("testclass").getName());
    }

    @Test
    public void printToJson() {
    	 Instance test = new Instance();
    	 test.addClass("testclass");
    	 assertNotNull(test.printToJson());
    }

    @Test
    public void copy() {
    	Instance test1 = new Instance();
    	test1.addClass("testclass1");
    	test1.addClass("testclass2");
    	test1.setHighlight(test1.getClass("testclass1"));
    	test1.getClass("testclass1").addRelationship("testclass1", "testclass2", "String");
    	test1.getClass("testclass1").addField("testfield", "testfieldtype");
    	Parameters newParam = new Parameters("testparam", "testparamtype");
    	List<Parameters> paramList = new ArrayList<Parameters>();
    	paramList.add(newParam);
    	test1.getClass("testclass1").addMethod("testmethod", "testmethodtype", paramList);
    	Instance test2 = test1.copy(test1);
    	assertEquals("testclass1", test2.getClass("testclass1").getName());
    	assertEquals(test2.getClass("testclass1").relationshipList.size(), 1);
    	assertEquals(test2.getClass("testclass1").fieldList.size(), 1);
    	assertEquals(test2.getClass("testclass1").methodList.size(), 1);
    	assertEquals(test2.getClass("testclass1").getMethod("testmethod").paramList.size(), 1);
    }

    @Test
    public void loadJson() throws FileNotFoundException, IOException {
    	Instance test = new Instance();
    	assertEquals(null, test.loadJson("fakeaddress"));
    }

}