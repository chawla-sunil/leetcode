package org.example.striver.string;

import java.util.Arrays;

public class S40L14LongestCommonPrefix {
//    Write a function to find the longest common prefix string amongst an array of strings.
//    If there is no common prefix, return an empty string "".
//
//    Example 1:
//    Input: strs = ["flower","flow","flight"]
//    Output: "fl"
//
//    Example 2:
//    Input: strs = ["dog","racecar","car"]
//    Output: ""
//    Explanation: There is no common prefix among the input strings.
//
//
//    Constraints:
//            1 <= strs.length <= 200
//            0 <= strs[i].length <= 200
//    strs[i] consists of only lowercase English letters if it is non-empty.

    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];

        int index = 0;
        while (index < first.length() && index < last.length()) {
            if (first.charAt(index) == last.charAt(index)) {
                index++;
            } else {
                break;
            }
        }

        return first.substring(0, index);
    }

}
