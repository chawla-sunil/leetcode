package org.example.dp_aditya_verma;

public class DP6LC494TargetSum {
//    You are given an integer array nums and an integer target.
//
//    You want to build an expression out of nums by adding one of the
//    symbols '+' and '-' before each integer in nums and then concatenate all the integers.
//
//    For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
//    Return the number of different expressions that you can build, which evaluates to target.
//
//
//
//    Example 1:
//    Input: nums = [1,1,1,1,1], target = 3
//    Output: 5
//    Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
//            -1 + 1 + 1 + 1 + 1 = 3
//            +1 - 1 + 1 + 1 + 1 = 3
//            +1 + 1 - 1 + 1 + 1 = 3
//            +1 + 1 + 1 - 1 + 1 = 3
//            +1 + 1 + 1 + 1 - 1 = 3
//
//    Example 2:
//    Input: nums = [1], target = 1
//    Output: 1
//
//
//    Constraints:
//            1 <= nums.length <= 20
//            0 <= nums[i] <= 1000
//            0 <= sum(nums[i]) <= 1000
//            -1000 <= target <= 1000



    // har element ke aage + or - lagake unka sum karo to given sum ke barabar aana chahiye
    // Example: nums = [1, 1, 2, 3], target = 1
    // In this example => +1 -1 -2 +3 = 1  |
    //                 => -1 +1 -2 +3 = 1  |   => Total = 3
    //                 => +1 +1 +2 -3 = 1  |
    // Agar is problem ko sort kare and saare positive (+) ek taraf
    // and saare negative (-) ek taraf rakh de to, 2 subsets ban jayenge
    // S1 will + and S2 with - sign, so this problem can be converted to
    // Count The Number Of Subset With A Given Difference problem


    // It will become Count The Number Of Subset With A Given Difference
    // Checkout Sunil chawla github repo for more info
    // This solution is submitted by Sunil Chawla on leetcode
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        int sum = 0;
        for (int num: nums) {
            sum += num;
        }

        int diff = target;
        if ((sum + diff) % 2 != 0 || (sum + diff) < 0) {
            return 0;
        }
        int s1 = (sum + diff) / 2;

        int[][] dp = new int[n+1][s1+1];

        // This initialization is important for edge case like num = [0], target = 0, output = 2
        // so that we can increase the value when we reach j = 0 again for i = 1;
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; // There's always one way to make sum 0 (by choosing no elements)
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= s1; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][s1];
    }

    // Not to be used in interview
    // This is also same good solution but does not work on num = [0], target = 0, output = 2 edge case
    public int findTargetSumWays2(int[] nums, int target) {
        int n = nums.length;

        int sum = 0;
        for (int num: nums) {
            sum += num;
        }

        int diff = target;
        if ((sum + diff) % 2 != 0 || (sum + diff) < 0) {
            return 0;
        }
        int s1 = (sum + diff) / 2;

        int[][] dp = new int[n+1][s1+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= s1; j++) {
                if (j == 0) {
                    // This is issue for edge case like num = [0], target = 0, output = 2
                    // We are setting it 1 everytime j = 0, but what j at i = 1 , j = 1 =>[0]
                    // Then the solution should be 2, it should add i=0 and j=0 solution in this
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = 0;
                } else if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][s1];
    }
}
