package edu.miracosta.cs113;

/**
 * HashTableOpen.java
 * This class is the completed HashTableOpen class from Section 7. It fully implements the KWHashMap
 * interface from the book. This class contains the inner class implementations of Entry.
 *
 * @version 1.0
 * @author Reesha Rajen
 */

public class HashTableOpen<K,V> implements KWHashMap<K, V> {

    private static final int START_CAPACITY = 101;

    private Entry<K, V>[] table;
    private double LOAD_THRESHOLD = 0.75;
    private int numKeys;
    private int numDeletes;
    private final Entry<K, V> DELETED = new Entry<K, V>(null, null);

    public HashTableOpen() {
        this.table = new Entry[START_CAPACITY];
    }

    private int find(Object key) {
        //Calculate the starting index
        System.out.println("Here is the hash code: " + key.hashCode());
        int index = key.hashCode() % table.length;
        System.out.println("Here is the hash code mod table size: " + index);
        if (index < 0) {
            index += table.length; // Make it positive.
        }
            // Increment index until an empty slot is reached
            // or the key is found.
        while ((table[index] != null) && (!key.equals(table[index].key))) {
            index++;
            // Check for wraparound.
            if (index >= table.length) {
                index = 0; // Wrap around.
            }
        }
        return index;
    }

    /**
     * DESCRIPTION: Compares the specified object with this map for equality.
     *
     * @param key Object containing the specified key.
     * @return Returns value to which the specified key is mapped.
     */
    @Override
    public V get(Object key) {
        //Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);

        // If the search is successful, return the value.
        if (table[index] != null) {
            return table[index].value;
        } else {
            return null; // Key was not found.
        }
    }

    /**
     * DESCRIPTION: Returns true if this map contains no key-value mappings.
     *
     * @return True if the map contains no key-value mappings. Else, returns false.
     */
    @Override
    public boolean isEmpty() {
        return this.numKeys == 0;
    }

    /**
     * DESCRIPTION: Associates the specified value with the specified key in this map. Optional operation.
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public V put(K key, V value) {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);
        System.out.println("TABLE INDEX: " + index);

        // If an empty element was found, insert new entry.
        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            numKeys++;
            // Check whether rehash is needed.
            double loadFactor = (double) (numKeys + numDeletes) / table.length;

            if (loadFactor > LOAD_THRESHOLD) {
                rehash();
            }
            return null;
        }

        // assert: table element that contains the key was found.
        // Replace value for this key.
        V oldVal = table[index].value;
        table[index].value = value;
        return oldVal;
    }

    private void rehash() {
        // Save a reference to oldTable.
        Entry<K, V>[] oldTable = table;
        // Double capacity of this table.
        table = new Entry[ 2 * oldTable.length + 1 ];

        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        numDeletes = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if ((oldTable[i] != null) && oldTable[i] != DELETED) {
                // Insert entry into expanded table
                put(oldTable[i].key, oldTable[i].value);
            }
        }
    }

    /**
     * DESCRIPTION: Removes the mapping for a key from this map if it is present. Optional operation.
     *
     * @param key
     * @return
     */
    @Override
    public V remove(Object key) {
        int index = find(key);

        if (table[index] == null) {
            return null;
        } else {
            V oldValue = table[index].getValue();
            table[index] = DELETED;

            numKeys--;
            numDeletes++;
            return oldValue;
        }
    }

    /**
     * DESCRIPTION: Returns the number of key-value mappings in this map.
     *
     * @return
     */
    @Override
    public int size() {
        return this.numKeys;
    }

    /** Contains key-value pairs for a hash table. */
    private static class Entry<K, V> {

        /** The key */
        private K key;
        /** The value */
        private V value;

        /**
         * Creates a new key-value pair.
         * @param key The key.
         * @param value The value.
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key.
         * @return The key.
         */
        public K getKey() {
            return this.key;
        }

        /**
         * Retrieves the value.
         * @return The value.
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Sets the value.
         * @param val The new value.
         * @return The old value.
         */
        public V setValue(V val) {
            V oldVal = this.value;
            this.value = val;
            return oldVal;
        }

    }
}
