package org.example.top_interview_questions;

public class LC6ZigzagConversion {
//    The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
//    (you may want to display this pattern in a fixed font for better legibility)
//
//    P   A   H   N
//    A P L S I I G
//    Y   I   R
//    And then read line by line: "PAHNAPLSIIGYIR"
//
//    Write the code that will take a string and make this conversion given a number of rows:
//
//    string convert(string s, int numRows);
//
//
//    Example 1:
//    Input: s = "PAYPALISHIRING", numRows = 3
//    Output: "PAHNAPLSIIGYIR"
//
//    Example 2:
//    Input: s = "PAYPALISHIRING", numRows = 4
//    Output: "PINALSIGYAHRPI"
//    Explanation:
//    P     I    N
//    A   L S  I G
//    Y A   H R
//    P     I
//
//    Example 3:
//    Input: s = "A", numRows = 1
//    Output: "A"
//
//
//    Constraints:
//
//            1 <= s.length <= 1000
//            s consists of English letters (lower-case and upper-case), ',' and '.'.
//            1 <= numRows <= 1000

    // Create nRows StringBuilder, and keep collecting characters from original string to corresponding StringBuilder.
    // Just take care of your index to keep them in bound.
    public String convert(String s, int numRows) {
        int n = s.length();

        if (numRows == 1 || numRows >= n) {
            // This condition is not really needed but will be helpful in mentioed condition cases
            // we will not need to traverse the whole code in these 2 conditions (numRows == 1 || numRows >= n)
            return s;
        }

        StringBuilder[] sb = new StringBuilder[numRows];
        for (int iRow = 0; iRow < numRows; iRow++) {
            sb[iRow] = new StringBuilder();
        }

        int i = 0;
        while(i < n) {
            for (int iRow = 0; iRow < numRows && i < n; iRow++) { // vertically down
                sb[iRow].append(s.charAt(i));
                i++;
            }

            for (int iRow = numRows-2; iRow >= 1 && i < n; iRow--) { // obliquely up (diagonal)
                sb[iRow].append(s.charAt(i));
                i++;
            }
        }

        for (int iRow = 1; iRow < numRows; iRow++) {
            sb[0].append(sb[iRow]);
        }

        return sb[0].toString();
    }
}
