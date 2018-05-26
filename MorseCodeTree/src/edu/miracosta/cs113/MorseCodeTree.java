package edu.miracosta.cs113;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * MorseCodeTree.java
 * Class containing methods for a MorseCodeTree object
 *
 * @version 1.0
 * @author Reesha Rajen
 */
public class MorseCodeTree<E> extends BinaryTree<E>  {

    //CONSTANTS
    public static final String[] MORSE_ALPHABET = {"*-", "-***", "-*-*", "-**", "*", "**-*","--*", "****", "**", "*---",
                                                    "-*-", "*-**", "--", "-*", "---", "*--*", "--*-", "*-*", "***",
                                                    "-", "**-", "***-", "*--", "-**-", "-*--", "--**"};

    /**
     * Inherits BinaryTree constructor
     */
    public MorseCodeTree() {
        super();
    }

    /**
     * Inherits BinaryTree constructor
     * @param root Node param
     */
    protected MorseCodeTree(Node<E> root) {
        super(root);
    }

    /**
     * Converts Binary Tree nodes to a String using preorder traversal
     * @return String containing data in Binary Tree
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Converts Binary Tree nodes to a String using inorder traversal
     * @return String containing data in Binary Tree
     */
    public String toStringInOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Converts Binary Tree nodes to a String using inorder traversal
     * @return String containing data in Binary Tree
     */
    public String toStringPostOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Builds a Binary Tree via an inputted file containing each English alphabetical letter and its respective Morse
     * code value, delineated by whitespace
     */
    public void readMorseCodeTree() {

        //TODO ask about the file path
        try (Scanner sc = new Scanner(new FileInputStream("morsecode.txt"))) { // Text file containing the preorder data.
            Character rootLetter = ' ';
            this.root = new Node<E>((E) rootLetter);

            while (sc.hasNext()) {
                Node<E> node = this.root; // The node for the current letter.
                Character letter = sc.next().charAt(0); // The actual letter.
                E data = (E) letter; // Gotta cast the char to a Character to an E even though E will be a Character.
                String morse = sc.next(); // The morse code string for letter.

                for (int x = 0; x < morse.length(); x++) { // Loop through each part of the morse code for the current letter.
                    if (morse.charAt(x) == '*') {
                        if (node.left == null) {
                            Node<E> nextLetter = new Node(data.toString());
                            node.left = nextLetter;
                        }

                        node = node.left;
                    } else if (morse.charAt(x) == '-') {
                        if (node.right == null) {
                            Node<E> nextLetter = new Node(data.toString());
                            node.right = nextLetter;
                        }

                        node = node.right;
                    }
                }

                node.data = data;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Decodes a String parameter from Morse code into an English line.
     * Delineates by whitespace found in String param.
     *
     * @param str String param
     * @return String containing decoded English line
     */
    public String decode(String str) {
        List<String> letters = Arrays.asList(str.split(" "));
        String tempStr = "";

        for (int i = 0; i < letters.size(); i++) {
            Node node = root;
            for (int j = 0; j < letters.get(i).length(); j++) {
                if (letters.get(i).charAt(j) == '*') {
                    if (node.left != null) {
                        Node temp = node.left;
                        node = temp;
                    }
                }
                else if (letters.get(i).charAt(j) == '-') {
                    if (node.right != null) {
                        Node temp = node.right;
                        node = temp;
                    }
                }
            }
            tempStr += node;
        }
        return tempStr;
    }

    /**
     * Decode English alphabet from a given array of Morse characters containing all 26 alphabetical characters
     */
    public void decodeAlphabet() {
        String tempStr = "";

        for (int i = 0; i < MORSE_ALPHABET.length; i++) {
            Node node = root;
            for (int j = 0; j < MORSE_ALPHABET[i].length(); j++) {
                if (MORSE_ALPHABET[i].charAt(j) == '*') {
                    if (node.left != null) {
                        Node temp = node.left;
                        node = temp;
                    }
                }
                else if (MORSE_ALPHABET[i].charAt(j) == '-') {
                    if (node.right != null) {
                        Node temp = node.right;
                        node = temp;
                    }
                }
            }
            tempStr += node;
        }
        char[] englishLetters = new char[26];
        for (int i = 0; i < tempStr.length(); i++) {
            englishLetters[i] = tempStr.charAt(i);
            System.out.println(MORSE_ALPHABET[i] + " " + englishLetters[i]);
        }
    }

    /**
     * Wrapper encode method: allows single-letter String param to be encoded to Morse code value
     * @param letter String param
     * @return String parameter containing morse code
     */
    public String encode(String letter) {
        return encoding(root, "", letter);
    }

    /**
     * Recursive encode method: allows single-letter String param to be encoded to Morse code value
     * @param root Root node of MorseCodeTree
     * @param path String param
     * @param letter Single-letter String param
     * @return String containing the encoded Morse code value
     */
    private String encoding(Node<E> root, String path, String letter) {
        String str = "";

        if(root.data.toString().equals(letter)) {
            str = "" + path + "\n";
        }
        if(root.left != null){
            str += encoding(root.left, path.concat("*"), letter);
        }
        if(root.right != null){
            str += encoding(root.right, path.concat("-"), letter);
        }

        return str;
    }
}
