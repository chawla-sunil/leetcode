package org.example.dp_aditya_verma;

public class DP10LC322CoinChange {
//    You are given an integer array coins representing coins of different
//    denominations and an integer amount representing a total amount of money.
//
//    Return the fewest number of coins that you need to make up that amount.
//    If that amount of money cannot be made up by any combination of the coins, return -1.
//
//    You may assume that you have an infinite number of each kind of coin.
//
//
//
//    Example 1:
//    Input: coins = [1,2,5], amount = 11
//    Output: 3
//    Explanation: 11 = 5 + 5 + 1
//
//    Example 2:
//    Input: coins = [2], amount = 3
//    Output: -1
//
//    Example 3:
//    Input: coins = [1], amount = 0
//    Output: 0
//
//
//    Constraints:
//
//            1 <= coins.length <= 12
//            1 <= coins[i] <= 231 - 1
//            0 <= amount <= 104

    // This is Unbounded Knapsack problem modified version =>
    // Solution => (3) Dynamic Programming Tabulation approach
    // just change like this =>
    // wt[]  => coins[]
    // val[] => remove it from the code
    // W     => amount

    // (1) Recursive approach
    public int coinChangeRecursive(int[] coins, int amount, int n) {
        // Base condition
        if (amount == 0) {
            return 0;
        }
        if (n == 0) {
            return Integer.MAX_VALUE - 1; // to avoid overflow
        }

        if (coins[n-1] <= amount) {
            return Math.min(1 + coinChangeRecursive(coins, amount - coins[n-1], n), // pick the coin
                    coinChangeRecursive(coins, amount, n-1)); // not pick the coin
        } else {
            return coinChangeRecursive(coins, amount, n-1); // not pick the coin
        }
    }

    // (2) Memoization approach base function
    public int coinChangeMemoizationBase(int[] coins, int amount, int n) {
        Integer[][] dp = new Integer[n + 1][amount + 1];
        return coinChangeMemoization(coins, amount, n, dp);
    }
    // (2) Memoization approach
    private int coinChangeMemoization(int[] coins, int amount, int n, Integer[][] dp) {
        // Base condition
        if (amount == 0) {
            return 0;
        }
        if (n == 0) {
            return Integer.MAX_VALUE - 1; // to avoid overflow
        }

        // If the value is already computed, return it
        if (dp[n][amount] != null) {
            return dp[n][amount];
        }

        if (coins[n-1] <= amount) {
            dp[n][amount] = Math.min(1 + coinChangeMemoization(coins, amount - coins[n-1], n, dp), // pick the coin
                    coinChangeMemoization(coins, amount, n-1, dp)); // not pick the coin
        } else {
            dp[n][amount] = coinChangeMemoization(coins, amount, n-1, dp); // not pick the coin
        }

        return dp[n][amount];
    }

    // (3) This approach is called bottom-up (tabulation)
    // Submit on LeetCode = coinChange and coinChange2 method

    // coinChange and coinChange2 are exact same Solution,
    // just different way to stop integer overflow
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        // Initialize the dp array
        for (int j = 1; j <= amount; j++) {
            dp[0][j] = Integer.MAX_VALUE - 1; // to avoid overflow, how will work =>
            // Because we will choose min(1 + MAX-1 and MAX-1),
            // so all values will be MAX-1 whenever we encounter this =>
            // dp[i][j] = Math.min(1 + dp[i][j - coins[i - 1]], dp[i - 1][j]);
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] <= j) {
                    dp[i][j] = Math.min(1 + dp[i][j - coins[i - 1]], // pick the coin
                            dp[i - 1][j]); // not pick the coin
                } else {
                    dp[i][j] = dp[i - 1][j]; // not pick the coin
                }
            }
        }

        return dp[n][amount] == Integer.MAX_VALUE - 1 ? -1 : dp[n][amount];
    }

    // This is Unbounded Knapsack problem modification
    public int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];

        // loop is not starting from 0 because
        // ✅ 0 coins needed to make amount 0
        for (int j = 1; j <= amount; j++) {
            // Impossible to make amount j with 0 coins
            dp[0][j] = Integer.MAX_VALUE; // Array is empty, so how many coins to sum = j(amount);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                if (coins[i-1] <= j) {
                    // This is done to stop integer overflow
                    int includeI = 1 + dp[i][j - coins[i - 1]];
                    int excludeI = dp[i-1][j];
                    if (dp[i][j - coins[i - 1]] == Integer.MAX_VALUE) {
                        includeI = Integer.MAX_VALUE;
                    }
                    dp[i][j] = Math.min(includeI, excludeI);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][amount] == Integer.MAX_VALUE ? -1 : dp[n][amount];
    }
}
