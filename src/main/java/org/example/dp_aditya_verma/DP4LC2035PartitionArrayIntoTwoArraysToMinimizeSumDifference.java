package org.example.dp_aditya_verma;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DP4LC2035PartitionArrayIntoTwoArraysToMinimizeSumDifference {
    // It is Minimum Subset Sum Difference in Aditya Verma's series

//    You are given an integer array nums of 2 * n integers.
//    You need to partition nums into two arrays of length n to minimize the absolute difference of the sums of the arrays.
//    To partition nums, put each element of nums into one of the two arrays.
//
//    Return the minimum possible absolute difference.
//
//
//    Example 1:
//    Input: nums = [3,9,7,3]
//    Output: 2
//    Explanation: One optimal partition is: [3,9] and [7,3].
//    The absolute difference between the sums of the arrays is abs((3 + 9) - (7 + 3)) = 2.
//
//    Example 2:
//    Input: nums = [-36,36]
//    Output: 72
//    Explanation: One optimal partition is: [-36] and [36].
//    The absolute difference between the sums of the arrays is abs((-36) - (36)) = 72.
//
//    Example 3:
//    Input: nums = [2,-1,0,4,-2,-9]
//    Output: 0
//    Explanation: One optimal partition is: [2,4,-9] and [-1,0,-2].
//    The absolute difference between the sums of the arrays is abs((2 + 4 + -9) - (-1 + 0 + -2)) = 0.
//
//
//    Constraints:
//
//            1 <= n <= 15
//    nums.length == 2 * n
//-107 <= nums[i] <= 107



    // This is extension of Subset Sum Problem

    // We need to find two subsets of equal size with minimum sum difference, lets say total sum is S
    // and 2 subset sums are S1 and S2 (let's assume S1 <= S2)
    // We need to minimize abs(S1 - S2)
    // We know that S1 + S2 = S  => S2 = S - S1
    // So, abs(S2 - S1) = abs((S - S1) - S1) = abs(S - 2*S1))
    // So, to minimize abs(S1 - S2), we need to minimize abs(S - 2*S1)
    // To minimize abs(S - 2*S1), we need to find S1 such that S1 is as close to S/2 as possible
    // for example [1,2,7] total sum is 10, S/2 is 5
    //        S1 will here in this region      |
    //                              0,1,2,3,4,5,6,7,8,9,10 (S)
    //        S1 will be from 0 to 5           |
    // S1 can be 0,1,2,3
    // S1 = 1, then S2 = 9, abs(1-9) = 8 (10 - 2*1 = 8) = S - 2*S1
    // S1 = 2, then S2 = 8, abs(2-8) = 6 (10 - 2*2 = 6) = S - 2*S1
    // S1 = 3, then S2 = 7, abs(3-7) = 4 (10 - 2*3 = 4) = S - 2*S1
    // so, minimum difference is abs(10 - 2*3) = 4 is the answer

    /*
     * DP Table for Minimum Subset Sum Difference:
     * Example: arr[] = [1, 2, 7], totalSum = 10, target = 10/2 = 5
     *
     * Initial state (base cases):
     *          sum→  0   1   2   3   4   5
     * i=0 (empty)   T   F   F   F   F   F
     * i=1 (1)       T   ?   ?   ?   ?   ?
     * i=2 (2)       T   ?   ?   ?   ?   ?
     * i=3 (7)       T   ?   ?   ?   ?   ?
     *
     * Final DP table after computation:
     *          sum→  0   1   2   3   4   5
     * i=0 (empty)   T   F   F   F   F   F
     * i=1 (1)       T   T   F   F   F   F
     * i=2 (2)       T   T   T   T   F   F
     * i=3 (7)       T   T   T   T   F   F  ← LAST ROW: Check from right to left from the middle(S/2=5)
     *               ↑   ↑   ↑   ↑   ↑   ↑
     *              S1=0 S1=1 S1=2 S1=3  F   F
     *
     * Finding minimum difference:
     * - Start from j = target (5) and go backwards
     * - Find largest j where dp[n][j] = T
     * - Here, largest S1 = 3 (dp[3][3] = T)
     * - S2 = totalSum - S1 = 10 - 3 = 7
     * - Minimum difference = |S1 - S2| = |3 - 7| = 4
     * - Or using formula: totalSum - 2*S1 = 10 - 2*3 = 4 ✓
     *
     * Valid S1 values from last row: {0, 1, 2, 3}
     * We pick S1 = 3 (closest to target = 5) for minimum difference
     */


    // note : We may not be able to do this using memoization since all values of lookup table are not filled in memoization,
    // so do this using tabulation since tabulation guarantees that all values of dp table are filled
    public int minimumDifference(int[] nums) {
        int n = nums.length / 2;
        int totalSum = Arrays.stream(nums).sum(); // for (int num : nums) {totalSum += num;}

        // dp[i][j] will be true if a subset with sum j can be formed using the first i elements
        boolean[][] dp = new boolean[n + 1][totalSum + 1];

        for (int i = 0; i<=n; i++) {
            for (int j = 0; j <= totalSum; j++) {
                if (j == 0) {
                    dp[i][j] = true;
                } else if (i ==0 ) {
                    dp[i][j] = false;
                } else if (nums[i-1] <= j) {
                    dp[i][j] = dp[i-1][j- nums[i-1]] || dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        // Find the largest j such that dp[n][j] is true
        for (int j = totalSum/2; j >= 0; j--) {
            if (dp[n][j]) {
                return totalSum - 2 * j;
            }
        }

        return -1; // This line should/will never be reached
    }


//    public static void main(String[] args) {
//        DP4LC2035PartitionArrayIntoTwoArraysToMinimizeSumDifference solver = new DP4LC2035PartitionArrayIntoTwoArraysToMinimizeSumDifference();
//        int[] nums = {3, 9, 7, 3};
//        int result = solver.minimumDifference(nums);
//        System.out.println("Minimum Difference: " + result); // Output: Minimum Difference: 2
//    }
}
