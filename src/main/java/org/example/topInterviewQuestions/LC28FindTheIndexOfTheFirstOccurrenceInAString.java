package org.example.topInterviewQuestions;

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
}
