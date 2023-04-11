package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @param <E>
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E>{

    private E[] backingArr;
    // size of the backing array
    private int arrSize;
    // size of tree
    private int size;
    private Comparator<? super E> c = null;


    /**
     * If this constructor is used to create an empty binary heap,
     * it is assumed that the elements are ordered using their natural ordering (i.e., E implements Comparable<? super E>).
     */
    public BinaryMaxHeap(){
        backingArr = (E[]) new Object[10];
        arrSize = 10;
    }

    /**
     * If this constructor is used to create an empty binary heap,
     * it is assumed that the elements are ordered using the provided Comparator object.
     */
    public BinaryMaxHeap(Comparator<? super E> cmp){
        backingArr = (E[]) new Object[10];
        arrSize = 10;
        c = cmp;
    }

    /**
     * If this constructor is used, then the binary heap is constructed from the given list.
     * Also, it is assumed that the elements are ordered using their natural ordering
     * (i.e., E implements Comparable<? super E>).  RECALL: Using the buildHeap operation,
     * we can construct a binary heap from these N items in O(N) time, which is more efficient
     * than adding them to the binary heap one at a time.  This constructor must use such an operation.
     */
    public BinaryMaxHeap(List<? extends E> elements){
        arrSize = elements.size();
        size = elements.size();
        heapify(elements);
    }

    /**
     * If this constructor is used, then the binary heap is constructed from the given list
     * (see RECALL note above).  Also, it is assumed that the elements are ordered using the provided Comparator object.
     */
    public BinaryMaxHeap(List<? extends E> arr, Comparator<? super E> C){
        arrSize = arr.size();
        size = arr.size();
        c = C;
        heapify(arr);
    }

    private void heapify(List<? extends E> elements){
        int index = (elements.size() / 2);
        elements.add(0,null);
        backingArr = (E[]) elements.toArray();
        for(int i = index; i > 0; i--)
            percolateDown(i);
    }

    private int getParentNode(int itemIdx) {
        return itemIdx / 2;
    }

    private void swap(int index1, int index2) {
        E temp = backingArr[index1];
        backingArr[index1] = backingArr[index2];
        backingArr[index2] = temp;
    }

    private int getLeftChild(int index) {
        return index * 2;
    }

    private int getRightChild(int index) {
        return (index * 2) + 1;
    }

    @SuppressWarnings("unchecked")
    private int compare(E item1, E item2) {
        if(c == null) {
            return ((Comparable<? super E>) item1).compareTo(item2);
        }
        return c.compare(item1,item2);
    }

    private void percolateUp(int index) {
        int currentInd = index;
        while(currentInd != 1) {
            E currentItem = backingArr[currentInd];
            int parent = getParentNode(currentInd);
            if(compare(currentItem,backingArr[parent]) > 0) {
                swap(currentInd,parent);
                currentInd = parent;
                continue;
            }
            return;
        }
    }

    private void percolateDown(int index) {
        int currentIdx = index;
        while(currentIdx < size/2) {
            E current = backingArr[currentIdx];
            E left = backingArr[getLeftChild(currentIdx)];
            E right = backingArr[getRightChild(currentIdx)];
            int larger = -1;
            if (left != null && compare(current, left) < 0) {
                larger = getLeftChild(currentIdx);
            }
            if (right != null && compare(current, right) < 0) {
                if (larger != -1 && compare(right, backingArr[larger]) > 0) {
                    larger = getRightChild(currentIdx);
                }
                if (larger == -1) {
                    larger = getRightChild(currentIdx);
                }
            }
            if (larger != -1) {
                swap(currentIdx, larger);
                currentIdx = larger;
            }else{return;}
        }
    }
    /**
     * Adds the given item to this priority queue.
     * O(1) in the average case, O(log N) in the worst case
     *
     * @param item
     */
    @Override
    public void add(E item) {
        if(size == arrSize - 1) {
            int oldSize = size;
            E[] tempArr = (E[]) new Object[oldSize * 2];
            for (int i = 1; i <= oldSize; i++)
                tempArr[i] = backingArr[i];
            backingArr = tempArr;
            arrSize = oldSize * 2;
        }
        backingArr[size + 1] = item;
        percolateUp(size + 1);
        size++;
    }

    /**
     * Returns, but does not remove, the maximum item this priority queue.
     * O(1)
     *
     * @return the maximum item
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (backingArr[1] == null)
            throw new NoSuchElementException();
        return backingArr[1];
    }

    /**
     * Returns and removes the maximum item this priority queue.
     * O(log N)
     *
     * @return the maximum item
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E extractMax() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        E maxItem = backingArr[1];
        swap(1,size);
        backingArr[size] = null;
        size--;
        int index = ((size / 2) );
        for (int i = index; i > 0; i--) {
            percolateDown(i);
        }
        return maxItem;
    }

    /**
     * Returns the number of items in this priority queue.
     * O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this priority queue is empty, false otherwise.
     * O(1)
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Empties this priority queue of items.
     * O(1)
     */
    @Override
    public void clear() {
        backingArr = (E[]) new Object[10];
        arrSize = 10;
        size = 0;
    }

    /**
     * Creates and returns an array of the items in this priority queue,
     * in the same order they appear in the backing array.
     * O(N)
     * <p>
     * (NOTE: This method is needed for grading purposes. The root item
     * must be stored at index 0 in the returned array, regardless of
     * whether it is in stored there in the backing array.)
     */
    @Override
    public Object[] toArray() {
        Object[] temp = (E[]) new Object[size];
        for(int i = 1; i <= size ; i++) {
            temp[i - 1] = backingArr[i];
        }
        return temp;
    }
}
