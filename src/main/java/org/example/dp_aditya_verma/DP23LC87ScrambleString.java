package org.example.dp_aditya_verma;

import java.util.HashMap;
import java.util.Map;

public class DP23LC87ScrambleString {
//    We can scramble a string s to get a string t using the following algorithm:
//
//    If the length of the string is 1, stop.
//    If the length of the string is > 1, do the following:
//    Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
//    Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
//            Apply step 1 recursively on each of the two substrings x and y.
//    Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.
//
//
//
//    Example 1:
//    Input: s1 = "great", s2 = "rgeat"
//    Output: true
//    Explanation: One possible scenario applied on s1 is:
//            "great" --> "gr/eat" // divide at random index.
//            "gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
//            "gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at random index each of them.
//            "g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
//            "r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
//            "r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
//    The algorithm stops now, and the result string is "rgeat" which is s2.
//    As one possible scenario led s1 to be scrambled to s2, we return true.
//
//    Example 2:
//    Input: s1 = "abcde", s2 = "caebd"
//    Output: false
//
//    Example 3:
//    Input: s1 = "a", s2 = "a"
//    Output: true
//
//
//    Constraints:
//
//    s1.length == s2.length
//    1 <= s1.length <= 30
//    s1 and s2 consist of lowercase English letters.



    /*
     * Example: s1 = "great", s2 = "rgeat"
     *
     * Tree showing the scrambling process:
     *
     *                              "great"
     *                                 |
     *                      split at index 2 (gr|eat)
     *                                 |
     *                    ┌────────────┴────────────┐
     *                   "gr"                     "eat"
     *                    |                          |
     *           split at index 1              split at index 1
     *           (g|r)                         (e|at)
     *                    |                          |
     *         ┌──────────┴──────────┐    ┌─────────┴─────────┐
     *        "g"                   "r"   "e"                 "at"
     *         |                     |     |                   |
     *      SWAP!                    |     |            split at index 1
     *         |                     |     |            (a|t)
     *         └──────────┬──────────┘     |                   |
     *                   "rg"               |         ┌─────────┴─────────┐
     *                    |                 |        "a"                 "t"
     *                    |                 |         |                   |
     *                    └────────┬────────┘      NO SWAP              NO SWAP
     *                           "rg" + "e"          |                   |
     *                                |              └─────────┬─────────┘
     *                                |                      "at"
     *                                |                        |
     *                                └───────────┬────────────┘
     *                                        "rge" + "at"
     *                                             |
     *                                         "rgeat" ✓
     *
     * Step-by-step breakdown:
     * 1. "great" → split(2) → "gr" + "eat"
     * 2. "gr" → split(1) → "g" + "r" → SWAP → "rg"
     * 3. "eat" → split(1) → "e" + "at"
     * 4. "at" → split(1) → "a" + "t" → NO SWAP → "at"
     * 5. Combine: "rg" + "e" + "at" = "rgeat"
     *
     * Binary representation of decisions:
     *
     *                              great(0,5)
     *                                 |
     *                           i=2 (split point)
     *                                 |
     *                    ┌────────────┴────────────┐
     *                   gr(0,2)                  eat(2,5)
     *                  [SWAP]                   [NO SWAP]
     *                    |                          |
     *              ┌─────┴─────┐            ┌──────┴──────┐
     *            g(0,1)      r(1,2)        e(2,3)      at(3,5)
     *              |           |             |           |
     *            leaf        leaf          leaf     ┌────┴────┐
     *                                              a(3,4)  t(4,5)
     *                                                |       |
     *                                              leaf    leaf
     *
     * Comparison with "rgeat":
     * - "great"[0:2]="gr" swaps with "rgeat"[3:5]="at"? NO
     * - "great"[0:2]="gr" swaps to "rg" matches "rgeat"[0:2]="rg"? YES ✓
     * - "great"[2:5]="eat" matches "rgeat"[2:5]="eat"? YES ✓
     *
     * Result: TRUE (s2 is a scrambled version of s1)
     */



    // for storing already solved problems
    Map<String, Boolean> map = new HashMap<>();

    // Memoization version of isScramble2
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();

        // if both strings are not equal in size
        if (s2.length() != n)
            return false;

        // if both strings are equal
        if (s1.equals(s2))
            return true;

        // if code is reached to this condition then following this are sure:
        // 1. size of both string is equal
        // 2. string are not equal
        // so size is equal (where size==1) and they are not equal then obviously false
        // example 'a' and 'b' size is equal ,string are not equal
        if (n == 1)
            return false;

        String key = s1 + " " + s2;

        // check if this problem has already been solved
        if (map.containsKey(key))
            return map.get(key);

        // for every iteration it can two condition
        // 1.we should proceed without swapping
        // 2.we should swap before looking next
        for (int i = 1; i < n; i++) {
            // ex of without swap: gr|eat and rg|eat
            // without swap
            if (
                // left part of first and second string
                    isScramble(s1.substring(0, i), s2.substring(0, i))
                            &&
                            // right part of first and second string;
                            isScramble(s1.substring(i), s2.substring(i))
            ) {
                // if without swap give us right answer then we do not need
                // to call the recursion withswap
                map.put(key, true);
                return true;
            }

            // ex of with swap: gr|eat rge|at
            // here we compare "gr" with "at" and "eat" with "rge"
            // with swap
            else if (
                // left part of first and right part of second
                    isScramble(s1.substring(0, i), s2.substring(n - i))
                            &&
                            // right part of first and left part of second
                            isScramble(s1.substring(i), s2.substring(0, n - i))
            ) {
                // if withswap give us right answer then we return true
                // otherwise the for loop do it work
                map.put(key, true);
                return true;
            }
            // we are not returning false in else case
            // because we want to check further cases with the for loop
        }
        map.put(key, false);
        return false;
    }

    // It is variation of MCM (Matrix Chain Multiplication)
    // adtiya verma dp series
    // Recursive method, so getting TLE(Time Limit Exceeded)
    public boolean isScramble2(String s1, String s2) {
        int n = s1.length();

        if (n != s2.length()) {
            return false;
        }

        if (s1.equals(s2)) {
            return true;
        }

        if (n == 1) {
            return false;
        }

        for (int i = 1; i < n; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                    isScramble(s1.substring(i), s2.substring(i))
            ) {
                return true;
            } else if (isScramble(s1.substring(0, i), s2.substring(n-i)) &&
                    isScramble(s1.substring(i), s2.substring(0, n-i))
            ) {
                return true;
            }
        }
        return false;
    }

}
