package exammakeup;


import java.util.*;

public class MedianTimer {
    static Random rng = new Random();



    public static void main(String[] args) {
//            Random randomNumberGenerator = new Random();
        // Do 10000 lookups and use the average running time
        int timesToLoop = 1;
        // For each problem size n . . .
        for (int n = 5000; n <= 100000; n += 5000) {
            //random generator
            Random rng = new Random();
            // generate a list of vertices
            int[] testLargeShuffled = new int[n+1];
            Integer[] testLargeShuffledInteger = new Integer[n+1];
            List<Integer> arrAsList = Arrays.asList(testLargeShuffledInteger);
            Collections.shuffle(arrAsList);
            arrAsList.toArray(testLargeShuffledInteger);
            for(int i = 0; i<=n;i++) {
                testLargeShuffledInteger[i] = i;
            }
            for(int i = 0;i<=n;i++) {
                testLargeShuffled[i] = testLargeShuffledInteger[i];
            }

            long startTime, midpointTime, stopTime;
            // First, spin computing stuff until one second has gone by
            // This allows this thread to stabilize
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }
            // Now, run the test
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++) {


//            Median.median(testLargeShuffled);
                Median.quickSort(testLargeShuffled);



            }
            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the "timesToLoop" loop
            for (int i = 0; i < timesToLoop; i++) { // empty block
            }
            stopTime = System.nanoTime();
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups
            // Average it over the number of runs
            double averageTime = ((midpointTime -
                    startTime) - (stopTime - midpointTime)) /
                    (double) timesToLoop;
            System.out.println(n + "\t" + averageTime);
        }
    }
}
