package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayListStack<E> implements StackInterface {

    /* CONSTANTS */
    private static final int INITIAL_CAPACITY = 10;

    /* INSTANCE VARIABLES */
    private ArrayList<E> data;
    private int stackTop = -1;

    /* METHODS */

    /**
     * Default ArrayListStack constructor
     */
    @SuppressWarnings("unchecked")
    public ArrayListStack() {
        data = new ArrayList<>(INITIAL_CAPACITY);
    }

    /**
     * Full ArrayListStack constructor @nery do i need this??
     * @param capacity allows user to allocate storage for an array with a specified capacity
     */
    @SuppressWarnings("unchecked")
    public ArrayListStack(int capacity) {
        data = new ArrayList<>(capacity);
    }

    @Override
    public boolean empty() {
        return data.size() == 0;
    }

    @Override
    public Object peek() {
        if(empty()) {
            throw new EmptyStackException();
        }
        return data.get(stackTop);
    }

    @Override
    public Object pop() {
        if(empty()) {
            throw new EmptyStackException();
        }
        else {
            E stackPop = data.get(stackTop);
            stackTop--;
            return stackPop;
        }
    }

    @Override
    public Object push(Object obj) {
        stackTop++;
        data.add((E) obj);
        return obj;
    }
}
