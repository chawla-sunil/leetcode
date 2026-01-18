package org.example.dp_aditya_verma;

public class DP7UnboundedKnapsack {
//    Given a knapsack weight, say capacity and a set of n items with certain value and weight,
//    The task is to fill the knapsack in such a way that we can get the maximum profit.
//    This is different from the classical Knapsack problem,
//    here we are allowed to use an unlimited number of instances of an item.
//
//    Examples:
//
//    Input: capacity = 100, val[]  = [1, 30], wt[] = [1, 50]
//    Output: 100 (take 1 value item 100 times)
//    Explanation: There are many ways to fill knapsack.
//                1) 2 instances of 50 unit weight item.
//                2) 100 instances of 1 unit weight item.
//                3) 1 instance of 50 unit weight item and 50 instances of 1 unit weight items.
//    We get maximum value with option 2.

    // (1) Recursive approach
    public int unboundedKnapsackRecursive(int[] wt, int[] val, int W, int n) {
        // Base condition
        if (n == 0 || W == 0) {
            return 0;
        }

        if (wt[n-1] <= W) {
            // Return the maximum of two cases:
            // (1) nth item included (note that n is not reduced here because we can include it again)
            // (2) not included
            return Math.max(
                    val[n-1] + unboundedKnapsackRecursive(wt, val, W - wt[n-1], n),  // includeItem
                    unboundedKnapsackRecursive(wt, val, W, n-1)                            // excludeItem
            );
        } else {
            // If weight of the nth item is more than Knapsack capacity W,
            // then this item cannot be included in the optimal solution
            return unboundedKnapsackRecursive(wt, val, W, n-1);
        }
    }

    // (2) This approach is called top-down (memoization)
    public int unboundedKnapsackMemoizationBase(int[] wt, int[] val, int W, int n) {
        Integer[][] dp = new Integer[n+1][W+1];
        return unboundedKnapsackMemoization(wt, val, W, n, dp);
    }

    private int unboundedKnapsackMemoization(int[] wt, int[] val, int W, int n, Integer[][] dp) {
        // Base condition
        if (n == 0 || W == 0) {
            return 0;
        }

        // If the value is already computed, return it
        if (dp[n][W] != null) {
            return dp[n][W];
        }

        // If weight of the nth item is more than Knapsack capacity W,
        // then this item cannot be included in the optimal solution
        if (wt[n-1] > W) {
            dp[n][W] = unboundedKnapsackMemoization(wt, val, W, n-1, dp);
        } else {
            // Return the maximum of two cases:
            // (1) nth item included (note that n is not reduced here)
            // (2) not included
            dp[n][W] = Math.max(
                    val[n-1] + unboundedKnapsackMemoization(wt, val, W - wt[n-1], n, dp),  // includeItem
                    unboundedKnapsackMemoization(wt, val, W, n-1, dp)                            // excludeItem
            );
        }
        return dp[n][W];
    }

    // (3) This approach is called bottom-up (tabulation)
    public int unboundedKnapsackTabulation(int[] wt, int[] val, int W, int n) {
        int[][] dp = new int[n + 1][W + 1];

        // Fill the dp array
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], val[i - 1] + dp[i][w - wt[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // OR this loop
//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= W; j++) {
//                if (i == 0 || j == 0) {
//                    // We just added this extra condition to handle base case
//                    // But it is not actually needed as dp array is int which is initialized with 0 by default
//                    dp[i][j] = 0;
//                } else if (dp[i][j] <= j) {
//                    dp[i][j] = Math.max(val[n-1] + dp[i][j - wt[i-1]], dp[i - 1][j]);
//                } else {
//                    dp[i][j] = dp[i - 1][j];
//                }
//            }
//        }
        return dp[n][W];
    }

    // (4) Space Optimized Tabulation approach
    public int unboundKnapsackTabulationSpaceOptimized(int[] wt, int[] val, int W, int n) {
        int[] dp = new int[W + 1];

        // Fill the dp array
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= W; j++) {
                // Check if current item can fit
                if (wt[i - 1] <= j) {
                    // Include or exclude the item (item can be reused)
                    dp[j] = Math.max(dp[j], val[i - 1] + dp[j - wt[i - 1]]);
                } else {
                    dp[j] = dp[j]; // remains unchanged (exclude item)
                    // This code else is not required, just for understanding
                }
            }
        }

        // More optimized loop
//        for (int i = 0; i < n; i++) {
//            for (int j = wt[i]; j <= W; j++) {
//                // Here we are using wt[i] instead of 1 because
//                // for weights less than wt[i], we cannot include the item
//
//                // Here, 2nd loop is forward because
//                // we can include the same item multiple times, so we need to consider
//                // we are using dp[i] on right side which means we can include the same item again
//                dp[j] = Math.max(dp[j], val[i] + dp[j - wt[i]]);
//            }
//        }
        return dp[W];
    }


//    public static void main(String[] args) {
//        DP7UnboundedKnapsack unboundedKnapsack = new DP7UnboundedKnapsack();
//        int[] wt = {1, 50};
//        int[] val = {1, 30};
//        int W = 100;
//        int n = wt.length;
//
//        int maxValRecursive = unboundedKnapsack.unboundedKnapsackRecursive(wt, val, W, n);
//        System.out.println("Maximum value (Recursive): " + maxValRecursive);
//
//        int maxValMemoization = unboundedKnapsack.unboundedKnapsackMemoizationBase(wt, val, W, n);
//        System.out.println("Maximum value (Memoization): " + maxValMemoization);
//
//        int maxValTabulation = unboundedKnapsack.unboundedKnapsackTabulation(wt, val, W, n);
//        System.out.println("Maximum value (Tabulation): " + maxValTabulation);
//
//        int maxValTabulationOptimized = unboundedKnapsack.unboundKnapsackTabulationSpaceOptimized(wt, val, W, n);
//        System.out.println("Maximum value (Tabulation Space Optimized): " + maxValTabulationOptimized);
//    }
}
