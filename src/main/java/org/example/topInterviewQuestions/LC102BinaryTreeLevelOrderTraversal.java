package org.example.topInterviewQuestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC102BinaryTreeLevelOrderTraversal {
//    Given the root of a binary tree, return the level order traversal of its nodes' values.
//    (i.e., from left to right, level by level).
//
//    Example 1:
//    Input: root = [3,9,20,null,null,15,7]
//    Output: [[3],[9,20],[15,7]]
//
//    Example 2:
//    Input: root = [1]
//    Output: [[1]]
//
//    Example 3:
//    Input: root = []
//    Output: []
//
//
//    Constraints:
//
//            The number of nodes in the tree is in the range [0, 2000].
//            -1000 <= Node.val <= 1000


    // My Solution, Easy to understand, Next is also good.
    // Next Solution is more good and Best
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        int height = height(root);
        for (int i = 0; i < height; i++) {
            ans.add(new ArrayList<>());
        }
        // List<Integer> firstList = new ArrayList<>();
        Queue<NodeWithLevel> q = new LinkedList<>();
        q.offer(new NodeWithLevel(0, root));

        while (!q.isEmpty()) {
            NodeWithLevel node = q.poll();
            ans.get(node.level).add(node.node.val);
            if (node.node.left!= null) {
                q.offer(new NodeWithLevel(node.level + 1, node.node.left));
            }
            if (node.node.right != null) {
                q.offer(new NodeWithLevel(node.level + 1, node.node.right));
            }
        }
        return ans;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
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

    class NodeWithLevel{
        int level;
        TreeNode node;
        NodeWithLevel() {}
        NodeWithLevel(int level, TreeNode node) {
            this.level = level;
            this.node = node;
        }
    }

    // Best, Even helpful in zigzag order also.
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        boolean leftToRight = true;

        while(!q.isEmpty()) {
            int levelSize = q.size();
            List<Integer> levelValues = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();

                levelValues.add(node.val);

                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }

            list.add(levelValues);
            leftToRight = !leftToRight;
        }
        return list;
    }
}
