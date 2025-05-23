package org.example.top_interview_questions;

public class LC124BinaryTreeMaximumPathSum {
//    A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
//    has an edge connecting them. A node can only appear in the sequence at most once. Note that the path
//    does not need to pass through the root.
//
//    The path sum of a path is the sum of the node's values in the path.
//
//    Given the root of a binary tree, return the maximum path sum of any non-empty path.
//
//    Example 1:
//    Input: root = [1,2,3]
//    Output: 6
//    Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
//
//    Example 2:
//    Input: root = [-10,9,20,null,null,15,7]
//    Output: 42
//    Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
//
//    Constraints:
//            The number of nodes in the tree is in the range [1, 3 * 10^4].
//            -1000 <= Node.val <= 1000


    // nice explaination => https://leetcode.com/problems/binary-tree-maximum-path-sum/?envType=featured-list&envId=top-interview-questions?envType=featured-list&envId=top-interview-questions
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumRecursion(root);
        return max;
    }

    public int maxPathSumRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = maxPathSumRecursion(root.left);
        int r = maxPathSumRecursion(root.right);

        l = Math.max(l,0); // l,r can be negative, so we will not take -ve and take 0
        r = Math.max(r,0);

        int currentMaxPath = l + r + root.val; // taking root in answer
        max = Math.max(max, currentMaxPath);

        return Math.max(l, r) + root.val; // we have to pass the answer to parent node
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
