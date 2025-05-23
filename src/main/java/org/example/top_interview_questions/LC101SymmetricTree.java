package org.example.top_interview_questions;

import java.util.LinkedList;
import java.util.Queue;

public class LC101SymmetricTree {
//    Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
//
//    Example 1:
//    Input: root = [1,2,2,3,4,4,3]
//    Output: true
//
//    Example 2:
//    Input: root = [1,2,2,null,3,null,3]
//    Output: false
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 1000].
//            -100 <= Node.val <= 100
//
//
//    Follow up: Could you solve it both recursively and iteratively?


    // Recursive Solution, Best
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        return left.val == right.val && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    // Iterative Approach
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root.left);
        q.offer(root.right);

        while(!q.isEmpty()) {
            TreeNode left = q.poll();
            TreeNode right = q.poll();

            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }

            q.offer(left.left);
            q.offer(right.right);
            q.offer(left.right);
            q.offer(right.left);
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
