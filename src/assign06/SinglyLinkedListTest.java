package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {
    public SinglyLinkedList<Integer> testInt;
    SinglyLinkedList<String> testString;
    @BeforeEach
    void setUp() {
        testInt = new SinglyLinkedList<Integer>();
        testString = new SinglyLinkedList<String>();
    }

    // Integer tests -----------------------------------------------
    @Test
    void insertFirstInt() {
        testInt.insertFirst(5);
        assertEquals(testInt.get(0),5);
        testInt.insertFirst(2);
        assertEquals(testInt.get(0),2);
        testInt.insertFirst(600);
        assertEquals(testInt.get(0),600);
        testInt.insertFirst(0);
        assertEquals(testInt.get(0),0);

    }

    @Test
    void insertInt() {
        testInt.insert(0,1);
        assertEquals(testInt.get(0),1);
        testInt.insertFirst(5);
        testInt.insertFirst(2);
        testInt.insertFirst(50);
        testInt.insertFirst(42);
        testInt.insert(3,1);
        assertEquals(testInt.get(3),1);

    }

    @Test
    void getFirst() {
        testInt.insert(0,1);
        assertEquals(testInt.getFirst(),1);
        testInt.insertFirst(42);
        testInt.insert(1,1);
        assertEquals(testInt.getFirst(),42);
        testInt.insert(0,100);
        assertEquals(testInt.getFirst(),100);

    }

    @Test
    void get() {
        testInt.insertFirst(10);
        testInt.insertFirst(20);
        testInt.insertFirst(40);
        assertEquals(testInt.get(0),40);
        testInt.insert(1,20);
        assertEquals(testInt.get(1),20);
        assertEquals(testInt.get(0),40);
    }

    @Test
    void deleteFirst() {
        // with insertFirst
        testInt.insertFirst(20);
        testInt.insertFirst(10);
        assertEquals(testInt.getFirst(),10);
        testInt.deleteFirst();
        assertEquals(testInt.getFirst(),20);
        // with insert
        testInt.insert(0,5);
        assertEquals(testInt.getFirst(),5);
        testInt.deleteFirst();
        assertEquals(testInt.getFirst(),20);
    }

    @Test
    void delete() {
        testInt.insertFirst(5);
        testInt.insertFirst(7);
        testInt.insertFirst(1);
        testInt.delete(0);
        assertEquals(testInt.getFirst(),7);
        testInt.delete(1);
        assertEquals(testInt.getFirst(),7);
    }
    @Test
    void deleteException() {
        testInt.insertFirst(10);
        testInt.insertFirst(15);
        testInt.delete(1);
        try {
            testInt.delete(1);
        } catch (IndexOutOfBoundsException e) {
           // passes if reached
        }
    }

    @Test
    void indexOf() {
        testInt.insertFirst(5);
        testInt.insertFirst(2);
        testInt.insertFirst(70);
        assertEquals(testInt.indexOf(2),1);
        assertEquals(testInt.indexOf(70),0);
        assertEquals(testInt.indexOf(5),2);
    }

    @Test
    void size() {
        testInt.insertFirst(5);
        testInt.insertFirst(2);
        testInt.insertFirst(70);
        testInt.insertFirst(5);
        testInt.insertFirst(2);
        testInt.insertFirst(70);
        testInt.insertFirst(5);
        testInt.insertFirst(2);
        testInt.insertFirst(70);
        assertEquals(testInt.size(),9);
    }

    @Test
    void isEmpty() {
        assertTrue(testInt.isEmpty());
        testInt.insertFirst(1);
        assertFalse(testInt.isEmpty());
    }

    @Test
    void clear() {
        testInt.insertFirst(5);
        testInt.insertFirst(2);
        testInt.insertFirst(70);
        assertFalse(testInt.isEmpty());
        testInt.clear();
        assertTrue(testInt.isEmpty());
        try {
            testInt.getFirst();
        } catch (NoSuchElementException e) {
            // passes if reached
        }

    }

    @Test
    void toArray() {
        testInt.insertFirst(5);
        testInt.insertFirst(2);
        testInt.insertFirst(70);
        System.out.println(Arrays.toString(testInt.toArray()));
    }

    @Test
    void iteratorNext() {
        testInt.insertFirst(5);
        testInt.insertFirst(2);
        testInt.insertFirst(70);
        Iterator i = testInt.iterator();
        SinglyLinkedList.Node t = (SinglyLinkedList.Node) i.next();
        assertEquals(t.getData(),70);
        t = (SinglyLinkedList.Node) i.next();
        assertEquals(t.getData(),2);
        t = (SinglyLinkedList.Node) i.next();
        assertEquals(t.getData(),5);
        try {
            i.next();
        } catch (NoSuchElementException e) {
            // passes if reached
        }

    }
    void iteratorHasNext() {
        testInt.insertFirst(5);
        testInt.insertFirst(2);
        testInt.insertFirst(70);
        Iterator i = testInt.iterator();
        assertEquals(i.next(),70);
        assertTrue(i.hasNext());

    }
    void iteratorRemove() {
        testInt.insertFirst(5);
        testInt.insertFirst(2);
        testInt.insertFirst(70);
        Iterator i = testInt.iterator();
        try {
            i.remove();
        } catch (IllegalArgumentException e) {
            //passes if reached
        }
        i.next();
        i.remove();
        assertEquals(i.next(),2);
        i.remove();
        assertEquals(i.next(),5);

    }
}