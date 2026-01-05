package org.example.striver.array;

public class S2LC189RotateArray {
//    Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
//
//    Example 1:
//    Input: nums = [1,2,3,4,5,6,7], k = 3
//    Output: [5,6,7,1,2,3,4]
//    Explanation:
//    rotate 1 steps to the right: [7,1,2,3,4,5,6]
//    rotate 2 steps to the right: [6,7,1,2,3,4,5]
//    rotate 3 steps to the right: [5,6,7,1,2,3,4]
//
//    Example 2:
//    Input: nums = [-1,-100,3,99], k = 2
//    Output: [3,99,-1,-100]
//    Explanation:
//    rotate 1 steps to the right: [99,-1,-100,3]
//    rotate 2 steps to the right: [3,99,-1,-100]

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k < 0) {
            k = k + nums.length; // if k is -ve
        }
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length -1);

        // OR we can do this order, same thing.
        // reverse(nums, 0, nums.length - 1);
        // reverse(nums, 0, k - 1);
        // reverse(nums, k, nums.length  - 1);
    }

    public void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    // This is simple and brute force using extra space O(n)
    public void rotate2(int[] nums, int k) {
        k = k % nums.length;
        int n = nums.length;
        int[] arr = new int[n];

        int j = 0;
        //i = 0 + n-k;   i < n + n-k; > understand the below loop conditions by this..
        for (int i = n-k; i < 2*n-k; i++) {
            arr[j++] = nums[i % n];
        }

        for (int i = 0; i < n; i++) {
            nums[i] = arr[i];
        }
    }
}
