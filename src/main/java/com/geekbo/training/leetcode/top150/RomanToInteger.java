package com.geekbo.training.leetcode.top150;

import java.util.HashMap;

/**
 *
 * 解题思路：
 *
 * 创建一个哈希表，将罗马数字字符映射到整数。
 * 从右到左遍历罗马数字，根据字符的值以及前一个字符的值来判断是加还是减。
 * 如果当前字符小于前一个字符，需要减去当前字符的值；否则，加上当前字符的值。
 *
 */
public class RomanToInteger {
    public int romanToInt(String s) {
        // 创建一个哈希表，将罗马数字字符映射到整数
        HashMap<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int prevValue = 0; // 用于跟踪前一个字符的值

        // 从右到左遍历罗马数字
        for (int i = s.length() - 1; i >= 0; i--) {
            char currentChar = s.charAt(i);
            int currentValue = romanMap.get(currentChar);

            // 如果当前字符小于前一个字符，表示需要减去当前字符的值
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            prevValue = currentValue; // 更新前一个字符的值
        }

        return result;
    }

    public static void main(String[] args) {
        RomanToInteger solution = new RomanToInteger();

        System.out.println("Test Case 1: " + solution.romanToInt("III")); // 预期输出：3
        System.out.println("Test Case 2: " + solution.romanToInt("LVIII")); // 预期输出：58
        System.out.println("Test Case 3: " + solution.romanToInt("MCMXCIV")); // 预期输出：1994
    }

}
