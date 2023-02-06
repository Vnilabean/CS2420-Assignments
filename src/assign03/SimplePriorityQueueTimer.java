package assign03;


import java.util.ArrayList;
import java.util.Random;

public class SimplePriorityQueueTimer {
        public static void main(String[] args) {
            Random randomNumberGenerator = new Random();
            // Do 10000 lookups and use the average running time
            int timesToLoop = 1;
            // For each problem size n . . .
            for (int n = 10000;n <= 200000;n += 10000) {
                // Generate an array of n uNIDs
                ArrayList<Integer> numbers = new ArrayList<Integer>(n);
                // Randomly shuffle the array
                for (int i = 0; i < n; i++) {
                    numbers.add(randomNumberGenerator.nextInt(1000));
                }
                // Generate a new "random" CS2420Class of students
                // NOTE: student names and contact information are meaningless and unimportant
                SimplePriorityQueue<Integer> randomClass = new SimplePriorityQueue<Integer>();
//                for (int number : numbers)
//                    randomClass.insert(number);
                long startTime, midpointTime, stopTime;
                // First, spin computing stuff until one second has gone by
                // This allows this thread to stabilize
                startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 1000000000) { // empty block
                }
                // Now, run the test
                startTime = System.nanoTime();
                for (int i = 0; i < timesToLoop; i++)
                    // Lookup a student with a uNID that does not exist,
                    // so method will not return early
                    for (int number : numbers)
                        randomClass.insert(number);

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


