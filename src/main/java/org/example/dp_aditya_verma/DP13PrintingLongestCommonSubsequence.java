package org.example.dp_aditya_verma;

public class DP13PrintingLongestCommonSubsequence {
//    Given two sequences, print the longest subsequence present in both of them.
//
//    Examples:
//
//    LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
//    LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.


    // Extension of DP11LC1143LongestCommonSubsequence.java longestCommonSubsequenceTabulation method
    public String printLongestCommonSubsequence(String x, String y, int m, int n) {
        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int i = m;
        int j = n;
        StringBuilder lcs = new StringBuilder();   // String lcs = "";

        while (i > 0 && j > 0) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) {
                lcs.insert(0, x.charAt(i - 1));   // lcs = x.charAt(i - 1) +  lcs;
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.toString();  // return lcs;
    }

//    public static void main(String[] args) {
//        DP13PrintingLongestCommonSubsequence lcs = new DP13PrintingLongestCommonSubsequence();
//        String X = "AGGTAB";
//        String Y = "GXTXAYB";
//        int m = X.length();
//        int n = Y.length();
//        System.out.println("Longest Common Subsequence is: " + lcs.printLongestCommonSubsequence(X, Y, m, n));
//    }
}
