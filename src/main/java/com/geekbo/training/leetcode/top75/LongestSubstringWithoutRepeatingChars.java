package com.geekbo.training.leetcode.top75;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingChars {
    /**
     * 无重复字符的最长子串
     * 解题思路：
     *
     * 我们使用一个HashMap来存储字符及其最后出现的位置。
     * 使用两个指针，start和end，来表示当前子字符串的起始和结束位置。
     * 遍历字符串s，对于每个字符，检查它是否已经在HashMap中出现过。如果出现过，更新start指针，将其移动到当前字符上次出现的位置的下一个位置。
     * 在每次迭代中，计算当前子字符串的长度，并与之前的最大长度比较，以更新maxLength。
     * 最后，返回maxLength作为结果。
     * 算法复杂度分析：
     *
     * 时间复杂度：这个算法的时间复杂度是O(n)，其中n是字符串s的长度，因为我们只需要一次遍历字符串。
     * 空间复杂度：空间复杂度为O(min(n, m))，其中n是字符串的长度，m是字符集的大小。HashMap的大小取决于字符集的大小，最坏情况下是O(n)。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 使用HashMap来存储字符及其最后出现的位置
        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0; // 用于跟踪最长子字符串的长度
        int start = 0; // 用于标记当前子字符串的起始位置

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            // 如果字符已经出现过，需要更新起始位置
            if (charIndexMap.containsKey(currentChar)) {
                start = Math.max(start, charIndexMap.get(currentChar) + 1);
            }

            // 计算当前子字符串的长度
            int currentLength = end - start + 1;
            maxLength = Math.max(maxLength, currentLength);

            // 更新字符的最后出现位置
            charIndexMap.put(currentChar, end);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingChars solution = new LongestSubstringWithoutRepeatingChars();
        // 测试用例
        System.out.println("Example 1: " + solution.lengthOfLongestSubstring("abcabcbb")); // 预期输出: 3
        System.out.println("Example 2: " + solution.lengthOfLongestSubstring("bbbbb"));   // 预期输出: 1
        System.out.println("Example 3: " + solution.lengthOfLongestSubstring("pwwkew"));   // 预期输出: 3
    }
}
