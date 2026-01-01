package org.example.two_pointers;

import java.util.Arrays;

public class LC1498NumberOfSubsequencesThatSatisfyTheGivenSumCondition {
//    You are given an array of integers nums and an integer target.
//    Return the number of non-empty subsequences of nums such that the sum of the minimum and
//    maximum element on it is less or equal to target. Since the answer may be too large, return it modulo 109 + 7.
//
//
//    Example 1:
//    Input: nums = [3,5,6,7], target = 9
//    Output: 4
//    Explanation: There are 4 subsequences that satisfy the condition.
//            [3] -> Min value + max value <= target (3 + 3 <= 9)
//            [3,5] -> (3 + 5 <= 9)
//            [3,5,6] -> (3 + 6 <= 9)
//            [3,6] -> (3 + 6 <= 9)
//
//    Example 2:
//    Input: nums = [3,3,6,8], target = 10
//    Output: 6
//    Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
//            [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
//    Example 3:
//
//    Input: nums = [2,3,3,4,6,7], target = 12
//    Output: 61
//    Explanation: There are 63 non-empty subsequences, two of them do not satisfy the condition ([6,7], [7]).
//    Number of valid subsequences (63 - 2 = 61).
//
//
//    Constraints:
//            1 <= nums.length <= 105
//            1 <= nums[i] <= 106
//            1 <= target <= 106

    // This solution is better because Math.pow will do integrer overflow,
    // so we are storing 2 powers and their mods(%), so the values comes under 2**10 + 7
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int mod = 1000000000 + 7;

        int[] power = new int[nums.length];
        power[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            power[i] = (2 * power[i-1]) % mod;
        }

        int l = 0;
        int r = nums.length - 1;
        int ans = 0;


        while (l <= r) {
            if (nums[l] + nums[r] > target) {
                r--;
            } else {
                ans += power[r-l]; // just this line is changed in other below solution to => (int) Math.pow(2, r-l);
                ans %= mod;
                l++;
            }
        }

        return ans;
    }


    // In an interview, give this solution first
    // Math.pow method in this solution will give integer overflow,
    // ex. If r - l = 40, the value of Math.pow(2, 40) is 1,099,511,627,776, which far exceeds
    // the maximum value of an int in Java (2,147,483,647).
    public int numSubseq2(int[] nums, int target) {
        Arrays.sort(nums);

        int l = 0;
        int r = nums.length - 1;
        int ans = 0;
        int mod = (int) Math.pow(10, 9) + 7;

        while (l <= r) {
            if (nums[l] + nums[r] > target) {
                r--;
            } else {
                ans += (int) Math.pow(2, r-l);
                ans %= mod;
                l++;
            }
        }

        return ans;
    }
}
