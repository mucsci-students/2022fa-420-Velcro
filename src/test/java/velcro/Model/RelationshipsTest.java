package main.java.velcro.Model;


import org.junit.Test;

import static org.junit.Assert.*;

public class RelationshipsTest {

    @Test
    public void getType() {


        Relationships test = new Relationships("source","destination","type");

        assertEquals("type",test.getType());
    }

    @Test
    public void getSource() {
        Relationships test = new Relationships("source","destination","type");

        assertEquals("source",test.getSource());
    }

    @Test
    public void setSource() {
        Relationships test = new Relationships("source","destination","type");
        test.setSource("sourceNew");

        assertEquals("sourceNew",test.getSource());
    }

    @Test
    public void setDestination() {
        Relationships test = new Relationships("source","destination","type");
        test.setDestination("destinationNew");

        assertEquals("destinationNew",test.getDestination());

    }

    @Test
    public void getDestination() {
        Relationships test = new Relationships("source","destination","type");

        assertEquals("destination",test.getDestination());
    }

    @Test
    public void testEquals() {
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

    }
}