package assign05;

import java.util.ArrayList;
import java.util.Collections;


public class ArrayListSorter {
    private static final int insertThresh = 3;
    //1: returns middle index
    //2: returns first index
    //3: returns greater item of index 0 and index size-1
    private static final int pivotType = 1;

    // mergesort ------------------------------------------------------------------------------------------

    /**
     * This method performs a merge sort on the generic ArrayList given as input.
     * It will use the Comparable interface's compareTo method to determine the correct ordering of elements.
     * Your implementation must switch over to insertion sort when the size of the sublist to be sorted meets
     * a certain threshold (i.e., becomes small enough).
     *
     * @param arr array to be merged sorted
     * @param <T> generic type
     */
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
        int arrSize = arr.size();

        // initializes temp array to arr size to use set to make merging quick
        ArrayList<T> tempMergeArray = new ArrayList<>(arrSize);
        for (int i = 0; i < arr.size(); i++) {
            tempMergeArray.add(null);
        }
        mergeSort(arr, 0, arr.size() - 1, tempMergeArray);
        arr = tempMergeArray;
    }

    /**
     * recursive mergesort method that only takes input from the driver method.
     *
     * @param arr   given array to sort
     * @param start start index
     * @param end   ending index
     * @param <T>   generic type
     */
    private static <T extends Comparable<? super T>> void mergeSort(ArrayList<T> arr, int start, int end, ArrayList<T> tempMergeArray) {
        // uses private final item to change insertion sort threshold
        if (start + insertThresh > end) {
            insertionSort(arr);
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(arr, start, mid, tempMergeArray);
        mergeSort(arr, mid + 1, end, tempMergeArray);
        merge(arr, start, mid, end, tempMergeArray);
    }

    /**
     * final step in merge sort of merging the sorted partitioned sections of the array.
     *
     * @param arr    given array
     * @param start  starting index
     * @param middle middle index
     * @param end    ending index
     * @param <T>    generic type
     */
    protected static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, int start, int middle, int end, ArrayList<T> tempMergeArray) {
        int leftPointer = start;
        int rightPointer = middle;
        int insertionPosition = start;

        // move pointer and compares to other pointer and adds the smaller to temp array
        while (leftPointer < middle && rightPointer <= end) {
            if (arr.get(leftPointer).compareTo(arr.get(rightPointer)) <= 0) {
                tempMergeArray.set(insertionPosition, arr.get(leftPointer));
                insertionPosition++;
                leftPointer++;
            } else {
                tempMergeArray.set(insertionPosition, arr.get(rightPointer));
                insertionPosition++;
                rightPointer++;
            }
        }

        // adds left over items if they were not added from the last while loop
        while (leftPointer < middle) {
            tempMergeArray.set(insertionPosition, arr.get(leftPointer));
            insertionPosition++;
            leftPointer++;
        }
        while (rightPointer <= end) {
            tempMergeArray.set(insertionPosition, arr.get(rightPointer));
            insertionPosition++;
            rightPointer++;
        }
    }

    /**
     * Insertion sort for mergeSort threshold
     *
     * @param arr array to be insertion sorted
     * @param <T> Generic
     */
    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr) {
        int size = arr.size();
        for (int i = 1; i < size; i++) {
            T current = arr.get(i);
            int prev = i - 1;
            while (prev >= 0 && arr.get(prev).compareTo(current) > 0) {
                arr.set(prev + 1, arr.get(prev));
                prev = prev - 1;
            }
            arr.set(prev + 1, current);
        }
    }

    // quicksort---------------------------------------------------------------------------------

    /**
     * This method performs a quicksort on the generic ArrayList given as input.
     * You must implement three different strategies for determining the pivot, and your
     * implementation should be able to easily switch among these strategies.
     *
     * @param arr to be sorted
     * @param <T> the type of array
     */
    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
        quicksort(arr, 0, arr.size() - 1);
    }

    /**
     * compares the start and end and if the start is lower than the end this calles partition
     * on the arr and its starting and ending position then recursively calls quickSort on the arrays that
     * are parted from start to partition and partition to end
     *
     * @param arr   to be sorted
     * @param start position
     * @param end   position
     * @param <T>   type to be used
     */
    private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr, int start, int end) {
        if (start < end) {
            int part = quickSortPartition(arr, start, end);
            quicksort(arr, start, part - 1);
            quicksort(arr, part, end);
        }
    }

    /**
     * Partitions an array. First selects a pivot and the left and right side of the array. compares the left and right
     * side of the array to the pivot and places all smaller (smaller than pivot) to left of pivot and
     * all greater elements to right of the pivot
     *
     * @param arr array
     * @param start starting index for recursion
     * @param end ending index for recursion
     * @param <T> generic type
     * @return
     */
    protected static <T extends Comparable<? super T>> int quickSortPartition(ArrayList<T> arr, int start, int end) {

        int pivotPosition = pivot(arr, pivotType, start, end);
        swap(arr, pivotPosition, end);
        // pivot point
        T pivotItem = arr.get(end);
        //index for the start
        int left = start;
        //index for the end
        int right = end;
        //while the start is lesser than the end index
        while (left <= right) {
            while (left < end && arr.get(left).compareTo(pivotItem) < 0) {
                left++;
            }
            while (right >= start && arr.get(right).compareTo(pivotItem) > 0) {
                right--;
            }
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }

        }
        return left;
    }

    /**
     * small method to swap two items in a array
     * @param arr array
     * @param index1 index of first item to swap
     * @param index2 index of second item to swap
     * @param <T>
     */
    private static <T> void swap(ArrayList<T> arr, int index1, int index2) {
        T temp = arr.get(index1);
        arr.set(index1, arr.get(index2));
        arr.set(index2, temp);
    }

    /**
     * pivot method to return int position for best pivot.
     *
     * @param arr array
     * @param type Allows changing the pivot strategy by setting type to 1-3
     *             1: returns middle index
     *             2: returns first index
     *             3: returns greater item of index 0 and index size-1
     *             Default: the last index
     * @param <T>  generic type
     * @return int position on the given array for the pivot
     */
    private static <T extends Comparable<? super T>> int pivot(ArrayList<T> arr, int type, int start, int end) {
        switch (type) {
            case 1 -> {
                return ((end - start) / 2) + start;
            }
            case 2 -> {
                return start;
            }
            case 3 -> {
                if (arr.get(start).compareTo(arr.get(end)) > 0) {
                    return start;

                } else {
                    return end;
                }
            }
            default -> {
                return end;
            }
        }
    }

    //generate arraylist methods---------------------------------------------------------------------

    /**
     * This method generates and returns an ArrayList of integers 1 to size in ascending order.
     *
     * @param size
     * @return
     */
    public static ArrayList<Integer> generateAscending(int size) {
        ArrayList<Integer> temp = new ArrayList<Integer>(size);
        for (int i = 1; i <= size; i++) {
            temp.add(i);
        }
        return temp;
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in permuted order (i,e., randomly ordered).
     * You may adapt the shuffle method written in Class Meeting 10 or make use of Java's Collections.shuffle method.
     *
     * @param size
     * @return random array
     */
    public static ArrayList<Integer> generatePermuted(int size) {
        ArrayList<Integer> temp = new ArrayList<Integer>(size);
        for (int i = 1; i <= size; i++) {
            temp.add(i);
        }
        Collections.shuffle(temp);
        return temp;
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in descending order.
     *
     * @param size
     * @return
     */
    public static ArrayList<Integer> generateDescending(int size) {
        ArrayList<Integer> temp = new ArrayList<Integer>(size);
        for (int i = size; i > 0; i--) {
            temp.add(i);
        }
        return temp;
    }
}
