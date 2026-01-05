package org.example.top_interview_questions;

public class LC45JumpGameII {
//    You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.
//
//    Each element nums[i] represents the maximum length of a forward jump from index i.
//    In other words, if you are at index i, you can jump to any index (i + j) where:
//
//    0 <= j <= nums[i] and
//    i + j < n
//    Return the minimum number of jumps to reach index n - 1.
//    The test cases are generated such that you can reach index n - 1.
//
//
//
//    Example 1:
//    Input: nums = [2,3,1,1,4]
//    Output: 2
//    Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
//
//    Example 2:
//    Input: nums = [2,3,0,1,4]
//    Output: 2
//
//
//    Constraints:
//
//            1 <= nums.length <= 104
//            0 <= nums[i] <= 1000
//    It's guaranteed that you can reach nums[n - 1].

    // The main idea is based on greedy. Let's say the range of the current jump is [curBegin, curEnd],
    // curFarthest is the farthest point that all points in [curBegin, curEnd] can reach.
    // Once the current point reaches curEnd, then trigger another jump, and set the new curEnd with curFarthest.

    // ==> In each interval, (till will reach the currEnd which is farthest in the last interval),
    // We calculate the farthest we can reach and once we reach the currEnd, that means we now need to increase the jump
    // and set currEnd = farthest because we can reach farthest from the current range now and
    // then continue this cycle of calculating farthest in the new range.
    // We only run loop till nums.length(n) - 2 because let's say our currEnd is n-2 then i==currEnd, we will increase
    // the jump, and we can anyway reach n-1, and we increase the jump.
    public int jump(int[] nums) {
        int jump = 0;
        int currEnd = 0;
        int farthest = 0;

        for (int i = 0; i <= nums.length - 2; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currEnd) {
                jump++;
                currEnd = farthest;
            }
        }

        return jump;
    }
}
