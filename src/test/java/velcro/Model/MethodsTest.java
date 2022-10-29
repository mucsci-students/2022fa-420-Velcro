package main.java.velcro.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import velcro.Model.*;

import static org.junit.jupiter.api.Assertions.*;

class MethodsTest {

    @Test
    void getName() {
        Methods test = new Methods("test","type",null);

        Assertions.assertEquals("test", test.getName());
    }

    @Test
    void getType() {
        Methods test = new Methods("test","type",null);

        Assertions.assertEquals("type", test.getType());
    }

    @Test
    void getParams() {
        Methods test = new Methods("test","type",null);
        Parameters test_p = new Parameters("test", "type");
        List<Parameters> list = new ArrayList<>();
        list.add(test_p);
        Methods test1 = new Methods("test1","type1",list);

        Assertions.assertEquals(null, test.getParams());
        Assertions.assertEquals(list, test1.getParams());
    }

    @Test
    void testEquals() {
        Methods test = new Methods("test","type",null);
        Parameters test_p = new Parameters("test", "type");
        List<Parameters> list = new ArrayList<>();
        list.add(test_p);
        Methods test1 = new Methods("test","type",list);
        Methods test2 = new Methods("test2","type2",null);

        Assertions.assertEquals(true, test.equals(test));
        Assertions.assertEquals(true, test.equals(test1));
        Assertions.assertEquals(false, test.equals(test2));
    }

    @Test
    void rename() {
        Methods test = new Methods("name", "type",null);

        Assertions.assertEquals(false, test.rename(""));
        Assertions.assertEquals(true,test.rename("newName"));
    }

    @Test
    void addParam() {
        List<Parameters> list = new ArrayList<>();
        Methods test = new Methods("test","type",list);
        test.addParam("testName","testType");

        Assertions.assertEquals("testName",test.getParams().get(0).getName());
        Assertions.assertEquals("testType",test.getParams().get(0).getType());
    }

    @Test
    void removeParam() {
        List<Parameters> list = new ArrayList<>();
        Methods test = new Methods("test","type",list);
        test.addParam("testName","testType");

        Assertions.assertEquals(true,test.removeParam("testName"));
        Assertions.assertEquals(false,test.removeParam("Name"));
    }

    @Test
    void clearParam() {
        List<Parameters> list = new ArrayList<>();
        Methods test = new Methods("test","type",list);
        test.addParam("testName","testType");

        Assertions.assertEquals(1,test.getParams().size());
        test.clearParam();
        Assertions.assertEquals(0,test.getParams().size());
    }

    @Test
    void getParam() {
        List<Parameters> list = new ArrayList<>();
        Methods test = new Methods("test","type",list);
        Methods test1 = new Methods("test","type",null);
        List<Parameters> list1 = new ArrayList<>();
        Methods test2 = new Methods("test","type",list1);
        test2.addParam("testName","testType");

        Assertions.assertEquals(null, test.getParam("testCheck"));
        Assertions.assertEquals(null, test1.getParam("testCheck"));
        Assertions.assertEquals(null, test2.getParam("testCheck"));
        Assertions.assertEquals(test2.getParams().get(0), test2.getParam("testName"));
    }
}
