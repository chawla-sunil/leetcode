package org.example.two_pointers;

import java.util.HashSet;

public class LC653TwoSumIVInputIsABST {
//    Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST
//    such that their sum is equal to k, or false otherwise.
//
//
//    Example 1:
//    Input: root = [5,3,6,2,4,null,7], k = 9
//    Output: true
//
//    Example 2:
//    Input: root = [5,3,6,2,4,null,7], k = 28
//    Output: false
//
//    Constraints:
//    The number of nodes in the tree is in the range [1, 104].
//            -104 <= Node.val <= 104
//    root is guaranteed to be a valid binary search tree.
//            -105 <= k <= 105

    HashSet<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);

        return findTarget(root.left, k) || findTarget(root.right, k);
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
