package org.example.top_interview_questions;

public class LC61RotateList {
//    Given the head of a linked list, rotate the list to the right by k places.
//
//
//
//    Example 1:
//    Input: head = [1,2,3,4,5], k = 2
//    Output: [4,5,1,2,3]
//
//    Example 2:
//    Input: head = [0,1,2], k = 4
//    Output: [2,0,1]
//
//
//    Constraints:
//
//    The number of nodes in the list is in the range [0, 500].
//            -100 <= Node.val <= 100
//            0 <= k <= 2 * 109

    // both are exact same solution but this is easy to understand
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = head;
        int total = 0;
        while (node != null) {
            total += 1;
            node = node.next;
        }
        k = k % total;
        if (k == 0) {
            return head;
        }

        node = head;
        int i = 1;
        while (i < (total - k)) {
            node = node.next;
            i++;
        }

        ListNode newStartNode = node.next;
        node.next = null;
        node = newStartNode;
        while (node.next != null) {
            node = node.next;
        }
        node.next = head;

        return newStartNode;
    }

    // the only difference is, in this solution
    // we save the last node of the head in dummy variable
    // so we don't have to write the last loop in first soution
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null) return head;

        int length = 1;
        ListNode dummy = head;

        while (dummy.next != null) {
            dummy = dummy.next;
            length++;
        }

        int position = k % length;
        if (position == 0) return head;

        ListNode current = head;
        for (int i = 0; i < length - position - 1; i++) {
            current = current.next;
        }

        ListNode newHead = current.next;
        current.next = null;
        dummy.next = head;

        return newHead;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
