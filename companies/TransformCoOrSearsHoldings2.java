

public class TransformCoOrSearsHoldings2 {
    // [1,2,3]
    // [4,5,6]
    // [7,8,9]

    // 123,   6,9 ,  69,   8,7  87    4    4  5
    //        5,8          5,4        5
    //        4,7

    public static void main(String[] args) {
        int[][] arr = {{1,2,3, 10},
                       {4,5,6, 11},
                       {7,8,9, 12}};
//        int[][] arr1 = {{1}, {2}, {3}};
        spiral(arr); // iterative
        spiralRecursion(arr); // recursive
    }

    public static void spiral(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int dir = 0; //-> 1=, 2 =<, 3=^

        int top = 0, bottom = m-1;
        int left = 0, right = n-1;

        while (top <= bottom && left <= right) {
            if (dir == 0) {
                for (int i = left; i <= right; i++) {
                    System.out.println(matrix[top][i]);
                }
                top++;
            } else if (dir == 1) {
                for (int i = top; i <= bottom; i++) {
                    System.out.println(matrix[i][right]);
                }
                right--;
            } else if (dir == 2) {
                for (int i = right; i >= left; i--) {
                    System.out.println(matrix[bottom][i]);
                }
                bottom--;
            } else if (dir == 3) {
                for (int i = bottom; i >= top; i--) {
                    System.out.println(matrix[i][left]);
                }
                left++;
            }
            dir = (dir + 1)%4;
        }
    }

    // jitesh.agarwal@transformco.com
    public int[][] rotation(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;

        int[][] newRotatedArray = new int[n][m];

        for (int i = 0; i < n; i++) {
            newRotatedArray[i] = new int[m];
            for (int j = 0; j < m; j++) {
                newRotatedArray[i][j] = arr[j][m-i-1];
            }
        }
        return newRotatedArray;
    }


    public static void spiralRecursion(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        spiralHelper(matrix);
    }

    private static void spiralHelper(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }

        for (int num : matrix[0]) {
            System.out.println(num);
        }

        // removing 1st row
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int[][] subMatrix = new int[numRows - 1][numCols];
        for (int i = 1; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                subMatrix[i - 1][j] = matrix[i][j];
            }
        }

        // Rotating
        int newNumRows = numCols;
        int newNumCols = numRows - 1;
        int[][] rotatedMatrix = new int[newNumRows][newNumCols];
        for (int i = 0; i < newNumRows; i++) {
            for (int j = 0; j < newNumCols; j++) {
                rotatedMatrix[i][j] = subMatrix[j][newNumRows - 1 - i];
            }
        }
        spiralHelper(rotatedMatrix);
    }
}
