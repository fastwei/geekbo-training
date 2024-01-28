package com.geekbo.training.leetcode.contest;

//381.11
public class KeypadTyping {

    /**
     * 计算打字单词的最小按键次数。
     * @param word 输入的单词，由不同的小写英文字母组成。
     * @return 返回打字该单词所需的最小按键次数。
     */
    public static int minimumPushes(String word) {
        // 每个不同的字母只需按一次，因此按键次数等于字母的数量。
        return word.length();
    }

    public static void main(String[] args) {
        // 测试用例
        String testWord1 = "abcde";
        int result1 = minimumPushes(testWord1);
        System.out.println("Expected: 5, Actual: " + result1);  // 预期：5

        String testWord2 = "xycdefghij";
        int result2 = minimumPushes(testWord2);
        System.out.println("Expected: 12, Actual: " + result2); // 预期：12

        // 可以添加更多测试用例来验证不同情况。
    }
}
