package edu.miracosta.cs113;

/**
 * BinaryTree.java
 * This class contains methods for the BinaryTree data structure. A Binary tree is a tree that serves as a
 * hierarchical data structure. It is a tree whose elements have at most two children. Since each element in a binary
 * tree can only have 2 children, we typically name them the left and the right child.
 *
 * @version 1.0
 * @author Reesha Rajen
 *
 * @param <E> Generic type
 */

import java.io.Serializable;
import java.util.Scanner;

public class BinaryTree<E> {

    //INSTANCE VARIABLES
    protected Node<E> root;

    //METHODS
    /**
     * DESCRIPTION:    Default constructor for BinaryTree, constructs Binary Tree with no data.
     * PRE-CONDITION:  N/A.
     * POST-CONDITION: Sets the root node to null.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * DESCRIPTION:    Full constructor for BinaryTree, constructs Binary Tree with data at the root.
     * PRE-CONDITION:  N/A.
     * POST-CONDITION: Sets the root node to root data.
     *
     * @param root Node param
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * DESCRIPTION:    Full constructor for BinaryTree, constructs Binary Tree with data at the root and a left
     *                 and right subtree, which are also binary trees.
     * PRE-CONDITION:  N/A.
     * POST-CONDITION: Sets the root node to root data.
     *
     * @param data Node parameter containing root data.
     * @param leftTree The left subtree of the full tree.
     * @param rightTree The right subtree of the full tree.
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<>(data);
        if(leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    /**
     * DESCRIPTION:    This method is a getter method for returning the left subtree of the binary tree object.
     * PRE-CONDITION:  Assumes that a binary tree object exists and is not null.
     * POST-CONDITION: Returns the left subtree, also a binary tree object.
     *
     * @return Binary Tree containing left subtree.
     */
    public BinaryTree<E> getLeftSubtree() {
        if(root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        } else {
            return null;
        }
    }

    /**
     * DESCRIPTION:    This method is a getter method for returning the right subtree of the binary tree object.
     * PRE-CONDITION:  Assumes that a binary tree object exists and is not null.
     * POST-CONDITION: Returns the right subtree, also a binary tree object.
     *
     * @return Binary Tree containing right subtree.
     */
    public BinaryTree<E> getRightSubtree() {
        if(root != null && root.right != null) {
            return new BinaryTree<>(root.right);
        } else {
            return null;
        }
    }

    /**
     * DESCRIPTION:    This method checks if a node of a binary tree is a leaf, or contains null children.
     * PRE-CONDITION:  Assumes that a binary tree object exists and is not null.
     * POST-CONDITION: Returns a boolean value.
     *
     * @return True if node.left is null and node.right is null simultaneously.
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    /**
     * DESCRIPTION:    This method returns a string containing all data within the binary tree object. The method
     *                 performs a pre-order traversal of the binary tree.
     * PRE-CONDITION:  Assumes that a binary tree object exists and is not null.
     * POST-CONDITION: Returns a String containing all nodes stored within the tree.
     *
     * @return String containing the root node data as well as the data from the left and right subtrees in
     *         pre-order traversal.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * DESCRIPTION:    This method performs a pre-order traversal of all nodes within the binary tree. The root node
     *                 is visited first, followed by the left and right subtrees, respectively.
     * PRE-CONDITION:  Assumes that a binary tree object exists and contains nodes.
     * POST-CONDITION: N/A.
     *
     * @param node Node representing the root of the tree/subtree.
     * @param depth Depth of starting node.
     * @param sb StringBuilder object.
     */
    public void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if(node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString() + "\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * DESCRIPTION:    This method performs an in-order traversal of all nodes within the binary tree. The left
     *                 subtree is visited first, followed by the root and the right subtree, respectively.
     * PRE-CONDITION:  Assumes that a binary tree object exists and contains nodes.
     * POST-CONDITION: N/A.
     *
     * @param node Node representing the root of the tree/subtree.
     * @param depth Depth of starting node.
     * @param sb StringBuilder object.
     */
    public void inOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if(node == null) {
            sb.append("null\n");
        } else {
            inOrderTraverse(node.left, depth + 1, sb);
            sb.append(node.toString() + "\n");
            inOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * DESCRIPTION:    This method performs a post-order traversal of all nodes within the binary tree. The right
     *                 subtree is visited first, followed by the left subtree and the root, respectively.
     * PRE-CONDITION:  Assumes that a binary tree object exists and contains nodes.
     * POST-CONDITION: N/A.
     *
     * @param node Node representing the root of the tree/subtree.
     * @param depth Depth of starting node.
     * @param sb StringBuilder object.
     */
    public void postOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if(node == null) {
            sb.append("null\n");
        } else {
            postOrderTraverse(node.left, depth + 1, sb);
            postOrderTraverse(node.right, depth + 1, sb);
            sb.append(node.toString() + "\n");
        }
    }

    /**
     /**
     * DESCRIPTION:    This method builds a binary tree with the respective node data using a Scanner object.
     *                 The tree is built recursively, with the left and right subtrees being scanned simultaneously.
     * PRE-CONDITION:  Assumes that a Scanner object exists and contains
     * POST-CONDITION: N/A.
     *
     * @param scan Scanner object
     * @return new BinaryTree object
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        String data = scan.next();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }

    /**
     * Inner Node class.
     *
     * @param <E> Generic type.
     */
    protected static class Node<E> implements Serializable {

        /** Generic data type */
        protected E data;
        /** Left node of given root */
        protected Node<E> left;
        /** Right node of given root */
        protected Node<E> right;

        /**
         * DESCRIPTION:    Node constructor.
         * PRE-CONDITION:  Assumes that a Scanner object exists and contains
         * POST-CONDITION: N/A.
         *
         * @param data Generic param
         */
        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        /**
         * Converts Node data to a String object
         * @return String containing data
         */
        public String toString() {
            return data.toString();
        }
    }
}
