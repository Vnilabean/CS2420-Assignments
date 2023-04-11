package assign09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class HashTableTimer {
    static private HashTable<StudentBadHash, Double> studentBad = new HashTable<>();
    static private HashTable<StudentMediumHash, Double> studentMed = new HashTable<>();
    static private HashTable<StudentGoodHash, Double> studentGood = new HashTable<>();
    static private HashTable<String, Double> studentGood1 = new HashTable<>();
    static private HashMap<String, Double> javaGood = new HashMap<>();
    static Random rng = new Random();

    /**
     * generate random string method used from an answer in https://stackoverflow.com/questions/2863852/how-to-generate-a-random-string-in-java
     * slightly modified by us from the original to not pass multiple parameters
     * @param length
     * @return
     */
    public static String generateString(int length)
    {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    public static void main(String[] args) {
//            Random randomNumberGenerator = new Random();
        // Do 10000 lookups and use the average running time
        int timesToLoop = 1;
        // For each problem size n . . .
        for (int n = 1000; n <= 20000; n += 1000) {
            //random generator
            Random rng = new Random();
            ArrayList<String> names = new ArrayList<>();
            for (int i = 0;i<n;i++) {
                String s = generateString(rng.nextInt(10));
                names.add(s);
                studentGood1.put(names.get(i), rng.nextDouble(0, 4));
                javaGood.put(names.get(i), rng.nextDouble(0, 4));
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
            for (int l = 0;l<n;l++){
//                    studentGood1.put(names.get(l),rng.nextDouble(0,4));
//                    javaGood.put(names.get(l),rng.nextDouble(0,4));
//                    studentGood1.containsKey(names.get(l));
//                    javaGood.containsKey(names.get(l));
//                    studentGood1.remove(names.get(l));
                    javaGood.remove(names.get(l));

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
            System.out.println(n + "\t" + averageTime + "\t" /* + studentGood.getCollisions() */);
        }
    }
}
