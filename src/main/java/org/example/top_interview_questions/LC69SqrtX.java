package org.example.top_interview_questions;

public class LC69SqrtX {
//    Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
//    The returned integer should be non-negative as well.
//
//    You must not use any built-in exponent function or operator.
//
//    For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
//
//    Example 1:
//    Input: x = 4
//    Output: 2
//    Explanation: The square root of 4 is 2, so we return 2.
//
//    Example 2:
//    Input: x = 8
//    Output: 2
//    Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
//
//    Constraints:
//            0 <= x <= 231 - 1


    public int mySqrt(int x) {
        if (x==0) {
            return 0;
        }

        int left = 1, right = x;

        while(left<=right) {
            int mid = left + (right - left) / 2;
            // mid * mid == x gives runtime error OR Interger Overflow problem also
            if (mid == x / mid) {
                return mid;
            }

            if (mid > x / mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }
}
