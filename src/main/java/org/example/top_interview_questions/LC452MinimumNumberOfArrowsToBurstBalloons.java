package org.example.top_interview_questions;

import java.util.Arrays;

public class LC452MinimumNumberOfArrowsToBurstBalloons {
//    There are some spherical balloons taped onto a flat wall that represents the XY-plane.
//    The balloons are represented as a 2D integer array points where points[i] = [xstart, xend]
//    denotes a balloon whose horizontal diameter stretches between xstart and xend.
//    You do not know the exact y-coordinates of the balloons.
//
//    Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
//    A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend.
//    There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely,
//    bursting any balloons in its path.
//
//    Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
//
//
//
//    Example 1:
//    Input: points = [[10,16],[2,8],[1,6],[7,12]]
//    Output: 2
//    Explanation: The balloons can be burst by 2 arrows:
//            - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
//            - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
//
//    Example 2:
//    Input: points = [[1,2],[3,4],[5,6],[7,8]]
//    Output: 4
//    Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
//
//    Example 3:
//    Input: points = [[1,2],[2,3],[3,4],[4,5]]
//    Output: 2
//    Explanation: The balloons can be burst by 2 arrows:
//            - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
//            - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
//
//
//    Constraints:
//
//            1 <= points.length <= 105
//    points[i].length == 2
//            -231 <= xstart < xend <= 231 - 1

    // Solution link:
    // https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/solutions/93703/share-my-explained-greedy-solution-by-jo-hddz
    // https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/solutions/1686627/cjavapython-6-lines-sort-and-greedy-imag-gdd3
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        // We are not doing this below because we will get integer overflow problem for
        // this example: [[-2147483646,-2147483645],[2147483646,2147483647]]
        // Arrays.sort(points, (a, b) -> a[1] - b[1]);

        int arrowPosition = points[0][1];
        int count = 1;

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= arrowPosition) {
                continue;
            }
            count++;
            arrowPosition = points[i][1];
        }

        return count;
    }
}
