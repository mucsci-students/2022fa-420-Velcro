package velcro;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationshipsTest {

    @Test
    void getSource() {
        Relationships test = new Relationships("source", "destination");
        Assertions.assertEquals("source",test.getSource());
    }

    @Test
    void setSource() {
        Relationships test = new Relationships("source", "destination");
        Assertions.assertEquals("destination",test.getDestination());
    }

    @Test
    void setDestination() {
        Relationships test = new Relationships("source", "destination");
        test.setSource("source1");
        Assertions.assertEquals("source1",test.getSource());
    }

    @Test
    void getDestination() {
        Relationships test = new Relationships("source", "destination");
        test.setDestination("destination1");
        Assertions.assertEquals("destination1",test.getDestination());
    }

    @Test
    void testEquals() {
        Relationships test = new Relationships("source", "destination");
        Relationships test1 = new Relationships("source1", "destination1");
        Assertions.assertEquals(false, test.equals(test1));
    }
}