
import java.util.ArrayList;
import java.util.List;

public class LC78Subsets {
//    Given an integer array nums of unique elements, return all possible
//    subsets
//            (the power set).
//
//    The solution set must not contain duplicate subsets. Return the solution in any order.
//
//    Example 1:
//    Input: nums = [1,2,3]
//    Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//
//    Example 2:
//    Input: nums = [0]
//    Output: [[],[0]]
//
//
//    Constraints:
//
//            1 <= nums.length <= 10
//            -10 <= nums[i] <= 10
//            All the numbers of nums are unique.


    // Best for BackTracking => 
    // https://leetcode.com/problems/subsets/solutions/27281/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning/?envType=featured-list&envId=top-interview-questions?envType=featured-list&envId=top-interview-questions

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // no need to sort, output can be in any order
        backtrack(list, nums, new ArrayList<>(), 0);
        return list;
    }

    public void backtrack(List<List<Integer>> list, int[] nums, List<Integer> tempList, int start) {
        list.add(new ArrayList<>(tempList)); // if we do this => list.add(tempList); => then our result
        // will look like this [ [], [], [], [], [] ]. this is because in this we are adding the same 
        // reference of tempList everytime. So whenever tempList changes everywhere value will change
        // to fix this we need to add new reference by this => list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, nums, tempList, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
