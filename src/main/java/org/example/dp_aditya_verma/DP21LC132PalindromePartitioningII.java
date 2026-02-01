package org.example.dp_aditya_verma;

public class DP21LC132PalindromePartitioningII {
//    Given a string s, partition s such that every substring of the partition is a palindrome.
//
//    Return the minimum cuts needed for a palindrome partitioning of s.
//
//
//    Example 1:
//    Input: s = "aab"
//    Output: 1
//    Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
//
//    Example 2:
//    Input: s = "a"
//    Output: 0
//
//    Example 3:
//    Input: s = "ab"
//    Output: 1
//
//
//    Constraints:
//
//            1 <= s.length <= 2000
//    s consists of lowercase English letters only.

    // (3) Memoization Approach, a little more optimised
    // minCut is the starting method
    public int minCut(String s) {
        int n = s.length();
        Integer[][] dp = new Integer[n][n];
        return minCutMemoizationOptimised(s, 0, n-1, dp);
    }

    public int minCutMemoizationOptimised(String s, int i, int j, Integer[][] dp) {
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (i >= j || isPalindrome(s, i, j)) {
            dp[i][j] = 0;
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            // Normal memoization function minCutMemoization was giving TLE,
            // so lets make it more optimized by checking if we i to k is palindrome or now
            if (isPalindrome(s, i, k)) {
                // Reason for if:
                // if the left string (after partition) is not a palindrome,
                // then we can't make a partition there, so it would be a waste of time
                // to check for the ans at that partition. Also, if, left side string is
                // a palindrome no need to call PP(str,i,k) because we will not be doing
                // any partition on the left side, we will just calculate the ans for right side
                // and add 1(because we just made a partition here)
                // AND SINCE i to k IS PALINDROME, we willn= not need to calculate minCutMemoizationOptimised(s, i, k, dp)
                int temp = 1 + minCutMemoizationOptimised(s, k+1, j, dp);
                min = Math.min(min, temp);
            }
        }

        dp[i][j] = min;
        return dp[i][j];
    }

    public boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // (1) Recursion Approach
    // Variation of MCM question aditya verma
    // Getting TLE(Time Limit Exceeded) on this approach, now lets use memoization
    // minCut is the starting method
    public int minCut2(String s) {
        int n = s.length();
        return minCutRecursion(s, 0, n-1);
    }

    public int minCutRecursion(String s, int i, int j) {
        if (i >= j || isPalindrome(s, i, j)) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        // int min = s.length() - 1; is also correct because we can make it palindrome
        // by every character, a single character is a palindrome
        for (int k = i; k < j; k++) {
            int temp = 1 + minCutRecursion(s, i, k) + minCutRecursion(s, k+1, j);
            // we have added 1+ that means we have made 1 cut at k (i to k, k+1 to j)
            min = Math.min(min, temp);
        }

        return min;
    }

    // (2) Memoization Approach
    // minCut is the starting method
    // Getting TLE(Time Limit Exceeded) on this approach, now lets use some other method
    public int minCut3(String s) {
        int n = s.length();
        Integer[][] dp = new Integer[n][n];
        return minCutMemoization(s, 0, n-1, dp);
    }

    public int minCutMemoization(String s, int i, int j, Integer[][] dp) {
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (i >= j || isPalindrome(s, i, j)) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int temp = 1 + minCutMemoization(s, i, k, dp) + minCutMemoization(s, k+1, j, dp);
            min = Math.min(min, temp);
        }

        dp[i][j] = min;
        return dp[i][j];
    }

    // In Java, String is immutable and always passed by value (the reference itself is copied).
    // To avoid creating new substring objects and pass by reference semantics, using StringBuilder
    // A little more optimized version of previous function
    // Still getting TLE
    public int minCut4(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        Integer[][] dp = new Integer[n][n];
        return minCutMemoizationOptimised(sb, 0, n-1, dp);
    }

    public int minCutMemoizationOptimised(StringBuilder s, int i, int j, Integer[][] dp) {
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (i >= j || isPalindrome(s, i, j)) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            if (isPalindrome(s, i, k)) {
                int temp = 1 + minCutMemoizationOptimised(s, i, k, dp) + minCutMemoizationOptimised(s, k+1, j, dp);
                min = Math.min(min, temp);
            }
        }

        dp[i][j] = min;
        return dp[i][j];
    }

    public boolean isPalindrome(StringBuilder s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
