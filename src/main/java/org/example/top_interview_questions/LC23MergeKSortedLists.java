package org.example.top_interview_questions;

import java.util.PriorityQueue;

public class LC23MergeKSortedLists {
//    You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
//
//    Merge all the linked-lists into one sorted linked-list and return it.
//
//
//            Example 1:
//
//    Input: lists = [[1,4,5],[1,3,4],[2,6]]
//    Output: [1,1,2,3,4,4,5,6]
//    Explanation: The linked-lists are:
//            [
//            1->4->5,
//            1->3->4,
//            2->6
//            ]
//    merging them into one sorted list:
//            1->1->2->3->4->4->5->6
//    Example 2:
//
//    Input: lists = []
//    Output: []
//    Example 3:
//
//    Input: lists = [[]]
//    Output: []
//
//
//    Constraints:
//
//       k == lists.length
//       0 <= k <= 10^4
//            0 <= lists[i].length <= 500
//            -10^4 <= lists[i][j] <= 10^4
//      lists[i] is sorted in ascending order.
//      The sum of lists[i].length will not exceed 10^4.

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

        for (ListNode node: lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        ListNode dummyNode = new ListNode(0);
        ListNode curr = dummyNode;
        while(!minHeap.isEmpty()) {
            curr.next = minHeap.poll();
            curr = curr.next;

            if (curr.next != null) {
                minHeap.add(curr.next);
            }
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


    public ListNode mergeKListsMethod2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }

    private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (start + 1 == end) {
            return merge(lists[start], lists[end]);
        }
        int mid = start + (end - start) / 2;
        ListNode left = mergeKListsHelper(lists, start, mid);
        ListNode right = mergeKListsHelper(lists, mid + 1, end);
        return merge(left, right);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = (l1 != null) ? l1 : l2;

        return dummy.next;
    }
}
