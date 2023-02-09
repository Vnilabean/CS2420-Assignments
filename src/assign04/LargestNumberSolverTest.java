package assign04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a tester class to test LargestNumberSolver and all of its methods
 *
 * @author Philippe Gonzalez and Conner Francis
 * @version February 7, 2023
 */
class LargestNumberSolverTest {
    public Integer[]randArr;
    public Integer[] regArr, regArr2, regArr3, empty, empty2, empty3, smallArr, smallArr2, largeArr, largeArr2, repeatArr, repeatArr2;
    public ArrayList<Integer[]> regArrList, emptyArraysList, emptyArrList, smallArrList, largeArrList, repeatArrList;
    Random ran;
    @BeforeEach
    void setUp() {
        ran = new Random();
        randArr = new Integer[10];
        for (int i = 0;i<10;i++) {
            randArr[i] = ran.nextInt(0,99);
        }
        regArr = new Integer[]{7, 43, 21, 5, 96}; //largest number being 96754321
        regArr2 = new Integer[]{6, 59, 73, 4}; //largest number being 736594
        regArr3 = new Integer[]{99, 34, 82}; //largest number being 998234
        regArrList = new ArrayList<Integer[]>();
        regArrList.add(regArr);
        regArrList.add(regArr2);
        regArrList.add(regArr3);
        //empty List and Arrays-------------------------------------
        empty = new Integer[]{};
        empty2 = new Integer[]{};
        empty3 = new Integer[]{};
        emptyArraysList = new ArrayList<Integer[]>();
        emptyArraysList.add(empty);
        emptyArraysList.add(empty2);
        emptyArraysList.add(empty3);
        emptyArrList = new ArrayList<Integer[]>();
        //small List and Arrays-------------------------------------
        smallArr = new Integer[]{3};
        smallArr2 = new Integer[]{2, 9};
        smallArrList = new ArrayList<Integer[]>();
        smallArrList.add(smallArr);
        smallArrList.add(smallArr2);
        //large List and Arrays--------------------------------------------
        largeArr = new Integer[]{410, 20, 93, 80, 69, 379, 20, 60, 432, 13, 72, 62, 70, 83, 9, 3, 14, 11, 62, 55, 34, 82, 80, 99, 56, 25, 79, 51, 51, 70, 79, 20, 34, 67, 40, 51, 41, 94, 89, 116, 874, 554, 137, 371, 17, 77, 97, 58, 83, 97, 26, 170, 54, 96, 33};
        largeArr2 = new Integer[]{92, 35, 63, 57, 997, 53, 39, 27, 51, 3, 15, 41, 27, 51, 57, 56, 91, 6, 94, 48, 75, 43, 16, 79, 22, 58, 72, 25, 87, 51, 82, 99, 5, 22, 23, 54, 625, 6798, 78, 100, 46, 59, 87, 90, 10, 19, 12};
        largeArrList = new ArrayList<Integer[]>();
        largeArrList.add(largeArr);
        largeArrList.add(largeArr2);
        //repeat number arrays----------------------------------------------
        repeatArr = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};
        repeatArr2 = new Integer[]{2, 2, 2, 2, 2, 2, 2};
        repeatArrList = new ArrayList<Integer[]>();
        repeatArrList.add(repeatArr);
        repeatArrList.add(repeatArr2);


    }
    static class CompareIntegers implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) { return Integer.compare(o1,o2); }
    }

    //Regular Array Tests---------------------------------------------------------------------
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
        assertArrayEquals(test, test2);
    }

    @Test
    void findLargestNumber() {
      BigInteger output = LargestNumberSolver.findLargestNumber(regArr2);
      BigInteger expected = new BigInteger("736594");
      assertEquals(expected, output);
    }

    @Test
    void findLargestInt() {
        assertEquals(96754321, LargestNumberSolver.findLargestInt(regArr));
    }

    @Test
    void findLargestLong() {
        regArr = new Integer[]{99, 17, 6, 8, 29, 34};
        assertEquals(9986342917L, LargestNumberSolver.findLargestLong(regArr));
    }

    @Test
    void sum() {
        BigInteger sumOfLargest = LargestNumberSolver.sum(regArrList);
        BigInteger expected = new BigInteger("98489149");
        assertEquals(expected, sumOfLargest);
    }

    @Test
    void findKthLargest() {
        assertEquals(regArr, LargestNumberSolver.findKthLargest(regArrList, 0));
        assertEquals(regArr3, LargestNumberSolver.findKthLargest(regArrList, 1));
        assertEquals(regArr2, LargestNumberSolver.findKthLargest(regArrList, 2));
    }

    @Test
    void readFile() {
        List<Integer[]> fileListArr = LargestNumberSolver.readFile("C:\\Users\\Owner\\IdeaProjects\\CS2420-Assignments\\src\\assign04\\integers.txt");
        Integer[] arr1 = new Integer[]{410, 21, 93, 80, 69, 379, 20, 60, 432, 13, 72, 62, 70, 83, 9, 3, 14, 11, 62, 55, 34, 83, 80, 99, 56, 25, 79, 51, 51, 70, 79, 20, 34, 67, 40, 51, 41, 94, 89, 116, 874, 554, 137, 371, 17, 77, 97, 58, 83, 97, 26, 17, 54, 96, 33};
        assertEquals(LargestNumberSolver.findLargestNumber(arr1), LargestNumberSolver.findLargestNumber(fileListArr.get(0)));
    }



    // Empty Array Tests-----------------------------------------------------------------
    @Test
    void emptyInsertionSort(){
        LargestNumberSolver.insertionSort(empty, new CompareIntegers());
    }
    @Test
    void emptyFindLargestNumber() {
        BigInteger bigInt = LargestNumberSolver.findLargestNumber(empty);
        BigInteger empty = new BigInteger("0");
        assertEquals(empty, bigInt);
    }

    @Test
    void emptyFindLargestInt() {
        int bigInt = LargestNumberSolver.findLargestInt(empty);
        int empty = 0;
        assertEquals(empty, bigInt);
    }

    @Test
    void emptyFindLargestLong() {
        long bigInt = LargestNumberSolver.findLargestLong(empty);
        long empty = 0;
        assertEquals(empty, bigInt);
    }

    @Test
    void emptySum() {
        BigInteger totalBigInt = LargestNumberSolver.sum(emptyArraysList);
        BigInteger zero = new BigInteger("0");
        assertEquals(zero, totalBigInt);
    }

    @Test
    void illegalFindKthLargest() {
        // ignored empty catch as it will pass if the exception was successfully thrown
        // empty
        try{
            LargestNumberSolver.findKthLargest(emptyArraysList, 0);
        }catch(IllegalArgumentException ignored){
        }
        // k too large
        try{
            LargestNumberSolver.findKthLargest(emptyArraysList, 100000);
        }catch(IllegalArgumentException ignored){
        }
        // k smaller than 0
        try{
            LargestNumberSolver.findKthLargest(emptyArraysList, -100000);
        }catch(IllegalArgumentException e){
            return;
        }
        fail();

    }

    @Test
    void emptyReadFile() {
        List<Integer[]> fileListArr = LargestNumberSolver.readFile("CookieMonster");
        assertEquals(emptyArrList.size(), fileListArr.size());

    }

    // Small Array Tests--------------------------------------------------------------------------
    @Test
    void smallInsertionSort(){
        LargestNumberSolver.insertionSort(smallArr, new CompareIntegers());
        assertEquals(3, smallArr[0]);
        LargestNumberSolver.insertionSort(smallArr2, new CompareIntegers());
        assertEquals(2, smallArr2[0]);
    }

    @Test
    void smallFindLargestNumber() {
        BigInteger output = LargestNumberSolver.findLargestNumber(smallArr2);
        BigInteger expected = new BigInteger("92");
        assertEquals(expected, output);
        BigInteger output1 = LargestNumberSolver.findLargestNumber(smallArr);
        BigInteger expected1 = new BigInteger("3");
        assertEquals(expected1, output1);
    }

    @Test
    void smallFindLargestInt() {
        assertEquals(3, LargestNumberSolver.findLargestInt(smallArr));
        assertEquals(92, LargestNumberSolver.findLargestInt(smallArr2));

    }

    @Test
    void smallFindLargestLong() {
        assertEquals(3L, LargestNumberSolver.findLargestLong(smallArr));
        assertEquals(92L, LargestNumberSolver.findLargestLong(smallArr2));
    }

    @Test
    void smallSum() {
        BigInteger sumOfLargest = LargestNumberSolver.sum(smallArrList);
        BigInteger expected = new BigInteger("95");
        assertEquals(expected, sumOfLargest);
    }

    @Test
    void smallFindKthLargest() {

        assertEquals(smallArr2, LargestNumberSolver.findKthLargest(smallArrList, 0));

        assertEquals(smallArr, LargestNumberSolver.findKthLargest(smallArrList, 1));
    }

    // Large Array Tests---------------------------------------------------------------------------
    @Test
    void largeInsertionSort() {
        LargestNumberSolver.insertionSort(largeArr, new CompareIntegers());
        assertEquals(3, largeArr[0]);
        LargestNumberSolver.insertionSort(largeArr2, new CompareIntegers());
        assertEquals(3, largeArr2[0]);
    }

    @Test
    void largeFindLargestNumber() {
        BigInteger output = LargestNumberSolver.findLargestNumber(largeArr);
        BigInteger expected = new BigInteger("999979796949389874838382808079797772707069676262605856555545451515143241410403793713434333262520202017170141371311611");
        assertEquals(expected, output);
        BigInteger output1 = LargestNumberSolver.findLargestNumber(largeArr2);
        BigInteger expected1 = new BigInteger("999979492919087878279787572679866362559585757565545351515148464341393532727252322221916151210100");
        assertEquals(expected1, output1);
    }

    @Test
    void largeFindLargestInt() {
        try {
            assertEquals(3, LargestNumberSolver.findLargestInt(largeArr));
        } catch (OutOfRangeException ignored) {
           // passes if this catch is reached
        }
        try {
            assertEquals(92, LargestNumberSolver.findLargestInt(largeArr2));
        } catch (OutOfRangeException ignored) {
            // passes if this catch is reached
            return;
        }
        fail();

    }

    @Test
    void findLargestIntThrowsException(){
        try {
            LargestNumberSolver.findLargestInt(intArrLarge);
        } catch (LargestNumberSolver.OutOfRangeException ignored) {
            // passes if this catch is reached
            return;
        }
        fail();
    }

    @Test
    void findLargestLongThrowsException(){
        try {
            LargestNumberSolver.findLargestLong(largeArr);
        } catch (OutOfRangeException ignored) {
            // passes if this catch is reached
            return;
        }
        fail();
    }

    @Test
    void largeSum() {
        BigInteger largeSum = new BigInteger("999979796949389874839382787572716860585349463835285722918105037272708786761918942177775726795247454339392057522521711");
        assertEquals(largeSum, LargestNumberSolver.sum(largeArrList));

    }

    @Test
    void largeFindKthLargest() {
        assertEquals(largeArr, LargestNumberSolver.findKthLargest(largeArrList, 0));
    }


    // Repeating numbers -----------------
    @Test
    void repeatFindLargestNumber() {
        BigInteger output = LargestNumberSolver.findLargestNumber(repeatArr);
        BigInteger expected = new BigInteger("0");
        assertEquals(expected, output);
        BigInteger output1 = LargestNumberSolver.findLargestNumber(repeatArr2);
        BigInteger expected1 = new BigInteger("2222222");
        assertEquals(expected1, output1);
    }

    @Test
    void repeatFindLargestInt() {
        assertEquals(2222222, LargestNumberSolver.findLargestInt(repeatArr2));
        assertEquals(0, LargestNumberSolver.findLargestInt(repeatArr));
    }

    @Test
    void repeatFindLargestLong() {
        assertEquals(2222222L, LargestNumberSolver.findLargestLong(repeatArr2));
        assertEquals(0L, LargestNumberSolver.findLargestLong(repeatArr));
    }




}

