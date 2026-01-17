package org.example.dp_aditya_verma;

public class DP5CountTheNumberOfSubsetWithAGivenDifference {
//    Given an array arr[] of size N and a given difference diff, the task is to count the number of partitions
//    that we can perform such that the difference between the sum of the two subsets is equal to the given difference.
//
//    Note: A partition in the array means dividing an array into two parts say S1 and S2 such that
//    the union of S1 and S2 is equal to the original array and each element is present in only of the subsets.
//
//    Examples:
//
//    Input: N = 4, arr[] = [5, 2, 6, 4], diff = 3
//    Output: 1
//    Explanation: We can only have a single partition which is shown below:
//    {5, 2} and {6, 4} such that S1 = 7 and S2 = 10 and thus the difference is 3
//
//    Input: N = 5, arr[] = [1, 2, 3, 1, 2], diff = 1
//    Output: 5
//    Explanation: We can have five partitions which is shown below
//    {1, 3, 1} and {2, 2} - S1 = 5, S2 = 4
//    {1, 2, 2} and {1, 3} - S1 = 5, S2 = 4
//    {3, 2} and {1, 1, 2} - S1 = 5, S2 = 4
//    {1, 2, 2} and {1, 3} - S1 = 5, S2 = 4
//    {3, 2} and {1, 1, 2} - S1 = 5, S2 = 4

    // We need to find count of subsets with given sum
    // Let sum of array elements = S
    // Let two subset sums be S1 and S2
    // We need to find count of partitions such that
    //                  S1 - S2 = diff
    // We know that     S1 + S2 = S  => S2 = S - S1
    // So,        S1 - (S - S1) = diff
    //              => 2*S1 - S = diff
    //                  => 2*S1 = diff + S
    //                    => S1 = (diff + S) / 2
    // So, we need to find count of subsets with sum = (diff + S) / 2

    // So this problem becomes to "Count of Subsets with Given Sum" problem
    // where given sum = (diff + S) / 2

    // (1) Recursive approach
    public int countSubsetsRecursive(int[] arr, int n, int diff) {
        // Calculate total sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        // Check if solution exists
        if ((diff + sum) % 2 != 0 || diff + sum < 0) return 0;

        int target = (diff + sum) / 2;
        return countSubsetsRecursiveHelper(arr, n, target);
    }

    private int countSubsetsRecursiveHelper(int[] arr, int n, int target) {
        // Base Cases
        if (target == 0) return 1; // Found a valid subset
        if (n == 0) return 0; // No elements left

        // If last element is greater than target, ignore it
        if (arr[n - 1] > target) return countSubsetsRecursiveHelper(arr, n - 1, target);

        // Otherwise, check if we can obtain the target by including or excluding the last element
        return countSubsetsRecursiveHelper(arr, n - 1, target) + countSubsetsRecursiveHelper(arr, n - 1, target - arr[n - 1]);
    }

    // (2) Memoization approach base function
    public int countSubsetsMemoizationBase(int[] arr, int n, int diff) {
        // Calculate total sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        // Check if solution exists
        if ((diff + sum) % 2 != 0 || diff + sum < 0) return 0;

        int target = (diff + sum) / 2;
        Integer[][] dp = new Integer[n + 1][target + 1];
        return countSubsetsMemoization(arr, n, target, dp);
    }

    // (2) Memoization approach
    private int countSubsetsMemoization(int[] arr, int n, int target, Integer[][] dp) {
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
    public int countSubsetsTabulation(int[] arr, int n, int diff) {
        // Calculate total sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        // Check if solution exists
        if ((diff + sum) % 2 != 0 || diff + sum < 0) return 0;

        int target = (diff + sum) / 2;
        int[][] dp = new int[n + 1][target + 1];

        // Base Cases
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; // There's always one way to make sum 0 (by choosing no elements)
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                // If last element is greater than target, ignore it
                if (arr[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // Otherwise, check if we can obtain the target by including or excluding the last element
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        return dp[n][target];
    }

    // (4) Space Optimization approach
    public int countSubsetsSpaceOptimization(int[] arr, int n, int diff) {
        // Calculate total sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        // Check if solution exists
        if ((diff + sum) % 2 != 0 || diff + sum < 0) return 0;

        int target = (diff + sum) / 2;
        int[] dp = new int[target + 1];
        dp[0] = 1; // There's always one way to make sum 0 (by choosing no elements)

        for (int i = 0; i < n; i++) {
            for (int j = target; j >= arr[i]; j--) {
                dp[j] = dp[j] + dp[j - arr[i]];
            }
        }

        return dp[target];
    }

//    public static void main(String[] args) {
//        DP5CountTheNumberOfSubsetWithAGivenDifference solver = new DP5CountTheNumberOfSubsetWithAGivenDifference();
//        int[] arr = {5, 2, 6, 4};
//        int diff = 3;
//        int n = arr.length;
//
//        System.out.println("Recursive: " + solver.countSubsetsRecursive(arr, n, diff));
//        System.out.println("Memoization: " + solver.countSubsetsMemoizationBase(arr, n, diff));
//        System.out.println("Tabulation: " + solver.countSubsetsTabulation(arr, n, diff));
//        System.out.println("Space Optimization: " + solver.countSubsetsSpaceOptimization(arr, n, diff));
//    }

    public static void main(String[] args) {
        DP5CountTheNumberOfSubsetWithAGivenDifference solver = new DP5CountTheNumberOfSubsetWithAGivenDifference();
        int[] arr = {0};
        int diff = 0;
        int n = arr.length;

        System.out.println("Recursive: " + solver.countSubsetsRecursive(arr, n, diff));
        System.out.println("Memoization: " + solver.countSubsetsMemoizationBase(arr, n, diff));
        System.out.println("Tabulation: " + solver.countSubsetsTabulation(arr, n, diff));
        System.out.println("Space Optimization: " + solver.countSubsetsSpaceOptimization(arr, n, diff));
    }
}
