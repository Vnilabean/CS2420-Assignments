package assign10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BinaryMaxHeapTest {
    BinaryMaxHeap<Integer> int1;
    BinaryMaxHeap<String> String1;

    @BeforeEach
    void setUp() {
        int1 = new BinaryMaxHeap<>();
        for(int i = 0;i<20;i++)
            int1.add(i);
    }

    @Test
    void add() {
        assertEquals(int1.size(), 20);
        int1.add(40);
        assertEquals(int1.peek(), 40);
        assertEquals(int1.size(), 21);
    }

    @Test
    void peek() {
       assertEquals(int1.peek(), 19);
    }

    @Test
    void extractMax() {
        assertEquals(int1.peek(), 19);
        int1.extractMax();
        assertEquals(int1.peek(), 18);
        int1.extractMax();
        assertEquals(int1.peek(), 17);
        int1.extractMax();
        assertEquals(int1.peek(), 16);
        int1.extractMax();
        assertEquals(int1.peek(), 15);
        int1.extractMax();
        assertEquals(int1.peek(), 14);
    }

    @Test
    void size() {
        assertEquals(int1.size(), 20);
        int1.extractMax();
        assertEquals(int1.size(), 19);
        int1.add(3);
        int1.add(21);
        assertEquals(int1.size(), 21);
    }

    @Test
    void isEmpty() {
        assertFalse(int1.isEmpty());
        int1.clear();
        assertTrue(int1.isEmpty());
    }

    @Test
    void clear() {
        int1.clear();
        assertTrue(int1.isEmpty());
        assertEquals(int1.size(), 0);
        int1.add(4);
        assertFalse(int1.isEmpty());
        assertEquals(int1.size(), 1);
    }

    @Test
    void toArray() {
        int[] arr = new int[19];
        int1.toArray();

    }
}