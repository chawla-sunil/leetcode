package org.example.two_pointers;

public class LC633SumOfSquareNumbers {
//    Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
//
//
//    Example 1:
//    Input: c = 5
//    Output: true
//    Explanation: 1 * 1 + 2 * 2 = 5
//
//    Example 2:
//    Input: c = 3
//    Output: false
//
//
//    Constraints:
//
//            0 <= c <= 231 - 1

    public boolean judgeSquareSum(int c) {
        long left = 0;
        long right = (long) Math.sqrt(c);

        while(left <= right) {
            long square = left * left + right * right;

            if (square > c) {
                right--;
            } else if (square < c) {
                left++;
            } else {
                return true;
            }
        }
        return false;
    }
}
