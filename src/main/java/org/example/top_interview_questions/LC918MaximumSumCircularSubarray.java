package org.example.top_interview_questions;

public class LC918MaximumSumCircularSubarray {
//    Given a circular integer array nums of length n,
//    return the maximum possible sum of a non-empty subarray of nums.
//
//    A circular array means the end of the array connects to the beginning of the array.
//    Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
//
//    A subarray may only include each element of the fixed buffer nums at most once.
//    Formally, for a subarray nums[i], nums[i + 1], ..., nums[j],
//    there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
//
//
//    Example 1:
//    Input: nums = [1,-2,3,-2]
//    Output: 3
//    Explanation: Subarray [3] has maximum sum 3.
//
//    Example 2:
//    Input: nums = [5,-3,5]
//    Output: 10
//    Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
//
//    Example 3:
//    Input: nums = [-3,-2,-3]
//    Output: -2
//    Explanation: Subarray [-2] has maximum sum -2.
//
//
//    Constraints:
//
//    n == nums.length
//    1 <= n <= 3 * 104
//    -3 * 104 <= nums[i] <= 3 * 104



    // good explanation: https://leetcode.com/problems/maximum-sum-circular-subarray/solutions/3066636/weird-kadane-explanation-with-images-by-y4b1m
    // we will use kadane's algorithm to find max-sum and min-sum sub-array
    // Max sum in circular can be :
    // 1. max sum kadane's algorithm OR
    // 2. When it circled, then find min-sum array and subtract it from total-sum of the array
    // to find the max-sum.
    // => Take the max of 1. and 2.

    // Better Writing =>
    // To solve the problem of finding the maximum sum of a subarray in a circular array,
    // we need to consider two cases:
    // 1. The maximum subarray is non-circular, i.e., it lies within the bounds of the array.
    // This is a standard Kadane’s algorithm problem.

    // 2. The maximum subarray is circular, which can be computed as:
    // Total sum of the array - Minimum subarray sum
    // (i.e., removing the minimum subarray will give us the maximum circular subarray sum).

    // 3. We return the maximum of these two cases, unless the entire array is made of negative numbers (where circular sum becomes 0), in which case we just return the result of Kadane’s algorithm.
    public int maxSubarraySumCircular(int[] nums) {
        int maxSum = nums[0];
        int currMaxSum = nums[0];

        int minSum = nums[0];
        int currMinSum = nums[0];

        int totalSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Kadane's algorithm for maximum sum
            // Either extend previous subarray or start a new one
            currMaxSum = Math.max(currMaxSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, currMaxSum);

            // Kadane's algorithm for minimum sum
            // Either extend previous subarray or start a new one
            currMinSum = Math.min(currMinSum + nums[i], nums[i]);
            minSum = Math.min(minSum, currMinSum);

            totalSum += nums[i];
        }

        // Edge case: if all numbers are negative, then minSum and totalSum will be same
        // so we need to return maxSum,
        // (even though maxSum will also be negative but it is the max sum)
        // in this edge case, totalSum - minSum will be 0 but answer should be maxSum
        if (minSum == totalSum) {
            return maxSum;
        }

        return Math.max(maxSum, totalSum - minSum);
    }
}
