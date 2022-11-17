package main.java.velcro.Model;


import org.junit.Test;

import static org.junit.Assert.*;

public class InstanceTest {

    @Test
    public void setInstance() {
        Instance test = new Instance();

    }

    @Test
    public void setHighlight() {
        Instance test = new Instance();
        Classes testClass = new Classes("testClass");
        test.setHighlight(testClass);
        assertEquals(testClass, test.highlight);
    }

    @Test
    public void addClass() {
        Instance test = new Instance();
        test.addClass("testClass");
        assertEquals("testClass", test.classList.get(0).getName());
    }

    @Test
    public void removeClass() {
        Instance test = new Instance();
        test.addClass("testClass");
        test.removeClass("testClass");
        assertEquals(0, test.classList.size());
    }

    @Test
    public void testRemoveClass() {
        Instance test = new Instance();
        test.addClass("testClass");
        Classes testClass = test.getClass("testClass");
        test.removeClass(testClass);
        assertEquals(0, test.classList.size());
    }

    @Test
    public void checkClass1() {
        Instance test = new Instance();
        assertEquals(false, test.checkClass("test"));
        test.addClass("test");
        assertEquals(true, test.checkClass("test"));
        assertEquals(false, test.checkClass("testClass"));
    }

    @Test
    public void testCheckClass() {
        Instance test = new Instance();
        assertEquals(false, test.checkClass("test"));
        test.addClass("test");
        //assertEquals(true, test.checkClass(testClass));
        //assertEquals(false, test.checkClass(testClass1));
    }

    @Test
    public void testGetClass() {
        Instance test = new Instance();
        assertEquals(null, test.getClass("test"));
        test.addClass("test");
        assertEquals("test", test.getClass("test").getName());
        assertEquals(null, test.getClass("test1"));
    }

    @Test
    public void printToJson() {
        Instance test = new Instance();
        //JSONAssert.assertEquals(test.printToJson(), ":[]", true);
    }

    @Test
    public void copy() {
    }

    @Test
    public void loadJson() {
    }

    @Test
    public void showContents() {
    }

    @Test
    public void testShowContents() {
    }
}