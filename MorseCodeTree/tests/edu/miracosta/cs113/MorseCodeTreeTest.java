package edu.miracosta.cs113;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * MorseCodeTreeTest.java
 * Class containing JUnit testing methods for a MorseCodeTree object
 *
 * @version 1.0
 * @author Reesha Rajen
 */
public class MorseCodeTreeTest {

    private static final MorseCodeTree MORSE_CODE_TREE = new MorseCodeTree();
    private static final String HELLO_WORLD = "helloworld";
    private static final String POKEMON_PROF_IDENTITY = "nerychapetonlamas";
    private static final String DEVELOPER = "reesha";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @Test
    public void testConstructMCTree() {
        MorseCodeTree tree = new MorseCodeTree();
        assertNull("Expected and actual trees DON'T match", tree.root);
    }

    @Test
    public void testGetMCTRoot() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.toString();

        assertEquals("Expected and actual tree roots DON'T match", " ", actual);
    }

    //TODO finish

    @Test
    public void testGetLetterA() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.left.right.toString();
        String expected = "a";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterB() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.right.left.left.left.toString();
        String expected = "b";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterC() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.right.left.right.left.toString();
        String expected = "c";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterD() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.right.left.left.toString();
        String expected = "d";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterE() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.left.toString();
        String expected = "e";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterF() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.left.left.right.left.toString();
        String expected = "f";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterG() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.right.right.left.toString();
        String expected = "g";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterH() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.left.left.left.left.toString();
        String expected = "h";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterI() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.left.left.toString();
        String expected = "i";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterJ() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.left.right.right.right.toString();
        String expected = "j";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterK() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.right.left.right.toString();
        String expected = "k";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterL() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.left.right.left.left.toString();
        String expected = "l";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterM() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.right.right.toString();
        String expected = "m";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterN() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.right.left.toString();
        String expected = "n";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterO() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.right.right.right.toString();
        String expected = "o";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterP() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.left.right.right.left.toString();
        String expected = "p";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterQ() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.right.right.left.right.toString();
        String expected = "q";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterR() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.left.right.left.toString();
        String expected = "r";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterS() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.left.left.left.toString();
        String expected = "s";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterT() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.right.toString();
        String expected = "t";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterU() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.left.left.right.toString();
        String expected = "u";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterV() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.left.left.left.right.toString();
        String expected = "v";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterW() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.left.right.right.toString();
        String expected = "w";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterX() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.right.left.left.right.toString();
        String expected = "x";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterY() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.right.left.right.right.toString();
        String expected = "y";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testGetLetterZ() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.root.right.right.left.left.toString();
        String expected = "z";

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testDecodeHelloWorld() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.decode("**** * *-** *-** --- *-- --- *-* *-** -**");
        String expected = HELLO_WORLD;

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testDecodeProfessorName() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.decode("-* * *-* -*-- -*-* **** *- *--* * - --- -* *-** *- -- *- ***");
        String expected = POKEMON_PROF_IDENTITY;

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testDecodeDeveloperName() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.decode("*-* * * *** **** *-");
        String expected = DEVELOPER;

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }

    @Test
    public void testDecodeEntireAlphabet() {
        MORSE_CODE_TREE.readMorseCodeTree();
        String actual = MORSE_CODE_TREE.decode("*- -*** -*-* -** * **-* --* **** ** *--- -*- *-** -- -* --- " +
                                                    "*--* --*- *-* *** - **- ***- *-- -**- -*-- --**");
        String expected = ALPHABET;

        assertEquals("Expected and actual node values DON'T match", expected, actual);
    }
}
