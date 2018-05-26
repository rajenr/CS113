package edu.miracosta.cs113;

/**
 * HashTableOpenDriver.java
 * This class is a Driver program for HashTableOpen.java.
 *
 * @version 1.0
 * @author Reesha Rajen
 */

public class HashTableOpenDriver {

    public static void main(String[] args) {

        HashTableOpen<String, String> hashTableOpen = new HashTableOpen<>();
        hashTableOpen.put("Test", "Nery");
        hashTableOpen.put("Test", "Not nery");

        System.out.println(hashTableOpen.get("Test"));
    }
}
