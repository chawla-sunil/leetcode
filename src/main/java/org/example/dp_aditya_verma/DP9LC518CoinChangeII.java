package org.example.dp_aditya_verma;

public class DP9LC518CoinChangeII {
//    You are given an integer array coins representing coins of different denominations
//    and an integer amount representing a total amount of money.
//
//    Return the number of combinations that make up that amount.
//    If that amount of money cannot be made up by any combination of the coins, return 0.
//
//    You may assume that you have an infinite number of each kind of coin.
//
//    The answer is guaranteed to fit into a signed 32-bit integer.
//
//
//    Example 1:
//    Input: amount = 5, coins = [1,2,5]
//    Output: 4
//    Explanation: there are four ways to make up the amount:
//            5=5
//            5=2+2+1
//            5=2+1+1+1
//            5=1+1+1+1+1
//
//
//    Example 2:
//    Input: amount = 3, coins = [2]
//    Output: 0
//    Explanation: the amount of 3 cannot be made up just with coins of 2.
//
//
//    Example 3:
//    Input: amount = 10, coins = [10]
//    Output: 1
//
//
//    Constraints:
//
//            1 <= coins.length <= 300
//            1 <= coins[i] <= 5000
//            All the values of coins are unique.
//            0 <= amount <= 5000

    // This is Unbounded Knapsack problem modified version =>
    // Solution => (3) Dynamic Programming Tabulation approach
    // just change like this =>
    // wt[]  => coins[]
    // val[] => remove it from the code
    // W     => amount

    // (1) Recursive approach
    public int coinChange2Recursive(int[] coins, int amount, int n) {
        // Base condition
        if (amount == 0) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }

        if (coins[n-1] <= amount) {
            // Return the sum of two cases:
            // (1) nth coin included (note that n is not reduced here because we can include it again)
            // (2) not included
            return coinChange2Recursive(coins, amount - coins[n-1], n) +  // includeCoin
                   coinChange2Recursive(coins, amount, n-1);               // excludeCoin
        } else {
            // If value of the nth coin is more than amount,
            // then this coin cannot be included in the solution
            return coinChange2Recursive(coins, amount, n-1);
        }
    }

    // (2) Memoization approach base function
    public int coinChange2MemoizationBase(int[] coins, int amount) {
        int n = coins.length;
        Integer[][] dp = new Integer[n + 1][amount + 1];
        return coinChange2Memoization(coins, amount, n, dp);
    }

    // (2) Memoization approach
    private int coinChange2Memoization(int[] coins, int amount, int n, Integer[][] dp) {
        // Base condition
        if (amount == 0) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }

        // If the value is already computed, return it
        if (dp[n][amount] != null) {
            return dp[n][amount];
        }

        if (coins[n-1] <= amount) {
            // Return the sum of two cases:
            // (1) nth coin included (note that n is not reduced here because we can include it again)
            // (2) not included
            dp[n][amount] = coinChange2Memoization(coins, amount - coins[n-1], n, dp) +  // includeCoin
                            coinChange2Memoization(coins, amount, n-1, dp);               // excludeCoin
        } else {
            // If value of the nth coin is more than amount,
            // then this coin cannot be included in the solution
            dp[n][amount] = coinChange2Memoization(coins, amount, n-1, dp);
        }

        return dp[n][amount];
    }

    // This is submitted to leetcode version
    // (3) Dynamic Programming Tabulation approach
    public int coinChange2DP(int[] coins, int amount) {
        // This is variation of Unbounded Knapsack problem
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        // Initialize: one way to make amount 0 (use no coins)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] <= j) {
                    // Add ways: include coin (reuse possible) + exclude coin
                    dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
                    //         ↑ current row (reuse)   ↑ previous row (exclude)
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][amount];
    }

//    public static void main(String[] args) {
//        DP9LC518CoinChangeII solver = new DP9LC518CoinChangeII();
//        int[] coins = {1, 2, 5};
//        int amount = 5;
//
//        int waysRecursive = solver.coinChange2Recursive(coins, amount, coins.length);
//        System.out.println("Ways (Recursive): " + waysRecursive);
//
//        int waysMemoization = solver.coinChange2MemoizationBase(coins, amount);
//        System.out.println("Ways (Memoization): " + waysMemoization);
//
//        int waysDP = solver.coinChange2DP(coins, amount);
//        System.out.println("Ways (DP Tabulation): " + waysDP);
//    }
}
