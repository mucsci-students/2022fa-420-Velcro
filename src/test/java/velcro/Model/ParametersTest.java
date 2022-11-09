package velcro.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParametersTest {

    @Test
    public void getName() {
<<<<<<< Updated upstream
=======
        Parameters test = new Parameters("test", "type");
        assertEquals("test", test.getName());
>>>>>>> Stashed changes
    }

    @Test
    public void getType() {
<<<<<<< Updated upstream
=======
        Parameters test = new Parameters("test", "type");
        assertEquals("type", test.getType());
>>>>>>> Stashed changes
    }

    @Test
    public void testEquals() {
<<<<<<< Updated upstream
=======
        /*
        Parameters test = new Parameters("name", "type");
        Parameters test1 = new Parameters("name", "type");
        Parameters test2 = new Parameters("name2", "type2");

        assertEquals(true,test.equals(test));
        assertEquals(true,test.equals(test1));
        assertEquals(false,test1.equals(test2));

         */
>>>>>>> Stashed changes
    }

    @Test
    public void rename() {
<<<<<<< Updated upstream
=======
        Parameters test = new Parameters("name", "type");

        assertEquals(false,test.rename(""));
        assertEquals(true,test.rename("newName"));
>>>>>>> Stashed changes
    }
}