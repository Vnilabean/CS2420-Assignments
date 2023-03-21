package assign08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    BinarySearchTree<Integer> intTree;
    @BeforeEach
    void setUp(){
        intTree = new BinarySearchTree<Integer>();
        intTree.add(3);
        intTree.add(2);
        intTree.add(4);
        intTree.add(1);
        intTree.add(6);
        intTree.add(5);
    }
    @Test
    void add() {
        intTree.clear();
        intTree.add(3);
        assertEquals(3,intTree.first());
        assertEquals(1,intTree.size());
        intTree.add(2);
        assertEquals(2,intTree.first());
        intTree.add(4);
        assertEquals(4,intTree.last());
        intTree.add(1);
        intTree.add(5);
    }

    @Test
    void addAll() {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(3);
        temp.add(2);
        temp.add(4);
        temp.add(1);
        intTree.clear();
        intTree.addAll(temp);
        assertEquals(1,intTree.first());
        assertEquals(4,intTree.last());
    }

    @Test
    void clear() {
        assertEquals(6,intTree.size());
        intTree.clear();
        assertEquals(0,intTree.size());
        try {
            intTree.last();
        }catch (NullPointerException E) {
            //passes
        }

    }

    @Test
    void contains() {
        // compare to current node> if not then compare and go left or right depending on where it should be until where it should be is null
        assertTrue(intTree.contains(1));
        assertTrue(intTree.contains(5));
        assertTrue(intTree.contains(2));
    }

    @Test
    void containsAll() {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(1);
        temp.add(5);
        temp.add(2);
        assertTrue(intTree.containsAll(temp));
    }

    @Test
    void first() {
        assertEquals(1,intTree.first());
    }

    @Test
    void isEmpty() {
        intTree.clear();
        assertTrue(intTree.isEmpty());
        BinarySearchTree<String> test = new BinarySearchTree<>();
        assertTrue(test.isEmpty());
    }

    @Test
    void last() {
        assertEquals(6,intTree.last());
    }

    @Test
    void remove() {
        intTree.remove(3);
        assertEquals(1,intTree.first());
        assertEquals(5,intTree.size());
        intTree.remove(1);
        assertEquals(2,intTree.first());
        assertEquals(4,intTree.size());

    }

    @Test
    void removeAll() {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(1);
        temp.add(5);
        temp.add(2);
        intTree.removeAll(temp);

    }

    @Test
    void size() {
        assertEquals(6,intTree.size());
        intTree.add(20);
        assertEquals(7,intTree.size());
    }

    @Test
    void toArrayList() {
        System.out.println(intTree.toArrayList());
    }
}