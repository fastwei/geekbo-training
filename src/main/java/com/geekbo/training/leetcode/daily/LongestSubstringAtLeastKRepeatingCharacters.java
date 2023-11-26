package com.geekbo.training.leetcode.daily;

/**
 *
 * 395. Longest Substring with At Least K Repeating Characters
 * Medium
 * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.
 *
 * if no such substring exists, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 *
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of only lowercase English letters.
 * 1 <= k <= 105
 *
 */
public class LongestSubstringAtLeastKRepeatingCharacters {
    /**
     *
     * 解题思路：
     *
     * 首先，我们统计字符串s中每个字符出现的频率，使用一个长度为26的数组count来存储。
     * 然后，我们遍历count数组，找到频率大于0但小于k的字符，记为splitChar。
     * 对于每个splitChar，我们将字符串s按照splitChar进行分割，得到多个子串。
     * 对于每个子串，我们递归调用函数longestSubstring来计算满足条件的最长子串的长度，并取其中的最大值。
     * 最后，返回最长子串的长度。
     *
     * 算法复杂度：
     *
     * 时间复杂度：我们需要遍历字符串s和count数组，所以时间复杂度为O(n)，其中n是字符串s的长度。
     * 空间复杂度：我们使用了一个长度为26的数组count来存储字符频率，所以空间复杂度为O(1)。
     *
     * 总结：
     *
     * 统计字符频率，找到频率小于k的字符，按照这些字符进行分割，计算满足条件的最长子串的长度。
     * 时间复杂度为O(n)，空间复杂度为O(1)。
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }

        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] > 0 && count[i] < k) {
                char splitChar = (char) (i + 'a');
                int maxLength = 0;
                for (String subString : s.split(String.valueOf(splitChar))) {
                    maxLength = Math.max(maxLength, longestSubstring(subString, k));
                }
                return maxLength;
            }
        }

        return s.length();
    }

    public static void main(String[] args) {
        LongestSubstringAtLeastKRepeatingCharacters solution = new LongestSubstringAtLeastKRepeatingCharacters();

        // 测试用例1
        String s1 = "aaabb";
        int k1 = 3;
        int result1 = solution.longestSubstring(s1, k1);
        System.out.println("测试用例1:");
        System.out.println(result1); // 预期输出: 3

        // 测试用例2
        String s2 = "ababbc";
        int k2 = 2;
        int result2 = solution.longestSubstring(s2, k2);
        System.out.println("测试用例2:");
        System.out.println(result2); // 预期输出: 5
    }
}
