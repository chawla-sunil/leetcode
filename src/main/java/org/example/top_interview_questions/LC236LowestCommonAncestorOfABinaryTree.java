package org.example.top_interview_questions;

public class LC236LowestCommonAncestorOfABinaryTree {
//    Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
//
//    According to the definition of LCA on Wikipedia:
//    “The lowest common ancestor is defined between two nodes p and q as the lowest node in T
//    that has both p and q as descendants (where we allow a node to be a descendant of itself).”
//
//
//
//    Example 1:
//    Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//    Output: 3
//    Explanation: The LCA of nodes 5 and 1 is 3.
//
//    Example 2:
//    Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//    Output: 5
//    Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
//
//    Example 3:
//    Input: root = [1,2], p = 1, q = 2
//    Output: 1
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [2, 105].
//            -109 <= Node.val <= 109
//    All Node.val are unique.
//            p != q
//    p and q will exist in the tree.


    // VISUAL EXAMPLE — Find LCA of 6 and 2:
    //
    //         3
    //        / \
    //       5   1
    //      / \
    //     6   2
    //
    //   Node 6 → base case (6 == p)                          → returns 6
    //   Node 2 → base case (2 == q)                          → returns 2
    //   Node 5 → left=6 (non-null), right=2 (non-null)       → returns 5  ✅ LCA
    //   Node 1 → left=null, right=null                        → returns null
    //   Node 3 → left=5 (non-null), right=null                → bubbles up 5
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // If we hit null, there's nothing here → return null.
        // If the current node is p or q, return it upward — we found one of the targets.
        if (root == null || root == p || root == q) {
            return root;
        }

        // Search the left subtree for p or q.
        // Search the right subtree for p or q.
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root; // p and q are on DIFFERENT sides → current node is the LCA
        }

        // both are on the SAME side → bubble up the result (pass it to upper function)
        return left != null ? left : right;
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
