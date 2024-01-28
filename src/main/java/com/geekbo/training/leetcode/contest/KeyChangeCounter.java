package com.geekbo.training.leetcode.contest;
//382.1
public class KeyChangeCounter {

    /**
     * 计算按键变更的次数。
     *
     * @param s 用户输入的字符串
     * @return 按键变更的次数
     */
    public static int countKeyChanges(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        char lastChar = Character.toLowerCase(s.charAt(0)); // 初始化为第一个字符的小写形式

        for (int i = 1; i < s.length(); i++) {
            char currentChar = Character.toLowerCase(s.charAt(i));
            // 如果当前字符与上一个字符不同，则计数增加
            if (currentChar != lastChar) {
                count++;
                lastChar = currentChar;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // 测试用例
        String test1 = "aAbBcC";
        System.out.println("Expected: 2, Actual: " + countKeyChanges(test1));

        String test2 = "AaAaAaaA";
        System.out.println("Expected: 0, Actual: " + countKeyChanges(test2));

        // 更多测试用例
        String test3 = "abcABC";
        System.out.println("Expected: 5, Actual: " + countKeyChanges(test3));

        String test4 = "";
        System.out.println("Expected: 0, Actual: " + countKeyChanges(test4));
    }
}
