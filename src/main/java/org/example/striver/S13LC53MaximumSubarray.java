package org.example.striver;

public class S13LC53MaximumSubarray {
//    Given an integer array nums, find the subarray with the largest sum, and return its sum.
//
//    Example 1:
//    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//    Output: 6
//    Explanation: The subarray [4,-1,2,1] has the largest sum 6.
//
//    Example 2:
//    Input: nums = [1]
//    Output: 1
//    Explanation: The subarray [1] has the largest sum 1.
//
//    Example 3:
//    Input: nums = [5,4,-1,7,8]
//    Output: 23
//    Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.

    public int maxSubArray(int[] nums) {
        int currSum = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currSum += nums[i];
            currSum = Math.max(nums[i], currSum);
            max = Math.max(max, currSum);
        }
        return max;
    }

    // Solution 2
    public int maxSubArray2(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;

        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];

            if(currSum > maxSum) {
                maxSum = currSum;
            }

            if (currSum < 0) {
                currSum = 0;
            }
        }
        return maxSum;
    }
}
