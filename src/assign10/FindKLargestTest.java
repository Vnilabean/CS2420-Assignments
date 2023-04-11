package assign10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FindKLargestTest {
    ArrayList<Integer> temp;
    Random rng;

    @BeforeEach
    void setUp() {
        rng = new Random();
        temp = new ArrayList<>();
        for(int i = 0; i<100;i++) {
            temp.add(rng.nextInt());
            Collections.shuffle(temp);
        }
    }

    @Test
    void findKLargestHeap() {
        System.out.println(FindKLargest.findKLargestHeap(temp,5));
    }

    @Test
    void testFindKLargestHeap() {
    }

    @Test
    void findKLargestSort() {
    }

    @Test
    void testFindKLargestSort() {
    }
}