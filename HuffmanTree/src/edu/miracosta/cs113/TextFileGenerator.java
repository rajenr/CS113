package edu.miracosta.cs113;

/**
 * TextFileGenerator.java
 * This class handles parsing a text file for use with the construction of a Huffman tree. There is also a bonus method
 * delineating any HTML and JavaScript tags.
 *
 * @author Nery Chapeton-Lamas
 * @author Reesha Rajen (adapted from Nery Chapeton-Lamas)
 * @version 2.0
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextFileGenerator {
    private static final String USER_AGENT = "Mozilla/5.0"; //needed for HTTP request
    private static final String VALID_SYMBOLS = " !.?\t\n";
    private static final int BITS_PER_CHARACTER = 16; //Specified rate for calculating compression

    /**
     * A getter of raw html from a url, outputting to a file
     * @param url The url to fetch
     * @param outputFilename The file we want to output into
     * @throws IOException
     */
    public static void makeCleanFile(String url, String outputFilename) throws IOException {
        URL http = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) http.openConnection();

        // optional default is GET
        conn.setRequestMethod("GET");
        //add request header
        conn.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        //Only execute if we had a 200 response code
        if (responseCode == 200) {
            try {
                PrintWriter cleanFile = new PrintWriter(new FileOutputStream(outputFilename));
                Scanner dirtyFile = new Scanner(conn.getInputStream());

                while(dirtyFile.hasNextLine()) {
                    cleanFile.println( TextFileGenerator.cleanString( dirtyFile.nextLine() ) );
                }

                dirtyFile.close();
                cleanFile.close();
                System.out.println("Downloaded webpage (and cleaned) to text file :D.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid response code.");
        }
        conn.disconnect();
    }

    /**
     * Take in an unParsed String and clean it, only leaving characters
     * that are in the whiteList
     * @param unParsed The dirty/unparsed line
     * @return The cleaned line
     */
    public static String cleanString(String unParsed) {
        char current;
        StringBuilder returnString = new StringBuilder();

        unParsed = removeHTML(unParsed);
        unParsed = removeJS(unParsed);

        // Loop through each char and if it is within the whitelist,
        // then add it to the StringBuilder
        for(int i = 0; i < unParsed.length(); i++) {
            current = unParsed.charAt(i);

            if(TextFileGenerator.withinWhiteList(current)) {
                returnString.append(current);
            }
        }
        return returnString.toString();
    }

    /**
     * DESCRIPTION: This method is responsible for removing all HTML tags from the file. The method itself utilizes
     * a simple regex to remove the HTML between the two angle brackets.
     * @param unParsed Unparsed String parameter containing HTML tags.
     * @return String containing the parsed String without HTML tags (String remains unparsed because
     * it has not been checked for character validity yet).
     */
    public static String removeHTML(String unParsed) {
        unParsed = unParsed.toString().replaceAll("\\<.*?>","");
        return unParsed;
    }

    /**
     * DESCRIPTION: This method is responsible for removing all JavaScript tags from the file, if the site
     * utilizes JavaScript. The method simply hunts for any remaining tags.
     * @param unParsed Unparsed String parameter containing JavaScript tags.
     * @return String containing the parsed String without JS tags (String remains unparsed because
     * it has not been checked for character validity yet).
     */
    public static String removeJS(String unParsed) {
        String[] jsTags = {"document.documentElement.className", "document.documentElement.className.replace",
                           "display", "border", "boxshadow", "height", "width", "margin", ".widget", "html",
                           ".className"};

        for (int i = 0; i < jsTags.length; i++) {
            unParsed = unParsed.replace(jsTags[i], "");
        }
        return unParsed;
    }

    /**
     * Check to see if the ascii value is within our whiteList
     * @param ascii The ascii of the character we are checking
     * @return True if it is within the whitelist, false if not
     */
    public static boolean withinWhiteList(char ascii) {
        //If it is a valid symbols, digit, capital letter or lower case letter
        return VALID_SYMBOLS.indexOf(ascii) != -1 || ((ascii >= '0' && ascii <= '9') || (ascii >= 'A' && ascii <= 'Z')
                || (ascii >= 'a' && ascii <= 'z'));
    }

    /**
     * Counts number of unicode chars in a text file
     *
     * @param filename file to open and count characters for
     * @return number of unicode characters (includes whitespace, etc.)
     */
    public static int getNumChars(String filename) {
        int count = 0;

        try {
            Scanner inputFile = new Scanner(new FileReader(filename));
            while (inputFile.hasNextLine()) {
                count += inputFile.nextLine().length() + 1; //add 1 to account for newline
            }
            inputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        count--;//accounting for end of file (EOF) not having newline char

        return count;
    }

    /**
     * DESCRIPTION: Returns the encoded file size. This is attributed to simply getting the number of characters
     * in the file, due to the fact that the file has been converted to a 'binary' file of 1s and 0s.
     * @param filename String parameter referring to the file name of the compressed file.
     * @return int containing the size of the compressed file.
     */
    public static int getEncodedFileSize(String filename) {
        int chars = 0;

        chars = getNumChars(filename);
        return chars;
    }

    /**
     * DESCRIPTION: Returns the decoded file size. This is attributed to simply getting the number of characters
     * in the file and multiplying it by 16, or the number of bits per character.
     * @param filename String parameter referring to the file name of the decompressed file.
     * @return int containing the size of the decompressed file.
     */
    public static int getDecodedFileSize(String filename) {
        int chars = 0;

        chars = getNumChars(filename) * BITS_PER_CHARACTER;
        return chars;
    }


    /**
     * DESCRIPTION: This method retrieves the compression rate. This is calculated by finding the compression ratio,
     * or the size of the uncompressed file over the size of the compressed file. The representation is that of a
     * ratio.
     * @param compressed String referring to the file name of the compressed file.
     * @param decompressed String referring to the file name of the decompressed file.
     * @return String containing the ratio of compression.
     */
    public static String getCompressionRate(String compressed, String decompressed) {

        int sizeEncode = getEncodedFileSize(compressed);
        int sizeDecode = getDecodedFileSize(decompressed);

        double perCompress = ( (double) sizeDecode/sizeEncode );

        DecimalFormat df = new DecimalFormat("#.##");

        /** COMPRESSION RATE: uncompressed file / compressed file, always a large ratio due to
         * the mathematics of the calculation.
         */
        return df.format(perCompress) + " : 1";

    }

    /**
     * DESCRIPTION: This method is responsible for storing a web page to the appropriate file location for future
     * use with the Huffman tree. The file will be stored in writeToMe.txt. The scrape method references this file
     * location for the correct construction of a Huffman tree.
     * PRE-CONDITION: Assumes a Scanner object is created.
     * POST-CONDITION: Writes content of the web page to the appropriate file location for future use.
     * @param keyboard Scanner object.
     */
    public static void storeWebPage(Scanner keyboard) {

        try {
            String url;
            System.out.print("ENTER VALID URL (Note: add a space and press ENTER): ");
            keyboard.nextLine();
            url = keyboard.nextLine();
            url = url.substring(0, url.length()-1); //Deletes extra space from line

            /** Borrows web browser in order to visit URL */
            System.out.println("Now visiting URL...");
            String url_open = url;
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));

            /** Creates the clean file and saves it */
            TextFileGenerator.makeCleanFile(url, "writeToMe.txt");

            /** Inform user of file location */
            System.out.println("\n\nSAVED to writeToMe.txt");
            System.out.println("UNCOMPRESSED FILE SIZE: " +
                    TextFileGenerator.getDecodedFileSize("writeToMe.txt") + " bits\n");
        } catch (IllegalArgumentException iae) {
            System.out.println("INVALID URL");
        } catch (InputMismatchException ime) {
            System.out.println("INVALID URL");
        } catch (IOException e) {
            System.out.println("INVALID URL");
        }
    }

    /**
     * DESCRIPTION: This method is responsible for importing a text file and writing it to the appropriate
     * text file location for use with the Huffman Tree scrape method, which will reference that file location.
     * The method also outputs previously created files for the user, including files for stress testing the
     * Huffman tree compression.
     * PRE-CONDITION: Assumes Scanner object is created.
     * POST-CONDITION: Writes content of text file to the appropriate file location for future use.
     *
     * @param keyboard Scanner object.
     */
    public static void importTextFile(Scanner keyboard) {

        String scrapeFile;
        try {

            /** PROMPT USER */
            System.out.println("TEXT FILES AVAILABLE: \n" +
                               "stressTest.txt (STRESS TESTING)\n" +
                               "rickRoll.txt (STRESS TESTING)\n" +
                               "huffmanInfo.txt (STRESS TESTING)\n" +
                               "OTHER FILE");
            System.out.print("Enter the name of the file for processing: ");
            keyboard.nextLine();
            scrapeFile = keyboard.nextLine();

            /** Account for user error or if file is not appended with .txt */
            if(!scrapeFile.contains(".txt")) {
                scrapeFile = scrapeFile + ".txt";
            }

            /** Retrieves the file */
            System.out.println("Now RETRIEVING file...");
            Scanner scan = new Scanner(new File(scrapeFile));

            System.out.println("\nOPENING file stream for writing...");
            File file = new File("writeToMe.txt");
            PrintStream pw = new PrintStream(file);

            System.out.println("PRINTING to file...\n");
            while(scan.hasNext()) {
                pw.print(scan.nextLine());
            }
            pw.close();

            System.out.println("Success. Saved as writeToMe.txt.\n");
        } catch (IllegalArgumentException iae) {
            System.out.println("INVALID FILE");
        } catch (InputMismatchException ime) {
            System.out.println("INVALID FILE");
        } catch (IOException e) {
            System.out.println("INVALID FILE");
        }
    }
}