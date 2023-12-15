package com.geekbo.training.leetcode.daily;

/**
 *
 * 415. Add Strings
 * Easy
 * Given two non-negative integers, num1 and num2 represented as string,
 * return the sum of num1 and num2 as a string.
 *
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
 * You must also not convert the inputs to integers directly.
 *
 *
 *
 * Example 1:
 *
 * Input: num1 = "11", num2 = "123"
 * Output: "134"
 * Example 2:
 *
 * Input: num1 = "456", num2 = "77"
 * Output: "533"
 * Example 3:
 *
 * Input: num1 = "0", num2 = "0"
 * Output: "0"
 *
 *
 * Constraints:
 *
 * 1 <= num1.length, num2.length <= 104
 * num1 and num2 consist of only digits.
 * num1 and num2 don't have any leading zeros except for the zero itself.
 *
 */
public class AddStrings {
    /**
     * 将两个非负整数字符串相加，返回它们的和作为字符串。
     * 要求不能使用任何内置的大整数库，也不能直接将输入转换为整数。
     *
     * 解题思路：
     * 1. 从字符串的最后一位开始逐位相加，同时记录进位carry。
     * 2. 使用StringBuilder来保存结果，方便逆序操作。
     * 3. 遍历完较短的字符串后，继续遍历较长的字符串。
     * 4. 最后如果还有进位，需要在结果最前面添加一个1。
     * 5. 最后将StringBuilder逆序并转换为字符串返回。
     *
     * 算法复杂度分析：
     * - 时间复杂度：O(max(m, n))，其中m和n分别为num1和num2的长度。
     * - 空间复杂度：O(max(m, n))，用于保存结果的StringBuilder的空间。
     */
    public static String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += num2.charAt(j) - '0';
                j--;
            }
            sb.append(sum % 10);
            carry = sum / 10;
        }

        if (carry != 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        // 测试用例
        String num1 = "11";
        String num2 = "123";
        // 预期输出: "134"
        System.out.println(addStrings(num1, num2));

        num1 = "456";
        num2 = "77";
        // 预期输出: "533"
        System.out.println(addStrings(num1, num2));

        num1 = "0";
        num2 = "0";
        // 预期输出: "0"
        System.out.println(addStrings(num1, num2));
    }
}