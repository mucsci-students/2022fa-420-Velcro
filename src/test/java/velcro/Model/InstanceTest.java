package velcro.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstanceTest {

    @Test
    void addClass() {
        Instance newInstance = new Instance();
        newInstance.addClass("testtext");
        Assertions.assertEquals("testtext", newInstance.classList.get(0).name);
    }

    @Test
    void removeClass() {
        Instance newInstance = new Instance();
        newInstance.addClass("testtext");
        newInstance.removeClass("testtext");
        Assertions.assertEquals(0, newInstance.classList.size());
    }

    @Test
    void testRemoveClass() {
        Instance newInstance = new Instance();
        newInstance.addClass("anothertesttext");
        Classes testClass3 = new Classes("anothertesttext");
        newInstance.removeClass(testClass3);
        Assertions.assertEquals(0, newInstance.classList.size());
    }

    @Test
    void checkClass() {
        Instance newInstance = new Instance();
        Assertions.assertEquals(false, newInstance.checkClass("testtextwrong"));
    }

    @Test
    void testCheckClass() {
        Instance newInstance = new Instance();
        Assertions.assertEquals(false, newInstance.checkClass("testtextwrong"));
    }

    @Test
    void testGetClass() {
    }

    @Test
    void printToJson() {
    }

    @Test
    void copy() {
    }

    @Test
    void loadJson() {
    }
}