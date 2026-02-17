package org.example.top_interview_questions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC219ContainsDuplicateII {
//    Given an integer array nums and an integer k,
//    return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
//
//
//
//    Example 1:
//    Input: nums = [1,2,3,1], k = 3
//    Output: true
//
//    Example 2:
//    Input: nums = [1,0,1,1], k = 1
//    Output: true
//
//    Example 3:
//    Input: nums = [1,2,3,1,2,3], k = 2
//    Output: false
//
//
//    Constraints:
//
//            1 <= nums.length <= 105
//            -109 <= nums[i] <= 109
//            0 <= k <= 105

    // Using Map
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                }
            }

            map.put(nums[i], i);
        }

        return false;
    }

    // using Set
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (i - k > 0) {
                set.remove(nums[i - k - 1]);
            }

            if (!set.add(nums[i])) {
                return true;
            }
        }

        return false;
    }
}
