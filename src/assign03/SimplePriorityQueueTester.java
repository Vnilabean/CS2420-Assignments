package assign03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests all the SimplePriorityQueue's methods and the various outcomes of what could
 * go wrong within the methods and any exceptions.
 *
 * @author Philippe Gonzalez and Conner Francis
 */
public class SimplePriorityQueueTester {
    SimplePriorityQueue<Integer> testInteger;
    SimplePriorityQueue<String> testString;
    SimplePriorityQueue<TestClassEmployee> testClass;
    @BeforeEach
    void SetUp(){
        // Integer testing queue
        testInteger = new SimplePriorityQueue<Integer>();
        testInteger.insert(5);
        testInteger.insert(1);
        testInteger.insert(8);
        testInteger.insert(4);
        testInteger.insert(2);
        testInteger.insert(14);
        testInteger.insert(100);
        testInteger.insert(1);
        testInteger.insert(3);
        //String testing queue
        testString = new SimplePriorityQueue<String>();
        testString.insert("Joel");
        testString.insert("DirtyTwoToes");
        testString.insert("Eli");
        testString.insert("Angela");
        testString.insert("Bott");
        testString.insert("Cena");
        testString.insert("Zeb");
        testString.insert("Waltuh");
        // TestClass testing queue // comparator sorts by the employee badge id number
        testClass = new SimplePriorityQueue<TestClassEmployee>(new TestClassEmployee.sortById());
        testClass.insert(new TestClassEmployee(randomName(),randomName(),12345));
        testClass.insert(new TestClassEmployee(randomName(),randomName(),12225));
        testClass.insert(new TestClassEmployee(randomName(),randomName(),41263));
        testClass.insert(new TestClassEmployee(randomName(),randomName(),89302));
        testClass.insert(new TestClassEmployee(randomName(),randomName(),33185));
        testClass.insert(new TestClassEmployee(randomName(),randomName(),49843));


    }




    @Test
    public void testFindMaxOfPriorityQue(){
        int temp = test.findMax();
        assertEquals(100,temp);

    }

    @Test
    public void testDeleteMaxInteger(){
        testInteger.deleteMax();
        int temp = testInteger.findMax();
        assertEquals(14, temp);

    }

    @Test
    public void testInsertInteger(){
        testInteger.insert(0);
        assertTrue(testInteger.contains(0));

    }


    @Test
    public void testInsertAllIntoPriorityQue(){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int i = 0;i<100;i++) {
            temp.add(i);
        }

        testInteger.clear();
        testInteger.insertAll(temp);
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
    public void testEmptyInteger(){
        testInteger.clear();
        assertTrue(testInteger.isEmpty());
        SimplePriorityQueue<Integer> temp = new SimplePriorityQueue<Integer>();
        assertTrue(temp.isEmpty());
    }

    @Test
    public void testClearInteger(){
        testInteger.clear();
        assertTrue(testInteger.isEmpty());
    }

}
