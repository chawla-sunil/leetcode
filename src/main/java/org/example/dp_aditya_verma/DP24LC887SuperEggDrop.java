package org.example.dp_aditya_verma;

public class DP24LC887SuperEggDrop {
//    You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.
//
//    You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break,
//    and any egg dropped at or below floor f will not break.
//
//    Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks,
//    you can no longer use it. However, if the egg does not break, you may reuse it in future moves.
//
//    Return the minimum number of moves that you need to determine with certainty what the value of f is.
//
//
//    Example 1:
//    Input: k = 1, n = 2
//    Output: 2
//    Explanation:
//    Drop the egg from floor 1. If it breaks, we know that f = 0.
//    Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
//    If it does not break, then we know f = 2.
//    Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
//
//    Example 2:
//    Input: k = 2, n = 6
//    Output: 3
//
//    Example 3:
//    Input: k = 3, n = 14
//    Output: 4
//
//
//    Constraints:
//
//            1 <= k <= 100
//            1 <= n <= 104


    // This is egg dropping problem


    // Just for extra explanation:
    // e = eggs, f = floors
    // We need to drop eggs from floors in such a way that we minimize the worst case
    // For that we will try dropping from each floor k = 1 to f
    // If egg breaks, we have e-1 eggs and k-1 floors to check
    // If egg doesn't break, we have e eggs and f-k floors to check
    // We will take max of both cases because we need to minimize the worst case
    // We will add 1 to max because we did one drop
    // We will take min of all the max values we got from dropping from each floor k

    // Irrelevant =>
    // e = 2, f = 100
    // This formula is only for e = 2
    // drops = ceil((-1 + sqrt(1 + 8*f)) / 2)
    // x + (x-1) + (x-2) + ... + 1 >= f
    // x(x+1)/2 >= f
    // x^2 + x - 2f >= 0
    // x = (-1 + sqrt(1 + 8f)) / 2
    // x = number of drop





    // (3) Memoization Optimized method using binary search
    // We were getting TLE (Time Limit Exceeded) in normal Memoization approach also,
    // So we will modify the k loop, instead of normal linear for loop,
    // we will use binary search for k
    // we split building in 2 parts belowKFloors(left), aboveKFloors(right)
    // if left has higher value that means, we will get highest value from left builing
    // and we can ignore the right value
    // THIS APPROACH WILL WORK WITHOUT MEMOIZATION ALSO
    public int superEggDrop(int e, int f) {
        Integer[][] dp = new Integer[e+1][f+1];
        return superEggDropMemoizationOptimized(e, f, dp);
    }

    public int superEggDropMemoizationOptimized(int e, int f, Integer[][] dp) {
        if (f == 0 || f == 1) { // where there is only 0 or 1 floor
            return f;
        }

        if (e == 1) { // when there is only 1 egg, start dropping from start
            return f;
        }

        if (dp[e][f] != null) {
            return dp[e][f];
        }

        int minDrop = Integer.MAX_VALUE;

        int left = 1;
        int right = f;

        while (left <= right) {
            int mid = left + (right - left) / 2; // mid = k = our normal linear loop variable

            int eggBroke = superEggDropMemoizationOptimized(e-1, mid-1, dp); // from left, down building. If egg breaks go down
            int eggDidNotBreak = superEggDropMemoizationOptimized(e, f-mid, dp); // from right, up building, if egg doesn't break go up
            int tempDrop = 1 + Math.max(eggBroke, eggDidNotBreak);
            minDrop = Math.min(minDrop, tempDrop);

            if (eggBroke < eggDidNotBreak) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        dp[e][f] = minDrop;
        return dp[e][f];
    }

    // (2) Memoization method
    // Getting TLE (Time Limit Exceeded) in this approach also
    public int superEggDrop2(int e, int f) {
        Integer[][] dp = new Integer[e+1][f+1];
        return superEggDropMemoization(e, f, dp);
    }

    public int superEggDropMemoization(int e, int f, Integer[][] dp) {
        if (f == 0 || f == 1) { // where there is only 0 or 1 floor
            return f;
        }

        if (e == 1) { // when there is only 1 egg, start dropping from start
            return f;
        }

        if (dp[e][f] != null) {
            return dp[e][f];
        }

        int minDrop = Integer.MAX_VALUE;

        for (int k = 1; k <= f; k++) {
            int tempDrop = 1 + Math.max(superEggDropMemoization(e-1, k-1, dp),
                    superEggDropMemoization(e, f-k, dp));
            minDrop = Math.min(minDrop, tempDrop);
        }

        dp[e][f] = minDrop;
        return dp[e][f];
    }

    // (1) Recursive method
    // Extesion of MCM, aditya verma DP series
    // Getting TLE(Time Limit Exceeded) in recursive method
    public int superEggDrop3(int e, int f) {
        // e = egg, f = floor
        if (f == 0 || f == 1) {
            return f;
        }

        if (e == 1) {
            return f;
        }

        int minDrop = Integer.MAX_VALUE;

        for (int k = 1; k <= f; k++) {
            int tempDrop = 1 + Math.max(superEggDrop(e-1, k-1), superEggDrop(e, f-k));
            minDrop = Math.min(minDrop, tempDrop);
        }

        return minDrop;
    }
}
