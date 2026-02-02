package org.example.dp_aditya_verma;

import org.example.dp_aditya_verma.DP26LC124BinaryTreeMaximumPathSum.TreeNode;

public class DP27MaximumPathSumBetweenTwoLeavesOfABinaryTree {
    // This is question of aditya verma dp series
    // Maximum Path sum | From leaf node to leaf node

//    Given a binary tree in which each node element contains a number. Find the maximum possible sum from one leaf node to another.
//    gfg: https://www.geeksforgeeks.org/dsa/find-maximum-path-sum-two-leaves-binary-tree/



    int max = Integer.MIN_VALUE;
    // main function
    public int maxPathSumBetweenTwoLeaves(TreeNode root) {
        maxPathSumRecursion(root);
        return max;
    }

    public int maxPathSumRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = maxPathSumRecursion(root.left);
        int r = maxPathSumRecursion(root.right);

        // If both left and right children are present
        if (root.left != null && root.right != null) {
            int currentMaxPath = l + r + root.val; // taking root in answer
            max = Math.max(max, currentMaxPath);
            return Math.max(l, r) + root.val; // we have to pass the answer to parent node
        }

        // If any of the two children is missing, return root sum for root being on one side
        return (root.left == null) ? r + root.val : l + root.val;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
