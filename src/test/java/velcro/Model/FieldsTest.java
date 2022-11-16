package main.java.velcro.Model;


import org.junit.Test;

import static org.junit.Assert.*;

public class FieldsTest {

    @Test
    public void getName() {

        Fields test = new Fields("name", "type");

        assertEquals("name",test.getName());
    }

    @Test
    public void getType() {
        Fields test = new Fields("name", "type");

        assertEquals("type",test.getType());
    }

    @Test
    public void testEquals() {
        Fields test = new Fields("name", "type");
        Fields test1 = new Fields("name", "type");
        Fields test2 = new Fields("name2", "type2");
        Fields test3 = new Fields("name2", "type1");
        Fields test4 = new Fields("name1", "type1");

        assertEquals(true,test.equals(test));
        assertEquals(true,test.equals(test1));
        assertEquals(false,test1.equals(test2));
        assertEquals(false, test2.equals(test3));
        assertEquals(false, test2.equals(test4));
    }

    @Test
    public void rename() {
        Fields test = new Fields("name", "type");

        assertEquals(false, test.rename(""));
        assertEquals(true,test.rename("newName"));
    }
}