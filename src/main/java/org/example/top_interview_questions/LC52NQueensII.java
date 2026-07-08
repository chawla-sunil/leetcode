package org.example.top_interview_questions;

public class LC52NQueensII {
//    The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
//
//    Given an integer n, return the number of distinct solutions to the n-queens puzzle.
//
//
//
//    Example 1:
//    Input: n = 4
//    Output: 2
//    Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
//
//
//    Example 2:
//    Input: n = 1
//    Output: 1
//
//
//    Constraints:
//
//            1 <= n <= 9


    // Good Back tracking solutions (This problem is not covered in these solutions but overall good)=>
    // https://leetcode.com/problems/combination-sum/solutions/16502/a-general-approach-to-backtracking-quest-dexx

    int count = 0;
    public int totalNQueens2(int n) {
        char[][] board = new char[n][n];
        // initially filled with '.'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        backTracking(board, 0);
        return count;
    }

    public void backTracking(char[][] board, int row) {
        if (row == board.length) {
            count++;
            return;
        }

        for (int col = 0; col < board[0].length; col++) {//col< n => col < board.length => n = board.length = board[0].length
            if (isSafeToPlaceQueen(board, row, col)) {
                board[row][col] = 'Q';
                backTracking(board, row + 1);
                board[row][col] = '.';
            }
        }
    }

    public boolean isSafeToPlaceQueen(char[][] board, int row, int col) {
        // check upper vertical column
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // check upper left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < board[0].length; i--, j++) {
            if (board[i][j] ==  'Q') {
                return false;
            }
        }

        return true;
    }





    // Solution 2 => kind of same solution.
    // Solution 1 is easy understand but Solution 2 is a little optimized.
    int count2 = 0;
    boolean[] cols;
    boolean[] diag1; //  \ diagonal direction (top-left to bottom-right)
    boolean[] diag2; //  / diagonal direction (top-right to bottom-left)
    public int totalNQueens(int n) {
        cols = new boolean[n];
        diag1 = new boolean[2 * n];
        diag2 = new boolean[2 * n];

        backtrack(0, n);

        return count2;
    }

    private void backtrack(int row, int n) {
        if (row == n) {
            count2++;
            return;
        }

        for (int col = 0; col < n; col++) {
            // Explanation for d1 and d2 variable =>

            // 1.1 => \ diagonals → row - col is constant
            // Take some coordinates and compute row - col:
            // (0,0): 0 - 0 = 0
            // (1,1): 1 - 1 = 0
            // (2,2): 2 - 2 = 0
            // (3,3): 3 - 3 = 0
            // These all lie on the same \ diagonal from top-left to bottom-right.
            // Another example:
            // (0,1): 0 - 1 = -1
            // (1,2): 1 - 2 = -1
            // (2,3): 2 - 3 = -1
            // All cells on the same \ diagonal have the same value of row - col.

            // The problem: row - col can be negative
            // If row and col are from 0 to n-1, then row - col ranges from -(n-1) to +(n-1).
            // The trick is:
            // int d1 = row - col + n;


            // 1.2 =>  / diagonals → row + col is constant
            // Now take some coordinates and compute row + col:
            // (0,3): 0 + 3 = 3
            // (1,2): 1 + 2 = 3
            // (2,1): 2 + 1 = 3
            // (3,0): 3 + 0 = 3
            // They are all on the same / diagonal from top-right to bottom-left.

            // So:
            // All cells on the same / diagonal have the same value of row + col.
            // This is exactly what the code does:
            // int d2 = row + col;
            // Here row + col ranges from 0 (when both are 0) to 2*(n-1) (when both are n-1), so the range is [0, 2n - 2]. Again, we just allocate:
            // diag2 = new boolean[2 * n];

            // 1.3 => cols array ensures one queen per column using cols[col].
            int d1 = row - col + n;
            int d2 = row + col;

            if (cols[col] || diag1[d1] || diag2[d2])
                continue;

            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            backtrack(row + 1, n);

            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}
