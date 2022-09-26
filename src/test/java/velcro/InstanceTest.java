package velcro;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstanceTest {

    @Test
    void addClass() {
        Instance newInstance = new Instance();
        newInstance.addClass("testtext");
        Assertions.assertEquals("testtext", newInstance.classList[0].name);
    }

    @Test
    void removeClass() {
        Instance newInstance = new Instance();
        newInstance.addClass("testtext");
        newInstance.removeClass("testtext");
        Assertions.assertEquals(0, newInstance.classList.length);
    }

    @Test
    void testRemoveClass() {
        Instance newInstance = new Instance();
        newInstance.addClass("anothertesttext");
        Classes testClass3 = new Classes("anothertesttext");
        newInstance.removeClass(testClass3);
        Assertions.assertEquals(0, newInstance.classList.length);
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
    void countClass() {
        Instance newInstance = new Instance();
        newInstance.addClass("testtext");
        Assertions.assertEquals(1, newInstance.countClass("testtext"));
    }

    @Test
    void classListToString() {
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