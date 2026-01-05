package org.example.top_interview_questions;

public class LC121BestTimeToBuyAndSellStock {
//    You are given an array prices where prices[i] is the price of a given stock on the ith day.
//
//    You want to maximize your profit by choosing a single day to buy one stock and choosing a
//    different day in the future to sell that stock.
//
//    Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
//
//    Example 1:
//    Input: prices = [7,1,5,3,6,4]
//    Output: 5
//    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
//    Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
//
//    Example 2:
//    Input: prices = [7,6,4,3,1]
//    Output: 0
//    Explanation: In this case, no transactions are done and the max profit = 0.
//
//
//    Constraints:
//            1 <= prices.length <= 10^5
//            0 <= prices[i] <= 10^4


    // this is the best solution
    // this and maxProfit3 are same solution
    public int maxProfit(int[] prices) {
        int minBuy = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minBuy) {
                minBuy = prices[i];
            } else {
                profit = Math.max(profit, prices[i] - minBuy);
            }
        }
        return profit;
    }

    // Simple Solution but giving TLE, O(n^2) time
    public int maxProfit2(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i; j < prices.length; j++) {
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max;
    }

    // In O(n) time
    public int maxProfit3(int[] prices) {
        int buy = Integer.MAX_VALUE; // int buy = prices[0]; it is same thing
        int sell = 0; // sell = profit

        for (int i = 0; i < prices.length; i++) {
            buy = Math.min(buy, prices[i]);
            sell = Math.max(sell, prices[i] - buy);
        }
        return sell;
    }
}
