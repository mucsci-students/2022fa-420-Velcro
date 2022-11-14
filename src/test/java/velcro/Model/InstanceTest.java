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
        //Instance test = new Instance();
        //Classes testClass = new Classes("testClass");
        //test.setHighlight(testClass);
        //assertEquals(testClass, test.getClass("testClass"));
    }

    @Test
    public void addClass() {
        //Instance test = new Instance();
        //test.addClass("testClass");
        //assertEquals("testClass", test.getClass("testClass"));
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
    public void checkClass() {
    }

    @Test
    public void testCheckClass() {
    }

    @Test
    public void testGetClass() {
    }

    @Test
    public void printToJson() {
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