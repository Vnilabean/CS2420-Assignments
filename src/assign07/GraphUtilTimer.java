package assign07;

import assign06.ArrayStack;
import assign06.LinkedListStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GraphUtilTimer {
    static Graph g = new Graph();
    static Random rng = new Random();


    public static void main(String[] args) {
//            Random randomNumberGenerator = new Random();
        // Do 10000 lookups and use the average running time
        int timesToLoop = 100;
        // For each problem size n . . .
        for (int n = 10000; n <= 200000; n += 10000) {
            //random generator
            Random rng = new Random();
            // generate a list of vertices

            ArrayList<Integer> sources = new ArrayList<>();
            ArrayList<Integer> dest = new ArrayList<>();
            ArrayList<Integer> sources2 = new ArrayList<>();
            ArrayList<Integer> dest2 = new ArrayList<>();
            // randomly connect the vertices using 2 * |V| edges
            for(int i = 0; i < 2 * n; i++) {
                sources.add(rng.nextInt(n));
                dest.add(rng.nextInt(n));
            }
            for(int p = 0;p<n;p++) {
                sources2.add(p);
                dest2.add(p+1);
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
                //are connected method
                try {
//                    GraphUtility.areConnected(sources,dest,rng.nextInt(n),rng.nextInt(n));
                } catch (IllegalArgumentException e) {
                    break;
                }
                //shortest path method
                try {
//               GraphUtility.shortestPath(sources,dest,rng.nextInt(n), rng.nextInt(n));
                } catch (IllegalArgumentException e) {
                    break;
                }
                //sort method
                try {
                    GraphUtility.sort(sources2, dest2);
                } catch (IllegalArgumentException e) {
                    System.out.println("not working");
                }


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
