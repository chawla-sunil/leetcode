package org.example.companies;

import java.util.HashMap;
import java.util.Map;

public class MrSool {
    // Problem 1
//    Given a string, find a longest sequence of repeating characters, and return that character.
//    aabbbbb -> b


    public static char solve1(String s) {
        if (s == null || s.length() == 0) {
            return 'a';
        }

        char answer = s.charAt(0);
        int currentlength = 1;
        int bestLength = 1;

        for  (int i = 1; i < s.length(); i++) {
            currentlength = s.charAt(i) == s.charAt(i-1)
                            ? currentlength + 1 : 1;


            if (currentlength > bestLength) {
                bestLength = currentlength;
                answer = s.charAt(i);
            }
            if (currentlength == bestLength) {
                bestLength = currentlength;
                answer = answer > s.charAt(i)
                         ? s.charAt(i) : answer;
            }


        }

        return answer;
    }


//    You are given two arrays — a source array and a target array.
//    You can perform exactly one operation: move any single element of the array to the beginning (front) of the array.
//    Determine how many minimum operations we have to perform to convert the source array to target array. If it is not possible to convert return -1.
//
//
//    source : [4, 2, 7, 12, 89, 5]
//    Target   : [4, 89, 2, 7, 12, 5]
//
//            [4, 2, 7, 12, 89, 5] -> [89, 4, 2, 7, 12, 5] -> [4, 89, 2, 7, 12, 5]
//    Answer = 2

    public static int solve2(int[] source, int[] target) {
        if (source.length != target.length) {
            return -1;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int s: source) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        for (int t: target) {
            map.put(t, map.getOrDefault(t, 0) - 1);
        }

        for (int frequency: map.values()) {
            if (frequency != 0) {
                return -1;
            }
        }

        int tIndex = target.length - 1;

        for (int i = source.length - 1; i >= 0 && tIndex >= 0; i--) {
            if (source[i] == target[tIndex]) {
                tIndex--;
            }
        }

        return tIndex + 1;
    }
}
