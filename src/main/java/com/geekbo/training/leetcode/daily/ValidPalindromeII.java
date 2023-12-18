package com.geekbo.training.leetcode.daily;

/**
 * 680. Valid Palindrome II
 * Easy
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aba"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * Example 3:
 * <p>
 * Input: s = "abc"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 */
public class ValidPalindromeII {
    /**
     * 判断字符串是否是回文串，可以删除最多一个字符
     * 解题思路：
     * <p>
     * 使用双指针方法，分别从字符串的左右两端开始遍历，判断对应的字符是否相等。
     * 如果遇到不相等的字符，尝试删除左边的字符或右边的字符，然后判断剩余的字符串是否是回文串。
     * 如果左指针等于右指针或者左指针大于右指针时，说明已经遍历完整个字符串，返回true。
     * 如果遍历过程中没有遇到不相等的字符，则返回true。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是字符串的长度。
     * 空间复杂度：O(1)。
     *
     * @param s 给定的字符串
     * @return 如果可以删除最多一个字符使得字符串成为回文串，则返回true；否则返回false
     */
    public static boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // 如果当前字符不相等，尝试删除左边字符或右边字符，然后判断剩余字符串是否是回文串
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }

        return true;
    }

    /**
     * 判断字符串是否是回文串
     *
     * @param s     给定的字符串
     * @param left  左边界
     * @param right 右边界
     * @return 如果字符串是回文串，则返回true；否则返回false
     */
    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        // 测试用例
        String s1 = "aba";
        // 删除任意一个字符后，字符串变成回文串
        // 所以返回true
        System.out.println(validPalindrome(s1));

        String s2 = "abca";
        // 删除字符'c'后，字符串变成回文串
        // 所以返回true
        System.out.println(validPalindrome(s2));

        String s3 = "abc";
        // 删除任意一个字符后，字符串不能变成回文串
        // 所以返回false
        System.out.println(validPalindrome(s3));
    }
}
