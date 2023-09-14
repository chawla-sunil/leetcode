
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
    
    public boolean isValid(String s) {
        // method 1 below but it is not optimised
        // Stack<Character> stack = new Stack<>();
        // Map<Character, Character> map = new HashMap<>();
        // map.put(')', '(');
        // map.put(']', '[');
        // map.put('}', '{');
        // int n = s.length();
        // for (int i = 0; i < n; i++) {
        //     if (Arrays.asList('(', '[', '{').contains(s.charAt(i))) {
        //         stack.add(s.charAt(i));
        //     } else {
        //         if (stack.isEmpty() || stack.pop() != map.get(s.charAt(i))) {
        //             return false;
        //         }
        //     }
        // }
        // return stack.isEmpty();


        // method 2 below is efficient
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
}
