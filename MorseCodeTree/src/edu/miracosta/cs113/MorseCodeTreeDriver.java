package edu.miracosta.cs113;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorseCodeTreeDriver {

    public static void main(String[] args) {
        
        //DECLARATIONS
        Scanner key = new Scanner(System.in);
        MorseCodeTree bt;
        int num;

        //INITIALIZATIONS
        bt = new MorseCodeTree(); //Create empty tree
        bt.readMorseCodeTree(); //Fill tree with values

        //PROMPT USER
        System.out.print("***MORSE CODE TRANSLATOR***" +
                            "\nc. 2017 Reesha Rajen" +
                            "\n\n\nPlease select a function:" +
                            "\n1. Test output for all morse code letters" +
                            "\n2. Decode a text file" +
                            "\n3. Decode a single line" +
                            "\n4. Encode a single character (NEW)" +
                            "\n5. Exit program" +
                            "\n\nChoice: ");
        num = key.nextInt();

        //PROCESSING
        if (num == 1) {
            bt.decodeAlphabet();
        }
        else if (num == 2) {
            System.out.print("Files that currently exist: decode.txt\n" +
                             "Enter the file name (ADD EXTENSION): ");
            key.nextLine();
            String fileName = key.nextLine();
            System.out.println("You entered " + fileName + ". Now decoding...");

            try (Scanner sc = new Scanner(new FileInputStream(fileName))) {
                while(sc.hasNextLine()) {
                    System.out.println(bt.decode(sc.nextLine()));
                }
            } catch (FileNotFoundException e) {
                System.out.println("Cannot find file specified.");
            }
        }
        else if (num == 3) {
            System.out.print("Enter a line of morse code: ");
            key.nextLine(); //Buffer flush
            String str = key.nextLine();
            bt.decode(str);
        }
        else if (num == 4) {
            System.out.print("Enter a single character to encode: ");
            key.nextLine(); //Buffer flush
            String letter = key.nextLine();
            char toEncode = letter.toLowerCase().charAt(0);
            System.out.println(bt.encode(toEncode + ""));
        }
        else if (num == 5) {
            System.out.println("Now exiting program...");
            System.exit(0);
        }
    }
}
