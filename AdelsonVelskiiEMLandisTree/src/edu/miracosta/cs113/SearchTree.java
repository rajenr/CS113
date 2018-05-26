package edu.miracosta.cs113;

/**
 * SearchTree.java
 * This is the interface that contains all necessary methods for a SearchTree.
 *
 * @version 2.0
 * @author Reesha Rajen
 *
 * @param <E> Generic type.
 */
public interface SearchTree<E> {
    /**
     * Inserts item to where it belongs in the tree.
     *
     * @param item The item to be added to the tree.
     * @return True if item is inserted, false if it isn't (already in tree)
     */
    boolean add(E item);

    /**
     * Returns true if target is found in the tree.
     *
     * @param target The item to check for.
     * @return True if target is found in the tree.
     */
    boolean contains(E target);

    /**
     * Returns a reference to the data in the node that is equal to target. If no such node is found, returns null.
     *
     * @param target The item to search for in the tree.
     * @return Object if found, null if not found.
     */
    E find(E target);

    /**
     * Removes target (if found) from tree and returns it; otherwise, returns null.
     *
     * @param target The item to delete in the tree.
     * @return The object if found, null if not found.
     */
    E delete(E target);

    /**
     * Removes target (if found) from tree and returns true; otherwise, returns false.
     *
     * @param target The item to remove.
     * @return Boolean indicating if object was able to be removed from the tree.
     */
    boolean remove(E target);
}
