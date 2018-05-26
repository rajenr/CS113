package edu.miracosta.cs113;

/**
 * HuffmanTreeTest.java
 * Class containing JUnit testing methods for a HuffmanTree data structure.
 *
 * @author Reesha Rajen
 * @version 1.0
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class HuffmanTreeTest {

    /** CONSTANTS */
    private static final HuffmanTree.HuffData huffData_1 = new HuffmanTree.HuffData(3, 'l');
    private static final HuffmanTree.HuffData huffData_2 = new HuffmanTree.HuffData(2, 'o');
    private static final HuffmanTree.HuffData huffData_3 = new HuffmanTree.HuffData(1, 'h');
    private static final HuffmanTree.HuffData huffData_4 = new HuffmanTree.HuffData(1, 'e');
    private static final HuffmanTree.HuffData huffData_5 = new HuffmanTree.HuffData(1, 'w');
    private static final HuffmanTree.HuffData huffData_6 = new HuffmanTree.HuffData(1, 'r');
    private static final HuffmanTree.HuffData huffData_7 = new HuffmanTree.HuffData(1, 'd');
    private static final HuffmanTree.HuffData huffData_8 = new HuffmanTree.HuffData(1, ' ');
    private static final HuffmanTree.HuffData[] huffData = new HuffmanTree.HuffData[8];

    /**
     * DESCRIPTION: Tests for correct construction of a Huffman Tree.
     * PRE-CONDITION: None.
     * POST-CONDITION Passes test if the root of the new Huffman Tree is returning null.
     */
    @Test
    public void testConstructHuffTree() {
        HuffmanTree tree = new HuffmanTree();
        assertNull("Expected and actual trees DON'T match", tree.root);
    }

    /**
     * DESCRIPTION: Tests for correct construction of a Huffman Tree with weighted nodes to see if the
     * weights are summed correctly and the parent node is set to the sum of the two child nodes.
     * PRE-CONDITION: None.
     * POST-CONDITION: Passes test if the expected and actual roots match.
     */
    @Test
    public void testBuildHuffTree() {
        huffData[0] = huffData_1;
        huffData[1] = huffData_2;
        huffData[2] = huffData_3;
        huffData[3] = huffData_4;
        huffData[4] = huffData_5;
        huffData[5] = huffData_6;
        huffData[6] = huffData_7;
        huffData[7] = huffData_8;

        BinaryTree<HuffmanTree.HuffData> huffTest;
        HuffmanTree huffmanTree = new HuffmanTree();
        huffTest = huffmanTree.buildTree(huffData);

        String expectedRoot = "/ 11.0";
        String expectedRootLeft = "/ 4.0";
        String expectedRootRight = "/ 7.0";

        String actualRoot = huffTest.root.toString();
        String actualRootL = huffTest.root.left.toString();
        String actualRootR = huffTest.root.right.toString();

        assertEquals("Expected and actual roots DON'T match", expectedRoot, actualRoot);
        assertEquals("Expected and actual roots DON'T match", expectedRootLeft, actualRootL);
        assertEquals("Expected and actual roots DON'T match", expectedRootRight, actualRootR);
    }
}
