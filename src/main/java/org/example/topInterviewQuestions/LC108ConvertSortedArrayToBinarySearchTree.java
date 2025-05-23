package org.example.topInterviewQuestions;

public class LC108ConvertSortedArrayToBinarySearchTree {
//    Given an integer array nums where the elements are sorted in ascending order, convert it to a
//    height-balanced binary search tree.
//
//            Example 1:
//    Input: nums = [-10,-3,0,5,9]
//    Output: [0,-3,9,-10,null,5]
//    Explanation: [0,-10,5,null,-3,null,9] is also accepted:
//
//    Example 2:
//    Input: nums = [1,3]
//    Output: [3,1]
//    Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
//
//
//    Constraints:
//
//            1 <= nums.length <= 104
//            -104 <= nums[i] <= 104
//            nums is sorted in a strictly increasing order.


    public TreeNode sortedArrayToBST(int[] nums) {
        return makeTree(nums, 0, nums.length - 1);
    }

    private TreeNode makeTree(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }

        int mid = (low + high) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = makeTree(nums, low, mid-1);
        root.right = makeTree(nums, mid+1, high);
        return root;
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
