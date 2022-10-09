package velcro.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationshipsTest {

    @Test
    void getType() {
        Relationships test = new Relationships("source", "destination", "Aggregation");
        Assertions.assertEquals("Aggregation",test.getType());
    }

    @Test
    void getSource() {
        Relationships test = new Relationships("source", "destination", "Aggregation");
        Assertions.assertEquals("source",test.getSource());
    }

    @Test
    void setSource() {
        Relationships test = new Relationships("source", "destination", "Aggregation");
        Assertions.assertEquals("destination",test.getDestination());
    }

    @Test
    void setDestination() {
        Relationships test = new Relationships("source", "destination", "Aggregation");
        test.setSource("source1");
        Assertions.assertEquals("source1",test.getSource());
    }

    @Test
    void getDestination() {
        Relationships test = new Relationships("source", "destination", "Aggregation");
        test.setDestination("destination1");
        Assertions.assertEquals("destination1",test.getDestination());
    }

    @Test
    void testEquals() {
        Relationships test = new Relationships("source", "destination", "Aggregation");
        Relationships test1 = new Relationships("source1", "destination1", "Aggregation");
        Assertions.assertEquals(false, test.equals(test1));
        Assertions.assertEquals(true, test.equals(test));
    }
}