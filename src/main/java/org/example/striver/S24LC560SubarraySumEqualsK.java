package org.example.striver;

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
}
