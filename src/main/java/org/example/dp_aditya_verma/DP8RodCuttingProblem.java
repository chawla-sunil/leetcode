package org.example.dp_aditya_verma;

public class DP8RodCuttingProblem {
//    Given a rod of length n inches and an array price[], where price[i] denotes the value of a piece of length i.
//    Your task is to determine the maximum value obtainable by cutting up the rod and selling the pieces.
//
//    Note: n = size of price, and price[] is 1-indexed array.
//
//    Example:
//
//    Input: price[] = [1, 5, 8, 9, 10, 17, 17, 20]
//    Output: 22
//    Explanation: The maximum obtainable value is 22 by cutting in two pieces of lengths 2 and 6, i.e., 5 + 17 = 22.


    // This is Unbounded Knapsack problem, change the parameters as per that =>
    // length => wt[]
    // price  => val[]
    //      n => W
    // So we can just use the Unbounded Knapsack code directly here with these parameter changes
    // Same conditions are also same as Unbounded Knapsack problem

    // The solutions mentioned in this class are different solutions of the same problem

    // My Explanation: (Unbounded Knapsack problem)
    // length = [1, 2, 3, 4, 5,  6,  7,  8] (length array might not be given, just for understanding)
    // price  = [1, 5, 8, 9, 10, 17, 17, 20]
    // n = 8
    // price array btata h kis length ke liye kitni price hai
    // Example: length 4 ki price 9 hai
    // Now,8m ki rod ko is prakar se cut karo ki ise beche to highest profit ho
    // So this problem becomes Unbounded Knapsack problem because
    // hum kisi bhi length ko multiple times cut kar sakte hai


    // Example: 2 + 6 = 8
    //          2 + 2 + 2 + 2 = 8
    //          4 + 4 = 8
    //          1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 = 8
    // and so on...

    // (1) Recursive approach
    public int rodCuttingRecursive(int[] price, int n) {
        // Base condition
        if (n <= 0) {
            return 0;
        }

        int maxVal = 0;

        // Try different cuts
        for (int i = 1; i <= n; i++) {
            maxVal = Math.max(maxVal, price[i - 1] + rodCuttingRecursive(price, n - i));
        }

        return maxVal;
    }

    // (2) Memoization approach base function
    public int rodCuttingMemoizationBase(int[] price, int n) {
        Integer[] dp = new Integer[n + 1];
        return rodCuttingMemoization(price, n, dp);
    }

    // (2) Memoization approach
    private int rodCuttingMemoization(int[] price, int n, Integer[] dp) {
        // Base condition
        if (n <= 0) {
            return 0;
        }

        // Check if the result is already computed
        if (dp[n] != null) {
            return dp[n];
        }

        int maxVal = 0;

        // Try different cuts
        for (int i = 1; i <= n; i++) {
            maxVal = Math.max(maxVal, price[i - 1] + rodCuttingRecursive(price, n - i));
        }

        dp[n] = maxVal;
        return maxVal;
    }

    // (3) Tabulation approach
    public int rodCuttingTabulation(int[] price, int n) {
        int[] dp = new int[n + 1];

        // Build the dp array
        for (int len = 1; len <= n; len++) {
            int maxVal = 0;
            for (int cut = 1; cut <= len; cut++) {
                maxVal = Math.max(maxVal, price[cut - 1] + dp[len - cut]);
            }
            dp[len] = maxVal;
        }

        return dp[n];
    }

//    public static void main(String[] args) {
//        DP8RodCuttingProblem rodCutting = new DP8RodCuttingProblem();
//        int[] price = {1, 5, 8, 9, 10, 17, 17, 20};
//        int n = price.length;
//
//        int maxProfitRecursive = rodCutting.rodCuttingRecursive(price, n);
//        System.out.println("Maximum Profit (Recursive): " + maxProfitRecursive);
//
//        int maxProfitMemoization = rodCutting.rodCuttingMemoizationBase(price, n);
//        System.out.println("Maximum Profit (Memoization): " + maxProfitMemoization);
//
//        int maxProfitTabulation = rodCutting.rodCuttingTabulation(price, n);
//        System.out.println("Maximum Profit (Tabulation): " + maxProfitTabulation);
//    }
}
