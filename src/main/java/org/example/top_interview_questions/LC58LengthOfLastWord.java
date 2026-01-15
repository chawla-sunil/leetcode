package org.example.top_interview_questions;

public class LC58LengthOfLastWord {
//    Given a string s consisting of words and spaces, return the length of the last word in the string.
//
//    A word is a maximal substring consisting of non-space characters only.
//
//
//    Example 1:
//    Input: s = "Hello World"
//    Output: 5
//    Explanation: The last word is "World" with length 5.
//
//    Example 2:
//    Input: s = "   fly me   to   the moon  "
//    Output: 4
//    Explanation: The last word is "moon" with length 4.
//
//    Example 3:
//    Input: s = "luffy is still joyboy"
//    Output: 6
//    Explanation: The last word is "joyboy" with length 6.
//
//
//    Constraints:
//
//            1 <= s.length <= 104
//            s consists of only English letters and spaces ' '.
//            There will be at least one word in s.

    // This is better and faster and uses O(1) space
    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;

        while(end >= 0 && s.charAt(end) == ' ') { // " "(string) and ' '(char) are not same, so " " will not work
            end--;
        }

        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }

        return end - start;
    }

    // Easy, good and first thought solution
    // but uses O(n) space
    public int lengthOfLastWord2(String s) {
        String[] words = s.trim().split("\\s+");
        return words[words.length - 1].length();
    }
}
