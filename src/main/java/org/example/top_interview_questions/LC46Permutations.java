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

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(result, nums, new ArrayList<>());
        return result;
    }

    public void backtracking(List<List<Integer>> result, int[] nums, List<Integer> tempList) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
        } else{
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) { continue; }
                tempList.add(nums[i]);
                backtracking(result, nums, tempList);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
