package edu.miracosta.cs113;

/**
 * HashTableChain.java
 * This class is the completed HashTableChain class from the lecture/Section 7.4. It fully implements the Map
 * interface from java.util.Map, with putAll() and values() methods omitted. This class contains the inner class
 * implementations of Entry, EntrySet, and SetIterator as described in Section 7.5. The SetIterator behaves as
 * iterators do, providing methods hasNext(), next(), data fields of index and lastItemReturned, as well as
 * remove().
 *
 * @version 1.0
 * @author Reesha Rajen
 *
 * @param <K> The type of keys maintained by this map.
 * @param <V> The type of mapped values.
 */

import java.util.*;

/** Hash table implementation using chaining. */
public class HashTableChain<K, V> implements MapInterface<K, V> {

    // CONSTANTS
    /** The capacity */
    private static int CAPACITY = 101;
    /** The maximum load factor */
    private static double LOAD_THRESHOLD = 3.0;

    // INSTANCE VARIABLES
    /** The table */
    private LinkedList<Entry<K, V>>[] table;
    /** The number of keys */
    private int numKeys;

    /**
     * DESCRIPTION:    Default constructor for a HashTableChain object.
     * PRE-CONDITION:  N/A.
     * POST-CONDITION: Creates a new LinkedList of Entry objects.
     */
    public HashTableChain() {
        table = new LinkedList[CAPACITY];
    }

    /**
     * DESCRIPTION:    Two arg custom constructor for a HashTableObject.
     * PRE-CONDITION:  N/A.
     * POST-CONDITION: Creates a new LinkedList of Entry objects.
     *
     * @param start The value of the new CAPACITY
     * @param load The value of the new LOAD_THRESHOLD
     */
    @SuppressWarnings("unchecked")
    public HashTableChain(int start, double load) {
        CAPACITY = start;
        LOAD_THRESHOLD = load;
        table = new LinkedList[CAPACITY];
    }

    /**
     * DESCRIPTION:    Returns a string containing all key-value entries within the HashTableChain object.
     * PRE-CONDITION:  Assumes a HashTableChain object exists with key-value entries.
     * POST-CONDITION: N/A.
     *
     * @return String containing all key-value entries.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.table.length; i++) {
            if (table[i] != null) {
                stringBuilder.append(i + ": ");

                for (Entry<K, V> pair : table[i]) {
                    stringBuilder.append(pair.toString() + " ");
                }
                stringBuilder.append("\n");
            }
        }
        return stringBuilder + "";
    }

    /**
     * DESCRIPTION:    Compares the specified object with this map for equality. Returns true if the given object
     *                 is also a map and the two maps represent the same mappings. More formally, two maps m1 and m2
     *                 represent the same mappings if m1.entrySet().equals(m2.entrySet()). This ensures that the
     *                 equals method works properly across different implementations of the Map interface.
     * PRE-CONDITION:  Assumes a map object exists with key-value pairs.
     * POST-CONDITION: Returns a boolean value.
     *
     * @param o Specified object representing a Map.
     * @returntrue if the specified object is equal to this map.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Map)) {
            return false;
        }

        Map<K, V> t = (Map<K, V>) o;
        if (t.size() != size()) {
            return false;
        }

        try {
            for (int i = 0; i < this.table.length; i++) {
                if (table[i] != null) {
                    for (Entry<K, V> e : table[i]) {
                        K key = e.getKey();
                        V value = e.getValue();
                        if (value == null) {
                            if (!(t.get(key) == null && t.containsKey(key)))
                                return false;
                        } else {
                            if (!value.equals(t.get(key)))
                                return false;
                        }
                    }
                }
            }
        } catch (ClassCastException cce)   {
            return false;
        }
        return true;
    }

    /**
     * @Nery This method is not included in java.util.Map
     *
     * DESCRIPTION:    Compares the specified object with this object for equality.
     * PRE-CONDITION:  Assumes a map object exists with key-value pairs.
     * POST-CONDITION: Returns a boolean value.
     *
     * @param o Specified object representing a key-value pair.
     * @returntrue if the specified object is equal to this key-value pair.
     */
    public boolean equalsPair(Object o) {
        if(! (o instanceof MapInterface)) {
            return false;
        } else {
            HashTableChain e = (HashTableChain) o;

            for (int i = 0; i < e.table.length; i++) {
                if (e.table[i] != null) {
                    for (Entry<K, V> pair : this.table[i]) {
                        if (pair.getKey().equals(e.table[i].get(i))) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    /**
     * DESCRIPTION:    Returns the hash code value for this map. The hash code of a map is defined to be the
     *                 sum of the hash codes of each entry in the map's entrySet() view. This ensures that
     *                 m1.equals(m2) implies that m1.hashCode()==m2.hashCode() for any two maps m1 and m2, as
     *                 required by the general contract of Object.hashCode().
     * PRE-CONDITION:  Assumes a map object exists with key-value pairs.
     * POST-CONDITION: Returns an integer value.
     *
     * @return The hash code value for this map.
     */
    public int hashCode() {
        int hashCode = 0;

        for (int i = 0; i < table.length; i++) {
            if (this.table != null && this.table[i] != null) {
                for (Entry<K, V> pair : table[i]) {
                    hashCode += pair.key.hashCode();
                }
            }

        }
        return hashCode;
    }

    /**
     * DESCRIPTION:    Removes all of the mappings from this map (optional operation). The map will be empty after
     *                 this call returns.
     * PRE-CONDITION:  Assumes a map object exists.
     * POST-CONDITION: Returns an empty map object.
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.table.length; i++) {
            if (this.table != null && this.table[i] != null) {
                for (Entry<K, V> pair : table[i]) {
                    table[i].remove(pair);
                    this.numKeys--;
                }
            }
        }
    }

    /**
     * DESCRIPTION:    Returns true if this map contains a mapping for the specified key. More formally, returns
     *                 true if and only if this map contains a mapping for a key k such that
     *                 (key==null ? k==null : key.equals(k)). (There can be at most one such mapping.)
     * PRE-CONDITION:  Assumes a map object exists and contains key-value pairs.
     * POST-CONDITION: Returns a boolean indicating if key is within map.
     *
     * @param key Key whose presence in this map is to be tested.
     * @return True if this map contains a mapping for the specified key.
     */
    @Override
    public boolean containsKey(Object key) {
        int index = key.hashCode() % this.table.length;

        if (index < 0) {
            index += this.table.length;
        }

        for (Entry<K, V> nextItem : this.table[index]) {
            if (nextItem.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * DESCRIPTION:    Returns true if this map maps one or more keys to the specified value. More formally,
     *                 returns true if and only if this map contains at least one mapping to a value v such that
     *                 (value==null ? v==null : value.equals(v)). This operation will probably require time linear
     *                 in the map size for most implementations of the Map interface.
     * PRE-CONDITION:  Assumes a map object exists and contains key-value pairs.
     * POST-CONDITION: Returns a boolean indicating if value is within map.
     *
     * @param value Value whose presence in this map is to be tested.
     * @return True if this map maps one or more keys to the specified value.
     */
    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < this.table.length; i++) {
            if (this.table != null && this.table[i] != null) {
                for (Entry<K, V> pair : table[i]) {
                    if (pair.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * DESCRIPTION:    Returns a Set view of the mappings contained in this map. The set is backed by the map,
     *                 so changes to the map are reflected in the set, and vice-versa. If the map is modified while
     *                 an iteration over the set is in progress (except through the iterator's own remove operation,
     *                 or through the setValue operation on a map entry returned by the iterator) the results of the
     *                 iteration are undefined. The set supports element removal, which removes the corresponding
     *                 mapping from the map, via the Iterator.remove, Set.remove, removeAll, retainAll and clear
     *                 operations. It does not support the add or addAll operations.
     * PRE-CONDITION:  Assumes a map object exists with values.
     * POST-CONDITION: Returns a set view of the mappings contained in this map.
     *
     * @return Set containing all mappings within the map.
     */
    @Override
    public Set entrySet() {
        return new EntrySet();
    }

    /**
     * DESCRIPTION:    Returns the value to which the specified key is mapped, or null if this map contains no
     *                 mapping for the key. More formally, if this map contains a mapping from a key k to a value v
     *                 such that (key==null ? k==null : key.equals(k)), then this method returns v; otherwise it
     *                 returns null. (There can be at most one such mapping.) If this map permits null values, then a
     *                 return value of null does not necessarily indicate that the map contains no mapping for the key;
     *                 it's also possible that the map explicitly maps the key to null. The containsKey operation may
     *                 be used to distinguish these two cases.
     * PRE-CONDITION:  Assumes a HashTableChain object exists and contains values.
     * POST-CONDITION: Returns the value to which the specified key is mapped.
     *
     * @param key The key whose associated value is to be returned.
     * @return The value to which the specified key is mapped, or null if this map contains no mapping for the key.
     */
    @Override
    public V get(Object key) {
        int index = key.hashCode() % this.table.length;

        if (index < 0) {
            index += this.table.length;
        }
        if (this.table[index] == null) {
            return null; // Key is not in the table
        }
        // Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : this.table[index]) {
            if (nextItem.key.equals(key)) {
                return nextItem.value;
            }
        }
        // Assert: key is not in table
        return null;
    }

    /**
     * DESCRIPTION:    Returns true if this map contains no key-value mappings.
     * PRE-CONDITION:  Assumes creation of a HashTableChain object.
     * POST-CONDITION: Boolean indicating if map is empty.
     *
     * @return True if the map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return this.numKeys == 0;
    }

    /**
     * DESCRIPTION:    Returns a Set view of the keys contained in this map. The set is backed by the map,
     *                 so changes to the map are reflected in the set, and vice-versa. If the map is modified
     *                 while an iteration over the set is in progress (except through the iterator's own remove
     *                 operation), the results of the iteration are undefined. The set supports element removal,
     *                 which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove,
     *                 removeAll, retainAll, and clear operations. It does not support the add or addAll operations.
     * PRE-CONDITION:  N/A.
     * POST-CONDITION: Returns the set view of the keys contained in this map
     *
     * @return A set view of keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>(size());
        for (int i = 0; i < this.table.length; i++) {
            if (this.table != null && this.table[i] != null) {
                for (Entry<K, V> pair : table[i]) {
                    if (pair != null) {
                        keySet.add(pair.getKey());
                    }
                }
            }
        }
        return keySet;
    }

    /**
     * DESCRIPTION:    Associates the specified value with the specified key in this map (optional operation).
     *                 If the map previously contained a mapping for the key, the old value is replaced by the
     *                 specified value. (A map m is said to contain a mapping for a key k if and only if
     *                 m.containsKey(k) would return true.)
     * PRE-CONDITION:  Assumes a HashTableChain object is created and contains key-value pairs.
     * POST-CONDITION: Returns the value associated with the key.
     *
     * @param key Key with which the specified value is to be associated.
     * @param value Value to be associated with the specified key.
     * @return The previous value associated with key, or null if there was no mapping for key. A null return can
     *         also indicate that the map previously associated null with key, if the implementation supports
     *         null values.
     */
    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;

        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        // Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            // If the search is successful, replace the old value.
            if (nextItem.key.equals(key)) {
                // Replace value for this key.
                V oldVal = nextItem.value;
                nextItem.setValue(value);
                return oldVal;
            }
        }

        // assert: key is not in the table, add new item.
        table[index].addFirst(new Entry<>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length)) {
            rehash();
        }
        return null;
    }

    /**
     * DESCRIPTION:    Increases the capacity of and internally reorganizes this
     *                 hashtable, in order to accommodate and access its entries more
     *                 efficiently.  This method is called automatically when the
     *                 number of keys in the hashtable exceeds this hashtable's capacity
     *                 and load factor.
     * PRE-CONDITION:  Assumes that number of keys found in HashTableChain exceeds the load threshold
     *                 multiplied by the length of the table.
     * POST-CONDITION: Returns the rehashed table.
     */
    private void rehash() {
        LinkedList<Entry<K,V>>[] rehashed = new LinkedList[2 * this.table.length + 1];
        int index;
        for (LinkedList<Entry<K,V>> list : table) {
            if (list != null) {
                for (Entry<K,V> pair : list) {
                    if (pair != null) {
                        index = pair.key.hashCode() % rehashed.length;
                        if (index < 0) {
                            index += rehashed.length;
                        }
                        if (rehashed[index] == null) {
                            // Create a new LinkedList at table[index]
                            rehashed[index] = new LinkedList<>();
                        }
                        // Assert: key is not in the table, add new item.
                        rehashed[index].addFirst(new Entry<>(pair.key, pair.value));
                    }
                }
            }
        }
        table = rehashed;
    }

    /**
     * DESCRIPTION:    Removes the mapping for a key from this map if it is present (optional operation). More
     *                 formally, if this map contains a mapping from key k to value v such that
     *                 (key==null ? k==null : key.equals(k)), that mapping is removed. (The map can contain at most
     *                 one such mapping.) Returns the value to which this map previously associated the key, or null
     *                 if the map contained no mapping for the key. If this map permits null values, then a return
     *                 value of null does not necessarily indicate that the map contained no mapping for the key;
     *                 it's also possible that the map explicitly mapped the key to null.The map will not contain a
     *                 mapping for the specified key once the call returns.
     * PRE-CONDITION:  Assumes the HashTableChain object has been created and contains key-value pairs.
     * POST-CONDITION: Returns previous value associated with the key removed.
     *
     * @param key Key whose mapping is to be removed from the map.
     * @return The previous value associated with key, or null if there was no mapping for key.
     */
    @Override
    public V remove(Object key) {
        int index = key.hashCode() % this.table.length;

        if (index < 0) {
            index += this.table.length;
        }
        if (table[index] == null) {
            return null;
        }

        for (Entry<K, V> pair : table[index]) {
            if (pair.getKey().equals(key)) {
                table[index].remove(pair);
                this.numKeys--;

                if (table[index].isEmpty()) {
                    table[index] = null;
                }
                return pair.getValue();
            }
        }
        return null;
    }

    /**
     * DESCRIPTION:    Returns the number of key-value mappings in this map. If the map contains more than
     *                 Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * PRE-CONDITION:  N/A.
     * POST-CONDITION: Returns the number of key-value mappings in this map.
     *
     * @return The number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return this.numKeys;
    }

    /** Inner class to implement the SetIterator for HashTableChain. */
    private class SetIterator implements Iterator<Entry<K, V>> {

        /** Data field index to keep track of the next value of the  iterator (initially 0). */
        int index = 0;
        /** Data field keeping track of the index of the last item returned by next(). This is used by remove(). */
        Iterator<Entry<K, V>> lastItemReturned = null;

        /**
         * DESCRIPTION:    Returns true if the iteration has more elements.
         *                 (In other words, returns true if next() would return an element rather than throwing
         *                 an exception.)
         * PRE-CONDITION:  N/A.
         * POST-CONDITION: Returns a boolean indicating if the iteration has more elements to return.
         *
         * @return True if the iteration has more elements.
         */
        @Override
        public boolean hasNext() {
            if (lastItemReturned != null) {
                if (lastItemReturned.hasNext()) {
                    return true;
                } else {
                    lastItemReturned = null;
                    index++;
                }
            }
            while (index < table.length && table[index] == null) {
                index++;
            }
            if (index == table.length) {
                return false;
            }
            lastItemReturned = table[index].iterator();
            return lastItemReturned.hasNext();
        }

        /**
         * DESCRIPTION:    Returns the next element in the iteration.
         * PRE-CONDITION:  N/A.
         * POST-CONDITION: Returns the next element in the iteration.
         *
         * @return The next element in the iteration.
         */
        @Override
        public Entry<K, V> next() {
            if (lastItemReturned != null) {
                if (lastItemReturned.hasNext()) {
                    return lastItemReturned.next();
                } else {
                    lastItemReturned = null;
                    index++;
                }
            }
            while (index < table.length && table[index] == null) {
                index++;
            }
            if (index == table.length) {
                return null;
            } else {
                lastItemReturned = table[index].iterator();
                return lastItemReturned.next();
            }
        }

        /**
         * DESCRIPTION:    Removes from the underlying collection the last element returned by this iterator
         *                 (optional operation). This method can be called only once per call to next(). The
         *                 behavior of an iterator is unspecified if the underlying collection is modified while
         *                 the iteration is in progress in any way other than by calling this method.
         * PRE-CONDITION:  N/A.
         * POST-CONDITION: N/A.
         */
        @Override
        public void remove() {
            if (lastItemReturned != null) {
                lastItemReturned.remove();
                numKeys--;

                if (table[index].isEmpty()) {
                    table[index] = null;
                }
            }
        }
    } // END of inner class SetIterator.

    /** Inner class to implement the EntrySet for HashTableChain. */
    private class EntrySet extends AbstractSet<Entry<K, V>> {

        /**
         * DESCRIPTION:    Returns an iterator object to iterate over the entries in the set.
         * PRE-CONDITION:  Assumes that a HashTableChain object exists and contains key-value pairs.
         * POST-CONDITION: Returns iterator object.
         *
         * @return An iterator over the elements contained in this collection.
         */
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new SetIterator();
        }

        /**
         * DESCRIPTION:    Returns the size of the set of entries.
         * PRE-CONDITION:  N/A.
         * POST-CONDITION: Returns integer containing the number of entries in the set.
         *
         * @return The number of entries.
         */
        @Override
        public int size() {
            return numKeys;
        }
    } // END of inner class EntrySet.


    /** Contains key-value pairs for a hash table. */
    private static class Entry<K, V> {

        /** The key */
        private K key;
        /** The value */
        private V value;

        /**
         * Creates a new key-value pair.
         *
         * @param key   The key.
         * @param value The value.
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key.
         *
         * @return The key.
         */
        public K getKey() {
            return this.key;
        }

        /**
         * Retrieves the value.
         *
         * @return The value.
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Sets the value.
         *
         * @param val The new value.
         * @return The old value.
         */
        public V setValue(V val) {
            V oldVal = this.value;
            this.value = val;
            return oldVal;
        }

        /**
         * DESCRIPTION: A String representation of an Entry object.
         *
         * @return String object containing all instance variables of an Entry object.
         */
        public String toString() {
            return "(KEY) " + this.getKey() + " (VALUE) " + this.getValue();
        }

    } // END of inner class Entry.
}
