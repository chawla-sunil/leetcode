package org.example.striver.array;

import java.util.HashMap;
import java.util.Map;

public class S24LC560SubarraySumEqualsK {
//    Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
//
//    A subarray is a contiguous non-empty sequence of elements within an array.
//
//
//    Example 1:
//    Input: nums = [1,1,1], k = 2
//    Output: 2
//
//    Example 2:
//    Input: nums = [1,2,3], k = 3
//    Output: 2

    // Explaination: https://leetcode.com/problems/subarray-sum-equals-k/solutions/6156695/adding-number-of-current-total-k
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int count = 0;
        int total = 0;
        for (int num: nums) {
            total += num;

            if (map.containsKey(total - k)) {
                count += map.get(total - k);
            }

            map.put(total, map.getOrDefault(total, 0) + 1);
        }
        return count;
    }


    // brute force solution
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }


    // this and next soltuion are for finding the longest subarray with sum equal to k.
    // This is not the solutionn for the problem stated above.
    // Problem statement for this and next solution:
// Given an array of integers nums and an integer k, return the length of the longest subarray whose sum equals to k.
    public int getLongestSubarray(int []a, int k) {
        int n = a.length; // size of the array.

        Map<Integer, Integer> preSumMap = new HashMap<>();
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            //calculate the prefix sum till index i:
            sum += a[i];

            // if the sum = k, update the maxLen:
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // calculate the sum of remaining part i.e. x-k:
            int rem = sum - k;

            //Calculate the length and update maxLen:
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            //Finally, update the map checking the conditions:
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }

        return maxLen;
    }

    // if we are given that the array is non-negative(positive values and zero),
    // we can use a sliding window approach (two pointer approach) to find the longest subarray with sum equal to k.
    public int getLongestSubarray2(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int maxLength = 0;

        while (right < nums.length) {
            sum += nums[right];

            while (sum > k && left <= right) {
                sum -= nums[left];
                left++;
            }

            if (sum == k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
            right++;
        }
        return maxLength;
    }
}
