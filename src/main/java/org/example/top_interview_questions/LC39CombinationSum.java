package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC39CombinationSum {
//    Given an array of distinct integers candidates and a target integer target,
//    return a list of all unique combinations of candidates where the chosen numbers sum to target.
//    You may return the combinations in any order.
//
//    The same number may be chosen from candidates an unlimited number of times.
//    Two combinations are unique if the frequency of at least one of the chosen numbers is different.
//
//    The test cases are generated such that the number of unique combinations
//    that sum up to target is less than 150 combinations for the given input.
//
//
//
//    Example 1:
//    Input: candidates = [2,3,6,7], target = 7
//    Output: [[2,2,3],[7]]
//    Explanation:
//    2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
//    7 is a candidate, and 7 = 7.
//    These are the only two combinations.
//
//
//    Example 2:
//    Input: candidates = [2,3,5], target = 8
//    Output: [[2,2,2,2],[2,3,3],[3,5]]
//
//
//    Example 3:
//    Input: candidates = [2], target = 1
//    Output: []
//
//
//    Constraints:
//
//            1 <= candidates.length <= 30
//            2 <= candidates[i] <= 40
//    All elements of candidates are distinct.
//            1 <= target <= 40

    // Good Back tracking solutions =>
    // https://leetcode.com/problems/combination-sum/solutions/16502/a-general-approach-to-backtracking-quest-dexx
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backTracking(candidates, target, 0, new ArrayList<>(), 0, res);

        return res;
    }

    public void backTracking(int[] candidates, int target, int start, List<Integer> temp, int currAddition, List<List<Integer>> res) {
        if (currAddition > target) {
            return;
        }
        if (currAddition == target) {
            res.add(new ArrayList<>(temp));
        }

        for (int i = start; i < candidates.length; i++) {
            temp.add(candidates[i]);
            backTracking(candidates, target, i, temp, currAddition + candidates[i], res); // not i + 1 because we can reuse same elements
            temp.remove(temp.size() - 1);
        }
    }
}
