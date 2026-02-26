package org.example.top_interview_questions;

import java.util.HashMap;
import java.util.Map;

public class LC106ConstructBinaryTreeFromInorderAndPostorderTraversal {
//    Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree
//    and postorder is the postorder traversal of the same tree, construct and return the binary tree.
//
//
//
//    Example 1:
//    Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
//    Output: [3,9,20,null,null,15,7]
//
//    Example 2:
//    Input: inorder = [-1], postorder = [-1]
//    Output: [-1]
//
//
//    Constraints:
//
//    1 <= inorder.length <= 3000
//    postorder.length == inorder.length
//    -3000 <= inorder[i], postorder[i] <= 3000
//    inorder and postorder consist of unique values.
//    Each value of postorder also appears in inorder.
//    inorder is guaranteed to be the inorder traversal of the tree.
//    postorder is guaranteed to be the postorder traversal of the tree.


    // Both methods take exact same time on leetcode.
    /**
     * Method 2: Using Low/High Pointers (Optimized approach)
     *
     * Approach:
     * - Use a global pointer (postIndex) to traverse postorder backwards
     * - Use 'low' and 'high' to track valid range in inorder array
     * - Don't create new arrays; just update pointer ranges
     *
     * How it works:
     * - low(inStart) and high(inEnd) define valid range in inorder for current subtree
     * - Start from last element of postorder (high to low traversal)
     * - postorder[postIndex] is the current root
     * - Decrement postIndex after processing each node
     * - Recursively build RIGHT subtree first, then LEFT subtree
     *
     * Why RIGHT first?
     * - Postorder visits: Left -> Right -> Root
     * - We read backwards, so we encounter Right subtree before Left
     *
     * Time: O(n) - each node visited once
     * Space: O(h) - only recursion stack, no extra arrays (h = height)
     */
    // Youtube video for explaination :
    // https://www.youtube.com/watch?v=ihj4IQGZ2zc
    // This youtube video is to Construct Binary Tree from Inorder and Preorder Traversal - Leetcode 105
    // but solution and approach is same. helpful video
    int pIndex = 0;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        // Start postIndex from end of postorder array
        pIndex = postorder.length - 1;
        // OR we can do this to track it instead of initializing globally =>
        // int[] postIndex = {postorder.length - 1};

        return makeTree(inorderMap, postorder, 0, inorder.length - 1);
    }

    public TreeNode makeTree(Map<Integer, Integer> inorderMap, int[] postorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        int rootVal = postorder[pIndex];
        pIndex--;
        int inIndex = inorderMap.get(rootVal); // Find root position in inorder (this divides left and right)

        TreeNode root = new TreeNode(rootVal);

        // Build RIGHT subtree first (because we read postorder backwards)
        // Right subtree valid range in inorder: [rootIndex+1, high]
        root.right = makeTree(inorderMap, postorder, inIndex + 1, inEnd);
        root.left = makeTree(inorderMap, postorder, inStart, inIndex - 1);
        // Build LEFT subtree second
        // Left subtree valid range in inorder: [low, rootIndex-1]

        return root;

    }



    /**
     * Method 2: Using Array Slicing (Intuitive and easy)
     *
     * Approach:
     * - The last element in postorder is always the root
     * - Find this root in inorder to split into left and right portions
     * - Recursively build left and right subtrees by creating new sub-arrays
     *
     * How it works:
     * - postorder[postEnd] is the root
     * - Find root position in inorder
     * - Left subtree: inorder[inStart...rootIndex-1] and postorder[postStart...rootIndex-1]
     * - Right subtree: inorder[rootIndex+1...inEnd] and postorder[rootIndex...postEnd-1]
     * - Slice arrays and recursively build subtrees
     *
     * Time: O(n) - each node visited once
     * Space: O(n) - new arrays created at each level + recursion stack
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return helper2(inorderMap, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode helper2(Map<Integer, Integer> inorderMap, int[] postorder,
                            int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        int rootVal = postorder[postEnd];
        int inIndex = inorderMap.get(rootVal); // mid of inorder, index of root

        // Calculate size of left subtree
        int leftNodesSize = inIndex - inStart;
        TreeNode root = new TreeNode(rootVal);

        // Build left subtree
        // Inorder left: [inStart, rootIndex-1]
        // Postorder left: [postStart, postStart+leftSize-1]
        root.left = helper2(inorderMap, postorder, inStart, inIndex - 1, postStart, postStart + leftNodesSize - 1);
        root.right = helper2(inorderMap, postorder, inIndex + 1, inEnd, postStart + leftNodesSize, postEnd - 1);
        // Build right subtree
        // Inorder right: [rootIndex+1, inEnd]
        // Postorder right: [postStart+leftSize, postEnd-1]

        return root;
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
