package com.geekbo.training.leetcode.daily;


/**
 * 1422. Maximum Score After Splitting a String
 * Solved
 * Easy
 * Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings (i.e. left substring and right substring).
 * <p>
 * The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "011101"
 * Output: 5
 * Explanation:
 * All possible ways of splitting s into two non-empty substrings are:
 * left = "0" and right = "11101", score = 1 + 4 = 5
 * left = "01" and right = "1101", score = 1 + 3 = 4
 * left = "011" and right = "101", score = 1 + 2 = 3
 * left = "0111" and right = "01", score = 1 + 1 = 2
 * left = "01110" and right = "1", score = 2 + 1 = 3
 * Example 2:
 * <p>
 * Input: s = "00111"
 * Output: 5
 * Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5
 * Example 3:
 * <p>
 * Input: s = "1111"
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= s.length <= 500
 * The string s consists of characters '0' and '1' only.
 */
public class MaxScore {
    /**
     * 最大分数
     * <p>
     * 解题思路：
     * 遍历字符串s，将其分为左子字符串和右子字符串。
     * 对于每个分割点，统计左子字符串中的0的个数和右子字符串中的1的个数，并计算它们的和。
     * 返回所有分割点中的最大和作为结果。
     * <p>
     * 算法复杂度分析：
     * 时间复杂度：O(n)，其中n是字符串s的长度。我们需要遍历整个字符串。
     * 空间复杂度：O(1)。我们只需要常数级别的额外空间来存储计数。
     */
    public static int maxScore(String s) {
        int maxScore = 0;
        int countZerosLeft = 0;
        int countOnesRight = (int) s.chars().filter(ch -> ch == '1').count();

        for (int i = 0; i < s.length() - 1; i++) {
            countZerosLeft += s.charAt(i) == '0' ? 1 : 0;
            countOnesRight -= s.charAt(i) == '1' ? 1 : 0;
            maxScore = Math.max(maxScore, countZerosLeft + countOnesRight);
        }

        return maxScore;
    }

    public static void main(String[] args) {
        String s1 = "011101";
        System.out.println("Input: " + s1);
        System.out.println("Output: " + maxScore(s1));
        // Expected output: 5

        String s2 = "00111";
        System.out.println("Input: " + s2);
        System.out.println("Output: " + maxScore(s2));
        // Expected output: 5

        String s3 = "1111";
        System.out.println("Input: " + s3);
        System.out.println("Output: " + maxScore(s3));
        // Expected output: 3
    }
}