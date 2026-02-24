package org.example.top_interview_questions;

public class LC92ReverseLinkedListII {
//    Given the head of a singly linked list and two integers left and right where left <= right,
//    reverse the nodes of the list from position left to position right, and return the reversed list.
//
//
//
//    Example 1:
//    Input: head = [1,2,3,4,5], left = 2, right = 4
//    Output: [1,4,3,2,5]
//
//    Example 2:
//    Input: head = [5], left = 1, right = 1
//    Output: [5]
//
//
//    Constraints:
//
//    The number of nodes in the list is n.
//    1 <= n <= 500
//            -500 <= Node.val <= 500
//            1 <= left <= right <= n
//
//
//    Follow up: Could you do it in one pass?

    // use these solution images for better understanding
    // link: https://leetcode.com/problems/reverse-linked-list-ii/solutions/2311084/javac-tried-to-explain-every-step-by-hi-5w5kl
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy; // previous from current node, such as dummy is previous from starting node
        ListNode curr = head; // prev.next // current node, such as head is current node for starting node

        for (int i = 1; i < left; i++) {
            prev = prev.next;
            curr = curr.next;
        }

        // Now prev is fix at left - 1 position.
        // and curr and forw will move, curr will move till right - 1,
        // so that means forw will move till right
        for (int i = 0; i < right - left; i++) {
            ListNode forw = curr.next; // 1=> curr se forward nikala
            curr.next = forw.next;     // 2=> curr ke aage forward ke aage wala lagado
            forw.next = prev.next;     // 3=> forw ko sabse aage laana matalab forw ke aage prev ke aage wala lagado
            prev.next = forw;          // 4=> prev ke aage forw lagado, is se forw sabse aage aa jaayega
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
