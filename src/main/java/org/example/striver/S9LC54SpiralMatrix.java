package org.example.striver;

import java.util.ArrayList;
import java.util.List;

public class S9LC54SpiralMatrix {
//    Given an m x n matrix, return all elements of the matrix in spiral order.
//
//    Example 1:
//    Input: matrix = [[1,2,3],
//                     [4,5,6],
//                     [7,8,9]]
//    Output: [1,2,3,6,9,8,7,4,5]
//
//    Example 2:
//    Input: matrix = [[1,2, 3, 4],
//                     [5,6, 7, 8],
//                     [9,10,11,12]]
//    Output: [1,2,3,4,8,12,11,10,9,5,6,7]

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        int m = matrix.length; // row
        int n = matrix[0].length; // col

        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;
        // we will an variable dir, which will tell where to
        // dir = 0 means => -> ➡️
        // dir = 1 means =>  ⬇️
        // dir = 2 means => <- ⬅️
        // dir = 3 means => ⬆️
        int dir = 0;
        while(top <= bottom && left <= right) {
            if (dir == 0) {
                for (int i = left; i<= right; i++) {
                    res.add(matrix[top][i]);
                }
                top++;
            } else if (dir == 1) {
                for (int i = top; i <= bottom; i++) {
                    res.add(matrix[i][right]);
                }
                right--;
            } else if (dir == 2) {
                for (int i = right; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            } else if (dir == 3) {
                for (int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }

            dir = (dir + 1) % 4;
        }

        return res;
    }

}
