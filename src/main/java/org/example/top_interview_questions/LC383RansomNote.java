package org.example.top_interview_questions;

import java.util.HashMap;
import java.util.Map;

public class LC383RansomNote {
//    Given two strings ransomNote and magazine, return true if
//    ransomNote can be constructed by using the letters from magazine and false otherwise.
//
//    Each letter in magazine can only be used once in ransomNote.
//
//
//    Example 1:
//    Input: ransomNote = "a", magazine = "b"
//    Output: false
//
//    Example 2:
//    Input: ransomNote = "aa", magazine = "ab"
//    Output: false
//
//    Example 3:
//    Input: ransomNote = "aa", magazine = "aab"
//    Output: true
//
//
//    Constraints:
//
//    1 <= ransomNote.length, magazine.length <= 105
//    ransomNote and magazine consist of lowercase English letters.

    // Both are exact same solution but this is better in time complexity
    // This takes 1ms and another solution takes 17ms.
    // simple exlaination is because hashmap collide can happen, so
    // because of this that might take extra time to solve
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        int[] count = new int[26];

        for (char c : magazine.toCharArray()) {
            count[c-'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            if (count[c-'a'] == 0) {
                return false;
            }
            count[c-'a']--;
        }
        return true;
    }

    public boolean canConstruct2(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < magazine.length(); i++) {
            map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i), 0) + 1);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            Integer count = map.get(ransomNote.charAt(i));

            if (count == null || count == 0) {
                return false;
            }
            map.put(ransomNote.charAt(i), count - 1);
        }

        return true;
    }
}
