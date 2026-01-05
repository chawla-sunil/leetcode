package org.example.top_interview_questions;

public class LC55JumpGame {
//    You are given an integer array nums. You are initially positioned at the array's first index,
//    and each element in the array represents your maximum jump length at that position.
//    Return true if you can reach the last index, or false otherwise.
//
//            Example 1:
//    Input: nums = [2,3,1,1,4]
//    Output: true
//    Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
//
//            Example 2:
//    Input: nums = [3,2,1,0,4]
//    Output: false
//    Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0,
//    which makes it impossible to reach the last index.
//
//            Constraints:
//            1 <= nums.length <= 10^4
//            0 <= nums[i] <= 10^5

    public boolean canJump(int[] nums) {
        // The basic idea is this: at each step, we keep track of the furthest reachable index.
        // The nature of the problem (eg. maximal jumps where you can hit a range of targets
        // instead of singular jumps where you can only hit one target) is that for an index
        // to be reachable, each of the previous indices have to be reachable.

        int reachable = 0;
        for (int i = 0; i < nums.length; i++) { // more optimisied by adding this in for loop condition => for (int i = 0; i < nums.length && (reachable < nums.length -1); i++),
            // this condition {(reachable < nums.length -1)} => means, Run till I have not reached to n-1,
            // If I have already reached n-1, then break for loop and in the end we are returning true;
            if (i > reachable) { return false; }
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }

    // This is also same good solution as above
    public boolean canJump2(int[] nums) {
        int goal = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= goal) {
                goal = i; // we will make our new goal to the index from where we can reach the end goal
            }
            // else condition means, we are not able to reach to final index from this index, so we don't wanna ended up here
        }

        // if we have reached till start that means we can reach the end
        return goal == 0;
    }
}
