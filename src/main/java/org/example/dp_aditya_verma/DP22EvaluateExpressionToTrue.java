package org.example.dp_aditya_verma;

public class DP22EvaluateExpressionToTrue {
    // Question line: https://www.interviewbit.com/problems/evaluate-expression-to-true/
//    Given an expression, A, with operands and operators (OR , AND , XOR),
//    in how many ways can you evaluate the expression to true, by grouping in different ways?
//
//    Operands are only true and false.
//
//    Return the number of ways to evaluate the expression modulo 10^3 + 3. (10^3 + 3 = 1003)
//
//
//
//    Input Format:
//
//    The first and the only argument of input will contain a string, A.
//
//    The string A, may contain these characters:
//    '|' will represent or operator
//    '&' will represent and operator
//    '^' will represent xor operator
//    'T' will represent true operand
//    'F' will false
//    Output:
//
//    Return an integer, representing the number of ways to evaluate the string.
//            Constraints:
//
//            1 <= length(A) <= 150
//
//    Example:
//    Input 1:
//    A = "T|F"
//    Output 1: 1
//    Explanation 1:
//    The only way to evaluate the expression is:
//            => (T|F) = T
//
//    Input 2:
//    A = "T^T^F"
//    Output 2:
//            0
//    Explanation 2:
//    There is no way to evaluate A to a true statement.


    // Explanation:
    //       T    ^   F   &   T              (i = 0,  j = n-1)
    //       ↑(i) ↑(k)        ↑(j)           (k will always be an operator index)
    //      ( - )     ( -     - )
    //   (i to k-1)   (k+1 to j)    (now these are 2 sub expressions/subproblems)

    // left sub-part => i to k-1,    right sub-part = k+1 to j
    // k will go from i+1 to j-1 (all-inclusive everywhere)

    // So we will need to find how many ways left sub-part can be true and false
    // isTrue = 1 means we want to evaluate the expression to true
    // isTrue = 0 means we want to evaluate the expression to false
    // So that we can do the calculations based on the operator

    // We will use recursion with memoization to solve this problem.
    // We will divide the expression into two parts at each operator and
    // calculate the number of ways to evaluate each part to true and false.
    // Then we will combine the results based on the operator to get the total number of ways
    // to evaluate the expression to true or false.


    // (1) Recursive Approach Base function
    public int countWaysRecursiveBase(String s) {
        int n = s.length();
        return countWaysRecursive(s, 1, n-1, 1);
    }

    // (1) Recursive Approach
    public int countWaysRecursive(String s, int i, int j, int isTrue) {
        if (i > 0) {
            return 0;
        }
        if (i == j) {
            if (isTrue == 1) {
                return s.charAt(i) == 'T' ? 1 : 0;
            } else {
                return s.charAt(i) == 'F' ? 1 : 0;
            }
        }

        int ans = 0;

        for (int k = i+1; k <= j-1; k += 2) {
            char operator  = s.charAt(k);
            int lt = countWaysRecursive(s, i, k-1, 1); // leftTrue
            int lf = countWaysRecursive(s, i, k-1, 0); // leftFalse
            int rt = countWaysRecursive(s, k+1, j, 1); // rightTrue
            int rf = countWaysRecursive(s, k+1, j, 0); // rightFalse

            if (operator == '&') {
                if (isTrue == 1) {
                    ans += lt * rt;
                } else {
                    ans += lt*rf + lf*rt + lf*rf;
                }
            } else if (operator == '|') {
                if (isTrue == 1) {
                    ans += lt*rt + lt*rf + lf*rt;
                } else {
                    ans += lf * rf;
                }
            } else if (operator == '^') {
                if (isTrue == 1) {
                    ans += lf*rt + lt*rf;
                } else {
                    ans += lt*rt + lf*rf;
                }
            }

        }

        return ans;
    }

    // (2) Memoization Approach Base function
    public int countWays(String s) {
        int n = s.length();
        Integer[][][] dp = new Integer[n][n][2]; // 0 -> false, 1 -> true
        return countWaysMemoization(s, 0, n - 1, 1, dp);
    }

    // (2) Memoization Approach
    public int countWaysMemoization(String s, int i, int j, int isTrue, Integer[][][] dp) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            if (isTrue == 1) {
                return s.charAt(i) == 'T' ? 1 : 0;
            } else {
                return s.charAt(i) == 'F' ? 1 : 0;
            }
        }

        if (dp[i][j][isTrue] != null) {
            return dp[i][j][isTrue];
        }

        int ways = 0;
        for (int k = i + 1; k <= j - 1; k += 2) {
            char operator = s.charAt(k);

            int leftTrue = countWaysMemoization(s, i, k - 1, 1, dp);
            int leftFalse = countWaysMemoization(s, i, k - 1, 0, dp);
            int rightTrue = countWaysMemoization(s, k + 1, j, 1, dp);
            int rightFalse = countWaysMemoization(s, k + 1, j, 0, dp);

            if (operator == '&') {
                if (isTrue == 1) {
                    ways += leftTrue * rightTrue;
                } else {
                    ways += leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
                }
            } else if (operator == '|') {
                if (isTrue == 1) {
                    ways += leftTrue * rightTrue + leftTrue * rightFalse + leftFalse * rightTrue;
                } else {
                    ways += leftFalse * rightFalse;
                }
            } else if (operator == '^') {
                if (isTrue == 1) {
                    ways += leftTrue * rightFalse + leftFalse * rightTrue;
                } else {
                    ways += leftTrue * rightTrue + leftFalse * rightFalse;
                }
            }
        }

        dp[i][j][isTrue] = ways % 1003;
        // we can do ways % 1003 in every step when we are calculating ways above if the values integer overflows
        return dp[i][j][isTrue];
    }
}
