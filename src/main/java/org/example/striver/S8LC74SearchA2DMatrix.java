package org.example.striver;

public class S8LC74SearchA2DMatrix {
//    You are given an m x n integer matrix matrix with the following two properties:
//    Each row is sorted in non-decreasing order.
//    The first integer of each row is greater than the last integer of the previous row.
//    Given an integer target, return true if target is in matrix or false otherwise.
//    You must write a solution in O(log(m * n)) time complexity.

//    Example 1:
//    Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//    Output: true
//
//    Example 2:
//    Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//    Output: false

    public boolean searchMatrix(int[][] matrix, int target) {
        // https://leetcode.com/problems/search-a-2d-matrix/solutions/1895837/c-binary-search-tree-explained-with-img
        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0;
        int col = n-1;

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
