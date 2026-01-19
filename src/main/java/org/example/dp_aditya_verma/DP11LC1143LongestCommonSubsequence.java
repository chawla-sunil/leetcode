package org.example.dp_aditya_verma;

public class DP11LC1143LongestCommonSubsequence {
//    Given two strings text1 and text2, return the length of their longest common subsequence.
//    If there is no common subsequence, return 0.
//
//    A subsequence of a string is a new string generated from the original string with
//    some characters (can be none) deleted without changing the relative order of the remaining characters.
//
//    For example, "ace" is a subsequence of "abcde".
//    A common subsequence of two strings is a subsequence that is common to both strings.
//
//
//
//    Example 1:
//    Input: text1 = "abcde", text2 = "ace"
//    Output: 3
//    Explanation: The longest common subsequence is "ace" and its length is 3.
//
//    Example 2:
//    Input: text1 = "abc", text2 = "abc"
//    Output: 3
//    Explanation: The longest common subsequence is "abc" and its length is 3.
//
//    Example 3:
//    Input: text1 = "abc", text2 = "def"
//    Output: 0
//    Explanation: There is no such common subsequence, so the result is 0.
//
//
//    Constraints:
//
//            1 <= text1.length, text2.length <= 1000
//            text1 and text2 consist of only lowercase English characters.

    // LCS = Longest Common Subsequence

    // (1) Recursive approach
    public int longestCommonSubsequenceRecursive(String x,
                                                 String y,
                                                 int m,
                                                 int n) {
        // Base condition
        if (m == 0 || n == 0) {
            return 0;
        }

        if (x.charAt(m - 1) == y.charAt(n - 1)) {
            return 1 + longestCommonSubsequenceRecursive(x, y, m - 1, n - 1); // pick the character
        } else {
            return Math.max(longestCommonSubsequenceRecursive(x, y, m - 1, n), // not pick from text1
                    longestCommonSubsequenceRecursive(x, y, m, n - 1)); // not pick from text2
        }
    }

    // (2) Memoization approach base function
    public int longestCommonSubsequenceMemoizationBase(String x,
                                                       String y,
                                                       int m,
                                                       int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        return longestCommonSubsequenceMemoization(x, y, m, n, dp);
    }

    // (2) Memoization approach
    public int longestCommonSubsequenceMemoization(String x,
                                                   String y,
                                                   int m,
                                                   int n,
                                                   int[][] dp) {
        // Base condition
        if (m == 0 || n == 0) {
            return 0;
        }

        if (dp[m][n] != -1) {
            return dp[m][n];
        }

        if (x.charAt(m - 1) == y.charAt(n - 1)) {
            dp[m][n] = 1 + longestCommonSubsequenceMemoization(x, y, m - 1, n - 1, dp);
        } else {
            dp[m][n] = Math.max(longestCommonSubsequenceMemoization(x, y, m - 1, n, dp),
                    longestCommonSubsequenceMemoization(x, y, m, n - 1, dp));
        }

        return dp[m][n];
    }

    // (3) Tabulation approach
    public int longestCommonSubsequenceTabulation(String x,
                                                  String y,
                                                  int m,
                                                  int n) {
        int[][] dp = new int[m + 1][n + 1];

        // Build the dp array
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) { // this condition is optional as dp is initialized to 0
                    // This is just for understanding that these are its base conditions
                    dp[i][j] = 0;
                } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]; // pick the character
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], // not pick from x
                            dp[i][j - 1]); // not pick from y
                }
            }
        }

        return dp[m][n];
    }

    // (4) Space Optimized Tabulation approach
    public int longestCommonSubsequenceTabulationSpaceOptimized(String x,
                                                                String y,
                                                                int m,
                                                                int n) {
        // We chose 2 arrays of size n+1 to store the current and previous row values
        // Because in LCS DP, we only need the previous row to compute the current row
        // This allows us to optimize space complexity from O(m*n) to O(n)
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];
        // Since raws are now only 2 (prev and curr),
        // so that's why previous is of size n+1 (total columns)

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1]; // pick the character
                } else {
                    curr[j] = Math.max(prev[j], // not pick from x, equivalent to dp[i-1][j]
                            curr[j - 1]); // not pick from y, equivalent to dp[i][j-1]
                }
            }
            // Move current row to previous row
            int[] temp = prev;
            prev = curr;
            curr = temp;
            // do actually need to do this swap and not simple prev = curr?
            // because this swaps the references, not the values.
            // we will overwrite curr in next iteration
        }

        return prev[n];
    }

//    public static void main(String[] args) {
//        DP11LC1143LongestCommonSubsequence lcs = new DP11LC1143LongestCommonSubsequence();
//        String text1 = "abcde";
//        String text2 = "ace";
//        int m = text1.length();
//        int n = text2.length();
//
//        // Test Recursive approach
//        System.out.println("Recursive: " + lcs.longestCommonSubsequenceRecursive(text1, text2, m, n));
//
//        // Test Memoization approach
//        System.out.println("Memoization: " + lcs.longestCommonSubsequenceMemoizationBase(text1, text2, m, n));
//
//        // Test Tabulation approach
//        System.out.println("Tabulation: " + lcs.longestCommonSubsequenceTabulation(text1, text2, m, n));
//
//        // Test Space Optimized Tabulation approach
//        System.out.println("Space Optimized Tabulation: " + lcs.longestCommonSubsequenceTabulationSpaceOptimized(text1, text2, m, n));
//    }
}