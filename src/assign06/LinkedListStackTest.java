package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListStackTest {
    LinkedListStack<Integer> intStack;
    @BeforeEach
    void setUp() {
        intStack = new LinkedListStack<>();
    }

    @Test
    void clear() {
        intStack.push(1);
        intStack.push(5);
        intStack.push(2);
        assertFalse(intStack.isEmpty());
        intStack.clear();
        assertTrue(intStack.isEmpty());

    }

    @Test
    void isEmpty() {
        assertTrue(intStack.isEmpty());
        intStack.push(1);
        intStack.push(5);
        intStack.push(2);
        assertFalse(intStack.isEmpty());
    }

    @Test
    void peek() {
        intStack.push(1);
        intStack.push(5);
        intStack.push(2);
        assertEquals(intStack.peek(),2);
        // makes sure is not modified
        assertEquals(intStack.peek(),2);
    }

    @Test
    void pop() {
        intStack.push(1);
        intStack.push(5);
        intStack.push(2);
        assertEquals(intStack.pop(),2);
        assertEquals(intStack.peek(),5);
        intStack.pop();
        intStack.pop();
        try {
            intStack.pop();
        } catch (NoSuchElementException e) {
            // passes if reached
        }

    }

    @Test
    void push() {
        intStack.push(1);
        assertEquals(intStack.peek(),1);
        intStack.push(1);
        assertEquals(intStack.peek(),1);
        intStack.push(5);
        assertEquals(intStack.peek(),5);
        intStack.push(2);
        assertEquals(intStack.peek(),2);

    }

    @Test
    void size() {
        for(int i = 0;i<100;i++) {
            intStack.push(i);
        }
        assertEquals(intStack.size(),100);
    }
}