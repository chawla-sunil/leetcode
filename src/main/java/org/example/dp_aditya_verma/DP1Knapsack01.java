package org.example.dp_aditya_verma;

public class DP1Knapsack01 {
//    Given two arrays, val[] and wt[], where each element represents the value and weight of an item respectively,
//    and an integer W representing the maximum capacity of the knapsack (the total weight it can hold).
//
//    The task is to put the items into the knapsack such that
//    the total value obtained is maximum without exceeding the capacity W.
//
//    Note: You can either include an item completely or exclude it entirely — fractional selection of items is not allowed.
//    Each item is available only once.
//
//    Input: W = 4, val[] = [1, 2, 3], wt[] = [4, 5, 1]
//    Output: 3
//    Explanation: Choose the last item, which weighs 1 unit and has a value of 3.
//
//    Input: W = 7, val[] = [3, 8, 7, 4], wt[] = [3, 1, 2, 6]
//    Output: 18
//    Explanation: Choose the last item, which weighs 1 unit and has a value of 3.


    // (1) Recursive approach
    public int knapsackRecursive(int[] wt, int[] val, int W, int n) {
        // Base condition
        if (n == 0 || W == 0) {
            return 0;
        }

        // If weight of the nth item is more than Knapsack capacity W,
        // then this item cannot be included in the optimal solution
        if (wt[n-1] > W) {
            return knapsackRecursive(wt, val, W, n-1);
        } else {
            // Return the maximum of two cases:
            // (1) nth item included
            // (2) not included
            return Math.max(
                    val[n-1] + knapsackRecursive(wt, val, W - wt[n-1], n-1),  // includeItem
                    knapsackRecursive(wt, val, W, n-1)                            // excludeItem
            );
        }
    }



    // (2) This approach is called top-down (memoization)
    public int knapsackMemoizationBase(int[] wt, int[] val, int W, int n) {
//        int[][] dp = new int[n + 1][W + 1];
//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= W; j++) {
//                dp[i][j] = -1;
//            }
//        }
        // if we choose Integer class then we can use null to check uninitialized state
        // instead of initializing with -1

        Integer[][] dp = new Integer[n+1][W+1];
        return knapsackMemoization(wt, val, W, n-1, dp);
    }

    // helper method for memoization, starting point method is knapsackMemoizationBase
    public int knapsackMemoization(int[] wt, int[] val, int W, int n, Integer[][] dp) {
        // Base condition
        if (n == 0 || W == 0) {
            return 0;
        }

        // Check if the value is already computed
        if (dp[n][W] != null) {
            return dp[n][W];
        }

        // If weight of the nth item is more than Knapsack capacity W,
        // then this item cannot be included in the optimal solution
        if (wt[n-1] > W) {
            dp[n][W] = knapsackMemoization(wt, val, W, n-1, dp);
        } else {
            // Return the maximum of two cases:
            // (1) nth item included
            // (2) not included
            dp[n][W] = Math.max(
                    val[n-1] + knapsackMemoization(wt, val, W - wt[n-1], n-1, dp),  // includeItem
                    knapsackMemoization(wt, val, W, n-1, dp)                            // excludeItem
            );
        }
        return dp[n][W];
    }


    // (3) Tabulation approach
    // draw the table for better understanding with initialized base conditions which is zero row and zero column values
    // being zero and then filling the table row by row or column by column
    // n -> i,   W -> j   => easier to understand

    // table for understanding the approach
    /*
                wt  = {3, 1, 2, 6}
                val = {3, 8, 7, 4}
                W = 7

                Item details:
                i=1: wt=3, val=3
                i=2: wt=1, val=8
                i=3: wt=2, val=7
                i=4: wt=6, val=4

                DP Table:
                        W(j)→  0   1   2   3   4   5   6   7
                               -----------------------------
                i=0 (no items) 0   0   0   0   0   0   0   0
                i=1 (wt=3,v=3) 0   0   0   3   3   3   3   3
                i=2 (wt=1,v=8) 0   8   8   11  11  11  11  11
                i=3 (wt=2,v=7) 0   8   8   11  15  15  18  18
                i=4 (wt=6,v=4) 0   8   8   11  15  15  18  18

                Maximum value = dp[4][7] = 18

     */

    // (3) This is bottom-up (tabulation) approach
    public int knapsackTabulation(int[] wt, int[] val, int W, int n) {
        int[][] dp = new int[n+1][W+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                // Base condition
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (wt[i-1] <= j) {
                    dp[i][j] = Math.max(
                            val[i-1] + dp[i-1][j - wt[i-1]],  // includeItem
                            dp[i-1][j]                        // excludeItem
                    );
                } else {
                    dp[i][j] = dp[i-1][j]; // excludeItem
                }
            }
        }

        return dp[n][W];
    }


//    public static void main(String[] args) {
//        DP1Knapsack01 knapsack = new DP1Knapsack01();
//        int[] val = {3, 8, 7, 4};
//        int[] wt = {3, 1, 2, 6};
//        int W = 7;
//        int n = val.length;
//        int maxValue = knapsack.knapsackRecursive(wt, val, W, n);
//        System.out.println("Maximum value in Knapsack = " + maxValue); // Output: 18
//        int maxValue2 = knapsack.knapsackMemoizationBase(wt, val, W, n);
//        System.out.println("Maximum value in Knapsack = " + maxValue2); // Output: 18
//        int maxValue3 = knapsack.knapsackTabulation(wt, val, W, n);
//        System.out.println("Maximum value in Knapsack = " + maxValue3); // Output: 18
//    }

}
