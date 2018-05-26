package edu.miracosta.cs113;

import java.util.Scanner;

/**
 * AVLTreeDriver.java
 * This class is the driver program for demonstration of a BST vs. an AVL tree.
 *
 * @author Reesha Rajen
 * @version 1.0
 * @lastModified 08 May 2018
 */
public class AVLTreeDriver {
    public static void main(String[] args){
        int input = 0;
        boolean isValid = true;
        Scanner keyboard = new Scanner(System.in);

        while (isValid) {
            System.out.println("*** AVL TREE w/o removal ***\n\n" +
                    "Welcome! Please select an option:\n" +
                    "1. BST vs. AVL DEMO\n" +
                    "2. EXAMPLE\n" +
                    "3. EXIT\n\n" +
                    "CHOICE: ");
            input = keyboard.nextInt();

            if (input == 1) {
                BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
                AVLTree<Integer> avlTree = new AVLTree<>();
                int[] data = new int[25];
                System.out.println("Now generating random numbers...");
                for (int i = 0; i < data.length; i++) {
                    data[i] = (int) Math.floor(Math.random() * 26);
                }
                System.out.println("Now adding to a binary search tree...");
                for(int i = 0; i < data.length; i++){
                    binarySearchTree.add(data[i]);
                    System.out.println("\n*** PRINTING NEW TREE ***\n");
                    System.out.println(binarySearchTree.toString());
                }
                System.out.println("Success! Now adding to an AVL tree...");
                for(int i = 0; i < data.length; i++){
                    avlTree.add(data[i]);
                    System.out.println("\n*** PRINTING NEW TREE ***\n");
                    System.out.println(avlTree.toString());
                }
                System.out.println("Success! Now printing both trees side by side...");
                System.out.println("\n*** BST ***\n");
                System.out.println(binarySearchTree.toString());
                System.out.println("\n*** AVL ***\n");
                System.out.println(avlTree.toString());
            } else if (input == 2) {
                // This example found in Topic 13 Slides.
                System.out.println("*** This word addition to an AVL tree performs exactly like the\ntrace performed" +
                        " in the TOPIC 13: Self-Balancing Trees lecture *** ");
                AVLTree<String> tree = new AVLTree<>();
                String[] words = {"The", "quick", "brown", "fox", "jumps",
                        "over", "the", "lazy", "dog"};

                for (String word : words) {
                    tree.add(word);
                    System.out.println(tree.toString());
                }
            } else {
                isValid = false;
                System.out.println("Now exiting program...");
            }
        }
    }
}
