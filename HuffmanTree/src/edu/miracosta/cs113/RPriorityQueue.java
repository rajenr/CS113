package edu.miracosta.cs113;

/**
 * RPriorityQueue.java (WIP)
 * This class contains the methods for RPriorityQueue, the author's implementation of Java's built-in
 * Priority Queue data structure.
 *
 * @author Reesha Rajen
 * @version 1.0
 */

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class RPriorityQueue<E> extends AbstractQueue<E> implements Queue<E> {

    // Data fields
    /** The ArrayList to hold the data. */
    private ArrayList<E> theData;
    /** An optional reference to a Comparator object. */
    Comparator<E> comparator = null;

    // Methods

    /**
     * DESCRIPTION: Default constructor for RPriorityQueue.
     */
    public RPriorityQueue() {
        theData = new ArrayList<E>();
    }

    /**
     * Returns an iterator over the elements contained in this collection.
     *
     * @return Iterator object
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * Returns the size of the priority queue.
     *
     * @return Integer indicating size of priority queue.
     */
    @Override
    public int size() {
        return 0;
    }


    /**
     * Insert an item into the priority queue.
     * pre: The ArrayList theData is in heap ordering.
     * post: The item is in the priority queue and theData is in heap order.
     *
     * @param item The item to be inserted
     * @throws NullPointerException if the item to be inserted is null.
     * @return True if successful, false if the item could not be inserted.
     */
    @Override
    public boolean offer(E item) {
        theData.add(item); // Add the item to the heap.
        int child = theData.size() - 1; // Child is newly inserted item.
        int parent = (child - 1)/2; // Find child's parent.

        while (parent >= 0 && compare(theData.get(parent), theData.get(child)) > 0) {
            swap(parent, child);
            child = parent;
            parent = (child - 1) / 2;
        }
        return true;
    }

    private void swap(int parent, int child) {
    }

    private int compare(E left, E right) {
        if (comparator != null) {
            return comparator.compare(left, right);
        }
        return 0;
    }

    /**
     * Removes the entry at the front of the queue and returns it if the queue is not empty. If the queue is empty,
     * throws a NoSuchElement exception.
     *
     * @return The entry at the front of the queue.
     */
    @Override
    public E remove() {
        return null;
    }

    /**
     * Remove an item from the priority queue.
     * pre: The ArrayList theData is in heap order.
     * post Removed smallest item, theData is in heap order.
     *
     * @return The item with the smallest priority value or null if empty.
     */
    @Override
    public E poll() {
        return null;
    }

    /**
     * Returns the entry at the front of the queue without removing it; returns null if the queue is empty.
     *
     * @return The entry at the front of the queue.
     */
    @Override
    public E peek() {
        return null;
    }

    /**
     * Returns the entry at the front of the queue without removing it. If the queue is empty, throws a
     * NoSuchElementException.
     *
     * @return The entry at the front of the queue.
     */
    @Override
    public E element() {
        return null;
    }
}
