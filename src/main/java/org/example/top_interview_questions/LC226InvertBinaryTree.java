package org.example.top_interview_questions;

import java.util.Stack;

public class LC226InvertBinaryTree {
//    Given the root of a binary tree, invert the tree, and return its root.
//
//
//
//    Example 1:
//    Input: root = [4,2,7,1,3,6,9]
//    Output: [4,7,2,9,6,3,1]
//
//
//    Example 2:
//    Input: root = [2,1,3]
//    Output: [2,3,1]
//
//
//    Example 3:
//    Input: root = []
//    Output: []
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 100].
//            -100 <= Node.val <= 100

    // Recursion, easy and understandable function
    // Both functions have same time complexity
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    // Using DFS, stack
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        final Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            final TreeNode node = stack.pop();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if(node.left != null) {
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
        }

        return root;
    }

    public class TreeNode {
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
