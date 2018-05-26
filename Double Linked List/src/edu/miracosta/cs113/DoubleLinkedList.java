package edu.miracosta.cs113;

/**
 * DoubleLinkedList.java : This class is my implementation for a DoubleLinkedList<E>.
 *                         Apart from a default constructor and a toString() method, all
 *                         methods from the List interface as well as the inner class
 *                         implementing ListIterator are included.
 *
 * @author Reesha Rajen
 * @version 1.1
 *
 */

import java.util.*;

public class DoubleLinkedList<E> implements List<E> {

    /** CONSTANTS */
    public final int DEFAULT_SIZE = 0;
    public final Node<E> DEFAULT_HEAD = null;
    public final Node<E> DEFAULT_TAIL = null;

    /** INSTANCE VARIABLES */
    private Node<E> head; //reference to the first node
    private Node<E> tail; //reference to the last node
    private int size;

    /** METHODS */

    /**
     * Default constructor for DoubleLinkedList class
     */
    public DoubleLinkedList(){
        this.size = DEFAULT_SIZE;
        this.head = DEFAULT_HEAD;
        this.tail = DEFAULT_TAIL;
    }

    /**
     * Class toString method
     * @return String containing all parameters, contained within brackets
     */
    public String toString(){
        if(this.size == DEFAULT_SIZE){
            return "[]";
        }
        else{
            DoubleListIterator iter = new DoubleListIterator(0);
            String thisString = "[";
            while(iter.hasNext()){
                thisString += iter.next() + ", ";
            }
            return thisString.substring(0, thisString.length()-2) + "]";
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if(this.size == DEFAULT_SIZE){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean contains(Object o) {
        DoubleListIterator iter = new DoubleListIterator(size);
        while(iter.hasPrevious()){
            if(o.equals(iter.previous())){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        ListIterator iterator = new DoubleListIterator();
        return iterator;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {

        listIterator(size).add(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        DoubleListIterator iter = new DoubleListIterator(indexOf(o));
        iter.remove();
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        this.head = null; //Gets rid of all nodes by simply setting head to null
        this.size = DEFAULT_SIZE; //resets the size
    }

    @Override
    public E get(int index) {
        if(index <= 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        else{
            return new DoubleListIterator(index).next();
        }
    }

    @Override
    public E set(int index, E element) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        new DoubleListIterator(index).set(element);
        return element;
    }

    @Override
    public void add(int index, E element) {
        listIterator(index).add(element);
    }

    @Override
    public E remove(int index) {
        DoubleListIterator iter = new DoubleListIterator(index);
        iter.remove();
        return iter.next();
    }

    @Override
    public int indexOf(Object o) {
        DoubleListIterator iter = new DoubleListIterator(0);
        while(iter.hasNext()){
            if(o.equals(iter.next())){
                return iter.nextIndex() - 1;
            }
        }
        return -1; //keeping the compiler happy
    }

    @Override
    public int lastIndexOf(Object o) {
        DoubleListIterator iter = new DoubleListIterator(size);
        while(iter.hasPrevious()){
            if(o.equals(iter.previous())){
                return iter.previousIndex();
            }
        }
        return -1; //because java
    }

    @Override
    public ListIterator<E> listIterator() {
        return new DoubleListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new DoubleListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * This class is the building block for a double-linked list.
     */
    private static class Node<E> {

        /**
         * Reference to the data
         */
        private E data;
        /**
         * Reference to the next node
         */
        private Node<E> next;

        private Node<E> prev;

        /** CONSTRUCTORS */
        /**
         * Creates a new node with a null next field (last node).
         *
         * @param data The data being stored
         */
        private Node(E data) {
            this.data = data;
            next = null;
        }

        /**
         * Creates a new node in reference to another node.
         *
         * @param data The data being stored
         * @param next The node referenced by the new node
         */
        private Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private class DoubleListIterator implements ListIterator<E> {

        public static final int DEFAULT_INDEX = 0;

        private Node<E> nextItem; //a reference to the last item returned
        private Node<E> lastItemReturned; //the index of the current item
        private int index;

        /**
         * Constructor for DoubleListIterator
         */
        public DoubleListIterator(){
            this(0);
        }

        /**
         * Constructor for DoubleListIterator
         * @param i The index of the item to be referenced
         */
        public DoubleListIterator(int i) {
            if (i < DEFAULT_INDEX || i > size) {
                throw new IndexOutOfBoundsException("Invalid index " + i);
            }
            lastItemReturned = null; //no item returned yet
            if (i == size) {
                index = size;
                nextItem = null;
            } else {
                nextItem = head;
                for (index = 0; index < i; index++) {
                    nextItem = nextItem.next;
                }
            }
        }

        /**
         * Indicate whether movement forward is defined
         * @return true if call to next will not throw an exception
         */
        @Override
        public boolean hasNext() {
            return nextItem != null;
        }

        /**
         * Moves the iterator forward and returns the next item.
         * @return The next item in the list
         * @throws NoSuchElementException if there is no such object
         */
        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        /**
         * Indicates whether movement backwards is defined
         * @return true if call to previous will not throw an exception
         */
        @Override
        public boolean hasPrevious(){
            return (nextItem == null && size != 0) || nextItem.prev != null;
        }

        /**
         * Moves the iterator backwards and returns the previous item
         * @return The previous item in the list
         * @throws NoSuchElementException(); if there is no such object
         */
        @Override
        public E previous() {
            if(!hasPrevious()){
                throw new NoSuchElementException();
            }
            if(nextItem == null) {
                nextItem = tail;
            }
            else{
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }

        @Override
        public int nextIndex() {
            return index++;
        }

        @Override
        public int previousIndex() {
            return index--;
        }

        @Override
        public void remove() {
            if(nextItem != null){
                if(lastItemReturned != null){
                    lastItemReturned.next = nextItem.next;
                    nextItem.next.prev = lastItemReturned;
                }
                else{
                    head = nextItem.next;
                }
            }

        }

        @Override
        public void set(E e) {
            if(lastItemReturned != null){
                lastItemReturned.data = e;
            }
            else {
                throw new IndexOutOfBoundsException();
            }
        }

        /**
         * Adds a new item between the item that will be returned by next and the item that
         * will be returned by previous.
         * If previous is called after add, the element added is returned.
         * @param e The item to be added
         */
        public void add(E e){
            if(head == null){
                head = new Node<E>(e);
                tail = head;
            }
            else if(nextItem == head){
                Node<E> newNode = new Node<E>(e);
                newNode.next = nextItem;
                nextItem.prev = newNode;
                head = newNode;
            }
            else if(nextItem == null){
                Node<E> newNode = new Node<E>(e);
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            else{
                Node<E> newNode = new Node<E>(e);
                newNode.prev = nextItem.prev;
                nextItem.prev.next = newNode;
                newNode.next = nextItem;
                nextItem.prev = newNode;
            }
            size++;
            index++;
            lastItemReturned = null;
        }
    }
}






