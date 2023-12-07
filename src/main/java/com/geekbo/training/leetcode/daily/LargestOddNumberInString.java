package com.geekbo.training.leetcode.daily;

public class LargestOddNumberInString {

    /**
     * 返回字符串中的最大奇数。
     * 解题思路：从字符串尾部向前遍历，找到第一个奇数字符并返回该字符及其前面的所有字符。
     * 算法复杂度：O(n)，其中n是字符串的长度，因为最坏情况下需要遍历整个字符串。
     *
     * @param num 表示大整数的字符串
     * @return 字符串中的最大奇数，如果不存在则返回空字符串
     */
    public static String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            if ((num.charAt(i) - '0') % 2 != 0) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }

    public static void main(String[] args) {
        // 测试用例和预期结果
        System.out.println(largestOddNumber("52").equals("5")); // 输出为 "5"
        System.out.println(largestOddNumber("4206").equals("")); // 输出为空字符串 ""
        System.out.println(largestOddNumber("35427").equals("35427")); // 输出为 "35427"
    }
}
