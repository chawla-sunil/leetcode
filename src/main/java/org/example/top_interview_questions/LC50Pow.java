package org.example.top_interview_questions;

public class LC50Pow {
//    Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
//
//    Example 1:
//    Input: x = 2.00000, n = 10
//    Output: 1024.00000
//
//    Example 2:
//    Input: x = 2.10000, n = 3
//    Output: 9.26100
//
//    Example 3:
//    Input: x = 2.00000, n = -2
//    Output: 0.25000
//    Explanation: 2-2 = 1/22 = 1/4 = 0.25
//
//
//    Constraints:
//            -100.0 < x < 100.0
//            -2^31 <= n <= (2^31)-1
//            n is an integer.
//            Either x is not zero or n > 0.
//            -10^4 <= xn <= 10^4

    public double myPow(double x, int n) {
        // Approach is simple, using the Binary Exponential (Basically dividing the power n by 2)
        // Example:
        // 2^10 = (2*2)^5 = 4^5 => n = even
        // 4^5 = (4) * (4^4) => n = odd
        // 4^4 = (4*4)^2 = 16^2 => n = even
        // 16^2 = (16*16)^1 = 256^1 => n =even
        // 256^1 = (256) * (256^0) => n = odd
        // we do this until n > 0

        double ans = 1;
        long nn = n;
        if (n < 0) { nn = -nn; }
// if we do not use nn as long, then it will fail at this case
// n = -2147483648 = Integer.MAX_VALUE, and we know int stores value between -2^31 to 2^31 - 1 
// so it will have integer overflow problem.   
        while(nn > 0) {
            if (nn % 2 == 1) {
                ans = ans * x;
                nn = nn - 1;
            } else {
                x = x * x;
                nn = nn / 2;
            }
        }
        if (n < 0) {
            ans = (double) (1.0 / ans);
        }
        return ans;
    }
}
