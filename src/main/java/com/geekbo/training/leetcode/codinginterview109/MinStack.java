package com.geekbo.training.leetcode.codinginterview109;

import java.util.Stack;

/**
 * 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。
 * 执行push、pop和min操作的时间复杂度必须为O(1)。
 */
class MinStack {

    // 主栈，用于存储元素
    private Stack<Integer> stack;
    // 辅助栈，用于存储当前栈中的最小值
    private Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        // 将元素入栈
        stack.push(x);
        // 如果辅助栈为空或者当前元素小于等于辅助栈的栈顶元素，则将当前元素也入辅助栈
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        // 如果主栈的栈顶元素等于辅助栈的栈顶元素，则辅助栈也要出栈
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        // 主栈出栈
        stack.pop();
    }

    public int top() {
        // 返回主栈的栈顶元素
        return stack.peek();
    }

    public int getMin() {
        // 返回辅助栈的栈顶元素，即当前栈中的最小值
        return minStack.peek();
    }

    public static void main(String[] args) {
        // 创建一个最小栈对象
        MinStack minStack = new MinStack();

        // 测试用例1
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());   // 返回 -3.
        minStack.pop();
        System.out.println(minStack.top());      // 返回 0.
        System.out.println(minStack.getMin());   // 返回 -2.

        // 测试用例2
        minStack.push(2);
        minStack.push(1);
        minStack.push(3);
        minStack.push(-1);
        System.out.println(minStack.getMin());   // 返回 -1.
        minStack.pop();
        System.out.println(minStack.top());      // 返回 3.
        System.out.println(minStack.getMin());   // 返回 1.
    }
}