package velcro.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class RelationshipsTest {

    @Test
    public void getType() {
<<<<<<< Updated upstream
=======
        Relationships test = new Relationships("source","destination","type");

        assertEquals("type",test.getType());
>>>>>>> Stashed changes
    }

    @Test
    public void getSource() {
<<<<<<< Updated upstream
=======
        Relationships test = new Relationships("source","destination","type");

        assertEquals("source",test.getSource());
>>>>>>> Stashed changes
    }

    @Test
    public void setSource() {
<<<<<<< Updated upstream
=======
        Relationships test = new Relationships("source","destination","type");
        test.setSource("sourceNew");

        assertEquals("sourceNew",test.getSource());
>>>>>>> Stashed changes
    }

    @Test
    public void setDestination() {
<<<<<<< Updated upstream
=======
        Relationships test = new Relationships("source","destination","type");
        test.setDestination("destinationNew");

        assertEquals("destinationNew",test.getDestination());

>>>>>>> Stashed changes
    }

    @Test
    public void getDestination() {
<<<<<<< Updated upstream
=======
        Relationships test = new Relationships("source","destination","type");

        assertEquals("destination",test.getDestination());
>>>>>>> Stashed changes
    }

    @Test
    public void testEquals() {
<<<<<<< Updated upstream
=======
        Relationships test = new Relationships("source","destination","type");
        Relationships test1 = new Relationships("source","destination","type");
        Relationships test2 = new Relationships("source1","destination1","type");
        Relationships test3 = new Relationships("source","destination2","type");
        Relationships test4 = new Relationships("source1","destination","type");

        assertEquals(true,test.equals(test));
        assertEquals(true,test.equals(test1));
        assertEquals(false,test.equals(test2));
        assertEquals(false,test.equals(test3));
        assertEquals(false,test.equals(test4));

>>>>>>> Stashed changes
    }
}