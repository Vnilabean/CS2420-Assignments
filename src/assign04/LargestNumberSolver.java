package assign04;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
    private static String arrToString(Integer[] arr) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0;i < arr.length;i++) {
            temp.append(arr[i]);
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

        return 0;
    }

    public static long findLargestLong(Integer[] arr) throws OutOfRangeException {

        return 0;
    }

    public static BigInteger sum(List<Integer[]> list) {

        return null;
    }

    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {

        return new Integer[0];
    }

    public static List<Integer[]> readFile(String filename) {

        return null;
    }

    static class CompareForLargestNumber implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            StringBuilder numab = new StringBuilder();
            StringBuilder numba = new StringBuilder();

            String num1 = o1.toString();
           String num2 = o2.toString();
           numab.append(num1);
           numab.append(num2);
           numba.append(num2);
           numba.append(num1);
           Integer abint = Integer.parseInt(numab.toString());
           Integer baint = Integer.parseInt(numba.toString());
           if (abint > baint) return -1;
           if (baint > abint) return 1;
           else return 0;


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
    @SuppressWarnings("serial")
    public class OutOfRangeException extends RuntimeException {
        public OutOfRangeException(String dataTypeName) {
            super("The value is too large for the " + dataTypeName + " datatype.");
        }
    }
}
