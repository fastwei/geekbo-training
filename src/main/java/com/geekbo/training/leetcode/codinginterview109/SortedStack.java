package com.geekbo.training.leetcode.codinginterview109;


import java.util.Stack;

/**
 * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。
 * 最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
 * 该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
 * <p>
 * 解题思路： 要对栈进行排序，可以使用一个辅助栈来实现。具体步骤如下：
 * <p>
 * 创建一个辅助栈，用于存放排序后的元素。
 * 将原栈中的元素依次弹出，与辅助栈中的元素比较。
 * 如果辅助栈为空或者当前元素小于等于辅助栈的栈顶元素，则将当前元素入栈。
 * 如果当前元素大于辅助栈的栈顶元素，则将辅助栈中的元素弹出并入原栈，直到找到合适的位置。
 * 将辅助栈中的元素依次弹出，并入原栈。
 * <p>
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：O(n^2)，其中n是栈中的元素个数。对于每个元素，需要将其与辅助栈中的元素比较，并将辅助栈中的元素弹出并入原栈。
 * 空间复杂度：O(n)，需要使用一个辅助栈来存放排序后的元素。
 */
class SortedStack {
    private Stack<Integer> stack;
    private Stack<Integer> tempStack;

    public SortedStack() {
        stack = new Stack<>();
        tempStack = new Stack<>();
    }

    public void push(int val) {
        // 将比val大的元素移到辅助栈
        while (!stack.isEmpty() && stack.peek() < val) {
            tempStack.push(stack.pop());
        }
        // 将val入栈
        stack.push(val);
        // 将辅助栈的元素移回原栈
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public int peek() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        return -1;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        SortedStack stack = new SortedStack();

        // 测试用例1
        stack.push(1);
        stack.push(2);
        System.out.println(stack.peek());   // 返回 1
        stack.pop();
        System.out.println(stack.peek());   // 返回 2

        // 测试用例2
        stack.pop();
        stack.push(1);
        stack.pop();
        System.out.println(stack.isEmpty());   // 返回 true
    }
}