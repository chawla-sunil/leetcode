package org.example.top_interview_questions;

public class LC148SortList {
//    Given the head of a linked list, return the list after sorting it in ascending order.
//
//
//    Example 1:
//    Input: head = [4,2,1,3]
//    Output: [1,2,3,4]
//
//    Example 2:
//    Input: head = [-1,5,3,4,0]
//    Output: [-1,0,3,4,5]
//
//    Example 3:
//    Input: head = []
//    Output: []
//
//
//    Constraints:
//    The number of nodes in the list is in the range [0, 5 * 104].
//    -105 <= Node.val <= 105
//
//
//    Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next; // important part
        // Step 1: Split the list into two halves
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow is now mid point
        ListNode midNext = slow.next;
        slow.next = null;
        // now we have 2 points, 1: head, 2: midNext
        // Hypothysis: sortList will give us sorted LinkedList
        // so now we need to sort first half, and second half
        // first half's head is head and second half's head is midNext
        // We have done slow.next = null;, so first half stops there and has no further reference.

        // Step 2: Recursively sort each half
        ListNode left = sortList(head);
        ListNode right = sortList(midNext);

        // step 3. merge left and right
        return merge(left, right);
    }

    public ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (left != null && right != null) {
            if (left.val < right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

        if (left != null) {
            current.next = left;
        }
        if (right != null) {
            current.next = right;
        }

        return dummy.next;
    }

     // Definition for singly-linked list.
     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

}
