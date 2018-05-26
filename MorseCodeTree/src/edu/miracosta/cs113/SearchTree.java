package edu.miracosta.cs113;

/**
 * SearchTree.java
 * SearchTree interface
 *
 * @version 1.0
 * @author Reesha Rajen
 */
public interface SearchTree<E> {

    /**
     * Inserts item where it belongs in the tree
     * @param item
     * @return true if item is inserted, false if it isn't (already in tree)
     */
    boolean add(E item);

    /**
     * Returns true if target is found in the tree
     * @param target
     * @return true if target is found in the tree
     */
    boolean contains(E target);

    /**
     * Returns a reference to the data in the node that is equal to target. If no such node is found, returns null.
     * @param target
     * @return
     */
    E find(E target);

    /**
     * Removes target (if found) from tree and returns it; otherwise, returns null.
     * @param target
     * @return
     */
    E delete(E target);

    /**
     * Removes target (if found) from tree and returns true; otherwise, returns false.
     * @param target
     * @return
     */
    boolean remove(E target);

}
