package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.List;

public class LC530MinimumAbsoluteDifferenceInBST {
//    Given the root of a Binary Search Tree (BST),
//    return the minimum absolute difference between the values of any two different nodes in the tree.
//
//
//
//    Example 1:
//    Input: root = [4,2,6,1,3]
//    Output: 1
//
//
//    Example 2:
//    Input: root = [1,0,48,null,null,12,49]
//    Output: 1
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [2, 104].
//            0 <= Node.val <= 105
//
//
//    Note: This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/


    // InOrder Traversal of a Binary Search Tree always Gives a sorted Array
    // and after that just calculate the distance between the 2 adjacent number of the array.
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size() - 1; i++) {
            min = Math.min(min, list.get(i+1) - list.get(i));
        }

        return min;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }



    int min = Integer.MAX_VALUE;
    TreeNode prev = null;
    public int getMinimumDifference2(TreeNode root) {
        if (root == null) {
            return min;
        }

        getMinimumDifference2(root.left);

        if (prev != null) {
            min = Math.min(min, root.val - prev.val);
        }
        prev = root;

        getMinimumDifference2(root.right);

        return min;
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
