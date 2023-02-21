package assign04;

import assign04.LargestNumberSolver;

import java.util.ArrayList;
import java.util.Random;

public class LargestNumberSolverTimer {

        public static void main(String[] args) {
            Random randomNumberGenerator = new Random();
            // Do 10000 lookups and use the average running time
            int timesToLoop = 1;
            // For each problem size n . . .
            for (int n = 1000;n <= 20000;n += 1000) {
                // Generate an array of n uNIDs
               ArrayList<Integer[]> arr =new ArrayList<Integer[]>(n);
               for (int p = 0;p<n;p++) {
                   Integer[] temp = new Integer[n/1000];
                   for (int i = 0; i < n/1000; i++) {
                       temp[i] = randomNumberGenerator.nextInt(0,99);
                   }
                   arr.add(temp);
               }

                long startTime, midpointTime, stopTime;
                // First, spin computing stuff until one second has gone by
                // This allows this thread to stabilize
                startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 1000000000) { // empty block
                }
                // Now, run the test
                startTime = System.nanoTime();

                LargestNumberSolver.findKthLargest(arr,0);

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



