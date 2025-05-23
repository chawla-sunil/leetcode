package org.example.top_interview_questions;

public class LC21MergeTwoSortedLinkedLists {
//    You are given the heads of two sorted linked lists list1 and list2.
//    Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
//    Return the head of the merged linked list.
//
//    Example 1:
//    Input: list1 = [1,2,4], list2 = [1,3,4]
//    Output: [1,1,2,3,4,4]
//
//    Example 2:
//    Input: list1 = [], list2 = []
//    Output: []
//
//    Example 3:
//    Input: list1 = [], list2 = [0]
//    Output: [0]
//
//
//    Constraints:
//
//            The number of nodes in both lists is in the range [0, 50].
//            -100 <= Node.val <= 100
//            Both list1 and list2 are sorted in non-decreasing order.

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(0);
        ListNode node = dummyNode;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = list1;
                node = node.next;
                list1 = list1.next;
            } else {
                node.next = list2;
                node = node.next;
                list2 = list2.next;
            }
        }

        while (list1 != null) {
            node.next = list1;
            node = node.next;
            list1 = list1.next;
        }

        while (list2 != null) {
            node.next = list2;
            node = node.next;
            list2 = list2.next;
        }

        return dummyNode.next;
    }

     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
