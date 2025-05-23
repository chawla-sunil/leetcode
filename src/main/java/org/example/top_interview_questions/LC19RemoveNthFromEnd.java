package org.example.top_interview_questions;

public class LC19RemoveNthFromEnd {

//    Given the head of a linked list, remove the nth node from the end of the list and return its head.
//
//    Example 1:
//    Input: head = [1,2,3,4,5], n = 2
//    Output: [1,2,3,5]
//    Example 2:
//
//    Input: head = [1], n = 1
//    Output: []
//    Example 3:
//
//    Input: head = [1,2], n = 1
//    Output: [1]
//
//    Constraints:
//    The number of nodes in the list is sz.
//            1 <= sz <= 30
//            0 <= Node.val <= 100
//            1 <= n <= sz

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {return null;}
        ListNode curr = head;
        int total = 0;
        while(curr != null) {
            total += 1;
            curr = curr.next;
        }
        if (total == n) {return head.next;}
        curr = head;

        for (int i = 1; i < total - n ; i++) {
            curr = curr.next;
        }
        ListNode newNode = curr.next;
        curr.next = newNode.next;
        return head;
    }

     private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeNthFromEndAnotherMethodInOneTraversal(ListNode head, int n) {
        ListNode fast = head, slow = head;
        for (int i = 0; i < n; i++) fast = fast.next;
        if (fast == null) return head.next;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
