package org.example.striver.array;

public class S10LC48RotateImage {
//    You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
//
//    You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
//    DO NOT allocate another 2D matrix and do the rotation.
//
//    Example 1:
//    Input: matrix = [[1,2,3],
//                     [4,5,6],
//                     [7,8,9]]

//    Output:          [[7,4,1],
//                      [8,5,2],
//                      [9,6,3]]

//    Example 2:
//    Input: matrix = [[5,1,9,11],
//                     [2,4,8,10],
//                     [13,3,6,7],
//                     [15,14,12,16]]

//    Output:           [[15,13,2,5],
//                       [14,3, 4,1],
//                       [12,6, 8,9],
//                       [16,7,10,11]]

    public void rotate(int[][] matrix) {
        // In this problem we are rotating clockwise,
        // for rotating anticlock wise, approach is written outside the function
        // same code can be used, just change the order of execution.

        // /*
        // * clockwise rotate
        // * first reverse up to down, then swap the symmetry
        // * 1 2 3     7 8 9     7 4 1
        // * 4 5 6  => 4 5 6  => 8 5 2
        // * 7 8 9     1 2 3     9 6 3
        // */
        int low = 0;
        int high = matrix.length - 1;

        // in this part, I am first rotating the array
        while(low < high) {
            int[] temp = matrix[low];
            matrix[low] = matrix[high];
            matrix[high] = temp;
            low++;
            high--;
        }

        // in this part, I am swapping elements diagonally
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }

    // /*
    // * anticlockwise rotate
    // * first swap the symmetry, then reverse up to down
    // * 1 2 3     1 4 7     3 6 9
    // * 4 5 6  => 2 5 8  => 2 5 8
    // * 7 8 9     3 6 9     1 4 7
    // */
}
