package org.example.striver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S20LC229MajorityElementII {
//    Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

//    Example 1:
//
//    Input: nums = [3,2,3]
//    Output: [3]
//
//    Example 2:
//    Input: nums = [1]
//    Output: [1]
//
//    Example 3:
//    Input: nums = [1,2]
//    Output: [1,2]

    // Since we want to return elements that appeared more than ⌊n/3⌋ times then atleast it must have appeared ⌊n/3⌋ + 1 times.
    // ⌊n/3⌋ + 1 is greater than the third of the array so
    // { ⌊n/3⌋ + 1 * 3 } > array size
    // So, it is impossible to return more than two elements.
    //. For simple solution, we can refer to this problem
    // https://leetcode.com/problems/majority-element/description/
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;

        int count1 = 0, count2 = 0;
        int element1 = 0, element2 = 0;

        for (int i = 0; i < n; i++) {
            if (count1 == 0 && nums[i] != element2) {
                count1++;
                element1 = nums[i];
            } else if (count2 == 0 && nums[i] != element1) {
                count2++;
                element2 = nums[i];
            } else if (nums[i] == element1) {
                count1++;
            } else if (nums[i] == element2) {
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int num: nums) {
            if (num == element1) {
                count1++;
            } else if (num == element2) {
                count2++;
            }
        }

        int threshold = nums.length / 3;

        List<Integer> res = new ArrayList<>();

        if (count1 > threshold) {
            res.add(element1);
        }
        if (count2 > threshold) {
            res.add(element2);
        }

        return res;
    }

    // Using hashmap
    public List<Integer> majorityElement2(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap<>();

        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        int threshold = n / 3;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() > threshold) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

}
