package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC17LetterCombinationsOfAPhoneNumber {
//    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
//
//    A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
//
//
//
//
//    Example 1:
//
//    Input: digits = "23"
//    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
//    Example 2:
//
//    Input: digits = ""
//    Output: []
//    Example 3:
//
//    Input: digits = "2"
//    Output: ["a","b","c"]
//
//
//    Constraints:
//
//            0 <= digits.length <= 4
//            digits[i] is a digit in the range ['2', '9'].

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<>();
        Map<Character, char[]> map = new HashMap<>();
        map.put('2', new char[] {'a', 'b', 'c'});
        map.put('3', new char[] {'d', 'e', 'f'});
        map.put('4', new char[] {'g', 'h', 'i'});
        map.put('5', new char[] {'j', 'k', 'l'});
        map.put('6', new char[] {'m', 'n', 'o'});
        map.put('7', new char[] {'p', 'q', 'r', 's'});
        map.put('8', new char[] {'t', 'u', 'v'});
        map.put('9', new char[] {'w', 'x', 'y', 'z'});

        List<String> output = new ArrayList<>();

        backTracing(0, "", map, output, digits);
        return output;
    }

    public void backTracing(int currentIndex, String currStr, Map<Character, char[]> map, List<String> output, String digits) {
        if (currStr.length() == digits.length()) {
            output.add(currStr);
            return;
        }
        char[] chars = map.get(digits.charAt(currentIndex));
        for (char c: chars) {
            backTracing(currentIndex + 1, currStr + c, map, output, digits);
        }
    }
}
