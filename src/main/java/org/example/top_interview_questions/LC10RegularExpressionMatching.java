package org.example.top_interview_questions;

public class LC10RegularExpressionMatching {
//    Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
//
//            '.' Matches any single character.
//            '*' Matches zero or more of the preceding element.
//    The matching should cover the entire input string (not partial).
//
//    Example 1:
//
//    Input: s = "aa", p = "a"
//    Output: false
//    Explanation: "a" does not match the entire string "aa".
//    Example 2:
//
//    Input: s = "aa", p = "a*"
//    Output: true
//    Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
//    Example 3:
//
//    Input: s = "ab", p = ".*"
//    Output: true
//    Explanation: ".*" means "zero or more (*) of any character (.)".
//
//
//    Constraints:
//
//            1 <= s.length <= 20
//            1 <= p.length <= 20
//    s contains only lowercase English letters.
//    p contains only lowercase English letters, '.', and '*'.
//    It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.

    public boolean isMatch(String s, String p) {
        return checkMatch(0, 0, s, p);
    }

    public boolean checkMatch(int i, int j, String s, String p) {
        if (i >= s.length() && j >= p.length()) {
            return true;
        }
        if (j >= p.length()) {
            return false;
        }

        boolean match = i < s.length() &&
                (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        if (j+1 < p.length() && p.charAt(j + 1) == '*') {
            return (checkMatch(i, j+2, s, p) ||       // not using the * 
                    (match && checkMatch(i+1, j, s, p)));  // using the *

        }
        if (match) {
            return checkMatch(i+1, j+1, s, p);
        }
        return false;
    }
}
