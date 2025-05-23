package org.example.top_interview_questions;

public class LC62UniquePaths {
//    There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
//    The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either
//    down or right at any point in time.
//
//    Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the
//    bottom-right corner.
//
//    The test cases are generated so that the answer will be less than or equal to 2 * 10^9.
//
//
//    Example 1:
//    Input: m = 3, n = 7
//    Output: 28
//
//    Example 2:

//    Input: m = 3, n = 2   => start . .
//    Output: 3                      . .
//                                   . . end
//    Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
//            1. Right -> Down -> Down
//            2. Down -> Down -> Right
//            3. Down -> Right -> Down
//
//
//    Constraints:
//            1 <= m, n <= 100


    // using DP

    // The idea behind this approach is to use a 2D Dynamic Programming (DP) array to store the number 
    // of unique paths to each cell. A cell (i,j)(i, j)(i,j) can be reached either 
    // from (i−1,j)(i-1, j)(i−1,j) or (i,j−1)(i, j-1)(i,j−1), and thus the number 
    // of unique paths to (i,j)(i, j)(i,j) is the sum of the number of unique paths to these two cells.
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }



    // 2nd Approach using Math (Combinations(PnC, Permutations and Combination))

    // The number of unique paths can be seen as the number of ways to choose 
    // m−1 downs and n−1 rights, regardless of the order. 
    // In combinatorial terms, this is equivalent to 
    // (m+n−2)C(m-1) = (m+n−2)C(n-1)
    // Here (m+n-2) = (m-1) + (n-1)
    // Formula => nCr =  C (n, r) = n! / [r! (n – r)!] // We will use this formula
    // Formula => nPr =  P (n, r) = n! / (n – r)!

    // Calculate C (m+n-2, m-1)
    // 6C2 = C(6, 2) = (6*5*4*3)/(1*2)
    public int uniquePaths2(int m, int n) {
        long ans = 1;
        for (int i = 1; i <= m - 1; i++) {
            ans = ans * (n - 1 + i) / i;
        }
        return (int)ans;
    }
}
