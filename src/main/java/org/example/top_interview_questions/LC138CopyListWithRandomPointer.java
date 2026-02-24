package org.example.top_interview_questions;

import java.util.HashMap;

public class LC138CopyListWithRandomPointer {
//    A linked list of length n is given such that each node contains an additional random pointer,
//    which could point to any node in the list, or null.
//
//    Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
//    where each new node has its value set to the value of its corresponding original node.
//    Both the next and random pointer of the new nodes should point to new nodes in the copied list
//    such that the pointers in the original list and copied list represent the same list state.
//    None of the pointers in the new list should point to nodes in the original list.
//
//    For example, if there are two nodes X and Y in the original list,
//    where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
//
//    Return the head of the copied linked list.
//
//    The linked list is represented in the input/output as a list of n nodes.
//    Each node is represented as a pair of [val, random_index] where:
//
//    val: an integer representing Node.val
//    random_index: the index of the node (range from 0 to n-1) that
//    the random pointer points to, or null if it does not point to any node.
//    Your code will only be given the head of the original linked list.
//
//
//
//    Example 1:
//    Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//    Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
//
//    Example 2:
//    Input: head = [[1,1],[2,1]]
//    Output: [[1,1],[2,1]]
//
//    Example 3:
//    Input: head = [[3,null],[3,0],[3,null]]
//    Output: [[3,null],[3,0],[3,null]]
//
//
//    Constraints:
//
//            0 <= n <= 1000
//            -104 <= Node.val <= 104
//            Node.random is null or is pointing to some node in the linked list.

    // With HashMap, easy and understandable
    // Both solutions have same time complexity and beats 100%
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node node = head;

        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }

        node = head;

        while (node != null) {
            Node copy = map.get(node);
            copy.next = map.get(node.next);
            copy.random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }

    // We will first attach the same val Node with the current node for every node
    // and then copy random node values
    // and then fix the original head node back to original
    // by removing original and copy from the merged node.
    public Node copyRandomList2(Node head) {
        Node node = head;
        Node next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (node != null) {
            next = node.next;

            node.next = new Node(node.val);
            node.next.next = next;

            node = next;
        }

        // Second round: assign random pointers for the copy nodes.
        node = head;
        while (node != null) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        Node dummyNode = new Node(0);
        Node res = dummyNode;
        node = head;

        while (node != null) {
            // extract the copy
            res.next = node.next;
            res = res.next;

            // restore the original list
            node.next = node.next.next;
            node = node.next;
        }

        return dummyNode.next;
    }

    // Definition for singly-linked list..
    class Node {

        int val;
        Node next;
        Node random;

        Node(int x) {
            val = x;
            next = null;
            random = null;
        }
    }
}
