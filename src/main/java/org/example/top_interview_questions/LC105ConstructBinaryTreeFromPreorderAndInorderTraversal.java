package org.example.top_interview_questions;

import java.util.HashMap;
import java.util.Map;

public class LC105ConstructBinaryTreeFromPreorderAndInorderTraversal {
//    Given two integer arrays preorder and inorder where preorder is the preorder traversal of a
//    binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
//
//            Example 1:
//    Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//    Output: [3,9,20,null,null,15,7]
//
//    Example 2:
//    Input: preorder = [-1], inorder = [-1]
//    Output: [-1]
//
//
//    Constraints:
//
//    1 <= preorder.length <= 3000
//    inorder.length == preorder.length
//    -3000 <= preorder[i], inorder[i] <= 3000
//    preorder and inorder consist of unique values.
//    Each value of inorder also appears in preorder.
//    preorder is guaranteed to be the preorder traversal of the tree.
//    inorder is guaranteed to be the inorder traversal of the tree.


    int pIndex = 0;
    // p = preorder
    // in = inorder
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }

        return makeTree(preorder, map, 0, n - 1);
    }

    private TreeNode makeTree(int[] preorder, Map<Integer, Integer> map, int low, int high) {
        if (low > high) {
            return null;
        }
        // p = preorder
        // in = inorder

        int pValue = preorder[pIndex];
        int inIndex = map.get(pValue);

        TreeNode root = new TreeNode(pValue);
        pIndex++;

        root.left = makeTree(preorder, map, low, inIndex - 1);
        root.right = makeTree(preorder, map, inIndex + 1, high);
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
