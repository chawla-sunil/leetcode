package org.example.striver.binarysearch;

public class S36LC875KokoEatingBananas {
//    Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
//    The guards have gone and will come back in h hours.
//
//    Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile.
//    If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
//
//    Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
//
//    Return the minimum integer k such that she can eat all the bananas within h hours.
//
//
//    Example 1:
//    Input: piles = [3,6,7,11], h = 8
//    Output: 4
//
//    Example 2:
//    Input: piles = [30,11,23,4,20], h = 5
//    Output: 30
//
//    Example 3:
//    Input: piles = [30,11,23,4,20], h = 6
//    Output: 23

    public int minEatingSpeed(int[] piles, int h) {
        int max = piles[0];
        for (int pile: piles) {
            if (pile > max) {
                max = pile;
            }
        }

        int min = 1;
        int ans = max;

        while (min <= max) {
            int mid = min + (max - min) / 2;

            long hours = 0; // hours it will take if we take mid as k(answer),
            // took long because hours might get integer overflow. example:
            // lets say mid is and more values are present in the array
            // hours += (805306368 + 1 - 1) / 1; // Result = 805306368
            for (int pile: piles) {
                hours = hours + (pile + mid - 1) / mid; // Ceiling division
            }

            if (hours <= h) {
                ans = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return ans;
    }
}
