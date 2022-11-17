package main.java.velcro.Model;


import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MethodsTest {

    @Test
    public void getName() {
        Methods test = new Methods("test","type",null);

        assertEquals("test", test.getName());

    }

    @Test
    public void getType() {
        Methods test = new Methods("test","type",null);

        assertEquals("type", test.getType());
    }

    @Test
    public void getParams() {
        Methods test = new Methods("test","type",null);
        Parameters test_p = new Parameters("test", "type");
        ArrayList<Parameters> list = new ArrayList<>();
        list.add(test_p);
        Methods test1 = new Methods("test1","type1",list);

        assertEquals(null, test.getParams());
        assertEquals(list, test1.getParams());
    }

    @Test
    public void testEquals() {

        Methods test = new Methods("test","type",null);
        Parameters test_p = new Parameters("test", "type");
        ArrayList<Parameters> list = new ArrayList<>();
        list.add(test_p);
        Methods test1 = new Methods("test","type",list);
        Methods test2 = new Methods("test2","type2",null);
        Methods test3 = new Methods("test2","type1",null);
        Methods test4 = new Methods("test1","type2",null);

        assertEquals(true, test.equals(test));
        assertEquals(true, test.equals(test1));
        assertEquals(false, test.equals(test2));
        assertEquals(false, test3.equals(test2));
        assertEquals(false, test4.equals(test2));
    }

    @Test
    public void rename() {
        Methods test = new Methods("name", "type",null);

        assertEquals(false, test.rename(""));
        assertEquals(true,test.rename("newName"));
    }

    @Test
    public void addParam() {
        ArrayList<Parameters> list = new ArrayList<>();
        Methods test = new Methods("test","type",list);
        test.addParam("testName","testType");

        assertEquals("testName",test.getParams().get(0).getName());
        assertEquals("testType",test.getParams().get(0).getType());
    }

    @Test
    public void removeParam() {

        ArrayList<Parameters> list = new ArrayList<>();
        Methods test = new Methods("test","type",list);
        test.addParam("testName","testType");

        assertEquals(false,test.removeParam("Name"));
        assertEquals(true,test.removeParam("testName"));
        assertEquals(false,test.removeParam("Name"));
    }

    @Test
    public void clearParam() {

        ArrayList<Parameters> list = new ArrayList<>();
        Methods test = new Methods("test","type",list);
        test.addParam("testName","testType");

        assertEquals(1,test.getParams().size());
        test.clearParam();
        assertEquals(0,test.getParams().size());
    }

    @Test
    public void getParam() {

        ArrayList<Parameters> list = new ArrayList<>();
        Methods test = new Methods("test","type",list);
        Methods test1 = new Methods("test","type",null);
        ArrayList<Parameters> list1 = new ArrayList<>();
        Methods test2 = new Methods("test","type",list1);
        test2.addParam("testName","testType");

        assertEquals(null, test.getParam("testCheck"));
        assertEquals(null, test1.getParam("testCheck"));
        assertEquals(null, test2.getParam("testCheck"));
        assertEquals(test2.getParams().get(0), test2.getParam("testName"));
    }
}