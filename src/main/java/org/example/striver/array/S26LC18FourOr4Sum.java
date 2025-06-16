package org.example.striver.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class S26LC18FourOr4Sum {
//    Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
//    0 <= a, b, c, d < n
//    a, b, c, and d are distinct.
//    nums[a] + nums[b] + nums[c] + nums[d] == target
//    You may return the answer in any order.
//
//
//    Example 1:
//    Input: nums = [1,0,-1,0,-2,2], target = 0
//    Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//
//    Example 2:
//    Input: nums = [2,2,2,2,2], target = 8
//    Output: [[2,2,2,2]]

    // It is just an extension problem of 3Sum
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int j = i+1; j < n; j++) {
                if (j > i+1 && nums[j] == nums[j-1]) {
                    continue;
                }

                int k = j + 1;
                int l = n - 1;

                while (k < l) {
                    long total = (long) nums[i] + nums[j] + nums[k] + nums[l];
                    // fails one test case if use int for total because of integer overflow.
                    if (total < target) {
                        k++;
                    } else if (total > target) {
                        l--;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k++;

                        while (nums[k] == nums[k-1] && k < l) {
                            k++;
                        }
                    }
                }
            }
        }

        return res;
    }

    // Another solution using Extra space, using set
    // https://leetcode.com/problems/4sum/solutions/3110025/c-easiest-beginner-friendly-sol-set-two-pointer-approach-o-n-3-time-and-o-n-space
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> s = new HashSet<>();
        List<List<Integer>> output = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int k = j + 1;
                int l = nums.length - 1;
                while (k < l) {
                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[k];
                    sum += nums[l];
                    if (sum == target) {
                        s.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k++;
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }
        output.addAll(s);
        return output;
    }

}
