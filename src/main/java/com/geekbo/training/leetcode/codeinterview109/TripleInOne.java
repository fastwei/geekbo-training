package com.geekbo.training.leetcode.codeinterview109;

import java.util.Arrays;

/**
 * 三合一。描述如何只用一个数组来实现三个栈。
 * <p>
 * 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。stackNum表示栈下标，value表示压入的值。
 * <p>
 * 构造函数会传入一个stackSize参数，代表每个栈的大小。
 * <p>
 * 示例1:
 * <p>
 * 输入：
 * ["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
 * [[1], [0, 1], [0, 2], [0], [0], [0], [0]]
 * 输出：
 * [null, null, null, 1, -1, -1, true]
 * 说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
 */
class TripleInOne {
    private static final int NUM_STACKS = 3;

    private int stackSize;
    private int[][] stack;
    private int[] top;

    public TripleInOne(int stackSize) {
        this.stackSize = stackSize;
        stack = new int[NUM_STACKS][stackSize];
        top = new int[NUM_STACKS];
        Arrays.fill(top, -1);
    }

    public void push(int stackNum, int value) {
        if (!isFull(stackNum)) {
            top[stackNum]++;
            stack[stackNum][top[stackNum]] = value;
        }
    }

    public int pop(int stackNum) {
        if (!isEmpty(stackNum)) {
            int value = stack[stackNum][top[stackNum]];
            top[stackNum]--;
            return value;
        } else {
            return -1;
        }
    }

    public int peek(int stackNum) {
        if (!isEmpty(stackNum)) {
            return stack[stackNum][top[stackNum]];
        } else {
            return -1;
        }
    }

    public boolean isEmpty(int stackNum) {
        return top[stackNum] == -1;
    }

    public boolean isFull(int stackNum) {
        return top[stackNum] == stackSize - 1;
    }
}