package org.example.top_interview_questions;

public class LC28FindTheIndexOfTheFirstOccurrenceInAString {
//    Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
//
//    Example 1:
//
//    Input: haystack = "sadbutsad", needle = "sad"
//    Output: 0
//    Explanation: "sad" occurs at index 0 and 6.
//    The first occurrence is at index 0, so we return 0.
//    Example 2:
//
//    Input: haystack = "leetcode", needle = "leeto"
//    Output: -1
//    Explanation: "leeto" did not occur in "leetcode", so we return -1.
//
//
//    Constraints:
//
//            1 <= haystack.length, needle.length <= 104
//            haystack and needle consist of only lowercase English characters.

    // both solutions are almost same but this one is a little better
    public int strStr(String haystack, String needle) {
        // return haystack.indexOf(needle);  // haha
        int hayLen = haystack.length();
        int needLen = needle.length();
        if (hayLen < needLen) {
            return -1;
        }
        for (int i =0; i <= hayLen - needLen; i++) {
            int j = 0;
            while (j < needLen && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == needLen) {
                return i;
            }
        }
        return -1;
    }

    // Simple Soulution, Time: O(n*h)
    // Loop over haystack and find needle
    // Takes 1 ms and beats 37.50, better solution is another one
    public int strStr2(String haystack, String needle) {
        int h = haystack.length();
        int n = needle.length();
        if (n > h) {
            return -1;
        }

        for (int i = 0; i <= h-n; i++) { // for haystack
            int k = i;
            for (int j = 0; j < n; j++) {
                if (haystack.charAt(k++) != needle.charAt(j)) { // for needle
                    break;
                }
                if (j == n-1) { // that means the loop did not break and
                    return i;
                }
            }
        }

        return -1;
    }
}
