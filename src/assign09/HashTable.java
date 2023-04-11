package assign09;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * This is a hashTable that implements the Map Interface and uses an Arraylist for the
 * data structure for its uses.
 * Uses separate chaining, and the capacity of the table need not be a prime number.
 * Further, the load factor λ for our hashTable is set at 5 with an initial backing array
 * size of 100 doubling the size everytime the load factor is exceeded 5 while almost guaranteeing it's not a prime number,
 * so it's not simply just double the size, but it starts off that way and adjusts as needed.
 * <p>
 * load factor, arrSize variable can be changed easily for timings
 *
 * @param <K> Generic Type for Key for map entry
 * @param <V> Generic Type for value for map entry
 *
 * @author Philippe Gonzalez and Conner Francis
 * @version April 4, 2023
 */
public class HashTable<K, V> implements Map<K, V>{

    private ArrayList<LinkedList<MapEntry<K, V>>> hashTable;
    private int collisions = 0;
    // Size of backing array & starting array size that can be changed for timings
    protected int arrSize = 100;
    // Total amount of items in the table
    private int size;
    private final double loadFactor = 5.0;

    /**
     * Constructor for the hashtable
     */
    public HashTable() {
        hashTable = new ArrayList<>(arrSize);
        size = 0;
            for(int i = 0; i < arrSize; i++) {
                hashTable.add(new LinkedList<>());
            }
    }

    protected int getCollisions(){
        return collisions;
    }

    /**
     * compression function for getting a array position from the hash code
     * @param key
     * @return
     */
    private int compressor(K key) {
        int hash = key.hashCode();
        if(key.hashCode() < 0) {
            hash = hash * -1;
        }
        return hash % arrSize;
    }

    /**
     * Removes all mappings from this map.
     * <p>
     * O(table length) for quadratic probing or separate chaining
     */
    @Override
    public void clear() {
        hashTable.clear();
        size = 0;
        arrSize = 100;
        for(int i = 0; i < arrSize; i++) {
            hashTable.add(new LinkedList<>());
        }
    }

    /**
     * Private helper method to grow the backing array when the load gets past the defined load factor
     * O(n)
     */
    private void grow() {
        List<MapEntry<K, V>> items = this.entries();
        BigInteger i = new BigInteger(String.valueOf(arrSize * 2));
        int newSize = i.nextProbablePrime().intValue() + 1;
        ArrayList<LinkedList<MapEntry<K,V>>> temp = new ArrayList<>(newSize);
        for(int l = 0; l < newSize; l++) {
            temp.add(new LinkedList<>());
        }
        hashTable = temp;
        arrSize = newSize;
        size = 0;
        for (var item : items) {
            this.put(item.getKey(),item.getValue());
        }

    }

    /**
     * Determines whether this map contains the specified key.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key
     * @return true if this map contains the key, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        LinkedList<MapEntry<K, V>> bucket = hashTable.get(compressor(key));
        if (bucket != null)
            for (var i : bucket)
                if (i.getKey().equals(key))
                    return true;
        return false;
    }

    /**
     * Determines whether this map contains the specified value.
     * <p>
     * O(table length) for quadratic probing
     * O(table length + N) for separate chaining
     *
     * @param value
     * @return true if this map contains one or more keys to the specified value,
     * false otherwise
     */
    @Override
    public boolean containsValue(V value) {
        for(int i = 0 ; i < arrSize ; i++) {
            LinkedList<MapEntry<K, V>> bucket = hashTable.get(i);
            for (var l : bucket)
                if (l.getValue().equals(value))
                    return true;
        }
        return false;
    }

    /**
     * Returns a List view of the mappings contained in this map, where the ordering of
     * mapping in the list is insignificant.
     * <p>
     * O(table length) for quadratic probing
     * O(table length + N) for separate chaining
     *
     * @return a List object containing all mapping (i.e., entries) in this map
     */
    @Override
    public List<MapEntry<K, V>> entries() {
        ArrayList<MapEntry<K, V>> temp = new ArrayList<>(arrSize);
       for(int i = 0 ; i < arrSize ; i++) {
           LinkedList<MapEntry<K, V>> bucket = hashTable.get(i);
           temp.addAll(bucket);
       }
        return temp;
    }

    /**
     * Gets the value to which the specified key is mapped.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key
     * @return the value to which the specified key is mapped, or null if this map
     * contains no mapping for the key
     */
    @Override
    public V get(K key) {
        LinkedList<MapEntry<K, V>> bucket = hashTable.get(compressor(key));
        for (var current : bucket) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
        }
        return null;
    }

    /**
     * Determines whether this map contains any mappings.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @return true if this map contains no mappings, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * (I.e., if the key already exists in this map, resets the value;
     * otherwise adds the specified key-value pair.)
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key
     * @param value
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V put(K key, V value) {
        if(size/arrSize >= loadFactor) {
            this.grow();
        }
            LinkedList<MapEntry<K, V>> bucket = hashTable.get(compressor(key));
            for (var current : bucket) {
                collisions++;
                if (current.getKey().equals(key)) {
                    V temp = current.getValue();
                    current.setValue(value);
                    return temp;
                }
        }
        bucket.add(new MapEntry<K,V>(key,value));
        size++;
        return null;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V remove(K key) {
        Iterator<MapEntry<K, V>> i = hashTable.get(compressor(key)).iterator();
        while(i.hasNext()) {
            MapEntry<K, V> current = i.next();
            if(current.getKey().equals(key)) {
                V item = current.getValue();
                i.remove();
                size--;
                return item;
            }
        }
        return null;
    }

    /**
     * Determines the number of mappings in this map.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @return the number of mappings in this map
     */
    @Override
    public int size() {
        return size;
    }
}
