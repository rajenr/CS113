package edu.miracosta.cs113;

/**
 * HuffmanTree.java
 * This class contains all methods for a Huffman Tree dealing with a generic object. A Huffman tree is used
 * to compress data by parsing a file, generating a character count based off of the file's contents, and then
 * storing each character as a leaf within the tree. Letters are encoded via strings of 1s and 0s, and the
 * tree stores the letters via their frequencies.
 *
 * @version 1.0
 * @author Reesha Rajen
 */

import java.io.*;
import java.util.*;

public class HuffmanTree<E> extends BinaryTree<E> {

    /** A reference to the completed Huffman tree. */
    private static BinaryTree<HuffData> huffTree;

    /**
     * DESCRIPTION: HuffmanTree default constructor.
     * PRE-CONDITION: None.
     * POST-CONDITION: Creates a Huffman Tree based off of the BinaryTree class's default constructor.
     */
    public HuffmanTree() {
        super();
    }

    /**
     * DESCRIPTION: HuffmanTree full constructor.
     * PRE-CONDITION: None.
     * POST-CONDITION: Creates a Huffman Tree based off of the BinaryTree class's full constructor.
     * @param root Node data type referencing the root of the tree
     */
    protected HuffmanTree(Node<E> root) {
        super(root);
    }

    /**
     * DESCRIPTION: This method is responsible for scraping a text file and gathering the frequency of any valid
     * character found in the file. Valid characters are indicated via a character array storing all valid characters.
     * These include:
     * A-Z (upper case letter) characters
     * a-z (lower case letter) characters
     * 0-9 (digit) characters
     * Symbols such as: space ( ), tab (\t), newline (\n), exclamation mark (!), period (.), question mark (?)
     * PRE-CONDITION: Assumes that the file is valid and in existence.
     * POST-CONDITION: Returns an array of HuffNodes containing the characters in the file with their respective
     * frequencies.
     * @param filename String referencing already created file. Throws exception if file is not found.
     * @return Returns an array of HuffData nodes containing all existing characters and their weights, or frequencies.
     */
    public static HuffData[] scrape(String filename) {
        HuffData data;
        ArrayList<HuffData> huffNodes = new ArrayList<>();
        HuffData[] datas;

        /** VALID CHARACTERS */
        char[] validChars = {'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I',
                             'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R',
                             's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', '0', '1',
                             '2', '3', '4', '5', '6', '7', '8', '9', ' ', '\t', '\n', '?','.', '!'};
        /** PARALLEL ARRAY */
        int[] counters = new int[validChars.length];

        String str;
        File file = new File(filename);
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Retrieve lines from the file
        str = scanner.nextLine();
        while (scanner.hasNextLine()) {
            str = str + "\n" + scanner.nextLine();
        }

        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < validChars.length; j++) {
                if (str.charAt(i) == validChars[j]) {
                    counters[j]++;
                }
            }
        }

        for (int i = 0; i < counters.length; i++) {
            //If the counter indicates that the letter is present
            if (counters[i] > 0) {
                data = new HuffData();
                data.setAll(counters[i], validChars[i]);
                huffNodes.add(data);
            }
        }

        //Create HuffData array based off of the ArrayList
        datas = new HuffData[huffNodes.size()];
        for (int i = 0; i < huffNodes.size(); i++) {
            datas[i] = huffNodes.get(i);
        }

        //Return the HuffData array
        return datas;
    }

    /**
     * DESCRIPTION: This method is responsible for constructing a Huffman Tree given an array of symbols. The
     * symbols are previously created Huffman Nodes, specifically Nodes with a character and a frequency. The nodes
     * are loaded into a priority queue, and the nodes are polled based on their weight. The nodes are combined
     * into a Binary Tree where each weighted node is a leaf. The root node is constructed as null. This process
     * repeats such that the Binary Tree does not contain any weighted node that is a root node. Once all the Binary
     * trees are combined, the newTree is offered to the priority queue, and then polled. This tree is the completed
     * Huffman Tree.
     * PRE-CONDITION: The HuffData[] is previously created and not null
     * POST-CONDITION: A Huffman Tree data type is returned containing all symbols originally contained in the
     * array of HuffNodes.
     * @param symbols A HuffData array containing all characters and frequencies.
     * @return A completed Binary Tree of HuffData, constructed such that of a Huffman Tree.
     */
    public BinaryTree<HuffData> buildTree(HuffData[] symbols)
    {
        /** Instantiate Java's built-in priority queue */
        PriorityQueue<BinaryTree<HuffData>> theQueue =
                new PriorityQueue<>(symbols.length, new CompareHuffmanTrees());

        /** Load the queue with the leaves, or the HuffData symbols */
        for (HuffData nextSymbol : symbols) {
            BinaryTree<HuffData> aBinaryTree = new BinaryTree<>(nextSymbol, null, null);
            theQueue.offer(aBinaryTree);
        }

        /** Construct the tree */
        while (theQueue.size() > 1) {
            BinaryTree<HuffData> left = theQueue.poll();
            BinaryTree<HuffData> right = theQueue.poll();
            double wl = left.getData().getWeight();
            double wr = right.getData().getWeight();
            HuffData sum = new HuffData(wl + wr, null);
            BinaryTree<HuffData> newTree = new BinaryTree<>(sum, left, right);
            theQueue.offer(newTree);
        }
        /** Instantiate huffTree static variable */
        huffTree = theQueue.poll();

        /** Return the huffTree variable */
        return this.huffTree;
    }

    /**
     * DESCRIPTION: This method is responsible for utilizing PrintStream in order to print out the
     * respective 'binary' codes for the letters that are present within the file itself.
     * PRE-CONDITION: Assumes that the Huffman Tree has been created.
     * POST-CONDITION: Prints the code using PrintStream.
     * @param out PrintStream object that will print to the console.
     * @param code String parameter responsible for working recursively.
     * @param tree Huffman Tree parameter.
     */
    public static void printCode(PrintStream out, String code, BinaryTree<HuffData> tree) {
        HuffData theData = tree.getData();
        if (theData.symbol != null) {
            if (theData.symbol.equals(' ')) {
                System.out.println("space: " + code);
            } else {
                System.out.println(theData.symbol + ": " + code);
            }
        } else {
            printCode(out, code + "0", tree.getLeftSubtree());
            printCode(out, code + "1", tree.getRightSubtree());
        }
    }

    /**
     * DESCRIPTION: This method works similarly to the printCode() method, but rather returns the codes as an
     * arraylist of strings. This method can be utilized for another encode method that will parse each indexed
     * variable within the arraylist itself.
     * PRE-CONDITION: Assumes that the Huffman Tree has been created.
     * POST-CONDITION: Returns an ArrayList data type of strings.
     * @param code String parameter that refers to the starting code.
     * @param tree Huffman Tree parameter.
     * @return String ArrayList containing all the HuffNodes with relative frequencies.
     */
    public static ArrayList<String> getCodes(String code, BinaryTree<HuffData> tree) {
        ArrayList<String> codes = new ArrayList<>();
        HuffData theData = tree.getData();

        if (theData.symbol != null) {
            if (theData.symbol.equals(' ')) {
                /** Here is where it will need to store into Array */
                codes.add("space: " + code);
            } else {
                codes.add(theData.symbol + ": " + code);
            }
        } else {
            getCodes(code + "0", tree.getLeftSubtree());
            getCodes(code + "1", tree.getRightSubtree());
        }
        return codes;
    }

    /**
     * DESCRIPTION: This is the recursive wrapper method for the encode() method. This method is responsible for the
     * base case of encoding an uncompressed text string from English to "binary".
     * @param message String parameter containing the English text to be compressed.
     */
    public static void encode(String message) {
        for (int i = 0; i < message.length(); i++) {
            encode(message.charAt(i) + "", huffTree, "");
        }
    }

    /**
     * NOTE TO PROF: I am so proud of this encode() method. It took me two hours to think of the logic behind it
     *       in order to write it recursively. When it worked, I may or may not have cried tears of joy. Definitely
     *       one of the cleanest methods I have ever written, in my opinion.
     *
     * DESCRIPTION: This method is responsible for encoding an uncompressed text string from English to
     * "binary". Working with the recursive wrapper method, it receives the static huffTree instance
     * variable, sets the data as the current root, and will traverse the tree as long as the current
     * root is null. This is due to the fact that the Huffman Tree stores null values at any non-leaf node.
     * From here, the method branches off into two recursive methods, creating a 2^n runtime, since it will begin at
     * one, branch to two, branch to four, etc. While inefficient, it is the most simplistic way to retrieve the path
     * of the character being searched for.
     * PRE-CONDITION: Assumes that the tree is created in order to run.
     * POST-CONDITION: Prints the path in the form of a String to the console.
     * @param message String parameter containing a single character only.
     * @param tree Static variable huffTree.
     * @param path Given String path indicating the start of the trace.
     */
    public static void encode(String message, BinaryTree<HuffData> tree, String path) {
        HuffData data = tree.getData();

        if (data.symbol == null) {
            encode(message, tree.getLeftSubtree(), path + "0");
            encode(message, tree.getRightSubtree(), path + "1");
        }
        else {
            if (data.getCharacter().equals(message.charAt(0))) {
                System.out.print(path);
            }
        }
    }

    /**
     * DESCRIPTION: This method is responsible for decoding a compressed text string back to English. The static variable
     * huffTree is set to the current tree, and the tree is traversed based on the character within the string.
     * PRE-CONDITION: String can only contain 0s or 1s, not any other "character". Otherwise, the character will
     * be ignored.
     * POST-CONDITION: Returns a string with the uncompressed text.
     * @param codedMessage String containing the coded message from the file.
     * @return StringBuilder data type containing the decoded/uncompressed message.
     */
    public String decode(String codedMessage) {
        StringBuilder result = new StringBuilder();
        BinaryTree<HuffData> currentTree = huffTree;
        for (int i = 0; i < codedMessage.length(); i++) {
            if (codedMessage.charAt(i) == '1') {
                currentTree = currentTree.getRightSubtree();
            } else if (codedMessage.charAt(i) ==  '0') {
                currentTree = currentTree.getLeftSubtree();
            } else {
                currentTree = huffTree;
            }
            if (currentTree.isLeaf()) {
                HuffData theData = currentTree.getData();
                result.append(theData.symbol);
                currentTree = huffTree;
            }
        }
        return result.toString();
    }

    /**
     * DESCRIPTION: Returns a string containing all the instance variables of a Huffman Node.
     * PRE-CONDITION: Assumes that the Huffman Tree has already been created.
     * @return String with all nodes in the Huffman Tree.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * DESCRIPTION: Performs a preorder traversal of the Huffman Tree.
     * PRE-CONDITION: Assumes the Huffman Tree has already been created.
     * POST-CONDITION: None.
     * @param node The local root
     * @param depth The depth
     * @param sb The string buffer to save the output
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for(int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if(node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth+1, sb);
            preOrderTraverse(node.right, depth+1, sb);
        }
    }

    /**
     * DESCRIPTION: This method is responsible for generating a Huffman tree based off of a text file.
     * The text file is stored as writeToMe.txt and contains all relevant text from either a web page source
     * or a prior existing text file.
     * PRE-CONDITION: Assumes that writeToMe.txt contains the text file for the Huffman Tree.
     * POST-CONDITION: Creates Huffman Tree and writes it to a file called printTree.txt.
     */
    public static void generateTreeFromFile() {
        try {
            HuffmanTree huffTest = new HuffmanTree();
            System.out.println("\nOPENING file stream for writing...");
            File file = new File("printTree.txt");
            PrintStream pw = new PrintStream(file);

            System.out.println("PRINTING to file...\n");
            pw.print(huffTest.buildTree(HuffmanTree.scrape("writeToMe.txt")));

            System.out.println("Success. Saved to printTree.txt.\n");
        } catch (NoSuchElementException nse) {
            System.out.println("No file found! Try storing a file from a web page first.\n");
        } catch (FileNotFoundException e) {
            System.out.println("No file found! Try storing a file from a web page first.\n");
        }
    }

    /**
     * DESCRIPTION: This method is responsible for the compression of a text file based off of Huffman codes
     * generated via a Huffman Tree.
     * The text file is stored as compress.txt and the Huffman Tree is constructed based off of the
     * file being scraped.
     * PRE-CONDITION: Assumes that generateTreeFromFile() has been called previously. If not, assumes that
     * writeToMe.txt contains the text necessary for the respective Huffman tree. Also assumes that compress.txt
     * has been created.
     * POST-CONDITION: Compresses file to a 'binary' file.
     */
    public static void compression() {
        try {
            HuffmanTree huffTest = new HuffmanTree();
            /** Retrieves the file */
            System.out.println("Now RETRIEVING file last created (writeToMe.txt)...");
            Scanner file = new Scanner(new File("writeToMe.txt"));

            /** Creates the Huffman Tree based off of the file */
            System.out.println("Success. RETRIEVING tree last stored...");
            huffTest.buildTree(HuffmanTree.scrape("writeToMe.txt"));

            /** Output to user */
            System.out.println("\n** BEGINNING COMPRESSION... **");

            /** Begins compression */
            PrintStream printStream = System.out;
            File compressedFile = new File("compress.txt");
            PrintStream pw = new PrintStream(compressedFile);

            while(file.hasNext()) { //Checks to see if the file has a line of text
                System.setOut(pw); //Sets the output stream to the PrintWriter since encode() is a void method
                HuffmanTree.encode(file.nextLine());
            }
            pw.close(); //Closes the stream
            System.setOut(printStream); //Reset the output stream

            System.out.println("Success. Saved to compress.txt.");
            System.out.println("UNCOMPRESSED FILE SIZE: " +
                    TextFileGenerator.getDecodedFileSize("writeToMe.txt") + " bits");
            System.out.println("COMPRESSED FILE SIZE: " +
                    TextFileGenerator.getEncodedFileSize("compress.txt") + " bits");
            System.out.println("COMPRESSION RATE: " + TextFileGenerator.getCompressionRate("compress.txt",
                    "writeToMe.txt"));
            System.out.println();

        } catch (NoSuchElementException nse) { //Exception handling if not successful
            System.out.println("No file found! Try storing a file as a web page first.\n");
        } catch (Exception e) {
            System.out.println("No file found! Try storing a file as a web page first.\n");
        }
    }

    /**
     * DESCRIPTION: This method is responsible for the decompression of a previously compressed text file.
     * The text file is stored as compress.txt and the Huffman Tree is constructed based off of the
     * file being scraped.
     * PRE-CONDITION: Assumes that compress.txt contains the compressed text file, and writeToMe.txt contains
     * text needed to build the Huffman Tree.
     * POST-CONDITION: Decompresses file back to original file.
     */
    public static void decompression() {
        try {
            HuffmanTree huffTest = new HuffmanTree();

            /** Retrieves the file */
            System.out.println("Now RETRIEVING file (compress.txt)...");
            Scanner file = new Scanner(new File("compress.txt"));

            /** Creates the Huffman Tree based off of the file */
            System.out.println("Success. RETRIEVING tree last stored...");
            huffTest.buildTree(HuffmanTree.scrape("writeToMe.txt"));

            /** Output to user */
            System.out.println("\n** BEGINNING DECOMPRESSION... **");

            /** Begins decompression */
            File uncompressedFile = new File("uncompress.txt");
            PrintStream pw = new PrintStream(uncompressedFile);

            while(file.hasNext()) {
                pw.print(huffTest.decode(file.nextLine()));
            }
            pw.close();
            System.out.println("Success. Saved to uncompress.txt");
            System.out.println("UNCOMPRESSED FILE SIZE: " +
                    TextFileGenerator.getDecodedFileSize("uncompress.txt") + " bits\n");
        } catch (NoSuchElementException nse) { //Exception handling if not successful
            System.out.println("No file found! Try storing a file as a web page first.\n");
        } catch (Exception e) {
            System.out.println("No file found! Try storing a file as a web page first.\n");
        }
    }

    // Nested Class
    /** A datum in the Huffman tree, referred to as HuffData. */
    public static class HuffData {

        /** CONSTANTS */

        private static final double DEFAULT_WEIGHT = 0.0;
        private static final Character DEFAULT_SYMBOL = ' ';

        /** INSTANCE VARIABLES */

        /** The weight or probability assigned to this HuffData. */
        private double weight;
        /** The alphabet symbol if this is a leaf. */
        private Character symbol;

        /** METHODS */

        /**
         * DESCRIPTION: Default HuffData constructor.
         * PRE-CONDITION: None.
         * POST-CONDITION: Creates a node to be stored in a HuffmanTree.
         */
        public HuffData() {
            this.weight = DEFAULT_WEIGHT;
            this.symbol = DEFAULT_SYMBOL;
        }

        /**
         * DESCRIPTION: Full HuffData constructor.
         * @param weight Notes the frequency of the valid character.
         * @param symbol Notes the valid character itself.
         */
        public HuffData(double weight, Character symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }

        /**
         * DESCRIPTION: Accessor method for the weight of a Huffman Node.
         * @return Returns the frequency of the valid character.
         */
        public double getWeight() {
            return this.weight;
        }

        /**
         * DESCRIPTION: Accessor method for the character of a Huffman Node.
         * @return Returns the character (note: the character will already have been checked for validity).
         */
        public Character getCharacter() {
            return this.symbol;
        }

        /**
         * DESCRIPTION: Sets all instance variables of a HuffNode.
         * @param weight Refers to the frequency of the valid character.
         * @param symbol Refers to the valid character itself.
         */
        public void setAll(double weight, Character symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }

        /**
         * DESCRIPTION: Returns a string containing all the instance variables of a Huffman Node.
         * @return String with all instance variables, denoted with a '/' separating the character
         * from the frequency itself.
         */
        public String toString() {
            if (this.symbol == null) {
                return "/ " + this.weight;
            }
            return "" + this.symbol + " / " + this.weight;
        }
    }

    /** A Comparator for Huffman trees; Nested class. */
    private static class CompareHuffmanTrees implements Comparator<BinaryTree<HuffData>> {
        /**
         * Compare two objects.
         * @param treeLeft The left-hand object.
         * @param treeRight The right-hand object.
         * @return -1 if left less than right, 0 if left equals right, and +1 if left greater than right.
         */
        @Override
        public int compare(BinaryTree<HuffData> treeLeft, BinaryTree<HuffData> treeRight) {
            double wLeft = treeLeft.getData().weight;
            double wRight = treeRight.getData().weight;

            return Double.compare(wLeft, wRight);
        }
    }
}
