package edu.miracosta.cs113;

/**
 * BinarySearchTreeWithRotate.java
 * This class extends the BinarySearchTree by adding the rotate operations. Rotation will change the balance of a
 * search tree while preserving the search tree property. Used as a common base class for self-balancing trees.
 *
 * @author Reesha Rajen
 * @version 1.0
 *
 * @param <E> Generic object type.
 */
public class BinarySearchTreeWithRotate<E extends Comparable<E>> extends BinarySearchTree<E> {
    // Methods
    /**
     * DESCRIPTION:    This method performs a right rotation.
     * PRE-CONDITION:  Assumes that a binary search tree object is created and contains values, and root is the root of
     *                 a binary search tree.
     * POST-CONDITION: The root of the binary search tree is root.right
     *                 root.right.right is raised one level.
     *                 root.right.left does not change levels.
     *                 root.left is lowered one level, and the new root is returned.
     *
     * @param root The root of the binary tree to be rotated.
     * @return The new root of the rotated tree.
     */
    protected Node<E> rotateRight(Node<E> root) {
        Node<E> temp = root.left;
        root.left = temp.right;
        temp.right = root;
        return temp;
    }

    /**
     * DESCRIPTION:    This method performs a left rotation.
     * PRE-CONDITION:  Assumes that a binary search tree object is created and contains values, and root is the root of
     *                 a binary search tree.
     * POST-CONDITION: The root of the binary search tree is root.left
     *                 root.left.left is raised one level.
     *                 root.left.right does not change levels.
     *                 root.right is lowered one level, and the new root is returned.
     *
     * @param root The root of the binary tree to be rotated.
     * @return The new root of the rotated tree.
     */
    protected Node<E> rotateLeft(Node<E> root) {
        Node<E> temp = root.right;
        root.right = temp.left;
        temp.left = root;
        return temp;
    }
}
