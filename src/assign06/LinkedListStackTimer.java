package assign06;


import java.util.ArrayList;
import java.util.Random;

public class LinkedListStackTimer {
    static LinkedListStack<Integer> listStack = new LinkedListStack<>();
    static ArrayStack<Integer> arrayStack = new ArrayStack<>();

    public static void main(String[] args) {
//            Random randomNumberGenerator = new Random();
            // Do 10000 lookups and use the average running time
            int timesToLoop = 100;
            // For each problem size n . . .
            for (int n = 10000; n <= 200000; n += 10000) {
                // Generate an array of n uNIDs

                // for the pop and peek tests // comment out for push
                for(int i = 0;i<n;i++) {
                     listStack.push(i);
                     arrayStack.push(i);
                }


                long startTime, midpointTime, stopTime;
                // First, spin computing stuff until one second has gone by
                // This allows this thread to stabilize
                startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 1000000000) { // empty block
                }
                // Now, run the test
                startTime = System.nanoTime();

                // timing push method

//                    listStack.push(n);
//                    arrayStack.push(n);


                // timing pop method  // make sure push method at line 20 is uncommented
//                    listStack.pop();
//                    arrayStack.pop();


                // timing peek method  // make sure push method at line 20 is uncommented

//                    listStack.peek();
                    arrayStack.peek();







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







