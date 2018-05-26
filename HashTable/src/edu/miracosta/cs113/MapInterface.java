package edu.miracosta.cs113;

/**
 * MapInterface.java
 *
 * @author Reesha Rajen
 * @version 1.0
 *
 * An object that maps keys to values. A map cannot contain duplicate keys; each key can map to at most one value.
 * This interface takes the place of the Dictionary class, which was a totally abstract class rather than an interface.
 * The Map interface provides three collection views, which allow a map's contents to be viewed as a set of keys,
 * collection of values, or set of key-value mappings. The order of a map is defined as the order in which the iterators
 * on the map's collection views return their elements. Some map implementations, like the TreeMap class, make specific
 * guarantees as to their order; others, like the HashMap class, do not.
 *
 * @param <K> The type of keys maintained by this map.
 * @param <V> The type of mapped values.
 */

import java.util.Set;

public interface MapInterface<K, V> {

    /** Removes all of the mappings from this map (optional operation). */
    void clear();

    /** Returns true if this map contains a mapping for the specified key. */
    boolean containsKey(Object key);

    /** Returns true if this map maps one or more keys to the specified value. */
    boolean containsValue(Object value);

    /** Returns a Set view of the mappings contained in this map. */
    Set entrySet();

    /** Compares the specified object with this map for equality. */
    boolean equals(Object o);

    /** Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key. */
    V get(Object key);

    /** Returns the hash code value for this map. */
    int hashCode();

    /** Returns true if this map contains no key-value mappings. */
    boolean isEmpty();

    /** Returns a Set view of the keys contained in this map. */
    Set<K> keySet();

    /** Associates the specified value with the specified key in this map (optional operation). */
    V put(K key, V value);

    /** Removes the mapping for a key from this map if it is present (optional operation). */
    V remove(Object key);

    /** Returns the number of key-value mappings in this map. */
    int size();

    /** Method not included in actual Java interface, specifies if key-value pair is equal to another. */
    boolean equalsPair(Object o);

}
