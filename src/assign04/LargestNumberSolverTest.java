package assign04;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LargestNumberSolverTest {
Integer[]randArr;
    Random ran;
    @BeforeEach
    void setUp() {
        ran = new Random();
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
        Integer[] intArr = new Integer[]{7,43,21,5};
        LargestNumberSolver.findLargestInt(intArr);
        System.out.println(Arrays.toString(intArr));
        Integer[] intArrLarge = new Integer[]{7,43,21,5,54,23,2,66};
        try {
            LargestNumberSolver.findLargestInt(intArrLarge);
        } catch (LargestNumberSolver.OutOfRangeException ignored) {
            // passes if this catch is reached
        }


    }

    @Test
    void findLargestLong() {
    }

    @Test
    void sum() {
    }

    @Test
    void findKthLargest() {
        ArrayList<Integer[]> temp = new ArrayList<Integer[]>();
        for (int i = 0; i<10;i++) {
            Integer[] t = new Integer[]{ran.nextInt(0,20),ran.nextInt(0,20)};
            temp.add(t);
        }
        for(Integer[] i : temp) {
            System.out.print(Arrays.toString(i));
        }

        System.out.println();
       System.out.println(Arrays.toString(LargestNumberSolver.findKthLargest(temp, 0)));
        System.out.println(Arrays.toString(LargestNumberSolver.findKthLargest(temp, 1)));
        System.out.println(Arrays.toString(LargestNumberSolver.findKthLargest(temp, temp.size()-1)));

    }

    @Test
    void readFile() {
        LargestNumberSolver.readFile("C:\\Users\\Owner\\IdeaProjects\\CS2420-Assignments\\src\\assign04\\integers.txt");

    }
}