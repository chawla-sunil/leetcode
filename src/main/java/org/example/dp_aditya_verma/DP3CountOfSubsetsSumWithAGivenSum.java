package org.example.dp_aditya_verma;

public class DP3CountOfSubsetsSumWithAGivenSum {
//    Given an array arr[] of length n and an integer target,
//    the task is to find the number of subsets with a sum equal to target.
//
//    Examples:
//
//    Input: arr[] = [1, 2, 3, 3], target = 6
//    Output: 3
//    Explanation: All the possible subsets are [1, 2, 3], [1, 2, 3] and [3, 3]
//
//    Input: arr[] = [1, 1, 1, 1], target = 1
//    Output: 4
//    Explanation: All the possible subsets are [1], [1], [1] and [1]

    // (1) Recursive approach
    public int countSubsetsRecursive(int[] arr, int n, int target) {
        // Handle edge base row (only first element available)
        if (n == 1) {
            if (target == 0 && arr[0] == 0) return 2; // pick or not pick zero
            if (target == 0 || target == arr[0]) return 1;
            return 0;
        }

        // Base Cases
        if (target == 0) return 1; // Found a valid subset
        if (n == 0) return 0; // No elements left

        // If last element is greater than target, ignore it
        if (arr[n - 1] > target) return countSubsetsRecursive(arr, n - 1, target);

        // Otherwise, check if we can obtain the target by including or excluding the last element
        return countSubsetsRecursive(arr, n - 1, target) + countSubsetsRecursive(arr, n - 1, target - arr[n - 1]);
    }

    // (2) Memoization approach base function
    public int countSubsetsMemoizationBase(int[] arr, int n, int target) {
        Integer[][] dp = new Integer[n + 1][target + 1];
        return countSubsetsMemoization(arr, n, target, dp);
    }

    // (2) Memoization approach
    private int countSubsetsMemoization(int[] arr, int n, int target, Integer[][] dp) {
        // Handle edge base row (only first element available)
        if (n == 1) {
            if (target == 0 && arr[0] == 0) return 2; // pick or not pick zero
            if (target == 0 || target == arr[0]) return 1;
            return 0;
        }

        // Base Cases
        if (target == 0) return 1; // Found a valid subset
        if (n == 0) return 0; // No elements left

        // Check if the result is already computed
        if (dp[n][target] != null) return dp[n][target];

        // If last element is greater than target, ignore it
        if (arr[n - 1] > target) {
            dp[n][target] = countSubsetsMemoization(arr, n - 1, target, dp);
            return dp[n][target];
        }

        // Otherwise, check if we can obtain the target by including or excluding the last element
        dp[n][target] = countSubsetsMemoization(arr, n - 1, target, dp) + countSubsetsMemoization(arr, n - 1, target - arr[n - 1], dp);
        return dp[n][target];
    }


    /*
     * DP Table Initialization for Count of Subsets with Given Sum:
     * Example: arr[] = [1, 2, 3, 3], target = 6
     *
     * Initial state (base cases):
     *         sum→  0   1   2   3   4   5   6
     * i=0 (empty)   1   0   0   0   0   0   0
     * i=1 (1)       1   ?   ?   ?   ?   ?   ?
     * i=2 (2)       1   ?   ?   ?   ?   ?   ?
     * i=3 (3)       1   ?   ?   ?   ?   ?   ?
     * i=4 (3)       1   ?   ?   ?   ?   ?   ?
     *
     * Final DP table after computation:
     *         sum→  0   1   2   3   4   5   6
     * i=0 (empty)   1   0   0   0   0   0   0
     * i=1 (1)       1   1   0   0   0   0   0
     * i=2 (2)       1   1   1   1   0   0   0
     * i=3 (3)       1   1   1   2   1   1   1
     * i=4 (3)       1   1   1   3   2   2   3
     *
     * dp[4][6] = 3 ✓ (three subsets: {1,2,3}, {1,2,3}, {3,3})
     *
     * Each cell dp[i][j] represents the COUNT of subsets using first i elements
     * that sum to j (not just true/false like subset sum problem).
     */

    // DP2SubsetSumProblem me OR(||) ki jagah pe + use lagado and baaki sab same hai
    // false ko 0 aur true ko 1 maan ke chalna hai in Subset Sum Problem
    // (3) Tabulation approach
    public int countSubsetsTabulation(int[] arr, int n, int target) {
        int[][] dp = new int[n + 1][target + 1];

        // This initialization is important for edge case like num = [0], target = 0, output = 2
        // so that we can increase the value when we reach j = 0 again for i = 1;
        // Base Cases
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; // There's always one way to make sum 0 (by choosing no elements)
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (arr[i - 1] <= j) {
                    // Check if we can obtain the target by including or excluding the last element
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
                } else {
                    // If last element is greater than target, ignore it
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][target];
    }

    // (4) Space Optimization approach
    public int countSubsetsSpaceOptimization(int[] arr, int n, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1; // one way to make sum 0 initially

        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                dp[0] *= 2; // zero can be picked or skipped without changing sum
                continue;
            }
            for (int j = target; j >= arr[i]; j--) {
                dp[j] = dp[j] + dp[j - arr[i]];
            }
        }

        return dp[target];
    }

//    public static void main(String[] args) {
//        DP3CountOfSubsetsSumWithAGivenSum solver = new DP3CountOfSubsetsSumWithAGivenSum();
//        int[] arr = {1, 2, 3, 3};   // int[] arr = {0};
//        int target = 6;             // int target = 0;
//        int n = arr.length;         // output should be 2 for arr={0} and diff=0
//
//        System.out.println("Recursive: " + solver.countSubsetsRecursive(arr, n, target));
//        System.out.println("Memoization: " + solver.countSubsetsMemoizationBase(arr, n, target));
//        System.out.println("Tabulation: " + solver.countSubsetsTabulation(arr, n, target));
//        System.out.println("Space Optimization: " + solver.countSubsetsSpaceOptimization(arr, n, target));
//    }
}
