package assign06;

import java.util.NoSuchElementException;

public class LinkedListStack implements Stack{
    /**
     * Removes all of the elements from the stack.
     */
    @Override
    public void clear() {

    }

    /**
     * @return true if the stack contains no elements; false, otherwise.
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public Object peek() throws NoSuchElementException {
        return null;
    }

    /**
     * Returns and removes the item at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public Object pop() throws NoSuchElementException {
        return null;
    }

    /**
     * Adds a given element to the stack, putting it at the top of the stack.
     *
     * @param element - the element to be added
     */
    @Override
    public void push(Object element) {

    }

    /**
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return 0;
    }
}
