package org.example.dp_aditya_verma;

public class DP16MinimumNumberOfDeletionsAndInsertions {
//    Given two strings s1 and s2. The task is to remove or insert the minimum number of characters
//    from/in s1 to transform it into s2. It could be possible that the same character needs to be removed
//    from one point of s1 and inserted into another point.
//
//    Examples :
//
//    Input: s1 = "heap", s2 = "pea"
//    Output: 3
//    Explanation: 'p' and 'h' deleted from heap. Then, 'p' is inserted at the beginning.
//
//    Input : s1 = "geeksforgeeks", s2 = "geeks"
//    Output: 8
//    Explanation: 8 deletions, i.e. remove all characters of the string "forgeeks".

    public int minDeletionsAndInsertions(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lcs = dp[m][n];

        // Minimum number of deletions = m - lcs
        // Minimum number of insertions = n - lcs
        return (m - lcs) + (n - lcs);
    }
}
