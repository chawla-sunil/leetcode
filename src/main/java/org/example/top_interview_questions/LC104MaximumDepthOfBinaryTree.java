package org.example.top_interview_questions;

public class LC104MaximumDepthOfBinaryTree {
//    Given the root of a binary tree, return its maximum depth.
//
//    A binary tree's maximum depth is the number of nodes along the longest path
//    from the root node down to the farthest leaf node.
//
//    Example 1:
//    Input: root = [3,9,20,null,null,15,7]
//    Output: 3
//
//    Example 2:
//    Input: root = [1,null,2]
//    Output: 2
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 10^4].
//            -100 <= Node.val <= 100

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
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
