package edu.miracosta.cs113;

/**
 * HashTableChainDriver.java
 * This class is a Driver program for HashTableChain.java.
 *
 * @version 1.0
 * @author Reesha Rajen
 */

public class HashTableChainDriver {

    public static void main(String[] args) {

        HashTableChain<String, String> hashTableChain = new HashTableChain<>(5, 3.0);
        hashTableChain.put("Sign", "Nery");
        hashTableChain.put("Sing", "Nery");

        System.out.println(hashTableChain.toString());
        System.out.println(hashTableChain.entrySet());
        System.out.println(hashTableChain.hashCode());
        System.out.println(hashTableChain.keySet());
    }
}
