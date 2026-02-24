package org.example.top_interview_questions;

public class LC82RemoveDuplicatesFromSortedListII {
//    Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
//    leaving only distinct numbers from the original list. Return the linked list sorted as well.
//
//
//
//    Example 1:
//    Input: head = [1,2,3,3,4,4,5]
//    Output: [1,2,5]
//
//    Example 2:
//    Input: head = [1,1,1,2,3]
//    Output: [2,3]
//
//
//    Constraints:
//
//    The number of nodes in the list is in the range [0, 300].
//            -100 <= Node.val <= 100
//    The list is guaranteed to be sorted in ascending order.

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                // Skip all nodes with the same value
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // Remove the entire duplicate group
                prev.next = head.next;
            } else {
                // Move 'prev' forward only if no duplicate was found
                prev = prev.next;
            }
            head = head.next;
        }
        return dummy.next;
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
