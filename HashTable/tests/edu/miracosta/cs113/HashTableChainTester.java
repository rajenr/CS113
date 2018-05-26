package edu.miracosta.cs113;

/**
 * HashTableChainTester.java
 * This class contains all JUnit tests for each method found in HashTableChain.java. The tests are designed
 * to thoroughly test method functionality.
 *
 * @version 1.0
 * @author Reesha Rajen
 */

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableChainTester {

    private MapInterface hashTable;
    private MapInterface hashTableCustom;

    @Before
    public void setupHashTable() {
        hashTable = new HashTableChain();
        hashTableCustom = new HashTableChain(5, 3.0);
        hashTableCustom.put(1, "Rick");
    }

    @Test
    public void testConstructors() {
        hashTableCustom = new HashTableChain(5, 3.0); //Intentionally overrides @Before
        assertTrue("HashTableChain should start as empty", hashTable.isEmpty());
        assertTrue("HashTableChain should start as empty", hashTableCustom.isEmpty());
        assertTrue("HashTableChain should start as empty", hashTable.size() == 0);
        assertTrue("HashTableChain should start as empty", hashTableCustom.size() == 0);
    }

    @Test
    public void testGet() {
        hashTableCustom.put(2, "Morty");
        assertEquals("Expected and actual values DON'T match", "Rick", hashTableCustom.get(1));
        assertFalse("Second value retrieved!", hashTableCustom.get(1) == "Morty");
    }

    @Test
    public void testPut() {
        assertFalse("HashTableChain should contain value", hashTableCustom.isEmpty());
    }

    @Test
    public void testPutMany() {
        hashTableCustom.put(2, "Morty");
        hashTableCustom.put(3, "Summer");
        assertEquals("Expected and actual values DON'T match","Morty", hashTableCustom.get(2));
        assertEquals("Expected and actual values DON'T match","Summer", hashTableCustom.get(3));
    }

    @Test
    public void testPutOverride() {
        hashTableCustom.put(1, "Rick Sanchez");
        assertFalse("Expected and actual values DON'T match, PUT method did not replace old value.",
                     hashTableCustom.containsValue("Rick"));
        assertEquals("Expected and actual values DON'T match, PUT method did not replace old value.",
                     "Rick Sanchez", hashTableCustom.get(1));
    }

    @Test
    public void testChaining() {
        hashTableCustom = new HashTableChain(5, 3.0); //Intentionally overrides @Before
        hashTableCustom.put("Sing", "Nery");
        hashTableCustom.put("Sign", "Nery");
        String expected = "1: (KEY) Sign (VALUE) Nery (KEY) Sing (VALUE) Nery \n";
        assertEquals("Expected and actual values DON'T match", expected, hashTableCustom.toString());
    }

    @Test
    public void testClearAndIsEmpty() {
        hashTableCustom.clear();
        assertTrue("HashTableChain should be empty", hashTableCustom.isEmpty());
        assertEquals("Expected and actual values DON'T match, CLEAR method did not clear HashTable.",
                null, hashTableCustom.get(1));
    }

    @Test
    public void testContainsKey() {
        assertTrue("HashTableChain should contain key", hashTableCustom.containsKey(1));
    }

    @Test
    public void testContainsKeys() {
        hashTableCustom.put(2, "Morty");
        hashTableCustom.put(3, "Summer");
        assertTrue("HashTableChain should contain key", hashTableCustom.containsKey(3));
        assertTrue("HashTableChain should contain key", hashTableCustom.containsKey(2));
        assertTrue("HashTableChain should contain key", hashTableCustom.containsKey(1));
    }

    @Test
    public void testContainsKeyError() {
        try {
            hashTableCustom.containsKey(2);
            fail("Contains Key should have thrown NullPointerException!");
        } catch (NullPointerException npe) {
            /** Test passed */
        }
    }

    @Test
    public void testContainsValue() {
        assertTrue("HashTableChain should contain value", hashTableCustom.containsValue("Rick"));
    }

    @Test
    public void testContainsValues() {
        hashTableCustom.put(2, "Morty");
        hashTableCustom.put(3, "Summer");
        assertTrue("HashTableChain should contain value", hashTableCustom.containsValue("Summer"));
        assertTrue("HashTableChain should contain value", hashTableCustom.containsValue("Morty"));
        assertTrue("HashTableChain should contain value", hashTableCustom.containsValue("Rick"));
        hashTableCustom.remove(2);
        assertFalse("HashTableChain should contain value", hashTableCustom.containsValue("Morty"));

    }

    @Test
    public void testToString() {
        String expected = "1: (KEY) 1 (VALUE) Rick \n";
        assertEquals("Expected and actual values DON'T match", expected, hashTableCustom.toString());
    }

    @Test
    public void testEntrySetKeySet() {
        String expected_1 = "[(KEY) Sign (VALUE) Nery, (KEY) Sing (VALUE) Nery]";
        String expected_2 = "[Sing, Sign]";

        hashTableCustom = new HashTableChain(5, 3.0); //Intentionally overrides @Before
        hashTableCustom.put("Sing", "Nery");
        hashTableCustom.put("Sign", "Nery");

        assertEquals("Expected and actual values DON'T match", expected_1,
                      hashTableCustom.entrySet().toString());
        assertEquals("Expected and actual values DON'T match", expected_2,
                      hashTableCustom.keySet().toString());
        hashTableCustom.remove("Sign");
        assertNotEquals("Expected and actual values DON'T match", expected_2,
                         hashTableCustom.keySet().toString());
    }

    @Ignore
    public void testEquals() {
        hashTableCustom = new HashTableChain(5, 3.0); //Intentionally overrides @Before
        MapInterface hashTableCompare = new HashTableChain(5, 3.0);
        hashTableCustom.put(1, "Morty");
        hashTableCompare.put(1, "Morty");
        assertTrue("Expected and actual values DON'T match", hashTableCustom.equals(hashTableCompare));
    }

    @Test
    public void testMapHashCode() {
        int expected = 5153932;

        hashTableCustom = new HashTableChain(5, 3.0); //Intentionally overrides @Before
        hashTableCustom.put("Sign", "Nery");
        hashTableCustom.put("Sing", "Nery");
        assertEquals("Expected and actual values DON'T match", expected, hashTableCustom.hashCode());
    }
}
