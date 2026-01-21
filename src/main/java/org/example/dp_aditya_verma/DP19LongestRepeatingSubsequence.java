package org.example.dp_aditya_verma;

public class DP19LongestRepeatingSubsequence {
//    Given a string s, the task is to find the length of the longest repeating subsequence,
//    such that the two subsequences don't have the same string character at the same position,
//    i.e. any ith character in the two subsequences shouldn't have the same index in the original string.
//
//    Examples:
//
//    Input: s= "abc"
//    Output: 0
//    Explanation: There is no repeating subsequence
//
//    Input: s= "AABEBCDD"
//    Output: 3
//    Explanation: The longest repeating subsequence is "ABD".
//    A A B E B C D D
//    * + *   +   * +  (* = one subsequence, + = other subsequence)
//    Here ABD is appearing twice and the index of A's, B's and D's are different.

    public int longestRepeatingSubsequence(String str) {
        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (str.charAt(i - 1) == str.charAt(j - 1) && i != j) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][n];
    }

//    public static void main(String[] args) {
//        DP19LongestRepeatingSubsequence obj = new DP19LongestRepeatingSubsequence();
//        String str = "AABEBCDD";
//        int result = obj.longestRepeatingSubsequence(str);
//        System.out.println("Length of Longest Repeating Subsequence: " + result); // Output: 3
//    }
}
