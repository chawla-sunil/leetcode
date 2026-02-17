package org.example.top_interview_questions;

import java.util.HashSet;
import java.util.Set;

public class LC202HappyNumber {
//    Write an algorithm to determine if a number n is happy.
//
//    A happy number is a number defined by the following process:
//
//    Starting with any positive integer, replace the number by the sum of the squares of its digits.
//    Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
//    Those numbers for which this process ends in 1 are happy.
//    Return true if n is a happy number, and false if not.
//
//
//
//            Example 1:
//
//    Input: n = 19
//    Output: true
//    Explanation:
//            1^2 + 9^2 = 82
//            8^2 + 2^2 = 68
//            6^2 + 8^2 = 100
//            1^2 + 0^2 + 0^2 = 1
//    Example 2:
//
//    Input: n = 2
//    Output: false
//
//
//    Constraints:
//
//            1 <= n <= 231 - 1


    // For the cases where solution does not exist which means there is gonna be a cycle
    // Reference image to understand
    // https://leetcode.com/problems/happy-number/solutions/3767573/easy-java-solution-two-pointers-floyds-t-ich2
    // We can take example of n = 4, it has cycle
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>(); // visited set

        while (!set.contains(n)) {
            set.add(n);

            n = squareAndGetNextNumber(n);
            if (n == 1) {
                return true;
            }
        }

        return false;
    }

    public int squareAndGetNextNumber(int n) {
        int res = 0;

        while (n > 0) {
            int remainder = n % 10;
            res = res + remainder * remainder;
            n = n / 10;
        }

        return res;
    }
}
