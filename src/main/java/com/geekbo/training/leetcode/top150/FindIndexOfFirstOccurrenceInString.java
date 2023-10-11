package com.geekbo.training.leetcode.top150;


/**
 *Find the Index of the First Occurrence in a String
 *
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 * Example 1:
 *
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 *
 * Example 2:
 *
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 *
 *
 *
 *
 *
 */
public class FindIndexOfFirstOccurrenceInString {


    /**
     *
     * 优化方式：
     *
     * 添加针对 needle 长度为 0 的特殊情况处理。
     * 使用双指针方式进行匹配，即一个指针用于遍历 haystack，另一个指针用于遍历 needle。
     * 这样的优化方式使代码更加清晰和高效。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int hayLen = haystack.length();
        int needleLen = needle.length();

        // 针对特殊情况进行处理
        if (needleLen == 0) {
            return 0;
        }

        for (int i = 0; i <= hayLen - needleLen; i++) {
            int j = 0;
            // 使用双指针进行匹配
            while (j < needleLen && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            // 如果找到匹配，返回起始位置
            if (j == needleLen) {
                return i;
            }
        }

        return -1; // 未找到匹配
    }

    /**
     *
     * 解题思路：
     *
     * 首先检查特殊情况，如果 needle 为 null 或 haystack 为 null，直接返回 -1。
     * 遍历 haystack，从每个可能的起始位置开始，依次检查是否与 needle 匹配。
     * 如果找到匹配，返回起始位置；如果没有找到匹配，返回 -1。
     * 算法复杂度：
     *
     * 时间复杂度：O((m-n+1) * n)，其中 m 是 haystack 的长度，n 是 needle 的长度。最坏情况下，需要检查 haystack 中的每个可能的子串与 needle 是否匹配。
     * 空间复杂度：O(1)。只使用了常数级的额外空间。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        // 针对特殊情况进行处理
        if (needle == null || haystack == null) {
            return -1;
        }

        int hayLen = haystack.length();
        int needleLen = needle.length();

        // 遍历主串
        for (int i = 0; i <= hayLen - needleLen; i++) {
            // 遍历模式串
            int j = 0;
            while (j < needleLen && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            // 如果模式串遍历完，说明找到匹配
            if (j == needleLen) {
                return i;
            }
        }

        return -1; // 未找到匹配
    }

    public static void main(String[] args) {
        FindIndexOfFirstOccurrenceInString findIndexOfFirstOccurrenceInString = new FindIndexOfFirstOccurrenceInString();
        String haystack1 = "sadbutsad";
        String needle1 = "sad";
        String haystack2 = "leetcode";
        String needle2 = "leeto";

        System.out.println("haystack: " + haystack1);
        System.out.println("needle: " + needle1);
        System.out.println("Output: " + findIndexOfFirstOccurrenceInString.strStr(haystack1, needle1)); // 应该打印 0
        System.out.println("Output: " + findIndexOfFirstOccurrenceInString.strStr2(haystack1, needle1)); // 应该打印 0

        System.out.println("haystack: " + haystack2);
        System.out.println("needle: " + needle2);
        System.out.println("Output: " + findIndexOfFirstOccurrenceInString.strStr(haystack2, needle2)); // 应该打印 -1
        System.out.println("Output: " + findIndexOfFirstOccurrenceInString.strStr2(haystack2, needle2)); // 应该打印 -1
    }
}
