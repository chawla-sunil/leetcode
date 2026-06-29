package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.List;

public class LC46Permutations {
//    Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
//
//            Example 1:
//
//    Input: nums = [1,2,3]
//    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//    Example 2:
//
//    Input: nums = [0,1]
//    Output: [[0,1],[1,0]]
//    Example 3:
//
//    Input: nums = [1]
//    Output: [[1]]
//
//
//    Constraints:
//
//            1 <= nums.length <= 6
//            -10 <= nums[i] <= 10
//    All the integers of nums are unique.

    // back tracking format breaktdown =>
    // https://leetcode.com/discuss/post/6164330/backtracking-cheat-sheet-in-java-from-ba-qojd/

    // Multiple other good backtracking problem solutions based on the above and my answer below format =>
    // https://leetcode.com/problems/permutations/solutions/18239/a-general-approach-to-backtracking-quest-e6b1


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTracking(nums, new ArrayList<>(), res);
        return res;
    }

    public void backTracking(int[] nums, List<Integer> temp, List<List<Integer>> res) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int num : nums) {
            if (temp.contains(num)) {
                continue;
            }
            temp.add(num);
            backTracking(nums, temp, res);
            temp.remove(temp.size() - 1);
        }
    }
}
