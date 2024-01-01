package com.geekbo.training.leetcode.premium100;


/**
 *
 * 266. 回文排列
 * 简单
 * 给你一个字符串 s ，如果该字符串的某个排列是 回文 ，则返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "code"
 * 输出：false
 * 示例 2：
 *
 * 输入：s = "aab"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "carerac"
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 5000
 * s 仅由小写英文字母组成
 *
 */
class PalindromePermutationChecker {

    /**
     * 解题思路：
     * 判断一个字符串的某个排列是否为回文，可以转换为判断该字符串中每个字符的出现次数是否为偶数，
     * 或者至多只有一个字符的出现次数为奇数。
     *
     * 算法复杂度分析：
     * 时间复杂度：O(n)，其中 n 是字符串的长度。需要遍历字符串并统计每个字符的出现次数。
     * 空间复杂度：O(26)，因为只有小写英文字母，所以需要一个长度为 26 的数组来统计字符出现的次数。
     */
    public boolean canPermutePalindrome(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int oddCount = 0;
        for (int num : count) {
            if (num % 2 == 1) {
                oddCount++;
            }
        }
        return oddCount <= 1;
    }

    public static void main(String[] args) {
        PalindromePermutationChecker palindromePermutationChecker = new PalindromePermutationChecker();
        // 测试用例1，预期结果是false
        boolean result1 = palindromePermutationChecker.canPermutePalindrome("code");
        System.out.println(result1);

        // 测试用例2，预期结果是true
        boolean result2 = palindromePermutationChecker.canPermutePalindrome("aab");
        System.out.println(result2);

        // 测试用例3，预期结果是true
        boolean result3 = palindromePermutationChecker.canPermutePalindrome("carerac");
        System.out.println(result3);
    }
}