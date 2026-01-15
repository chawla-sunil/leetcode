package org.example.top_interview_questions;

public class LC238ProductOfArrayExceptSelf {
//    Given an integer array nums, return an array answer such that
//    answer[i] is equal to the product of all the elements of nums except nums[i].
//
//    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
//    You must write an algorithm that runs in O(n) time and without using the division operation.
//
//
//    Example 1:
//    Input: nums = [1,2,3,4]
//    Output: [24,12,8,6]
//
//    Example 2:
//    Input: nums = [-1,1,0,-3,3]
//    Output: [0,0,9,0,0]
//
//
//    Constraints:
//
//            2 <= nums.length <= 105
//            -30 <= nums[i] <= 30
//    The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
//
//
//    Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)

    // In this solution, we are only calculating the prefix product and
    // calculating suffix product on the go in second loop instead of storing
    // suffix product in a different array like solution method productExceptSelf2
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] pre = new int[n]; // prefix product
        pre[0] = 1;
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i-1] * nums[i-1];
        }

        int suf = 1; // suffix product
        for (int i = n-1; i >= 0; i--) {
            pre[i] = pre[i] * suf;
            suf = suf * nums[i];
        }

        return pre;
    }

    // prefix product and suffix product
    // This is better solution and good to understand
    // but it uses 2 extra array to store prefix product and suffix product
    // we can do this with only 1 extra array as done in the main solution
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n];
        int[] suf = new int[n];

        pre[0] = 1;
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i-1] * nums[i-1];
        }

        suf[n-1] = 1;
        for (int i = n-2; i >= 0; i--) {
            suf[i] = suf[i+1] * nums[i+1];
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = pre[i] * suf[i];
        }

        return res;
    }

    // good but it will not work when nums[i] = 0
    // because we will not be able to divide by 0
    // Will not run on leetcode
    public int[] productExceptSelf3(int[] nums) {
        int product  = 1;

        for (int i = 0; i < nums.length; i++) {
            product *= nums[i];
        }

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = product / nums[i];
        }

        return ans;
    }

    // brute force
    public int[] productExceptSelf4(int[] nums) {
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i==j) {
                    continue;
                }
                product *= nums[j];
            }
            res[i] = product;
        }

        return res;
    }
}
