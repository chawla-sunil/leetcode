package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC94BinaryTreeInorderTraversal {
//    Given the root of a binary tree, return the inorder traversal of its nodes' values.
//
//    Example 1:
//    Input: root = [1,null,2,3]
//    Output: [1,3,2]
//
//    Example 2:
//    Input: root = []
//    Output: []
//
//    Example 3:
//    Input: root = [1]
//    Output: [1]
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 100].
//            -100 <= Node.val <= 100
//
//    Follow up: Recursive solution is trivial, could you do it iteratively?
    


    // Recursion Approach, Best
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderRecursion(root, list);
        return list;
    }

    private void inorderRecursion(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderRecursion(root.left, list);
        list.add(root.val);
        inorderRecursion(root.right, list);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while(node != null || !stack.isEmpty()) {
            while(node != null) {
                stack.add(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
        return list;
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
