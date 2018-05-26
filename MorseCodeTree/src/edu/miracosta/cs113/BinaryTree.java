package edu.miracosta.cs113;

import java.io.Serializable;
import java.util.Scanner;

/**
 * BinaryTree.java
 * Class containing methods for the Binary Tree data structure
 *
 * @version 1.0
 * @author Reesha Rajen
 */
public class BinaryTree<E> {
    //INSTANCE VARIABLES
    protected Node<E> root;

    //METHODS
    /**
     * Default constructor for edu.miracosta.cs113.BinaryTree, constructs Binary Tree with no data
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Incomplete constructor for edu.miracosta.cs113.BinaryTree, constructs Binary Tree
     * @param root Node param
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Full constructor for edu.miracosta.cs113.BinaryTree class, constructs full Binary Tree
     * @param data Node param containing root
     * @param leftTree Binary Tree param
     * @param rightTree Binary Tree param
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<E>(data);
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
     * Getter method for returning left subtree, assumes Binary Tree is created
     * @return Binary Tree containing left subtree
     */
    public BinaryTree<E> getLeftSubtree() {
        if(root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Getter method for returning right subtree, assumes Binary Tree is created
     * @return Binary Tree containing right subtree
     */
    public BinaryTree<E> getRightSubtree() {
        if(root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return null;
        }
    }

    /**
     * Checks if node of binary tree is a leaf
     * @return true if node.left is null and node.right is null simultaneously, does not return true otherwise
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    /**
     * Returns string containing all data within binary tree, utilizes preorder traversal
     * @return String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Preorder traverses a Binary Tree. Assumes binary tree is constructed with root.
     *
     * @param node Node representing the root of the tree/subtree
     * @param depth Depth of starting Node
     * @param sb StringBuilder object
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
     * Inorder traverses a Binary Tree. Assumes binary tree is constructed with root.
     *
     * @param node Node representing the root of the tree/subtree
     * @param depth Depth of starting Node
     * @param sb StringBuilder object
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
     * Postorder traverses a Binary Tree. Assumes binary tree is constructed with root.
     *
     * @param node Node representing the root of the tree/subtree
     * @param depth Depth of starting Node
     * @param sb StringBuilder object
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
     * Creates binary tree from Scanner parameter
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
            return new BinaryTree<String>(data, leftTree, rightTree);
        }
    }

    protected static class Node<E> implements Serializable {

        /** Generic data type */
        protected E data;
        /** Left node of given root */
        protected Node<E> left;
        /** Right node of given root */
        protected Node<E> right;

        /**
         * Node constructor
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
