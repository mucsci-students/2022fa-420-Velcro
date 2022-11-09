package velcro.Model;

import org.junit.Test;

<<<<<<< Updated upstream
import static org.junit.Assert.*;

=======
import java.util.ArrayList;

import static org.junit.Assert.*;

>>>>>>> Stashed changes
public class MethodsTest {

    @Test
    public void getName() {
<<<<<<< Updated upstream
=======
        Methods test = new Methods("test","type",null);

        assertEquals("test", test.getName());
>>>>>>> Stashed changes
    }

    @Test
    public void getType() {
<<<<<<< Updated upstream
=======
        Methods test = new Methods("test","type",null);

        assertEquals("type", test.getType());
>>>>>>> Stashed changes
    }

    @Test
    public void getParams() {
<<<<<<< Updated upstream
=======
        Methods test = new Methods("test","type",null);
        Parameters test_p = new Parameters("test", "type");
        ArrayList<Parameters> list = new ArrayList<>();
        list.add(test_p);
        Methods test1 = new Methods("test1","type1",list);

        assertEquals(null, test.getParams());
        assertEquals(list, test1.getParams());
>>>>>>> Stashed changes
    }

    @Test
    public void testEquals() {
<<<<<<< Updated upstream
=======
        Methods test = new Methods("test","type",null);
        Parameters test_p = new Parameters("test", "type");
        ArrayList<Parameters> list = new ArrayList<>();
        list.add(test_p);
        Methods test1 = new Methods("test","type",list);
        Methods test2 = new Methods("test2","type2",null);

        assertEquals(true, test.equals(test));
        assertEquals(true, test.equals(test1));
        assertEquals(false, test.equals(test2));
>>>>>>> Stashed changes
    }

    @Test
    public void rename() {
<<<<<<< Updated upstream
=======
        Methods test = new Methods("name", "type",null);

        assertEquals(false, test.rename(""));
        assertEquals(true,test.rename("newName"));
>>>>>>> Stashed changes
    }

    @Test
    public void addParam() {
<<<<<<< Updated upstream
=======
        ArrayList<Parameters> list = new ArrayList<>();
        Methods test = new Methods("test","type",list);
        test.addParam("testName","testType");

        assertEquals("testName",test.getParams().get(0).getName());
        assertEquals("testType",test.getParams().get(0).getType());
>>>>>>> Stashed changes
    }

    @Test
    public void removeParam() {
<<<<<<< Updated upstream
=======
        ArrayList<Parameters> list = new ArrayList<>();
        Methods test = new Methods("test","type",list);
        test.addParam("testName","testType");

        assertEquals(true,test.removeParam("testName"));
        assertEquals(false,test.removeParam("Name"));
>>>>>>> Stashed changes
    }

    @Test
    public void clearParam() {
<<<<<<< Updated upstream
=======
        ArrayList<Parameters> list = new ArrayList<>();
        Methods test = new Methods("test","type",list);
        test.addParam("testName","testType");

        assertEquals(1,test.getParams().size());
        test.clearParam();
        assertEquals(0,test.getParams().size());
>>>>>>> Stashed changes
    }

    @Test
    public void getParam() {
<<<<<<< Updated upstream
=======
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
>>>>>>> Stashed changes
    }
}