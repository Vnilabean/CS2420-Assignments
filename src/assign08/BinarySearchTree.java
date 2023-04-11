package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * This class works as a Binary Search Tree that deals with generics that implement the Comparable interface
 * and this class itself implements the SortedSet Interface.
 * This works as a non-balanced BST with smaller values residing on the left side of the root node and
 * bigger values residing on the right side of the root node all in relation to the root node's value.
 *
 * @param <Type> Generic Object that implements Comparable
 * @author Philippe Gonzalez and Conner Francis
 * @version March 22, 2023
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private BinaryNode<Type> rootNode;
    private int treeSize; // amount of nodes
    /**
     * BinarySearchTree Constructor
     * that creates a rootNode that is set to null (empty BinarySearchTree)
     */
    public BinarySearchTree() {
        rootNode = null;
    }

    /**
     * BinarySearchTree Constructor
     * that creates a rootNode that contains a data Type (singular noded BinarySearchTree)
     *
     * @param data of Generic Type that is contained within the rootNode
     */
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
        while (current != null) {
            previousNode = current;
            if (current.getData().compareTo(newNode.getData()) > 0) {
                current = current.getLeftBinaryNode();
            } else {
                current = current.getRightBinaryNode();
            }
        }
        // if tree contains the item already
        if (previousNode.getData().equals(newNode.getData())) {
            return false;
        }
        // compares it to the node it should be at to see if it is to the left or right
        if (newNode.getData().compareTo(previousNode.getData()) > 0) {
            previousNode.setRightBinaryNode(newNode);
        } else {
            previousNode.setLeftBinaryNode(newNode);
        }
        treeSize++;
        return true;
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
                if (current.getLeftBinaryNode() == null) {
                    return false;
                }
                current = current.getLeftBinaryNode();
            } else if (item.compareTo(current.getData()) > 0) {
                if (current.getRightBinaryNode() == null) {
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
        if (!isEmpty()) {
            if (rootNode.getData().compareTo(rootNode.getLeftmost().getData()) < 0) {
                return rootNode.getData();
            }
            return rootNode.getLeftmost().getData();
        } else { throw new NoSuchElementException();}
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
        BinaryNode<Type> nodeToBeRemoved = new BinaryNode<>(item);
        BinaryNode<Type> temp;
        BinaryNode<Type> current = rootNode;
        BinaryNode<Type> previousNode = rootNode;
        if (this.isEmpty() || !this.contains(item))
            return false;
        // traverses the tree to find the location where the successor is
        while (nodeToBeRemoved.getData().compareTo(current.getData()) != 0) {
            previousNode = current;
            if (current.getData().compareTo(nodeToBeRemoved.getData()) > 0) {
                current = current.getLeftBinaryNode();
            } else {
                current = current.getRightBinaryNode();
            }
        }
        // if the node has been found
        if (current.getData().equals(nodeToBeRemoved.getData())) {
            // two children of the current node
            if (current.getRightBinaryNode() != null && current.getLeftBinaryNode() != null) {
                temp = findSuccessor(current); // find successor
                remove(temp.getData()); // remove successor
                temp.setLeftBinaryNode(current.getLeftBinaryNode()); //sets the successor's left and right node to the root's
                temp.setRightBinaryNode(current.getRightBinaryNode());
                current.setLeftBinaryNode(null);
                current.setRightBinaryNode(null);
                if (nodeToBeRemoved.getData().equals(rootNode.getData()))
                    rootNode = temp;
                current = temp;
                return true;
            }
            // one child on the right of the current node
            else if (current.getRightBinaryNode() != null && current.getLeftBinaryNode() == null) {
                if (previousNode.getLeftBinaryNode() != null && previousNode.getLeftBinaryNode().equals(current))
                    previousNode.setLeftBinaryNode(current.getRightBinaryNode());
                if (previousNode.getRightBinaryNode() != null && previousNode.getRightBinaryNode().equals(current))
                    previousNode.setLeftBinaryNode(current.getRightBinaryNode());
                if (previousNode.equals(current)) {
                    rootNode = current.getRightBinaryNode();
                    current.setRightBinaryNode(null);
                }
                current = null;
                treeSize--;
                return true;
            }
            // one child on the left of the current node
            else if (current.getRightBinaryNode() == null && current.getLeftBinaryNode() != null) {
                //if the current node was on the left of the previous node
                if (previousNode.getLeftBinaryNode() != null && previousNode.getLeftBinaryNode().equals(current))
                    previousNode.setLeftBinaryNode(current.getLeftBinaryNode());
                if (previousNode.getRightBinaryNode() != null || previousNode.getRightBinaryNode().equals(current))
                    previousNode.setLeftBinaryNode(current.getLeftBinaryNode());
                current = null;
                treeSize--;
                return true;
            }
            //no children of the current node
            else {
                //
                if (previousNode.getLeftBinaryNode() != null && previousNode.getLeftBinaryNode().getData().equals(current.getData()))
                    previousNode.setLeftBinaryNode(null);
                if (previousNode.getRightBinaryNode() != null && previousNode.getRightBinaryNode().getData().equals(current.getData()))
                    previousNode.setRightBinaryNode(null);
                if(current.equals(previousNode))
                    rootNode = null;
                treeSize--;
                return true;
            }
        }
        // if node has not been found and nothing was removed
        return false;
    }

    /**
     * The successor of the current node will always be on the left and whatever
     * the right most node of it. (when this is called there is two children and the bigger value will be
     * on the right of the current node which makes it the successor)
     *
     * @param current node to find a successor for
     * @return current node's leftBinaryNode's most right node
     */
    private BinaryNode<Type> findSuccessor(BinaryNode<Type> current) {
        return current.getLeftBinaryNode().getRightmost();
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
        if (rootNode != null) {
            recArrayList(sorted, rootNode);
        }
        return sorted;
    }

    /**
     * recursive search for adding into an array. Order can be changed by moving the recursive calls within the method.
     *
     * @param arr         array to add to
     * @param currentNode current node for recursive function to use
     */
    private void recArrayList(ArrayList<Type> arr, BinaryNode<Type> currentNode) {
        if (currentNode != null) {
            // adds the smallest items first
            recArrayList(arr, currentNode.getLeftBinaryNode());
            arr.add(currentNode.getData());
            recArrayList(arr, currentNode.getRightBinaryNode());
        }
    }

    /**
     * BinaryNode Class that resides within the BinarySearch Tree Class and a BinaryNode
     * holds a data type, reference to a LeftBinaryNode and RightBinaryNode
     *
     * @param <T> Generic Object Type
     */
    public static class BinaryNode<T> {
        private T data;
        private BinaryNode<T> leftBinaryNode;
        private BinaryNode<T> rightBinaryNode;

        /**
         * BinaryNode Constructor with parameters
         *
         * @param nodeData  the data this Binary Node will store (int, String, double,...etc (any data type))
         * @param leftNode  left Binary Node of this Binary Node
         * @param rightNode right Binary Node of this Binary Node
         */
        public BinaryNode(T nodeData, BinaryNode<T> leftNode, BinaryNode<T> rightNode) {
            data = nodeData;
            leftBinaryNode = leftNode;
            rightBinaryNode = rightNode;
        }

        /**
         * BinaryNode Constructor with one parameter
         *
         * @param nodeData The data type/just data that this BinaryNode created will store
         */
        public BinaryNode(T nodeData) {
            data = nodeData;
            leftBinaryNode = null;
            rightBinaryNode = null;
        }

        /**
         * @return the right BinaryNode of this node
         */
        public BinaryNode<T> getRightBinaryNode() {
            return rightBinaryNode;
        }

        /**
         * sets the right BinaryNode of this node
         */
        public void setRightBinaryNode(BinaryNode<T> node) {
            rightBinaryNode = node;
        }

        /**
         * @return the left BinaryNode of this node
         */
        public BinaryNode<T> getLeftBinaryNode() {
            return leftBinaryNode;
        }

        /**
         * sets the left BinaryNode of this node
         */
        public void setLeftBinaryNode(BinaryNode<T> node) {
            leftBinaryNode = node;
        }

        /**
         * @return the data of this node
         */
        public T getData() {
            return data;
        }

        /**
         * sets the data of this node
         *
         * @param item of Generic type attached to this node (gonna be the data of this node)
         */
        public void setData(T item) {
            data = item;
        }

        /**
         * loop through the right side of the nodes from this node (current node)
         * and when the right side of the current node is null just
         * return the current node
         *
         * @return the right most node in reference to this node
         */
        public BinaryNode<T> getRightmost() {
            BinaryNode<T> current = this;
            while (current.getRightBinaryNode() != null) {
                current = current.getRightBinaryNode();
            }
            return current;
        }

        /**
         * loop through the left side of the nodes from this node (current node)
         * and when the left side of the current node is null just
         * return the current node
         *
         * @return the left most node in reference to this node
         */
        public BinaryNode<T> getLeftmost() {
            BinaryNode<T> current = this;
            while (current.getLeftBinaryNode() != null) {
                current = current.getLeftBinaryNode();
            }
            return current;
        }
    }

}

