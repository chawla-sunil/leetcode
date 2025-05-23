package org.example.companies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PYOR {
    public static void main(String[] args) {
        System.out.println("123");

//        Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
//
//        Input: nums1 = [1,3], nums2 = [2]
//        Output: 2.00000
//        Explanation: merged array = [1,2,3] and median is 2.
//
//        Input: nums1 = [1,2], nums2 = [3,4]
//        Output: 2.50000
//        Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

        int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> ans = solve(nums);
        System.out.println(ans);

//        Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
//
//        Notice that the solution set must not contain duplicate triplets.
//
//        Example 1:
//
//        Input: nums = [-1,0,1,2,-1,-4]
//        Output: [[-1,-1,2],[-1,0,1]]
//        Explanation:
//        nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
//        nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
//        nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
//        The distinct triplets are [-1,0,1] and [-1

    }

    public static List<List<Integer>> solve(int[] num) {
        List<List<Integer>> result = new ArrayList<>();

        int n = num.length;

        for (int i = 0; i < n - 2; i++) {
            Set<Integer> visited = new HashSet<>();
            int needed = - num[i];

            for (int j = i +1; j < n; j++) {
                int complementary =  needed - num[j];

                if (visited.contains(complementary)) {
                    List<Integer> sol = Arrays.asList(num[i], complementary, num[j]);
                    result.add(sol);
                    visited.remove(complementary);
                } else {
                    visited.add(num[j]);
                }
            }
        }
        return result;
    }
}
