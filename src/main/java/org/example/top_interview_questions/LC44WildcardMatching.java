package org.example.top_interview_questions;

public class LC44WildcardMatching {
//    Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
//
//            '?' Matches any single character.
//            '*' Matches any sequence of characters (including the empty sequence).
//    The matching should cover the entire input string (not partial).
//
//
//
//    Example 1:
//
//    Input: s = "aa", p = "a"
//    Output: false
//    Explanation: "a" does not match the entire string "aa".
//    Example 2:
//
//    Input: s = "aa", p = "*"
//    Output: true
//    Explanation: '*' matches any sequence.
//            Example 3:
//
//    Input: s = "cb", p = "?a"
//    Output: false
//    Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
//
//
//    Constraints:
//
//            0 <= s.length, p.length <= 2000
//    s contains only lowercase English letters.
//    p contains only lowercase English letters, '?' or '*'.
    
    
    
    // Recursion code below
    //
    // public boolean isMatch(String s, String p) {
    //     return checkMatch(s, p, 0, 0);
    // }

    // public boolean checkMatch(String s, String p, int i, int j) {
    //     if (i == s.length() && j == p.length()) {
    //         return true;
    //     }

    //     if (j > p.length() - 1 && i <= s.length() - 1) {
    //         return false;
    //     }

    //     if (i>s.length()-1 && j <= p.length()-1) {
    //         for (int jj=j; jj <= p.length() - 1; jj++){
    //             if (p.charAt(jj) != '*') {
    //                 return false;
    //             }
    //         }
    //         return true;
    //     }

    //     boolean match = (i < s.length()) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');

    //     if (p.charAt(j) == '*') {
    //         return checkMatch(s, p, i, j+1) // skiping *
    //                || checkMatch(s, p, i+1, j); // taking *
    //     }

    //     if (match) {
    //         return checkMatch(s, p, i+1, j+1);
    //     }
    //     return false;
    // }

    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();

        // Create a DP array to store intermediate results
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];

        // Initialize the DP array
        dp[0][0] = true;

        // Handle patterns that start with '*'
        for (int j = 1; j <= pLen; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        // Fill the DP array
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }

        return dp[sLen][pLen];
    }
}
