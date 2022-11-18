package main.java.velcro.Model;


import org.junit.Test;

import main.java.velcro.Model.IteratorList.Iterator;
import main.java.velcro.View.GUIFrame;

import static org.junit.Assert.*;


public class IteratorListTest {

    @Test
    public void add() {
        IteratorList list1 = new IteratorList();
        Instance instance = new Instance();
        ZEllipse test = new ZEllipse(0.1F, 0.0F, 0.0F, 0.0F, "name", instance);
        list1.add(test);
        assertEquals(1, list1.size());
    }

    @Test
    public void remove() {
        IteratorList list1 = new IteratorList();
        Instance instance = new Instance();
        ZEllipse test = new ZEllipse(0.1F, 0.0F, 0.0F, 0.0F, "name", instance);
        list1.add(test);
        list1.remove(test);
        assertEquals(0, list1.size());
    }

    @Test
    public void testRemove() {
        IteratorList list1 = new IteratorList();
        Instance instance = new Instance();
        ZEllipse test = new ZEllipse(0.1F, 0.0F, 0.0F, 0.0F, "name", instance);
        list1.add(test);
        list1.remove("name1");
        assertEquals(1, list1.size());
        list1.remove("name");
        assertEquals(0, list1.size());
    }

    @Test
    public void getIterator() {
        IteratorList list = new IteratorList();
        Instance instance = new Instance();
        ZEllipse value = new ZEllipse(0F,0F,0F,0F,"name",instance);
        assertEquals(null, list.getIterator().currentValue());
    }
    
    @Test
    public void iteratorTest() {
        IteratorList list = new IteratorList();
        Iterator test = list.getIterator();
        test.first();
        test.next();
        assertEquals(true, test.isDone());
    }

    @Test
    public void size() {
        IteratorList list1 = new IteratorList();
        assertEquals(0, list1.size());
        Instance instance = new Instance();
        ZEllipse test = new ZEllipse(0.1F, 0.0F, 0.0F, 0.0F, "name", instance);
        list1.add(test);
        assertEquals(1, list1.size());

    }

    @Test
    public void clear() {
        IteratorList list1 = new IteratorList();
        assertEquals(0, list1.size());
        Instance instance = new Instance();
        ZEllipse test = new ZEllipse(0.1F, 0.0F, 0.0F, 0.0F, "name", instance);
        list1.add(test);
        assertEquals(1, list1.size());
        list1.clear();
        assertEquals(0, list1.size());
    }
}