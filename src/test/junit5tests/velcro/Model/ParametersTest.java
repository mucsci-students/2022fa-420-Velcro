package main.java.velcro.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametersTest {

    @Test
    void getName() {
        Parameters test = new Parameters("test", "type");
        Assertions.assertEquals("test", test.getName());
    }

    @Test
    void getType() {
        Parameters test = new Parameters("test", "type");
        Assertions.assertEquals("type", test.getType());
    }

    @Test
    void testEquals() {
        Parameters test = new Parameters("name", "type");
        Parameters test1 = new Parameters("name", "type");
        Parameters test2 = new Parameters("name2", "type2");

        Assertions.assertEquals(true,test.equals(test));
        Assertions.assertEquals(true,test.equals(test1));
        Assertions.assertEquals(false,test1.equals(test2));
    }

    @Test
    void rename() {
        Parameters test = new Parameters("name", "type");

        Assertions.assertEquals(false,test.rename(""));
        Assertions.assertEquals(true,test.rename("newName"));
    }
}
