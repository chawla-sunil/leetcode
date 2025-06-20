package org.example.striver.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S21LC56MergeIntervals {
//    Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
//    and return an array of the non-overlapping intervals that cover all the intervals in the input.

//    Example 1:
//    Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//    Output: [[1,6],[8,10],[15,18]]
//    Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
//
//    Example 2:
//    Input: intervals = [[1,4],[4,5]]
//    Output: [[1,5]]
//    Explanation: Intervals [1,4] and [4,5] are considered overlapping.

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        // Arrays.sort(intervals, (a, b) -> a[0] - b[0]); OR we can write this way, both are same.

        List<int[]> merged = new ArrayList<>();
        int[] currBigInterval = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];

            if (currBigInterval[1] >= curr[0]) {
                currBigInterval[1] = Math.max(curr[1], currBigInterval[1]);
            } else {
                merged.add(currBigInterval);
                currBigInterval = curr;
            }
        }
        merged.add(currBigInterval);

        return merged.toArray(new int[merged.size()][2]);
    }
}
