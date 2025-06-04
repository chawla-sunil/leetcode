package org.example.striver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S16LC118PascalsTriangle {
//    Given an integer numRows, return the first numRows of Pascal's triangle.
//
//    In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

//    Example 1:
//    Input: numRows = 5
//    Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
//
//    Example 2:
//    Input: numRows = 1
//    Output: [[1]]

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(List.of(1)); // Arrays.asList(1));

        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = res.get(i-1);
            List<Integer> currRow = new ArrayList<>();

            currRow.add(1);

            for (int j = 1; j < i; j++) {
                currRow.add(prevRow.get(j-1) + prevRow.get(j));
            }

            currRow.add(1);
            res.add(currRow);
        }
        return res;
    }
}
