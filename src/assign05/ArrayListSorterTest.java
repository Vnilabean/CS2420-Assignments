package assign05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
    void quicksort() {
        System.out.println(permutedArr.toString());
        ArrayListSorter.quicksort(permutedArr);
        System.out.println(permutedArr.toString());
        ArrayListSorter.quicksort(descendingArr);
        System.out.println(descendingArr.toString());
    }

    @Test
    void generateAscending() {
    }

    @Test
    void generatePermuted() {
    }

    @Test
    void generateDescending() {
    }
}