package org.example.top_interview_questions;

public class LC114FlattenBinaryTreeToLinkedList {
//    Given the root of a binary tree, flatten the tree into a "linked list":
//
//    The "linked list" should use the same TreeNode class where
//    the right child pointer points to the next node in the list and the left child pointer is always null.
//    The "linked list" should be in the same order as a pre-order traversal of the binary tree.
//
//
//    Example 1:
//    Input: root = [1,2,5,3,4,null,6]
//    Output: [1,null,2,null,3,null,4,null,5,null,6]
//
//
//    Example 2:
//    Input: root = []
//    Output: []
//
//
//    Example 3:
//    Input: root = [0]
//    Output: [0]
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 2000].
//            -100 <= Node.val <= 100
//
//
//    Follow up: Can you flatten the tree in-place (with O(1) extra space)?


    // Solution explained here, link:
    // https://leetcode.com/problems/flatten-binary-tree-to-linked-list/solutions/1207642/js-python-java-c-simple-o1-space-recursi-b73z
    // This algorithm is called Morris Traversal
    // In this we will attach left point of the node in between of
    // node and node's right
    /**
     * Iterative approach to flatten binary tree to linked list (in-place).
     * Strategy: For each node with a left child, attach its right subtree
     * to the rightmost node of the left subtree, then move left subtree to right.
     * Time: O(n), Space: O(1)
     */
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            // Only process nodes that have a left child
            if (curr.left != null) {
                // Find the rightmost node in the left subtree
                TreeNode runner = curr.left;
                while (runner.right != null) {
                    runner = runner.right;
                }
                // Attach current node's right subtree to rightmost node of left subtree
                runner.right = curr.right;
                // Move left subtree to right position
                curr.right = curr.left;
                // Clear left pointer
                curr.left = null;
            }
            // Move to next node in flattened list
            curr = curr.right;
        }
    }

    // Recursion
    /**
     * Recursive approach to flatten binary tree to linked list.
     * Strategy: Reverse pre-order traversal (right->left->root) builds the
     * flattened list from end to beginning. Each node points to the previously
     * processed node (head), creating a linked list in pre-order sequence.
     * Time: O(n), Space: O(h) where h is tree height (recursion stack)
     */
    TreeNode head = null;
    public void flatten2(TreeNode root) {
        if (root != null) {
            reversePreOrder(root);
        }
    }

    public void reversePreOrder(TreeNode node) {
        // Process right subtree first
        if (node.right != null) {
            reversePreOrder(node.right);
        }
        // Then process left subtree
        if (node.left != null) {
            reversePreOrder(node.left);
        }
        // Finally, link current node to the previously built list
        node.left = null;           // Clear left pointer
        node.right = head;          // Point right to previous head
        head = node;                // Update head to current node
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
