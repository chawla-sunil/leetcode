package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.List;

public class LC68TextJustification {
//    Given an array of strings words and a width maxWidth, format the text such that
//    each line has exactly maxWidth characters and is fully (left and right) justified.
//
//    You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
//    Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
//
//    Extra spaces between words should be distributed as evenly as possible.
//    If the number of spaces on a line does not divide evenly between words,
//    the empty slots on the left will be assigned more spaces than the slots on the right.
//
//    For the last line of text, it should be left-justified, and no extra space is inserted between words.
//
//    Note:
//
//    A word is defined as a character sequence consisting of non-space characters only.
//    Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
//    The input array words contains at least one word.
//
//
//    Example 1:
//    Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
//    Output:
//            [
//            "This    is    an",
//            "example  of text",
//            "justification.  "
//            ]
//
//
//    Example 2:
//    Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
//    Output:
//            [
//            "What   must   be",
//            "acknowledgment  ",
//            "shall be        "
//            ]
//    Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
//    Note that the second line is also left-justified because it contains only one word.
//            Example 3:
//
//    Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
//    Output:
//            [
//            "Science  is  what we",
//            "understand      well",
//            "enough to explain to",
//            "a  computer.  Art is",
//            "everything  else  we",
//            "do                  "
//            ]
//
//
//    Constraints:
//
//            1 <= words.length <= 300
//            1 <= words[i].length <= 20
//    words[i] consists of only English letters and symbols.
//1 <= maxWidth <= 100
//    words[i].length <= maxWidth

    // This solution is alos good => link =>
    // line => https://leetcode.com/problems/text-justification/solutions/24902/java-easy-to-understand-broken-into-seve-8qj7

    // This Solution is conmpletely mine and can be tell in interviews as it is
    //We will iterate from left to right, We will keep calculating the curr length of the string till maxWidth.
    //For last line and a line with one word, we just need to left satisty, so we will handle that case
    // specifically in if (i == n || wordCount == 1) condition. Now for other cases,
    // we will first findout how many equal spaceswe will need between each words and as specifies in the problem,
    // extra spaces we should start putting from the left.
    // Link for my Solution: https://leetcode.com/problems/text-justification/solutions/7492413/simple-while-loop-solution-by-sunilchawl-11b9
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int n = words.length;
        int i = 0;

        while (i < n) {
            int currLen = 0;
            int start = i;

            // Finding the range of words that can fit in the current line
            while (i < n && currLen + words[i].length() + (i - start) <= maxWidth) { // (i - start) are spaces between each words (at least 1)
                currLen += words[i].length();
                i++;
            }

            int end = i - 1; // The last word of the current line
            int wordCount = end - start + 1;
            int spaces = maxWidth - currLen;

            StringBuilder line = new StringBuilder();

            // Handling of the last line or a line with only one word
            if (i == n || wordCount == 1) {
                for (int j = start; j <= end; j++) {
                    line.append(words[j]);
                    if (j < end) {
                        line.append(" ");
                    }
                }
                // Adding spaces at the end of the line till the length is maxWidth
                while (line.length() < maxWidth) {
                    line.append(" ");
                }
            } else {
                // Equal minimun spaces between words
                int spacesBetweenWords = spaces / (wordCount - 1);
                int extraSpaces = spaces % (wordCount - 1); // Extra spaces to add from the left

                for (int j = start; j <= end; j++) {
                    line.append(words[j]);
                    if (j < end) { // Adding spaces between words
                        for (int k = 0; k < spacesBetweenWords; k++) {
                            line.append(" ");
                        }
                        if (extraSpaces > 0) {
                            line.append(" "); // adding extra spaces
                            extraSpaces--;
                        }
                    }
                }
            }

            result.add(line.toString());
        }

        return result;
    }
}
