package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.List;

public class LC199BinaryTreeRightSideView {
//    Given the root of a binary tree, imagine yourself standing on the right side of it,
//    return the values of the nodes you can see ordered from top to bottom.
//
//
//
//    Example 1:
//    Input: root = [1,2,3,null,5,null,4]
//    Output: [1,3,4]
//    Explanation:
//
//
//
//    Example 2:
//    Input: root = [1,2,3,4,null,null,null,5]
//    Output: [1,3,4,5]
//    Explanation:
//
//
//
//    Example 3:
//    Input: root = [1,null,3]
//    Output: [1,3]
//
//    Example 4:
//    Input: root = []
//    Output: []
//
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 100].
//            -100 <= Node.val <= 100


    // Uses DFS (right child first) to capture the first visible node at each depth level.
    // maxHeight tracks the deepest level seen so far — only adds a node if it's the
    // first one encountered at a new level (i.e., the rightmost node at that depth).
    // Recursively traverses right subtree before left.
    // If current height exceeds maxHeight, this node is the rightmost at this level, we add it.
    int maxHeight = -1;
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, 0, res);
        return res;
    }

    public void helper(TreeNode node, int height, List<Integer> res) {
        if (node == null) {
            return;
        }
        if (height > maxHeight) {
            res.add(node.val);
            maxHeight = height;
        }
        helper(node.right, height + 1, res);
        helper(node.left, height + 1, res);
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
