package exammakeup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MedianTest {
int[] test;
int[] testRev;
int[]testSmall;
int[]testLargeShuffled;
Random ran = new Random();
    @BeforeEach
    void setUp() {
        test = new int[11];
        for(int i = 0; i<=10;i++) {
            test[i] = i;
        }
        testRev = new int[21];
        int revPos = 20;
        for(int i = 0; i<=20;i++) {
            testRev[i] = revPos;
            revPos--;
        }
        testSmall = new int[1];
        testSmall[0] = 1;
        Integer[] testLargeShuffledInteger = new Integer[101];
        testLargeShuffled = new int[101];
        for(int i = 0; i<=100;i++) {
            testLargeShuffledInteger[i] = i;
        }
        List<Integer> arrAsList = Arrays.asList(testLargeShuffledInteger);
        Collections.shuffle(arrAsList);
        arrAsList.toArray(testLargeShuffledInteger);
        for(int i = 0;i<=100;i++) {
            testLargeShuffled[i] = testLargeShuffledInteger[i];
        }



    }

    @Test
    void medianLargeShuffled() {
        // 0...100 array
        int med  = Median.median(testLargeShuffled);
        assertEquals(50,med);

        // tests that all elements below median are smaller on the array
        for(int i = 0;i<50;i++) {
            assertTrue(testLargeShuffled[i] < med);
        }

        // median is in the middle of the array
        assertEquals(testLargeShuffled[50], med);

        // tests that all elements above median are larger on the array
        for(int i = 51;i<100;i++) {
            assertTrue(testLargeShuffled[i] > med);

        }
    }

    @Test
    void medianNormal(){
        // 0...10 array
        assertEquals(5,Median.median(test));

    }

    @Test
    void medianReverse() {
        // 20...0 array
        assertEquals(10,Median.median(testRev));
    }

    @Test
    void medianSmall() {
        // array items [1]
        assertEquals(1,Median.median(testSmall));
    }



}