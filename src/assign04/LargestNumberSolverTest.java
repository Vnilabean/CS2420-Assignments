package assign04;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LargestNumberSolverTest {
Integer[]randArr;
    @BeforeEach
    void setUp() {
        Random ran = new Random();
       randArr = new Integer[10];
        for (int i = 0;i<10;i++) {
            randArr[i] = ran.nextInt(0,99);
        }
    }
    static class CompareIntegers implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
        return Integer.compare(o1,o2);
        }
        }

    @Test
    void insertionSort() {
        // 100-0 to sort
        Integer[] test = new Integer[100];
        for (int i = 99;i >= 0;i--) {
            test[i] = i;
        }
        // 0-100 to compare to
        Integer[] test2 = new Integer[100];
        for (int i = 0;i < 100;i++) {
            test2[i] = i;
        }
        // default comparator for Integers in natural ordering
        CompareIntegers cmp = new CompareIntegers();
        LargestNumberSolver.insertionSort(test, cmp);
        System.out.println(Arrays.toString(test));
        assertArrayEquals(test, test2);
    }

    

    @Test
    void findLargestNumber() {

      BigInteger output = LargestNumberSolver.findLargestNumber(randArr);
        System.out.println(output);
    }

    @Test
    void findLargestInt() {
    }

    @Test
    void findLargestLong() {
    }

    @Test
    void sum() {
    }

    @Test
    void findKthLargest() {
    }

    @Test
    void readFile() {
    }
}