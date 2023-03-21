package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    public static class BinaryNode<T> {
        private T data;
        private BinaryNode<T> leftBinaryNode;
        private BinaryNode<T> rightBinaryNode;

        public BinaryNode(T nodeData, BinaryNode<T> leftNode, BinaryNode<T> rightNode) {
            data = nodeData;
            leftBinaryNode = leftNode;
            rightBinaryNode = rightNode;
        }

        public BinaryNode(T nodeData) {
            data = nodeData;
            leftBinaryNode = null;
            rightBinaryNode = null;
        }

        public BinaryNode<T> getRightBinaryNode() {
            return rightBinaryNode;
        }

        public void setRightBinaryNode(BinaryNode<T> node) {
            rightBinaryNode = node;
        }

        public BinaryNode<T> getLeftBinaryNode() {
            return leftBinaryNode;
        }

        public void setLeftBinaryNode(BinaryNode<T> node) {
            leftBinaryNode = node;
        }

        public T getData() {
            return data;
        }

        public void setData(T item) {
            data = item;
        }

        public BinaryNode<T> getRightmost() {
            BinaryNode<T> current = this;
            while (current.getRightBinaryNode() != null) {
                current = current.getRightBinaryNode();
            }
            return current;
        }

        public BinaryNode<T> getLeftmost() {
            BinaryNode<T> current = this;
            while (current.getLeftBinaryNode() != null) {
                current = current.getLeftBinaryNode();
            }
            return current;
        }
    }

    private BinaryNode<Type> rootNode;
    private int treeSize;

    public BinarySearchTree() {
        rootNode = null;
    }

    public BinarySearchTree(Type data) {
        rootNode = new BinaryNode<>(data);
    }


    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(Type item) {
        BinaryNode<Type> newNode = new BinaryNode<>(item);
        BinaryNode<Type> current = rootNode;
        BinaryNode<Type> previousNode = rootNode;
        if (rootNode == null) {
            rootNode = newNode;
            treeSize++;
            return true;
        }
        // traverses the tree to find the location where the node should be
        while(current != null) {
            previousNode = current;
            if(current.getData().compareTo(newNode.getData()) > 0) {
                current = current.getLeftBinaryNode();
            }else{
                current = current.getRightBinaryNode();
            }
        }
        // if tree contains the item already
        if(previousNode.getData().equals(newNode.getData())) {
            return false;
        }
        // compares it to the node it should be at to see if it is to the left or right
        if(newNode.getData().compareTo(previousNode.getData()) > 0) {
            previousNode.setRightBinaryNode(newNode);
        }else{
            previousNode.setLeftBinaryNode(newNode);
        }
        treeSize++;
        return true;


//        if (current.getData().compareTo(rootNode.getData()) > 0) {
//            for (int i = 0; i < treeSize; i++) {
//                if (next.getRightBinaryNode() == null || current.getData().compareTo(next.getRightBinaryNode().getData()) < 0) {
//                    next.setRightBinaryNode(current);
//                    return true;
//                }
//                if (next.getRightBinaryNode().getLeftBinaryNode() == null || current.getData().compareTo(next.getRightBinaryNode().getLeftBinaryNode().getData()) < 0) {
//                    next.getRightBinaryNode().setLeftBinaryNode(current);
//                    return true;
//                }
//                next = next.getRightBinaryNode();
//            }
//        } else if (current.getData().compareTo(rootNode.getData()) < 0) {
//            for (int i = 0; i < treeSize; i++) {
//                if (next.getLeftBinaryNode() == null || current.getData().compareTo(next.getLeftBinaryNode().getData()) < 0) {
//                    next.setLeftBinaryNode(current);
//                    return true;
//                }
//                if (next.getLeftBinaryNode().getRightBinaryNode() == null || current.getData().compareTo(next.getLeftBinaryNode().getRightBinaryNode().getData()) < 0) {
//                    next.getLeftBinaryNode().setRightBinaryNode(current);
//                    return true;
//                }
//                next = next.getLeftBinaryNode();
//            }
//        }
    }



    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        boolean wasAdded = false;
        for (Type i : items) {
            if (add(i)) {
                wasAdded = true;
            }
        }
        return wasAdded;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        rootNode = null;
        treeSize = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    @Override
    public boolean contains(Type item) {
        BinaryNode<Type> current = rootNode;
        if (rootNode.getData().equals(item)) {
            return true;
        }
        while (!item.equals(current.getData())) {
            if (item.compareTo(current.getData()) < 0) {
                if (current.getLeftBinaryNode() != null) {
                    return false;
                }
                current = current.getLeftBinaryNode();
            }
            if (item.compareTo(current.getData()) > 0) {
                if (current.getRightBinaryNode() != null) {
                    return false;
                }
                current = current.getRightBinaryNode();
            }
        }
        return true;

    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        for (Type i : items) {
            if (!contains(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type first() throws NoSuchElementException {
        if (rootNode.getData().compareTo(rootNode.getLeftmost().getData()) < 0) {
            return rootNode.getData();
        }
        return rootNode.getLeftmost().getData();
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        if (rootNode != null && rootNode.getData() == null) {
            return true;
        }
        return rootNode == null;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type last() throws NoSuchElementException {
        if (rootNode.getData().compareTo(rootNode.getRightmost().getData()) > 0) {
            return rootNode.getData();
        }
        return rootNode.getRightmost().getData();
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(Type item) {
        BinaryNode<Type> nodeToBeRemov = new BinaryNode<>(item);
        BinaryNode<Type> temp = new BinaryNode<>(rootNode.getData());
        BinaryNode<Type> current = rootNode;
        BinaryNode<Type> previousNode = rootNode;
        // traverses the tree to find the location where the node should be
        while(nodeToBeRemov != current) {
            previousNode = current;
            if(current.getData().compareTo(nodeToBeRemov.getData()) > 0) {
                current = current.getLeftBinaryNode();
            }else{
                current = current.getRightBinaryNode();
            }
        }
        // if tree contains the item already
        if(current.getData().equals(nodeToBeRemov.getData())) {
            if (current.getRightBinaryNode() != null && current.getLeftBinaryNode() != null) { // two children
                temp = findSuccessor(current);
                remove(temp.getData());
                current = temp;
                return true;
            }
            else if (current.getRightBinaryNode() != null && current.getLeftBinaryNode() == null) { // one child on the right
                if (previousNode.getLeftBinaryNode() != null || previousNode.getLeftBinaryNode().equals(current))
                    previousNode.setLeftBinaryNode(current.getRightBinaryNode());
                if (previousNode.getRightBinaryNode() != null || previousNode.getRightBinaryNode().equals(current))
                    previousNode.setLeftBinaryNode(current.getRightBinaryNode());
                current = null;
                return true;
            }
            else if (current.getRightBinaryNode() == null && current.getLeftBinaryNode() != null) { // one child on the left
                if (previousNode.getLeftBinaryNode() != null || previousNode.getLeftBinaryNode().equals(current))
                    previousNode.setLeftBinaryNode(current.getLeftBinaryNode());
                if (previousNode.getRightBinaryNode() != null || previousNode.getRightBinaryNode().equals(current))
                    previousNode.setLeftBinaryNode(current.getLeftBinaryNode());
                current = null;
                return true;
            }
            else { //no children
                current = null;
                return true;
            }
        }
        return false;
    }

    private BinaryNode<Type> findSuccessor(BinaryNode<Type> current){
        if (current.getRightBinaryNode() != null)
            return current.getLeftmost();
        return null;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually removed); otherwise,
     * returns false
     */
    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        boolean removed = false;
        for (Type i : items) {
            if (remove(i)) {
                removed = true;
            }
        }
        return removed;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return treeSize;
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<Type> toArrayList() {
        ArrayList<Type> sorted = new ArrayList<>();
        if(rootNode != null) {
            recArrayList(sorted,rootNode);
        }
        return sorted;
    }

    private void recArrayList(ArrayList<Type> arr,BinaryNode<Type> currentNode) {
        if(currentNode != null) {
            recArrayList(arr,currentNode.getLeftBinaryNode());
            arr.add(currentNode.getData());
            recArrayList(arr,currentNode.getRightBinaryNode());

        }
    }

}

