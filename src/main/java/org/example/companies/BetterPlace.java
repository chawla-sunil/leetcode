package org.example.companies;

import java.util.Arrays;

public class BetterPlace {
    public static void main(String[] args) {
        int[] arr = { 2,1,1,2 };
        int max = robbing(arr);
        System.out.println(max);

        int[] arr2 = {1,0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int max2 = maxOne(arr2, 3);
        System.out.println(max2);

    }

    public static int maxOne(int[] arr, int k) {
//        Maximize number of 0s by flipping a subarray
//        Given a binary array, find the maximum number of zeros in an array with one flip of a subarray allowed. A flip operation switches all 0s to 1s and 1s to 0s.
//        Examples:
//        Input :  arr[] = {0, 1, 0, 0, 1, 1, 0}
//        Output : 6
//        We can get 6 zeros by flipping the subarray {4, 5}
//
//        Input :  arr[] = {0, 0, 0, 1, 0, 1}
//        Output : 5
        int start = 0;
        int end = 0;
        int zero = 0;

        while (end < arr.length) {
            if (arr[end] == 0) {
                zero++;
            }
            end++;
            if (zero > k) {
                if (arr[start] == 0) {
                    zero--;
                }
                start++;
            }
        }
        return end -  start;
    }
    public static int robbing(int[] arr) {
//        Maximum sum such that no two elements are adjacent (Stickler Thief)
//        Given an array arr[] of positive numbers, The task is to find the maximum sum of a subsequence
//        such that no 2 numbers in the sequence should be adjacent in the array.
//
//        Input: arr[] = {3, 2, 7, 10}
//        Output: 13
//        Explanation: The subsequence is {3, 10}. This gives sum = 13.
//        This is the highest possible sum of a subsequence following the given criteria
//
//        https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return recursion(arr, 0, n, dp);
    }

    public static int recursion(int[] arr, int currIndex, int n, int[] dp) {
        if (currIndex >= n) {
            return 0;
        }
        if (dp[currIndex] != -1) {
            return dp[currIndex];
        }
        dp[currIndex] = Math.max(arr[currIndex] + recursion(arr, currIndex + 2, n, dp),
                         recursion(arr, currIndex + 1, n, dp));
        return dp[currIndex];
    }
}
