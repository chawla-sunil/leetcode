package org.example.striver.string;

import java.util.HashMap;

public class S43LC242ValidAnagram {
//    Given two strings s and t, return true if t is an anagram of s, and false otherwise.
//
//    Example 1:
//    Input: s = "anagram", t = "nagaram"
//    Output: true
//
//    Example 2:
//    Input: s = "rat", t = "car"
//    Output: false
//
//    Constraints:
//    1 <= s.length, t.length <= 5 * 104
//    s and t consist of lowercase English letters.
//
//
//    Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] track = new int[26];

        for (int i = 0; i < s.length(); i++) {
            track[s.charAt(i) - 'a']++;
            track[t.charAt(i) - 'a']--;
        }

        for (int i: track) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isAnagram2(String s, String t) {
        // Solution for follow-up question
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }

        for (char c: map.keySet()) {
            if (map.get(c) != 0) {
                return false;
            }
        }

        return true;
    }
}
