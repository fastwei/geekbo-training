package com.geekbo.training.leetcode.daily;

/**
 * 2264. Largest 3-Same-Digit Number in String
 * Easy
 * <p>
 * You are given a string num representing a large integer. An integer is good if it meets the following conditions:
 * It is a substring of num with length 3.
 * It consists of only one unique digit.
 * Return the maximum good integer as a string or an empty string "" if no such integer exists.
 * <p>
 * Note:
 * A substring is a contiguous sequence of characters within a string.
 * There may be leading zeroes in num or a good integer.
 */
public class LargestThreeSameDigitNumber {

    public static void main(String[] args) {
        LargestThreeSameDigitNumber solution = new LargestThreeSameDigitNumber();

        // 测试用例1
        // 预期输出: "777"
        String num1 = "6777133339";
        String result1 = solution.largestThreeSameDigitNumber(num1);
        System.out.println(result1);

        // 测试用例2
        // 预期输出: "000"
        String num2 = "2300019";
        String result2 = solution.largestThreeSameDigitNumber(num2);
        System.out.println(result2);

        // 测试用例3
        // 预期输出: ""
        String num3 = "42352338";
        String result3 = solution.largestThreeSameDigitNumber(num3);
        System.out.println(result3);
    }

    /**
     * 解题思路：
     * 遍历num字符串，找到所有长度为3的子串，然后判断子串是否满足条件：
     * - 子串中的所有字符都相等
     * 如果满足条件，比较子串与当前最大好整数的大小，更新最大好整数。
     * 最后返回最大好整数。
     *
     * 算法复杂度分析：
     * - 遍历num字符串的时间复杂度为O(n)，其中n为num的长度。
     * - 对于每个长度为3的子串，判断是否满足条件的时间复杂度为O(1)。
     * 因此，总的时间复杂度为O(n)。
     *
     * @param num 给定的字符串
     * @return 最大好整数
     */
    public String largestThreeSameDigitNumber(String num) {
        // 初始化最大好整数为空字符串
        String maxGoodNumber = "";

        // 遍历num字符串
        for (int i = 0; i <= num.length() - 3; i++) {
            // 获取长度为3的子串
            String subString = num.substring(i, i + 3);
            // 判断子串是否满足条件：子串中的所有字符都相等
            if (subString.charAt(0) == subString.charAt(1) && subString.charAt(1) == subString.charAt(2)) {
                // 比较子串与当前最大好整数的大小，更新最大好整数
                if (subString.compareTo(maxGoodNumber) > 0) {
                    maxGoodNumber = subString;
                }
            }
        }

        // 返回最大好整数
        return maxGoodNumber;
    }
}