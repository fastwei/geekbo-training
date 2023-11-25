package com.geekbo.training.leetcode.daily;

/**
 * 409. Longest Palindrome
 * Easy
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
 * <p>
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 * Example 2:
 * <p>
 * Input: s = "a"
 * Output: 1
 * Explanation: The longest palindrome that can be built is "a", whose length is 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 2000
 */
public class LongestPalindrome {

    /**
     * 解题思路：
     * <p>
     * 这个问题可以使用哈希表来解决。
     * 统计每个字符在字符串中出现的次数。
     * 遍历每个字符的出现次数，如果出现次数是偶数，直接加上出现次数；
     * 如果出现次数是奇数，加上出现次数-1，并且标记存在奇数次数的字符。
     * 如果存在奇数次数的字符，最后结果加1。
     * 返回最终的长度。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：遍历字符串和遍历哈希表的时间复杂度都是O(n)，其中n为字符串的长度，所以总时间复杂度为O(n)。
     * 空间复杂度：使用了一个长度为128的数组来存储字符出现的次数，所以空间复杂度为O(1)。
     * <p>
     * 总结：
     * <p>
     * 使用哈希表的思路，统计字符出现的次数，并计算最长回文串的长度。
     * 时间复杂度为O(n)，空间复杂度为O(1)。
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        int[] count = new int[128];

        // 统计每个字符出现的次数
        for (char ch : s.toCharArray()) {
            count[ch]++;
        }

        int length = 0;
        boolean hasOdd = false;

        // 遍历每个字符的出现次数
        for (int freq : count) {
            // 如果出现次数是偶数，直接加上出现次数
            if (freq % 2 == 0) {
                length += freq;
            }
            // 如果出现次数是奇数，加上出现次数-1，并且标记存在奇数次数的字符
            else {
                length += freq - 1;
                hasOdd = true;
            }
        }

        // 如果存在奇数次数的字符，将最后结果加1
        if (hasOdd) {
            length++;
        }

        return length;
    }

    public static void main(String[] args) {
        LongestPalindrome solution = new LongestPalindrome();

        // 测试用例1
        String s1 = "abccccdd";
        int result1 = solution.longestPalindrome(s1);
        System.out.println("测试用例1:");
        System.out.println(result1); // 预期输出: 7

        // 测试用例2
        String s2 = "a";
        int result2 = solution.longestPalindrome(s2);
        System.out.println("测试用例2:");
        System.out.println(result2); // 预期输出: 1
    }
}
