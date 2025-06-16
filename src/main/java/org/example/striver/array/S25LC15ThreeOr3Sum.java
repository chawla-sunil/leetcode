package org.example.striver.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class S25LC15ThreeOr3Sum {
//    Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
//    such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
//
//    Notice that the solution set must not contain duplicate triplets.
//
//    Example 1:
//    Input: nums = [-1,0,1,2,-1,-4]
//    Output: [[-1,-1,2],[-1,0,1]]
//    Explanation:
//    nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
//    nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
//    nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
//    The distinct triplets are [-1,0,1] and [-1,-1,2].
//    Notice that the order of the output and the order of the triplets does not matter.
//
//    Example 2:
//    Input: nums = [0,1,1]
//    Output: []
//    Explanation: The only possible triplet does not sum up to 0.
//
//    Example 3:
//    Input: nums = [0,0,0]
//    Output: [[0,0,0]]
//    Explanation: The only possible triplet sums up to 0.

    // Time:  O(n^2) and Space: O(1)
    // This one is liite better because of no extra space
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();


        for (int i = 0; i < nums.length; i++) {
            // since it is sorted, if there is no negative,
            // there is no point to iterate over the array, once you are on past 0
            if (nums[i] > 0) {
                break;
            }

            // if we get the same element on i and i-1, because we don't want any duplicates
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }


            int j = i + 1;
            int k = nums.length - 1;

            while(j < k) {
                int total = nums[i] + nums[j] + nums[k];

                if (total < 0) {
                    j++;
                } else if (total > 0) {
                    k--;
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;

                    // if we get the same element on j and j-1, because we don't want any duplicates
                    while(nums[j] == nums[j-1] && j < k) {
                        j++;
                    }
                }
            }
        }

        return res;
    }

    // Time:  O(n^2) and Space: O(n) for taking hashset
    public List<List<Integer>> threeSum2(int[] nums) {
        int target = 0;
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int j = i+1;
            int k = nums.length - 1;

            while(j < k) {
                int total = nums[i] + nums[j] + nums[k];

                if (total < target) {
                    j++;
                } else if (total > target) {
                    k--;
                } else {
                    set.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                }
            }
        }

        res.addAll(set);

        return res;
    }
}
