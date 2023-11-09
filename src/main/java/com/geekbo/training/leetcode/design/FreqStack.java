package com.geekbo.training.leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 895. Maximum Frequency Stack
 * Hard
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
 * <p>
 * Implement the FreqStack class:
 * <p>
 * FreqStack() constructs an empty frequency stack.
 * void push(int val) pushes an integer val onto the top of the stack.
 * int pop() removes and returns the most frequent element in the stack.
 * If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
 * [[], [5], [7], [5], [7], [4], [5], [], [], [], []]
 * Output
 * [null, null, null, null, null, null, null, 5, 7, 5, 4]
 * <p>
 * Explanation
 * FreqStack freqStack = new FreqStack();
 * freqStack.push(5); // The stack is [5]
 * freqStack.push(7); // The stack is [5,7]
 * freqStack.push(5); // The stack is [5,7,5]
 * freqStack.push(7); // The stack is [5,7,5,7]
 * freqStack.push(4); // The stack is [5,7,5,7,4]
 * freqStack.push(5); // The stack is [5,7,5,7,4,5]
 * freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
 * freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
 * freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
 * freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
 */
class FreqStack {
    private Map<Integer, Integer> freqMap;
    private Map<Integer, Stack<Integer>> stackMap;
    private int maxFreq;

    public FreqStack() {
        freqMap = new HashMap<>();
        stackMap = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        int freq = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, freq);
        maxFreq = Math.max(maxFreq, freq);

        if (!stackMap.containsKey(freq)) {
            stackMap.put(freq, new Stack<>());
        }
        stackMap.get(freq).push(val);
    }

    public int pop() {
        int val = stackMap.get(maxFreq).pop();
        freqMap.put(val, freqMap.get(val) - 1);

        if (stackMap.get(maxFreq).isEmpty()) {
            maxFreq--;
        }

        return val;
    }
}