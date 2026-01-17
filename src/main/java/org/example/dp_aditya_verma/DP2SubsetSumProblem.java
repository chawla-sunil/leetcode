package org.example.dp_aditya_verma;

public class DP2SubsetSumProblem {
//    Given an array arr[] of non-negative integers and a value sum,
//    the task is to check if there is a subset of the given array whose sum is equal to the given sum.
//
//    Examples:
//    Input: arr[] = [3, 34, 4, 12, 5, 2], sum = 9
//    Output: True
//    Explanation: There is a subset (4, 5) with sum 9.
//
//
//    Input: arr[] = [3, 34, 4, 12, 5, 2], sum = 30
//    Output: False
//    Explanation: There is no subset that add up to 30.

    // Write all 3 approaches (1. Recursive, 2. Memoization, 3. Tabulation) like DP1Knapsack01.java class

    // (1) Recursive approach
    public boolean isSubsetSumRecursive(int[] arr, int n, int sum) {
        // Base Cases
        if (sum == 0) return true;
        if (n == 0) return false;

        // If last element is greater than sum, ignore it
        if (arr[n - 1] > sum) return isSubsetSumRecursive(arr, n - 1, sum);

        // Otherwise, check if sum can be obtained by any of the following:
        // (1) including the last element
        // (2) excluding the last element
        return isSubsetSumRecursive(arr, n - 1, sum) || isSubsetSumRecursive(arr, n - 1, sum - arr[n - 1]);
    }


    // (2) Memoization approach base function
    public boolean isSubsetSumMemoizationBase(int[] arr, int n, int sum) {
        Boolean[][] dp = new Boolean[n + 1][sum + 1];
        return isSubsetSumMemoization(arr, n, sum, dp);
    }
    // (2) Memoization approach
    public boolean isSubsetSumMemoization(int[] arr, int n, int sum, Boolean[][] dp) {
        // Base Cases
        if (sum == 0) return true;
        if (n == 0) return false;

        // Check if the result is already computed
        if (dp[n][sum] != null) return dp[n][sum];

        // If last element is greater than sum, ignore it
        if (arr[n - 1] > sum) {
            dp[n][sum] = isSubsetSumMemoization(arr, n - 1, sum, dp);
            return dp[n][sum];
        }

        // Otherwise, check if sum can be obtained by any of the following:
        // (1) including the last element
        // (2) excluding the last element
        dp[n][sum] = isSubsetSumMemoization(arr, n - 1, sum, dp) || isSubsetSumMemoization(arr, n - 1, sum - arr[n - 1], dp);
        return dp[n][sum];
    }


    /*
     * DP Table Initialization for Subset Sum Problem:
     * Example: arr[] = [3, 34, 4, 12, 5, 2], sum = 9
     *
     * Initial state (base cases):
     *         sum→  0   1   2   3   4   5   6   7   8   9
     * i=0 (empty)   T   F   F   F   F   F   F   F   F   F
     * i=1 (3)       T   ?   ?   ?   ?   ?   ?   ?   ?   ?
     * i=2 (34)      T   ?   ?   ?   ?   ?   ?   ?   ?   ?
     * i=3 (4)       T   ?   ?   ?   ?   ?   ?   ?   ?   ?
     * i=4 (12)      T   ?   ?   ?   ?   ?   ?   ?   ?   ?
     * i=5 (5)       T   ?   ?   ?   ?   ?   ?   ?   ?   ?
     * i=6 (2)       T   ?   ?   ?   ?   ?   ?   ?   ?   ?
     *
     * Final DP table after computation:
     *         sum→  0   1   2   3   4   5   6   7   8   9
     * i=0 (empty)   T   F   F   F   F   F   F   F   F   F
     * i=1 (3)       T   F   F   T   F   F   F   F   F   F
     * i=2 (34)      T   F   F   T   F   F   F   F   F   F
     * i=3 (4)       T   F   F   T   T   F   F   T   F   F
     * i=4 (12)      T   F   F   T   T   F   F   T   F   F
     * i=5 (5)       T   F   F   T   T   T   F   T   T   T
     * i=6 (2)       T   F   T   T   T   T   T   T   T   T
     *
     * dp[6][9] = T ✓ (subset {3, 4, 2} sums to 9)
     */
    // (3) Tabulation approach
    public boolean isSubsetSumTabulation(int[] arr, int n, int sum) {
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // Initialize the first column as true (sum = 0)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Initialize the first row, except dp[0][0], as false (no elements to form sum > 0)
        for (int j = 1; j <= sum; j++) {
            dp[0][j] = false;
        }

        // Fill the dp table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j]; // Exclude the element
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]]; // Include or exclude the element
                }
            }
        }

        return dp[n][sum];
    }

    // (3) Tabulation approach = exact same solution as isSubsetSumTabulation but slightly different way of writing
    public boolean isSubsetSumTabulation2(int[] arr, int sum, int n) {
        Boolean[][] dp = new Boolean[n+1][sum+1];

        for (int i =0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = false;
                } else if (arr[i-1] <= j) {
                    dp[i][j] = dp[i-1][j - arr[i-1]] || dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][sum];
    }

    // (3) Tabulation approach, with 1-D Array optimization
    // DP with 1-D Array
    // Since in 2-D Array solution also, we are alway comparing it with current num and previous raw result
    // so we can use 1-D Array also
    // In the 2-D Array 1st column is alway True(when j = 0), so we will keep dp[0]=true always in this solution
    public boolean isSubsetSumTabulation3(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }

        if (sum % 2 == 1) {
            return false;
        }

        int targetSum = sum / 2;
        boolean[] dp = new boolean[targetSum+1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = targetSum; j >= 1; j--) {
                if (nums[i-1] <= j) {
                    // until now (till i), can we create j sum or if we can create j-nums[i-1] sum
                    dp[j] = dp[j - nums[i-1]] || dp[j];
                }

                // Similar to this for comparison
                // if (nums[i-1] <= j) {
                //     dp[i][j] = dp[i-1][j - nums[i-1]] || dp[i-1][j];
                // }
            }
        }

        return dp[targetSum];
    }

//    public static void main(String[] args) {
//        DP2SubsetSumProblem subsetSum = new DP2SubsetSumProblem();
//        int[] arr = {3, 34, 4, 12, 5, 2};
//        int sum = 9;
//        int n = arr.length;
//
//        // Test Recursive approach
//        System.out.println("Recursive: " + subsetSum.isSubsetSumRecursive(arr, n, sum));
//
//        // Test Memoization approach
//        System.out.println("Memoization: " + subsetSum.isSubsetSumMemoizationBase(arr, n, sum));
//
//        // Test Tabulation approach
//        System.out.println("Tabulation: " + subsetSum.isSubsetSumTabulation(arr, n, sum));
//    }
}
