package com.geekbo.training.leetcode.daily;

/**
 * 1759. Count Number of Homogenous Substrings
 * <p>
 * Given a string s, return the number of homogenous substrings of s.
 * Since the answer may be too large, return it modulo 109 + 7.
 * <p>
 * A string is homogenous if all the characters of the string are the same.
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abbcccaa"
 * Output: 13
 * Explanation: The homogenous substrings are listed as below:
 * "a"   appears 3 times.
 * "aa"  appears 1 time.
 * "b"   appears 2 times.
 * "bb"  appears 1 time.
 * "c"   appears 3 times.
 * "cc"  appears 2 times.
 * "ccc" appears 1 time.
 * 3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.
 * Example 2:
 * <p>
 * Input: s = "xy"
 * Output: 2
 * Explanation: The homogenous substrings are "x" and "y".
 * Example 3:
 * <p>
 * Input: s = "zzzzz"
 * Output: 15
 */
class CountHomogenousSubstrings {
    /**
     * 通过遍历字符串并使用两个变量来计算连续子串的数量。
     * count 变量用于累加连续子串的数量，并使用模运算保持结果在 [0, 10^9 + 7] 范围内。
     * consecutiveCount 变量用于记录当前连续子串的长度。
     * 如果当前字符与前一个字符相同，则连续子串的长度加一，否则将连续子串的长度重置为 1。
     * 在每次更新 count 时，都将 consecutiveCount 的值加到 count 中。
     * <p>
     * 该算法的时间复杂度为 O(n)，其中 n 是字符串的长度。
     *
     * @param s
     * @return
     */
    public static int countHomogenous(String s) {
        int count = 0;
        int consecutiveCount = 0;
        char prevChar = '\0';

        for (char c : s.toCharArray()) {
            if (c == prevChar) {
                consecutiveCount++;
            } else {
                consecutiveCount = 1;
                prevChar = c;
            }

            count = (count + consecutiveCount) % ((int) 1e9 + 7);
        }

        return count;
    }

    public static void main(String[] args) {
        // 测试用例1
        String s1 = "abbcccaa";
        System.out.println("测试用例1: " + countHomogenous(s1)); // 预期输出: 13

        // 测试用例2
        String s2 = "xy";
        System.out.println("测试用例2: " + countHomogenous(s2)); // 预期输出: 2

        // 测试用例3
        String s3 = "zzzzz";
        System.out.println("测试用例3: " + countHomogenous(s3)); // 预期输出: 15
    }
}
