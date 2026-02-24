package org.example.top_interview_questions;

public class LC100SameTree {
//    Given the roots of two binary trees p and q, write a function to check if they are the same or not.
//
//    Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
//
//
//
//    Example 1:
//    Input: p = [1,2,3], q = [1,2,3]
//    Output: true
//
//    Example 2:
//    Input: p = [1,2], q = [1,null,2]
//    Output: false
//
//    Example 3:
//    Input: p = [1,2,1], q = [1,1,2]
//    Output: false
//
//
//    Constraints:
//
//    The number of nodes in both trees is in the range [0, 100].
//            -104 <= Node.val <= 104

    // Both are same solution, 2nd is just a little optimised
    // this is good to understand first
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        if (p == null) { // this means q is also null, because we filtered from above if condition,
            // this means q is also null, because we filtered from above if condition,
            // so no need to write
            return true;
        }
        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        if (p == null) { // this means q is also null, because we filtered from above if condition,
            // this means q is also null, because we filtered from above if condition,
            // so no need to write
            return true;
        }
        if (p.val != q.val) {
            return false;
        }

        // By this way we won't need to check for right if we find left tree false;
        boolean checkLeft = isSameTree(p.left, q.left);
        if (!checkLeft) {
            return false;
        }
        return isSameTree(p.right, q.right);
    }


     // Definition for a binary tree node.
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
