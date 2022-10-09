package velcro.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldsTest {

    @Test
    void getName() {
        Fields test = new Fields("field1", "type1");
        Assertions.assertEquals("field1", test.getName());
    }

    @Test
    void getType() {
        Fields test = new Fields("field1", "type1");
        Assertions.assertEquals("type1", test.getType());
    }

    @Test
    void testEquals() {
        Fields test = new Fields("field1", "type1");
        Fields test2 = new Fields("field2", "type2");
        Assertions.assertEquals(true, test.equals(test));
        Assertions.assertEquals(false, test.equals(test2));
    }

    @Test
    void rename() {
        Fields test = new Fields("field1", "type1");
        test.rename("test2");
        Assertions.assertEquals("test2", test.getName());
    }
}