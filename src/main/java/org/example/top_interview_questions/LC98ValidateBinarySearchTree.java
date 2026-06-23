package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC98ValidateBinarySearchTree {
//    Given the root of a binary tree, determine if it is a valid binary search tree (BST).
//    A valid BST is defined as follows:
//    -- The left subtree of a node contains only nodes with keys less than the node's key.
//    -- The right subtree of a node contains only nodes with keys greater than the node's key.
//    -- Both the left and right subtrees must also be binary search trees.
//
//
//    Example 1:
//    Input: root = [2,1,3]
//    Output: true
//
//    Example 2:
//    Input: root = [5,1,4,null,null,3,6]
//    Output: false
//    Explanation: The root node's value is 5 but its right child's value is 4.
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 10^4].
//            -(2^31) <= Node.val <= ((2^31) - 1)


    
    // Recursive inorder way, best, if it is valid BST, the inorder will be sorted
    // InOrder Traversal of a Binary Search Tree always Gives a sorted Array
    // so if it is not sorted, then it is not BST
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i-1)) {
                return false;
            }
        }

        return true;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }





    // To uderstand this, do 3 questions of Binary Search Tree from
    // Top Interview 150 question series.
    // Here, We are going inorder and inorder of BST is always sorted array
    // we will check prev node with current node and
    // if prev is bigger than current than than we save marked no-BST(isBST = false)
    TreeNode prev;
    boolean isBST = true;
    public boolean isValidBST2(TreeNode root) {
        helperInorder(root);
        return isBST;
    }

    public void helperInorder(TreeNode root) {
        if (root == null) {
            return;
        }

        helperInorder(root.left);

        if (prev != null) {
            if (prev.val >= root.val) {
                isBST = false;
            }
        }
        prev = root;

        helperInorder(root.right);
    }




    // iterative approach
    public boolean isValidBST3(TreeNode root) {
        if (root == null) {
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;

        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != null && prev.val >= root.val) {
                return false;
            }
            prev = root;
            root = root.right;
        }
        return true;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
