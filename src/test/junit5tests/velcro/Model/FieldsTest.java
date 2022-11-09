package main.java.velcro.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldsTest {

    @Test
    void getName() {
        Fields test = new Fields("name", "type");

        Assertions.assertEquals("name",test.getName());
    }

    @Test
    void getType() {
        Fields test = new Fields("name", "type");

        Assertions.assertEquals("type",test.getType());
    }

    @Test
    void testEquals() {
        Fields test = new Fields("name", "type");
        Fields test1 = new Fields("name", "type");
        Fields test2 = new Fields("name2", "type2");

        Assertions.assertEquals(true,test.equals(test));
        Assertions.assertEquals(true,test.equals(test1));
        Assertions.assertEquals(false,test1.equals(test2));
    }

    @Test
    void rename() {
        Fields test = new Fields("name", "type");

        Assertions.assertEquals(false, test.rename(""));
        Assertions.assertEquals(true,test.rename("newName"));
    }
}
