package velcro;

import org.junit.jupiter.api.Assertions;
import velcro.Attributes;

import static org.junit.jupiter.api.Assertions.*;

class AttributesTest {

    @org.junit.jupiter.api.Test
    void getName() {
        Attributes test = new Attributes("test1");
        Assertions.assertEquals("test1", test.getName());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        Attributes test = new Attributes("test1");
        Attributes test1 = new Attributes("test2");
        Assertions.assertEquals(false, test.equals(test1));
    }

    @org.junit.jupiter.api.Test
    void rename() {
        Attributes test = new Attributes("test1");
        test.rename("test2");
        Assertions.assertEquals(true,  test.getName().equals("test2"));
        Assertions.assertEquals(false,  test.getName().equals("test1"));
    }
}