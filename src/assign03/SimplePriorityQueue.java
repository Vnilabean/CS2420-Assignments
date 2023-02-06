/**
 * a priority queue that can accommodate any type of item
 * (e.g., strings, integers, library books, and anything).
 * A priority queue is a container in which access is limited to the "highest priority" item,
 * which we define as the maximum item.  Of all operations this one requires for the priority queue,
 * finding the maximum item and deleting the maximum item must run the fastest.
 * To achieve fast findMax and deleteMax methods, we have decided to maintain the priority queue as a sorted array of items,
 * smallest to largest. Every priority queue will begin as an empty array (which is already in sorted order),
 * and we can simply insert items in the correct order to ensure that the array remains sorted
 *
 * @author Philippe Gonzalez and Conner Francis
 * @version January 29, 2023
 */
package assign03;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
/**
 * An array that acts as a priority que from ascending order left to right with the
 * biggest objects on the right and smaller ones on the left of the priority que to indicate its priority.
 * Holds the size in a separate variable to change it when necessary such as inserting something or removing the max
 * number. When the priority que runs out of size it gets doubled.(not the field)
 *
 * @author Philippe Gonzalez and Conner Francis
 * @version Jan 31, 2023
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E> {

    private E[] priorityQue; // array storage
    private int size; // keeps track of how many items have been added
    private Comparator<? super E> c = null;

    @SuppressWarnings("unchecked")
    /**
     * If this constructor is used to create the priority queue, it is assumed that the elements
     * are ordered using their natural ordering
     */
    public SimplePriorityQueue() {
        size = 0;
        priorityQue = (E[]) new Object[10];
    }

    /**
     * If this constructor is used to create the priority queue, it is assumed that the
     * elements are ordered using the provided Comparator object.
     *
     * @param cmp
     */
    public SimplePriorityQueue(Comparator<? super E> cmp) {
        priorityQue = (E[]) new Object[10];
        c = cmp;
    }

    /**
     * private helper method to double an array's size and
     * put the original priority queue's elements into the new one
     */
    private void growArray() {
        E[] result = (E[]) new Object[priorityQue.length * 2];
        for (int i = 0; i < size; i++) {
            result[i] = priorityQue[i];
        }
        priorityQue = result;
    }


    /**
     * Retrieves, but does not remove, the maximum element in this priority
     * queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public E findMax() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return priorityQue[size - 1];
    }

    /**
     * Retrieves and removes the maximum element in this priority queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public E deleteMax() throws NoSuchElementException {
        if (size == 0) {
           throw new NoSuchElementException();
        }
        E temp = priorityQue[size - 1];
        priorityQue[size - 1] = null;
        size--;
        return temp;
    }

    /**
     * Inserts the specified element into this priority queue.
     * if the last element inserted is the final spot within the priority queue
     * then grow the array. Search the array to know where to insert the element within
     * the array.
     *
     * @param item - the element to insert
     */
    @Override
    public void insert(E item) {
        int cmp;
        if (size == priorityQue.length - 1) {
            growArray();
        }
        if (size == 0) {
            priorityQue[0] = item;
            size++;
            return;
        }
        int index = binarySearchArray(item,0);
        if (c != null) {
            cmp = (c.compare(item,priorityQue[index]));
        }
        else {
            cmp = ((Comparable<? super E>) item).compareTo(priorityQue[index]);

        }
        if (cmp <= 0) {
            for (int i = size-1; i >= index; i--) {
                priorityQue[i + 1] = priorityQue[i];
            }
            priorityQue[index] = item;
        }
        if (cmp > 0) {

            for (int i = size; i > index; i--) {
                priorityQue[i + 1] = priorityQue[i];
            }
            priorityQue[index+1] = item;
        }

        size++;
    }

    /**
     * Inserts the specified elements into this priority queue.
     *
     * @param coll - the collection of elements to insert
     */
    @Override
    public void insertAll(Collection<? extends E> coll) {
        for (E item : coll) {
            insert(item);
        }
    }

    /**
     * Indicates whether this priority queue contains the specified element.
     *
     * @param item - the element to be checked for containment in this priority
     *             queue
     */
    @Override
    public boolean contains(E item) {
     int outcome = binarySearchArray(item,1);
        return outcome != -1;
    }

    /**
     * @return the number of elements in this priority queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if this priority queue contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all the elements from this priority queue. The queue will be
     * empty when this call returns.
     */
    @Override
    public void clear() {
        priorityQue = (E[]) new Object[10];
        size = 0;
    }

    /**
     * loops through the priority que by taking the end points and dividing it
     * and slowly searches through it and if it finds the correct element return the index.
     * If it can't find the correct element it returns the Index of the element closest to the indicated
     * item with the index of that element being less than the indicated item.
     *
     * @param item generic item E that is used for binary searching the array
     * @param search
     * @return the index of the item found
     */
    private int binarySearchArray(E item, int search) {
        int lower = 0;
        int upper = size - 1;


        while (lower <= upper) {
            int cmp;
            int mid = (lower + upper) / 2;
            if (c != null) {
                cmp = (c.compare(item,priorityQue[mid]));
            }
            else {
                cmp = ((Comparable<? super E>) item).compareTo(priorityQue[mid]);
            }

            if (search != 1 && lower == upper) {
                return lower;
            }
            if (cmp == 0) {
                return mid;
            }
            if (cmp > 0) {
                lower = mid + 1;
            }
            if (cmp < 0) {
                upper = mid - 1;
            }
        }
        if (search != 1) {
            return lower;
        }
        return -1;
    }
}