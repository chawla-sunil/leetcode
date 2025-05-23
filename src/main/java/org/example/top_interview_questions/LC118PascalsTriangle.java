package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC118PascalsTriangle {
//    Given an integer numRows, return the first numRows of Pascal's triangle.
//
//    In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
//
//    Example 1:
//    Input: numRows = 5
//    Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
//
//    Example 2:
//    Input: numRows = 1
//    Output: [[1]]
//
//    Constraints:
//            1 <= numRows <= 30

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1));
        if (numRows == 1) {
            return list;
        }


        for (int i = 1; i < numRows; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 1; i < numRows; i++) {
            List<Integer> prev = list.get(i-1);
            List<Integer> curr = list.get(i);

            curr.add(1);

            for (int j = 0; j < prev.size() - 1; j++) {
                curr.add(prev.get(j) + prev.get(j+1));
            }

            curr.add(1);
        }
        return list;
    }
}
