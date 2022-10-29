package main.java.velcro.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationshipsTest {

    @Test
    void getType() {
        Relationships test = new Relationships("source","destination","type");

        Assertions.assertEquals("type",test.getType());
    }

    @Test
    void getSource() {
        Relationships test = new Relationships("source","destination","type");

        Assertions.assertEquals("source",test.getSource());
    }

    @Test
    void setSource() {
        Relationships test = new Relationships("source","destination","type");
        test.setSource("sourceNew");

        Assertions.assertEquals("sourceNew",test.getSource());
    }

    @Test
    void setDestination() {
        Relationships test = new Relationships("source","destination","type");
        test.setDestination("destinationNew");

        Assertions.assertEquals("destinationNew",test.getDestination());

    }

    @Test
    void getDestination() {
        Relationships test = new Relationships("source","destination","type");

        Assertions.assertEquals("destination",test.getDestination());
    }

    @Test
    void testEquals() {
        Relationships test = new Relationships("source","destination","type");
        Relationships test1 = new Relationships("source","destination","type");
        Relationships test2 = new Relationships("source1","destination1","type");
        Relationships test3 = new Relationships("source","destination2","type");
        Relationships test4 = new Relationships("source1","destination","type");

        Assertions.assertEquals(true,test.equals(test));
        Assertions.assertEquals(true,test.equals(test1));
        Assertions.assertEquals(false,test.equals(test2));
        Assertions.assertEquals(false,test.equals(test3));
        Assertions.assertEquals(false,test.equals(test4));

    }
}
