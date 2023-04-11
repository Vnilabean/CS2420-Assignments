package assign08;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

public class BinarySearchTreeTimer {



        static Random rng = new Random();
        static BinarySearchTree<Integer> randomOrder = new BinarySearchTree<>();
        static BinarySearchTree<Integer> ascendingOrder = new BinarySearchTree<>();
        static TreeSet<Integer> tree = new TreeSet<>();


        public static void main(String[] args) {
//            Random randomNumberGenerator = new Random();
            // Do 10000 lookups and use the average running time
            int timesToLoop = 10;
            // For each problem size n . . .
            for (int n = 1000; n <= 20000; n += 1000) {
                //random generator
                Random rng = new Random();
                // generate a list of vertices
                ArrayList<Integer> arr = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    arr.add(i);
                }
                ArrayList<Integer> rand = new ArrayList<>();

//                ascendingOrder.addAll(sorted);
                Collections.shuffle(arr);
                tree.addAll(arr);
//                randomOrder.addAll(arr);

                long startTime, midpointTime, stopTime;
                // First, spin computing stuff until one second has gone by
                // This allows this thread to stabilize
                startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 1000000000) { // empty block
                }
                // Now, run the test
                startTime = System.nanoTime();
                for (int i = 0; i < timesToLoop; i++) {
                    // test 1 ---------------------
//                    ascendingOrder.contains(rng.nextInt(n));
//                    randomOrder.contains(rng.nextInt(n));
                    // test 2 ---------------------
//                    tree.addAll(arr);
//                    randomOrder.addAll(arr);
                    // test 3 ---------------------
//                       tree.contains(rng.nextInt(n));
//                    randomOrder.contains(rng.nextInt(n));
                    // test 4 ---------------------
                    tree.remove(rng.nextInt(n));
//                    randomOrder.remove(rng.nextInt(n));

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


