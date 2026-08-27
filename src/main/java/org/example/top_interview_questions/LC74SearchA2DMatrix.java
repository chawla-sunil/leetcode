package org.example.top_interview_questions;

public class LC74SearchA2DMatrix {
//    You are given an m x n integer matrix matrix with the following two properties:
//
//    Each row is sorted in non-decreasing order.
//    The first integer of each row is greater than the last integer of the previous row.
//    Given an integer target, return true if target is in matrix or false otherwise.
//
//    You must write a solution in O(log(m * n)) time complexity.
//
//
//    Example 1:
//    Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//    Output: true
//
//    Example 2:
//    Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//    Output: false
//
//
//    Constraints:
//
//    m == matrix.length
//    n == matrix[i].length
//    1 <= m, n <= 100
//            -104 <= matrix[i][j], target <= 104


    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / cols;
            int col = mid % cols;
            int curr = matrix[row][col];

            if (curr == target) {
                return true;
            } else if (curr < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    // searchMatrix is better and easier to understand than searchMatrix2
    // but searchMatrix2 is also good and can be easily understood
    public boolean searchMatrix2(int[][] matrix, int target) {
        // https://leetcode.com/problems/search-a-2d-matrix/solutions/1895837/c-binary-search-tree-explained-with-img
        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0;
        int col = n-1;
        // we start from top right corner
        // if target is greater than current cell than we increase row
        // if target is less than current cell than we decrease col
        // so row increases and col decreases. (just like left and right in normarl Binary Search)
        while(row < m && col > -1) {
            int curr = matrix[row][col];
            if (curr == target) {
                return true;
            }
            if (target > curr) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }
}
