package org.example.dp_aditya_verma;

public class DP12LongestCommonSubstring {
//    Given two strings 's1' and 's2', find the length of the longest common substring.
//    A substring is a contiguous sequence of characters within a string.
//    x = abefcgs
//    y = pdabefnd
//    output = 4 => abef is the longest common substring


    // (1) Recursive approach Base function
    public int longestCommonSubstringRecursiveBase(String x,
                                                   String y,
                                                   int m,
                                                   int n) {
        return longestCommonSubstringRecursive(x, y, m, n, 0);
    }
    // (1) Recursive approach
    public int longestCommonSubstringRecursive(String x,
                                               String y,
                                               int m,
                                               int n,
                                               int count) {
        // Base condition
        if (m == 0 || n == 0) {
            return count;
        }

        if (x.charAt(m - 1) == y.charAt(n - 1)) {
            count = longestCommonSubstringRecursive(x, y, m - 1, n - 1, count + 1); // pick the character
        }

        count = Math.max(count,
                         Math.max(longestCommonSubstringRecursive(x, y, m - 1, n, 0), // not pick from x
                                  longestCommonSubstringRecursive(x, y, m, n - 1, 0)   // not pick from y
                         )
        );

        return count;
    }

    // (2) Memoization approach base function
    public int longestCommonSubstringMemoizationBase(String x,
                                                     String y,
                                                     int m,
                                                     int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        return longestCommonSubstringMemoization(x, y, m, n, dp);
    }
    // (2) Memoization approach
    public int longestCommonSubstringMemoization(String x,
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
            dp[m][n] = 1 + longestCommonSubstringMemoization(x, y, m - 1, n - 1, dp);
        } else {
            dp[m][n] = Math.max(longestCommonSubstringMemoization(x, y, m - 1, n, dp),
                    longestCommonSubstringMemoization(x, y, m, n - 1, dp));
        }

        return dp[m][n];
    }

    // (3) Tabulation approach
    public int longestCommonSubstringTabulation(String x,
                                                String y,
                                                int m,
                                                int n) {
        int[][] dp = new int[m + 1][n + 1];
        int result = 0;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) { // this condition is optional as dp is initialized to 0
                    // This is just for understanding that these are its base conditions
                    dp[i][j] = 0;
                } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return result;
    }

    // (4) Space Optimized Tabulation approach
    public int longestCommonSubstringSpaceOptimized(String x,
                                                    String y,
                                                    int m,
                                                    int n) {
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];
        int result = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                    result = Math.max(result, curr[j]);
                } else {
                    curr[j] = 0;
                }
            }
            prev = curr.clone();
            // Alternatively, we can swap references instead of cloning (prev = curr.clone();)
            // int[] temp = prev;
            // prev = curr;
            // curr = temp;
            // do actually need to do this swap and not simple prev = curr?
            // because this swaps the references, not the values.
            // we will overwrite curr in next iteration
        }

        return result;
    }

    // (5) other naive brute force O(n^2) approach
    public int longestCommonSubstringBruteForce(String x, String y) {
        int m = x.length();
        int n = y.length();
        int result = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int curr = 0;
                while ((i + curr) < m && (j + curr) < n
                        && x.charAt(i + curr) == y.charAt(j + curr)) {
                    curr++;
                }
                result = Math.max(result, curr);
            }
        }

        return result;
    }


//    public static void main(String[] args) {
//        DP12LongestCommonSubstring lcs = new DP12LongestCommonSubstring();
//        String x = "abefcgs";
//        String y = "pdabefnd";
//        int m = x.length();
//        int n = y.length();
//
//        System.out.println("Longest Common Substring Length (Recursive): " +
//                lcs.longestCommonSubstringRecursiveBase(x, y, m, n));
//        System.out.println("Longest Common Substring Length (Memoization): " +
//                lcs.longestCommonSubstringMemoizationBase(x, y, m, n));
//        System.out.println("Longest Common Substring Length (Tabulation): " +
//                lcs.longestCommonSubstringTabulation(x, y, m, n));
//        System.out.println("Longest Common Substring Length (Space Optimized): " +
//                lcs.longestCommonSubstringSpaceOptimized(x, y, m, n));
//        System.out.println("Longest Common Substring Length (Brute Force): " +
//                lcs.longestCommonSubstringBruteForce(x, y));
//    }
}
