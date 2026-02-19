package org.example.top_interview_questions;

import java.util.Stack;

public class LC155MinStack {
//    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
//
//    Implement the MinStack class:
//
//    MinStack() initializes the stack object.
//    void push(int val) pushes the element val onto the stack.
//    void pop() removes the element on the top of the stack.
//    int top() gets the top element of the stack.
//    int getMin() retrieves the minimum element in the stack.
//    You must implement a solution with O(1) time complexity for each function.
//
//
//
//    Example 1:
//
//    Input
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//        [[],[-2],[0],[-3],[],[],[],[]]
//
//    Output
//[null,null,null,null,-3,null,0,-2]
//
//    Explanation
//    MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin(); // return -3
//minStack.pop();
//minStack.top();    // return 0
//minStack.getMin(); // return -2
//
//
//    Constraints:
//
//            -231 <= val <= 231 - 1
//    Methods pop, top and getMin operations will always be called on non-empty stacks.
//    At most 3 * 104 calls will be made to push, pop, top, and getMin.

    class MinStack {
        private Node head;

        public MinStack() {

        }

        public void push(int val) {
            if (head == null) {
                head = new Node(val, val, null);
            } else {
                head = new Node(val, Math.min(val, head.min), head);
            }
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }

        private class Node {
            int val;
            int min;
            Node next;

            private Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */

// This below solution is also decent but not great.
// Above one is great solution

    class MinStack2 {
        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        public void push(int x) {
            // only push the old minimum value when the current
            // minimum value changes after pushing the new value x
            if(x <= min){
                stack.push(min);
                min=x;
            }
            stack.push(x);
        }

        public void pop() {
            // if pop operation could result in the changing of the current minimum value,
            // pop twice and change the current minimum value to the last minimum value.
            if(stack.pop() == min) min=stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }
}
