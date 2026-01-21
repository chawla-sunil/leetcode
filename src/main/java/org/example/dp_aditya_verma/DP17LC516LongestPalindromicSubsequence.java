package org.example.dp_aditya_verma;

public class DP17LC516LongestPalindromicSubsequence {
//    Given a string s, find the longest palindromic subsequence's length in s.
//
//    A subsequence is a sequence that can be derived from another sequence
//    by deleting some or no elements without changing the order of the remaining elements.
//
//
//
//    Example 1:
//    Input: s = "bbbab"
//    Output: 4
//    Explanation: One possible longest palindromic subsequence is "bbbb".
//
//    Example 2:
//    Input: s = "cbbd"
//    Output: 2
//    Explanation: One possible longest palindromic subsequence is "bb".
//
//
//    Constraints:
//
//            1 <= s.length <= 1000
//            s consists only of lowercase English letters.

    // For LPS (Longest Palindromic Subsequence), we just need to find
    // LCS (Longest Common Subsequence) of s and reverse(s)
    public int longestPalindromeSubseq(String s) {
        // This is just an extension problem of LCS(Longest Common Subsequence)
        // From Aditya verma DP series
        String t = new StringBuilder(s).reverse().toString();

        int m = s.length();
        int n = t.length(); // m == n obviously
        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
