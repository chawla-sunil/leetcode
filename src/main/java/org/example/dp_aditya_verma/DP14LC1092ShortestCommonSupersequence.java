package org.example.dp_aditya_verma;

public class DP14LC1092ShortestCommonSupersequence {
//    Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
//    If there are multiple valid strings, return any of them.
//
//    A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.
//
//
//
//    Example 1:
//    Input: str1 = "abac", str2 = "cab"
//    Output: "cabac"
//    Explanation:
//    str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
//    str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
//    The answer provided is the shortest such string that satisfies these properties.
//
//    Example 2:
//    Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
//    Output: "aaaaaaaa"
//
//
//    Constraints:
//
//            1 <= str1.length, str2.length <= 1000
//            str1 and str2 consist of lowercase English letters.

    public String shortestCommonSupersequence(String str1, String str2) {
        // This is just an extension problem of LCS(Longest Common Subsequence)
        // From Aditya verma DP series
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lcs = dp[m][n];

        // length of the Shortest Common SuperSequence = m + n - lcs

        int i = m;
        int j = n;
        StringBuilder res = new StringBuilder();
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                res.insert(0, str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                res.insert(0, str1.charAt(i - 1));
                i--;
            } else {
                res.insert(0, str2.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            res.insert(0, str1.charAt(i - 1));
            i--;
        }

        while (j > 0) {
            res.insert(0, str2.charAt(j - 1));
            j--;
        }

        return res.toString();
    }

    // This is not a good approach to use in interviews but just for the sake of variety
    // It is extension of DP13PrintingLongestCommonSubsequence.java which is already implemented
    // which is printing longest common subsequence(LCS)
    public String shortestCommonSupersequence2(String str1, String str2) {
        DP13PrintingLongestCommonSubsequence lcsCalculator = new DP13PrintingLongestCommonSubsequence();
        String lcs = lcsCalculator.printLongestCommonSubsequence(str1, str2, str1.length(), str2.length());

        StringBuilder scs = new StringBuilder();

        int i = 0, j = 0;
        for (char c : lcs.toCharArray()) {
            while (i < str1.length() && str1.charAt(i) != c) {
                scs.append(str1.charAt(i));
                i++;
            }
            while (j < str2.length() && str2.charAt(j) != c) {
                scs.append(str2.charAt(j));
                j++;
            }
            scs.append(c);
            i++;
            j++;
        }

        // Append remaining characters of str1 and str2
        scs.append(str1.substring(i));
        scs.append(str2.substring(j));

        return scs.toString();
    }

//    public static void main(String[] args) {
//        DP14LC1092ShortestCommonSupersequence scsCalculator = new DP14LC1092ShortestCommonSupersequence();
//        String str1 = "abac";
//        String str2 = "cab";
//        System.out.println("Shortest Common Supersequence is: " + scsCalculator.shortestCommonSupersequence(str1, str2));
//    }
}
