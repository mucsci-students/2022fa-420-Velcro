package velcro.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametersTest {

    @Test
    void getName() {
        Parameters test1 = new Parameters("param1", "type1");
        Assertions.assertEquals("param1", test1.getName());
    }

    @Test
    void getType() {
        Parameters test1 = new Parameters("param1", "type1");
        Assertions.assertEquals("type1", test1.getType());
    }

    @Test
    void testEquals() {
        Parameters test1 = new Parameters("param1", "type1");
        Parameters test2 = new Parameters("param2", "type2");
        Assertions.assertEquals(true, test1.equals(test1));
        Assertions.assertEquals(false, test1.equals(test2));
    }

    @Test
    void rename() {
        Parameters test1 = new Parameters("param1", "type1");
        test1.rename("test");
        Assertions.assertEquals("test", test1.getName());
    }
}