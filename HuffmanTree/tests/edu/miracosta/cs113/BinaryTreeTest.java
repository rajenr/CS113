package edu.miracosta.cs113;

/**
 * BinaryTreeTest.java
 * Class containing the JUnit test methods for the Binary Tree data structure.
 *
 * @version 2.0
 * @author Reesha Rajen
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryTreeTest {

    /** CONSTANTS */
    private static final BinaryTree.Node ROOT_NODE = new BinaryTree.Node("Cow");
    private static final BinaryTree.Node LEFT_NODE = new BinaryTree.Node("Chipmunk");
    private static final BinaryTree.Node RIGHT_NODE = new BinaryTree.Node("Dog");
    private static final BinaryTree.Node[] NODES = {ROOT_NODE, LEFT_NODE, RIGHT_NODE};

    /**
     * DESCRIPTION: Tests for a correct construction of Binary Tree by asserting if the root
     * is initialized to null. The expected root is also null.
     */
    @Test
    public void testConstructTree() {
        BinaryTree tree = new BinaryTree();
        assertNull("Expected and actual trees DON'T match", tree.root);
    }

    /**
     * DESCRIPTION: Tests for the correct construction of Binary Tree with a given root. This test
     * utilizes the constructor with a root node parameter.
     */
    @Test
    public void testConstructTreeWithRoot() {

        for (BinaryTree.Node n : NODES) {
            BinaryTree test = new BinaryTree(n);
            assertEquals("Expected and actual roots DON'T MATCH", n, test.root);
        }
    }

    /**
     * DESCRIPTION: Tests for correct retrieval of the left and right subtrees of a Binary Tree data
     * structure. This test utilizes assertEquals to ensure that the expected and actual subtrees match.
     */
    @Test
    public void testGetLeftRightSubtree() {
        String expectedL, actualL, expectedR, actualR;
        BinaryTree leftSubtree = new BinaryTree(LEFT_NODE);
        BinaryTree rightSubtree = new BinaryTree(RIGHT_NODE);

        BinaryTree testLeft = new BinaryTree(ROOT_NODE, leftSubtree, null);
        BinaryTree testRight = new BinaryTree(ROOT_NODE, null, rightSubtree);

        expectedL = leftSubtree.toString();
        expectedR = rightSubtree.toString();
        actualL = testLeft.getLeftSubtree().toString();
        actualR = testRight.getRightSubtree().toString();

        assertEquals("Expected and actual subtrees DON'T MATCH", expectedL, actualL);
        assertEquals("Expected and actual subtrees DON'T MATCH", expectedR, actualR);
    }
}
