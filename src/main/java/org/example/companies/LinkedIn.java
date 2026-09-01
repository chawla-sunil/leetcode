package org.example.companies;

import java.util.List;

public class LinkedIn {

    // People are sitting in a theater row and you're an usher (you tell people where to sit).
//
// Given a row of seats which are either occupied (1) or unoccupied (0) and the condition
// that new people being seated do not like to sit next to anyone else, implement the
// function that determines whether you can or cannot seat a number of people in the row.
//
//
// Input: [1,0,1,0,0,0,1,0,0]
// Can we seat # of people? -> Expected Result
//
//    3 => true
//    4 => false
//
// Input: [1,0,0,1,0,0,1,0,0]
//
//    1 => true
//    2 => false
//
// Input: [0]
//
//    1 => true

// [1,0,0,1,0,1,0,1,0,1]
// [1,0,1,0,1,0,0,1,0,1]


//            0 => continue, l = true;
//            1 = r = true, l false;
//            2 = l false, r true, l true seated = 1
//            3 = r false, l false
//            4 = r true, seated = 2, l true
//            5 = l false,
//            6 = continue, l true
//            7, l true, => l false
//            8 = l false r true seated 3


    public boolean solve1(int[] seats, int people) {
        if (people <= 0) {
            return true;
        }
        if (seats == null || seats.length == 0) {
            return false;
        }

        int seated = 0;
        boolean prevStatus = false;

        for(int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                prevStatus = true;
                continue;
            }

            boolean rightStatus = i == seats.length - 1 ||  seats[i + 1] == 0;

            if (!prevStatus && rightStatus) {
                seated++;
                prevStatus = true;

                if (seated >= people) {
                    return true;
                }
            } else {
                prevStatus = false;
            }
        }

        return false;
    }







    /**
     * Given a nested list of integers, returns the sum of all integers in the list weighted by their reversed level #.
     * For example, given the list {{1,1},2,{1,1}} the deepest level is 2. Thus the function should return 8 (four 1's with weight 1, one 2 with weight 2)
     * Given the list {1,{4,{6,2}}} the function should return 19 (1 with weight 3, 4 with weight 2, 6 with weight 1, 2 with weight 1)
     *
     * It is the "reverse depth" of the item in the list: eg for the above item { 1, {4, { 6, 2 } } }
     *
     *  1 (reverse-depth 3) . = 1 * 3 = 3
     *   \
     *   { 4 } (reverse-depth 2) = 4 * 2 = 8
     *      \
     *      { 6, 2 } (reverse-depth 1) = 6 * 1 + 2 * 1 = 8
     *
     *									= 3 + 8 + 8 = 19
     */




    public int reverseDepthSum (List<NestedInteger> input)
    {
        // implementation here
        int maxDepth = getDepth(input, 1);
        return solve2(input, 1, maxDepth);
    }

    public int solve2(List<NestedInteger> input, int depth, int maxDepth) {
        int sum = 0;

        for (NestedInteger item: input) {
            if (item.isInteger()) {
                int reverseDepth = maxDepth - depth + 1;
                sum += reverseDepth * item.getInteger();
            } else {
                sum += solve2(item.getList(), depth + 1, maxDepth);
            }
        }

        return sum;
    }

    public int getDepth(List<NestedInteger> input, int depth) {
        int maxDepth = depth;

        for (NestedInteger item: input) {
            if (!item.isInteger()) {
                maxDepth = Math.max(maxDepth, getDepth(item.getList(), depth + 1));
            }
        }

        return maxDepth;
    }
    /**
     * This is the interface that represents nested lists.
     * You should not implement it, or speculate about its implementation.
     */
    public interface NestedInteger
    {
        /** @return true if this NestedInteger holds a single integer, rather than a nested list */
        boolean isInteger();
        /** @return the single integer that this NestedInteger holds, if it holds a single integer
         * Return null if this NestedInteger holds a nested list */
        Integer getInteger();
        /** @return the nested list that this NestedInteger holds, if it holds a nested list
         * Return null if this NestedInteger holds a single integer */
        List<NestedInteger> getList();
    }

}
