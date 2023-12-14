package com.geekbo.training.leetcode.codinginterview109;

/**
 * 题目：翻转数位
 * 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
 * <p>
 * 示例 1：
 * 输入: num = 1775(110111011112)
 * 输出: 8
 * <p>
 * 示例 2：
 * 输入: num = 7(01112)
 * 输出: 4
 */
public class ReverseBits {

    /**
     * 解题思路：
     * 使用双指针法。
     * @param num
     * @return
     */
    public int reverseBits(int num) {
        int ans = 0;
        String s = Integer.toBinaryString(num);
        if (s.length() < 32) {
            s = "0" + s;
        }
        int x = 0;
        int cur = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                cur++;
            }
            while (cur > 1 && x < i) {
                if (s.charAt(x) == '0') {
                    cur--;
                }
                x++;
            }
            ans = Math.max(ans, i - x + 1);
        }
        return ans;
    }

    /**
     * 解题思路：
     * 使用双指针法。
     * 定义 left 和 right 两个指针，分别指向当前连续的 1 的起始位置和结束位置。
     * 遍历整数 num 的每一位，如果当前位为 1，则将 right 指针右移一位；
     * 如果当前位为 0，则将 left 指针指向 right 指针的下一位，将 right 指针右移一位。
     * 在遍历过程中记录最大的连续 1 的长度。
     *
     * 算法复杂度分析：
     * 时间复杂度：O(logN)，其中 N 为 num 的位数。
     * 空间复杂度：O(1)。
     *
     * todo:还有些测试用例通不过
     *
     * @param num
     * @return
     */
    public int reverseBits2(int num) {
        int left = 0; // 连续 1 的起始位置
        int right = 0; // 连续 1 的结束位置
        int maxLen = 0; // 最大连续 1 的长度

        while (num != 0) {
            if ((num & 1) == 1) {
                right++;
            } else {
                left = right + 1;
                right++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            num >>>= 1;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        ReverseBits solution = new ReverseBits();
        // 测试用例1
        int num1 = 1775;
        // 二进制表示为 11011101111
        // 翻转第 4 个 0 后得到 1111111111，连续 1 的个数为 10
        int result1 = solution.reverseBits(num1);
        System.out.println(result1); // 10

        // 测试用例2
        int num2 = 7;
        // 二进制表示为 111
        // 不需要翻转 0，连续 1 的个数为 3
        int result2 = solution.reverseBits(num2);
        System.out.println(result2); // 3
    }
}