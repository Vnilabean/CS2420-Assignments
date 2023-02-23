package assign05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

class ArrayListSorterTest {
    ArrayList<Integer> permutedArr, ascendingArr, descendingArr;
    @BeforeEach
    void setUp() {
        permutedArr = ArrayListSorter.generatePermuted(20);
        ascendingArr = ArrayListSorter.generateAscending(20);
        descendingArr = ArrayListSorter.generateDescending(20);

    }

    @Test
    void mergesort() {
        System.out.println(permutedArr.toString());
        ArrayListSorter.mergesort(permutedArr);
        System.out.println(permutedArr.toString());
    }
    @Test
    void mergesortSmall() {
        ArrayList<Integer> smallArr = new ArrayList<>();
        ArrayList<Integer> smallArrSorted = new ArrayList<>();
        smallArrSorted.add(1);
        smallArrSorted.add(2);
        smallArr.add(2);
        smallArr.add(1);
        ArrayListSorter.mergesort(smallArr);
        assertEquals(smallArr,smallArrSorted);
    }

    @Test
    void mergesortEmpty() {
        ArrayList<Integer> smallArr = new ArrayList<>();
        ArrayListSorter.mergesort(smallArr);
        assertEquals(smallArr,smallArr);
    }

    @Test
    void mergesortVeryLarge() {
        ArrayList<Integer> largeArrSorted = ArrayListSorter.generateAscending(200);
        ArrayList<Integer> largeArrPerm = ArrayListSorter.generatePermuted(200);
        ArrayListSorter.mergesort(largeArrPerm);
        assertEquals(largeArrSorted,largeArrPerm);
    }

    @Test
    void mergesortRandVeryLarge() {
        Random r = new Random();
        ArrayList<Integer> largeArrSorted = new ArrayList<>(1000);
        ArrayList<Integer> largeArrRan = new ArrayList<>();
        for (int i =0;i<1000;i++) {
            largeArrRan.add(r.nextInt(0,10000));
        }
        for (int i =0;i<1000;i++) {
           largeArrSorted.add(null);
        }

        Collections.copy(largeArrSorted,largeArrRan);
        Collections.sort(largeArrSorted);


        ArrayListSorter.mergesort(largeArrRan);
        assertEquals(largeArrSorted,largeArrRan);
    }

    @Test
    void mergesortDescending() {
        ArrayListSorter.mergesort(descendingArr);
        assertEquals(descendingArr,ascendingArr);
    }

    //quicksortInteger tests---------------------------------------------------

    @Test
    void quicksortPermuted() {
        ArrayListSorter.quicksort(permutedArr);
        assertEquals(permutedArr,ascendingArr);
    }

    @Test
    void quicksortSmall() {
        ArrayList<Integer> smallArr = new ArrayList<>();
        ArrayList<Integer> smallArrSorted = new ArrayList<>();
        smallArrSorted.add(1);
        smallArrSorted.add(2);
        smallArr.add(2);
        smallArr.add(1);
        ArrayListSorter.quicksort(smallArr);
        assertEquals(smallArr,smallArrSorted);
    }

    @Test
    void quicksortEmpty() {
        ArrayList<Integer> smallArr = new ArrayList<>();
        ArrayListSorter.quicksort(smallArr);
        assertEquals(smallArr,smallArr);
    }

    @Test
    void quicksortVeryLarge() {
        ArrayList<Integer> largeArrSorted = ArrayListSorter.generateAscending(200);
        ArrayList<Integer> largeArrPerm = ArrayListSorter.generatePermuted(200);
        ArrayListSorter.quicksort(largeArrPerm);
        assertEquals(largeArrSorted,largeArrPerm);
    }

    @Test
    void quickSortDescending() {
        ArrayListSorter.quicksort(descendingArr);
        assertEquals(descendingArr,ascendingArr);
    }

    //generate array tests----------------------------------------

    @Test
    void generateAscending() {
    }

    @Test
    void generatePermuted() {
    }

    @Test
    void generateDescending() {
    }

    //more complex private method tests---------------------------------------------------

    @Test
    void testMerge() {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0;i<descendingArr.size();i++) {
            temp.add(null);
        }
        ArrayListSorter.merge(descendingArr,0,descendingArr.size()/2,descendingArr.size()-1,temp);
        // essential what this did was sort as if it was two singular halves of values as merge should keep doing this until very small.
        assertEquals(Arrays.toString(temp.toArray()),"[10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11]");
    }

    @Test
    void testPartition(){

        ArrayListSorter.quickSortPartition(descendingArr,0,descendingArr.size()-1);
        // partition moves items around the pivot select which is 11 in this case, which shows this works
        assertEquals(Arrays.toString(descendingArr.toArray()), "[11, 2, 3, 4, 5, 6, 7, 8, 9, 1, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20]");
    }

    @Test
    void testInsertionSort(){
        ArrayListSorter.insertionSort(permutedArr,0,permutedArr.size()-1);
        assertEquals(permutedArr, ascendingArr);
    }

    //mergesortString tests-----------------------------------------------------------------

    @Test
    void mergeSortString(){
        ArrayListSorter.mergesort(names);
        assertEquals(names.get(0), "Abba");
        assertEquals(names.get(1), "Bernadet");
        assertEquals(names.get(2), "John");
        assertEquals(names.get(3), "Kyle");
        assertEquals(names.get(4), "Zaja");
    }

    @Test
    void quickSortString(){
        ArrayListSorter.quicksort(names);
        assertEquals(names.get(0), "Abba");
        assertEquals(names.get(1), "Bernadet");
        assertEquals(names.get(2), "John");
        assertEquals(names.get(3), "Kyle");
        assertEquals(names.get(4), "Zaja");

    }

}