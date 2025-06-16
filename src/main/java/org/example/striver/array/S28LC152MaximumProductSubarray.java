package org.example.striver.array;

public class S28LC152MaximumProductSubarray {
//    Given an integer array nums, find a subarray that has the largest product, and return the product.
//
//    The test cases are generated so that the answer will fit in a 32-bit integer.
//
//
//    Example 1:
//    Input: nums = [2,3,-2,4]
//    Output: 6
//    Explanation: [2,3] has the largest product 6.
//
//    Example 2:
//    Input: nums = [-2,0,-1]
//    Output: 0
//    Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

    // 2 pointer approach, where we will find product from start and end and then compare them and choose the max
    // better soltuion
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int left = 1;
        int right = 1;
        int ans = nums[0];

        for(int i = 0; i < n; i++) {
            left = left * nums[i]; // product from the left
            right = right * nums[n - 1- i]; // product from the right

            ans = Math.max(ans, Math.max(left, right));

            // if any of left or right become 0 then update it to 1
            left = left==0 ? 1 : left;
            right = right==0 ? 1 : right;
        }

        return ans;
    }



    // Finding max at every point
    public int maxProduct2(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max;

            max = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            min = Math.min(nums[i], Math.min(temp * nums[i], min * nums[i]));

            ans = Math.max(ans, max);
        }

        return ans;
    }

}
