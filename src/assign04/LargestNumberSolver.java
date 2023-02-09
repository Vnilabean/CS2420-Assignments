package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class LargestNumberSolver {




    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            T key = arr[i];
            int a = i-1;
            while ( (a > -1) && (cmp.compare(arr[a],key) > 0)) {
                arr[a+1] = arr[a];
                a--;
            }
            arr[a+1] = key;
        }
    }

    /**
     * Makes a string of characters that removes [] and , for BigInteger to be able to use
     * @param arr array to make a usable string
     * @return string array as only numbers
     */
    private static String arrToString(Integer[] arr) {
        StringBuilder temp = new StringBuilder();
        for (Integer integer : arr) {
            temp.append(integer);
        }
        return temp.toString();
    }

    public static BigInteger findLargestNumber(Integer[] arr) {
       Integer[] temp = new Integer[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        insertionSort(temp,new CompareForLargestNumber());

        return new BigInteger(arrToString(temp));
    }

    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
        //  2,147,483,647

        Integer[] temp = new Integer[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        insertionSort(temp, new CompareForLargestNumber());
        BigInteger tempBigInteger = new BigInteger(arrToString(temp));
        if(tempBigInteger.compareTo(new BigInteger(String.valueOf(2147483647))) > 0) {
            throw new OutOfRangeException("int");
        }
        return tempBigInteger.intValue();
    }

    public static long findLargestLong(Integer[] arr) throws OutOfRangeException {

        return 0;
    }

    public static BigInteger sum(List<Integer[]> list) {

        return null;
    }

    /**
     * This method finds the largest number from a list of integer arrays of those integer arrays.
     * k = 0 should return the highest number and k = 1 should return the second-highest number.
     * Any values of k that go beyond the length of the list of integer arrays should throw an IllegalArgumentException.
     * Neither the list nor any of the inner arrays should be modified in any way.
     *
     * @param list list of integer arrays
     * @param k the index of the array that is the biggest of kth time
     * @return the kth the largest integer array within the list provided
     * @throws IllegalArgumentException if the index location is out of bound this is thrown
     */
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
       if (k >= list.size() || k < 0) {
           throw new IllegalArgumentException();
       }

        Integer[][] arrs = new Integer[list.size()][];
        int i = 0;
        for (Integer[] item: list) {
            arrs[i] = item;
            i++;
        }
        insertionSort(arrs, new CompareForLargestNumberArray());
        return arrs[k];
    }

    public static List<Integer[]> readFile(String filename){
        Scanner s = null;
        try {
            s = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer[]> finalArr= new ArrayList<>();
        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(" ");
            Integer[] nums = new Integer[line.length];
            for (int i = 0;i < line.length;i++) {
                nums[i] = Integer.parseInt(line[i]);
            }
            finalArr.add(nums);
        }

        return finalArr;
    }

    static class CompareForLargestNumber implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            // checks different orders of numbers next to each other
            StringBuilder numab = new StringBuilder();
            StringBuilder numba = new StringBuilder();

            String num1 = o1.toString();
           String num2 = o2.toString();
           numab.append(num1);
           numab.append(num2);
           numba.append(num2);
           numba.append(num1);
           int abint = Integer.parseInt(numab.toString());
           int baint = Integer.parseInt(numba.toString());
           if (abint > baint) return -1;
           if (baint > abint) return 1;
           else return 0;


        }

    }

    static class CompareForLargestNumberArray implements Comparator<Integer[]> {

        @Override
        public int compare(Integer[] o1, Integer[] o2) {
           return LargestNumberSolver.findLargestNumber(o2).compareTo(LargestNumberSolver.findLargestNumber(o1));
        }
    }




    /**
     * This class represents an exception to the normal flow of a program when a
     * numerical value is out of range for a data type.
     *
     * NOTE: The warning about serialized runtime is suppressed because it is
     *       beyond the scope of CS 2420.  Do not suppress warnings in your
     *       programs unless you have been given explicit guidance/permission to
     *       so by the course staff!
     *
     * @author Erin Parker
     * @version February 10, 2021
     */

}
