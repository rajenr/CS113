package edu.miracosta.cs113;

/**
 * HuffmanTreeDriver.java
 * This class is the driver for a bottom-up lossless compression algorithm that utilizes the Huffman Tree abstract
 * data type. The user is prompted to store a web page as a text file, or to import a previously existing text
 * file. From here, the user is able to create a Huffman Tree based off of the saved text file, and compress
 * the original text file without any data loss. Likewise, the user is able to decompress a text file as well.
 *
 * ALGORITHM:
 *
 * SET boolean isValid to TRUE
 * WHILE input isValid
 *  DISPLAY menu
 *      PROMPT user for input
 *          IF user input is 1
 *              STORE page
*           IF user input is 2
 *              STORE text file
*           IF user input is 3
 *              GENERATE tree
 *          IF user input is 4
 *              COMPRESS file
 *          IF user input is 5
 *              UNCOMPRESS text file
 *          IF user input is 6
 *              EXIT program
 *          ELSE
 *              DISPLAY menu
 *
 * @author Reesha Rajen
 * @version 1.0
 */

import java.util.Scanner;

public class HuffmanTreeDriver {

    public static void main(String[] args) {

        /** INITIALIZATIONS */
        boolean isValid = true;
        int userInput;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("*** PIED PIPER ***\n" +
                           "The Bottom-Up LOSSLESS Compression Algorithm. Making Data Storage Problems Smaller.\n" +
                           "c. Reesha Rajen\n");

        while(isValid) {

            /** DISPLAY MENU */
            System.out.println(
                    "Please make a selection:\n" +
                    "1. STORE A WEB PAGE AS TEXT FILE\n" +
                    "2. IMPORT A TEXT FILE\n" +
                    "3. GENERATE A HUFFMAN TREE (Note: file must exist)\n" +
                    "4. COMPRESS A FILE (Note: tree must exist)\n" +
                    "5. UNCOMPRESS A FILE (Note: tree and compressed file must exist)\n" +
                    "6. EXIT PROGRAM");

            /** USER INPUT */
            userInput = keyboard.nextInt(); //User input

            /** PROCESSING */
            if (userInput == 6) {
                isValid = false;
                System.out.println("Thank you for using Pied Piper's compression " +
                                   "algorithm with a Weissman Score of 5.2 !");

            } else if (userInput == 1) {
                /** STORE WEB PAGE */
                TextFileGenerator.storeWebPage(keyboard);
            } else if (userInput == 2) {
                /** IMPORT TEXT FILE */
                TextFileGenerator.importTextFile(keyboard);
            } else if (userInput == 3) {
                /** GENERATE HUFFMAN TREE */
                HuffmanTree.generateTreeFromFile();
            } else if (userInput == 4) {
                /** FILE COMPRESSION */
                HuffmanTree.compression();
            } else if (userInput == 5) {
                /** FILE DECOMPRESSION */
                HuffmanTree.decompression();
            }
        }
    }
}