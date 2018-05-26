package edu.miracosta.cs113;

/**
 * BinarySearchTree.java
 * Class containing methods for the Binary Search Tree data structure. Extends Comparable Java interface.
 * This class is a node-based binary tree data structure which has the following properties:
 * 1. The left subtree of a node contains only nodes with keys lesser than the node's key.
 * 2. The right subtree of a node contains only nodes with keys greater than the node's key.
 * 3. The left and right subtree each must also be a binary search tree.
 * 4. There must be no duplicate nodes.
 * The above properties of Binary Search Tree provide an ordering among keys so that the operations like search,
 * minimum, and maximum can be done fast. If there is no ordering, then we may have to compare every key to
 * search a given key.
 *
 * @version 1.0
 * @author Reesha Rajen
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E> {

    //INSTANCE VARIABLES
    protected boolean addReturn;
    protected E deleteReturn;

    /**
     * DESCRIPTION:    Starter method add.
     * PRE-CONDITION:  The object to insert must implement the Comparable interface.
     * POST-CONDITION: N/A.
     * @param item The object being inserted
     * @return true if the object is inserted, false if the object already exists in the tree
     */
    public boolean add(E item) {
        root = add(root, item);
        return addReturn;
    }

    /**
     * DESCRIPTION:    Recursive add method.
     * PRE-CONDITION:  The starter add method has been called.
     * POST-CONDITION: The data field addReturn is set true if the item is added to the tree, false if the item
     *                 is already in the tree.
     * @param localRoot The local root of the subtree.
     * @param item The object to be inserted.
     * @return The new local root that now contains the inserted item.
     */
    private Node<E> add(Node<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree — insert it.
            addReturn = true;
            return new Node<E>(item);
        } else if (item.compareTo(localRoot.data) == 0) {
            // item is equal to localRoot.data
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item is less than localRoot.data
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } else {
            // item is greater than localRoot.data
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /**
     * DESCRIPTION:    Returns true if target is located within the tree.
     * PRE-CONDITION:  Assumes that a BST object has been created.
     * POST-CONDITION: N/A.
     * @param target The item to check for.
     * @return True if target is found in the tree.
     */
    @Override
    public boolean contains(E target) {
        return find(target) != null;
    }

    /**
     * DESCRIPTION:    Starter method find.
     * PRE-CONDITION:  The target object must implement the Comparable interface.
     * POST-CONDITION: N/A.
     * @param target The item to search for in the tree.
     * @return The object, if found, otherwise, return null.
     */
    public E find(E target) {
        return find(root, target);
    }

    /**
     * DESCRIPTION:    Recursive find method.
     * PRE-CONDITION:  The starter find method has been called.
     * POST-CONDITION: N/A.
     * @param localRoot The root of the local subtree.
     * @param target The object being sought.
     * @return The object, if found, otherwise, return null.
     */
    private E find(BinaryTree.Node<E> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }
        //Compare the target with the data field at the root
        int compResult = target.compareTo(localRoot.data);
        if (compResult == 0) {
            return localRoot.data;
        } else if (compResult < 0) {
            return find(localRoot.left, target);
        } else {
            return find(localRoot.right, target);
        }
    }

    /**
     * DESCRIPTION:    Starter delete method.
     * PRE-CONDITION:  N/A.
     * POST-CONDITION: The object is no longer within the tree.
     * @param target The item to delete in the tree.
     * @return The object deleted from the tree or null if the object was not found in the tree.
     * @throws ClassCastException if target does not implement the Comparable interface.
     */
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    /**
     * DESCRIPTION:    Recursive delete method.
     * PRE-CONDITION:  The starter delete method has been called.
     * POST-CONDITION: The item is not in the tree; deleteReturn is equal to the deleted item as it was stored
     *                 in the tree, or null, if the item was not found.
     * @param localRoot The root of the current subtree.
     * @param item The item to be deleted.
     * @return The modified local root that does not contain the item.
     */
    private BinaryTree.Node<E> delete(BinaryTree.Node<E> localRoot, E item) {
        if(localRoot == null) {
            //item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }
        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if(compResult < 0) {
            //item is smaller than localRoot.data
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if(compResult > 0) {
            //item is larger than localRoot.data
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else {
            //item is at local root
            deleteReturn = localRoot.data;
            if(localRoot.left == null) {
                return localRoot.right; //If there is no left child, return the right child, can also be null

            } else if(localRoot.right == null) {
                return localRoot.left; //If there is no right child, return the left child

            } else { //Node being deleted has two children, replace the data with inorder predecessor
                if(localRoot.left.right == null) {
                    localRoot.data = localRoot.left.data; //The left child has no right child, replace with data in the left child
                    localRoot.left = localRoot.left.left; //Replace the left child with its left child

                    return localRoot;
                }
                else {
                    //Search for the inorder predecessor (ip) and
                    //replace deleted node's data with ip
                    localRoot.data = findLargestChild(localRoot.left); //Search for the inorder predecessor
                    return localRoot;
                }
            }
        }
    }

    /**
     * DESCRIPTION:    Find the node that is the in-order and predecessor and replace with its left child (if any).
     * PRE-CONDITION:  N/A.
     * POST-CONDITION: Its left child, if any.
     * @param parent The parent of possible inorder predecessor (ip).
     * @return The data in the inorder predecessor (ip).
     */
    private E findLargestChild(BinaryTree.Node<E> parent) {
        //If the right child has no right child, it is
        //the inorder predecessor(ip)
        if(parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;

            return returnValue;
        }
        else {
            return findLargestChild(parent.right);
        }
    }

    /**
     * DESCRIPTION:    Removes target (if found) from tree and returns true; otherwise, returns false.
     * PRE-CONDITION:  N/A.
     * POST-CONDITION: N/A.
     * @param target The item to remove.
     * @return Boolean containing true or false.
     */
    @Override
    public boolean remove(E target) {
        return delete(target) != null;
    }
}
