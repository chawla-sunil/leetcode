package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC30SubstringWithConcatenationOfAllWords {
//    You are given a string s and an array of strings words. All the strings of words are of the same length.
//
//    A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
//
//    For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are
//    all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
//    Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.
//
//
//
//    Example 1:
//    Input: s = "barfoothefoobarman", words = ["foo","bar"]
//    Output: [0,9]
//    Explanation:
//    The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
//    The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
//
//    Example 2:
//    Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//    Output: []
//    Explanation:
//    There is no concatenated substring.
//
//    Example 3:
//    Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//    Output: [6,9,12]
//    Explanation:
//    The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
//    The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
//    The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].
//
//
//
//    Constraints:
//
//            1 <= s.length <= 104
//            1 <= words.length <= 5000
//            1 <= words[i].length <= 30
//            s and words[i] consist of lowercase English letters.


    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || words == null || s.isEmpty() || words.length == 0) {
            return new ArrayList<>();
        }

        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        int n = s.length();
        int num = words.length;
        int wordLen = words[0].length();

        for (int i = 0; i < n - num * wordLen + 1; i++) {
            String sub = s.substring(i, i + num * wordLen);
            if (isConcat(sub, counts, wordLen)) {
                res.add(i);
            }
        }
        return res;
    }

    public boolean isConcat(String sub, Map<String, Integer> counts, int wordLen) {
        Map<String, Integer> visited = new HashMap<>();
        for (int i = 0; i < sub.length(); i += wordLen) {
            String sWord = sub.substring(i, i + wordLen);
            visited.put(sWord, visited.getOrDefault(sWord, 0) + 1);

            // if (visited.get(sWord) > counts.getOrDefault(sWord, 0)) {
            //     return false;
            // }
            // This condition is causing TLE(Time Limit Exceeded)
        }

        // Validate that the visited map matches the counts map
        return visited.equals(counts);
    }

}
