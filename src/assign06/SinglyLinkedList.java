package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a SinglyLinkedList using a custom iterator
 *
 * @param <E> Generic item
 * @author Philippe Gonzalez and Conner Francis
 */
public class SinglyLinkedList<E> implements List<E> {
    private Node<E> head;
    private int size;
    /**
     * Constructor for empty SinglyLinkedList
     */
    public SinglyLinkedList() {
        head = null;
        size = 0;
    }
    public SinglyLinkedList(Node<E> headItem){
        head = headItem;
        size = 1;
    }

    /**
     * Constructor for creating a SinglyLinkedList with one head node containing parameter element
     *
     * @param element Data the node will contain
     */
    public SinglyLinkedList(E element) {
        head = new Node<E>(element);
        size = 1;
    }

    /**
     * Inserts an element at the beginning of the list.
     * O(1) for a singly-linked list.
     *
     * @param element - the element to add
     */
    @Override
    public void insertFirst(E element) {
        Node<E> first = new Node<E>(element);
        if(head == null) {
            head = first;
            size++;
        }else {
            first.setNext(head);
            head = first;
            size++;
        }
    }

    /**
     * Inserts an element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index   - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
     */
    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException {


        if (head == null && index == 0) {
            insertFirst(element);
            return;
        }
        if (index == 0) {
            insertFirst(element);
            return;
        }
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> prevNode = head;
        Node<E> currentNode = head;
        Node<E> newNode = new Node<E>((E) element);
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                prevNode.setNext(newNode);
                size++;
                if(currentNode != null) {
                    newNode.setNext(currentNode);
                }

                return;
            }
            prevNode = currentNode;

            if(currentNode.hasNext()) {
                currentNode = currentNode.getNext();
            }
            else{
                currentNode = null;
            }


        }


        prevNode.setNext(newNode);
        size++;
        if (!prevNode.equals(currentNode)) {
            newNode.setNext(currentNode);

        }
    }

    /**
     * Gets the first element in the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.getData();
    }

    /**
     * Gets the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> currentNode = head;
        if (index == 0) {
            return head.getData();
        }
        for(int i = 0;i<=index;i++) {
            if (i == index) {
                return currentNode.getData();
            }
            currentNode = currentNode.getNext();
        }

        // once iterator is done

        return null;
    }

    /**
     * Deletes and returns the first element from the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E deleteFirst() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            Node<E> temp = head;
            head = null;
            size--;
            return temp.getData();
        }
        Node<E> temp = head;
        head = head.getNext();
        size--;
        return temp.getData();
    }

    /**
     * Deletes and returns the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E delete(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
           return deleteFirst();

        }
        Node<E> currentNode = head;
        Node<E> prevNode = head;
        for (int l = 0; l <= index; l++) {
            if (l == index) {
                if (currentNode.getNext() != null) {
                    prevNode.setNext(currentNode.getNext());
                    size--;
                    return currentNode.getData();
                }
                prevNode.setNext(null);
                size--;
                return currentNode.getData();
            }
            prevNode = currentNode;
            if(currentNode.hasNext()) {
                currentNode = currentNode.getNext();
            }
        }

        return null;
    }

    /**
     * Determines the index of the first occurrence of the specified element in the list,
     * or -1 if this list does not contain the element.
     * O(N) for a singly-linked list.
     *
     * @param element - the element to search for
     * @return the index of the first occurrence; -1 if the element is not found
     */
    @Override
    public int indexOf(E element) {
        Node<E> currentNode = head;
        int position = 0;

        while (currentNode.getData() != null) {
            if (currentNode.getData().equals(element)) {
                return position;
            }
            if(currentNode.hasNext()) {
                currentNode = currentNode.getNext();
            }
            position++;
        }
        return -1;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return true if this collection contains no elements; false, otherwise
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Removes all of the elements from this list.
     * O(1) for a singly-linked list.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Generates an array containing all of the elements in this list in proper sequence
     * (from first element to last element).
     * O(N) for a singly-linked list.
     *
     * @return an array containing all of the elements in this list, in order
     */
    @Override
    public Object[] toArray() {
        Object[] temp = new Object[size];
        if(head == null) {
            return temp;
        }
        Node<E> currentNode = head;
        for (int l = 0; l < size; l++) {
            temp[l] = currentNode.getData();
            currentNode = currentNode.getNext();

        }
        return temp;
    }

    /**
     * @return an iterator over the elements in this list in proper sequence (from first
     * element to last element)
     */
    @Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator();
    }

    public class SinglyLinkedListIterator implements Iterator<E> {

        private boolean removeCallable = false;
        private Node<E> currentNode = head;
        private Node<E> prevNode = null;
        private boolean firstCall = true;
        /**
         * Returns true if the iteration has more elements.
         * (In other words, returns true if next would
         * return an element rather than throwing an exception.)
         *
         * @return true if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return currentNode.getNext() != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() throws NoSuchElementException {
            if (firstCall) {
                firstCall = false;
                removeCallable = true;
                return head.getData();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            if(currentNode.getData() != null) {
                prevNode = currentNode;
            }
            currentNode = currentNode.getNext();
            removeCallable = true;
            return currentNode.getData();
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to next.
         */
        @Override
        public void remove() throws IllegalStateException {
            if (removeCallable) {
                if (prevNode == null) {
                    removeCallable = false;
                    head = head.getNext();
                    currentNode.setData(null);
                    size--;
                    return;
                }
                prevNode.setNext(currentNode.getNext());
                removeCallable = false;
                size--;
                return;
            }
            throw new IllegalStateException();
        }
    }



}
