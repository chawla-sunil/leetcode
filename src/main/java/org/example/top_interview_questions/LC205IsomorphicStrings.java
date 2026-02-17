package org.example.top_interview_questions;

public class LC205IsomorphicStrings {
//    Given two strings s and t, determine if they are isomorphic.
//
//    Two strings s and t are isomorphic if the characters in s can be replaced to get t.
//
//    All occurrences of a character must be replaced with another character while preserving the order of characters.
//    No two characters may map to the same character, but a character may map to itself.
//
//
//
//    Example 1:
//    Input: s = "egg", t = "add"
//    Output: true
//    Explanation:
//    The strings s and t can be made identical by:
//    Mapping 'e' to 'a'.
//    Mapping 'g' to 'd'.
//
//    Example 2:
//    Input: s = "f11", t = "b23"
//    Output: false
//    Explanation:
//    The strings s and t can not be made identical as '1' needs to be mapped to both '2' and '3'.
//
//    Example 3:
//    Input: s = "paper", t = "title"
//    Output: true
//
//
//
//    Constraints:
//
//    1 <= s.length <= 5 * 104
//    t.length == s.length
//    s and t consist of any valid ascii character.

    public boolean isIsomorphic(String s, String t) {
        int[] map1 = new int[200];
        int[] map2 = new int[200];

        if (s.length() != t.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (map1[s.charAt(i)] != map2[t.charAt(i)]) {
                return false;
            }

            map1[s.charAt(i)] = i + 1;
            map2[t.charAt(i)] = i + 1;
        }

        return true;
    }
}
