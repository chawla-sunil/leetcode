package org.example.dp_aditya_verma;

public class DP18LC1312MinimumInsertionStepsToMakeAStringPalindrome {
//    Given a string s. In one step you can insert any character at any index of the string.
//
//    Return the minimum number of steps to make s palindrome.
//
//    A Palindrome String is one that reads the same backward as well as forward.
//
//
//
//    Example 1:
//    Input: s = "zzazz"
//    Output: 0
//    Explanation: The string "zzazz" is already palindrome we do not need any insertions.
//
//    Example 2:
//    Input: s = "mbadm"
//    Output: 2
//    Explanation: String can be "mbdadbm" or "mdbabdm".
//
//    Example 3:
//    Input: s = "leetcode"
//    Output: 5
//    Explanation: Inserting 5 characters the string becomes "leetcodocteel".

    // For LPS (Longest Palindromic Subsequence), we just need to find
    // LCS (Longest Common Subsequence) of s and reverse(s)
    public int minInsertions(String s) {
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

        int lps = dp[m][n];

        return m - lps;
    }
}
