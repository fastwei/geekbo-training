package com.geekbo.training.leetcode.top150;

import java.util.Stack;

/**
 * 解题思路：
 * <p>
 * 使用两个栈：一个栈用于存储数据，另一个栈用于存储当前栈中的最小元素。
 * 在push操作时，首先将元素压入数据栈中，然后判断最小栈是否为空或当前元素是否小于等于最小栈的栈顶元素，如果是，则将当前元素压入最小栈中。
 * 在pop操作时，首先获取数据栈的栈顶元素，然后将其弹出数据栈。如果该元素等于最小栈的栈顶元素，则也将最小栈的栈顶元素弹出。
 * 在top操作时，直接返回数据栈的栈顶元素。
 * 在getMin操作时，直接返回最小栈的栈顶元素。
 * <p>
 * 算法复杂度分析：
 * <p>
 * push操作的时间复杂度为O(1)，因为只需要将元素压入栈中。
 * pop操作的时间复杂度为O(1)，因为只需要将元素弹出栈中。
 * top操作的时间复杂度为O(1)，因为只需要返回栈顶元素。
 * getMin操作的时间复杂度为O(1)，因为只需要返回最小栈的栈顶元素。
 */
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        int val = stack.pop();
        if (val == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        // 创建MinStack对象
        MinStack minStack = new MinStack();

        // 调用push方法，并验证返回值为null
        minStack.push(-2);  // 输出: null
        minStack.push(0);   // 输出: null
        minStack.push(-3);  // 输出: null

        // 调用getMin方法，并验证返回值为-3
        System.out.println(minStack.getMin());  // 输出: -3

        // 调用pop方法，并验证返回值为null
        minStack.pop();     // 输出: null

        // 调用top方法，并验证返回值为0
        System.out.println(minStack.top());     // 输出: 0

        // 调用getMin方法，并验证返回值为-2
        System.out.println(minStack.getMin());  // 输出: -2
    }
}