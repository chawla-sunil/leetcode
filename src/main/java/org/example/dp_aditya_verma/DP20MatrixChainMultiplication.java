package org.example.dp_aditya_verma;

public class DP20MatrixChainMultiplication {
//    Given an array arr[] which represents the dimensions of a sequence of matrices
//    where the ith matrix has the dimensions (arr[i-1] x arr[i]) for i>=1,
//    find the most efficient way to multiply these matrices together.
//    The efficient way is the one that involves the least number of multiplications.
//
//    Input: arr[] = [2, 1, 3, 4]
//    Output: 20
//    Explanation: There are 3 matrices of dimensions 2 × 1, 1 × 3, and 3 × 4,
//                 Let this 3 input matrices be M1, M2, and M3. There are two ways to multiply:
//                 ((M1 x M2) x M3) and (M1 x (M2 x M3)),
//                 note that the result of (M1 x M2) is a 2 x 3 matrix and result of (M2 x M3) is a 1 x 4 matrix.
//            ((M1 x M2) x M3)  requires (2 x 1 x 3) + (2 x 3 x 4) = 30
//            (M1 x (M2 x M3))  requires (1 x 3 x 4) + (2 x 1 x 4) = 20.
//    The minimum of these two is 20.

    // MY EXPLANATION:
    // we are given an array arr[] of size n which contains the dimensions of matrices
    // we need to find the minimum number of multiplications needed to multiply the matrices
    // which is we need to find minimum cost of matrix multiplication
    // Cost =>   [ - - ]     [ - ]
    //           [ - - ]  X  [ - ]      => We will need to multiply 6 time in this case
    //      (2x3)[ - - ]     [ - ](3x1)
    // Cost = 2*3*1 = 6   =>         2 * (3, 3) * 1 = 6

    // Example:
    // arr = [40, 20, 30, 10, 30] input
    // output  = 26000
    // There will 4 matrics from this array
    // A(40x20), B(20x30), C(30x10), D(10x30)
    // Minimum cost will come when we do the multiplication in this way
    // (A(BC))D => (A X (B X C)) X D
    // First we will do BC => 20*30*10 = 6000
    // Now we have A(20x10) and D(10x30)
    // Now we will do A(BC) => 40*20*10 = 8000
    // Now we have (A(BC))(10x30)
    // Now we will do (A(BC))D => 40*10*30 = 12000
    // Total cost = 6000 + 8000 + 12000 = 26000

    // Matric mulplication is associative but not commutative
    // (A X B) X C = A X (B X C)
    // A X B != B X A

    // Way 1: ✅ we will use this in our problem
    //  i=1                     => i is starting index of matrix
    //  j=4                     => j is ending index of matrix
    //  [40, 20, 30, 10, 30]  => A(40x20), B(20x30), C(30x10), D(10x30)
    //       ↑(j)        ↑(j)  => matrix dimension => arr[i-1] x arr[i]
    //       ↑(k) => k = i to j-1
    //  [ - - ]     [ - - - ]    => splitting the matrix multiplication at k
    //  [i → k,  k+1 → j]       => splitting the matrix multiplication at k
    //  [i to k, k+1 to j]      => two parts of matrix multiplication
    //  k = i to j-1 (range of k)

    //  [40, 20, 30, 10, 30]  => A(40x20), B(20x30), C(30x10), D(10x30)
    //       ↑(j)        ↑(j)
    //               ↑(k)
    //  [-   -   -   -], [-]    => splitting the matrix multiplication at k
    //  [i → k,  k+1 → j]       => splitting the matrix multiplication at k
    //  [i to k, k+1 to j]      => two parts of matrix multiplication
    //  k = i to j-1 (range of k)

    // Way 2:
    // [40, 20, 30, 10, 30]
    //     ↑(j)         ↑(j)
    //          ↑(k) => k = i+1 to j
    // [ - - ],  [ - - - ]      => splitting the matrix multiplication at
    // [i→k-1,    k → j]        => splitting the matrix multiplication at k
    // [i to k-1, k to j]      => two parts of matrix multiplication
    // k = i+1 to j (range of k)

    // [40, 20, 30, 10, 30]
    //     ↑(j)        ↑(j)
    //                 ↑(k)
    // [ - - - - ],  [ - ]      => splitting the matrix multiplication at k
    // [i→k-1,    k → j]        => splitting the matrix multiplication at k
    // [i to k-1, k to j]       => two parts of matrix multiplication
    // k = i+1 to j (range of k)

    // So we have 2 way
    // 1. k = i to j-1     => 2 matrics =>  [i to k] and [k+1 to j]  => [i→k , k+1→j]
    // 2. k = i+1 to j     => 2 matrics =>  [i to k-1] and [k to j]  => [i→k-1 , k→j]
    // Both are same just different way of looking at the problem
    // We are gonna use first way where range of k = i to j-1

    // Now code explanation of how to compute the cost:
    //                                [40, 20, 30, | 10, 30]
    //                                     ↑(j)    |     ↑(j)
    //                                         ↑(k)|
    // for i to k A(40x20), B(20x30)               | for k+1 to j C(30x10), D(10x30)
    //                \ ↑-----↑ /                  |                  \ ↑-----↑ /
    //                 \   ↓   /                   |                   \   ↓   /
    // cost =           40*20*30 = 24000           |                   30*10*30 = 9000
    // We will get new matrix from i to k          | We will get new matrix from k+1 to j
    //                                    (40x30)  |  (30x30)
    //                                          40*30*30 = 36000
    //                                      /      ↓      \
    //                                 arr[i-1] * arr[k] * arr[j]
    //     Total Cost = cost(i to k) + cost(k+1 to j) + cost of multiplying two new matrix
    //                = 24000 + 9000 + 36000 = 69000
    //

    // please write the explanation for the recursive code here
    // The recursive approach tries all possible places to split the product chain
    // and calculates the cost of multiplying the matrices in those splits.
    // It returns the minimum cost among all possible splits.


    // (1) Recursive approach Base function
    public int matrixChainOrderRecursiveBase(int[] arr) {
        int n = arr.length;
        return matrixChainOrderRecursive(arr, 1, n - 1);
    }
    // (1) Recursive approach (Exponential time complexity)
    public int matrixChainOrderRecursive(int[] arr, int i, int j) {
        if (i >= j) {
            return 0;
        }

        int minCost = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int cost = matrixChainOrderRecursive(arr, i, k)
                    + matrixChainOrderRecursive(arr, k + 1, j)
                    + arr[i - 1] * arr[k] * arr[j];

            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }

    // (2) Memoization approach
    public int mcm(int[] arr) {
        int n = arr.length;
        Integer[][] dp = new Integer[n][n];
        return mcmMemoization(arr, 1, n-1, dp);
    }
    public int mcmMemoization(int[] arr, int i, int j, Integer[][] dp) {
        if (i >= j) {
            return 0;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        int minCost = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int tempCost = mcmMemoization(arr, i, k, dp)
                    + mcmMemoization(arr, k + 1, j, dp)
                    + arr[i - 1] * arr[k] * arr[j];

            minCost = Math.min(minCost, tempCost);
        }

        dp[i][j] = minCost;
        return dp[i][j];
    }

    // Never use this in interviews, use recursion and memoization approach
    // (3) Tabulation approach (Bottom-up DP)
    public int matrixChainOrderTabulation(int[] arr, int n) {
        int[][] dp = new int[n][n];

        for (int len = 2; len < n; len++) {
            for (int i = 1; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k <= j - 1; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[1][n - 1];
    }

}
