package edu.miracosta.cs113;

/**
 * AVLTree.java
 * This class implements a self-balancing binary search tree using the algorithm defined by Adelson-Velskii and Landis.
 * The AVL tree is a type of Binary Search Tree where the difference between heights of left and right subtrees
 * cannot be more than one for all nodes.
 *
 * @author Reesha Rajen
 * @version 1.0
 * @lastModified 08 May 2018
 *
 * @param <E> Generic object type.
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {

    /** Data fields */
    private boolean increase;

    /**
     * DESCRIPTION:    Add starter method.
     * PRE-CONDITION:  THe item to insert implements the Comparable interface.
     * POST-CONDITION: N/A.
     *
     * @param item The item being inserted.
     * @return True if the object is inserted; false if the object already exists in the tree.
     * @throws ClassCastException if item is not Comparable.
     */
    @Override
    public boolean add(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }

    /**
     * DESCRIPTION:    This method implements the insertion algorithm and serves as the recursive add method. This
     *                 method inserts the given object into the tree. It will set the data field addReturn to true
     *                 (inherited from class BinarySearchTree) if the item is inserted and false if the item is
     *                 already within the tree.
     * PRE-CONDITION:  N/A.
     * POST-CONDITION: Data field addReturn is set to true if the item is inserted, false if the item is already in
     *                 the tree.
     * @param localRoot THe local root of the subtree.
     * @param item The object to be inserted.
     * @return The new local root of the subtree with the item inserted.
     */
    private AVLNode<E> add(AVLNode<E> localRoot, E item) {
        // Begin by seeing whether the localRoot is null. If it is, then we set addReturn and increase to true and
        // return a new AVLNode, which contains the item to be inserted.
        if (localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode<>(item);
        }
        // Next, compare the inserted item with the data field of the current node. If it is equal, set
        // addReturn and increase to false and return the localRoot unchanged.
        if (item.compareTo(localRoot.data) == 0) {
            // Item is already in the tree
            increase = false;
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item < data
            localRoot.left = add((AVLNode<E>) localRoot.left, item);
            /** Check if increase has been set to true. */
            if (increase) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
        } else {
            // item > data
            localRoot.right = add((AVLNode<E>) localRoot.right, item);
            /** Check if increase has been set to true. */
            if (increase) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }
        }
        /** Rebalancing is not needed. */
        return localRoot;
    }

    /**
     * DESCRIPTION:    This method is responsible for decrementing the balance of the node as we return from an
     *                 insertion into a node's left subtree. This method also indicates whether the subtree height
     *                 at that node as not increased by setting increase (currently true) to false. There are two cases
     *                 to consider: a node that is balanced and a node that is right-heavy. If a node is balanced,
     *                 insertion into its left subtree will cause it to become left-heavy, and its height will also
     *                 increase by 1. If a node is right-heavy, insertion into its left subtree will cause it to become
     *                 balanced, and its height will not increase.
     * PRE-CONDITION:  Assumes that an AVLTree object is created and contains nodes.
     * POST-CONDITION: Decrements the balance of a node.
     * @param node Node parameter.
     */
    private void decrementBalance(AVLNode<E> node) {
        // Decrement the balance.
        node.balance--;
        if (node.balance == AVLNode.BALANCED) {
            /** If now balanced, overall height has not increased. */
            increase = false;
        }
    }

    /**
     * DESCRIPTION:    This method is responsible for incrementing the balance of the node as we return from an
     *                 insertion into a node's left subtree. This method parallels decrementBalance as the logic
     *                 is similar in structure.
     * PRE-CONDITION:  Assumes that an AVLTree object is created and contains nodes.
     * POST-CONDITION: Increments the balance of a node.
     * @param node Node parameter.
     */
    private void incrementBalance(AVLNode<E> node) {
        // Increment the balance.
        node.balance++;
        if (node.balance == AVLNode.BALANCED) {
            increase = false;
        }
    }

    /**
     * DESCRIPTION:    This method is responsible for rebalancing the left side of the tree. If the left child
     *                 ia left heavy, the rotation process restores the balance to both the tree and its left
     *                 subtree and reducing its overall height by 1.
     * PRE-CONDITION:  Data field localRoot is the root of an AVL subtree that is critically left-heavy.
     * POST-CONDITION: Balance is restored.
     * @param localRoot Root of the AVL subtree that needs rebalancing.
     * @return A new localRoot.
     */
    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        // Obtain a reference to the left child.
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
        // See whether left-right heavy.
        if (leftChild.balance > AVLNode.BALANCED) {
            // Obtain reference to left-right child.
            AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
            /** Adjust the balances to b their new values after the rotations are performed. */
            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            } else if (leftRightChild.balance > AVLNode.BALANCED) {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else {
                leftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform left rotation.
            localRoot.left = rotateLeft(leftChild);
        } else { // Left-left case
            /** In this case the leftChild (the new root) and the root (new right child) will both be balanced
             *  after the rotation. */
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Now rotate the local root right.
        return (AVLNode<E>) rotateRight(localRoot);
    }

    /**
     * DESCRIPTION:    This method is responsible for rebalancing the right side of the tree. If the right child
     *                 ia right heavy, the rotation process restores the balance to both the tree and its right
     *                 subtree and reducing its overall height by 1.
     * PRE-CONDITION:  Data field localRoot is the root of an AVL subtree that is critically right-heavy.
     * POST-CONDITION: Balance is restored.
     * @param localRoot Root of the AVL subtree that needs rebalancing.
     * @return A new localRoot.
     */
    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
        // Obtain a reference to the right child.
        AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
        // See whether left-right heavy.
        if (rightChild.balance < AVLNode.BALANCED) {
            // Obtain a reference to the right-left child.
            AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;
            /** Adjust the balances to b their new values after the rotations are performed. */
            if (rightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            } else if (rightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else {
                rightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform right rotation.
            localRoot.right = rotateRight(rightChild);
        } else { // Right-right case
            /** In this case the rightChild (the new root) and the root (new left child) will both be balanced
             *  after the rotation. */
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Now rotate the local root left.
        return (AVLNode<E>) rotateLeft(localRoot);
    }

    /**
     * DESCRIPTION: This class is an inner class representing an AVL Node. It extends the BinaryTree.Node class
     *              by adding the balance field.
     *
     * @param <E> Generic object type of AVL Node.
     */
    private static class AVLNode<E> extends Node<E> {
        /** Constant to indicate left-heavy */
        public static final int LEFT_HEAVY = -1;
        /** Constant to indicate balanced */
        public static final int BALANCED = 0;
        /** Constant to indicate right-heavy */
        public static final int RIGHT_HEAVY = 1;
        /** Balance is height of the right subtree - height of the left subtree */
        private int balance;

        // Methods
        /**
         * DESCRIPTION:    Construct a node with the given item as the data field.
         * PRE-CONDITION:  N/A.
         * POST-CONDITION: N/A.
         *
         * @param item The data field.
         */
        public AVLNode(E item) {
            super(item);
            balance = BALANCED;
        }

        /**
         * DESCRIPTION:    Returns a string representation of this object. The balance value is now appended to the
         *                 contents.
         * PRE-CONDITION:  N/A.
         * POST-CONDITION: N/A.
         *
         * @return String representation of this object.
         */
        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    } // END of AVLNode inner class.
}
