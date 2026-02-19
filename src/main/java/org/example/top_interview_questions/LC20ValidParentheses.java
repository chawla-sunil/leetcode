package org.example.top_interview_questions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC20ValidParentheses {
//    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//    An input string is valid if:
//
//    Open brackets must be closed by the same type of brackets.
//    Open brackets must be closed in the correct order.
//    Every close bracket has a corresponding open bracket of the same type.
//
//    Example 1:
//
//    Input: s = "()"
//    Output: true
//    Example 2:
//
//    Input: s = "()[]{}"
//    Output: true
//    Example 3:
//
//    Input: s = "(]"
//    Output: false
//
//
//    Constraints:
//            1 <= s.length <= 10^4
//            s consists of parentheses only '()[]{}'.

    // isValid and isValid2 are similar but this is more elegant solution for interviews.
    // stack add and push both method does the same work.
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c: s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                if (stack.isEmpty() || c != stack.pop()) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // isValid2 and isValid3 are same but isValid2 is simple and easy to understand.
    public boolean isValid2(String s) {
        Stack<Character> st = new Stack<>();

        for (char c: s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                st.add(c);
            } else if (st.isEmpty()) {
                return false;
            } else if (c == ')' && st.pop() != '(') {
                return false;
            } else if (c == '}' && st.pop() != '{') {
                return false;
            } else if (c == ']' && st.pop() != '[') {
                return false;
            }
        }

        return st.isEmpty(); // return true; is also fine.
    }

    public boolean isValid3(String s) {
        // method 1 below but it is not optimised
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (Arrays.asList('(', '[', '{').contains(s.charAt(i))) {
                stack.add(s.charAt(i));
            } else {
                if (stack.isEmpty() || stack.pop() != map.get(s.charAt(i))) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
