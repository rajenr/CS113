package edu.miracosta.cs113;

/**
 * Queue.java
 * This is the Queue interface that RPriorityQueue follows, as well as Java's built in Priority Queue.
 *
 * @param <E> Generic data type.
 * @author Reesha Rajen
 * @version 1.0
 */
public interface Queue<E> {

    /**
     * Inserts item at the rear of the queue. Returns true if successful; returns false if the item could not be inserted.
     * @param item Item to be inserted in the queue.
     * @return True if successful, false if the item could not be inserted.
     */
    boolean offer(E item);

    /**
     * Removes the entry at the front of the queue and returns it if the queue is not empty. If the queue is empty,
     * throws a NoSuchElement exception.
     * @return The entry at the front of the queue.
     */
    E remove();

    /**
     * Removes the entry at the front of the queue and returns it; returns null if the queue is empty.
     * @return The entry at the front of the queue.
     */
    E poll();

    /**
     * Returns the entry at the front of the queue without removing it; returns null if the queue is empty.
     * @return The entry at the front of the queue.
     */
    E peek();

    /**
     * Returns the entry at the front of the queue without removing it. If the queue is empty, throws a
     * NoSuchElementException.
     * @return The entry at the front of the queue.
     */
    E element();

    int size();

}
