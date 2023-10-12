package com.geekbo.training.leetcode.top150;

public class ValidPalindrome {
    /**
     * 解题思路：
     * <p>
     * 将字符串全部转换为小写，以便不区分大小写。
     * 使用正则表达式[^a-z0-9]去除非字母和数字的字符，将字符串中的特殊字符、空格等移除。
     * 使用双指针，一个指向字符串的开头，一个指向字符串的结尾，逐字符比较是否相等，直到两指针相遇。
     * 如果两指针一直相等，说明是回文串；否则，不是。
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(N)，其中N是字符串s的长度。
     * 空间复杂度：O(1)，除了常数个变量，没有额外使用空间。
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        // 将字符串全部转换为小写
        s = s.toLowerCase();

        // 使用正则表达式去除非字母和数字的字符
        s = s.replaceAll("[^a-z0-9]", "");

        int left = 0;
        int right = s.length() - 1;

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
        ValidPalindrome solution = new ValidPalindrome();

        String s1 = "A man, a plan, a canal: Panama";
        boolean result1 = solution.isPalindrome(s1);
        System.out.println("Example 1: " + result1); // 输出 true

        String s2 = "race a car";
        boolean result2 = solution.isPalindrome(s2);
        System.out.println("Example 2: " + result2); // 输出 false

        String s3 = " ";
        boolean result3 = solution.isPalindrome(s3);
        System.out.println("Example 3: " + result3); // 输出 true
    }
}
