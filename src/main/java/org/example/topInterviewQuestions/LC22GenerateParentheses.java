package org.example.topInterviewQuestions;

import java.util.ArrayList;
import java.util.List;

public class LC22GenerateParentheses {
//    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//    Example 1:
//
//    Input: n = 3
//    Output: ["((()))","(()())","(())()","()(())","()()()"]
//
//    Example 2:
//    Input: n = 1
//    Output: ["()"]
//
//    Constraints:
//
//     1 <= n <= 8

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        recurse(res, 0, 0, "", n);
        return res;
    }

    public void recurse(List<String> res, int openBracket, int closeBracket, String s, int n) {
        if (s.length() == n * 2) {
            res.add(s);
            return;
        }
        if (openBracket < n) {
            recurse(res, openBracket + 1, closeBracket, s + "(", n);
        }
        if (closeBracket < openBracket) {
            recurse(res, openBracket, closeBracket + 1, s + ")", n);
        }
    }
}
