package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC637AverageOfLevelsInBinaryTree {
//    Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
//    Answers within 10-5 of the actual answer will be accepted.
//
//
//    Example 1:
//    Input: root = [3,9,20,null,null,15,7]
//    Output: [3.00000,14.50000,11.00000]
//    Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
//    Hence return [3, 14.5, 11].
//
//    Example 2:
//    Input: root = [3,9,20,15,7]
//    Output: [3.00000,14.50000,11.00000]
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 104].
//            -231 <= Node.val <= 231 - 1

    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<Double> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double sum = 0.0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                sum += node.val;
            }
            res.add(sum / levelSize);
        }

        return res;
    }


    // Simple and easy and logic is also correct
    // Some Leetcode cases are failing
    // The logic in this code is almost correct for calculating the average of each level in a binary tree,
    // but there's a subtle issue with how we arer calculating the average.
    // Specifically, dividing each node's value by levelSize as we iterate through the nodes of the current level,
    // which can lead to inaccuracies due to floating-point arithmetic.
    public List<Double> averageOfLevels2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<Double> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double average = 0.0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                // Here, adding the fraction of each node's value divided by the number of nodes in the level.
                // While this might seem correct, it can introduce rounding errors
                // because performing the division for each node individually.
                // example, if a level has 30-40 nodes and all nodes have value 0
                // Then the will come out as -0.00002, which can happen in double data type
                // So this appraoch is correct upto certain point
                average += ((double) node.val / levelSize);
            }
            res.add(average);
        }

        return res;
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
