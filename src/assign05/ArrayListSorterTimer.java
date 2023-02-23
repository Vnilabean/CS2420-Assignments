package assign05;


import java.util.ArrayList;
import java.util.Random;

public class ArrayListSorterTimer {
    static ArrayList<Integer> toBeSortedPerm = ArrayListSorter.generatePermuted(200000);
    static ArrayList<Integer> toBeSortedAsc = ArrayListSorter.generateAscending(200000);
    static ArrayList<Integer> toBeSortedDes = ArrayListSorter.generateAscending(200000);

    public static void sectionCopy(ArrayList arr, int n,int type) {
        switch (type) {
            case 1, 2 -> {
                for (int l = 0; l < n; l++) {
                    arr.add(toBeSortedPerm.get(l));
                }
            }
            case 3, 4 -> {
                for (int l = 0; l < n; l++) {
                    arr.add(toBeSortedAsc.get(l));
                }
            }
            case 5, 6 -> {
                for (int l = 0; l < n; l++) {
                    arr.add(toBeSortedDes.get(l));
                }
            }
        }

    }

    public static void main(String[] args) {


        for (int threshLoop = 5; threshLoop <= 6; threshLoop++) {
            if (threshLoop == 1 || threshLoop == 3 || threshLoop == 5) {
                System.out.println("Testing quicksort: at type " + threshLoop);
            }
            else {System.out.println("Testing mergesort: at type " + threshLoop);}
//            Random randomNumberGenerator = new Random();
            // Do 10000 lookups and use the average running time
            int timesToLoop = 3;
            // For each problem size n . . .
            for (int n = 10000; n <= 200000; n += 10000) {
                // Generate an array of n uNIDs
                ArrayList<Integer> section = new ArrayList<>();
                sectionCopy(section,n,threshLoop);
                long startTime, midpointTime, stopTime;
                // First, spin computing stuff until one second has gone by
                // This allows this thread to stabilize
                startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 1000000000) { // empty block
                }
                // Now, run the test
                startTime = System.nanoTime();

                switch (threshLoop) {
                    case 1,3,5-> {
                        ArrayListSorter.setPivot(1);
                        ArrayListSorter.quicksort(section);
                        break;
                    }
                    case 2,4,6 -> {
                        ArrayListSorter.setThresh(200);
                        ArrayListSorter.mergesort(section);
                        break;
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

}





