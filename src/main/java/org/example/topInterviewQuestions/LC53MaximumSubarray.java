package org.example.topInterviewQuestions;

public class LC53MaximumSubarray {
//    Given an integer array nums, find the subarray
//    with the largest sum, and return its sum.
//
//    Example 1:
//
//    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//    Output: 6
//    Explanation: The subarray [4,-1,2,1] has the largest sum 6.
//
//    Example 2:
//
//    Input: nums = [1]
//    Output: 1
//    Explanation: The subarray [1] has the largest sum 1.
//
//    Example 3:
//
//    Input: nums = [5,4,-1,7,8]
//    Output: 23
//    Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
//
//
//    Constraints:
//
//            1 <= nums.length <= 10^5
//            -10^4 <= nums[i] <= 10^4
//
//
//    Follow up: If you have figured out the O(n) solution, try coding another solution
//    using the divide and conquer approach, which is more subtle.


    // this is kadane algorithm O(N) simple
    public int maxSubArray2(int[] nums) {
        int maxSum = Integer.MIN_VALUE; // or int maxSum = nums[0]
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

    // this is using divide and conquer, Solution to FOLLOW-UP Question

//     We can solve this using divide and conquer strategy.
//     This is the follow-up question asked in this question. This involves modelling the problem by
//     observing that the maximum subarray sum is such that it lies somewhere -
//     => entirely in the left-half of array [L, mid-1], OR
//     => entirely in the right-half of array [mid+1, R], OR
//     => in array consisting of mid element along with some part of left-half and
//        some part of right-half such that these form contiguous
//        subarray - [L', R'] = [L', mid-1] + [mid] + [mid+1,R'], where L' >= L and R' <= R

//     With the above observation, we can recursively divide the array into sub-problems on the left and right halves
//     and then combine these results on the way back up to find the maximum subarray sum.
    
    public int maxSubArray(int[] nums) {
        return maxAnswer(nums, 0, nums.length - 1);
    }

    public int maxAnswer(int[] nums, int L, int R) {
        if (L > R) {
            return Integer.MIN_VALUE;
        }
        int mid = (L + R) / 2;
        int leftSum = 0;
        int rightSum = 0;

        int currSum = 0;
        // leftSum = max subarray sum in [L, mid-1] and starting from mid-1
        for (int i = mid -1; i >= L; i--) {
            currSum += nums[i];
            leftSum = Math.max(leftSum, currSum);
        }

        currSum = 0;
        // rightSum = max subarray sum in [mid+1, R] and starting from mid+1
        for(int i = mid + 1; i <= R; i++) {
            currSum += nums[i];
            rightSum = Math.max(rightSum, currSum);
        }

        // return max of 3 cases
        return Math.max(
                leftSum + rightSum + nums[mid],
                Math.max(maxAnswer(nums, L, mid - 1),
                        maxAnswer(nums, mid+1, R)
                )
        );
    }
}
