package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.List;

public class LC73SetMatrixZeroes {
//    Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
//    You must do it in place.
//
//            Example 1:
//    Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
//    Output: [[1,0,1],[0,0,0],[1,0,1]]
//
//    Example 2:
//    Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//    Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
//
//
//    Constraints:
//
//    m == matrix.length
//    n == matrix[0].length
//            1 <= m, n <= 200
//            -2^31 <= matrix[i][j] <= 2^31 - 1
//
//
//    Follow up:
//
//    A straightforward solution using O(mn) space is probably a bad idea.
//    A simple improvement uses O(m + n) space, but still not the best solution.
//    Could you devise a constant space solution?


    // more optimised solutuon with O(1) space
    // Use the first row and column as a note, reference.
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean first_row_zero = false;
        boolean first_col_zero = false;

        // find if first raw and col has 0 in it in the next 2 loops
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                first_col_zero = true;
                break;

            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                first_row_zero = true;
                break;
            }
        }

        // set first row and col as reference zero
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // use first row and col reference and in-place the zero in the matrix
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // set the first row and col zero
        if (first_row_zero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
        if (first_col_zero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    // easy and understandable solution
    // uses O(n) space
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        for (int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void setZeroes3(int[][] matrix) {
        List<Integer> rows = new ArrayList<>(); //rowsWithZero
        List<Integer> cols = new ArrayList<>(); //colsWithZero

        for (int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int r: rows) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[r][j] = 0;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int c: cols) {
                matrix[i][c] = 0;
            }
        }
    }
}
