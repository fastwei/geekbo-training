package com.geekbo.training.leetcode.daily;

import java.util.Stack;

public class RemoveDuplicateLetters {
    /**
     * 去除字符串中重复的字母，使得每个字母只出现一次，并保证返回结果的字典序最小
     * 
     * @param s 输入的字符串
     * @return 去重后的字符串
     */
    public static String removeDuplicateLetters(String s) {
        int[] count = new int[26]; // 统计每个字母出现的次数
        boolean[] visited = new boolean[26]; // 记录每个字母是否已经在栈中

        // 统计每个字母出现的次数
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            count[ch - 'a']--; // 当前字符出现次数减1

            if (visited[ch - 'a']) {
                continue; // 如果当前字符已经在栈中，直接跳过
            }

            // 如果当前字符小于栈顶元素，并且栈顶元素在后面还会出现，则将栈顶元素弹出
            while (!stack.isEmpty() && ch < stack.peek() && count[stack.peek() - 'a'] > 0) {
                visited[stack.pop() - 'a'] = false;
            }

            stack.push(ch); // 将当前字符入栈
            visited[ch - 'a'] = true; // 标记当前字符已经在栈中
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop()); // 将栈中的元素逆序拼接成结果字符串
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String s1 = "bcabc";
        // 去除重复字母后，保持相对位置不变，返回 "abc"
        String expected1 = "abc";

        String s2 = "cbacdcbc";
        // 去除重复字母后，保持相对位置不变，返回 "acdb"
        String expected2 = "acdb";

        // 测试用例1
        String result1 = removeDuplicateLetters(s1);
        System.out.println(result1); // 输出：abc
        System.out.println(result1.equals(expected1)); // true

        // 测试用例2
        String result2 = removeDuplicateLetters(s2);
        System.out.println(result2); // 输出：acdb
        System.out.println(result2.equals(expected2)); // true
    }
}