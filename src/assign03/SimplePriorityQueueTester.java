package assign03;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests all the SimplePriorityQueue's methods and the various outcomes of what could
 * go wrong within the methods and any exceptions.
 *
 * @author Philippe Gonzalez and Conner Francis
 */
public class SimplePriorityQueueTester {
    SimplePriorityQueue<Integer> test;
    @BeforeEach
    void SetUp(){
        test = new SimplePriorityQueue<Integer>();
        test.insert(5);
        test.insert(1);
        test.insert(8);
        test.insert(4);
        test.insert(2);
        test.insert(14);
        test.insert(100);
        test.insert(1);
        test.insert(3);

    }

    @Test
    public void test1(){
        System.out.println(test.toString());
    }


    @Test
    public void testFindMaxOfPriorityQue(){
        int temp = test.findMax();
        assertEquals(100,temp);

    }

    @Test
    public void testDeleteMaxOfPriorityQue(){
        test.deleteMax();
        int temp = test.findMax();
        assertEquals(14, temp);

    }

    @Test
    public void testInsertIntoPriorityQue(){
        test.insert(0);
        assertTrue(test.contains(0));

    }

    @Test
    public void testInsertAllIntoPriorityQue(){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int i = 0;i<100;i++) {
            temp.add(i);
        }

        test.clear();
        test.insertAll(temp);
        for(int i = 0;i<100;i++) {
            assertTrue(temp.contains(i));
        }

    }

    @Test
    public void testContainsInPriorityQue(){
        assertTrue(test.contains(14));
    }

    @Test
    public void testSizeOfPriorityQue(){
    int size = test.size();
    assertEquals(size,9);
    }

    @Test
    public void testIfPriorityQueIsEmpty(){
        test.clear();
        assertTrue(test.isEmpty());
        SimplePriorityQueue<Integer> temp = new SimplePriorityQueue<Integer>();
        assertTrue(temp.isEmpty());
    }

    @Test
    public void testClearPriorityQue(){
        test.clear();
        assertTrue(test.isEmpty());
    }

}
