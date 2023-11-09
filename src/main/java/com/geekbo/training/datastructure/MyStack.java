package com.geekbo.training.datastructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. Implement Stack using Queues
 * Easy
 * Implement a last-in-first-out (LIFO) stack using only two queues.
 * The implemented stack should support all the functions of a normal
 * stack (push, top, pop, and empty).
 * <p>
 * Implement the MyStack class:
 * <p>
 * void push(int x) Pushes element x to the top of the stack.
 * int pop() Removes the element on the top of the stack and returns it.
 * int top() Returns the element on the top of the stack.
 * boolean empty() Returns true if the stack is empty, false otherwise.
 * Notes:
 * <p>
 * You must use only standard operations of a queue,
 * which means that only push to back, peek/pop from front, size and is empty operations are valid.
 * Depending on your language, the queue may not be supported natively.
 * You may simulate a queue using a list or deque (double-ended queue)
 * as long as you use only a queue's standard operations.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output
 * [null, null, null, 2, 2, false]
 * <p>
 * Explanation
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // return 2
 * myStack.pop(); // return 2
 * myStack.empty(); // return False
 */
class MyStack {
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    private int top;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        q1.add(x);
        top = x;
    }

    public int pop() {
        while (q1.size() > 1) {
            top = q1.remove();
            q2.add(top);
        }
        int popValue = q1.remove();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return popValue;
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return q1.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top()); // Output: 2
        System.out.println(myStack.pop()); // Output: 2
        System.out.println(myStack.empty()); // Output: false
    }
}