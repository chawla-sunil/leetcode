package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC117PopulatingNextRightPointersInEachNodeII {
//    Given a binary tree
//
//    struct Node {
//        int val;
//        Node *left;
//        Node *right;
//        Node *next;
//    }
//    Populate each next pointer to point to its next right node.
//    If there is no next right node, the next pointer should be set to NULL.
//
//    Initially, all next pointers are set to NULL.
//
//
//    Example 1:
//    Input: root = [1,2,3,4,5,null,7]
//    Output: [1,#,2,3,#,4,5,7,#]
//    Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
//
//    Example 2:
//    Input: root = []
//    Output: []
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 6000].
//    -100 <= Node.val <= 100
//
//
//    Follow-up:
//
//    You may only use constant extra space.
//    The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.

    // Simple and easy solution but uses O(N) space
    // Use BFS to level traversal, a List to store the Nodes of each level.
    public Node connect1(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelNodeCount = queue.size();
            List<Node> levelNodes = new ArrayList<>();

            for (int i = 0; i < levelNodeCount; i++) {
                Node currNode = queue.poll();
                levelNodes.add(currNode);

                if (currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if (currNode.right != null) {
                    queue.offer(currNode.right);
                }
            }

            for (int i = 0; i < levelNodeCount - 1; i++) {
                levelNodes.get(i).next = levelNodes.get(i+1);
            }
        }

        return root;
    }

    // A little better than last because it does not usese O(N) space
    // In fact, we just need a Node to store the Previous Node.
    // instead of List<Node> levelNodes = new ArrayList<>();
    public Node connect2(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;

            for (int i = 0; i < size; i++) {
                Node currNode = queue.poll();

                if (prev != null) {
                    prev.next = currNode;
                }
                prev = currNode;

                if (currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if (currNode.right != null) {
                    queue.offer(currNode.right);
                }
            }
        }

        return root;
    }

    // This and connect2 method are exact same
    public Node connect3(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = new Node(0);

            for (int i = 0; i < size; i++) {
                Node currNode = queue.poll();

                prev.next = currNode;
                prev = currNode;

                if (currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if (currNode.right != null) {
                    queue.offer(currNode.right);
                }
            }
        }

        return root;
    }

    // Each level can be seem as a Linked List.
    // For example, the root node is a linked list with one node,
    // and the second level is a linked list with two nodes and so on...
    // We are calculating/making next level linkedLinked from current level nodes.
    // And then when we go to next level,
    // we make linkedList of its next level as it is already linked in prev step/loop
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node currNode = root;
        while (currNode != null) {
            Node dummy = new Node(0);
            Node prev = dummy;

            while (currNode != null) {
                if (currNode.left != null) {
                    prev.next = currNode.left;
                    prev = currNode.left;
                }
                if (currNode.right != null) {
                    prev.next = currNode.right;
                    prev = currNode.right;
                }

                // the next node of current level
                currNode = currNode.next;
            }
            // Node one level is complete and
            // we need to create nexet level
            // after process the next level, process
            currNode = dummy.next;
        }

        return root;
    }

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
