package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void get() {
    }

    @Test
    void deleteFirst() {
    }

    @Test
    void delete() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void clear() {
    }

    @Test
    void toArray() {
    }

    @Test
    void iterator() {
    }
}