package org.example.companies;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GoFynd {
//    public void delete(Node node) {
//        node.data = node.next.data;
//        node.next = node.next.next;
//    }

    public static void main(String[] args) {
        System.out.println(balance2("()()[{}]{}"));
    }

    public static boolean balance(String s) {
        Stack<Character> stack = new Stack<>();

        for(char c: s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push(('}'));
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

    public static boolean balance2(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        for(char c: s.toCharArray()) {
            if (map.containsValue(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || map.get(c) != stack.pop()) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
