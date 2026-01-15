package org.example.top_interview_questions;

import java.util.Arrays;

public class LC135Candy {
//    There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
//
//    You are giving candies to these children subjected to the following requirements:
//
//    Each child must have at least one candy.
//    Children with a higher rating get more candies than their neighbors.
//    Return the minimum number of candies you need to have to distribute the candies to the children.
//
//
//    Example 1:
//    Input: ratings = [1,0,2]
//    Output: 5
//    Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
//
//    Example 2:
//    Input: ratings = [1,2,2]
//    Output: 4
//    Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
//    The third child gets 1 candy because it satisfies the above two conditions.
//
//
//    Constraints:
//
//    n == ratings.length
//1 <= n <= 2 * 104
//            0 <= ratings[i] <= 2 * 104

    // link: https://leetcode.com/problems/candy/solutions/2234434/c-on-time-o1-space-full-explanation-by-t-sud6
    // This is O(n) time and uses O(1) space
    // In this solution, we traverse from left to right and can consider it as peak and velley problem as showen in the DIAGRAM
    // We maintain peak and valley to add how much extra we need to add for higher rating students
    // He we are first looking for equal ratings and then higher rating and then lower rating
    // So at peak, we are adding peak and add valley we are adding valley also, so we will need to decrease the
    // one which is minimum because higher rating student should get higher rating

    // DIAGRAM EXAMPLE =>
    // Let take the Rating as : [1,3,6,8,9,5,3,6,8,5,4,2,2,3,7,7,9,8,6,6,6,4,2]
    // Each child represented as rating(candy he is given)
    // Peak = max(peak, valley)

    // See when peak is encountered we take max of the peak calculated from left and valley calculated from right.
    // When we get any equal element it gets reset to 1 candy or if it is peak we take max(0, right valley)

    //            (5)         (4)                         (3)
    //             9           8                           9
    //            /|\         /|\                         /|\
    //           / | \       / | \                (3)    / | \
    //       (4)8  |  5(2)  6  |  5(3)             7 __ 7  |  8(2)
    //         /   |   \   (2) |   \              /|   (1) |   \
    //        /    |    \ /    |    \            / |    |  |    \         (3)
    //    (3)6     |     3     |     4(2)       3(2)    |  |     6 __ 6 __ 6    -> Total candy = 50
    //      /      |    (1)    |      \        /   | Reset |    (1)  (1)   |\
    //     /       |           |       \      /    |  to 1 |          |    | \
    // (2)3        |           |        2 __ 2     |       |          |    |  4(2)
    //   /         |           |       (1)  (1)    |       |        Reset  |   \
    //  /          |           |                   |       |         to 1  |    \
    // 1(1)        |           |                   |       |               |     2(1)
    //    Peak= max(5,3)  Peak= max(3,4)    Peak= max(3,0) |         Peak= max(0,3)
    //                                                Peak= max(2,3)
    public int candy(int[] ratings) {
        int n = ratings.length;
        int candies = n;
        int i = 1; // we will start traversing form the 1, so we can compare its rating from its last neight index 0;

        while (i < n) {
            if (ratings[i] == ratings[i-1]) {
                i++;
                continue;
            }

            // For increasing slope
            int peak = 0;
            while (i<n && ratings[i] > ratings[i-1]) {
                peak += 1;
                candies += peak;
                i++;
            }

            // For decreasing slope
            int valley = 0;
            while(i<n && ratings[i] < ratings[i-1]) {
                valley += 1;
                candies += valley;
                i++;
            }

            candies -= Math.min(peak, valley);
            // Keep only the higher peak
            // here we are added both peak and valley into the maximus point but we only need to add the maximum one
            // and substract the minimun one.
        }

        return candies;
    }

    // link: https://leetcode.com/problems/candy/solutions/6802500/double-pass-greedy-with-images-example-w-7vuj
    // This is simple and easy understandable solution
    // In this solution, we go from left to right and increase the candies by 1 if the next ranking is higher than previous
    // then we go from right to left, and increase candies by 1 if we see higher ranking but there is catch which is
    // when we see a peak, we need to choose the maximus of its previous alloted candies(from left to right loop)
    // and new candies allocation ( by  +1 from right to left loop)
    // This is O(n) time and uses O(n) space
    public int candy2(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        // initially allot 1 candy to each student
        Arrays.fill(candies, 1); // this means => for (int i = 0; i < n; i++) { candies[i] = 1; }

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1] + 1;
            }
        }

        for (int i = n-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                candies[i] = Math.max(candies[i], candies[i+1] + 1);
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res += candies[i];
        }

        return res;
    }
}
