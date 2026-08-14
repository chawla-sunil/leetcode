package org.example.companies;

public class Zluri {
    // 14 August 2026
//    Design an Excel-Like Table with Formula Support
//    Problem Statement
//    You are required to design a custom data table that mimics basic Excel functionality, including setting values and evaluating simple formulas.
//    Requirements
//    Data Structure
//
//    A 26 × 26 grid (2D array), where each cell can store either a numeric value or a formula.
//    Inputs
//
//    A sequence of operations to perform on the grid.
//    Allowed Operations:
//    setValue(row, col, value)
//    Sets the value at the given (row, col) index.
//    value can be:
//
//    A string representing a number, e.g. '5'
//    A formula of the form SUM(r1, c1, r2, c2), which computes the sum of the values in the two coordinates (r1, c1) and (r2, c2) dynamically.
//    printData()
//    Outputs the current state of the sheet as a 26 × 26 grid.
//            Example
//    setValue(0, 0, '2');
//    setValue(0, 1, '5');
//    setValue(1, 2, 'SUM(0, 0, 1, 1)');
//    printData();
//    setValue(2, 3, 'SUM(0, 1, 1, 2)');
//    setValue(0, 0, '3');
//    printData();
//    Expected Behaviour
//    The formula values should be evaluated dynamically.
//            Initially:
//            (1,2) = (0,0) + (1,1)
//            = 2 + 5
//            = 7
//    After updating (0,0) to 3:
//            (1,2) = 3 + 5
//            = 8
//            (2,3) = (0,1) + (1,2)
//            = 5 + 8
//            = 13
//    printData() should output the current values of all 26 × 26 cells.

    private int size = 26;
    private String[][] cells = new String[size][size];

    private void setValue(int row, int col, String value) {
        cells[row][col] = value;
    }

    private void printData() {
        Integer[][] dp = new Integer[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(evaluate(i, j, dp) + " ");
            }
            System.out.println();
        }
    }

    private int evaluate(int row, int col, Integer[][] dp) {
        if (dp[row][col] != null) {
            return dp[row][col];
        }
        String value = cells[row][col];
        if (value == null) {
            dp[row][col] = 0;
            return 0;
        }

        if (!value.startsWith("SUM")) {
            dp[row][col] = Integer.parseInt(value);
            return dp[row][col];
        }

        String[] parts = value.substring(4, value.length() - 1).split(",");
        int r1 = Integer.parseInt(parts[0]);
        int c1 = Integer.parseInt(parts[1]);
        int r2 = Integer.parseInt(parts[2]);
        int c2 = Integer.parseInt(parts[3]);
        int sum = evaluate(r1, c1, dp) + evaluate(r2, c2, dp);
        dp[row][col] = sum;
        return sum;
    }
}
