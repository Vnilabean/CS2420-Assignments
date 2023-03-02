package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals(testInt.get(0), 5);
        testInt.insertFirst(2);
        assertEquals(testInt.get(0), 2);
        testInt.insertFirst(600);
        assertEquals(testInt.get(0), 600);
        testInt.insertFirst(0);
        assertEquals(testInt.get(0),0);

    }

    @Test
    void insertInt() {
        testInt.insert(0, 1);
        assertEquals(testInt.get(0), 1);
        testInt.insertFirst(5);
        testInt.insertFirst(2);
        testInt.insertFirst(50);
        testInt.insertFirst(42);
        testInt.insert(3,1);
        assertEquals(testInt.get(3),1);

    }

    @Test
    void getFirst() {
        testInt.insert(0, 1);
        assertEquals(testInt.getFirst(), 1);
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
        assertEquals(testInt.get(0), 40);
        testInt.insert(1, 20);
        assertEquals(testInt.get(1), 20);
        assertEquals(testInt.get(0), 40);
    }

    @Test
    void deleteFirst() {
        // with insertFirst
        testInt.insertFirst(20);
        testInt.insertFirst(10);
        assertEquals(testInt.getFirst(), 10);
        testInt.deleteFirst();
        assertEquals(testInt.getFirst(), 20);
        // with insert
        testInt.insert(0, 5);
        assertEquals(testInt.getFirst(), 5);
        testInt.deleteFirst();
        assertEquals(testInt.getFirst(), 20);
    }

    @Test
    void delete() {
        testInt.insertFirst(5);
        testInt.insertFirst(7);
        testInt.insertFirst(1);
        testInt.delete(0);
        assertEquals(testInt.getFirst(), 7);
        testInt.delete(1);
        assertEquals(testInt.getFirst(), 7);
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
        assertEquals(testInt.indexOf(2), 1);
        assertEquals(testInt.indexOf(70), 0);
        assertEquals(testInt.indexOf(5), 2);
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
        assertEquals(testInt.size(), 9);
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
    void removeMiddle() {
        testInt.insertFirst(3);
        testInt.insertFirst(2);
        testInt.insertFirst(1);
        Iterator i = testInt.iterator();
        i.next();
        i.next();
        i.remove();
        System.out.println(Arrays.toString(testInt.toArray()));
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
        assertEquals(i.next(), 70);
        assertEquals(i.next(), 2);
        assertEquals(i.next(), 5);
        try {
            i.next();
        } catch (NoSuchElementException e) {
            // passes if reached
        }

    }

    @Test
    void iteratorHasNext() {
        testInt.insertFirst(5);
        testInt.insertFirst(2);
        testInt.insertFirst(70);
        Iterator i = testInt.iterator();
        assertEquals(i.next(),70);
        assertTrue(i.hasNext());

    }

    @Test
    void removeNotCallableTwice() {
        testInt.insertFirst(5);
        testInt.insertFirst(2);
        testInt.insertFirst(70);
        Iterator i = testInt.iterator();
       i.next();
        i.remove();
        try {
            i.remove();
        } catch (IllegalStateException e) {
            // passes if reached
        }
    }

    @Test
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
        assertEquals(testInt.get(0), 2);
        i.next();
        i.remove();
        assertEquals(testInt.get(0), 5);
    }

    // String tests -------------------------------------------------------------------
    @Test
    void insertStrFirstInt() {
        testString.insertFirst("hello");
        assertEquals(testString.get(0), "hello");
        testString.insertFirst("test");
        assertEquals(testString.get(0), "test");
        testString.insertFirst("hi");
        assertEquals(testString.get(0), "hi");
    }

    @Test
    void insertIntStr() {
        testString.insertFirst("hello");
        assertEquals(testString.get(0), "hello");
        testString.insert(0, "test");
        assertEquals(testString.get(0), "test");
        testString.insert(1, "hi");
        assertEquals(testString.get(1), "hi");
        testString.insert(2, "A");
        assertEquals(testString.get(2), "A");
    }

    @Test
    void getFirstStr() {
        testString.insertFirst("hello");
        assertEquals(testString.getFirst(), "hello");
        testString.insert(0, "test");
        assertEquals(testString.getFirst(), "test");
    }

    @Test
    void getStr() {
        testString.insertFirst("a");
        testString.insertFirst("b");
        testString.insertFirst("c");
        assertEquals(testString.get(0), "c");
        testString.insert(1, "d");
        assertEquals(testString.get(1), "d");
        assertEquals(testString.get(0), "c");
    }

    @Test
    void deleteFirstStr() {
        // with insertFirst
        testString.insertFirst("a");
        testString.insertFirst("b");
        assertEquals(testString.getFirst(), "b");
        testString.deleteFirst();
        assertEquals(testString.getFirst(), "a");
        // with insert
        testString.insert(0, "f");
        assertEquals(testString.getFirst(), "f");
        testString.deleteFirst();
        assertEquals(testString.getFirst(), "a");
    }

    @Test
    void deleteStr() {
        testString.insertFirst("a");
        testString.insertFirst("b");
        testString.insertFirst("c");
        testString.delete(0);
        assertEquals(testString.getFirst(), "b");
        testString.delete(1);
        assertEquals(testString.getFirst(), "b");
    }

    @Test
    void indexOfStr() {
        testString.insertFirst("a");
        testString.insertFirst("b");
        testString.insertFirst("c");
        assertEquals(testString.indexOf("a"), 2);
        assertEquals(testString.indexOf("b"), 1);
        assertEquals(testString.indexOf("c"), 0);
    }

    @Test
    void sizeStr() {
        testString.insertFirst("a");
        testString.insertFirst("b");
        testString.insertFirst("c");
        testString.insertFirst("d");
        testString.insertFirst("e");
        testString.insertFirst("f");
        testString.insertFirst("g");
        testString.insertFirst("h");
        testString.insertFirst("i");
        assertEquals(testString.size(), 9);
    }

    @Test
    void clearStr() {
        testString.insertFirst("J");
        testString.insertFirst("F");
        testString.insertFirst("k");
        assertFalse(testString.isEmpty());
        testString.clear();
        assertTrue(testString.isEmpty());
        try {
            testString.getFirst();
        } catch (NoSuchElementException e) {
            // passes if reached
        }
    }

    @Test
    void toArrayStr() {
        testString.insertFirst("a");
        testString.insertFirst("b");
        testString.insertFirst("c");
        String[] strArr = new String[]{"c", "b", "a"};
        assertArrayEquals(strArr, testString.toArray());

    }

    @Test
    void iteratorNextStr() {
        testString.insertFirst("a");
        testString.insertFirst("b");
        testString.insertFirst("c");
        Iterator i = testString.iterator();
        assertEquals(i.next(), "c");
        assertEquals(i.next(), "b");
        assertEquals(i.next(), "a");
        try {
            i.next();
        } catch (NoSuchElementException e) {
            // passes if reached
        }
    }

    @Test
    void iteratorHasNextStr() {
        testString.insertFirst("Tommy");
        testString.insertFirst("jimmy");
        testString.insertFirst("Sammy");
        Iterator i = testString.iterator();
        assertEquals(i.next(), "Sammy");
        assertTrue(i.hasNext());
    }

    @Test
    void iteratorRemoveStr() {
        testString.insertFirst("a");
        testString.insertFirst("b");
        testString.insertFirst("c");
        Iterator i = testString.iterator();
        try {
            i.remove();
        } catch (IllegalStateException e) {
            //passes if reached
        }
        i.next();
        i.remove();
        assertEquals(i.next(), "b");
        i.remove();
        assertEquals(i.next(), "a");
    }
}