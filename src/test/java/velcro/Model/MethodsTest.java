package velcro.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MethodsTest {

    @Test
    void getName() {
        ArrayList params = new ArrayList<>();
        Parameters test1 = new Parameters("param1", "type1");
        Parameters test2 = new Parameters("param2", "type2");
        params.add(test1);
        params.add(test2);
        Methods test = new Methods("method1", "type1", params);
        Assertions.assertEquals("param1", test1.getName());
    }

    @Test
    void getType() {
        ArrayList params = new ArrayList<Parameters>();
        Parameters test1 = new Parameters("param1", "type1");
        Parameters test2 = new Parameters("param2", "type2");
        params.add(test1);
        params.add(test2);
        Methods test = new Methods("method1", "type1", params);
        Assertions.assertEquals("type1", test1.getType());
    }

    @Test
    void getParams() {
        ArrayList params = new ArrayList<Parameters>();
        Methods test = new Methods("method1", "type1", params);
        Assertions.assertEquals(0, test.getParams().size());
    }

    @Test
    void testEquals() {
        ArrayList params = new ArrayList<Parameters>();
        Methods testParam = new Methods("method1", "type1", params);
        ArrayList params1 = new ArrayList<Parameters>();
        Methods testParam1 = new Methods("method2", "type2", params1);
        Assertions.assertEquals(true, testParam.equals(testParam));
        Assertions.assertEquals(false, testParam.equals(testParam1));
    }

    @Test
    void rename() {
        ArrayList params = new ArrayList<Parameters>();
        Methods test = new Methods("method1", "type1", params);
        Assertions.assertEquals(false, test.rename(""));
        Assertions.assertEquals(true, test.rename("test1"));
    }

    @Test
    void addParam() {
        ArrayList params = new ArrayList<>();
        Methods test = new Methods("method1", "type1", params);
        test.addParam("elem1", "type1");
        Assertions.assertEquals(1, test.paramList.size());
    }

    @Test
    void removeParam() {
        ArrayList params = new ArrayList<>();
        Methods test = new Methods("method1", "type1", params);
        test.addParam("elem1", "type1");
        test.removeParam("elem1");
        Assertions.assertEquals(0, test.paramList.size());
    }

    @Test
    void clearParam() {
    }

    @Test
    void getParam() {
    }
}